/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades;

/*
 * Fichero: JavaMail.java
 * Autor: Jeison Nisperuza
 * Fecha: 24/12/2010 18:14
 */
import java.awt.FlowLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import es.tdmsl.app.ventanas.VentanaPrincipal;

public class JavaMail {

    public static JPanel panelCopiaSeg;

    public void envioMail(String rutaCorreo,String pasword, String para, String asunto, String mensaje, String rutaAdjunto) {
        try {
            Properties props = new Properties();


// Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

// TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

// Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

// Nombre del usuario
            props.setProperty("mail.smtp.user", rutaCorreo);

// Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "false");

//Con esto estamos en disposición de obtener nuestra instancia de Session.
////////////////////////////////////////////////////////

////////////////////////////////////////////////
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
//Construir el mensaje para enviar con JavaMail
//Vamos ahora a construir un mensaje simple de texto. Para ellos instanciamos la clase MimeMessage y le ponemos varios datos.
//En el constructor debemos pasarle el objeto Session que obtuvimos anteriormente
            MimeMessage message = new MimeMessage(session);
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            BodyPart adjunto = new MimeBodyPart();
            System.out.println("RUTA FICHERO" + rutaAdjunto);
            adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
            adjunto.setFileName("Datos Taller");//opcional
            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
// Quien envia el correo
            message.setFrom(new InternetAddress(InetAddress.getLocalHost().getHostName()));

// A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("datostaller@gmail.com"));

// Elasunto del mensaje
            message.setSubject(asunto);
// El cuerpo y adjunto del mensaje
            message.setContent(multiParte);
// Enviamos
            Transport t = session.getTransport("smtp");
            t.connect(rutaCorreo,"programataller");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
//mostramos el resultado del proceso            
           
            panelCopiaSeg = new JPanel();
            JLabel label = new JLabel();

            panelCopiaSeg.setLayout(new FlowLayout());
//            panelCopiaSeg.setLocationRelativeTo(null);
            panelCopiaSeg.setVisible(true);

            panelCopiaSeg.setSize(600, 400);//ancho,alto
            VentanaPrincipal.notas.add(panelCopiaSeg);
            //panelCopiaSeg.toFront();
            
            panelCopiaSeg.add(label);
            label.setText("<html><center><font color = blue size = 5>Realizando envio mail copia seguridad</font></center><br><hr>"
                    + "Se ha enviado un mensaje a la direccion :&nbsp;&nbsp;<font color = blue>"
                    + para + "</font><br>"
                    + "con el asunto :&nbsp;&nbsp;<font color = blue>   "
                    + asunto + "</font><br>"
                    + "con el archivo :&nbsp;&nbsp;<font color = blue>   \""
                    + adjunto.getDataHandler().getName() + "\"</font><br>"
                    +"<br><br><br><br><br><br>"
                    + "</html>");

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
