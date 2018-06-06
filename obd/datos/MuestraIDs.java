package es.tdmsl.obd.datos;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JScrollPane;
public class MuestraIDs extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JList jListIDs = null;
    private JButton jButtonIDsAceptar = null;
    private JScrollPane jScrollPaneListaIDs = null;
    public MuestraIDs() {
        super();
        initialize();
    }
    private void initialize() {
        this.setSize(300, 200);
        this.setLocation(500, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("CAN Bus IDs");
    }
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJButtonIDsAceptar(), null);
            jContentPane.add(getJScrollPaneListaIDs(), null);
        }
        return jContentPane;
    }
    public JList getJListIDs() {
        if (jListIDs == null) {
            jListIDs = new JList();
        }
        return jListIDs;
    }
    public JButton getJButtonIDsAceptar() {
        if (jButtonIDsAceptar == null) {
            jButtonIDsAceptar = new JButton();
            jButtonIDsAceptar.setBounds(new Rectangle(200, 133, 82, 22));
            jButtonIDsAceptar.setText("OK");
        }
        return jButtonIDsAceptar;
    }

    private JScrollPane getJScrollPaneListaIDs() {
        if (jScrollPaneListaIDs == null) {
            jScrollPaneListaIDs = new JScrollPane();
            jScrollPaneListaIDs.setBounds(new Rectangle(10, 10, 273, 115));
            jScrollPaneListaIDs.setViewportView(getJListIDs());
        }
        return jScrollPaneListaIDs;
    }
}
