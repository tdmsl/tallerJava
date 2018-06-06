package es.tdmsl.obd;

import es.tdmsl.obd.presentacion.VistaPrincipal;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Manu on 09/11/2016.
 */
public class prueba_serial {
    public prueba_serial() {
        CommPortIdentifier.getPortIdentifiers();
        /*int j = 1;
        Vector<String> listapuertos = new Vector<String>();
        for (Enumeration i = CommPortIdentifier.getPortIdentifiers(); i.hasMoreElements(); ) {
            CommPortIdentifier idpuerto = (CommPortIdentifier) i.nextElement();
            listapuertos.add(j++ + ". " + idpuerto.getName() + "\n");
        }*/
    }

    public static void main(String[] args){
        new VistaPrincipal();

    }

}
