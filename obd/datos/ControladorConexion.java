package es.tdmsl.obd.datos;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import es.tdmsl.obd.Bluetooth.ConexionBluetooth;
import es.tdmsl.obd.presentacion.Dialogos;
import gnu.io.*;


/**
 * Created by Manu on 04/11/2016.
 */
public class ControladorConexion implements Runnable {
    SerialPort port;
    CommPortIdentifier idpuerto;
    OutputStream outputStream;
    InputStream inputStream;
    String estado;
    StringBuffer tramaEnviada;
    StringBuffer tramaRecibidaInicial;
    StringBuffer tramaDatos;
    StringBuffer tramaDatosCan;
    StringBuffer tramaMediciones;
    StringBuffer pids_disponibles;
    JTextArea respuestaTexto;
    public String strDatos;
    lecturaTXTErrores lecErr;
    boolean conexionCOM = false;
    boolean listo = false;
    boolean procesaDatos = false;
    boolean permisoEnvioManual = false;
    boolean DestruirHilo = false;
    private volatile Thread hilo;
    Date temps, temps2;
    String numeroProtocolo;
    Vector<String> ids;
    String ID;
    MuestraIDs MIDs;
    Conexion con;
    private ConexionBluetooth conexionBluetooth;


    public ControladorConexion(JTextArea respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
        lecErr = new lecturaTXTErrores();

        //MID = new MuestraIDs();
    }

    public ControladorConexion() {

    }

    public void establecerConexion(Conexion con) {
        this.con = con;
        this.tramaEnviada = new StringBuffer();
        this.tramaRecibidaInicial = new StringBuffer();
        this.tramaDatos = new StringBuffer();
        this.tramaDatosCan = new StringBuffer();
        this.tramaMediciones = new StringBuffer();
        this.pids_disponibles = new StringBuffer();
        this.ID = "";
        this.numeroProtocolo = con.getProtocolo();


        System.out.println("con =  " + con.toString());
        if (conexionCOM == false) {
            if ("Puerto Serie".equals(con.tipoConexion)) {
                try {
                    idpuerto = CommPortIdentifier.getPortIdentifier(con.getNombrePuerto());
                } catch (NoSuchPortException e) {
                    estado("Port " + con.getNombrePuerto() + " not available.");

                }
                try {
                    port = (SerialPort) idpuerto.open("VisualOBD", 2000);
                    estado("Connected " + con.getNombrePuerto() + ".");
                } catch (PortInUseException e) {
                    estado("Port " + con.getNombrePuerto() + " in use.");
                }
                try {
                    port.setSerialPortParams(con.getVelocidad(), con.getDataBits(), con.getStopBits(), con.getParidad());
                    inicioHiloLectura();
                } catch (UnsupportedCommOperationException e) {
                    estado("Operation not supported.");
                }
            } else if ("Bluetooth".equals(con.getTipoConexion())) {
                System.out.println("jRadioButtonBluetooth.isSelected())");
                //estado("iniciando busqueda de Bluetooth");
                //System.out.println(cP.cojerDatosConexion().getTipoConexion());
                conexionBluetooth = ConexionBluetooth.getInstancia();
                estado("conectando con Bluetooth\n");
                port = conexionBluetooth.getPort();
                inicioHiloLectura();

            } else if ("wifi".equals(con.getTipoConexion())) {
                estado("La conexion wifi no esta implementada");

            } else {
                estado("No se ha establecido el tipo de conexion");
            }


        }
        if (conexionCOM == true) {

            enviar("atz");
            //timer();
           // enviar(con.getProtocolo());
            //timer();
            enviar("atdp");
            //timer();//Describe the current Protocol
           /* enviar("atdpn");
            timer();//Describe the Protocol by Number*/
            //enviar("0100");
            //timer();
        }

    }

    private void    inicioHiloLectura() {
        hilo = new Thread(this);
        hilo.start();
        hilo.setPriority(Thread.MIN_PRIORITY);
        conexionCOM = true;
    }


