package es.tdmsl.obd.presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConfiguracion extends JPanel {
    private static final long serialVersionUID = 1L;
    private JComboBox jComboBoxNombrePort = null;
    private JComboBox jComboBoxDataBits = null;
    private JComboBox jComboBoxStopBits = null;
    private JComboBox jComboBoxParidad = null;
    private JComboBox jComboBoxVelocidad = null;
    private JLabel jLabelTituloPserie = null;
    private JLabel jLabelVelocidad = null;
    private JLabel jLabelNombrePuerto = null;
    private JLabel jLabelBitsDatos = null;
    private JLabel jLabelBitsStop = null;
    private JLabel jLabelParidad = null;
    private JLabel jLabeTipoConexion = null;
    private JButton jButtonAplicar = null;
    private JButton jButtonIdentificarPuertos = null;
    public ButtonGroup buttonGroup;
    private static JRadioButton jRadioButtonPSerie;
    private static JRadioButton jRadioButtonBluetooth;
    private static JRadioButton jRadioButtonWifi;
    String[] puertos = {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "/d ev/ttyS0", "/dev/ttyS1", "/dev/ttyACM0", "/dev/ttyACM1", "/dev/ttyUSB0", "/dev/ttyUSB1"};
    String[] velocidad = {"75", "110", "134", "150", "300", "600", "1200", "1800", "2400", "4800", "7200", "9600", "14400", "1920 0", "38400", "57600", "115200", "128000"};
    String[] bitsDatos = {"4", "5", "6", "7", "8"};
    String[] paridad = {"Ninguno", "Impar", "Par", "Marca", "Espacio"};
    String[] bitsParada = {"1", "2", "1,5"};
    private JList jListListaPuertos = null;
    private JScrollPane jScrollPaneListaPuertos = null;


    public VistaConfiguracion() {
        super();
        initialize();
        jRadioButtonPSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRadioButtonPSerie.setSelected(true);
                setjRadioButtonPSerie(jRadioButtonPSerie);
                System.out.println("jRadioButtonPSerie.isSelected ="+jRadioButtonPSerie.isSelected());
            }
        });
        jRadioButtonBluetooth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRadioButtonBluetooth.setSelected(true);
                setjRadioButtonBluetooth(jRadioButtonBluetooth);
                System.out.println("jRadioButtonBluetooth ="+jRadioButtonBluetooth.isSelected());
            }
        });
        jRadioButtonWifi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRadioButtonWifi.setSelected(true);
                setjRadioButtonWifi(jRadioButtonWifi);
                System.out.println("jRadioButtonWifi.isSelected = "+jRadioButtonWifi.isSelected());
            }
        });
    }

    public String getTipoConexion() {
        if (jRadioButtonPSerie.isSelected() == true) {
            return "Puerto Serie";
        }else if (jRadioButtonBluetooth.isSelected()==true){
            return "Bluetooth";
        }else if (jRadioButtonWifi.isSelected()==true){
            return "wifi";
        }


        return "Sin Selrccion";

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public JRadioButton getjRadioButtonPSerie() {
        return jRadioButtonPSerie;
    }

    public JRadioButton getjRadioButtonBluetooth() {
        return jRadioButtonBluetooth;
    }

    public JRadioButton getjRadioButtonWifi() {
        return jRadioButtonWifi;
    }

    public void setjComboBoxNombrePort(JComboBox jComboBoxNombrePort) {
        this.jComboBoxNombrePort = jComboBoxNombrePort;
    }

    public void setjRadioButtonPSerie(JRadioButton jRadioButtonPSerie) {
        this.jRadioButtonPSerie = jRadioButtonPSerie;
    }

    public void setjRadioButtonBluetooth(JRadioButton jRadioButtonBluetooth) {
        this.jRadioButtonBluetooth = jRadioButtonBluetooth;
    }

    public void setjRadioButtonWifi(JRadioButton jRadioButtonWifi) {
        this.jRadioButtonWifi = jRadioButtonWifi;
    }

    private void initialize() {
        jLabelParidad = new JLabel();
        jLabelParidad.setBounds(new Rectangle(30, 385, 68, 17));
        jLabelParidad.setText("Parity:");
        jLabelParidad.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabelBitsStop = new JLabel();
        jLabelBitsStop.setBounds(new Rectangle(30, 310, 90, 17));
        jLabelBitsStop.setText("Stop bits:");
        jLabelBitsStop.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabelBitsDatos = new JLabel();
        jLabelBitsDatos.setBounds(new Rectangle(30, 235, 88, 17));
        jLabelBitsDatos.setText("Data bits:");
        jLabelBitsDatos.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabelNombrePuerto = new JLabel();
        jLabelNombrePuerto.setBounds(new Rectangle(30, 85, 122, 17));
        jLabelNombrePuerto.setText("Port Identifier:");
        jLabelNombrePuerto.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabelVelocidad = new JLabel();
        jLabelVelocidad.setBounds(new Rectangle(30, 160, 79, 17));
        jLabelVelocidad.setText("Bit rate:");
        jLabelVelocidad.setFont(new Font("Dialog", Font.BOLD, 14));
        jLabelTituloPserie = new JLabel();
        jLabelTituloPserie.setBounds(new Rectangle(30, 25, 257, 26));
        jLabelTituloPserie.setFont(new Font("Dialog", Font.BOLD, 18));
        jLabelTituloPserie.setText("Serial port configuration");
        buttonGroup = new ButtonGroup();
        jRadioButtonPSerie = new JRadioButton("Puerto Serie");
        jRadioButtonBluetooth = new JRadioButton("Bluetooth");
        jRadioButtonWifi =  new JRadioButton("Wifi") ;



        buttonGroup.add(jRadioButtonPSerie);
        buttonGroup.add(jRadioButtonBluetooth);
        buttonGroup.add(jRadioButtonWifi);

        jRadioButtonBluetooth.setSelected(true);

        this.setLayout(null);
        this.setBackground(new Color(238, 238, 238));
        this.setLocation(new Point(0, 0));
        this.setSize(new Dimension(800, 675));
        this.add(getJPanelTipoConexion());
        this.add(getJComboBoxNombrePort(), null);
        this.add(getJComboBoxDataBits(), null);
        this.add(getJComboBoxStopBits(), null);
        this.add(getJComboBoxParidad(), null);
        this.add(getJComboBoxVelocidad(), null);
        this.add(jLabelTituloPserie, null);
        this.add(jLabelVelocidad, null);
        this.add(jLabelNombrePuerto, null);
        this.add(jLabelBitsDatos, null);
        this.add(jLabelBitsStop, null);
        this.add(jLabelParidad, null);
        this.add(getJButtonAplicar(), null);
        this.add(getJButtonIdentificarPuertos(), null);
        this.add(getJScrollPaneListaPuertos(), null);
    }

    private JPanel getJPanelTipoConexion() {
        jLabeTipoConexion = new JLabel();
        //jLabeTipoConexion.setBounds(new Rectangle(400, 0, 500, 500));//leftMargin,topMargin,width,hight,
        jLabeTipoConexion.setFont(new Font("Dialog", Font.BOLD, 18));
        jLabeTipoConexion.setText("Tipo de Conexion");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setBorder(new LineBorder(Color.black, 1));
        jPanel.setBounds(new Rectangle(400, 25, 200, 150));//leftMargin,topMargin,width,hight

        jPanel.add(jLabeTipoConexion, null);
        jPanel.add(jRadioButtonPSerie, null);
        jPanel.add(jRadioButtonBluetooth, null);
        jPanel.add(jRadioButtonWifi, null);

        return jPanel;
    }

   /* public ButtonGroup getButtonGroup() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonPSerie);
        buttonGroup.add(jRadioButtonBluetooth);
        buttonGroup.add(jRadioButtonWifi);
        System.out.println("buttonGroup "+buttonGroup.toString());
        return buttonGroup;
    }*/


    public JComboBox getJComboBoxNombrePort() {
        if (jComboBoxNombrePort == null) {
            jComboBoxNombrePort = new JComboBox(puertos);
            jComboBoxNombrePort.setBounds(new Rectangle(30, 105, 135, 25));
            jComboBoxNombrePort.setSelectedIndex(0);
        }
        return jComboBoxNombrePort;
    }

    public JComboBox getJComboBoxDataBits() {
        if (jComboBoxDataBits == null) {
            jComboBoxDataBits = new JComboBox(bitsDatos);
            jComboBoxDataBits.setBounds(new Rectangle(30, 255, 135, 25));
            jComboBoxDataBits.setSelectedIndex(4);
        }
        return jComboBoxDataBits;
    }

    public JComboBox getJComboBoxStopBits() {
        if (jComboBoxStopBits == null) {
            jComboBoxStopBits = new JComboBox(bitsParada);
            jComboBoxStopBits.setBounds(new Rectangle(30, 330, 135, 25));
            jComboBoxStopBits.setSelectedIndex(0);
        }
        return jComboBoxStopBits;
    }

    public JComboBox getJComboBoxParidad() {
        if (jComboBoxParidad == null) {
            jComboBoxParidad = new JComboBox(paridad);
            jComboBoxParidad.setBounds(new Rectangle(30, 405, 135, 25));
            jComboBoxParidad.setSelectedIndex(0);
        }
        return jComboBoxParidad;
    }

    public JComboBox getJComboBoxVelocidad() {
        if (jComboBoxVelocidad == null) {
            jComboBoxVelocidad = new JComboBox(velocidad);
            jComboBoxVelocidad.setBounds(new Rectangle(30, 180, 135, 25));
            jComboBoxVelocidad.setSelectedIndex(14);
        }
        return jComboBoxVelocidad;
    }

    public JButton getJButtonAplicar() {
        if (jButtonAplicar == null) {
            jButtonAplicar = new JButton();
            jButtonAplicar.setBounds(new Rectangle(30, 648, 115, 20));
            jButtonAplicar.setText("Apply");
        }
        return jButtonAplicar;
    }

    public JButton getJButtonIdentificarPuertos() {
        if (jButtonIdentificarPuertos == null) {
            jButtonIdentificarPuertos = new JButton();
            jButtonIdentificarPuertos.setBounds(new Rectangle(30, 520, 209, 19));
            jButtonIdentificarPuertos.setText("Search for available ports");
        }
        return jButtonIdentificarPuertos;
    }

    public JList getJListListaPuertos() {
        if (jListListaPuertos == null) {
            jListListaPuertos = new JList();
            jListListaPuertos.setEnabled(false);
            jListListaPuertos.setFont(new Font("Dialog", Font.BOLD, 12));
        }
        return jListListaPuertos;
    }

    private JScrollPane getJScrollPaneListaPuertos() {
        if (jScrollPaneListaPuertos == null) {
            jScrollPaneListaPuertos = new JScrollPane();
            jScrollPaneListaPuertos.setBounds(new Rectangle(30, 540, 209, 58));
            jScrollPaneListaPuertos.setViewportView(getJListListaPuertos());
        }
        return jScrollPaneListaPuertos;
    }



}
