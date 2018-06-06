package es.tdmsl.obd.presentacion;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class VistaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JMenuBar jJMenuBar = null;
    private JMenu jMenuInicio = null;
    private JMenuItem jMenuItemErrores = null;
    private JMenuItem jMenuItemLectura = null;
    private JPanel jPanel = null;
    private JButton jButtonConectar = null;
    private JButton jButtonDesconectar = null;
    private JTextArea jTextArea = null;
    private JMenu jMenuOpciones = null;
    private JMenu jMenuAyuda = null;
    private JMenuItem jMenuItemPSerie = null;
    private JMenuItem jMenuItemProtocolo = null;
    private JScrollPane jScrollPane = null;
    private JButton jButtonEnvioManual = null;
    private JTextField jTextFieldEnvioManual = null;
    private JMenuItem jMenuItemPresentacion = null;
    private JMenuItem jMenuItemAbout = null;
    private JMenuItem jMenuItemExit = null;
    private JMenuItem jMenuItemAT;
    private JMenuItem jMenuItemPids;
    private JMenuItem jMenuItemAscII;
    private JMenuItem jMenuItemImagenes;
    private JMenuItem jMenuItemNotas;

    public VistaPrincipal() {
        super();
        initialize();
    }
    public void initialize() {
        this.setSize(1050, 734);
        this.setContentPane(getJContentPane());
        this.setLocationRelativeTo(null);
        this.setJMenuBar(getJJMenuBar());
        this.setTitle("VisualOBD");
        this.setVisible(true);
    }
    public JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJPanel(), null);
        }
        return jContentPane;
    }
    private JMenuBar getJJMenuBar() {
        if (jJMenuBar == null) {
            jJMenuBar = new JMenuBar();
            jJMenuBar.add(getJMenuInicio());
            jJMenuBar.add(getJMenuOpciones());
            jJMenuBar.add(getJMenuAyuda());
        }
        return jJMenuBar;
    }
    private JMenu getJMenuInicio() {
        if (jMenuInicio == null) {
            jMenuInicio = new JMenu();
            jMenuInicio.setText(" Inicio ");
            jMenuInicio.add(getJMenuItemPresentacion());
            jMenuInicio.add(getJMenuItemErrores());
            jMenuInicio.add(getJMenuItemLectura());
            jMenuInicio.add(getJMenuItemExit());
        }
        return jMenuInicio;
    }
    public JMenuItem getJMenuItemErrores() {
        if (jMenuItemErrores == null) {
            jMenuItemErrores = new JMenuItem();
            jMenuItemErrores.setText("Lectura de DTCs");
        }
        return jMenuItemErrores;
    }
    public JMenuItem getJMenuItemLectura() {
        if (jMenuItemLectura == null) {
            jMenuItemLectura = new JMenuItem();
            jMenuItemLectura.setText("Lectura Parametros");
        }
        return jMenuItemLectura;
    }
    private JPanel getJPanel() {
        if (jPanel == null) {
            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setBounds(new Rectangle(795, 2, 250, 675));
            jPanel.add(getJButtonConectar(), null);
            jPanel.add(getJButtonDesconectar(), null);
            jPanel.add(getJScrollPane(), null);
            jPanel.add(getJButtonEnvioManual(), null);
            jPanel.add(getJTextFieldEnvioManual(), null);
        }
        return jPanel;
    }
    public JButton getJButtonConectar() {
        if (jButtonConectar == null) {
            jButtonConectar = new JButton();
            jButtonConectar.setBounds(new Rectangle(5, 647, 115, 20));
            jButtonConectar.setText("Connectar");
        }
        return jButtonConectar;
    }
    public JButton getJButtonDesconectar() {
        if (jButtonDesconectar == null) {
            jButtonDesconectar = new JButton();
            jButtonDesconectar.setBounds(new Rectangle(120, 647, 119, 20));
            jButtonDesconectar.setText("Disconnect");
        }
        return jButtonDesconectar;
    }
    public JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
            jTextArea.setEditable(false);
        }
        return jTextArea;
    }

    private JMenu getJMenuOpciones() {
        if (jMenuOpciones == null) {
            jMenuOpciones = new JMenu();
            jMenuOpciones.setText(" Options ");
            jMenuOpciones.add(getJMenuItemPSerie());
            jMenuOpciones.add(getJMenuItemProtocolo());
        }
        return jMenuOpciones;
    }
    private JMenu getJMenuAyuda() {
        if (jMenuAyuda == null) {
            jMenuAyuda = new JMenu();
            jMenuAyuda.setText(" Help ");
            jMenuAyuda.add(getJMenuItemAbout());
            jMenuAyuda.add(getJMenuComandosAT());
            jMenuAyuda.add(getJMenuPids());
            jMenuAyuda.add(getJMenuCodigosAscII());
            jMenuAyuda.add(getJMenuImagenes());
            jMenuAyuda.add(getJMenuNotas());
        }
        return jMenuAyuda;
    }



    public JMenuItem getJMenuItemPSerie() {
        if (jMenuItemPSerie == null) {
            jMenuItemPSerie = new JMenuItem();
            jMenuItemPSerie.setText("Serial Port configuration");
        }
        return jMenuItemPSerie;
    }
    public JMenuItem getJMenuItemProtocolo() {
        if (jMenuItemProtocolo == null) {
            jMenuItemProtocolo = new JMenuItem();
            jMenuItemProtocolo.setText("Communication protocol configuration");
        }
        return jMenuItemProtocolo;
    }
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new
                    JScrollPane(getJTextArea(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jScrollPane.setBounds(new Rectangle(5, 26, 235, 616));
            jScrollPane.setViewportView(getJTextArea());
            jScrollPane.setWheelScrollingEnabled(true);
        }
        return jScrollPane;
    }
    public JButton getJButtonEnvioManual() {
        if (jButtonEnvioManual == null) {
            jButtonEnvioManual = new JButton();
            jButtonEnvioManual.setBounds(new Rectangle(163, 5, 77, 19));
            jButtonEnvioManual.setText("Enviar");
        }
        return jButtonEnvioManual;
    }
    public JTextField getJTextFieldEnvioManual() {
        if (jTextFieldEnvioManual == null) {
            jTextFieldEnvioManual = new JTextField();
            jTextFieldEnvioManual.setBounds(new Rectangle(5, 5, 159, 20));

        }
        return jTextFieldEnvioManual;
    }
    public JMenuItem getJMenuItemPresentacion() {
        if (jMenuItemPresentacion == null) {
            jMenuItemPresentacion = new JMenuItem();
            jMenuItemPresentacion.setText("Initial presentation");
        }
        return jMenuItemPresentacion;
    }
    public JMenuItem getJMenuItemAbout() {
        if (jMenuItemAbout == null) {
            jMenuItemAbout = new JMenuItem();
            jMenuItemAbout.setText("About VisualOBD");
        }
        return jMenuItemAbout;
    }

    public JMenuItem getJMenuComandosAT() {
        if (jMenuItemAT == null) {
            jMenuItemAT = new JMenuItem();
            jMenuItemAT.setText("ComandosAT");
        }

        return jMenuItemAT;
    }

    public JMenuItem getJMenuPids() {
        if (jMenuItemPids == null) {
            jMenuItemPids = new JMenuItem();
            jMenuItemPids.setText("Pids");
        }

        return jMenuItemPids;
    }

    public JMenuItem getJMenuCodigosAscII() {
        if (jMenuItemAscII == null) {
            jMenuItemAscII = new JMenuItem();
            jMenuItemAscII.setText("Codigos ASC||");
        }

        return jMenuItemAscII;
    }

    public JMenuItem getJMenuImagenes() {
        if (jMenuItemImagenes == null) {
            jMenuItemImagenes = new JMenuItem();
            jMenuItemImagenes.setText("Imagenes");
        }

        return jMenuItemImagenes;
    }

    public JMenuItem getJMenuNotas() {
        if (jMenuItemNotas == null) {
            jMenuItemNotas = new JMenuItem();
            jMenuItemNotas.setText("Notas");
        }

        return jMenuItemNotas;
    }

    public JMenuItem getJMenuItemExit() {
        if (jMenuItemExit == null) {
            jMenuItemExit = new JMenuItem();
            jMenuItemExit.setText("Exit");
        }
        return jMenuItemExit;
    }
}
