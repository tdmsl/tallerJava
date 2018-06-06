package es.tdmsl.obd2.controladores;

import es.tdmsl.obd2.bluetooth.ConexionBluetooth;
import es.tdmsl.obd2.principal.VistaPrincipal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Manu on 06/05/2017.
 */
public class ControladorInfSys {
    ControladorConexion controladorConexion;
    String vin;
    private File miDir = new File(".");

    public ControladorInfSys(VistaPrincipal vistaPrincipal, OutputStream outStream, InputStream inputStream) {
        controladorConexion = new ControladorConexion(vistaPrincipal, outStream, inputStream);

    }

    public String getVin() {
        controladorConexion.enviar("0902", false,true);
        vin = controladorConexion.enviar("0902", false,true);
        System.out.println(vin);
        /*try {
            switch (ConexionBluetooth.instancia.remote_device.getFriendlyName(false)) {
                case "Manu Borge (GT-N7100)":
                    vin = vin.substring(10, 48);
                    break;
                case "OBDII":
                    vin = vin.substring(10, 48);
                    break;

                default:
                    vin = vin.substring(10, 48);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        vin = vin.substring(10, 48);
        StringBuffer stringBuffer = new StringBuffer();
        //System.out.println(vin.length());
        int a = 0, b = 2;
        for (int i = 0; i < (vin.length()) / 2; i++) {
            if (Integer.parseInt(vin.substring(a, a + 2)) == 21 || Integer.parseInt(vin.substring(a, a + 2)) == 22) {
                //System.out.println("encontrado");
            } else {
                //System.out.println(i+"  "+vin.substring(a,a+2)+"  "+(char)Integer.parseInt(vin.substring(a,a+2),16));
                stringBuffer.append((char) Integer.parseInt(vin.substring(a, a + 2), 16));
            }
            a = a + 2;

        }
        vin = stringBuffer.toString();
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
