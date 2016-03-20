/**
* Universidad Del Valle de Guatemala
* 19-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class TuplaRefForeign {

   private String nombreTablaRef;
   private ArrayList<String> columnaForeign;

    public TuplaRefForeign(String nombreTablaRef) {
        this.nombreTablaRef = nombreTablaRef;
        this.columnaForeign= new ArrayList();
    }

    public String getNombreTablaRef() {
        return nombreTablaRef;
    }

    public void setNombreTablaRef(String nombreTablaRef) {
        this.nombreTablaRef = nombreTablaRef;
    }

    public ArrayList<String> getReferencesForeign() {
        return columnaForeign;
    }

    public void setReferencesForeign(ArrayList<String> referencesForeign) {
        this.columnaForeign = referencesForeign;
    }

   
    @Override
    public String toString() {
        return "TuplaRefForeign{" + "nombreTablaRef=" + nombreTablaRef + ", columnaRef=" + this.columnaForeign + '}';
    }
    
    
    
    
}

