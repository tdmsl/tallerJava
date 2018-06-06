package es.tdmsl.obd2.controladores;

import es.tdmsl.obd2.principal.VistaPrincipal;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Manu on 29/01/2017.
 */
public class ControladorConexion {
    boolean eco;
    StringBuffer tramaEnviada;
    OutputStream outputStream;
    InputStream inputStream;
    VistaPrincipal vistaPrincipal;
    //int numeroErrores;
   // Map<String, String> tablaHexBin = new HashMap<String, String>();
    //private File miDir = new File(".");

    public static ControladorConexion instancia;// = new conexionBluetooth()


    public static ControladorConexion getInstancia(VistaPrincipal vistaPrincipal, OutputStream outStream, InputStream inputStream) {
        if (instancia == null) {
            instancia = new ControladorConexion(vistaPrincipal, outStream, inputStream);
        }
        return instancia;
    }

    public ControladorConexion(VistaPrincipal vistaPrincipal, OutputStream outputStream, InputStream inputStream) {
        tramaEnviada = new StringBuffer();
        this.vistaPrincipal = vistaPrincipal;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
       /* tablaHexBin.put("0", "0000");
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
        tablaHexBin.put("F", "1111");*/

    }

    public String enviar(String trama,boolean eco,boolean manual) {

        this.eco=eco;
        int data;
        tramaEnviada.append(trama + "\r");//Solamente necessita el retorno de carro, sin el salto de linea.
        for (int i = 0; i < tramaEnviada.length(); i++) {
            data = tramaEnviada.codePointAt(i);
            try {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
                vistaPrincipal.textAreaProgreso.append(e + "\n");
                JOptionPane.showMessageDialog(null,e);
            }
        }
        if (eco){
            vistaPrincipal.textAreaProgreso.append("    Enviado \n" + trama + "\n");
        }


        tramaEnviada.delete(0, tramaEnviada.length());
        if(manual){
            return recibir();
        }

        return null;
    }

