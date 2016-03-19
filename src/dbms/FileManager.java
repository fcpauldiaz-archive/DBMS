/**
* Universidad Del Valle de Guatemala
* 16-mar-2016
* Pablo Díaz 13203
*/

package dbms;

import java.io.File;

/**
 * Clase que maneja la escritura/lectura de archivos en JAVA.
 * @author Pablo
 */
public class FileManager {
    
    public void FieManager()
    {
        
    }
    
    /**
     * Método que crea el directorio para cada base de datos
     * @param nameDir nombre de la base de datos
     * @return regresa true si fue correcta la creación
     * regresa false si hay error o no es válido.
     */
    public boolean createDirectory(String nameDir){
    
        File file = new File("DB/"+nameDir);
        if (!file.exists()) {
            if (file.mkdir()) {
               DBMS.debug("Base de datos creada");
            }
            else{
                 DBMS.debug("Ha ocurrido un error");
                return false;
            }
        return true;
        }else{
             DBMS.debug("La base de datos ya existe");
            return false;
        }
    }
    /**
     * Este método sirve para revisar si existe un archivo
     * @param path
     * @param fileName
     * @return true si existe, false si no
     */
    public boolean checkFile(String path,String fileName){
        if (path.isEmpty()){
            DBMS.debug("La base de datos no existep");
            return false;
        }

        File file = new File("DB/"+path+"/",fileName+".json");
        path = path.replaceAll("//", path);
        if (!file.exists()) {

                DBMS.debug("La tabla " +fileName + " ha sido creado en la base de datos " + path );

        return true;
        }else{
            DBMS.debug("La tabla " +fileName + " ya existe en la base de datos " + path );
            return false;
    }
    }
    /**
     * Este método sirve para revisar si existe una base de datos
     * @param db nombre de la base de datos
     * @return true si existe, false si no existe.
     */
    public boolean checkDB(String db){
        if (db.isEmpty()){
            DBMS.debug("La base de datos no ha sido seleccionada");
            return false;
        }

        File file = new File("DB/"+db+"/");
        
        if (!file.exists()) {

            DBMS.debug("La base de datos " + db + " no existe");

           return false;
        }else{
            DBMS.debug("La base de datos " + db + " ha sido seleccionada");
            return true;
    }
}
    
   
    
    
}
