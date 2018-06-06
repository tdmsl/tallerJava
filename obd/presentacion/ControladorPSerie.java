package es.tdmsl.obd.presentacion;

import javax.swing.*;

import es.tdmsl.obd.datos.Conexion;
import es.tdmsl.obd.dominio.ControladorDominioConexion;

import java.util.Enumeration;

public class ControladorPSerie {
    VistaConfiguracion VC;
    Conexion con;
    ControladorDominioConexion contDom;
    public ControladorPSerie(ControladorDominioConexion contDom){
        VC = new VistaConfiguracion();
        this.contDom = contDom;
        eventos();
        datosconexion();
    }
    public void eventos(){
        VC.getJButtonIdentificarPuertos().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                puertosDisponibles();
            }
        });
        VC.getJButtonAplicar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try{
                    if(contDom.estado_conexion()==true){
                        contDom.desconectar();
                    }
                    datosconexion();
                }catch(Exception err){
                }
            }
        });
    }
    public JPanel retornaPanel(){
        return this.VC;
    }
    public void puertosDisponibles(){
        VC.getJListListaPuertos().setListData(contDom.puertosdisponibles());
    }

    public Conexion cojerDatosConexion(){

        return con;
    }

    public void datosconexion(){
        System.out.println("ControladorPuertoSerie/datosConexion()");
        con = new Conexion(Integer.parseInt((String)VC.getJComboBoxVelocidad().getSelectedItem()),
                Integer.parseInt((String)VC.getJComboBoxDataBits().getSelectedItem()),
                (VC.getJComboBoxStopBits().getSelectedIndex())+1,
                VC.getJComboBoxParidad().getSelectedIndex(),
                VC.getJComboBoxNombrePort().getSelectedItem().toString(),
                "atsp0",
                VC.getTipoConexion());
        System.out.println("Datos Conexion : "+con.toString());

    }

    /*private String getTipoConexion() {
        System.out.println("desde getTipoConexion()"+VC.getjRadioButtonPSerie().getText());
        if (VC.getjRadioButtonPSerie().isSelected()== true){
            return VC.getjRadioButtonPSerie().getText();


        }
        else if (VC.getjRadioButtonBluetooth().isSelected()==true){
            return VC.getjRadioButtonPSerie().getText();

        }
        else if (VC.getjRadioButtonWifi().isSelected()==true){
            return VC.getjRadioButtonPSerie().getText();

        }else {return "Sin selecion";}


    }*/
}
