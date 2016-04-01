/**
* Universidad Del Valle de Guatemala
* 18-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import java.util.ArrayList;

/**
 * Esta clase tiene como objetivo modelar cada constraint simple en una tabla.
 * @author Pablo
 */
public class Constraint {
    
    private String nombre;
    private String tipo;
    private ArrayList<String> references;
    private TuplaRefForeign referencesForeign;
    private TuplaCheck  tuplaCheck;

    public Constraint(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.references = new ArrayList();
        
    }
    
    public Constraint(){
        this.references = new ArrayList();
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
    
    public void setReferencesForeign(TuplaRefForeign references) {
        this.referencesForeign = references;
    }
    
    public TuplaRefForeign getReferencesForeign(){
        return this.referencesForeign;
    }

    public TuplaCheck getTuplaCheck() {
        return tuplaCheck;
    }

    public void setTuplaCheck(TuplaCheck tuplaCheck) {
        this.tuplaCheck = tuplaCheck;
    }

    @Override
    public String toString() {
        return "Constraint{" + "nombre=" + nombre + ", tipo=" + tipo + ", references=" + references + ", referencesForeign=" + referencesForeign + ", tuplaCheck=" + tuplaCheck + '}';
    }
    
    

    
    
            

}
