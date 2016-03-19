/**
* Universidad del Valle de Guatemala
* Pablo Diaz 13203
*/

package dbms;

import static dbms.DBMS.debug;

/**
 *
 * @author Pablo
 */
public class DBMS {
    
   public static boolean  debug = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
       ANTGui window = new ANTGui();
       window.setExtendedState(ANTGui.MAXIMIZED_BOTH);
       window.setVisible(true);
       window.setLocationRelativeTo(null);
    }

      public static void debug(String str){
         if (debug){
             System.out.println(str);
         }
     }
    

}
