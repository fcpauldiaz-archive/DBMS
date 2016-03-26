/**
* Universidad Del Valle de Guatemala
* 16-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import antlr.sqlBaseVisitor;
import antlr.sqlParser;
import java.util.ArrayList;


/**
 *
 * @author Pablo
 * @param <T>
 */
public class Visitor<T> extends sqlBaseVisitor {

    private FileManager manejador = new FileManager();
    private ArchivoMaestroDB  mdb = new ArchivoMaestroDB();
    private ArchivoMaestroTabla mdt = new ArchivoMaestroTabla();
    private JSONParser json = new JSONParser();
    private Tabla tabla;
    public String bdActual ="";
    public String tablaActual="";
    
    /**
     * Método que crea la base de datos y actualiza el archivo maestro de bases de datos.
     * @param ctx
     * @return 
     */
    @Override
    public Object visitSchema_definition(sqlParser.Schema_definitionContext ctx) {
        
        String nombreBaseDatos = ctx.getChild(2).getText();
        DBMS.debug("DB name " + nombreBaseDatos);
        boolean carpeta = manejador.createDirectory(nombreBaseDatos);
        boolean master = manejador.checkFile("DB/", "MasterDB");
        if (carpeta){
            mdb.agregarDB(nombreBaseDatos);
        }
        if (carpeta && master){
        
         ArchivoMaestroDB masterSaved = (ArchivoMaestroDB) json.JSONtoObject("DB/", "MasterDB", "ArchivoMaestroDB");
         mdb.agregarExistente(masterSaved);
         
        
        }
        if (carpeta){
            DBMS.debug("Se ha creado la base de datos", ctx.getStart());
            json.objectToJSON("DB/", "MasterDB", mdb);
            mdb = new ArchivoMaestroDB(); //sirve para perder la referencia
        }
        else{
            DBMS.throwError("Error: la base de datos ya existe", ctx.getStart());
        }
        
        
        return super.visitSchema_definition(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Método que crea la tabla
     * Este método tiene que visitar los hijos para terminar de crear la tabla.
     * @param ctx
     * @return 
     */
    @Override
    public Object visitTable_definition(sqlParser.Table_definitionContext ctx) {
        
        tabla = new Tabla();
        String nombreTabla = ctx.getChild(2).getText();
        tablaActual = nombreTabla;
       
            for (int i = 0;i<ctx.getChildCount();i++){
            this.visit(ctx.getChild(i));
        }
        
       
        ArchivoMaestroDB masterSaved = (ArchivoMaestroDB) json.JSONtoObject("DB/", "MasterDB", "ArchivoMaestroDB");
       
        if (manejador.checkFileTabla(bdActual, nombreTabla)){
            DBMS.debug("Nombre tabla " + nombreTabla);
            mdt.agregarTabla(nombreTabla);
            if (manejador.checkFile(bdActual, nombreTabla)){
                ArchivoMaestroTabla masterTable = (ArchivoMaestroTabla) json.JSONtoObject("DB/"+bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
                mdt.agregarExistente(masterTable);
            }
            json.objectToJSON("DB/"+bdActual+"/", "MasterTable"+bdActual, mdt);
            
            
            
            masterSaved.aumentarTablaCount(bdActual);
            mdb.agregarExistente(masterSaved);
            
            json.objectToJSON("DB/", "MasterDB", mdb);
            if (tabla != null)
                json.objectToJSON("DB/"+bdActual+"/", nombreTabla, tabla);
            
            mdb = new ArchivoMaestroDB(); //sirve para perder la referencia
        }
        else{
            if (bdActual.isEmpty())
                DBMS.throwError("Error: No se ha seleccionado la base de datos", ctx.getStart());
            else
                DBMS.throwError("Error: La tabla ya existe", ctx.getStart());
        }
       
        mdt = new ArchivoMaestroTabla(); //sirve para perder la referencia
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitColumn(sqlParser.ColumnContext ctx) {
        
        String nombreColumna = ctx.getChild(0).getText();
        String tipo = (String)visit(ctx.getChild(1));
        TuplaColumna tupla = new TuplaColumna();
        if (tipo.contains("€")){
            int tam = Integer.parseInt(tipo.substring(tipo.indexOf("€")+1));
            tupla.setTamaño(tam);
            tipo = tipo.substring(0,tipo.indexOf("€"));
        }
        DBMS.debug(nombreColumna);
        DBMS.debug(tipo);

        tupla.setNombre(nombreColumna);
        tupla.setTipo(tipo);
        tabla.agregarColumna(tupla);
        return null;
    }

    @Override
    public Object visitUse_schema_statement(sqlParser.Use_schema_statementContext ctx) {
        
        bdActual = ctx.getChild(2).getText();
       
        if (manejador.checkDB(bdActual)==false)
            bdActual = "";
        return super.visitUse_schema_statement(ctx); //To change body of generated methods, choose Tools | Templates.
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
        Tabla tabla_c = tabla;
                
        ArrayList<TuplaColumna> camposActuales = tabla_c.getColumnas();
        for (int i =0;i<tabla.getConstraints().size();i++){
             if (tabla.getConstraints().get(i).getNombre().equals(nombreConstraint)){
                 DBMS.debug("Error: el nombre del constraint " + nombreConstraint + " ya ha sido usado");
                 tabla = null;
                 return null;
                }
        }
        boolean verificador = revisarListadoIDs(camposActuales, listadoIDS);
        if (verificador){
            constraint.setReferences(listadoIDS);
            tabla.addConstraint(constraint);
        }
        else{
             DBMS.debug("campo "+listadoIDS+" no existe en la tabla " + nombreTabla );
             tabla = null; //ya no se guarda la tabla.
        }
        
        return super.visitConstraintPrimaryKey(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitConstraintCheck(sqlParser.ConstraintCheckContext ctx) {
        
            String nombreConstraint = ctx.getChild(0).getText();
            String tipoConstraint = ctx.getChild(1).getText();
           
            Constraint constraint = new Constraint();
            constraint.setTipo(tipoConstraint);
            constraint.setNombre(nombreConstraint);
        
            System.out.println("entra");
            //tengo que revisar que exista este campo en la tabla actual
            String op1 = ctx.getChild(3).getText();
            //este solo sirve para guardar
            String relOp = ctx.getChild(4).getText();
            
            //este campo puede ser número o un ID, como lo identifico?.
            //bueno, no me sirve saber esto hasta que haga la operación.
            String op2 = ctx.getChild(5).getText();
            ArrayList<TuplaColumna> columnas = tabla.getColumnas();
            boolean verCheck = false;
            for (int i = 0;i<columnas.size();i++){
                if (columnas.get(i).getNombre().equals(op1)){
                    verCheck= true;
                }
            }
            if (!verCheck){
                tabla = null;
                return null;
            }
            TuplaCheck check = new TuplaCheck();
            check.setOp1(op1);
            check.setOp2(op2);
            
            check.setOperador(relOp);
            constraint.setTuplaCheck(check);
            tabla.addConstraint(constraint);
            
        
        
        return super.visitConstraintCheck(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitConstraintForeignKey(sqlParser.ConstraintForeignKeyContext ctx) {
        String nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
        String nombreConstraint = ctx.getChild(0).getText();
        String tipoConstraint = ctx.getChild(1).getText();
        ArrayList<String> listadoIDS = (ArrayList<String>)visit(ctx.getChild(4));
        Constraint constraint = new Constraint();
        constraint.setTipo(tipoConstraint);
        constraint.setNombre(nombreConstraint);
        
         String nombreTablaRef = ctx.getChild(7).getText();
            ArrayList<String> listadoIDSREF = (ArrayList<String>)visit(ctx.getChild(9));
            
            //ahora tengo que revisar que existe la tabla que se referencia
            //y revisar que el listado IDS REF existe en esa tabla.
            
            //paso 1. ir a buscar tabla de referencia
            Tabla tablaRef = (Tabla)json.JSONtoObject("DB/"+bdActual+"/", nombreTablaRef, "Tabla");
            //TODO: ¿Qué pasa si no existe la tabla? falta validar esto.
            //paso 2. verificar que los campos de referencia existan en la tabla de referencia
            ArrayList<TuplaColumna> columnasRef = tablaRef.getColumnas();
         
            boolean verificadorRef = this.revisarListadoIDs(columnasRef, listadoIDSREF);
            //si no existen los campos en la referencia
            if (!verificadorRef){//no pasa la validación
                tabla = null;
                return null;
            }
            //si llega aquí es porque si existen
            TuplaRefForeign tuplaForeign = new TuplaRefForeign(nombreTablaRef);
            tuplaForeign.setReferencesForeign(listadoIDS);
            constraint.setReferencesForeign(tuplaForeign);
            
             //ahora busco la tabla y verifico los campos de los constraints
            Tabla tabla_c = tabla;

            ArrayList<TuplaColumna> camposActuales = tabla_c.getColumnas();
            boolean verificador = revisarListadoIDs(camposActuales, listadoIDS);
            if (verificador){
                constraint.setReferences(listadoIDS);
                tabla.addConstraint(constraint);
            }
            else{
                 DBMS.debug("campo "+listadoIDS+" no existe en la tabla " + nombreTabla );
                 tabla = null; //ya no se guarda la tabla.
            }
        
        
        return super.visitConstraintForeignKey(ctx); //To change body of generated methods, choose Tools | Templates.
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
    public Object visitTipo_literal(sqlParser.Tipo_literalContext ctx) {
        if (ctx.getChild(0).getChildCount()>1){
            return ctx.getChild(0).getChild(0).getText()+"€" + ctx.getChild(0).getChild(2).getText();
        }
        
       return ctx.getChild(0).getChild(0).getText();//To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Eliminar una base de datos, todas las tablas y actualizar el archivo maestro
     * @param ctx
     * @return 
     */
    @Override
    public Object visitDrop_schema_statement(sqlParser.Drop_schema_statementContext ctx) {
        
        String nombreDB = ctx.getChild(2).getText();
        boolean verificador = manejador.checkDB(nombreDB);
        if (!verificador){
            DBMS.debug("La base de datos " + nombreDB + " no existe");
            return super.visitDrop_schema_statement(ctx);
        }
        manejador.eliminarDB(nombreDB);
        ArchivoMaestroDB mdbActual = (ArchivoMaestroDB)json.JSONtoObject("DB/", "MasterDB", "ArchivoMaestroDB");
        ArrayList<TuplaDB> arrayDB = mdbActual.getNombreDB();
        ArrayList<TuplaDB> modificarArrayDB = arrayDB;
        for (int i = 0;i<arrayDB.size();i++){
            if (arrayDB.get(i).getNombreDB().equals(nombreDB)){
                modificarArrayDB.remove(i);
            }
        }
        json.objectToJSON("DB/", "MasterDB", mdbActual);
        
        
        return super.visitDrop_schema_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitAlter_database_statement(sqlParser.Alter_database_statementContext ctx) {
        
        String nombreDBActual = ctx.getChild(2).getText();
        String nombreNuevoDB = ctx.getChild(5).getText();
        manejador.renameFile(bdActual, nombreDBActual, nombreNuevoDB);
        ArchivoMaestroDB mdbActual = (ArchivoMaestroDB)json.JSONtoObject("DB/", "MasterDB", "ArchivoMaestroDB");
        ArrayList<TuplaDB> arrayDB = mdbActual.getNombreDB();
        ArrayList<TuplaDB> modificarArrayDB = arrayDB;
        for (int i = 0;i<arrayDB.size();i++){
            if (arrayDB.get(i).getNombreDB().equals(nombreDBActual)){
                modificarArrayDB.get(i).setNombreDB(nombreNuevoDB);
            }
        }
        json.objectToJSON("DB/", "MasterDB", mdbActual);
        
        
        return super.visitAlter_database_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitRename_table_statement(sqlParser.Rename_table_statementContext ctx) {
        String nombreActual = ctx.getChild(2).getText();
        String nombreNuevo = ctx.getChild(5).getText();
       
        //antes reviso que exista la tabla en la base de datos actual
        boolean verificador = manejador.checkFile(bdActual, nombreActual);
        if (!verificador){
            DBMS.debug("El archivo " + nombreActual + " no existe");
            return super.visitRename_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
        }
        
        //renombra el archivo
        manejador.renameFileJSON(bdActual, nombreActual, nombreNuevo);
        ArchivoMaestroTabla tablita = (ArchivoMaestroTabla)json.JSONtoObject("DB/"+bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
        ArrayList<TuplaTabla> arrayTablas = tablita.getTablas();
        for (int i =0;i<arrayTablas.size();i++){
            TuplaTabla t = arrayTablas.get(i);
            if (t.getNombreTabla().equals(nombreActual)){
                t.setNombreTabla(nombreNuevo);
            }
        }
         json.objectToJSON("DB/"+bdActual +"/", "MasterTable"+bdActual, tablita);
        
        return super.visitRename_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    

    
    
    
    
    
  
}
