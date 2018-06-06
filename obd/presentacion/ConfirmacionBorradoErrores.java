package es.tdmsl.obd.presentacion;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
public class ConfirmacionBorradoErrores extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel jLabelConfirmaErrores = null;
    private JButton jButtonOK = null;
    private JLabel jLabelConfirmarBorradoErrores = null;
    private JButton jButtonCancel = null;
    public ConfirmacionBorradoErrores() {
        super();
        initialize();
    }
    private void initialize() {
        this.setSize(330, 200);
        this.setLocation(500,200);
        this.setContentPane(getJContentPane());
        this.setTitle("Confirm erasing error codes");
    }
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jLabelConfirmarBorradoErrores = new JLabel();
            jLabelConfirmarBorradoErrores.setBounds(new Rectangle(10, 41, 206, 20));
            jLabelConfirmarBorradoErrores.setText("Do you really want to continue?");
            jLabelConfirmaErrores = new JLabel();
            jLabelConfirmaErrores.setBounds(new Rectangle(10, 20, 308, 20));
            jLabelConfirmaErrores.setText("You will erase all error codes stored in the ECU.");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelConfirmaErrores, null);
            jContentPane.add(getJButtonOK(), null);
            jContentPane.add(jLabelConfirmarBorradoErrores, null);
            jContentPane.add(getJButtonCancel(), null);
        }
        return jContentPane;
    }
    public JButton getJButtonOK() {
        if (jButtonOK == null) {
            jButtonOK = new JButton();
            jButtonOK.setBounds(new Rectangle(161, 135, 75, 20));
            jButtonOK.setText("OK");
        }
        return jButtonOK;
    }
    public JButton getJButtonCancel() {
        if (jButtonCancel == null) {
            jButtonCancel = new JButton();
            jButtonCancel.setBounds(new Rectangle(235, 135, 75, 20));
            jButtonCancel.setText("Cancel");
        }
        return jButtonCancel;
    }
}
