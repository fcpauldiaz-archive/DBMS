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
        for(int i = 0;i<tabla.getColumnas().size();i++)
            if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna)){
                existe = true;
                DBMS.throwMessage( "Error: Columna: "+nombreColumna+" ya existe en la tabla "+ nombreTabla, ctx.getStart());
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
            ArchivoMaestroTabla ar = (ArchivoMaestroTabla)json.JSONtoObject(bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
            Tabla tab = (Tabla)json.JSONtoObject(bdActual,nombreTabla , "Tabla");
            boolean existe = false;
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
                                existe = true;
                                DBMS.throwMessage( "Error: La tabla: "+nombreColumna+" tiene referencias a constraints de llaves pimarias, no se puede eliminar", ctx.getStart());
                                return super.visitAccionDropColumn(ctx); //To change body of generated methods, choose Tools | Templates.
                            }
                        }else
                           if(tab.getConstraints().get(j).getTipo().equals("check")){
                               if(tab.getConstraints().get(j).getTuplaCheck().getOp1().equals(nombreColumna)||tab.getConstraints().get(j).getTuplaCheck().getOp2().equals(nombreColumna)){
                                    existe = true;
                                    DBMS.throwMessage( "Error: La columna: "+nombreColumna+" tiene referencias a constraints de check, no se puede eliminar", ctx.getStart());
                                    return super.visitAccionDropColumn(ctx); //To change body of generated methods, choose Tools | Templates.
                               }
                           }
                    }
                }
            }
            if(!existe){
                for(int i = 0;i<tabla.getColumnas().size();i++)
                    if(tabla.getColumnas().get(i).getNombre().equals(nombreColumna))
                        tabla.getColumnas().remove(i);
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
        return super.visitAlter_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
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
    public Object visitConstraintPrimaryKey(sqlParser.ConstraintPrimaryKeyContext ctx) {
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
        return super.visitConstraintPrimaryKey(ctx); //To change body of generated methods, choose Tools | Templates.
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
}
