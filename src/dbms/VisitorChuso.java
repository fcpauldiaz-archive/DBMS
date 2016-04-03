/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import antlr.sqlBaseVisitor;
import antlr.sqlParser;
import static dbms.ANTGui.jTable1;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergio
 */
public class VisitorChuso <T> extends sqlBaseVisitor {
    private JSONParser json = new JSONParser();
    private String bdActual = "";
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
                    for(int j = 0 ;j<tab.getConstraints().size();j++)
                        if(tab.getConstraints().get(j).getTipo().equals("foreign"))
                            if(tab.getConstraints().get(j).getReferencesForeign().getNombreTablaRef().equals(nombreTabla))
                                existe = true;
                }
            }
            if(existe){
                DBMS.throwMessage( "La tabla: "+nombreTabla+" tiene referencias en otra(s) tablas sobre llaves foraneas, no se puede eliminar", ctx.getStart());
                return super.visitDrop_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
            }
            File directory = new File("DB/"+bdActual+"/"+nombreTabla);
            manejador.eliminarDB(bdActual+"/"+nombreTabla);
            for(int i = 0;i<ar.getTablas().size();i++)
                if(ar.getTablas().get(i).getNombreTabla().equals(nombreTabla))
                    ar.getTablas().remove(i);
            json.objectToJSON(bdActual, "MasterTable"+bdActual, ar);
        }else
            DBMS.throwMessage( "La tabla: "+nombreTabla+" no existe en la base de datos "+ bdActual, ctx.getStart());
        return super.visitDrop_table_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }  
}
