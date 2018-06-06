/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.utilidades;

import es.tdmsl.app.ventanas.VentanaPrincipal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Manu
 */
public class Reloj extends JLabel implements Runnable {
String hora,minutos,segundos,ampm;
Calendar calendario;
Thread h1;
//JLabel reloj;

public void Reloj(){
   
h1 = new Thread(this);
h1.start();
}

    
    @Override
    public void run() {
         Thread ct = Thread.currentThread();
 while(ct == h1) {   
  calcula();
 //System.out.println("estoy en reloj"); 
 // setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
  VentanaPrincipal.reloj.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
  try {
   Thread.sleep(1000);
  }catch(InterruptedException e) {}
 }
    }
public void calcula () {        

Calendar calendario = new GregorianCalendar();
Date fechaHoraActual = new Date();
calendario.setTime(fechaHoraActual);
ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
if(ampm.equals("PM")){
int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
 hora = h>9?""+h:"0"+h;
}else{
hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
}
minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
}
    
        
    }
