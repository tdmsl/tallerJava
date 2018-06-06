package es.tdmsl.obd2.controladores;

import es.tdmsl.obd2.bluetooth.ConexionBluetooth;
import es.tdmsl.obd2.principal.VistaPrincipal;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Manu on 30/04/2017.
 */
public class ControladorErrores {
    Map<String, String> tablaHexBin = new HashMap<String, String>();
    int numeroErrores;
    ControladorConexion controladorConexion;
    //private File miDir = new File(".");

    public ControladorErrores() {
        controladorConexion = ConexionBluetooth.instancia.controladorConexion;

        tablaHexBin.put("0", "0000");
        tablaHexBin.put("1", "0001");
        tablaHexBin.put("2", "0010");
        tablaHexBin.put("3", "0011");
        tablaHexBin.put("4", "0100");
        tablaHexBin.put("5", "0101");
        tablaHexBin.put("6", "0110");
        tablaHexBin.put("7", "0111");
        tablaHexBin.put("8", "1000");
        tablaHexBin.put("9", "1001");
        tablaHexBin.put("A", "1010");
        tablaHexBin.put("B", "1011");
        tablaHexBin.put("C", "1100");
        tablaHexBin.put("D", "1101");
        tablaHexBin.put("E", "1110");
        tablaHexBin.put("F", "1111");
    }

