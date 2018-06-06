/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.h2.tools.Server;



/**
 *
 * @author Manu
 */
public class ConexionBD {

    private static ConexionBD instancia = null;
    private Connection conexion;
    //private static final String PATH = System.getProperty("user.dir");
    // private static final String URL = "jdbc:h2:" + PATH + "\\datos\\DatosTaller;IFEXISTS=TRUE";//para no crear una base de datos si no exixte
    private String PATH = Principal.PATH;
    private String URL = "jdbc:h2:tcp://192.168.1.38:4370/" + PATH + " /datos/DatosTaller;IFEXISTS=TRUE;AUTO_SERVER=true";//para no crear una base de datos si no exixte
    private static final String USER = "SA";
    private static final String PASS = "";
    private String rutaDatos = "  " + PATH + "\\datos\\DatosTaller.h2.db";
    private String servidor = "localhost";
    public boolean host;
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    private ArrayList<String> valores;
    
    private ConexionBD() throws SQLException, ClassNotFoundException {
        try {
           
            if ("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress())) {
                     System.out.println("estoy sin conexion");
                     Class.forName("org.h2.Driver");// para modo enbebido 
                     URL = "jdbc:h2:" + PATH + "\\datos\\DatosTaller;DB_CLOSE_ON_EXIT=FALSE";//para no crear una base de datos si no exixte
                     host = false;
            }
            else{
                if ("si".equals(Principal.propiedades.getProperty("Servidor"))) {//es el servidor
                    //arranco el servidor
                    System.out.println("seleccionada opcion " + "Servidor");
                    //Server server = Server.createTcpServer("-tcpPort","9092", "-tcpAllowOthers");
                    Server server = Server.createTcpServer("-tcpAllowOthers");
                    server.start();
                    
                    System.out.println("Server :" + server.getStatus());
                    //URL = "jdbc:h2:" + server.getURL() + "/" + PATH + " /datos/DatosTaller;IFEXISTS=TRUE;AUTO_SERVER=true";
                     URL = "jdbc:h2:" + server.getURL() + "/" + PATH + " /datos/DatosTaller;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE";
                    System.out.println(URL);
                    //System.out.println("jdbc:h2:tcp://" + Principal.propiedades.getProperty("Server") + "/" + Principal.propiedades.getProperty("RutaBaseDatos") + ";AUTO_SERVER=true;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
                    //System.out.println("jdbc:h2:tcp://" + Principal.propiedades.getProperty("Server") + "/" + Principal.propiedades.getProperty("RutaBaseDatos"));
                    servidor = server.getStatus();
                    host = true;

             } else {
                    //me conecto al servidor con la informacion del configIni
                    URL = "jdbc:h2:tcp://" + Principal.propiedades.getProperty("Server") + "/" + Principal.propiedades.getProperty("RutaBaseDatos")+";DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE";
                    System.out.println(""+URL);
                    servidor = Principal.propiedades.getProperty("Server");
                    rutaDatos = Principal.propiedades.getProperty("RutaBaseDatos");
                    host = false;
             }
            }
conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

     public String getRutaDatos() {
        return rutaDatos;
    }
    
    public void setRutaDatos(String rutaDatos) {
        this.rutaDatos = rutaDatos;
    }

    public static ConexionBD getConexionBD() throws SQLException, ClassNotFoundException {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getCon() {
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (instancia != null) {
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    @Override
    public String toString() {
        return URL.toString();
    }

    public void creaBD() throws SQLException {
        Statement stmt = instancia.getCon().createStatement();
        ResultSet rs = null;
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ALMACEN(REFERENCIA varchar(40) NOT NULL UNIQUE,"
                + "DESCRIPCION varchar(50),"
                + "MARCA varchar(20),"
                + "PTS_VENTA double,"
                + "PTS_COSTO double,"
                + "DESCUENTO varchar(50),"
                + "PTS_VENTAE double,"
                + "ID IDENTITY NOT NULL, PRIMARY KEY (ID))");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CLIENTES(IDC integer NOT NULL, PRIMARY KEY (IDC),"
                + "NOMBRE varchar(50),"
                + "DIRECCION varchar(35),"
                + " CIUDAD varchar(35),"
                + " PROVINCIA varchar(35),"
                + " CODIGOPOSTAL varchar(5),"
                + " TELEFONO varchar(13),"
                + "OBSERVACIONESCLIENTE varchar(8000),"
                + " NIF varchar(15))");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MANOOBRA (ID IDENTITY NOT NULL, PRIMARY KEY (ID),"
                + "IDT integer,"
                + "IDR integer,"
                + "CONCEPTO varchar(50),"
                + " HORASTRABAJO double,"
                + " VALORHORA double,"
                + "TOTAL double,"
                + "FECHA date,"
                + "OPERARIO integer)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MARCAS ( ID IDENTITY  NOT NULL,"
                + "MARCA varchar(50)NOT NULL UNIQUE)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MATERIALES ( ID IDENTITY  NOT NULL, PRIMARY KEY (ID),"
                + "IDT integer,"
                + "IDR integer,"
                + "REFERENCIA varchar(40),"
                + "DESCRIPCION varchar(65),"
                + "PVP double,"
                + "CANTIDAD double,"
                + "DESCUENTO integer,"
                + "TOTAL double)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MATRICULAS (MATRICULA varchar(10)NOT NULL UNIQUE  ,"
                + "IDCLIENTE integer,"
                + "MARCA varchar(15),"
                + "MODELO varchar(25),"
                + "BASTIDOR varchar(20),"
                + "CODIGO varchar(20),"
                + "OBSERVACIONESMATRICULA varchar(8000),"
                + " PRIMARY KEY (MATRICULA))");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MODELOS (ID IDENTITY NOT NULL, PRIMARY KEY (ID),"
                + "MODELO varchar(50) NOT NULL UNIQUE,"
                + "MARCA varchar(50) NOT NULL )");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS OPERARIOS (IDO  INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0)  NOT NULL, PRIMARY KEY (IDO),"
                + "NOMBRE varchar(50),"
                + "DIRECCION varchar(50),"
                + "CODIGOPOSTAL integer,"
                + "TELEFONO varchar(50),"
                + "FECHAALTA date NOT NULL,"
                + "FECHABAJA date ,"
                + "SUELDONETO double,"
                + "SUELDOBRUTO double,"
                + "OBSERVACIONES varchar(8000))");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PERSONALIZACION ( ID IDENTITY  NOT NULL , PRIMARY KEY (ID)  ,"
                + "NOMBREEMPRESA varchar(50),"
                + "CIF varchar(50),"
                + "DIRECCION varchar(50),"
                + "CODIGOPOSTAL varchar(50),"
                + "CIUDAD varchar(50),"
                + "TELEFONO varchar(50),"
                + "EMAIL varchar(50),"
                + "IVA integer,"
                + "VALORMO integer,"
                + "NOTAS clob,"
                + "LOGO varchar(255),"
                + "REGIND varchar(50),"
                + "REGESP varchar(50),"
                + "CUENTABANCO varchar(23),"
                + "TEXTOORDEN varchar(255),"
                + "TEXTOPRESUPUESTO varchar(255),"
                + "TEXTOFACTURA varchar(255),"
                + "TEXTOFACTURAREBU varchar(255),"
                + "RUTAINFORMES varchar(255),"
                + "RUTAIMGFONDO varchar(250),"
                + "RUTAAGENDA varchar(255),"
                + "RUTABANCO varchar(255),"
                + "RUTAEXCEL varchar(255),"
                + "RUTADRIVE varchar(255),"
                + "RUTACORREO varchar(255),"
                + "NFACTURA integer,"
                + "LOOKANDFEEL varchar(50))");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS REBU(NFACTURA integer , "
                +"  IDR INTEGER,"
                +"  VALOR_COMPRA DOUBLE,"
                +"  VALOR_VENTA DOUBLE,"
                +"  FECHA_COMPRA DATE,"
                +"  FECHA_VENTA DATE)");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS REPARACIONES (IDR integer NOT NULL, PRIMARY KEY (IDR),"
                + "CUENTACLIENTE integer,"
                + "MATRICULA varchar(50),"
                + "KM integer,"
                + "FECHAENTRADA date,"
                + "FECHASALIDA date,"
                + "NFACTURA varchar(50),"
                + "RECEPTOR integer,"
                + "TRABAJOSREALIZAR varchar(2147483647),"
                + "CUENTACLIENTE2 integer,"
                + "NSINIESTRO varchar(50))");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TELEFONOS ( IDT IDENTITY NOT NULL, PRIMARY KEY (IDT),"
                + "NOMBRE varchar(50),"
                + "DIRECCION varchar(50),"
                + "TELEFONO varchar(50),"
                + "MOVIL varchar(50),"
                + "OBSERVACIONES varchar(8000))");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TRABAJOSEXT (ID IDENTITY  NOT NULL, PRIMARY KEY (ID),"
                + "IDT integer,"
                + "IDR integer,"
                + "DESCRIPCION varchar(50),"
                + "PVP double,"
                + "OPERARIO integer)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CAJA (ID IDENTITY  NOT NULL, PRIMARY KEY (ID),"
                + " FECHA date, "
                + "INGRESO double, "
                + "SALIDA double, "
                + "TIPO varchar(50), "
                + "CONCEPTO varchar(250))");

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BANCOS (ID IDENTITY  NOT NULL, PRIMARY KEY (ID),"
                + " FECHA date,"
                + " INGRESO double,"
                + " SALIDA double,"
                + " TIPO varchar(50),"
                + " CONCEPTO varchar(250),"
                + " USER integer)");
         //CREATE TABLE CONTROLHORAS (IDO DECIMAL(65535, 32767), FECHA DATE, HORA TIME(6));
         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CONTROLHORAS"
                 + "(IDO DECIMAL,"
                 + "FECHA DATE,"
                 + "ENTRADA TIME,"
                 + "SALIDA TIME)");
       
        //CARGO DATOS INICIALES EN PERSONALIZACION
        rs = stmt.executeQuery("select ID from PERSONALIZACION WHERE ID = 1");
        //rs.next();
        if (!rs.next()) {
            // JOptionPane.showMessageDialog(null, "//ResultSet is empty" );
            stmt.executeUpdate("INSERT INTO PERSONALIZACION (NOMBREEMPRESA,"
                    + " CIF,"
                    + " DIRECCION,"
                    + " CODIGOPOSTAL,"
                    + " CIUDAD,"
                    + " TELEFONO,"
                    + " EMAIL,"
                    + " IVA,"
                    + " VALORMO,"
                    + " NOTAS,"
                    + " LOGO,"
                    + " REGIND,"
                    + " REGESP,"
                    + " CUENTABANCO,"
                    + " TEXTOORDEN,"
                    + " TEXTOPRESUPUESTO,"
                    + " TEXTOFACTURA,"
                    + " RUTAINFORMES,"
                    + " RUTAIMGFONDO,"
                    + " RUTAAGENDA,"
                    + " RUTABANCO,"
                    + " RUTAEXCEL,"
                    + " RUTADRIVE,"
                    + " RUTACORREO,"
                    + " NFACTURA,"
                    + " LOOKANDFEEL)"
                    + " VALUES ("
                    + "'TDM taller de motos SL',"
                    + " 'B 80521834',"
                    + " 'Avda/ Trueba Nº 22',"
                    + " '28017',"
                    + " 'Madrid',"
                    + " '914075011',"
                    + " 'tdmsl@yahoo.es',"
                    + " 21,"
                    + " 35,"
                    + " '',"
                    + " '" + System.getProperty("user.dir") + "\\carpetasExt\\imagenes\\logos\\logo.gif',"
                    + " '063130',"
                    + " '020053',"
                    + " '2100 2971 85 02000XX',"
                    + " 'Autorizo con mi firma, abajo reflejada, a que se realizen los trabajos indicados en este documento. Asi como las pruebas de conduccion necesarias con el vehiculo. Todo ello renunciando a un presupuesto previo',"
                    + " 'Autorizo con mi firma abajo reflejada, a realizar los trabajos especificados en este documento, no siendo necesario comunicarme ninguna variacion en el importe del presupuesto siempre y cuando esta no sea superior a un 20%',"
                    + " 'Todas las reparaciones estan garantizadas por 3 meses 0 2000 Km. La garantia incluye:\\nMano de obra, piezas, servicio de grua, desplazamientos e impuestos y su cumplimiento se realizara sin que quepa postergacion',"
                    + " '" + System.getProperty("user.dir") + "\\carpetasExt\\informes',"
                    + " '" + System.getProperty("user.dir") + "\\carpetasExt\\imagenes\\fondos\\null',"
                    + " 'https://www.google.com/calendar/b/1/render?tab=wc',"
                    + " 'https://loc1.lacaixa.es/',"
                    + " 'https://docs.google.com/spreadsheets/d/1rKedp_bjEQWhImbhKZ-z8RJib3FSp30bqEL5_L_ioWg/edit?usp=sharing',"
                    + " 'https://drive.google.com/drive/#my-drive',"
                    + " 'userProgramaTaller@gmail.com',"
                    + "0,"
                    + "'javax.swing.plaf.metal.MetalLookAndFeel')");

        }
        
        //CARGO DATOS INICIALES EN CLIENTES
        rs = stmt.executeQuery("select IDC from CLIENTES WHERE IDC = 0");
        //rs.next();
        if (!rs.next()) {
             //JOptionPane.showMessageDialog(null, "//ResultSet is empty" );
            stmt.executeUpdate("INSERT INTO CLIENTES (IDC,NOMBRE,DIRECCION,CIUDAD,PROVINCIA,CODIGOPOSTAL,TELEFONO,OBSERVACIONESCLIENTE,NIF) VALUES (0,'TDM SL', 'AVDA TRUEBA Nº2','MADRID','MADRID','28017', '914075011','NO BORRAR','B80521834')");
        }
        
        //CARGO DATOS INICIALES EN OPERARIOS
        rs = stmt.executeQuery("select IDO from OPERARIOS WHERE IDO = 0");
        //rs.next();
        if (!rs.next()) {
             //JOptionPane.showMessageDialog(null, "//ResultSet is empty" );
            stmt.executeUpdate("INSERT INTO OPERARIOS (NOMBRE,DIRECCION,CODIGOPOSTAL,TELEFONO,FECHAALTA,OBSERVACIONES) VALUES ('TALLER', 'NO BORRAR',0,0, CURRENT_DATE(), 'Operario TALLER\n No debe ser borrado\nSe utiliza como usuario por defecto')");
        }
    }

    private ArrayList<String> leerArchivoINI() {//ya no se usa Ahora uso la clase properties
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        // archivo = new File (rutaConfigIni);
        //System.out.println("ruta config " + rutaConfigIni);
        archivo = new File(PATH+"\\datos\\config.ini");
        try {
            fr = new FileReader(archivo);
        } catch (FileNotFoundException ex) {
             JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        br = new BufferedReader(fr);
        // Lectura del fichero
        String linea;

        try {
            valores = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                String[] arraylinea = linea.split(",");
//                System.out.println("" + arraylinea[0]);
//                System.out.println("" + arraylinea[1]);
                valores.add(arraylinea[1]);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valores;
    }

   
}