    //--------------------------------------------------------------------------
    public void desconectar() {
        if (conexionCOM == true) {
            enviar("atws");//En linux el hilo del run, se para en el
            //datosRecibidos.read(), por tanto necessiatmos mandar algo
            DestruirHilo = false;//para que se mueva de ahi, atws esta bien porque resetea
            //la interface al desconectar.
            while (DestruirHilo == false) {//Espera a que se reciba la ultima trama en run()
                //para poder despues destruir el hilo y cerrar los
                //puertos.
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hilo = null;//Obliga a desacer el bucle del hilo para asi pararlo de forma
            //segura.
            conexionCOM = false;//Cuando el hilo ya ha recibido la informacion (en linux),
            //sigue el camino muriendo, ya que hilo=null
            try {
                outputStream.close();
            } catch (IOException e) {
                estado("Failed to close communication");
            }
            try {

                inputStream.close();
            } catch (IOException e) {
                estado("Failed to close communication.");
            }
            try {
                port.disableReceiveFraming();//Meramente formal
                port.close();
                estado("Offline.");
            } catch (Exception e) {
                estado("Failed to close port");
            }
        } else {
            estado("You are not connected.");
        }
    }


    //-----------------------------------------------------------------------
    public void enviar(String trama) {
        int longitudTramaEnv = 0;
        int data;
        listo = false;
        if (conexionCOM == true) {
            this.tramaEnviada.append(trama + "\r");//Solamente necessita el retorno de carro, sin el salto de linea.
            try {
                if (con.getTipoConexion() == "Puerto Serie") {
                    outputStream = port.getOutputStream();
                } else if (con.getTipoConexion() == "Bluetooth") {
                    outputStream = conexionBluetooth.outStream;
                }

                for (int i = 0; i < tramaEnviada.length(); i++) {
                    data = tramaEnviada.codePointAt(i);
                    outputStream.write(data);
                }
                longitudTramaEnv = tramaEnviada.length();
                tramaEnviada.delete(0, longitudTramaEnv);
            } catch (IOException e) {
                estado("Unable to send " + tramaEnviada + ".");
            } catch (NullPointerException e) {
                estado("error " + e + ".");
            }
        } else {
            estado("You are not connected to any port.");
        }
        listo = true;
    }
    //-----------------------------------------------------------------

    public void run() {
        boolean datos = false;
        String tramaRecibida;
        Thread thisThread = Thread.currentThread();
        while (hilo == thisThread) {
            char z;
            int longitudTrama = 0;
            int reb = 0;
            try {
                //datosRecibidos = InputStream;
                while ((reb = conexionBluetooth.inStream.read()) > -1) {
                    z = (char) reb;
                    if (reb == 62) { //Cuando se recibe 62 = >, se sale del bucle
                        // para separar los mensajes.
                        // Se hace break en run
                        break;
                    }
                    if (reb != -1) {
                        tramaRecibidaInicial.append(z);
                    }
                }
                DestruirHilo = true;//Esto da permiso al metodo desconectar() para  poner el hilo=null.


            } catch (IOException e1) {
                estado("No response.");
            }
            if (reb == 62) {
                try {
                    tramaRecibidaInicial.deleteCharAt(tramaRecibidaInicial.length() - 1); //Se borra el último  salto de linea
                } catch (RuntimeException e2) {
                    estado("No response.");
                }
                estado("Sent " + tramaRecibidaInicial.toString());
                tramaRecibida = tramaRecibidaInicial.toString();
                longitudTrama = tramaRecibidaInicial.length();
                tramaRecibidaInicial.delete(0, longitudTrama);
                if (permisoEnvioManual == false) {
                    for (int x = 0; x < tramaRecibida.length(); x++) {
                                                     //System.out.println((char) tramaRecibida.codePointAt(x));
                        //if (tramaRecibida.codePointAt(x) == 10) {
                            datos = true;
                        //}
                        if (datos == true) {
                            //System.out.println("----------------------------------------------");
                            tramaDatos.append(tramaRecibida.charAt(x));
                        }
                    }
                    datos = false;
                    try {
                        if (tramaRecibida.codePointAt(0) == 97 || tramaRecibida.codePointAt(0) == 65) {
                            procesaDatos = false;
                        } else {
                            procesaDatos = true;
                        }
                    } catch (RuntimeException e2) {
                        estado("No response.");
                    }
                   /* try {
                        if (tramaRecibida.substring(0, 5).toString().compareTo("atdpn") == 0) {
                            if (tramaDatos.codePointAt(1) == 65) {
                                numeroProtocolo = tramaDatos.substring(2, 3);
                            } else {
                                numeroProtocolo = tramaDatos.substring(1, 2);
                            }
                            estado("Protocol number: " + numeroProtocolo);
                        }
                    } catch (RuntimeException e1) {
                        estado("No response.");
                    }*/
                    if (procesaDatos == true) {
                        numeroProtocolo="6";//////////////////////////////////////////////////////////////
                        //System.out.println("tramaRecibida"+tramaRecibida.replaceAll("\r",""));
                        //System.out.println("tramaDatos"+tramaDatos.toString().replaceAll("\r",""));

                        if (Integer.parseInt(numeroProtocolo) >= 6) {
                            try {
                                System.out.println("tramaDatos "+tramaDatos.toString().replaceAll("\r",""));
                                System.out.println("tramaDatos.codePointAt(1) "+(char) tramaDatos.codePointAt(1));
                                //System.out.println("290 en el run----------------------------------------------------------");
                                //if (tramaDatos.codePointAt(18) == 52) {//=4   //modificado para 0101
                                if (tramaDatos.codePointAt(1) == 51) {//=3   //modificado para 03
                                    //El siguiente metodo diferencia las cabeceras CAN.
                                    /*if (ID.compareTo("") == 0) {
                                        ids = new Vector<String>();
                                        for (int f = 0; f < tramaDatos.length(); f++) {
                                            if (tramaDatos.codePointAt(f) == 10) {
                                                ids.add(tramaDatos.substring(f + 1, f + 4));//Delemito los IDs, hay que almacenarlos.
                                            }
                                        }
                                        mostrarIDS(ids);
                                    }*/
                                    /*for (int f = 0; f < tramaDatos.length(); f++) {
                                        if (tramaDatos.codePointAt(f) == 10) {
                                            if (ID.compareTo(tramaDatos.substring(f + 1, f + 4)) == 0) {
                                                int longitud = 0;
                                                try {
                                                    for (int g = f + 8; tramaDatos.codePointAt(g) != 10; g++) {
                                                        longitud = g;
                                                    }
                                                } catch (Exception e) {
                                                    longitud = longitud + 1;
                                                    System.out.println(e);
                                                }
                                                tramaDatosCan.append("\n" + tramaDatos.substring(f + 8, longitud));
                                                //No hacemos break para que asi coja todas las lineas con la misma cabecera.
                                            }
                                        }
                                    }*/
                                    ///////////////////////////////////
                                    //strDatos=tramaDatos.substring(17).replaceAll("\r","");//añadido para hacerlo funcionar 0101
                                    strDatos=tramaDatos.substring(15).replaceAll("\r","");//añadido para hacerlo funcionar 03
                                    //System.out.println("strDatos = "+strDatos+">");
                                    ///////////////////////////////////
                                    //strDatos = tramaDatosCan.toString();
                                    //System.out.println("tramaDatos.substring(13)"+strDatos);
                                    longitudTrama = tramaDatosCan.length();
                                    tramaDatosCan.delete(0, longitudTrama);
                                   /* if (ID == "") {
                                        strDatos = "N";//Para que los demas metodos sepan que no se han   asignado los ids CAN.
                                    }*/
                                } else if (tramaDatos.codePointAt(10) == 55) {
                                    strDatos = "Neg";//Para que se sepa que hay respuesta negativa.
                                } else {
                                    strDatos = "N";//Para que los demas metodos sepan que no a llegado  informacion
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                                strDatos = "N";
                            }
                        }
                        //si ptrtocolo !=6
                        else {
                            try {
                                if (tramaDatos.codePointAt(10) == 52) {
                                    for (int f = 0; f < tramaDatos.length(); f++) {
                                        if (tramaDatos.codePointAt(f) == 10) {
                                            tramaDatos.delete(f + 1, f + 10);
                                        }
                                    }
                                    strDatos = tramaDatos.toString();
                                } else if (tramaDatos.codePointAt(10) == 55) {
                                    strDatos = "Neg";//Para que se sepa que hay respuesta negativa.
                                } else {
                                    strDatos = "N";//Para que los demas metodos sepan que no a llegado informacion
                                }

                            } catch (Exception e) {
                                System.out.println(e);
                                strDatos = "N";
                            }
                        }
                    }
                    listo = true;
                    longitudTrama = tramaDatos.length();
                    tramaDatos.delete(0, longitudTrama);
                }
                permisoEnvioManual = false;
            }
        }
    }


    //--------------------------------------------------------------------
    public Vector puertosdisponibles() {

        int j = 1;
        Vector<String> listapuertos = new Vector<String>();
        for (Enumeration i = CommPortIdentifier.getPortIdentifiers(); i.hasMoreElements(); ) {

            CommPortIdentifier idpuerto = (CommPortIdentifier) i.nextElement();

            listapuertos.add(j++ + ". " + idpuerto.getName() + "\n");
        }
        return listapuertos;
    }

    //-------------------------------------------------
    public void estado(String estado) {
        respuestaTexto.append(">" + estado + "\n");
        respuestaTexto.setCaretPosition(respuestaTexto.getText().length());
    }

    //------------------------------------------------
    public String  devuelve_cantidad_errores() {
        if (conexionCOM == true) {          //esperando cantidad de errores
            //System.out.println("valor de strDatos encontrado despues del comando 0101 = " + strDatos);
            //strDatos = "\n41 01 84 4B A5 F1 0A\r";
// asignando cantidad de errores
            if (strDatos.compareTo("N") == 0) {
                return "ECU does not respond.";
            } else if (strDatos.compareTo("Neg") == 0) {
                return "ECU responded negatively.";
            } else if (strDatos.compareTo("NElm") == 0) {
                return "ELM does not respond.";
            } else {
                //return lecErr.descifraTramaHex(strDatos);
                return lecErr.descifraTramaHex(strDatos);
            }
        } else {
            return "Without connection port.";
        }
    }

    //-----------------------------------------------------------------
    public String devuelve_descripcion_errores() {//Este metodo coje 2 o mas lineas de errores
        if (conexionCOM == true) { //Al devolver 2 lineas, entre lineas no hay el
            //simbolo ">", esto puede ser bueno.
            //esperando descripcion de errores
            timer();
            System.out.println("valor de srtDatos encontrado despues de pulsar el comando 03 = " + strDatos);
            //strDatos = "\n43 01 33 02 45 61 21 0A\r\n43 01 33 02 45 61 21 0A\r";/////////////////////////////////////
            //asignando cantidad de errores
            if (strDatos.compareTo("N") == 0) {
                return "Not received information about errors.";
            } else if (strDatos.compareTo("Neg") == 0) {

                return "ECU responded negatively.";
            } else if (strDatos.compareTo("NElm") == 0) {
                return "ELM does not respond.";
            } else {
                Dialogos.visualizaDialogo(null, "strDatos\n" +
                        "" + strDatos, "Informacion", 9000);

                return lecErr.descifraTramaHexDescripcionErrores(strDatos + "\n");
            }
        } else {
            return "First you must connect to the COM port.";
        }
    }

    //--------------------------------------------------------------------
    public String borrado_de_errores() {
        if (conexionCOM == true) {
            timer();
            //strDatos="\n44 0A\r";
            if (strDatos.compareTo("N") == 0) {
                return "ECU does not respond.";
            } else if (strDatos.compareTo("Neg") == 0) {
                return "ECU responded negatively.";
            } else if (strDatos.compareTo("NElm") == 0) {
                return "ELM does not respond.";
            } else {
                return "Removed errors stored in the ECU..";
            }
        } else {
            return "Without connection port.";
        }
    }

    //------------------------------------------------------------
    public String devuelve_pid() {
        timer();
        //strDatos="\n41 0C 04 88 8F B0\r";
        if (strDatos.compareTo("N") == 0) {
            strDatos = "\n41 0C 00 00 00 00\r";//Para que no pete el hilo de mediciones si llega   un NO DATA o algo inadecuado.
            estado("ECU does not respond");
            return strDatos;
        } else if (strDatos.compareTo("Neg") == 0) {
            strDatos = "\n41 0C 00 00 00 00\r";
            estado("ECU responded negatively");
            return strDatos;
        } else if (strDatos.compareTo("NElm") == 0) {
            strDatos = "\n41 0C 00 00 00 00\r";
            estado("ELM does not respond");
            return strDatos;
        } else {
            return strDatos;
        }
    }

    //////---------------------------------------------------------------------------------------
    public boolean estado_conexion() {
        return conexionCOM;
    }

    //----------------------------------------------------------------------------------
    public void borrar_pids_establecidos() {
        int longitudTrama;
        longitudTrama = pids_disponibles.length();
        pids_disponibles.delete(0, longitudTrama);
    }

    //-----------------------------------------------------------------------------------------
    public void establece_pids() {
        if (conexionCOM == true) {//esperando pids
            timer();    //asignando pids
            String pids_binario;
            //strDatos = "\n41 00 FF FF FF FF 0A\r";
            int pids_decimal;
            int longitud_trama;
            int t = 1;
            if (strDatos.compareTo("N") == 0) {
                pids_disponibles.append("N00000000000000000000000000000000");
                estado("ECU does not respond");
            } else if (strDatos.compareTo("Neg") == 0) {
                pids_disponibles.append("N00000000000000000000000000000000");
                estado("ECU responded negatively");
            } else if (strDatos.compareTo("NElm") == 0) {
                pids_disponibles.append("N00000000000000000000000000000000");
                estado("ELM does not respond");
            } else {
                System.out.println("strDatos" + strDatos);
                for (int q = 0; q < 4; q++) {  //Da 4 pasadas para lo 4 bytes de datos
                    for (int r = t; r < (t + 2); r++) {  //coje los bytes, en grupos de 2 chars
                        tramaMediciones.append(strDatos.charAt(r));
                        System.out.println("trama Mediciones.append " + tramaMediciones);
                    }
                    t = t + 3;
                    pids_decimal = Integer.parseInt(tramaMediciones.toString(), 16);
//pasa de hex a int
                    pids_binario = Integer.toString(pids_decimal, 2);
//pasa de int a bin
                    longitud_trama = tramaMediciones.length();
                    tramaMediciones.delete(0, longitud_trama);
                    for (int f = 0; f < (8 - pids_binario.length()); f++) {//introduce un 0 al principio de las tramas de menos de 8 bits
                        pids_disponibles.append("0");
                    }
                    pids_disponibles.append(pids_binario);
                }
            }
        }
    }

    //------------------------------------------------------------------------------------
    public String cojer_pids() {
        if (conexionCOM == true) {
            if (pids_disponibles.codePointAt(0) != 78) {//Si los datos que llegan NO empiezan por N
                return pids_disponibles.toString();
            } else {
                estado("No data available pids");
                return "N";
            }
        } else {
            estado("Without connection port.");
            return "N";
        }
    }

    //------------------------------------------------------------------
    public void mostrarIDS(Vector vect) {
        MIDs.getJListIDs().setListData(vect);
        MIDs.getJListIDs().setSelectedIndex(0);
        MIDs.setVisible(true);
        MIDs.getJButtonIDsAceptar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                asignaIDCanBus();
                MIDs.setVisible(false);
            }
        });
    }

    //------------------------------------------------------------------

    public void asignaIDCanBus() {
        ID = MIDs.getJListIDs().getSelectedValue().toString();
    }

    //-------------------------------------------------------------
    public void timer() {
        temps = new Date();
        while (agafatemps() - temps.getTime() < 5000) {//Espera 5 segundos la respuesta del puerto procedente de la interface ELM.
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (listo == true) {
                break;
            }
        }
        if (listo == false) {
            System.out.println("Sin conexion a la interface ELM");
            estado("Sin conexión a la interfaz ELM.");
            strDatos = "NElm";
            listo = true;
        }
    }

    //-------------------------------------------------------------------
    public void permiteEnvioManual(boolean permiso) {
        permisoEnvioManual = permiso;//Avilita el envio manual de ordenes a la interface ELM.
    }

    //---------------------------
    public long agafatemps() {//Metodo que utiliza el timer().
        temps2 = new Date();
        return temps2.getTime();
    }
    //----------------------------------
}//FIN CLASE