/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author JHOAN
 */
public class NewMain {

    public boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
     
     
    public static void main(String[] args) {
        NewMain javaIOUtils = new NewMain();
        String fromFile = "C:\\Users\\JHOAN\\Pictures\\ciclovida.png";
        Path    rutarelativa= Paths.get(fromFile);
        
        Path    absoluta= rutarelativa.toAbsolutePath();
        String toFile = "C:\\Users\\JHOAN\\Desktop\\bd.png";
        
        
        boolean result = javaIOUtils.copyFile(fromFile, toFile);
        System.out.println(result?
            "Success! File copying (Éxito! Fichero copiado)":
                "Error! Failed to copy the file (Error! No se ha podido copiar el fichero)");
        
        System.out.println(" el nombre del archivo es "+rutarelativa);
         System.out.println(" el nombre del archivo es "+absoluta);
    }
    
}
