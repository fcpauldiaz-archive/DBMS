/**
* Universidad Del Valle de Guatemala
* 16-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import java.util.ArrayList;

/**
 * Esta clase modela la cada estructura tabla que se guarda en un JSON.
 * @author Pablo
 */
public class Tabla {
    
    private final ArrayList<TuplaColumna> tablaDeclaration;
    private final ArrayList<Constraint> constraints;
    private ArrayList<ArrayList> tableData;

    public Tabla(){
        this.tablaDeclaration = new ArrayList(50);
        this.constraints = new ArrayList(50);
        this.tableData = new ArrayList(10000);
    }
    
    public void agregarColumna(TuplaColumna tupla){
        tablaDeclaration.add(tupla);
        printColumns();
    }
    
    public ArrayList<TuplaColumna> getColumnas(){
        return this.tablaDeclaration;
    }
    
    public void merge(Tabla t){
       this.tablaDeclaration.addAll(t.getColumnas());
    }
    
    public void printColumns(){
        for (int i = 0;i<this.tablaDeclaration.size();i++){
            System.out.println(tablaDeclaration.get(i));
        }
    }
    
    public ArrayList<Constraint> getConstraints(){
        return this.constraints;
    }
    
    public void addConstraint(Constraint constraint){
        this.constraints.add(constraint);
    }

    public void addRowToTable(ArrayList element) {
        this.tableData.add(element);
    }
    
    public ArrayList<ArrayList> getDataInTable() {
        return this.tableData;
    }
    
    @Override
    public String toString() {
        return "Tabla{" + "tablaDeclaration=" + tablaDeclaration + ", constraints=" + constraints + '}';
    }
    
    
}
