/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades;

import es.tdmsl.app.Principal;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manu
 */
public class Util {

    public static Vector<String> getCabeceras(Class c, String cabeceras) {
        //Class c = this.getClass();
        Field[] atributos;
        atributos = c.getDeclaredFields();
        int n = atributos.length;
        Vector<String> cab;
        Vector<String> cabNoVisibles;
        String nombreAtributo;

        // Partir las cabeceras no visibles:
        String[] auxCabeceras = cabeceras.split(",");
        cabNoVisibles = new Vector<String>();
        for (String s : auxCabeceras) {
            cabNoVisibles.add(s);
        }

        cab = new Vector<>();
        for (int i = 0; i < n; i++) {
            nombreAtributo = atributos[i].getName();
            if (!cabNoVisibles.contains(nombreAtributo)) {
                cab.add(nombreAtributo.toUpperCase());
            }
        }

        return cab;
    }

    public static java.util.Date stringToDate(String fecha) throws ParseException {
        if (fecha == null || fecha.equals("")) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(fecha);
    }

    public static String dateToString(java.util.Date fecha) {
        if (fecha == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }
    
    public static java.sql.Date dateUtiltodateSQL(java.util.Date fecha){
        java.sql.Date date;
        
        date = new java.sql.Date(fecha.getTime());
        return date;
    }

    public static void jOptionPaneTimeOut(Component padre, String texto, String titulo, final long timeout) {
        JOptionPane jOptionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE);
        jOptionPane.setMessage(texto);
        final JDialog jDialog = jOptionPane.createDialog(padre, titulo);

        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timeout * 1000);
                    if (jDialog.isVisible()) {
                        jDialog.setVisible(false);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        hilo.start();
        jDialog.setVisible(true);
    }
    
   
}
