package es.tdmsl.obd.presentacion;

import javax.swing.JPanel;
import es.tdmsl.obd.dominio.ControladorDominioConexion;

public class ControladorProtocolo {
    VistaProtocolo VProt;
    String protocolo;
    ControladorDominioConexion contDom;
    public ControladorProtocolo(ControladorDominioConexion contDom){
        VProt = new VistaProtocolo();
        this.contDom = contDom;
        eventos();
    }
    public String fijar_protocolo(){
        if(VProt.getJRadioButtonAutomatico().isSelected()==true){
            protocolo="atsp0";
        }
        if(VProt.getJRadioButtonPWM().isSelected()==true){
            protocolo="atsp1";
        }
        if(VProt.getJRadioButtonVPW().isSelected()==true){
            protocolo="atsp2";
        }
        if(VProt.getJRadioButtonISO().isSelected()==true){
            protocolo="atsp3";
        }
        if(VProt.getJRadioButtonKWP1().isSelected()==true){
            protocolo="atsp4";
        }
        if(VProt.getJRadioButtonKWP2().isSelected()==true){
            protocolo="atsp5";
        }
        if(VProt.getJRadioButtonCAN1().isSelected()==true){
            protocolo="atsp6";
        }
        if(VProt.getJRadioButtonCAN2().isSelected()==true){
            protocolo="atsp7";
        }
        if(VProt.getJRadioButtonCAN3().isSelected()==true){
            protocolo="atsp8";
        }
        if(VProt.getJRadioButtonCAN4().isSelected()==true){
            protocolo="atsp9";
        }
        return protocolo;
    }
    public JPanel devuelve_panel(){
        return this.VProt;
    }
    public void eventos(){
        VProt.getJComboBoxPrio1().setEnabled(false);
        VProt.getJComboBoxPrio2().setEnabled(false);
        VProt.getJComboBoxRec1().setEnabled(false);
        VProt.getJComboBoxRec2().setEnabled(false);
        VProt.getJComboBoxTran1().setEnabled(false);
        VProt.getJComboBoxTran2().setEnabled(false);
        VProt.getJButtonAplByHead().setVisible(false);
        VProt.getJButtonAplByHead().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                contDom.enviar("atsh "+VProt.getJComboBoxPrio1().getSelectedItem()+
                        VProt.getJComboBoxPrio2().getSelectedItem()+" "+
                        VProt.getJComboBoxRec1().getSelectedItem()+
                        VProt.getJComboBoxRec2().getSelectedItem()+" "+
                        VProt.getJComboBoxTran1().getSelectedItem()+
                        VProt.getJComboBoxTran2().getSelectedItem());
            }
        });
        VProt.getJCheckBoxIntroHead().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(VProt.getJCheckBoxIntroHead().isSelected()==true){
                    VProt.getJComboBoxPrio1().setEnabled(true);
                    VProt.getJComboBoxPrio2().setEnabled(true);
                    VProt.getJComboBoxRec1().setEnabled(true);
                    VProt.getJComboBoxRec2().setEnabled(true);
                    VProt.getJComboBoxTran1().setEnabled(true);
                    VProt.getJComboBoxTran2().setEnabled(true);
                    VProt.getJButtonAplByHead().setVisible(true);
                }else{
                    VProt.getJComboBoxPrio1().setEnabled(false);
                    VProt.getJComboBoxPrio2().setEnabled(false);
                    VProt.getJComboBoxRec1().setEnabled(false);
                    VProt.getJComboBoxRec2().setEnabled(false);
                    VProt.getJComboBoxTran1().setEnabled(false);
                    VProt.getJComboBoxTran2().setEnabled(false);
                    VProt.getJButtonAplByHead().setVisible(false);
                }
            }
        });
    }
}
