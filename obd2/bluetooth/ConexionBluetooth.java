package es.tdmsl.obd2.bluetooth;

import es.tdmsl.obd2.controladores.ControladorConexion;
import es.tdmsl.obd2.controladores.ControladorInfSys;
import es.tdmsl.obd2.principal.VistaPrincipal;
import gnu.io.SerialPort;

import javax.bluetooth.*;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by Manu on 10/12/2016.
 */
public class ConexionBluetooth implements DiscoveryListener {
    //private SerialPort port;
    //;
    private static Object lock = new Object();

    private static Vector remdevices = new Vector();

    private static String connectionURL = null;

    private LocalDevice locdevice;

    public RemoteDevice remote_device;

    private DiscoveryAgent discoveryAgent;
    public int discoveryAgentID;
    private StreamConnection socket;
    private OutputStream outStream;
    private InputStream inStream;
    private VistaPrincipal vistaPrincipal;
    public  ControladorConexion controladorConexion;
    private StringBuffer stringBuffer;
    private boolean fin=false;
    public ControladorInfSys controladorInfSys;
    private String Nchasis;

    public static ConexionBluetooth instancia;// = new conexionBluetooth()


    public static ConexionBluetooth getInstancia(VistaPrincipal vistaPrincipal) {
        if (instancia == null) {
            instancia = new ConexionBluetooth(vistaPrincipal);
        }
        return instancia;
    }

