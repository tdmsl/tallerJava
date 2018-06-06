package es.tdmsl.app.threads;

import es.tdmsl.app.ventanas.Formularios.FormEstadisticas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Manu on 17/04/2017.
 */
public class contador extends Thread {

    @Override
    public void run() {
        FormEstadisticas.cronometroActivo=true;
                System.out.println("entrando en ThreadContador");
                JDialog jDialog=new JDialog();
                jDialog.setModal(false);
                jDialog.setAlwaysOnTop(true);
                jDialog.setTitle("Realizando consulta facturacion");
                //jDialog.setUndecorated(true);
                jDialog.getContentPane().setLayout(new BoxLayout(jDialog.getContentPane(),BoxLayout.Y_AXIS));
                //jDialog.setLocationRelativeTo(FormEstadisticas.grafico3);

                //jDialog.toFront();
                JLabel tiempo=new JLabel("00:00:0000");
                tiempo.setFont(new Font("Helvetica", Font.BOLD, 50 ));

                //  Image image1=new ImageIcon(getClass().getResource("../images/cursor.gif")).getImage();
                jDialog.add(tiempo);
                jDialog.pack();
                jDialog.setVisible(true);
        Integer minutos = 0 , segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min="", seg="", mil="";
        try {
            this.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cargaGrafico3 ThreadcargaGrafico3 =new cargaGrafico3();
        ThreadcargaGrafico3.start();
        /*try {
            ThreadcargaGrafico3.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ThreadcargaGrafico3.setPriority(Thread.MAX_PRIORITY);
        try
        {
            int x=400;//posicionamos el JDialog
            int y=600;//posicionamos el JDialog
            double movx=1;
            double movy=1;
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while( FormEstadisticas.cronometroActivo )
            {
                Thread.sleep( 100 );
                //Incrementamos 4 milesimas de segundo
                milesimas += 100;

                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                    }
                }

                x+=movx;
                y+=movy;
                if (x<200||x>900) {
                    movx = (movx)*(-1);//+ Math.random();
                    //y = 600;
                }
                if (y<450||y>600) {
                    //x = 200;
                    movy = (movy)*(-1);//+ Math.random();
                }
                jDialog.setLocation(x,y);
                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();

                if( milesimas < 10 ) mil = "00" + milesimas;
                else if( milesimas < 100 ) mil = "0" + milesimas;
                else mil = milesimas.toString();


                //Colocamos en la etiqueta la informacion
                        tiempo.setText( min + ":" + seg + ":" + mil );

            }
        }catch(Exception e){}
        //tiempo.setText( min + ":" + seg + ":" + mil );

    }
}
