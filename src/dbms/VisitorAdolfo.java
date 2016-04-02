/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import antlr.sqlBaseVisitor;
import antlr.sqlParser;
import java.util.ArrayList;
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
    public T visitInsert_value(@NotNull sqlParser.Insert_valueContext ctx) {
        String nombreTabla = ctx.getChild(2).getText();
        boolean existenciaTabla = manejador.checkFile(bdActual, nombreTabla);
        
        if (!existenciaTabla) {
            DBMS.throwMessage("Error: Insert fallido, la tabla " + nombreTabla + " no existe", ctx.getStart());
            
            return null;
        }
        
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual + "/", nombreTabla, "Tabla");
        
        // Todos los elementos que se iran en el insert
        ArrayList<String> insertValues = (ArrayList<String>) visit(ctx.getChild(5));
        
        // La cantidad de columnas de la tabla
        int cantColumnasTabla = tabla.getColumnas().size();
        
        // Si la cantidad de valores del insert es mayor que la que necesitamos está mal
        if (insertValues.size() > cantColumnasTabla) {
            DBMS.throwMessage("Insert Error: Cantidad de valores mayor de los necesarios", ctx.getStart());
            
            return null;
        }
        
        // La cantidad de valores son exactamente los que necesita el INSERT de la tabla
        if (insertValues.size() == cantColumnasTabla) {
            // Vamos a comprar entonces que los tipos de los datos sean los de la tabla
            ArrayList<TuplaColumna> columnasTabla = tabla.getColumnas();
            
            for (int i = 0; i  < columnasTabla.size(); i++) {
                String tipoColumna = columnasTabla.get(i).getTipo();
                
                if (!insertValues.get(i).contains(tipoColumna)) {
                    DBMS.throwMessage("Insert Error: Tipo de dato del valor: " + insertValues.get(i) + " Es incorrecto", ctx.getStart());
                    
                    return null;
                }
            }
        }
        
        return (T)visitChildren(ctx);
    }
    
    @Override
    public ArrayList<String> visitList_values(@NotNull sqlParser.List_valuesContext ctx) {
        ArrayList<String> valores = new ArrayList();
        
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (visit(ctx.getChild(i)) == null) {
                continue;
            }
            valores.add((String) visit(ctx.getChild(i)));
        }
        
        return valores;
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
        return "INT_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitDate_literal(@NotNull sqlParser.Date_literalContext ctx) {
        return "DATE_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitFloat_literal(@NotNull sqlParser.Float_literalContext ctx) {
        return "FLOAT_literal" + "€" + ctx.getText();
    }
    
    @Override
    public Object visitChar_literal(@NotNull sqlParser.Char_literalContext ctx) {
        return "CHAR_literal" + "€" + ctx.getText();
    }
}
