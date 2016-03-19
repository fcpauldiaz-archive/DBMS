/**
* Universidad Del Valle de Guatemala
* 16-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 * @param <T>
 */
public class JSONParser<T> {
    
    public JSONParser(){
        
    }
    
    public void objectToJSON(String path,String fileName, T object){
         
       
         try (Writer writer = new FileWriter(path+fileName+".json")) {
             
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, writer);
            
        }catch (IOException ex) {
            Logger.getLogger(DBMS.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    public T JSONtoObject(String path,String fileName, String object){
         try {
                Gson gson = new Gson();
		BufferedReader br = new BufferedReader(
			new FileReader(path+fileName+".json"));

		//convert the json string back to object
                T obj = null;
                if (object.equals("ArchivoMaestroDB"))
		    obj = (T)gson.fromJson(br, ArchivoMaestroDB.class);
                if (object.equals("ArchivoMaestroTabla"))
		    obj = (T)gson.fromJson(br, ArchivoMaestroTabla.class);
                 if (object.equals("Tabla"))
		    obj = (T)gson.fromJson(br, Tabla.class);
 
		System.out.println(obj);
                return (T)obj;
	} catch (IOException e) {
            e.printStackTrace();
	}
         
        return null;
    }
}
