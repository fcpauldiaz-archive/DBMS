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
 */
public class Visitor<T> extends sqlBaseVisitor {

    private FileManager manejador = new FileManager();
    private ArchivoMaestroDB  mdb = new ArchivoMaestroDB();
    private ArchivoMaestroTabla mdt = new ArchivoMaestroTabla();
    private JSONParser json = new JSONParser();
    private Tabla tabla;
    public String bdActual ="";
    public String tablaActual="";
    
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
            json.objectToJSON("DB/", "MasterDB", mdb);
            mdb = new ArchivoMaestroDB(); //sirve para perder la referencia
        }
        
        
        return super.visitSchema_definition(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitTable_definition(sqlParser.Table_definitionContext ctx) {
        
        tabla = new Tabla();
        String nombreTabla = ctx.getChild(2).getText();
        tablaActual = nombreTabla;
        System.out.println(tablaActual+"TBACTUAL");
        for (int i = 0;i<ctx.getChildCount();i++){
            this.visit(ctx.getChild(i));
        }
        
       
        ArchivoMaestroDB masterSaved = (ArchivoMaestroDB) json.JSONtoObject("DB/", "MasterDB", "ArchivoMaestroDB");
       
        if (manejador.checkFileTabla(bdActual, nombreTabla)){
            DBMS.debug("Nombre tabla " + nombreTabla);
            mdt.agregarTabla(nombreTabla);
            ArchivoMaestroTabla masterTable = (ArchivoMaestroTabla) json.JSONtoObject("DB/"+bdActual+"/", "MasterTable"+bdActual, "ArchivoMaestroTabla");
            mdt.agregarExistente(masterTable);
            json.objectToJSON("DB/"+bdActual+"/", "MasterTable"+bdActual, mdt);
            
            
            
            masterSaved.aumentarTablaCount(bdActual);
            mdb.agregarExistente(masterSaved);
            
            json.objectToJSON("DB/", "MasterDB", mdb);
            if (tabla != null)
                json.objectToJSON("DB/"+bdActual+"/", nombreTabla, tabla);
            
            mdb = new ArchivoMaestroDB(); //sirve para perder la referencia
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
    public Object visitConstraintType(sqlParser.ConstraintTypeContext ctx) {
       
        
        String nombreTabla = ctx.getParent().getParent().getParent().getChild(2).getText();
        String nombreConstraint = ctx.getChild(0).getText();
        String tipoConstraint = ctx.getChild(1).getText();
        ArrayList<String> listadoIDS = (ArrayList<String>)visit(ctx.getChild(4));
        Constraint constraint = new Constraint();
        constraint.setTipo(tipoConstraint);
        constraint.setNombre(nombreConstraint);
        if (ctx.getChildCount()>6){
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
            constraint.setReferencesForeign(listadoIDSREF);
            
        }
        //ahora busco la tabla y verifico los campos de los constraints
        Tabla tabla_c = tabla;
                
        ArrayList<TuplaColumna> camposActuales = tabla_c.getColumnas();
        boolean verificador = revisarListadoIDs(camposActuales, listadoIDS);
        if (verificador){
            constraint.setReferences(listadoIDS);
            tabla.addConstraint(constraint);
        }
        else{
             DBMS.debug("Algún campo "+listadoIDS+" no existe en la tabla " + nombreTabla );
             tabla = null; //ya no se guarda la tabla.
        }
        
        
        
        return super.visitConstraintType(ctx); //To change body of generated methods, choose Tools | Templates.
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

    
    
    
  
}