    public String recibir() {

        StringBuffer respuesta = new StringBuffer();
        //String trama = ("no data");
        int caracter = 0;
        try {
            while ((char) caracter != (char) '>') {
                caracter = inputStream.read();
                respuesta.append((char) caracter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (eco){
            vistaPrincipal.textAreaProgreso.append("    Recibido\n" + respuesta + "\n\n");
            //System.out.println("respuesta "+String.valueOf(respuesta).replaceAll("\r",""));
        }

        return respuesta.toString().replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "");
        //return respuesta+"";
    }

    public static void pausa(){
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {}
    }


   /* public List<String> listaPids() {
        StringBuffer tramaBinaria = new StringBuffer();
        List<String> listaPidsSoportados = new ArrayList<String>();
        String trama = ("no data");


        String respuesta = enviar("0100",true);// muestra pid disponibles

        // if (respuesta.)


        //formateamos la respuesta//quitamos pid y cabeceras
        trama = respuesta.toString();
        trama = trama.replaceAll("\\s", "");
        System.out.println(trama);
        if (trama.contains("UNABLETOCONNECT")) {
            vistaPrincipal.textPanePrincipal.setText(vistaPrincipal.textPanePrincipal.getText() + "\"UNABLETOCONNECT\"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
            trama = "";
        } else {


            trama = trama.replace("SEARCHING...", "");
            trama = trama.replace("0100", "");
            trama = trama.replace("4100", "");
            //trama = trama.replace("1:", "");
            //trama = trama.replace("2:", "");
            trama = trama.replace(">", "");
            //vistaPrincipal.textPanePrincipal.setText(vistaPrincipal.textPanePrincipal.getText()+trama+"\n");
            //vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
            *//*if (!trama.isEmpty()){

            }*//*
            try {
                trama = trama.substring(0, 8);
            } catch (Exception e) {
                System.out.println("error " + e);
            }

            //////////////////////////////
            //trama= "BE1FA813";
            //trama= "B";
            //////////////////////////////
            //trama = trama.replace("\n\r","");
            System.out.println("TRAMA  =  " + trama);
            System.out.println("LONGITUD TRAMA  =  " + trama.length());
        }

        int x = 0;
        //se pasa de hex a bin
        for (int l = 1; l <= trama.length(); l++) {
            tramaBinaria.append(tablaHexBin.get(trama.substring(x, x + 1)));
            x++;

        }


        System.out.println("trama binaria  " + tramaBinaria);
        System.out.println("trama binaria  " + "10111110000111111010100000010011");
        System.out.println("longitud trama binaria  " + tramaBinaria.length());

        //primeros 32 digitos
        int numeroDePid = 1;
        try {
            for (int i = 1; i <= 32; i++) {

                if (tramaBinaria.charAt(i - 1) == '1') {
                    System.out.println(numeroDePid + " = " + String.format("%02x", numeroDePid).toUpperCase());
                    //se pasa a hexadecimal y se añade al array "listaPidsSoportados"
                    listaPidsSoportados.add(String.format("%02x", numeroDePid).toUpperCase());
                }
                numeroDePid++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("error en el 1º tramo" + e);


        }
        try {
            if (tramaBinaria.length() > 32) {
                numeroDePid = 1;
                for (int i = 33; i <= 64; i++) {

                    if (tramaBinaria.charAt(i - 1) == '1') {
                        listaPidsSoportados.add(String.format("%02x", numeroDePid).toUpperCase());
                    }
                    numeroDePid++;
                }
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("error en el 2º tramo" + e);

        }
        try {
            if (tramaBinaria.length() > 64) {
                numeroDePid = 1;
                for (int i = 65; i < 96; i++) {

                    if (tramaBinaria.charAt(i - 1) == '1') {
                        listaPidsSoportados.add(String.format("%02x", numeroDePid).toUpperCase());
                    }
                    numeroDePid++;
                }
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("error en el 3º tramo" + e);

        }


        System.out.println("pid soportados " + listaPidsSoportados);
        System.out.println("numero de PID  " + listaPidsSoportados.size());

        return listaPidsSoportados;


    }*/

   /* public String mumeroDTCs() {
        //String strDatos="\n41 01 84 4B A5 F1 0A\r";
        //trama del movil 41018517FF00

        String strDatos;//="41 01 84 07 EF 80"
        //strDatos ="41 01 84 4B A5 F1 0A";
        String estadoErrores = "error";

        int valorDec;
        strDatos = enviar("0101");
        strDatos = strDatos.replace("SEARCHING...", "");
        strDatos = strDatos.replaceAll(" ","");
        strDatos = strDatos.replaceAll("4101","");
        System.out.println(strDatos);
        String trama = strDatos;
        StringBuffer tramaBinaria=new StringBuffer();
        int x = 0;
        //se pasa de hex a bin
        for (int l = 1; l <= trama.length(); l++) {
            tramaBinaria.append(tablaHexBin.get(trama.substring(x, x + 1)));
            x++;

        }
        System.out.println(tramaBinaria);
        try {
            //valorDec = Integer.parseInt(strDatos.substring(6, 8), 16);//funcionando
            System.out.println(strDatos.substring(0, 2));
            valorDec = Integer.parseInt(strDatos.substring(0, 2), 16);

            //System.out.println(valorDec);
            if (valorDec < 128) {
                numeroErrores = valorDec - 128;
                estadoErrores = "La luz del motor de comprobación (MIL) está apagada\n y la ECU no tiene ningún código de error almacenado";
            } else {
                numeroErrores = valorDec - 128;
                estadoErrores = "La luz del motor de verificación (MIL) está encendida<br> y la ECU <font color=blue> " + numeroErrores + "</font> errores almacenados<br>";

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        numeroErrores=4;//para ecusim movil
        return estadoErrores;


    }*/

   /* public String descripcionErrores() {
        Properties codigosError = new Properties();
        InputStream inputStream = null;
        try {
            String path =miDir.getCanonicalPath()+"/src/es/tdmsl/obd2/datos/codigosError.properties";
            System.out.println(path);
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
        respuesta = enviar("03");
        //respuesta="43 02 02 51 04 60 43 01 08 90";//ECU Engine Pro
        //respuesta =respuesta.replaceAll("\\s","");
        respuesta = respuesta.replaceAll(".: ", "");
        respuesta = respuesta.replaceAll(">", "");
        respuesta = respuesta.replaceAll(" ", "");
        //respuesta = respuesta.substring(2);
        respuesta = respuesta.substring(4);//si es can para usar con ecusim
        codigoP = new StringBuffer();
        StringBuffer codigoError = new StringBuffer();
        //int incremento = 6;//funcionando
        int incremento = 0;
        codigoError.append("<br><table border=1><tr ><td COLSPAN=8>A</td><td COLSPAN=8>B</td><td COLSPAN=8>C</td><td COLSPAN=8>D</td></tr>" +
                "<tr><td>A7</td><td>A6</td><td>A5</td><td>A4</td><td>A3</td><td>A2</td><td>A1</td><td>A0</td>" +
                "<td>B7</td><td>B6</td><td>B5</td><td>B4</td><td>B3</td><td>B2</td><td>B1</td><td>B0</td>" +
                "<td>C7</td><td>C6</td><td>C5</td><td>C4</td><td>C3</td><td>C2</td><td>C1</td><td>C0</td>" +
                "<td>D7</td><td>D6</td><td>D5</td><td>D4</td><td>D3</td><td>D2</td><td>D1</td><td>D0</td></tr>" +
                "</table><br>" +
                respuesta + "<hr><br>");
        codigoError.append("respuesta a decodificar : "+respuesta+"<br><br>");
        codigoError.append("<table border=1><th colspan=2>CODIGOS DE ERROR</th>");
        System.out.println("respuesta posicion " + incremento + " de la cadena respuesta  = " + respuesta.codePointAt(incremento)+" = "+(char)respuesta.codePointAt(incremento));
        for (int s = 0; s < numeroErrores; s++) {
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
            for (int p = incremento+1 ; p < (incremento + 4); p++) {//Introduce el resto denumeros del error
                if (respuesta.codePointAt(p) != 32) {// space
                    codigoP.append(respuesta.charAt(p));
                }

            }
            incremento = incremento + 4;//Coloca la posicion en la trama para el siguiente//error
            System.out.println("incremento "+incremento);
            String DTC = codigoP.toString();
            String sCadena;


            //try {
            //fr = new FileReader("CodigosErrores.txt");
            //bf = new BufferedReader(fr);
                while ((sCadena = bf.readLine())!=null) {
                    if(codError.compareTo(sCadena.substring(0, 5))==0){//se compara el error leido con cada linea
                                                                       // del fichero, si lo encuentra se sale del bucle
                        codigoError.append(sCadena+"\n\n");
                        codigo_encontrado=true;
                        break;
                    }
                }

            codigoError.append("<tr><td>" + DTC + "</td><td>"+codigosError.getProperty(DTC)+"</td></tr>");
                if(codigo_encontrado==false){
                    codigoError.append("Unknown "+codError+" error\n\n");
                }
            //} catch (IOException e) {
            //    codigoError.append("Database error codes inaccessible");
            //}
            longitudTrama = codigoP.length();//Se deja vacio el buffer que contiene el código procedente de la ECU
            codigoP.delete(0, longitudTrama);
        }
        codigoError.append("</table>");
        return codigoError.toString();
    }*/

    public void borrarDTCs() {
        enviar("04",true,true);
    }

    private String hex_to_bin(String respuesta) {
        StringBuffer tramaBin = new StringBuffer();
        //Formatter fmt = new Formatter();
        for (int i = 0; i < respuesta.length(); i++) {
            tramaBin.append(String.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(respuesta.substring(i, i + 1), 16)).toString())));
            //System.out.println(tramaBin.toString());

        }
        return tramaBin.toString();
    }


}

