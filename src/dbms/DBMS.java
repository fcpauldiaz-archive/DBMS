/**
* Universidad del Valle de Guatemala
* Pablo Diaz 13203
*/

package dbms;

import static dbms.ANTGui.jTextArea3;
import static dbms.DBMS.debug;
import static dbms.ANTGui.selected;
import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.Token;
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
         debug = selected;
         if (debug){
            agregarLog(str);
            System.out.println(str);
         }
     }
     public static void debug(String str, Token ctx){
         debug = selected;
         if (debug){
             agregarLog(str,ctx);
             System.out.println(str);
         }
     }
     /**
      * Agregar la palabra "Error:" al principio para mostrar el mensaje en rojo
      * @param str mensaje
      * @param ctx ctx.getStart()
      */
     public static void throwMessage(String str, Token ctx){
         agregarLog(str,ctx);
     }
       public static void agregarLog(String mensaje, Token ctx){
        
        StyledDocument doc = jTextArea3.getStyledDocument();
        int linea = ctx.getLine();
        int columna = ctx.getCharPositionInLine();
        Style style = jTextArea3.addStyle("", null);
        StyleConstants.setForeground(style, Color.red);
        
        if (mensaje.contains("Error")){
            try {
                doc.insertString(doc.getLength(), "linea: " + linea +": "+ columna +  " " + mensaje+"\n",style);
                
            }
            catch (BadLocationException e){}
        }
        else{
            StyleConstants.setForeground(style, Color.blue);
            try { doc.insertString(doc.getLength(), "linea: " + linea +": "+ columna +  " " + mensaje+"\n",style); }
            catch (BadLocationException e){}
        }
        
        
        
    }
       
        public static void agregarLog(String mensaje){
        
        StyledDocument doc = jTextArea3.getStyledDocument();
       
        Style style = jTextArea3.addStyle("", null);
        StyleConstants.setForeground(style, Color.red);
        
        
      if (mensaje.contains("Error")){
            try {
                doc.insertString(doc.getLength(), " " + mensaje+"\n",style);
                
            }
            catch (BadLocationException e){}
        }
        else{
            StyleConstants.setForeground(style, Color.blue);
            try { doc.insertString(doc.getLength(),  " " + mensaje+"\n",style); }
            catch (BadLocationException e){}
        }
    
        
        
        
    }

}
