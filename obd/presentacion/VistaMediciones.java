package es.tdmsl.obd.presentacion;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
public class VistaMediciones extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTabbedPane jTabbedPane = null;
    private JPanel jPanel4_19 = null;
    private JPanel jPanel21_40 = null;
    private JPanel jPanel41_60 = null;
    private JTextField jTextField04 = null;
    private JTextField jTextField05 = null;
    private JTextField jTextField06 = null;
    private JTextField jTextField07 = null;
    private JTextField jTextField08 = null;
    private JTextField jTextField09 = null;
    private JTextField jTextField0A = null;
    private JTextField jTextField0B = null;
    private JTextField jTextField0C = null;
    private JTextField jTextField0D = null;
    private JTextField jTextField0E = null;
    private JTextField jTextField0F = null;
    private JTextField jTextField10 = null;
    private JTextField jTextField11 = null;
    private JTextField jTextField12 = null;
    private JTextField jTextField14 = null;
    private JTextField jTextField15 = null;
    private JTextField jTextField16 = null;
    private JTextField jTextField17 = null;
    private JTextField jTextField18 = null;
    private JTextField jTextField19 = null;
    private JTextField jTextField1A = null;
    private JTextField jTextField1B = null;
    private JTextField jTextField1E = null;
    private JTextField jTextField1F = null;
    private JTextField jTextField24 = null;
    private JTextField jTextField25 = null;
    private JTextField jTextField26 = null;
    private JTextField jTextField27 = null;
    private JTextField jTextField28 = null;
    private JTextField jTextField29 = null;
    private JTextField jTextField2A = null;
    private JTextField jTextField2B = null;
    private JTextField jTextField2C = null;
    private JTextField jTextField2D = null;
    private JTextField jTextField2E = null;
    private JTextField jTextField2F = null;
    private JTextField jTextField33 = null;
    private JTextField jTextField31 = null;
    private JTextField jTextField32 = null;
    private JTextField jTextField34 = null;
    private JTextField jTextField35 = null;
    private JTextField jTextField36 = null;

    private JTextField jTextField37 = null;
    private JTextField jTextField38 = null;
    private JTextField jTextField39 = null;
    private JTextField jTextField3A = null;
    private JTextField jTextField3B = null;
    private JTextField jTextField3E = null;
    private JTextField jTextField3F = null;
    private JLabel jLabel04 = null;
    private JLabel jLabel05 = null;
    private JLabel jLabel06 = null;
    private JLabel jLabel07 = null;
    private JLabel jLabel08 = null;
    private JLabel jLabel09 = null;
    private JLabel jLabel0A = null;
    private JLabel jLabel0B = null;
    private JLabel jLabel0C = null;
    private JLabel jLabel0D = null;
    private JLabel jLabel0E = null;
    private JLabel jLabel0F = null;
    private JLabel jLabel10 = null;
    private JLabel jLabel11 = null;
    private JLabel jLabel12 = null;
    private JLabel jLabel14 = null;
    private JLabel jLabel15 = null;
    private JLabel jLabel16 = null;
    private JLabel jLabel18 = null;
    private JLabel jLabel19 = null;
    private JLabel jLabel17 = null;
    private JLabel jLabel1A = null;
    private JLabel jLabel1E = null;
    private JLabel jLabel1B = null;
    private JLabel jLabel1F = null;
    private JLabel jLabel24 = null;
    private JLabel jLabel25 = null;
    private JLabel jLabel26 = null;
    private JLabel jLabel27 = null;
    private JLabel jLabel28 = null;
    private JLabel jLabel29 = null;
    private JLabel jLabel2A = null;
    private JLabel jLabel2B = null;
    private JLabel jLabel2C = null;
    private JLabel jLabel2D = null;
    private JLabel jLabel2E = null;
    private JLabel jLabel2F = null;
    private JLabel jLabel33 = null;
    private JLabel jLabel31 = null;
    private JLabel jLabel32 = null;
    private JLabel jLabel34 = null;
    private JLabel jLabel35 = null;
    private JLabel jLabel36 = null;
    private JLabel jLabel38 = null;
    private JLabel jLabel39 = null;
    private JLabel jLabel37 = null;
    private JLabel jLabel3A = null;
    private JLabel jLabel3E = null;
    private JLabel jLabel3B = null;
    private JLabel jLabel3F = null;
    private JLabel jLabel12_1 = null;
    private JLabel jLabel12_2 = null;
    private JLabel jLabelSensores = null;
    private JTextField jTextField21 = null;
    private JTextField jTextField22 = null;
    private JTextField jTextField23 = null;

    private JLabel jLabel21 = null;
    private JLabel jLabel22 = null;
    private JLabel jLabel23 = null;
    private JLabel jLabel21_1 = null;
    private JTextField jTextField3C = null;
    private JTextField jTextField3D = null;
    private JLabel jLabel3C = null;
    private JLabel jLabel3D = null;
    private JButton jButtonLeer = null;
    private JButton jButtonDetener = null;
    private JTextField jTextField42 = null;
    private JTextField jTextField43 = null;
    private JTextField jTextField44 = null;
    private JTextField jTextField45 = null;
    private JTextField jTextField46 = null;
    private JTextField jTextField47 = null;
    private JTextField jTextField48 = null;
    private JTextField jTextField49 = null;
    private JTextField jTextField4A = null;
    private JTextField jTextField4B = null;
    private JTextField jTextField4C = null;
    private JTextField jTextField4D = null;
    private JTextField jTextField4E = null;
    private JLabel jLabel42 = null;
    private JLabel jLabel43 = null;
    private JLabel jLabel44 = null;
    private JLabel jLabel45 = null;
    private JLabel jLabel46 = null;
    private JLabel jLabel47 = null;
    private JLabel jLabel48 = null;
    private JLabel jLabel49 = null;
    private JLabel jLabel4A = null;
    private JLabel jLabel4B = null;
    private JLabel jLabel4C = null;
    private JLabel jLabel4D = null;
    private JLabel jLabel4E = null;
    private JTextField jTextField51 = null;
    private JLabel jLabel51 = null;
    private JLabel jLabel52 = null;
    private JTextField jTextField52 = null;
    private JTextField jTextField1C = null;
    private JLabel jLabel1C = null;
    public VistaMediciones() {
        super();
        initialize();
    }
    private void initialize() {
        this.setSize(803, 676);
        this.setLayout(null);
        this.add(getJTabbedPane(), null);
        this.add(getJButtonLeer(), null);
        this.add(getJButtonDetener(), null);
    }
    private JTabbedPane getJTabbedPane() {
        if (jTabbedPane == null) {
            jTabbedPane = new JTabbedPane();
            jTabbedPane.setBounds(new Rectangle(6, 6, 788, 638));
            jTabbedPane.addTab(" PID's 4-19 ", null,
                    getJPanel4_19(), null);

            jTabbedPane.addTab(" PID's 21-40 ", null,
                    getJPanel21_40(), null);
            jTabbedPane.addTab(" PID's 41-60 ", null,
                    getJPanel41_60(), null);
        }
        return jTabbedPane;
    }
    private JPanel getJPanel4_19() {
        if (jPanel4_19 == null) {
            jLabel1C = new JLabel();
            jLabel1C.setBounds(new Rectangle(5, 505, 287, 16));
            jLabel1C.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel1C.setText("This vehicle conforms the standards:");
            jLabelSensores = new JLabel();
            jLabelSensores.setBounds(new Rectangle(380, 145, 335, 17));
            jLabelSensores.setText("Oxygen sensor voltage, Short term fuel trim");
            jLabelSensores.setFont(new Font("Dialog", Font.BOLD, 14));
            jLabel12_2 = new JLabel();
            jLabel12_2.setBounds(new Rectangle(675, 121, 78, 17));
            jLabel12_2.setText("air status");
            jLabel12_2.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel12_1 = new JLabel();
            jLabel12_1.setBounds(new Rectangle(675, 103, 85, 17));
            jLabel12_1.setText("secondary");
            jLabel12_1.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel1F = new JLabel();
            jLabel1F.setBounds(new Rectangle(525, 525, 198, 17));
            jLabel1F.setText("Run time since engine start");
            jLabel1F.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel1B = new JLabel();
            jLabel1B.setBounds(new Rectangle(525, 445, 138, 17));
            jLabel1B.setText("Bank 2, Sensor 4");
            jLabel1B.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel1E = new JLabel();
            jLabel1E.setBounds(new Rectangle(525, 485, 152, 17));
            jLabel1E.setText("Auxiliary input status");
            jLabel1E.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel1A = new JLabel();
            jLabel1A.setBounds(new Rectangle(525, 405, 138, 17));
            jLabel1A.setText("Bank 2, Sensor 3");
            jLabel1A.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel17 = new JLabel();
            jLabel17.setBounds(new Rectangle(525, 285, 136, 17));
            jLabel17.setText("Bank 1, Sensor 4");
            jLabel17.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel19 = new JLabel();
            jLabel19.setBounds(new Rectangle(525, 365, 138, 17));
            jLabel19.setText("Bank 2, Sensor 2");
            jLabel19.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel18 = new JLabel();
            jLabel18.setBounds(new Rectangle(525, 325, 136, 17));
            jLabel18.setText("Bank 2, Sensor 1");
            jLabel18.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel16 = new JLabel();
            jLabel16.setBounds(new Rectangle(525, 245, 136, 17));
            jLabel16.setText("Bank 1, Sensor 3");
            jLabel16.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel15 = new JLabel();
            jLabel15.setBounds(new Rectangle(525, 205, 134, 17));
            jLabel15.setText("Bank 1, Sensor 2");
            jLabel15.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel14 = new JLabel();

            jLabel14.setBounds(new Rectangle(525, 165, 133, 17));
            jLabel14.setText("Bank 1, Sensor 1");
            jLabel14.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel12 = new JLabel();
            jLabel12.setBounds(new Rectangle(675, 85, 99, 17));
            jLabel12.setText("Commanded ");
            jLabel12.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel11 = new JLabel();
            jLabel11.setBounds(new Rectangle(525, 45, 132, 17));
            jLabel11.setText("Throttle position");
            jLabel11.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel10 = new JLabel();
            jLabel10.setBounds(new Rectangle(525, 5, 132, 17));
            jLabel10.setText("MAF air flow rate");
            jLabel10.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0F = new JLabel();
            jLabel0F.setBounds(new Rectangle(150, 445, 167, 17));
            jLabel0F.setText("Intake air temperature");
            jLabel0F.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0E = new JLabel();
            jLabel0E.setBounds(new Rectangle(150, 405, 133, 17));
            jLabel0E.setText("Timing advance");
            jLabel0E.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0D = new JLabel();
            jLabel0D.setBounds(new Rectangle(150, 365, 120, 17));
            jLabel0D.setText("Vehicle speed");
            jLabel0D.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0C = new JLabel();
            jLabel0C.setBounds(new Rectangle(150, 325, 104, 17));
            jLabel0C.setText("Engine RPM");
            jLabel0C.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0B = new JLabel();
            jLabel0B.setBounds(new Rectangle(150, 285, 186, 17));
            jLabel0B.setText("Intake manifold pressure");
            jLabel0B.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel0A = new JLabel();
            jLabel0A.setBounds(new Rectangle(150, 245, 118, 17));
            jLabel0A.setText("Fuel pressure");
            jLabel0A.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel09 = new JLabel();
            jLabel09.setBounds(new Rectangle(150, 205, 225, 17));
            jLabel09.setText("Long term fuel % trim_Bank 2");
            jLabel09.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel08 = new JLabel();
            jLabel08.setBounds(new Rectangle(150, 165, 226, 17));
            jLabel08.setText("Short term fuel % trim_Bank 2");
            jLabel08.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel07 = new JLabel();
            jLabel07.setBounds(new Rectangle(150, 125, 225, 17));
            jLabel07.setText("Long term fuel % trim_Bank 1");
            jLabel07.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel06 = new JLabel();
            jLabel06.setBounds(new Rectangle(150, 85, 224, 17));
            jLabel06.setText("Short term fuel % trim_Bank 1");
            jLabel06.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel05 = new JLabel();
            jLabel05.setBounds(new Rectangle(150, 45, 207, 17));
            jLabel05.setText("Engine coolant temperature");
            jLabel05.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel04 = new JLabel();
            jLabel04.setBounds(new Rectangle(150, 5, 206, 17));
            jLabel04.setText("Calculated engine load value");
            jLabel04.setFont(new Font("Dialog", Font.PLAIN, 14));

            jPanel4_19 = new JPanel();
            jPanel4_19.setLayout(null);
            jPanel4_19.add(getJTextField04(), null);
            jPanel4_19.add(getJTextField05(), null);
            jPanel4_19.add(getJTextField06(), null);
            jPanel4_19.add(getJTextField07(), null);
            jPanel4_19.add(getJTextField08(), null);
            jPanel4_19.add(getJTextField09(), null);
            jPanel4_19.add(getJTextField0A(), null);
            jPanel4_19.add(getJTextField0B(), null);
            jPanel4_19.add(getJTextField0C(), null);
            jPanel4_19.add(getJTextField0D(), null);
            jPanel4_19.add(getJTextField0E(), null);
            jPanel4_19.add(getJTextField0F(), null);
            jPanel4_19.add(getJTextField10(), null);
            jPanel4_19.add(getJTextField11(), null);
            jPanel4_19.add(getJTextField12(), null);
            jPanel4_19.add(getJTextField14(), null);
            jPanel4_19.add(getJTextField15(), null);
            jPanel4_19.add(getJTextField16(), null);
            jPanel4_19.add(getJTextField17(), null);
            jPanel4_19.add(getJTextField18(), null);
            jPanel4_19.add(getJTextField19(), null);
            jPanel4_19.add(getJTextField1A(), null);
            jPanel4_19.add(getJTextField1B(), null);
            jPanel4_19.add(getJTextField1E(), null);
            jPanel4_19.add(getJTextField1F(), null);
            jPanel4_19.add(jLabel04, null);
            jPanel4_19.add(jLabel05, null);
            jPanel4_19.add(jLabel06, null);
            jPanel4_19.add(jLabel07, null);
            jPanel4_19.add(jLabel08, null);
            jPanel4_19.add(jLabel09, null);
            jPanel4_19.add(jLabel0A, null);
            jPanel4_19.add(jLabel0B, null);
            jPanel4_19.add(jLabel0C, null);
            jPanel4_19.add(jLabel0D, null);
            jPanel4_19.add(jLabel0E, null);
            jPanel4_19.add(jLabel0F, null);
            jPanel4_19.add(jLabel10, null);
            jPanel4_19.add(jLabel11, null);
            jPanel4_19.add(jLabel12, null);
            jPanel4_19.add(jLabel14, null);
            jPanel4_19.add(jLabel15, null);
            jPanel4_19.add(jLabel16, null);
            jPanel4_19.add(jLabel18, null);
            jPanel4_19.add(jLabel19, null);
            jPanel4_19.add(jLabel17, null);
            jPanel4_19.add(jLabel1A, null);
            jPanel4_19.add(jLabel1E, null);
            jPanel4_19.add(jLabel1B, null);
            jPanel4_19.add(jLabel1F, null);
            jPanel4_19.add(jLabel12_1, null);
            jPanel4_19.add(jLabel12_2, null);
            jPanel4_19.add(jLabelSensores, null);
            jPanel4_19.add(getJTextField1C(), null);
            jPanel4_19.add(jLabel1C, null);
        }
        return jPanel4_19;
    }

    private JPanel getJPanel21_40() {
        if (jPanel21_40 == null) {
            jLabel3D = new JLabel();
            jLabel3D.setBounds(new Rectangle(525, 485, 258, 16));
            jLabel3D.setText("Catalyst Temperature Bank2, Sensor1");
            jLabel3D.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel3C = new JLabel();
            jLabel3C.setBounds(new Rectangle(525, 445, 258, 16));
            jLabel3C.setText("Catalyst Temperature Bank1, Sensor1");
            jLabel3C.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel21_1 = new JLabel();
            jLabel21_1.setBounds(new Rectangle(145, 23, 166, 17));
            jLabel21_1.setText("indicator lamp (MIL) on");
            jLabel21_1.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel23 = new JLabel();
            jLabel23.setBounds(new Rectangle(150, 85, 192, 16));
            jLabel23.setText("Fuel Rail Pressure (diesel)");
            jLabel23.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel22 = new JLabel();
            jLabel22.setBounds(new Rectangle(150, 45, 170, 17));
            jLabel22.setText("Fuel Rail Pressure");
            jLabel22.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel21 = new JLabel();
            jLabel21.setBounds(new Rectangle(145, 5, 243, 17));
            jLabel21.setText("Distance traveled with malfunction");
            jLabel21.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel3F = new JLabel();
            jLabel3F.setBounds(new Rectangle(525, 565, 258, 17));
            jLabel3F.setText("Catalyst Temperature Bank2, Sensor2");
            jLabel3F.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel3B = new JLabel();
            jLabel3B.setBounds(new Rectangle(525, 405, 221, 17));
            jLabel3B.setText("O2S8_WR_lambda(1): Current");
            jLabel3B.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel3E = new JLabel();
            jLabel3E.setBounds(new Rectangle(525, 525, 258, 17));
            jLabel3E.setText("Catalyst Temperature Bank1, Sensor2");
            jLabel3E.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel3A = new JLabel();
            jLabel3A.setBounds(new Rectangle(525, 365, 223, 17));
            jLabel3A.setText("O2S7_WR_lambda(1): Current");
            jLabel3A.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel37 = new JLabel();
            jLabel37.setBounds(new Rectangle(525, 245, 220, 17));
            jLabel37.setText("O2S4_WR_lambda(1): Current");
            jLabel37.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel39 = new JLabel();
            jLabel39.setBounds(new Rectangle(525, 325, 222, 17));
            jLabel39.setText("O2S6_WR_lambda(1): Current");
            jLabel39.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel38 = new JLabel();
            jLabel38.setBounds(new Rectangle(525, 285, 220, 17));
            jLabel38.setText("O2S5_WR_lambda(1): Current");
            jLabel38.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel36 = new JLabel();
            jLabel36.setBounds(new Rectangle(525, 205, 221, 17));
            jLabel36.setText("O2S3_WR_lambda(1): Current");
            jLabel36.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel35 = new JLabel();
            jLabel35.setBounds(new Rectangle(525, 165, 222, 17));
            jLabel35.setText("O2S2_WR_lambda(1): Current");
            jLabel35.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel34 = new JLabel();

            jLabel34.setBounds(new Rectangle(525, 125, 223, 17));
            jLabel34.setText("O2S1_WR_lambda(1): Current");
            jLabel34.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel32 = new JLabel();
            jLabel32.setBounds(new Rectangle(525, 45, 216, 17));
            jLabel32.setText("Evap. System Vapor Pressure");
            jLabel32.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel31 = new JLabel();
            jLabel31.setBounds(new Rectangle(525, 5, 257, 17));
            jLabel31.setText("Distance traveled since codes cleared");
            jLabel31.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel33 = new JLabel();
            jLabel33.setBounds(new Rectangle(525, 85, 159, 17));
            jLabel33.setText("Barometric pressure");
            jLabel33.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2F = new JLabel();
            jLabel2F.setBounds(new Rectangle(150, 565, 127, 17));
            jLabel2F.setText("Fuel Level Input");
            jLabel2F.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2E = new JLabel();
            jLabel2E.setBounds(new Rectangle(150, 525, 224, 17));
            jLabel2E.setText("Commanded evaporative purge");
            jLabel2E.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2D = new JLabel();
            jLabel2D.setBounds(new Rectangle(150, 485, 89, 17));
            jLabel2D.setText("EGR Error");
            jLabel2D.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2C = new JLabel();
            jLabel2C.setBounds(new Rectangle(150, 445, 153, 17));
            jLabel2C.setText("Commanded EGR");
            jLabel2C.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2B = new JLabel();
            jLabel2B.setBounds(new Rectangle(150, 405, 224, 17));
            jLabel2B.setText("O2S8_WR_lambda(1): Voltage");
            jLabel2B.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel2A = new JLabel();
            jLabel2A.setBounds(new Rectangle(150, 365, 225, 17));
            jLabel2A.setText("O2S7_WR_lambda(1): Voltage");
            jLabel2A.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel29 = new JLabel();
            jLabel29.setBounds(new Rectangle(150, 325, 226, 17));
            jLabel29.setText("O2S6_WR_lambda(1): Voltage");
            jLabel29.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel28 = new JLabel();
            jLabel28.setBounds(new Rectangle(150, 285, 225, 17));
            jLabel28.setText("O2S5_WR_lambda(1): Voltage");
            jLabel28.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel27 = new JLabel();
            jLabel27.setBounds(new Rectangle(150, 245, 225, 17));
            jLabel27.setText("O2S4_WR_lambda(1): Voltage");
            jLabel27.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel26 = new JLabel();
            jLabel26.setBounds(new Rectangle(150, 205, 227, 17));
            jLabel26.setText("O2S3_WR_lambda(1): Voltage");
            jLabel26.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel25 = new JLabel();
            jLabel25.setBounds(new Rectangle(150, 165, 225, 17));
            jLabel25.setText("O2S2_WR_lambda(1): Voltage");
            jLabel25.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel24 = new JLabel();
            jLabel24.setBounds(new Rectangle(150, 125, 227, 17));
            jLabel24.setText("O2S1_WR_lambda(1): Voltage");
            jLabel24.setFont(new Font("Dialog", Font.PLAIN, 14));

            jPanel21_40 = new JPanel();
            jPanel21_40.setLayout(null);
            jPanel21_40.add(getJTextField24(), null);
            jPanel21_40.add(getJTextField25(), null);
            jPanel21_40.add(getJTextField26(), null);
            jPanel21_40.add(getJTextField27(), null);
            jPanel21_40.add(getJTextField28(), null);
            jPanel21_40.add(getJTextField29(), null);
            jPanel21_40.add(getJTextField2A(), null);
            jPanel21_40.add(getJTextField2B(), null);
            jPanel21_40.add(getJTextField2C(), null);
            jPanel21_40.add(getJTextField2D(), null);
            jPanel21_40.add(getJTextField2E(), null);
            jPanel21_40.add(getJTextField2F(), null);
            jPanel21_40.add(getJTextField33(), null);
            jPanel21_40.add(getJTextField31(), null);
            jPanel21_40.add(getJTextField32(), null);
            jPanel21_40.add(getJTextField34(), null);
            jPanel21_40.add(getJTextField35(), null);
            jPanel21_40.add(getJTextField36(), null);
            jPanel21_40.add(getJTextField37(), null);
            jPanel21_40.add(getJTextField38(), null);
            jPanel21_40.add(getJTextField39(), null);
            jPanel21_40.add(getJTextField3A(), null);
            jPanel21_40.add(getJTextField3B(), null);
            jPanel21_40.add(getJTextField3E(), null);
            jPanel21_40.add(getJTextField3F(), null);
            jPanel21_40.add(jLabel24, null);
            jPanel21_40.add(jLabel25, null);
            jPanel21_40.add(jLabel26, null);
            jPanel21_40.add(jLabel27, null);
            jPanel21_40.add(jLabel28, null);
            jPanel21_40.add(jLabel29, null);
            jPanel21_40.add(jLabel2A, null);
            jPanel21_40.add(jLabel2B, null);
            jPanel21_40.add(jLabel2C, null);
            jPanel21_40.add(jLabel2D, null);
            jPanel21_40.add(jLabel2E, null);
            jPanel21_40.add(jLabel2F, null);
            jPanel21_40.add(jLabel33, null);
            jPanel21_40.add(jLabel31, null);
            jPanel21_40.add(jLabel32, null);
            jPanel21_40.add(jLabel34, null);
            jPanel21_40.add(jLabel35, null);
            jPanel21_40.add(jLabel36, null);
            jPanel21_40.add(jLabel38, null);
            jPanel21_40.add(jLabel39, null);
            jPanel21_40.add(jLabel37, null);
            jPanel21_40.add(jLabel3A, null);
            jPanel21_40.add(jLabel3E, null);
            jPanel21_40.add(jLabel3B, null);
            jPanel21_40.add(jLabel3F, null);
            jPanel21_40.add(getJTextField21(), null);
            jPanel21_40.add(getJTextField22(), null);
            jPanel21_40.add(getJTextField23(), null);
            jPanel21_40.add(jLabel21, null);
            jPanel21_40.add(jLabel22, null);
            jPanel21_40.add(jLabel23, null);
            jPanel21_40.add(jLabel21_1, null);
            jPanel21_40.add(getJTextField3C(), null);
            jPanel21_40.add(getJTextField3D(), null);
            jPanel21_40.add(jLabel3C, null);
            jPanel21_40.add(jLabel3D, null);

        }
        return jPanel21_40;
    }
    private JPanel getJPanel41_60() {
        if (jPanel41_60 == null) {
            jLabel52 = new JLabel();
            jLabel52.setBounds(new Rectangle(525, 85, 123, 19));
            jLabel52.setText("\tEthanol fuel %");
            jLabel52.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel51 = new JLabel();
            jLabel51.setBounds(new Rectangle(380, 5, 99, 19));
            jLabel51.setText("Fuel Type:");
            jLabel51.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel4E = new JLabel();
            jLabel4E.setBounds(new Rectangle(150, 485, 239, 19));
            jLabel4E.setText("Time since trouble codes cleared");
            jLabel4E.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel4D = new JLabel();
            jLabel4D.setBounds(new Rectangle(150, 445, 163, 19));
            jLabel4D.setText("Time run with MIL on");
            jLabel4D.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel4C = new JLabel();
            jLabel4C.setBounds(new Rectangle(150, 405, 216, 19));
            jLabel4C.setText("Commanded throttle actuator");
            jLabel4C.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel4B = new JLabel();
            jLabel4B.setBounds(new Rectangle(150, 365, 209, 19));
            jLabel4B.setText("Accelerator pedal position F");
            jLabel4B.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel4A = new JLabel();
            jLabel4A.setBounds(new Rectangle(150, 325, 209, 19));
            jLabel4A.setText("Accelerator pedal position E");
            jLabel4A.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel49 = new JLabel();
            jLabel49.setBounds(new Rectangle(150, 285, 211, 19));
            jLabel49.setText("Accelerator pedal position D");
            jLabel49.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel48 = new JLabel();
            jLabel48.setBounds(new Rectangle(150, 245, 194, 19));
            jLabel48.setText("Absolute throttle position C");
            jLabel48.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel47 = new JLabel();
            jLabel47.setBounds(new Rectangle(150, 205, 194, 19));
            jLabel47.setText("Absolute throttle position B");
            jLabel47.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel46 = new JLabel();
            jLabel46.setBounds(new Rectangle(150, 165, 182, 19));
            jLabel46.setText("Ambient air temperature");
            jLabel46.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel45 = new JLabel();
            jLabel45.setBounds(new Rectangle(150, 125, 179, 19));
            jLabel45.setText("Relative throttle position");
            jLabel45.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel44 = new JLabel();
            jLabel44.setBounds(new Rectangle(150, 85, 201, 19));
            jLabel44.setText("Command equivalence ratio");
            jLabel44.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel43 = new JLabel();
            jLabel43.setBounds(new Rectangle(150, 45, 147, 19));
            jLabel43.setText("Absolute load value");
            jLabel43.setFont(new Font("Dialog", Font.PLAIN, 14));
            jLabel42 = new JLabel();

            jLabel42.setBounds(new Rectangle(150, 5, 176, 19));
            jLabel42.setText("Control module voltage");
            jLabel42.setFont(new Font("Dialog", Font.PLAIN, 14));
            jPanel41_60 = new JPanel();
            jPanel41_60.setLayout(null);
            jPanel41_60.add(getJTextField42(), null);
            jPanel41_60.add(getJTextField43(), null);
            jPanel41_60.add(getJTextField44(), null);
            jPanel41_60.add(getJTextField45(), null);
            jPanel41_60.add(getJTextField46(), null);
            jPanel41_60.add(getJTextField47(), null);
            jPanel41_60.add(getJTextField48(), null);
            jPanel41_60.add(getJTextField49(), null);
            jPanel41_60.add(getJTextField4A(), null);
            jPanel41_60.add(getJTextField4B(), null);
            jPanel41_60.add(getJTextField4C(), null);
            jPanel41_60.add(getJTextField4D(), null);
            jPanel41_60.add(getJTextField4E(), null);
            jPanel41_60.add(jLabel42, null);
            jPanel41_60.add(jLabel43, null);
            jPanel41_60.add(jLabel44, null);
            jPanel41_60.add(jLabel45, null);
            jPanel41_60.add(jLabel46, null);
            jPanel41_60.add(jLabel47, null);
            jPanel41_60.add(jLabel48, null);
            jPanel41_60.add(jLabel49, null);
            jPanel41_60.add(jLabel4A, null);
            jPanel41_60.add(jLabel4B, null);
            jPanel41_60.add(jLabel4C, null);
            jPanel41_60.add(jLabel4D, null);
            jPanel41_60.add(jLabel4E, null);
            jPanel41_60.add(getJTextField51(), null);
            jPanel41_60.add(jLabel51, null);
            jPanel41_60.add(jLabel52, null);
            jPanel41_60.add(getJTextField52(), null);
        }
        return jPanel41_60;
    }
    public JTextField getJTextField04() {
        if (jTextField04 == null) {
            jTextField04 = new JTextField();
            jTextField04.setBounds(new Rectangle(5, 5, 140, 40));
            jTextField04.setHorizontalAlignment(JTextField.CENTER);
            jTextField04.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField04.setEditable(false);
            jTextField04.setBackground(Color.white);
        }
        return jTextField04;
    }
    public JTextField getJTextField05() {
        if (jTextField05 == null) {
            jTextField05 = new JTextField();
            jTextField05.setBounds(new Rectangle(5, 45, 140, 40));
            jTextField05.setHorizontalAlignment(JTextField.CENTER);
            jTextField05.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField05.setEditable(false);
            jTextField05.setBackground(Color.white);
        }
        return jTextField05;
    }

    public JTextField getJTextField06() {
        if (jTextField06 == null) {
            jTextField06 = new JTextField();
            jTextField06.setBounds(new Rectangle(5, 85, 140, 40));
            jTextField06.setHorizontalAlignment(JTextField.CENTER);
            jTextField06.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField06.setEditable(false);
            jTextField06.setBackground(Color.white);
        }
        return jTextField06;
    }
    public JTextField getJTextField07() {
        if (jTextField07 == null) {
            jTextField07 = new JTextField();
            jTextField07.setBounds(new Rectangle(5, 125, 140, 40));
            jTextField07.setHorizontalAlignment(JTextField.CENTER);
            jTextField07.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField07.setEditable(false);
            jTextField07.setBackground(Color.white);
        }
        return jTextField07;
    }
    public JTextField getJTextField08() {
        if (jTextField08 == null) {
            jTextField08 = new JTextField();
            jTextField08.setBounds(new Rectangle(5, 165, 140, 40));
            jTextField08.setHorizontalAlignment(JTextField.CENTER);
            jTextField08.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField08.setEditable(false);
            jTextField08.setBackground(Color.white);
        }
        return jTextField08;
    }
    public JTextField getJTextField09() {
        if (jTextField09 == null) {
            jTextField09 = new JTextField();
            jTextField09.setBounds(new Rectangle(5, 205, 140, 40));
            jTextField09.setHorizontalAlignment(JTextField.CENTER);
            jTextField09.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField09.setEditable(false);
            jTextField09.setBackground(Color.white);
        }
        return jTextField09;
    }
    public JTextField getJTextField0A() {
        if (jTextField0A == null) {
            jTextField0A = new JTextField();
            jTextField0A.setBounds(new Rectangle(5, 245, 140, 40));
            jTextField0A.setHorizontalAlignment(JTextField.CENTER);
            jTextField0A.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0A.setEditable(false);
            jTextField0A.setBackground(Color.white);
        }
        return jTextField0A;
    }
    public JTextField getJTextField0B() {
        if (jTextField0B == null) {
            jTextField0B = new JTextField();

            jTextField0B.setBounds(new Rectangle(5, 285, 140, 40));
            jTextField0B.setHorizontalAlignment(JTextField.CENTER);
            jTextField0B.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0B.setEditable(false);
            jTextField0B.setBackground(Color.white);
        }
        return jTextField0B;
    }
    public JTextField getJTextField0C() {
        if (jTextField0C == null) {
            jTextField0C = new JTextField();
            jTextField0C.setBounds(new Rectangle(5, 325, 140, 40));
            jTextField0C.setHorizontalAlignment(JTextField.CENTER);
            jTextField0C.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0C.setEditable(false);
            jTextField0C.setBackground(Color.white);
        }
        return jTextField0C;
    }
    public JTextField getJTextField0D() {
        if (jTextField0D == null) {
            jTextField0D = new JTextField();
            jTextField0D.setBounds(new Rectangle(5, 365, 140, 40));
            jTextField0D.setHorizontalAlignment(JTextField.CENTER);
            jTextField0D.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0D.setEditable(false);
            jTextField0D.setBackground(Color.white);
        }
        return jTextField0D;
    }
    public JTextField getJTextField0E() {
        if (jTextField0E == null) {
            jTextField0E = new JTextField();
            jTextField0E.setBounds(new Rectangle(5, 405, 140, 40));
            jTextField0E.setHorizontalAlignment(JTextField.CENTER);
            jTextField0E.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0E.setEditable(false);
            jTextField0E.setBackground(Color.white);
        }
        return jTextField0E;
    }
    public JTextField getJTextField0F() {
        if (jTextField0F == null) {
            jTextField0F = new JTextField();
            jTextField0F.setBounds(new Rectangle(5, 445, 140, 40));
            jTextField0F.setHorizontalAlignment(JTextField.CENTER);
            jTextField0F.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField0F.setEditable(false);
            jTextField0F.setBackground(Color.white);
        }
        return jTextField0F;
    }
    public JTextField getJTextField10() {
        if (jTextField10 == null) {
            jTextField10 = new JTextField();
            jTextField10.setBounds(new Rectangle(380, 5, 140, 40));
            jTextField10.setHorizontalAlignment(JTextField.CENTER);
            jTextField10.setFont(new Font("Dialog", Font.BOLD, 22));

            jTextField10.setEditable(false);
            jTextField10.setBackground(Color.white);
        }
        return jTextField10;
    }
    public JTextField getJTextField11() {
        if (jTextField11 == null) {
            jTextField11 = new JTextField();
            jTextField11.setBounds(new Rectangle(380, 45, 140, 40));
            jTextField11.setHorizontalAlignment(JTextField.CENTER);
            jTextField11.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField11.setEditable(false);
            jTextField11.setBackground(Color.white);
        }
        return jTextField11;
    }
    public JTextField getJTextField12() {
        if (jTextField12 == null) {
            jTextField12 = new JTextField();
            jTextField12.setBounds(new Rectangle(380, 85, 291, 53));
            jTextField12.setHorizontalAlignment(JTextField.CENTER);
            jTextField12.setFont(new Font("Dialog", Font.PLAIN, 17));
            jTextField12.setEditable(false);
            jTextField12.setBackground(Color.white);
        }
        return jTextField12;
    }
    public JTextField getJTextField14() {
        if (jTextField14 == null) {
            jTextField14 = new JTextField();
            jTextField14.setBounds(new Rectangle(380, 165, 140, 40));
            jTextField14.setHorizontalAlignment(JTextField.CENTER);
            jTextField14.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField14.setEditable(false);
            jTextField14.setBackground(Color.white);
        }
        return jTextField14;
    }
    public JTextField getJTextField15() {
        if (jTextField15 == null) {
            jTextField15 = new JTextField();
            jTextField15.setBounds(new Rectangle(380, 205, 140, 40));
            jTextField15.setHorizontalAlignment(JTextField.CENTER);
            jTextField15.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField15.setEditable(false);
            jTextField15.setBackground(Color.white);
        }
        return jTextField15;
    }
    public JTextField getJTextField16() {
        if (jTextField16 == null) {
            jTextField16 = new JTextField();
            jTextField16.setBounds(new Rectangle(380, 245, 140, 40));
            jTextField16.setHorizontalAlignment(JTextField.CENTER);
            jTextField16.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField16.setEditable(false);
            jTextField16.setBackground(Color.white);
        }

        return jTextField16;
    }
    public JTextField getJTextField17() {
        if (jTextField17 == null) {
            jTextField17 = new JTextField();
            jTextField17.setBounds(new Rectangle(380, 285, 140, 40));
            jTextField17.setHorizontalAlignment(JTextField.CENTER);
            jTextField17.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField17.setEditable(false);
            jTextField17.setBackground(Color.white);
        }
        return jTextField17;
    }
    public JTextField getJTextField18() {
        if (jTextField18 == null) {
            jTextField18 = new JTextField();
            jTextField18.setBounds(new Rectangle(380, 325, 140, 40));
            jTextField18.setHorizontalAlignment(JTextField.CENTER);
            jTextField18.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField18.setEditable(false);
            jTextField18.setBackground(Color.white);
        }
        return jTextField18;
    }
    public JTextField getJTextField19() {
        if (jTextField19 == null) {
            jTextField19 = new JTextField();
            jTextField19.setBounds(new Rectangle(380, 365, 140, 40));
            jTextField19.setHorizontalAlignment(JTextField.CENTER);
            jTextField19.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField19.setEditable(false);
            jTextField19.setBackground(Color.white);
        }
        return jTextField19;
    }
    public JTextField getJTextField1A() {
        if (jTextField1A == null) {
            jTextField1A = new JTextField();
            jTextField1A.setBounds(new Rectangle(380, 405, 140, 40));
            jTextField1A.setHorizontalAlignment(JTextField.CENTER);
            jTextField1A.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField1A.setEditable(false);
            jTextField1A.setBackground(Color.white);
        }
        return jTextField1A;
    }
    public JTextField getJTextField1B() {
        if (jTextField1B == null) {
            jTextField1B = new JTextField();
            jTextField1B.setBounds(new Rectangle(380, 445, 140, 40));
            jTextField1B.setHorizontalAlignment(JTextField.CENTER);
            jTextField1B.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField1B.setEditable(false);
            jTextField1B.setBackground(Color.white);
        }
        return jTextField1B;
    }
    public JTextField getJTextField1E() {

        if (jTextField1E == null) {
            jTextField1E = new JTextField();
            jTextField1E.setBounds(new Rectangle(380, 485, 140, 40));
            jTextField1E.setHorizontalAlignment(JTextField.CENTER);
            jTextField1E.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField1E.setEditable(false);
            jTextField1E.setBackground(Color.white);
        }
        return jTextField1E;
    }
    public JTextField getJTextField1F() {
        if (jTextField1F == null) {
            jTextField1F = new JTextField();
            jTextField1F.setBounds(new Rectangle(380, 525, 140, 40));
            jTextField1F.setHorizontalAlignment(JTextField.CENTER);
            jTextField1F.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField1F.setEditable(false);
            jTextField1F.setBackground(Color.white);
        }
        return jTextField1F;
    }
    public JTextField getJTextField24() {
        if (jTextField24 == null) {
            jTextField24 = new JTextField();
            jTextField24.setBounds(new Rectangle(5, 125, 140, 40));
            jTextField24.setHorizontalAlignment(JTextField.CENTER);
            jTextField24.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField24.setEditable(false);
            jTextField24.setBackground(Color.white);
        }
        return jTextField24;
    }
    public JTextField getJTextField25() {
        if (jTextField25 == null) {
            jTextField25 = new JTextField();
            jTextField25.setBounds(new Rectangle(5, 165, 140, 40));
            jTextField25.setHorizontalAlignment(JTextField.CENTER);
            jTextField25.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField25.setEditable(false);
            jTextField25.setBackground(Color.white);
        }
        return jTextField25;
    }
    public JTextField getJTextField26() {
        if (jTextField26 == null) {
            jTextField26 = new JTextField();
            jTextField26.setBounds(new Rectangle(5, 205, 140, 40));
            jTextField26.setHorizontalAlignment(JTextField.CENTER);
            jTextField26.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField26.setEditable(false);
            jTextField26.setBackground(Color.white);
        }
        return jTextField26;
    }
    public JTextField getJTextField27() {
        if (jTextField27 == null) {
            jTextField27 = new JTextField();
            jTextField27.setBounds(new Rectangle(5, 245, 140, 40));

            jTextField27.setHorizontalAlignment(JTextField.CENTER);
            jTextField27.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField27.setEditable(false);
            jTextField27.setBackground(Color.white);
        }
        return jTextField27;
    }
    public JTextField getJTextField28() {
        if (jTextField28 == null) {
            jTextField28 = new JTextField();
            jTextField28.setBounds(new Rectangle(5, 285, 140, 40));
            jTextField28.setHorizontalAlignment(JTextField.CENTER);
            jTextField28.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField28.setEditable(false);
            jTextField28.setBackground(Color.white);
        }
        return jTextField28;
    }
    public JTextField getJTextField29() {
        if (jTextField29 == null) {
            jTextField29 = new JTextField();
            jTextField29.setBounds(new Rectangle(5, 325, 140, 40));
            jTextField29.setHorizontalAlignment(JTextField.CENTER);
            jTextField29.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField29.setEditable(false);
            jTextField29.setBackground(Color.white);
        }
        return jTextField29;
    }
    public JTextField getJTextField2A() {
        if (jTextField2A == null) {
            jTextField2A = new JTextField();
            jTextField2A.setBounds(new Rectangle(5, 365, 140, 40));
            jTextField2A.setHorizontalAlignment(JTextField.CENTER);
            jTextField2A.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2A.setEditable(false);
            jTextField2A.setBackground(Color.white);
        }
        return jTextField2A;
    }
    public JTextField getJTextField2B() {
        if (jTextField2B == null) {
            jTextField2B = new JTextField();
            jTextField2B.setBounds(new Rectangle(5, 405, 140, 40));
            jTextField2B.setHorizontalAlignment(JTextField.CENTER);
            jTextField2B.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2B.setEditable(false);
            jTextField2B.setBackground(Color.white);
        }
        return jTextField2B;
    }
    public JTextField getJTextField2C() {
        if (jTextField2C == null) {
            jTextField2C = new JTextField();
            jTextField2C.setBounds(new Rectangle(5, 445, 140, 40));
            jTextField2C.setHorizontalAlignment(JTextField.CENTER);
            jTextField2C.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2C.setEditable(false);

            jTextField2C.setBackground(Color.white);
        }
        return jTextField2C;
    }
    public JTextField getJTextField2D() {
        if (jTextField2D == null) {
            jTextField2D = new JTextField();
            jTextField2D.setBounds(new Rectangle(5, 485, 140, 40));
            jTextField2D.setHorizontalAlignment(JTextField.CENTER);
            jTextField2D.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2D.setEditable(false);
            jTextField2D.setBackground(Color.white);
        }
        return jTextField2D;
    }
    public JTextField getJTextField2E() {
        if (jTextField2E == null) {
            jTextField2E = new JTextField();
            jTextField2E.setBounds(new Rectangle(5, 525, 140, 40));
            jTextField2E.setHorizontalAlignment(JTextField.CENTER);
            jTextField2E.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2E.setEditable(false);
            jTextField2E.setBackground(Color.white);
        }
        return jTextField2E;
    }
    public JTextField getJTextField2F() {
        if (jTextField2F == null) {
            jTextField2F = new JTextField();
            jTextField2F.setBounds(new Rectangle(5, 565, 140, 40));
            jTextField2F.setHorizontalAlignment(JTextField.CENTER);
            jTextField2F.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField2F.setEditable(false);
            jTextField2F.setBackground(Color.white);
        }
        return jTextField2F;
    }
    public JTextField getJTextField33() {
        if (jTextField33 == null) {
            jTextField33 = new JTextField();
            jTextField33.setBounds(new Rectangle(380, 85, 140, 40));
            jTextField33.setHorizontalAlignment(JTextField.CENTER);
            jTextField33.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField33.setEditable(false);
            jTextField33.setBackground(Color.white);
        }
        return jTextField33;
    }
    public JTextField getJTextField31() {
        if (jTextField31 == null) {
            jTextField31 = new JTextField();
            jTextField31.setBounds(new Rectangle(380, 5, 140, 40));
            jTextField31.setHorizontalAlignment(JTextField.CENTER);
            jTextField31.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField31.setEditable(false);
            jTextField31.setBackground(Color.white);
        }
        return jTextField31;

    }
    public JTextField getJTextField32() {
        if (jTextField32 == null) {
            jTextField32 = new JTextField();
            jTextField32.setBounds(new Rectangle(380, 45, 140, 40));
            jTextField32.setHorizontalAlignment(JTextField.CENTER);
            jTextField32.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField32.setEditable(false);
            jTextField32.setBackground(Color.white);
        }
        return jTextField32;
    }
    public JTextField getJTextField34() {
        if (jTextField34 == null) {
            jTextField34 = new JTextField();
            jTextField34.setBounds(new Rectangle(380, 125, 140, 40));
            jTextField34.setHorizontalAlignment(JTextField.CENTER);
            jTextField34.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField34.setEditable(false);
            jTextField34.setBackground(Color.white);
        }
        return jTextField34;
    }
    public JTextField getJTextField35() {
        if (jTextField35 == null) {
            jTextField35 = new JTextField();
            jTextField35.setBounds(new Rectangle(380, 165, 140, 40));
            jTextField35.setHorizontalAlignment(JTextField.CENTER);
            jTextField35.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField35.setEditable(false);
            jTextField35.setBackground(Color.white);
        }
        return jTextField35;
    }
    public JTextField getJTextField36() {
        if (jTextField36 == null) {
            jTextField36 = new JTextField();
            jTextField36.setBounds(new Rectangle(380, 205, 140, 40));
            jTextField36.setHorizontalAlignment(JTextField.CENTER);
            jTextField36.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField36.setEditable(false);
            jTextField36.setBackground(Color.white);
        }
        return jTextField36;
    }
    public JTextField getJTextField37() {
        if (jTextField37 == null) {
            jTextField37 = new JTextField();
            jTextField37.setBounds(new Rectangle(380, 245, 140, 40));
            jTextField37.setHorizontalAlignment(JTextField.CENTER);
            jTextField37.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField37.setEditable(false);
            jTextField37.setBackground(Color.white);
        }
        return jTextField37;
    }
    public JTextField getJTextField38() {

        if (jTextField38 == null) {
            jTextField38 = new JTextField();
            jTextField38.setBounds(new Rectangle(380, 285, 140, 40));
            jTextField38.setHorizontalAlignment(JTextField.CENTER);
            jTextField38.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField38.setEditable(false);
            jTextField38.setBackground(Color.white);
        }
        return jTextField38;
    }
    public JTextField getJTextField39() {
        if (jTextField39 == null) {
            jTextField39 = new JTextField();
            jTextField39.setBounds(new Rectangle(380, 325, 140, 40));
            jTextField39.setHorizontalAlignment(JTextField.CENTER);
            jTextField39.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField39.setEditable(false);
            jTextField39.setBackground(Color.white);
        }
        return jTextField39;
    }
    public JTextField getJTextField3A() {
        if (jTextField3A == null) {
            jTextField3A = new JTextField();
            jTextField3A.setBounds(new Rectangle(380, 365, 140, 40));
            jTextField3A.setHorizontalAlignment(JTextField.CENTER);
            jTextField3A.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3A.setEditable(false);
            jTextField3A.setBackground(Color.white);
        }
        return jTextField3A;
    }
    public JTextField getJTextField3B() {
        if (jTextField3B == null) {
            jTextField3B = new JTextField();
            jTextField3B.setBounds(new Rectangle(380, 405, 140, 40));
            jTextField3B.setHorizontalAlignment(JTextField.CENTER);
            jTextField3B.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3B.setEditable(false);
            jTextField3B.setBackground(Color.white);
        }
        return jTextField3B;
    }
    public JTextField getJTextField3E() {
        if (jTextField3E == null) {
            jTextField3E = new JTextField();
            jTextField3E.setBounds(new Rectangle(380, 525, 140, 40));
            jTextField3E.setHorizontalAlignment(JTextField.CENTER);
            jTextField3E.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3E.setEditable(false);
            jTextField3E.setBackground(Color.white);
        }
        return jTextField3E;
    }
    public JTextField getJTextField3F() {
        if (jTextField3F == null) {
            jTextField3F = new JTextField();
            jTextField3F.setBounds(new Rectangle(380, 565, 140, 40));

            jTextField3F.setHorizontalAlignment(JTextField.CENTER);
            jTextField3F.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3F.setEditable(false);
            jTextField3F.setBackground(Color.white);
        }
        return jTextField3F;
    }
    public JTextField getJTextField21() {
        if (jTextField21 == null) {
            jTextField21 = new JTextField();
            jTextField21.setBounds(new Rectangle(5, 5, 140, 40));
            jTextField21.setHorizontalAlignment(JTextField.CENTER);
            jTextField21.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField21.setEditable(false);
            jTextField21.setBackground(Color.white);
        }
        return jTextField21;
    }
    public JTextField getJTextField22() {
        if (jTextField22 == null) {
            jTextField22 = new JTextField();
            jTextField22.setBounds(new Rectangle(5, 45, 140, 40));
            jTextField22.setHorizontalAlignment(JTextField.CENTER);
            jTextField22.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField22.setEditable(false);
            jTextField22.setBackground(Color.white);
        }
        return jTextField22;
    }
    public JTextField getJTextField23() {
        if (jTextField23 == null) {
            jTextField23 = new JTextField();
            jTextField23.setBounds(new Rectangle(5, 85, 140, 40));
            jTextField23.setHorizontalAlignment(JTextField.CENTER);
            jTextField23.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField23.setEditable(false);
            jTextField23.setBackground(Color.white);
        }
        return jTextField23;
    }
    public JTextField getJTextField3C() {
        if (jTextField3C == null) {
            jTextField3C = new JTextField();
            jTextField3C.setBounds(new Rectangle(380, 445, 140, 40));
            jTextField3C.setHorizontalAlignment(JTextField.CENTER);
            jTextField3C.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3C.setEditable(false);
            jTextField3C.setBackground(Color.white);
        }
        return jTextField3C;
    }
    public JTextField getJTextField3D() {
        if (jTextField3D == null) {
            jTextField3D = new JTextField();
            jTextField3D.setBounds(new Rectangle(380, 485, 140, 40));
            jTextField3D.setHorizontalAlignment(JTextField.CENTER);
            jTextField3D.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField3D.setEditable(false);

            jTextField3D.setBackground(Color.white);
        }
        return jTextField3D;
    }
    public JButton getJButtonLeer() {
        if (jButtonLeer == null) {
            jButtonLeer = new JButton();
            jButtonLeer.setBounds(new Rectangle(6, 650, 130, 20));
            jButtonLeer.setText("Start reading");
        }
        return jButtonLeer;
    }
    public JButton getJButtonDetener() {
        if (jButtonDetener == null) {
            jButtonDetener = new JButton();
            jButtonDetener.setBounds(new Rectangle(135, 650, 130, 20));
            jButtonDetener.setText("Stop");
        }
        return jButtonDetener;
    }
    public JTextField getJTextField42() {
        if (jTextField42 == null) {
            jTextField42 = new JTextField();
            jTextField42.setBounds(new Rectangle(5, 5, 140, 40));
            jTextField42.setHorizontalAlignment(JTextField.CENTER);
            jTextField42.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField42.setEditable(false);
            jTextField42.setBackground(Color.white);
        }
        return jTextField42;
    }
    public JTextField getJTextField43() {
        if (jTextField43 == null) {
            jTextField43 = new JTextField();
            jTextField43.setBounds(new Rectangle(5, 45, 140, 40));
            jTextField43.setHorizontalAlignment(JTextField.CENTER);
            jTextField43.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField43.setEditable(false);
            jTextField43.setBackground(Color.white);
        }
        return jTextField43;
    }
    public JTextField getJTextField44() {
        if (jTextField44 == null) {
            jTextField44 = new JTextField();
            jTextField44.setBounds(new Rectangle(5, 85, 140, 40));
            jTextField44.setHorizontalAlignment(JTextField.CENTER);
            jTextField44.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField44.setEditable(false);
            jTextField44.setBackground(Color.white);
        }
        return jTextField44;
    }
    public JTextField getJTextField45() {
        if (jTextField45 == null) {

            jTextField45 = new JTextField();
            jTextField45.setBounds(new Rectangle(5, 125, 140, 40));
            jTextField45.setHorizontalAlignment(JTextField.CENTER);
            jTextField45.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField45.setEditable(false);
            jTextField45.setBackground(Color.white);
        }
        return jTextField45;
    }
    public JTextField getJTextField46() {
        if (jTextField46 == null) {
            jTextField46 = new JTextField();
            jTextField46.setBounds(new Rectangle(5, 165, 140, 40));
            jTextField46.setHorizontalAlignment(JTextField.CENTER);
            jTextField46.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField46.setEditable(false);
            jTextField46.setBackground(Color.white);
        }
        return jTextField46;
    }
    public JTextField getJTextField47() {
        if (jTextField47 == null) {
            jTextField47 = new JTextField();
            jTextField47.setBounds(new Rectangle(5, 205, 140, 40));
            jTextField47.setHorizontalAlignment(JTextField.CENTER);
            jTextField47.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField47.setEditable(false);
            jTextField47.setBackground(Color.white);
        }
        return jTextField47;
    }
    public JTextField getJTextField48() {
        if (jTextField48 == null) {
            jTextField48 = new JTextField();
            jTextField48.setBounds(new Rectangle(5, 245, 140, 40));
            jTextField48.setHorizontalAlignment(JTextField.CENTER);
            jTextField48.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField48.setEditable(false);
            jTextField48.setBackground(Color.white);
        }
        return jTextField48;
    }
    public JTextField getJTextField49() {
        if (jTextField49 == null) {
            jTextField49 = new JTextField();
            jTextField49.setBounds(new Rectangle(5, 285, 140, 40));
            jTextField49.setHorizontalAlignment(JTextField.CENTER);
            jTextField49.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField49.setEditable(false);
            jTextField49.setBackground(Color.white);
        }
        return jTextField49;
    }
    public JTextField getJTextField4A() {
        if (jTextField4A == null) {
            jTextField4A = new JTextField();
            jTextField4A.setBounds(new Rectangle(5, 325, 140, 40));
            jTextField4A.setHorizontalAlignment(JTextField.CENTER);

            jTextField4A.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField4A.setEditable(false);
            jTextField4A.setBackground(Color.white);
        }
        return jTextField4A;
    }
    public JTextField getJTextField4B() {
        if (jTextField4B == null) {
            jTextField4B = new JTextField();
            jTextField4B.setBounds(new Rectangle(5, 365, 140, 40));
            jTextField4B.setHorizontalAlignment(JTextField.CENTER);
            jTextField4B.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField4B.setEditable(false);
            jTextField4B.setBackground(Color.white);
        }
        return jTextField4B;
    }
    public JTextField getJTextField4C() {
        if (jTextField4C == null) {
            jTextField4C = new JTextField();
            jTextField4C.setBounds(new Rectangle(5, 405, 140, 40));
            jTextField4C.setHorizontalAlignment(JTextField.CENTER);
            jTextField4C.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField4C.setEditable(false);
            jTextField4C.setBackground(Color.white);
        }
        return jTextField4C;
    }
    public JTextField getJTextField4D() {
        if (jTextField4D == null) {
            jTextField4D = new JTextField();
            jTextField4D.setBounds(new Rectangle(5, 445, 140, 40));
            jTextField4D.setHorizontalAlignment(JTextField.CENTER);
            jTextField4D.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField4D.setEditable(false);
            jTextField4D.setBackground(Color.white);
        }
        return jTextField4D;
    }
    public JTextField getJTextField4E() {
        if (jTextField4E == null) {
            jTextField4E = new JTextField();
            jTextField4E.setBounds(new Rectangle(5, 485, 140, 40));
            jTextField4E.setHorizontalAlignment(JTextField.CENTER);
            jTextField4E.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField4E.setEditable(false);
            jTextField4E.setBackground(Color.white);
        }
        return jTextField4E;
    }
    public JTextField getJTextField51() {
        if (jTextField51 == null) {
            jTextField51 = new JTextField();
            jTextField51.setBounds(new Rectangle(380, 25, 336, 59));
            jTextField51.setHorizontalAlignment(JTextField.CENTER);
            jTextField51.setFont(new Font("Dialog", Font.PLAIN, 17));
            jTextField51.setEditable(false);
            jTextField51.setBackground(Color.white);

        }
        return jTextField51;
    }
    public JTextField getJTextField52() {
        if (jTextField52 == null) {
            jTextField52 = new JTextField();
            jTextField52.setBounds(new Rectangle(380, 85, 140, 40));
            jTextField52.setHorizontalAlignment(JTextField.CENTER);
            jTextField52.setFont(new Font("Dialog", Font.BOLD, 22));
            jTextField52.setEditable(false);
            jTextField52.setBackground(Color.white);
        }
        return jTextField52;
    }
    public JTextField getJTextField1C() {
        if (jTextField1C == null) {
            jTextField1C = new JTextField();
            jTextField1C.setBounds(new Rectangle(5, 525, 300, 40));
            jTextField1C.setFont(new Font("Dialog", Font.PLAIN, 17));
            jTextField1C.setEditable(false);
            jTextField1C.setBackground(Color.white);
        }
        return jTextField1C;
    }
}
