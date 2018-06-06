package es.tdmsl.obd.presentacion;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
public class VistaProtocolo extends JPanel {
    private static final long serialVersionUID = 1L;
    private JRadioButton jRadioButtonAutomatico = null;
    private JRadioButton jRadioButtonPWM = null;
    private JRadioButton jRadioButtonVPW = null;
    private JRadioButton jRadioButtonISO = null;
    private JRadioButton jRadioButtonKWP1 = null;
    private JRadioButton jRadioButtonKWP2 = null;
    private JRadioButton jRadioButtonCAN1 = null;
    private JRadioButton jRadioButtonCAN2 = null;
    private JRadioButton jRadioButtonCAN3 = null;
    private JRadioButton jRadioButtonCAN4 = null;
    private ButtonGroup grupoBotones;
    private JLabel jLabelProtocolo = null;
    private JCheckBox jCheckBoxIntroHead = null;

    private JComboBox jComboBoxPrio1 = null;
    private JComboBox jComboBoxPrio2 = null;
    private JComboBox jComboBoxRec1 = null;
    private JComboBox jComboBoxRec2 = null;
    private JComboBox jComboBoxTran1 = null;
    private JComboBox jComboBoxTran2 = null;
    private JLabel jLabelPrio = null;
    private JLabel jLabelRec = null;
    private JLabel jLabelTran = null;
    private JButton jButtonAplByHead = null;
    private String [] hexa={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public VistaProtocolo() {
        super();
        initialize();
    }
    private void initialize() {
        jLabelTran = new JLabel();
        jLabelTran.setBounds(new Rectangle(690, 120, 86, 16));
        jLabelTran.setFont(new Font("Dialog", Font.PLAIN, 12));
        jLabelTran.setText("Tool address");
        jLabelRec = new JLabel();
        jLabelRec.setBounds(new Rectangle(575, 120, 91, 16));
        jLabelRec.setFont(new Font("Dialog", Font.PLAIN, 12));
        jLabelRec.setText("ECU address");
        jLabelPrio = new JLabel();
        jLabelPrio.setBounds(new Rectangle(445, 120, 53, 16));
        jLabelPrio.setFont(new Font("Dialog", Font.PLAIN, 12));
        jLabelPrio.setText("Priority");
        jLabelProtocolo = new JLabel();
        jLabelProtocolo.setBounds(new Rectangle(10, 10, 375, 28));
        jLabelProtocolo.setFont(new Font("Dialog", Font.BOLD, 18));
        jLabelProtocolo.setText("Selecting the communication protocol");
        this.setSize(new Dimension(775, 712));
        this.setLayout(null);
        this.add(getJRadioButtonAutomatico(), null);
        this.add(getJRadioButtonPWM(), null);
        this.add(getJRadioButtonVPW(), null);
        this.add(getJRadioButtonISO(), null);
        this.add(getJRadioButtonKWP1(), null);
        this.add(getJRadioButtonKWP2(), null);
        this.add(getJRadioButtonCAN1(), null);
        this.add(getJRadioButtonCAN2(), null);
        this.add(getJRadioButtonCAN3(), null);
        this.add(getJRadioButtonCAN4(), null);
        this.add(jLabelProtocolo, null);
        this.add(getJCheckBoxIntroHead(), null);
        this.add(getJComboBoxPrio1(), null);
        this.add(getJComboBoxPrio2(), null);
        this.add(getJComboBoxRec1(), null);
        this.add(getJComboBoxRec2(), null);
        this.add(getJComboBoxTran1(), null);
        this.add(getJComboBoxTran2(), null);
        this.add(jLabelPrio, null);
        this.add(jLabelRec, null);
        this.add(jLabelTran, null);
        this.add(getJButtonAplByHead(), null);
        grupoBotones = new ButtonGroup();
        grupoBotones.add(getJRadioButtonAutomatico());
        grupoBotones.add(getJRadioButtonPWM());
        grupoBotones.add(getJRadioButtonVPW());
        grupoBotones.add(getJRadioButtonISO());

        grupoBotones.add(getJRadioButtonKWP1());
        grupoBotones.add(getJRadioButtonKWP2());
        grupoBotones.add(getJRadioButtonCAN1());
        grupoBotones.add(getJRadioButtonCAN2());
        grupoBotones.add(getJRadioButtonCAN3());
        grupoBotones.add(getJRadioButtonCAN4());
        getJRadioButtonAutomatico().setSelected(true);
    }
    public JRadioButton getJRadioButtonAutomatico() {
        if (jRadioButtonAutomatico == null) {
            jRadioButtonAutomatico = new JRadioButton();
            jRadioButtonAutomatico.setBounds(new Rectangle(10, 70, 328, 21));
            jRadioButtonAutomatico.setText("Automatic detection of communication protocol");
        }
        return jRadioButtonAutomatico;
    }
    public JRadioButton getJRadioButtonPWM() {
        if (jRadioButtonPWM == null) {
            jRadioButtonPWM = new JRadioButton();
            jRadioButtonPWM.setBounds(new Rectangle(10, 120, 219, 21));
            jRadioButtonPWM.setText("SAE J1850 PWM (41.6 kBit/s)");
        }
        return jRadioButtonPWM;
    }
    public JRadioButton getJRadioButtonVPW() {
        if (jRadioButtonVPW == null) {
            jRadioButtonVPW = new JRadioButton();
            jRadioButtonVPW.setBounds(new Rectangle(10, 170, 219, 21));
            jRadioButtonVPW.setText("SAE J1850 VPW (10.4 kBit/s)");
        }
        return jRadioButtonVPW;
    }
    public JRadioButton getJRadioButtonISO() {
        if (jRadioButtonISO == null) {
            jRadioButtonISO = new JRadioButton();
            jRadioButtonISO.setBounds(new Rectangle(10, 220, 114, 21));
            jRadioButtonISO.setText("ISO 9141-2");
        }
        return jRadioButtonISO;
    }
    public JRadioButton getJRadioButtonKWP1() {
        if (jRadioButtonKWP1 == null) {
            jRadioButtonKWP1 = new JRadioButton();
            jRadioButtonKWP1.setBounds(new Rectangle(10, 270, 256, 21));
            jRadioButtonKWP1.setText("ISO 14230-4 KWP2000 (5-baud init)");
        }
        return jRadioButtonKWP1;
    }
    public JRadioButton getJRadioButtonKWP2() {
        if (jRadioButtonKWP2 == null) {
            jRadioButtonKWP2 = new JRadioButton();
            jRadioButtonKWP2.setBounds(new Rectangle(10, 320, 243, 21));
            jRadioButtonKWP2.setText("ISO 14230-4 KWP2000 (fast init)");
        }
        return jRadioButtonKWP2;
    }

    public JRadioButton getJRadioButtonCAN1() {
        if (jRadioButtonCAN1 == null) {
            jRadioButtonCAN1 = new JRadioButton();
            jRadioButtonCAN1.setBounds(new Rectangle(10, 370, 300, 21));
            jRadioButtonCAN1.setText("ISO 15765-4 CAN (11-bit ID, 500 kBit/s)");
        }
        return jRadioButtonCAN1;
    }
    public JRadioButton getJRadioButtonCAN2() {
        if (jRadioButtonCAN2 == null) {
            jRadioButtonCAN2 = new JRadioButton();
            jRadioButtonCAN2.setBounds(new Rectangle(10, 420, 300, 21));
            jRadioButtonCAN2.setText("ISO 15765-4 CAN (29-bit ID, 500 kBit/s)");
        }
        return jRadioButtonCAN2;
    }
    public JRadioButton getJRadioButtonCAN3() {
        if (jRadioButtonCAN3 == null) {
            jRadioButtonCAN3 = new JRadioButton();
            jRadioButtonCAN3.setBounds(new Rectangle(10, 470, 300, 21));
            jRadioButtonCAN3.setText("ISO 15765-4 CAN (11-bit ID, 250 kBit/s)");
        }
        return jRadioButtonCAN3;
    }
    public JRadioButton getJRadioButtonCAN4() {
        if (jRadioButtonCAN4 == null) {
            jRadioButtonCAN4 = new JRadioButton();
            jRadioButtonCAN4.setBounds(new Rectangle(10, 520, 300, 21));
            jRadioButtonCAN4.setText("ISO 15765-4 CAN (29-bit ID, 250 kBit/s)");
        }
        return jRadioButtonCAN4;
    }
    public JCheckBox getJCheckBoxIntroHead() {
        if (jCheckBoxIntroHead == null) {
            jCheckBoxIntroHead = new JCheckBox();
            jCheckBoxIntroHead.setBounds(new Rectangle(425, 70, 254, 21));
            jCheckBoxIntroHead.setFont(new Font("Dialog", Font.BOLD, 14));
            jCheckBoxIntroHead.setText("Enter specific header bytes");
        }
        return jCheckBoxIntroHead;
    }
    public JComboBox getJComboBoxPrio1() {
        if (jComboBoxPrio1 == null) {
            jComboBoxPrio1 = new JComboBox(hexa);
            jComboBoxPrio1.setBounds(new Rectangle(445, 135, 40, 20));
        }
        return jComboBoxPrio1;
    }
    public JComboBox getJComboBoxPrio2() {
        if (jComboBoxPrio2 == null) {
            jComboBoxPrio2 = new JComboBox(hexa);
            jComboBoxPrio2.setBounds(new Rectangle(484, 135, 40, 20));
        }
        return jComboBoxPrio2;
    }
    public JComboBox getJComboBoxRec1() {

        if (jComboBoxRec1 == null) {
            jComboBoxRec1 = new JComboBox(hexa);
            jComboBoxRec1.setBounds(new Rectangle(575, 135, 40, 20));
        }
        return jComboBoxRec1;
    }
    public JComboBox getJComboBoxRec2() {
        if (jComboBoxRec2 == null) {
            jComboBoxRec2 = new JComboBox(hexa);
            jComboBoxRec2.setBounds(new Rectangle(614, 135, 40, 20));
        }
        return jComboBoxRec2;
    }
    public JComboBox getJComboBoxTran1() {
        if (jComboBoxTran1 == null) {
            jComboBoxTran1 = new JComboBox(hexa);
            jComboBoxTran1.setBounds(new Rectangle(690, 135, 40, 20));
        }
        return jComboBoxTran1;
    }
    public JComboBox getJComboBoxTran2() {
        if (jComboBoxTran2 == null) {
            jComboBoxTran2 = new JComboBox(hexa);
            jComboBoxTran2.setBounds(new Rectangle(729, 135, 40, 20));
        }
        return jComboBoxTran2;
    }
    public JButton getJButtonAplByHead() {
        if (jButtonAplByHead == null) {
            jButtonAplByHead = new JButton();
            jButtonAplByHead.setBounds(new Rectangle(445, 180, 81, 18));
            jButtonAplByHead.setText("Apply");
        }
        return jButtonAplByHead;
    }
}
