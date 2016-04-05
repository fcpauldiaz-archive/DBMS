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
        
        Tabla tabla = getTablaFromNombre(bdActual, nombreTabla);
        
        /* Verificar si la tabla existe */
        if (tabla == null) {
            DBMS.throwMessage("Error: Insert fallido, la tabla " + nombreTabla + " no existe", ctx.getStart());
            
            return null;
        }
        
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
        
        // Si la cantidad de valores del insert es mayor que la que necesitamos está mal
        if (insertValues.size() > cantColumnasTabla) {
            DBMS.throwMessage("Insert Error: Cantidad de valores mayor de los necesarios", ctx.getStart());
            
            return null;
        }
        
        // En el caso en donde se haya definido las columnas a insertar
        if (!isSimpleInsert) {
            // Validar cantidad de columnas contra inserts
            if (insertValues.size() != insertColumnNames.size()) {
                DBMS.throwMessage("Insert Error: Cantidad de columnas y valores no coinciden", ctx.getStart());

                return null;
            }
            
            // Validar que la cantidad de columnas a insertar no sean mayores que la definición de la tabla
            if (insertColumnNames.size() > cantColumnasTabla) {
                DBMS.throwMessage("Insert Error: Cantidad de columnas mayor de los necesarios", ctx.getStart());
            
                return null;
            }
            
            // Validar que las columnas a ingresar no sean repetidas
            if (!verificarDobleLlamadaColumna(insertColumnNames)) {
                DBMS.throwMessage("Insert Error: Columnas repetidas", ctx.getStart());
            
                return null;  
            }
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
        
        // Si está definida la lista de columnas, vamos a verificar
        // que la columnas existan
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
        }
        
        // Vemos si la cantidad de columnas listada en el insert es menor que la cantidad
        // de columnas en la tabla
        if (!isSimpleInsert && (insertColumnNames.size() < cantColumnasTabla)) {
            int contInvalidInsertValues = 0;
            for (int i = 0; i < insertColumnNames.size(); i++) {
                String tipoColumna = getColumnTypeFromColumnName(insertColumnNames.get(i), tabla);
                
                if (!insertValues.get(i).contains(tipoColumna)) {
                    ArrayList datosInsertValue = getTypeAndValueFromInsertValue(insertValues.get(i));
                    
                    DBMS.throwMessage(
                            "Insert Error: Tipo de dato del valor: " + datosInsertValue.get(0) +
                            " Es incorrecto, se requiere un " + datosInsertValue.get(1),
                            ctx.getStart()
                    );
                    
                    contInvalidInsertValues++;
                }
            }
            
            if (contInvalidInsertValues > 0) {
                return null;
            }
            
        }
        
        // En el caso en el que se hayan definido las columnas y es la misma cantidad de la tabla
        if (!isSimpleInsert && (insertColumnNames.size() == cantColumnasTabla)) {
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
    
    public Tabla getTablaFromNombre(String bd, String nombre) {
        if (!manejador.checkFile(bdActual, nombre)) {
            return null;
        }
        
        Tabla tabla = (Tabla)json.JSONtoObject(bdActual + "/", nombre, "Tabla");
        
        return tabla;
    }
    
    public ArrayList getTypeAndValueFromInsertValue(String value) {
        int indexOfEpsilon = value.indexOf("€");
        ArrayList returnData = new ArrayList();
        
        returnData.add(0, value.substring(0, indexOfEpsilon));
        returnData.add(0, value.substring(indexOfEpsilon + 1));
        
        return returnData;
    }
    
    public boolean verificarDobleLlamadaColumna(ArrayList<String> columnasEval) {
        for (String s: columnasEval) {
            int contRepetidos = 0;
            for (String s_1: columnasEval) {
                if (s.equals(s_1)) {
                    contRepetidos++;
                }
            }
            
            if (contRepetidos > 1) {
                return false;
            }
            
            contRepetidos = 0;
        }
        
        return true;
    }
    
    public boolean verifyPossibleCast(String insertType, String columnType) {
        if (
            (insertType.contains("INT") && columnType.contains("FLOAT")) ||
            (insertType.contains("FLOAT") && columnType.contains("INT")) ||
            (insertType.contains("CHAR") && columnType.contains("DATE"))
        ) {
            return true;
        }
        
        return false;
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
