package es.tdmsl.obd.Bluetooth;

import es.tdmsl.obd.presentacion.Dialogos;
import gnu.io.SerialPort;

import javax.bluetooth.*;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Manu on 10/12/2016.
 */
public class ConexionBluetooth implements DiscoveryListener {
    private SerialPort port;;
    private static Object lock = new Object();

    private static Vector remdevices = new Vector();

    private static String connectionURL = null;

    private static LocalDevice locdevice;

    private RemoteDevice remote_device;

    private DiscoveryAgent discoveryAgent;
    private StreamConnection socket;
    public OutputStream outStream;
    public InputStream inStream;

    private static ConexionBluetooth instancia;// = new conexionBluetooth()


    public static ConexionBluetooth getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBluetooth();
        }
        return instancia;
    }

    public ConexionBluetooth() {
       /* Bluetooth bluetooth = Bluetooth.getInstancia();
          UUID device_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService
 */

        try {
            locdevice = LocalDevice.getLocalDevice();
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        }
        System.out.println(locdevice.getFriendlyName());
        discoveryAgent = locdevice.getDiscoveryAgent();
        //buscando dispositivos
        buscarDispositivos();

        while (connectionURL == null) {
            System.out.println("esperando conexion");
            Dialogos.visualizaDialogo(null,locdevice.getFriendlyName()+"\n" +
                    "ESPERANDO CONEXION","Informacion",9000);

        }
        System.out.println(connectionURL);
        try {
            socket = (StreamConnection) Connector.open(connectionURL);
            outStream = socket.openOutputStream();
            inStream = socket.openInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void buscarDispositivos() {
        System.out.println("********Iniciando Busqueda de Dispositivos******");
        Dialogos.visualizaDialogo(null,locdevice.getFriendlyName()+"\n" +
                "Buscando Dispositivos","Informacion",9000);
        try {
            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
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
        System.out.println("********Iniciando Busqueda de Servicios******");
        //String uuid = "00001101-0000-1000-8000-00805f9b34fb";
        //java.util.UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService
        javax.bluetooth.UUID[] uuidset = new javax.bluetooth.UUID[1];
        uuidset[0] = new javax.bluetooth.UUID("1101", true);//correcto
        //uuidset[0] = new javax.bluetooth.UUID("00001101-0000-1000-8000-00805f9b34fb", false);
        //uuidset[0]=new javax.bluetooth.UUID(uuid,true);
        //discoveryAgent = locdevice.getDiscoveryAgent();
        try {

            discoveryAgent.searchServices(null, uuidset, remote_device, this);
        } catch (BluetoothStateException e) {
            e.printStackTrace();
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

    public static void main(String[] args) throws BluetoothStateException, IOException {

        new ConexionBluetooth();
    }

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        try {
            System.out.println(remoteDevice.getFriendlyName(false));
            remdevices.add(remoteDevice);
            if (Objects.equals(remoteDevice.getFriendlyName(false), "OBDII")) {
                remote_device = remoteDevice;
                //System.out.println("******************************"+remote_device);
            }
            if (Objects.equals(remoteDevice.getFriendlyName(false), "DAELIM_ISCAN-60230")) {
                remote_device = remoteDevice;
                //System.out.println("******************************"+remote_device);
            }
            remote_device = remoteDevice;
            System.out.println(remoteDevice.getFriendlyName(false));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {
        connectionURL = serviceRecords[0].getConnectionURL(0, false);
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
                System.out.println("Encontrados " + i + "--" + i1 + " servicio disponible");
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
                //Dialogos.visualizaDialogo(null,"Encontrado \n" ,"Informacion",9000);
                //JOptionPane.showMessageDialog(null,"","",JOptionPane.INFORMATION_MESSAGE);
                //buscando servicios
                buscarServicios();
                break;

            case DiscoveryListener.INQUIRY_TERMINATED:
                System.out.println("Inquiry Terminated");
                break;

            case DiscoveryListener.INQUIRY_ERROR:
                System.out.println("Inquiry Error");
                break;

            default:
                System.out.println("Unknown Response Code");
        }
        System.out.println("Encontrados " + remdevices.size() + " dispositivos");


    }

    public SerialPort getPort() {
        //port= (SerialPort) socket;
        return null;
    }
}