    public String mumeroDTCs() {
        //String strDatos="\n41 01 84 4B A5 F1 0A\r";
        //trama del movil 41018517FF00

        //String strDatos;//="41 01 84 07 EF 80"
        //strDatos ="41 01 84 4B A5 F1 0A";
        StringBuffer estadoErrores=new StringBuffer() ;

        int valorDec;
        String respuesta = controladorConexion.enviar("0101",true,true);
        respuesta = respuesta.replace("SEARCHING...", "");
        respuesta = respuesta.replaceAll(" ", "");
        respuesta = respuesta.substring(4,6);
        System.out.println(respuesta);
        String trama = respuesta;
        StringBuffer tramaBinaria = new StringBuffer();
        int x = 0;
        /*//se pasa de hex a bin
        for (int l = 1; l <= trama.length(); l++) {
            tramaBinaria.append(tablaHexBin.get(trama.substring(x, x + 1)));
            x++;

        }*/
        System.out.println(tramaBinaria);

        try {
            estadoErrores.append("Dispositivo " + ConexionBluetooth.instancia.remote_device.getFriendlyName(false) + "<br>");
            estadoErrores.append("respuesta a decodificar : " + respuesta + "<br><br>");
            //valorDec = Integer.parseInt(strDatos.substring(6, 8), 16);//funcionando
            System.out.println(respuesta.substring(0, 2));
            valorDec = Integer.parseInt(respuesta.substring(0, 2), 16);
            if (Objects.equals(ConexionBluetooth.instancia.remote_device.getFriendlyName(false), "Manu Borge (GT-N7100)")) {
                valorDec-=1;//para ecusim movil
            }
            //System.out.println(valorDec);
            if (valorDec < 128) {
                numeroErrores = valorDec - 128;
                estadoErrores.append("La luz del motor de comprobación (MIL) está apagada\n y la ECU no tiene ningún código de error almacenado") ;
            } else {
                numeroErrores = valorDec - 128;
                estadoErrores.append("La luz del motor de verificación (MIL) está encendida<br> y la ECU <font color=blue> " + numeroErrores + "</font> errores almacenados<br>") ;

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return estadoErrores.toString();


    }

    public String descripcionErrores()  {
        Properties codigosError = new Properties();
        InputStream inputStream = null;
        try {
            ///src/es/tdmsl/obd2/
            //String path = miDir.getCanonicalPath() + "\\carpetasExt\\codigosError.properties";
            String path = System.getProperty("user.dir") + "\\carpetasExt\\obd\\datos\\codigosError.properties";
            //System.out.println(path);
            //System.out.println(path2);
            inputStream = new FileInputStream(path);
            codigosError.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer codigoP;
        int longitudTrama;
        String respuesta;
        respuesta = controladorConexion.enviar("03",false,true);
        //respuesta="43 02 02 51 04 60 43 01 08 90";//ECU Engine Pro
        //respuesta =respuesta.replaceAll("\\s","");
        System.out.println(respuesta);
        //JOptionPane.showMessageDialog(null,respuesta);
        if (respuesta.equals("NO DATA>")){
            //JOptionPane.showMessageDialog(null,"NO DATA");
        }else {
            respuesta = respuesta.replaceAll(".: ", "");
            respuesta = respuesta.replaceAll(">", "");
            respuesta = respuesta.replaceAll(" ", "");
            respuesta = respuesta.substring(4);//si es ECU Engine Pro
            /*if (ConexionBluetooth.instancia.remote_device.getFriendlyName(false).equals("Manu Borge (GT-N7100)")){
                respuesta = respuesta.substring(4);//si es ECU Engine Pro

            }else if(ConexionBluetooth.instancia.remote_device.getFriendlyName(false).equals("OBDII")) {
                respuesta = respuesta.substring(2);//si es ECUSIM
            }*/


        }
        codigoP = new StringBuffer();
        StringBuffer codigoError = new StringBuffer();
        //int incremento = 6;//funcionando
        int incremento = 0;
        /*codigoError.append("<br><table border=1><tr ><td COLSPAN=8>A</td><td COLSPAN=8>B</td><td COLSPAN=8>C</td><td COLSPAN=8>D</td></tr>" +
                "<tr><td>A7</td><td>A6</td><td>A5</td><td>A4</td><td>A3</td><td>A2</td><td>A1</td><td>A0</td>" +
                "<td>B7</td><td>B6</td><td>B5</td><td>B4</td><td>B3</td><td>B2</td><td>B1</td><td>B0</td>" +
                "<td>C7</td><td>C6</td><td>C5</td><td>C4</td><td>C3</td><td>C2</td><td>C1</td><td>C0</td>" +
                "<td>D7</td><td>D6</td><td>D5</td><td>D4</td><td>D3</td><td>D2</td><td>D1</td><td>D0</td></tr>" +
                "</table><br>" +
                respuesta + "<hr><br>");*/

        //codigoError.append("Dispositivo " + bluetooth.getSocket().getRemoteDevice().getName() + "<\n>");
        codigoError.append("respuesta a decodificar : " + respuesta + "\n\n  ");
        codigoError.append("<table border=1><th colspan=2>CODIGOS DE ERROR</th>");
        System.out.println("respuesta posicion " + incremento + " de la cadena respuesta  = " + respuesta.codePointAt(incremento) + " = " + (char) respuesta.codePointAt(incremento));
        for (int s = 0; s <  numeroErrores; s++) {
            boolean codigo_encontrado = false;
            if (respuesta.codePointAt(incremento) == 48) {//if = 0
                codigoP.append("P0");
            } else if (respuesta.codePointAt(incremento) == 49) {//if =1
                codigoP.append("P1");
            } else if (respuesta.codePointAt(incremento) == 50) {//if =2
                codigoP.append("P2");
            } else if (respuesta.codePointAt(incremento) == 51) {//if =3
                codigoP.append("P3");
            } else if (respuesta.codePointAt(incremento) == 52) {//if =4
                codigoP.append("C0");
            } else if (respuesta.codePointAt(incremento) == 53) {//if =5
                codigoP.append("C1");
            } else if (respuesta.codePointAt(incremento) == 54) {//if =6
                codigoP.append("C2");
            } else if (respuesta.codePointAt(incremento) == 55) {//if =7
                codigoP.append("C3");
            } else if (respuesta.codePointAt(incremento) == 56) {//if =8
                codigoP.append("B0");
            } else if (respuesta.codePointAt(incremento) == 57) {//if =9
                codigoP.append("B1");
            } else if (respuesta.codePointAt(incremento) == 65) {//if =A
                codigoP.append("B2");
            } else if (respuesta.codePointAt(incremento) == 66) {//if =B
                codigoP.append("B3");
            } else if (respuesta.codePointAt(incremento) == 67) {//if =C
                codigoP.append("U0");
            } else if (respuesta.codePointAt(incremento) == 68) {//if =D
                codigoP.append("U1");
            } else if (respuesta.codePointAt(incremento) == 69) {//if =E
                codigoP.append("U2");
            } else if (respuesta.codePointAt(incremento) == 70) {//if =F
                codigoP.append("U3");
            }
            for (int p = incremento + 1; p < (incremento + 4); p++) {//Introduce el resto denumeros del error
                if (respuesta.codePointAt(p) != 32) {// space
                    codigoP.append(respuesta.charAt(p));
                }

            }
            incremento = incremento + 4;//Coloca la posicion en la trama para el siguiente//error
            System.out.println("incremento " + incremento);
            String DTC = codigoP.toString();
            String sCadena;


            //try {
            //fr = new FileReader("CodigosErrores.txt");
            //bf = new BufferedReader(fr);
                /*while ((sCadena = bf.readLine())!=null) {
                    if(codError.compareTo(sCadena.substring(0, 5))==0){//se compara el error leido con cada linea
                                                                       // del fichero, si lo encuentra se sale del bucle
                        codigoError.append(sCadena+"\n\n");
                        codigo_encontrado=true;
                        break;
                    }
                }*/

            codigoError.append("<tr><td>" + DTC + "</td><td>" + codigosError.getProperty(DTC) + "</td></tr>");
                /*if(codigo_encontrado==false){
                    codigoError.append("Unknown "+codError+" error\n\n");
                }*/
            //} catch (IOException e) {
            //    codigoError.append("Database error codes inaccessible");
            //}
            longitudTrama = codigoP.length();//Se deja vacio el buffer que contiene el código procedente de la ECU
            codigoP.delete(0, longitudTrama);
        }
        codigoError.append("</table></html");
        return codigoError.toString();
    }
}
