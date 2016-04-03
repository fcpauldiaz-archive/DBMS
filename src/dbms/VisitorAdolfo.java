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
        // Existen dos tipos de insert
        // 1. En donde se ingresan los nombres de las columnas y luego los valores
        // 2. En donde no se ingresan los nombres de las columnas y sólo valores
        // Esa diferencia de insert estará dado por una bandera simpleInsert en donde
        // true es cuando no tiene columnas definidas y el caso contrario pues se deduce
        
        String nombreTabla = ctx.getChild(2).getText();
        boolean existenciaTabla = manejador.checkFile(bdActual, nombreTabla);
        
        if (!existenciaTabla) {
            DBMS.throwMessage("Error: Insert fallido, la tabla " + nombreTabla + " no existe", ctx.getStart());
            
            return null;
        }
        
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual + "/", nombreTabla, "Tabla");
        
        // Todos los elementos que se iran en el insert
        ArrayList<String> insertValues = null;
        ArrayList<String> insertColumnNames = null;
        // Por default vamos a suponer que es un insertSimple
        boolean isSimpleInsert = true;
        
        if (ctx.getChildCount() == 8) {
            insertValues = (ArrayList<String>) visit(ctx.getChild(5));
        }
        
        if (ctx.getChildCount() == 9) {
            insertValues = (ArrayList<String>) visit(ctx.getChild(6));
            insertColumnNames = (ArrayList<String>) visit(ctx.getChild(3));
            
            isSimpleInsert = false;
        }
        
        // La cantidad de columnas de la tabla
        int cantColumnasTabla = tabla.getColumnas().size();
        
        // En el caso en donde se haya definido las columnas a insertar
        if (!isSimpleInsert) {
            if (insertValues.size() != insertColumnNames.size()) {
                DBMS.throwMessage("Insert Error: Cantidad de columnas y valores no coinciden", ctx.getStart());

                return null;
            }
        }
        
        // Si la cantidad de valores del insert es mayor que la que necesitamos está mal
        if (insertValues.size() > cantColumnasTabla) {
            DBMS.throwMessage("Insert Error: Cantidad de valores mayor de los necesarios", ctx.getStart());
            
            return null;
        }
        
        // La cantidad de valores son exactamente los que necesita el INSERT de la tabla
        if (isSimpleInsert && (insertValues.size() == cantColumnasTabla)) {
            // Vamos a comprar entonces que los tipos de los datos sean los de la tabla
            
            int contInvalidInsertValue = 0;
            ArrayList<TuplaColumna> columnasTabla = tabla.getColumnas();
            
            for (int i = 0; i  < columnasTabla.size(); i++) {
                String tipoColumna = columnasTabla.get(i).getTipo();
                if (!insertValues.get(i).contains(tipoColumna)) {
                    int indexOfSubs = insertValues.get(i).indexOf("€") + 1;
                    String valor = insertValues.get(i).substring(indexOfSubs);
                    DBMS.throwMessage(
                            "Insert Error: Tipo de dato del valor: " + valor + " Es incorrecto, se requiere un " + tipoColumna,
                            ctx.getStart()
                    );
                    
                    contInvalidInsertValue++;
                }
            }
            
            if (contInvalidInsertValue > 0) {
                return null;
            }
        }
        
        // En el caso en el que se hayan definido las columnas, debemos validar que las columnas existan
        if (!isSimpleInsert) {
            int cantValidColumnNames = 0;
            
            ArrayList<TuplaColumna> columnasTabla = tabla.getColumnas();
            for (int i = 0; i < columnasTabla.size(); i++) {
                for (int j = 0; j < insertColumnNames.size(); j++) {
                    if (columnasTabla.get(i).getNombre().equals(insertColumnNames.get(j))) {
                        cantValidColumnNames++;
                    }
                }
            }
        
            if (cantValidColumnNames != insertColumnNames.size()) {
                    DBMS.throwMessage("Insert Error: Es posible que una de las columnas a hidratar no exista", ctx.getStart());
                    
                    return null;
            }
            
            int contInvalidInsertValue = 0;
            
            for (int i = 0; i < insertColumnNames.size(); i++) {
                String tipoColumna = getColumnTypeFromColumnName(insertColumnNames.get(i), tabla);
                if (!insertValues.get(i).contains(tipoColumna)) {
                    int indexOfSubs = insertValues.get(i).indexOf("€") + 1;
                    String valor = insertValues.get(i).substring(indexOfSubs);
                    
                    DBMS.throwMessage(
                            "Insert Error: Tipo de dato del valor: " + valor + " Es incorrecto, se requiere un " + tipoColumna,
                            ctx.getStart()
                    );
                    
                    contInvalidInsertValue++;
                }
            }
            
            if (contInvalidInsertValue > 0) {
                return null;
            }
        }
        
        
        return (T)visitChildren(ctx);
    }
    
    public String getColumnTypeFromColumnName(String nombreColumna, Tabla tabla) {
        for (TuplaColumna tc: tabla.getColumnas()) {
            if (tc.getNombre().equals(nombreColumna)) {
                return tc.getTipo();
            }
        }
        
        return null;
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
    public Object visitInsert_column_names(@NotNull sqlParser.Insert_column_namesContext ctx) {
        ArrayList<String> columnNames = new ArrayList();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().contains("(") ||
                ctx.getChild(i).getText().contains(")") ||
                ctx.getChild(i).getText().contains(",")
            ) {
                continue;
            }
            columnNames.add(ctx.getChild(i).getText());
        }
        return columnNames;
    }
    
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
