package es.tdmsl.obd.presentacion;

import javax.swing.*;

import es.tdmsl.obd.dominio.ControladorDominioConexion;

import java.util.Date;

public class ControladorErrores {
    VistaCodigosError VCE;
    ControladorDominioConexion contDom;
    ControladorMediciones contMed;
    ConfirmacionBorradoErrores confErr;
    boolean ventanaConfirmaErrores;
    public ControladorErrores(ControladorDominioConexion contDom,ControladorMediciones contMed){
        this.contMed=contMed;
        VCE = new VistaCodigosError();
        this.contDom = contDom;
        ventanaConfirmaErrores=false;
        eventos();
    }
    public void eventos(){
        VCE.getJButtonErrores().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(contMed.lectura==true){
                    contMed.pararHilo=true;
                    contMed.inicializacion=true;
                    contMed.lectura=false;
                    while(contMed.indica_hilo_destruido==false){
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException error) {
                            error.printStackTrace();
                        }
                    }
                }
                consulta_errores();
            }
        });
        VCE.getJButtonBorrarErrores().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(ventanaConfirmaErrores==false){
                    confirmaErrores();
                    ventanaConfirmaErrores=true;//Esta variable evita que podamos
//abrir varias ventanas.
                }
            }
        });
    }
    public void consulta_errores(){
        System.out.println("ControladorErrores/consulta_errores");
        /*contDom.enviar("0101");
        while (contDom.getContCon().strDatos == null){
            //System.out.println("strDatos == null");
        }
        VCE.getJTextAreaErrores().append(contDom.devuelve_cantidad_errores()+"\n\n");
        System.out.println("Respuesta 0101 = "+contDom.getContCon().strDatos);*/
        //contDom.getContCon().strDatos = null;
        contDom.enviar("03");
        while (contDom.getContCon().strDatos == null){
            System.out.println("strDatos == null");
        }
        JOptionPane.showMessageDialog(null,(contDom.getContCon().strDatos.toString()));
        VCE.getJTextAreaErrores().append(contDom.devuelve_descripcion_errores()+"\n\n");
        //System.out.println("Respuesta 03 "+contDom.getContCon().strDatos);
    }
    /////////////////////////añadido/////////////
   /* public void timer() {
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
            estado("Sin conexión a la interfaz ELM.");
            strDatos = "NElm";
            listo = true;
        }
    }*/
    //////////////////////////añadido

    public JPanel devuelve_panel(){
        return this.VCE;
    }

    public void confirmaErrores(){
        confErr=new ConfirmacionBorradoErrores();
        confErr.setVisible(true);
        confErr.getJButtonOK().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                contDom.enviar("04");
                VCE.getJTextAreaErrores().append(contDom.borrado_de_errores()+"\n\n");
                confErr.setVisible(false);
                ventanaConfirmaErrores=false;
            }
        });
        confErr.getJButtonCancel().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                confErr.setVisible(false);
                ventanaConfirmaErrores=false;
            }
        });
        confErr.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                confErr.setVisible(false);
                ventanaConfirmaErrores=false;
            }
        });
    }
}
