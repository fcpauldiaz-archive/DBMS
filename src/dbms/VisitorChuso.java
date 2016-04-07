/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import antlr.sqlBaseVisitor;
import antlr.sqlParser;
import static dbms.ANTGui.bdActual;
import static dbms.ANTGui.jTable1;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergio
 */
public class VisitorChuso <T> extends sqlBaseVisitor {
    private JSONParser json = new JSONParser();
    private FileManager manejador = new FileManager();
    private String globalLogic ="";
    private ArrayList indexActuales = new ArrayList();
    @Override
    public Object visitUse_schema_statement(sqlParser.Use_schema_statementContext ctx) {
        bdActual = ctx.getChild(2).getText();
        if (manejador.checkDB(bdActual)==false)
            bdActual = "";
        return super.visitUse_schema_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    public Object visitShow_schema_statement(sqlParser.Show_schema_statementContext ctx) {
        ArchivoMaestroDB ar = (ArchivoMaestroDB)json.JSONtoObject("","MasterDB" , "ArchivoMaestroDB");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Nombre de la BDD","Cantidad de tablas"});
        for(int i = 0;i<ar.getNombreDB().size();i++)
            model.addRow(new Object[]{ar.getNombreDB().get(i).getNombreDB(),ar.getNombreDB().get(i).getCantidadTablas()});
        return super.visitShow_schema_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitDrop_table_statement(sqlParser.Drop_table_statementContext ctx) {
        String nombreTabla =  ctx.getChild(2).getText(); 
        int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro que desea eliminar la tabla "+nombreTabla+" ?");
        if(dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.CANCEL_OPTION){
            return super.visitDrop_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
        }
        //ir a buscar la tabla str 
        boolean check = manejador.checkFile(bdActual, nombreTabla);
        if (check ){
            ArchivoMaestroTabla ar = (ArchivoMaestroTabla)json.JSONtoObject(bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
            Tabla tab = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
            boolean existe = false;
            for(int i = 0; i<ar.getTablas().size();i++){
                String nombreTablaActual = ar.getTablas().get(i).getNombreTabla();
                if(!nombreTabla.equals(nombreTablaActual)){
                    tab = (Tabla)json.JSONtoObject(bdActual,nombreTablaActual , "Tabla");
                    for(int j = 0 ;j<tab.getConstraints().size();j++){
                        if(tab.getConstraints().get(j).getTipo().equals("foreign")){
                            if(tab.getConstraints().get(j).getReferencesForeign().getNombreTablaRef().equals(nombreTabla)){
                                existe = true;
                            }
                        }
                    }
                }
            }
            if(existe){
                DBMS.throwMessage( "Error: La tabla: "+nombreTabla+" tiene referencias en otra(s) tablas sobre llaves foraneas, no se puede eliminar", ctx.getStart());
                return super.visitDrop_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
            }
          
            boolean eliminacion = manejador.deleteTable(bdActual, nombreTabla);
            if (eliminacion ){
                DBMS.throwMessage("Se ha eliminado la tabla " + nombreTabla, ctx.getStart());
            }
            else{
                DBMS.throwMessage("Error: ha ocurrido un problema al eliminar la tabla " + nombreTabla, ctx.getStart());
            }
        
            for(int i = 0;i<ar.getTablas().size();i++){
                if(ar.getTablas().get(i).getNombreTabla().equals(nombreTabla))
                    ar.getTablas().remove(i);
            }
            json.objectToJSON(bdActual, "MasterTable"+bdActual, ar);
        }else
            DBMS.throwMessage( "Error: La tabla: "+nombreTabla+" no existe en la base de datos "+ bdActual, ctx.getStart());
        return super.visitDrop_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }  
    @Override
    public Object visitTipo_literal(sqlParser.Tipo_literalContext ctx) {
        if (ctx.getChild(0).getChildCount()>1){
            return ctx.getChild(0).getChild(0).getText()+"€" + ctx.getChild(0).getChild(2).getText();
        }
        
       return ctx.getChild(0).getChild(0).getText();//To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object visitAccionAddColumn(sqlParser.AccionAddColumnContext ctx) {
        String nombreTabla = ctx.getParent().getChild(2).getText();
        String nombreColumna = ctx.getChild(2).getText();
        String tipo = (String)visit(ctx.getChild(3));
        TuplaColumna tupla = new TuplaColumna();
        if (tipo.contains("€")){
            int tam = Integer.parseInt(tipo.substring(tipo.indexOf("€")+1));
            tupla.setTamaño(tam);
            tipo = tipo.substring(0,tipo.indexOf("€"));
        }
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
        boolean existe = false;
        for(int i = 0;i<tabla.getColumnas().size();i++) {
            if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna)){
                existe = true;
                DBMS.throwMessage( "Error: Columna: "+nombreColumna+" ya existe en la tablana "+ nombreTabla, ctx.getStart());
               
            }   
        }
        if(!existe){
            tupla.setNombre(nombreColumna);
            tupla.setTipo(tipo);
            tabla.agregarColumna(tupla);
            json.objectToJSON(bdActual, nombreTabla, tabla);
            DBMS.throwMessage( "columna: "+nombreColumna+" agregada a la tabla: "+ nombreTabla, ctx.getStart());
        }
      return super.visitAccionAddColumn(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitAccionDropColumn(sqlParser.AccionDropColumnContext ctx) {
        String nombreTabla = ctx.getParent().getChild(2).getText();
        String nombreColumna = ctx.getChild(2).getText();
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
        boolean error = true;
        for(int i = 0;i<tabla.getColumnas().size();i++)
            if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna))
                error = false;
        if(!error){
            ArchivoMaestroTabla ar = (ArchivoMaestroTabla)json.JSONtoObject(bdActual, "MasterTable"+bdActual, "ArchivoMaestroTabla");
            Tabla tab = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
            boolean existe = false;
            ArrayList indices = new ArrayList();
            for(int i = 0; i<ar.getTablas().size();i++){
                String nombreTablaActual = ar.getTablas().get(i).getNombreTabla();
                if(!nombreTabla.equals(nombreTablaActual)){
                    tab = (Tabla)json.JSONtoObject(bdActual,nombreTablaActual , "Tabla");
                    for(int j = 0 ;j<tab.getConstraints().size();j++){
                        if(tab.getConstraints().get(j).getTipo().equals("foreign")){
                            if(tab.getConstraints().get(j).getReferencesForeign().getNombreTablaRef().equals(nombreTabla)&&tab.getConstraints().get(j).getReferencesForeign().getReferencesForeign().contains(nombreColumna)){
                                existe = true;
                                DBMS.throwMessage( "Error: La columna: "+nombreColumna+" tiene referencias en otra(s) tablas sobre llaves foraneas, no se puede eliminar", ctx.getStart());
                                return super.visitAccionDropColumn(ctx); //To change body of generated methods, choose Tools | Templates.
                            }
                        }
                    }
                }else{
                    tab = (Tabla)json.JSONtoObject(bdActual,nombreTablaActual , "Tabla");
                    for(int j = 0 ;j<tab.getConstraints().size();j++){
                       if(tab.getConstraints().get(j).getTipo().equals("primary")){
                            if(tab.getConstraints().get(j).getReferences().contains(nombreColumna)){
                                tabla.getConstraints().get(j).getReferences().remove(tabla.getConstraints().get(j).getReferences().lastIndexOf(nombreColumna));
                                if(tabla.getConstraints().get(j).getReferences().isEmpty())
                                    indices.add(j);
                            }
                        }else
                           if(tab.getConstraints().get(j).getTipo().equals("check")){
                               //hay un error en esta línea porque se cambio la estructura de tupla check
                               //ahora es un arraylist.
                               int cont=0;
                               for(int p = 0;p<tab.getConstraints().get(j).getTuplaCheck().size();p++)
                                   if(tab.getConstraints().get(j).getTuplaCheck().get(p).getOp1().contains(nombreColumna)||tab.getConstraints().get(j).getTuplaCheck().get(p).getOp2().contains(nombreColumna)){
                                       tab.getConstraints().get(j).getTuplaCheck().remove(p-cont);
                                       cont++;
                                   }
                               if(tab.getConstraints().get(j).getTuplaCheck().isEmpty()){
                                    indices.add(j);
                               }
                           }else{
                               if(tab.getConstraints().get(j).getReferences().contains(nombreColumna)){
                                    tabla.getConstraints().get(j).getReferences().remove(tabla.getConstraints().get(j).getReferences().lastIndexOf(nombreColumna));
                                    if(tabla.getConstraints().get(j).getReferences().isEmpty())
                                        indices.add(j);
                                }
                           }
                    }
                }
            }
            if(!existe){
                for(int i = 0;i<tabla.getColumnas().size();i++)
                    if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna))
                        tabla.getColumnas().remove(i);
                for(int i = 0;i<indices.size();i++)
                    tabla.getConstraints().remove((int) indices.get(i)-i);
                DBMS.throwMessage( "columna: "+nombreColumna+" eliminada de la tabla: "+ nombreTabla, ctx.getStart());
                json.objectToJSON(bdActual, nombreTabla, tabla);
            }
        }else
            DBMS.throwMessage( "Error: Columna: "+nombreColumna+" no existe en la tabla "+ nombreTabla, ctx.getStart());
        return super.visitAccionDropColumn(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitAccionDropConstraint(sqlParser.AccionDropConstraintContext ctx) {
        String nombreTabla = ctx.getParent().getChild(2).getText();
        String nombreConstraint = ctx.getChild(2).getText();
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
        ArrayList<Constraint> constraints = tabla.getConstraints();
        boolean existe = false;
        int indice =0;
        for(int i = 0;i<constraints.size();i++)
         if(constraints.get(i).getNombre().equals(nombreConstraint)){
             existe = true;
             indice = i;
         }
        if(!existe)
             DBMS.throwMessage( "Error: constraint: "+nombreConstraint+" no existe en la tabla "+ nombreTabla, ctx.getStart());
        else{
             tabla.getConstraints().remove(indice);
             DBMS.throwMessage( "constraint: "+nombreConstraint+" eliminado la tabla "+ nombreTabla, ctx.getStart());
             json.objectToJSON(bdActual, nombreTabla, tabla);
        }
        return super.visitAccionDropConstraint(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitAlter_table_statement(sqlParser.Alter_table_statementContext ctx) {
        String nombreTabla =  ctx.getChild(2).getText();  
        boolean check = manejador.checkFile(bdActual, nombreTabla);
        if (check ){
            for (int i = 0;i<ctx.getChildCount();i++)
                this.visit(ctx.getChild(i));
        }else
            DBMS.throwMessage( "Error: La tabla: "+nombreTabla+" no existe en la base de datos "+ bdActual, ctx.getStart());
        return null;
    }
    /**
     * Método que regresa un array con los nombres de los ids en constraints
     * 
     * @param ctx
     * @return ArrayList de Strings
     */
    @Override
    public Object visitId_list(sqlParser.Id_listContext ctx) {
        
        ArrayList<String> listadoId = new ArrayList();
        for (int i = 0; i<ctx.getChildCount(); i++) {
            
            if (!ctx.getChild(i).getText().equals(",")) {
                listadoId.add(ctx.getChild(i).getText());
            }
        }
        
       return listadoId; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object visitConstraintPrimaryKeyAlter(sqlParser.ConstraintPrimaryKeyAlterContext ctx) {
        String nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
        String nombreConstraint = ctx.getChild(0).getText();
        String tipoConstraint = ctx.getChild(1).getText();
        ArrayList<String> listadoIDS = (ArrayList<String>)visit(ctx.getChild(4));
        Constraint constraint = new Constraint();
        constraint.setTipo(tipoConstraint);
        constraint.setNombre(nombreConstraint);
         //ahora busco la tabla y verifico los campos de los constraints
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual+"/", nombreTabla, "Tabla");
        Tabla tabla_c = tabla;
        if(tipoConstraint.equals("primary"))
            for(int i = 0;i<tabla.getConstraints().size();i++){
                if(tabla.getConstraints().get(i).getTipo().equals("primary")){
                     DBMS.throwMessage("Error: Constraint primary key ya existe en la tabla " + nombreTabla, ctx.getStart() );
                    return super.visitConstraintPrimaryKeyAlter(ctx); //To change body of generated methods, choose Tools | Templates.
                }
            }        
        ArrayList<TuplaColumna> camposActuales = tabla_c.getColumnas();
        for (int i =0;i<tabla.getConstraints().size();i++){
             if (tabla.getConstraints().get(i).getNombre().equals(nombreConstraint)){
                 DBMS.throwMessage("Error: el nombre del constraint " + nombreConstraint + " ya ha sido usado",ctx.getStart());
                 tabla = null;
                 return null;
                }
        }
        boolean verificador = revisarListadoIDs(camposActuales, listadoIDS);
        if (verificador){
            constraint.setReferences(listadoIDS);
            tabla.addConstraint(constraint);
            DBMS.debug("Se ha agregado el constraint " + nombreConstraint + " a la tabla " + nombreTabla, ctx.getStart());
            json.objectToJSON(bdActual, nombreTabla, tabla);
        }
        else{
             DBMS.throwMessage("Error: campo "+listadoIDS+" no existe en la tabla " + nombreTabla, ctx.getStart() );
             tabla = null; //ya no se guarda la tabla.
        }
        return super.visitConstraintPrimaryKeyAlter(ctx); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public Object visitConstraintForeignKeyAlter(sqlParser.ConstraintForeignKeyAlterContext ctx) {
        String nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
        String nombreConstraint = ctx.getChild(0).getText();
        String tipoConstraint = ctx.getChild(1).getText();
        ArrayList<String> listadoIDS = (ArrayList<String>)visit(ctx.getChild(4));
        Constraint constraint = new Constraint();
        constraint.setTipo(tipoConstraint);
        constraint.setNombre(nombreConstraint);
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        for(int i = 0;i<tabla.getConstraints().size();i++)
            if(tabla.getConstraints().get(i).getNombre().equals(nombreConstraint)){
                DBMS.throwMessage("Error: Constraint "+nombreConstraint+" ya existe en la tabla " + nombreTabla,ctx.getStart() );
                return super.visitConstraintForeignKeyAlter(ctx); //To change body of generated methods, choose Tools | Templates.
            }
         String nombreTablaRef = ctx.getChild(7).getText();
            ArrayList<String> listadoIDSREF = (ArrayList<String>)visit(ctx.getChild(9));
            
            //ahora tengo que revisar que existe la tabla que se referencia
            //y revisar que el listado IDS REF existe en esa tabla.
            
            //paso 1. ir a buscar tabla de referencia
            Tabla tablaRef = (Tabla)json.JSONtoObject(bdActual, nombreTablaRef, "Tabla");
            //TODO: ¿Qué pasa si no existe la tabla? falta validar esto.
            //paso 2. verificar que los campos de referencia existan en la tabla de referencia
            ArrayList<TuplaColumna> columnasRef = tablaRef.getColumnas();
         
            boolean verificadorRef = this.revisarListadoIDs(columnasRef, listadoIDSREF);
            //si no existen los campos en la referencia
            if (!verificadorRef){//no pasa la validación
                DBMS.throwMessage("No existe algún campo de " +listadoIDSREF, ctx.getStart());
                tabla = null;
                return null;
            }
            //si llega aquí es porque si existen
            TuplaRefForeign tuplaForeign = new TuplaRefForeign(nombreTablaRef);
            tuplaForeign.setReferencesForeign(listadoIDSREF);
            constraint.setReferencesForeign(tuplaForeign);
            
             //ahora busco la tabla y verifico los campos de los constraints
            Tabla tabla_c = tabla;

            ArrayList<TuplaColumna> camposActuales = tabla_c.getColumnas();
            boolean verificador = revisarListadoIDs(camposActuales, listadoIDS);
            if (verificador){
                constraint.setReferences(listadoIDS);
                tabla.addConstraint(constraint);
                DBMS.debug("Se ha agregado el constraint " + nombreConstraint,ctx.getStart());
                json.objectToJSON(bdActual, nombreTabla, tabla);
            }
            else{
                 DBMS.throwMessage("Error: campo "+listadoIDS+" no existe en la tabla " + nombreTabla,ctx.getStart() );
                 tabla = null; //ya no se guarda la tabla.
            }
        
        
        return super.visitConstraintForeignKeyAlter(ctx); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public Object visitConstraintCheckAlter(sqlParser.ConstraintCheckAlterContext ctx) {
         String nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
            Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
            String nombreConstraint = ctx.getChild(0).getText();
            String tipoConstraint = ctx.getChild(1).getText();
           
            Constraint constraint = new Constraint();
            constraint.setTipo(tipoConstraint);
            constraint.setNombre(nombreConstraint);
        
            
         
            
            ArrayList<Constraint> columnas = tabla.getConstraints();
            boolean verCheck = true;
            for (int i = 0;i<columnas.size();i++){
                if (columnas.get(i).getNombre().equals(nombreConstraint)){
                    verCheck= false;
                }
            }
            if (!verCheck){
                tabla = null;
                DBMS.throwMessage("Error: El nombre del constraint "+nombreConstraint + " ya existe " , ctx.getStart());
                return null;
            }
           
            for (int i = 3;i<ctx.getChildCount();i++){
                    T visitCheck = (T)this.visit(ctx.getChild(i));
                    if (visitCheck instanceof TuplaCheck){
                        if (!this.globalLogic.isEmpty()){
                           
                            ((TuplaCheck)visitCheck).setOperadorLogico(globalLogic);
                        }
                        constraint.addTuplaCheck((TuplaCheck)visitCheck);
                    }
                    
            }
            this.globalLogic = "";
            tabla.addConstraint(constraint);
            DBMS.debug("Se ha agregado el constraint " + nombreConstraint, ctx.getStart());
            json.objectToJSON(bdActual, nombreTabla, tabla);
        
        
        return super.visitConstraintCheckAlter(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object visitLogic(sqlParser.LogicContext ctx) {
        this.globalLogic = ctx.getChild(0).getText();
        return super.visitLogic(ctx); //To change body of generated methods, choose Tools | Templates.
    }
      @Override
    public Object visitCheck_exp(sqlParser.Check_expContext ctx) {
        String op1Check = ctx.getChild(0).getText();
        String operatorCheck = ctx.getChild(1).getText();
        String op2Check = ctx.getChild(2).getText();
        TuplaCheck tupla = new TuplaCheck();
        tupla.setOp1(op1Check);
        tupla.setOperador(operatorCheck);
        tupla.setOp2(op2Check);
        return tupla; //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean revisarListadoIDs(ArrayList<TuplaColumna> camposActuales, ArrayList<String> listadoIDS){
        boolean verificador = true;
        for (int i = 0;i<listadoIDS.size();i++){
            String idActual = listadoIDS.get(i);
            boolean verificadorInterno = false;
            for (int j = 0;j<camposActuales.size();j++){
                String campoActual = camposActuales.get(j).getNombre();
                if (idActual.equals(campoActual)){
                    verificadorInterno = true;
                }
            }
            verificador = verificadorInterno;

        }
        return verificador;
    }

    @Override
    public Object visitShow_table_statement(sqlParser.Show_table_statementContext ctx) {
        if(!bdActual.isEmpty()){
            ArchivoMaestroTabla archivo = (ArchivoMaestroTabla)json.JSONtoObject(bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            model.setColumnIdentifiers(new Object[]{"Nombre de la Tabla","Cantidad de Registros"});
            for(int i = 0;i<archivo.getTablas().size();i++)
                model.addRow(new Object[]{archivo.getTablas().get(i).getNombreTabla(),archivo.getTablas().get(i).getCantidadRegistros()});
        }else
           DBMS.throwMessage("Error: No hay base de datos seleccionada ", ctx.getStart() ); 
        return super.visitShow_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object visitFinal_where(sqlParser.Final_whereContext ctx) {
        ArrayList indexes = new ArrayList();
         String nombrePapa = ctx.getParent().getChild(0).getText();
        String nombreTabla = "";
        if(nombrePapa.equals("delete"))
            nombreTabla = ctx.getParent().getChild(2).getText();
        else
            if(nombrePapa.equals("update"))
                nombreTabla = ctx.getParent().getChild(1).getText();
            else
                nombreTabla = ctx.getParent().getChild(3).getText();
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        for(int i = 0;i<ctx.getChildCount();i++){
            indexes = (ArrayList) this.visit(ctx.getChild(i));
            indexActuales = indexes;
        }
        return indexes;
    }
    @Override
    public Object visitFirst_where_statement(sqlParser.First_where_statementContext ctx) {
        indexActuales = new ArrayList();
        String nombrePapa = ctx.getParent().getParent().getChild(0).getText();
        String nombreTabla = "";
        if(nombrePapa.equals("delete"))
            nombreTabla = ctx.getParent().getParent().getChild(2).getText();
        else
            if(nombrePapa.equals("update"))
                nombreTabla = ctx.getParent().getParent().getChild(1).getText();
            else
                nombreTabla = ctx.getParent().getParent().getChild(3).getText();
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        for(int i = 0;i<tabla.getDataInTable().size();i++)
            indexActuales.add(i);
        return (ArrayList) this.visit(ctx.getChild(0));
    }

    @Override
    public Object visitWhere_statement(sqlParser.Where_statementContext ctx) {
         String nombrePapa = ctx.getParent().getParent().getChild(0).getText();
        String nombreTabla = "";
        if(nombrePapa.equals("delete"))
            nombreTabla = ctx.getParent().getParent().getChild(2).getText();
        else
            if(nombrePapa.equals("update"))
                nombreTabla = ctx.getParent().getParent().getChild(1).getText();
            else
                nombreTabla = ctx.getParent().getParent().getChild(3).getText();
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        String logic = ctx.getChild(0).getText().toLowerCase();
        ArrayList indexReturn = new ArrayList();
        indexReturn.addAll(indexActuales);
        if(logic.equals("or")){
            indexActuales = new ArrayList();
            for(int i = 0;i<tabla.getDataInTable().size();i++)
                indexActuales.add(i);
            indexReturn.addAll((ArrayList) this.visit(ctx.getChild(1)));
            return indexReturn;
        }else
            if(logic.equals("and")){
               return (ArrayList) this.visit(ctx.getChild(1)); 
            }
        return null;
    }

    @Override
    public Object visitCondition(sqlParser.ConditionContext ctx) {
        String nombrePapa = ctx.getParent().getParent().getParent().getChild(0).getText();
        String nombreTabla = "";
        if(nombrePapa.equals("delete"))
            nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
        else
            if(nombrePapa.equals("update"))
                nombreTabla = ctx.getParent().getParent().getParent().getChild(1).getText();
            else
                nombreTabla = ctx.getParent().getParent().getParent().getChild(3).getText();
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        String op1 =(String) (this.visit(ctx.getChild(0)));
        op1 = op1.toUpperCase();
        String op2 =(String) (this.visit(ctx.getChild(2)));
        op2 = op2.toUpperCase();
        String operacion = ctx.getChild(1).getText();
        //CAMBIAR CAMBIAR CAMBIAR CAMBIAR CAMBIAR EL RETURN
        if(!op1.equals(op2)){
            DBMS.throwMessage( "Error: La comparacion no se realizo con los mismos tipos de variables", ctx.getStart());
            return "error";
        }
        //la columna esta en el primero y no en el segundo
        ArrayList registro = new ArrayList();
        ArrayList listaFinal = new ArrayList();
        if(ctx.getChild(0).getChildCount()>1&&ctx.getChild(2).getChildCount()<2){
            int columna = columnExist(nombreTabla,ctx.getChild(0).getChild(2).getText());
            for(int i = 0;i<indexActuales.size();i++){
                registro = tabla.getDataInTable().get((int)indexActuales.get(i));
                switch(operacion){
                    case "<":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion < solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1<operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion <= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                           int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1<=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    case ">":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion > solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1>operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case ">=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion >= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1>=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<>":
                        if(op1.equals("INT")){
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!registro.get(columna).equals(ctx.getChild(2).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "!=":
                        if(op1.equals("INT")){
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!registro.get(columna).equals(ctx.getChild(2).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "=":
                        if(op1.equals("INT")){
                            int operador2 = Integer.parseInt(ctx.getChild(2).getText());
                            int operador1 =(int)Math.round((Double) registro.get(columna));
                            if(operador1==operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else{
                            if(registro.get(columna).equals(ctx.getChild(2).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                }
            }
        }
        //la columna no esta en el primero sino que en el segundo 
        if(ctx.getChild(0).getChildCount()<2&&ctx.getChild(2).getChildCount()>1){
            int columna = columnExist(nombreTabla,ctx.getChild(2).getChild(2).getText());
            for(int i = 0;i<indexActuales.size();i++){
                registro = tabla.getDataInTable().get((int)indexActuales.get(i));
                switch(operacion){
                    case "<":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion < solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1<operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion <= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                           int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1<=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    case ">":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion > solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1>operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case ">=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion >= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1>=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<>":
                        if(op1.equals("INT")){
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!registro.get(columna).equals(ctx.getChild(0).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "!=":
                        if(op1.equals("INT")){
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!registro.get(columna).equals(ctx.getChild(0).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "=":
                        if(op1.equals("INT")){
                            int operador1 = Integer.parseInt(ctx.getChild(0).getText());
                            int operador2 =(int)Math.round((Double) registro.get(columna));
                            if(operador1==operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(registro.get(columna).equals(ctx.getChild(0).getText())){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                }
            }
        }
        //la columna  esta en el primero y en el segundo 
        if(ctx.getChild(0).getChildCount()>1&&ctx.getChild(2).getChildCount()>1){
            int columna1 = columnExist(nombreTabla,ctx.getChild(2).getChild(0).getText());
            int columna2 = columnExist(nombreTabla,ctx.getChild(2).getChild(2).getText());
            for(int i = 0;i<indexActuales.size();i++){
                registro = tabla.getDataInTable().get((int)indexActuales.get(i));
                switch(operacion){
                    case "<":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion < solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1<operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion <= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                           int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1<=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    case ">":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion > solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1>operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case ">=":
                        if(!op1.equals("INT")){
                            DBMS.throwMessage( "Error: la comparacion >= solo se puede entre int", ctx.getStart());
                            return "error";
                        }else{
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1>=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }
                    break;
                    case "<>":
                        if(op1.equals("INT")){
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!registro.get(columna1).equals(registro.get(columna2))){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "!=":
                        if(op1.equals("INT")){
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1!=operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(!(registro.get(columna1).equals(registro.get(columna2)))){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                    case "=":
                        if(op1.equals("INT")){
                            int operador1 =(int)Math.round((Double) registro.get(columna1));
                            int operador2 =(int)Math.round((Double) registro.get(columna2));
                            if(operador1==operador2){
                                listaFinal.add(indexActuales.get(i));
                            }
                        }else
                            if(registro.get(columna1).equals(registro.get(columna2))){
                                listaFinal.add(indexActuales.get(i));
                            }
                    break;
                }
            }
        }
        indexActuales = new ArrayList();
        return listaFinal;
    }

    @Override
    public Object visitIdentifier(sqlParser.IdentifierContext ctx) {
        if(ctx.getChildCount()>1){
            String nombreTabla = ctx.getChild(0).getText();
            String nombreColumna = ctx.getChild(2).getText();
            Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
            if(tableExist(nombreTabla)<0){
                DBMS.throwMessage( "Error: La tabla: "+nombreTabla+" no existe en la base de datos "+ bdActual, ctx.getStart());
                return "error";
            }
            int indiceColumna = columnExist(nombreTabla,nombreColumna);
            if(indiceColumna<0){
                DBMS.throwMessage( "Error: La Columna: "+nombreColumna+" no existe en la tabla "+ nombreTabla, ctx.getStart());
                return "error";
            }
            return tabla.getColumnas().get(indiceColumna).getTipo();
        }else{
            String contenido = ctx.getChild(0).getText();
            try {
                Integer.parseInt(contenido);
                return "INT";
            } catch (NumberFormatException e) {
                if(contenido.contains("\'")){
                    if(!contenido.startsWith("\'")){
                        DBMS.throwMessage( "String o char debe comenzar con \'", ctx.getStart());
                        return "error";
                    }else
                        if(!contenido.endsWith("\'")){
                            DBMS.throwMessage( "String o char debe terminar con \'", ctx.getStart());
                            return "error";
                        }else
                            return "CHAR";
                }else{
                    DBMS.throwMessage( "Error de referencia, debe ir de la forma tabla.columna ", ctx.getStart());
                    return "error";
                }
             }
            
        }
    }
    public int tableExist(String nombreTabla){
        ArchivoMaestroTabla archivo = (ArchivoMaestroTabla)json.JSONtoObject(bdActual, "MasterTable"+bdActual, "ArchivoMaestroTabla");
        for(int i = 0;i<archivo.getTablas().size();i++)
            if(archivo.getTablas().get(i).getNombreTabla().equals(nombreTabla))
                return i;
        return -1;
    }
    public int columnExist(String nombreTabla,String nombreColumna){
        Tabla tabla = (Tabla) json.JSONtoObject(bdActual, nombreTabla, "Tabla");
        for(int i = 0;i<tabla.getColumnas().size();i++)
            if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna))
                return i;
        return -1;
    }
    
}
