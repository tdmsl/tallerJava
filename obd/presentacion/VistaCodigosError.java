package es.tdmsl.obd.presentacion;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JScrollPane;
public class VistaCodigosError extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextArea jTextAreaErrores = null;
    private JButton jButtonErrores = null;
    private JScrollPane jScrollPaneErrores = null;
    private JButton jButtonBorrarErrores = null;
    public VistaCodigosError() {
        super();
        initialize();
    }
    private void initialize() {
        this.setSize(new Dimension(800, 677));
        this.setLayout(null);
        this.add(getJTextAreaErrores(), null);
        this.add(getJButtonErrores(), null);
        this.add(getJScrollPaneErrores(), null);
        this.add(getJButtonBorrarErrores(), null);
    }
    public JTextArea getJTextAreaErrores() {
        if (jTextAreaErrores == null) {
            jTextAreaErrores = new JTextArea();
            jTextAreaErrores.setBounds(new Rectangle(77, 88, 182, 155));
            jTextAreaErrores.setFont(new Font("Dialog", Font.BOLD, 19));
            jTextAreaErrores.setEditable(false);
        }
        return jTextAreaErrores;
    }
    public JButton getJButtonErrores() {
        if (jButtonErrores == null) {
            jButtonErrores = new JButton();
            jButtonErrores.setBounds(new Rectangle(7, 650, 177, 20));
            jButtonErrores.setText("Read error codes");
        }
        return jButtonErrores;
    }
    private JScrollPane getJScrollPaneErrores() {
        if (jScrollPaneErrores == null) {
            jScrollPaneErrores = new
                    JScrollPane(getJTextAreaErrores(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jScrollPaneErrores.setBounds(new Rectangle(7, 7, 784, 637));
        }
        return jScrollPaneErrores;
    }

    public JButton getJButtonBorrarErrores() {
        if (jButtonBorrarErrores == null) {
            jButtonBorrarErrores = new JButton();
            jButtonBorrarErrores.setBounds(new Rectangle(183, 650, 183, 20));
            jButtonBorrarErrores.setText("Clear error codes");
        }
        return jButtonBorrarErrores;
    }
}
