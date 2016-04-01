/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import antlr.sqlBaseVisitor;
import antlr.sqlParser;
import org.antlr.v4.runtime.misc.NotNull;

/**
 *
 * @author adolfo
 */
public class VisitorAdolfo<T> extends sqlBaseVisitor{
    
    private JSONParser json = new JSONParser();
    private FileManager manejador = new FileManager();
    public String bdActual ="";
    
    @Override
    public T visitInsert(@NotNull sqlParser.InsertContext ctx) {
        String nombreTabla = ctx.getChild(2).getText();
        boolean existenciaTabla = manejador.checkFile(bdActual, nombreTabla);
        
        if (!existenciaTabla) {
            DBMS.throwMessage("Error: Insert fallido, la tabla " + nombreTabla + " no existe", ctx.getStart());
            
            return null;
        }
        
        Tabla tabla = (Tabla)json.JSONtoObject("DB/" + bdActual + "/", nombreTabla, "Tabla");
        
        int cantColumnas = tabla.getColumnas().size();
        
        return (T)visitChildren(ctx);
    }
    
    @Override
    public Object visitUse_schema_statement(sqlParser.Use_schema_statementContext ctx) {
        
        bdActual = ctx.getChild(2).getText();
       
        if (manejador.checkDB(bdActual)==false)
            bdActual = "";
        return super.visitUse_schema_statement(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    //"€"
    @Override
    public Object visitInt_literal(@NotNull sqlParser.Int_literalContext ctx) {
        return "int_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitDate_literal(@NotNull sqlParser.Date_literalContext ctx) {
        return "date_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitFloat_literal(@NotNull sqlParser.Float_literalContext ctx) {
        return "float_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitChar_literal(@NotNull sqlParser.Char_literalContext ctx) {
        return "char_literal" + "€" + ctx.getText();
    }
}
