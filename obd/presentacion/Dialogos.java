package es.tdmsl.obd.presentacion;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * Created by Manu on 28/12/2016.
 */
public class  Dialogos {
    private static JOptionPane option = new JOptionPane("",
            INFORMATION_MESSAGE);
    private static JDialog dialogo = null;

    public static void visualizaDialogo(Component padre, String texto,
                                        String titulo, final long timeout)
    {
        option.setMessage(texto);
        if ( null == dialogo )
        {
            dialogo = option.createDialog(padre, titulo);
        }
        else
        {
            dialogo.setTitle(titulo);
        }

        Thread hilo = new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(timeout);
                    if ( dialogo.isVisible() )
                    {
                        dialogo.setVisible(false);
                    }
                }
                catch ( InterruptedException e )
                {
                    e.printStackTrace();
                }
            }
        };
        hilo.start();

        dialogo.setVisible(true);
    }

}
