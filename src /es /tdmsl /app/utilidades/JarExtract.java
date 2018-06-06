/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author manu
 */
public class JarExtract {
 public static void extractJar(String jarFile, java.io.File directory)throws IOException{
  java.util.jar.JarInputStream jarInput = new java.util.jar.JarInputStream(new FileInputStream(jarFile));
  java.util.jar.JarEntry jarEntry=null;
     while((jarEntry=jarInput.getNextJarEntry())!=null){
      java.io.File file=new java.io.File(directory,jarEntry.getName());
         if (jarEntry.isDirectory()){
          if (!file.exists())
                 file.mkdirs();
         }else{ java.io.File dir = new java.io.File(file.getParent());
             if (!dir.exists())dir.mkdirs();
             byte[] bytes = new byte[1024];
             java.io.InputStream inputStream   = new BufferedInputStream(jarInput);
             FileOutputStream fileOutputStream = new FileOutputStream(file);
             int read = -1;
             while ((read = inputStream.read(bytes)) != -1) {
              fileOutputStream.write(bytes, 0, read);
          }
             fileOutputStream.close();            
         }
     }}
public static void main(String[] args) throws IOException {
 extractJar("\\dist\\tallerJava.jar",new java.io.File("/dist/carpetasExt/"));
 }   
}