    public ConexionBluetooth(VistaPrincipal vistaPrincipal) {
       /* Bluetooth bluetooth = Bluetooth.getInstancia();
          UUID device_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService
 */

        this.vistaPrincipal = vistaPrincipal;

        vistaPrincipal.textPanePrincipal.getDocument().putProperty(vistaPrincipal.textPanePrincipal.getDocument().StreamDescriptionProperty,null);
        try {
            locdevice = LocalDevice.getLocalDevice();
        } catch (BluetoothStateException e) {
            e.printStackTrace();
           JDialog jDialog = new JDialog(vistaPrincipal,e.toString());
           jDialog.setLocation(vistaPrincipal.getHeight()/4,vistaPrincipal.getHeight()/2);
           jDialog.setSize((e.toString().length())*7,0);
           jDialog.setVisible(true);
        }
        System.out.println(locdevice.getFriendlyName());
        stringBuffer = new StringBuffer();
        stringBuffer.append("<font><br>Dispositivo local :</font>   <font color=blue>"+locdevice.getFriendlyName()+"</font><br><br>");
        vistaPrincipal.textPanePrincipal.setText( stringBuffer+"");
        vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
        discoveryAgent = locdevice.getDiscoveryAgent();
        //buscando dispositivos
        buscarDispositivos();

        while (connectionURL == null) {
            System.out.println("esperando conexion");
            stringBuffer.append("<center><br><hr><br><font color='blue' size = 6 >Esperando conexion</font><br></center></html>");
            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("CONECTADO CON "+connectionURL);

        try {
            if (socket==null){
                JOptionPane.showMessageDialog(null,"socket es null");
            }
System.out.println(socket);
            socket = (StreamConnection) Connector.open(connectionURL);
            outStream = socket.openOutputStream();
            inStream = socket.openInputStream();
            inicializarELM();
            //controladorConexion = ControladorConexion.getInstancia(vistaPrincipal, this.outStream, this.inStream);
            String protocolo=controladorConexion.enviar("atdp",false,true);

            stringBuffer.append("<font>Conectando con</font><font color=blue> "+
                    connectionURL +
                    "</font>" +
                    "<br><br>" );
                    stringBuffer.append("<font>Abriendo el socket</font><font color=blue> "+
                            socket.toString() +
                            "</font>" +
                            "<br><br>"+
                    "<font>Inicializando....</font><font color=blue>OK</font><br><br>");
            controladorInfSys=new ControladorInfSys(vistaPrincipal,outStream,inStream);
            stringBuffer.append("<font>Protocolo encontrado : </font><font color=blue size=4>"+protocolo+"</font><br><br>");

            if (remote_device.getFriendlyName(false).equals("Manu Borge (GT-N7100)")){
                 Nchasis=controladorInfSys.getVin();
            }else {
                Nchasis="NO DISPONIBLE";
                Nchasis=controladorInfSys.getVin();
            }
            stringBuffer.append("<font>Nº de chasis </font><font color=blue>"+Nchasis+"</font>");
            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.buttonConectar.setText("CONECTADO");
            vistaPrincipal.buttonConectar.setSelected(false);
            /*if(controladorConexion.enviar("atdp",false).contains("CAN")){
                ecu=false;
            }*/

            //System.out.println( controladorConexion.enviar("atdp",false));
            //JOptionPane.showMessageDialog(null,""+protocolo);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e);
        }

    }

    private void inicializarELM() {
        controladorConexion=ControladorConexion.getInstancia(vistaPrincipal,outStream,inStream);
        controladorConexion.enviar("ATZ",true,true);
        controladorConexion.enviar("ATSP0",true,true);//set protocolo automatico
        controladorConexion.enviar("ATE0",true,true);//desactiva el eco
        controladorConexion.enviar("atdp",true,true);//Describe el protocolo actual del vehículo
        controladorConexion.enviar("ath0",true,true);//oculta cabezeras
        controladorConexion.enviar("atdpn",true,true);//muestra el Nºde protocolo

        //controladorConexion.enviar("0100");
    }


    public void buscarDispositivos() {
        System.out.println("********Iniciando Busqueda de Dispositivos******");
        stringBuffer.append("<font>&#9733 Iniciando Busqueda de Dispositivos</font> <br><br>");
        vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
        vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());

        try {
            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"discoveryAgent.startInquiry()"+e);
        }
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buscarServicios() {
        try {
            System.out.println("********Iniciando Busqueda de Servicios******");

            //String uuid = "00001101-0000-1000-8000-00805f9b34fb";
            //java.util.UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService
            UUID[] uuidset = new UUID[1];
            uuidset[0] = new UUID("1101", true);//correcto
            //uuidset[0] = new javax.bluetooth.UUID("00001101-0000-1000-8000-00805f9b34fb", false);
            //uuidset[0]=new javax.bluetooth.UUID(uuid,true);
            //discoveryAgent = locdevice.getDiscoveryAgent();

//JOptionPane.showMessageDialog(null,"nnn");
            discoveryAgent.searchServices(null, uuidset, remote_device, this);
            stringBuffer.append(" <br><br><font>&#9733 Iniciando Busqueda de Servicios sobre el dispositivo </font>" +
                    "<font color=blue>  "+remote_device.getFriendlyName(false)+" </font>" +
                    "<font>con el UUID = " + uuidset[0].toString() +"</font>" +
                    "<br><br>");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
        } catch (BluetoothStateException e) {
            e.printStackTrace();
            stringBuffer.append("<font>"+e.toString()+"</font><br>");
            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());

        } catch (IOException e) {
            e.printStackTrace();
            stringBuffer.append("<font>"+e.toString()+"</font><br>");
            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
        }
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enviar(String cmd) {

        try {
            for (int i = 0; i < cmd.length(); i++) {
                outStream.write(cmd.codePointAt(i));//Se escribe en el Puerto serie
                System.out.println("enviar " + (char) cmd.codePointAt(i));
                if ((i + 1) == cmd.length()) {
                    outStream.write(13);//Solamente necesita el retorno de carro,sin el salto de linea.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recibir() {
        StringBuffer respuesta = new StringBuffer();
        String trama = ("no data");
        int caracter = 0;

        while ((char) caracter != (char) '>') {
            try {
                caracter = inStream.read();//Se lee el Puerto serie
            } catch (IOException e) {
                e.printStackTrace();
            }
            respuesta.append((char) caracter);
            System.out.println((char) caracter);
        }

        System.out.println(respuesta.toString().replaceAll("[\n\r]", ""));
    }

   /* public static void Main(String[] args) throws BluetoothStateException, IOException {

        new ConexionBluetooth(vistaPrincipal);
    }*/

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {

        try {


            System.out.println(remoteDevice.getFriendlyName(false ));
            stringBuffer.append("<font>Encontrado el dispositivo </font><font color=blue>  "+remoteDevice.getFriendlyName(false)+"</font><br>");
            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());


            if (Objects.equals(remoteDevice.getFriendlyName(false), "OBDII")&&fin==false) {
                //remoteDevice.
                remote_device = remoteDevice;
                fin=true;
            }
            else if (Objects.equals(remoteDevice.getFriendlyName(false), "DAELIM_ISCAN-60230")&&fin==false) {
                remote_device = remoteDevice;
                fin=true;
            }
            else if (Objects.equals(remoteDevice.getFriendlyName(false), "Manu Borge (GT-N7100)")&&fin==false) {
                remote_device = remoteDevice;
                fin=true;
            }
            else if(remote_device==null){
                stringBuffer.append("<font>No se ha encontrado ningun dispositivo OBD</font><br>");
                vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
                vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
            }

        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e);
            //JOptionPane.showMessageDialog(vistaPrincipal,e);
        }

    }

    @Override
    public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {
        connectionURL = serviceRecords[0].getConnectionURL(0, false);

        try {
            stringBuffer.append("<font>Encontrado el servicio</font><font color=blue> "+
                    serviceRecords[0].getHostDevice().getFriendlyName(false)+
                    "</font><br><br>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
        vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
        //port= remote_device.

    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {
        synchronized (lock) {
            lock.notify();
        }
        switch (i) {
            case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
                System.out.println("Busqueda de servicios Completed");

                //System.out.println("connectionURL "+connectionURL);
                break;

            case DiscoveryListener.SERVICE_SEARCH_TERMINATED:

                System.out.println("SERVICE_SEARCH Terminated");

                break;

            case DiscoveryListener.SERVICE_SEARCH_ERROR:
                System.out.println("SERVICE_SEARCH Error");
                break;

            default:
                System.out.println("Unknown Response Code");
        }


    }

    @Override
    public void inquiryCompleted(int i) {

        switch (i) {
            case DiscoveryListener.INQUIRY_COMPLETED:
                System.out.println("Inquiry Completed");


                //buscando servicios
                buscarServicios();
                break;

            case DiscoveryListener.INQUIRY_TERMINATED:
                System.out.println("Inquiry Terminated");
                break;

            case DiscoveryListener.INQUIRY_ERROR:
                System.out.println("Inquiry Error");
                stringBuffer.append("<font>No se han encontrado ningun dispositivo</font><br>");
                vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
                vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
                break;

            default:
                System.out.println("Unknown Response Code");
        }
        System.out.println("Encontrados " + remdevices.size() + " dispositivos");


    }

    public OutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(OutputStream outStream) {
        this.outStream = outStream;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public SerialPort getPort() {
        //port= (SerialPort) socket;
        return null;
    }


    public  void desconexion() {
        stringBuffer.delete(0,stringBuffer.length());
        //stringBuffer.append("<font>desconectando</font><br>");
        try {
            if (false){
                stringBuffer.append("<font>Cancelando busqueda de dispositivos</font><br>");
                //discoveryAgent.cancelInquiry(DiscoveryAgent.GIAC, this);
            }

            if (false){
                stringBuffer.append("<font>Cancelando busqueda de servicios</font><br>");
                System.out.println(discoveryAgent.retrieveDevices(0)[0].getFriendlyName(false)==null);
                discoveryAgent.cancelServiceSearch(DiscoveryAgent.PREKNOWN);

            }
            if (false){
                stringBuffer.append("<font>Borrando remote_device</font>"+remote_device.getFriendlyName(false)+"<br>");
                remote_device=null;
            }


            if (false){
                stringBuffer.append("<font>Borrando outStream</font>"+outStream.toString()+"<br>");
                stringBuffer.append("<font>Borrando inputStream</font>"+inStream.toString()+"<br>");
                outStream.close();
                inStream.close();
            }

            if (false){
                stringBuffer.append("<font>Cerrando socket</font>"+socket.toString()+"<br>");


            }
            if (true){
                stringBuffer.append("<font>Eliminando Instancia y cerrado socket</font>"+instancia.toString()+"<br>");
                socket.close();
                connectionURL="";
                instancia=null;

            }
            if (true){
                connectionURL=null;
            }


            //locdevice.d
            //remote_device.
            //socket=null;
            //instancia=null;

            vistaPrincipal.textPanePrincipal.setText(stringBuffer+"");
            vistaPrincipal.textPanePrincipal.paint(vistaPrincipal.textPanePrincipal.getGraphics());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
