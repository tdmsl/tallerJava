package es.tdmsl.obd.dominio;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

import es.tdmsl.obd.datos.Conexion;
import es.tdmsl.obd.datos.ControladorConexion;
import es.tdmsl.obd.presentacion.Dialogos;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;


public class ControladorDominioConexion{
    Conexion con;
    ControladorConexion contCon;



    public ControladorConexion getContCon() {
        return contCon;
    }

    public ControladorDominioConexion(JTextArea respuestaTexto) {

        contCon = new ControladorConexion(respuestaTexto);
    }


    public void establecerConexion(
            int velocidad,
            int dataBits,
            int stopBits,
            int paridad,
            String nombrePuerto,
            String protocolo,
            String tipoConexion){
        System.out.println("ControladorDominioConexion/establecerConexion()");
        con = new Conexion(velocidad,dataBits,stopBits,paridad,nombrePuerto,protocolo,tipoConexion);
        System.out.println("Conectar con los datos de Conexion : "+con.toString());
        Dialogos.visualizaDialogo(null,"Estableciendo la conexion\n" +
                ""+con.toString(),"Informacion",9000);
        contCon.establecerConexion(con);
    }




    public void enviar(String tramaEnviada){
        contCon.enviar(tramaEnviada);
    }
    public void desconectar(){
        contCon.desconectar();
    }
    public Vector puertosdisponibles(){
        return contCon.puertosdisponibles();
    }
    public String devuelve_cantidad_errores(){
        return contCon.devuelve_cantidad_errores();
    }
    public String devuelve_descripcion_errores(){
        return contCon.devuelve_descripcion_errores();
    }
    public boolean estado_conexion(){
        return contCon.estado_conexion();
    }

    public String cojer_pids(){
        return contCon.cojer_pids();
    }
    public String devuelvePid(){
        return contCon.devuelve_pid();
    }
    public void establece_pids(){
        contCon.establece_pids();
    }
    public String borrado_de_errores(){
        return contCon.borrado_de_errores();
    }
    public void borrar_pids_establecidos(){
        contCon.borrar_pids_establecidos();
    }
    public void permiteEnvioManual(boolean permiso){
        contCon.permiteEnvioManual(permiso);
    }


}