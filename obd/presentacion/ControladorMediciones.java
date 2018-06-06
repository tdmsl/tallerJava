package es.tdmsl.obd.presentacion;

import java.text.DecimalFormat;
import javax.swing.JPanel;
import es.tdmsl.obd.dominio.ControladorDominioConexion;

public class ControladorMediciones implements Runnable{
    VistaMediciones VMed;
    ControladorDominioConexion contDom;
    String pids_disponibles;
    DecimalFormat df;
    boolean lectura=false;
    boolean pararHilo=false;
    boolean inicializacion=false;
    boolean indica_hilo_destruido;
    boolean conectado=false;
    Thread hilo;
    public ControladorMediciones(ControladorDominioConexion contDom){
        VMed = new VistaMediciones();
        this.contDom = contDom;
        df = new DecimalFormat("0.0");//Especifica los decimales mostrados en las TextField.
        eventos();
    }

    public void eventos(){
        VMed.getJButtonLeer().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(lectura==false){
                    if(conectado==true){
                        if(inicializacion==true){

                            hilo = new Thread(ControladorMediciones.this);
                            inicializacion=false;
                            indica_hilo_destruido=false;
                        }
                        contDom.borrar_pids_establecidos();
                        contDom.enviar("0100");
                        contDom.establece_pids();
                        contDom.enviar("0120");
                        contDom.establece_pids();
                        contDom.enviar("0140");
                        contDom.establece_pids();
                        pids_disponibles=contDom.cojer_pids();
                        if(pids_disponibles.codePointAt(0)==78){// si posicion 1 = N
                            lectura=false;
                        }else{
                            pararHilo=false;
                            lectura=true;
                            hilo.start();
                        }
                    }else{
                        contDom.enviar("0100");
                    }
                }
            }
        });
        VMed.getJButtonDetener().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(conectado==true){
                    pararHilo=true;
                    lectura=false;
                    inicializacion=true;
                }else{
                    contDom.enviar("0100");
                }
            }
        });
    }
    public JPanel devuelve_panel(){

        return this.VMed;
    }
    public void run(){
        System.out.println("pids_disponibles "+pids_disponibles);
        while(true){
            if(pids_disponibles.codePointAt(4)==49){// = 1
                contDom.enviar("0104");//carga del motor calculada
                System.out.println("carga del motor calculada = "+contDom.devuelvePid().replaceAll("[\n\r]","")+"\n");

                //int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int valorDec=Integer.parseInt(contDom.devuelvePid().replaceAll("[\n\r]","").replace(" ",""), 16);
                VMed.getJTextField04().setText(df.format((valorDec*100)/255)+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(5)==49){// = 1 OK
                contDom.enviar("0105");//Temperatura de anticongelante
                //System.out.println("contDom.devuelvePid() "+contDom.devuelvePid().replaceAll("[\n\r]",""));
                System.out.println("Temperatura de anticongelante "+contDom.devuelvePid().substring(20,22));
                //int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(20,22), 16);
                VMed.getJTextField05().setText(Integer.toString(valorDec-40)+"ºC");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(6)==49){
                contDom.enviar("0106");//Short term fuel trim—Bank 1
                //int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                String v = contDom.devuelvePid().replaceAll("[\n\r]","");
                v = v.replace(" ","");
                System.out.println("Short term fuel trim—Bank 1"+v);
                int valorDec=Integer.parseInt(v, 16);
                VMed.getJTextField06().setText(df.format(((valorDec-
                        128)*100)/128)+"%");
                System.out.println();
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(7)==49){
                contDom.enviar("0107");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField07().setText(df.format(((valorDec-
                        128)*100)/128)+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(8)==49){
                contDom.enviar("0108");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField08().setText(df.format(((valorDec-
                        128)*100)/128)+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(9)==49){
                contDom.enviar("0109");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField09().setText(df.format(((valorDec-
                        128)*100)/128)+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(10)==49){
                contDom.enviar("010A");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField0A().setText(Integer.toString(valorDec*3)+"kPa");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(10)==49){
                contDom.enviar("010B");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField0B().setText(Integer.toString(valorDec)+"kPa");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;

                }
            }
            if(pids_disponibles.codePointAt(11)==49){// = 1
                contDom.enviar("010C");
                System.out.println("subString "+contDom.devuelvePid().substring(4,6));
                int a=Integer.parseInt(contDom.devuelvePid().substring(1,3), 16);
                int b=Integer.parseInt(contDom.devuelvePid().substring(4,6), 16);
                VMed.getJTextField0C().setText(Integer.toString(((a*256)+b)/4)+"rpm");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(12)==49){//kmh
                contDom.enviar("010D");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                VMed.getJTextField0D().setText(Integer.toString(valorDec)+"km/h");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(13)==49){
                contDom.enviar("010E");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField0E().setText(df.format((valorDec/2)-64)+"º");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(14)==49){//Temperatura del aire del colector de admisión
                contDom.enviar("010F");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                VMed.getJTextField0F().setText(Integer.toString(valorDec-40)+"ºC");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(15)==49){//Velocidad del flujo del aire MAF
                contDom.enviar("0110");
                int a=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                int b=Integer.parseInt(contDom.devuelvePid().substring(4,5), 16);
                VMed.getJTextField10().setText(df.format(((a*256)+b)/100)+"g/s");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(16)==49){
                contDom.enviar("0111");
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                VMed.getJTextField11().setText(df.format((valorDec*100)/255)+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(17)==49){
                contDom.enviar("0112");
                StringBuffer trama_binaria=new StringBuffer();
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                String pids_binario=Integer.toString(valorDec, 2);

                for(int f=0;f<(8-pids_binario.length());f++){//introduce un 0 al principio
//de las tramas de menos de 8 bits
                    trama_binaria.append("0");
                }
                trama_binaria.append(pids_binario);
                if(trama_binaria.codePointAt(7)==49){
                    VMed.getJTextField12().setText("Upstream of catalytic converter");
                }else if(trama_binaria.codePointAt(6)==49){
                    VMed.getJTextField12().setText("Downstream of catalytic converter");
                }else if(trama_binaria.codePointAt(5)==49){
                    VMed.getJTextField12().setText("From the outside atmosphere or off");
                }else{
                    VMed.getJTextField12().setText("OK");
                }
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(19)==49){//Sensor de oxígeno 1 A: Voltaje B: Ajuste de combustible a corto plazo
                contDom.enviar("0114");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(4,5), 16);
                VMed.getJTextField14().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(20)==49){
                contDom.enviar("0115");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField15().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(21)==49){
                contDom.enviar("0116");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField16().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(22)==49){
                contDom.enviar("0117");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField17().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;

                }
            }
            if(pids_disponibles.codePointAt(23)==49){
                contDom.enviar("0118");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField18().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(24)==49){
                contDom.enviar("0119");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField19().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(25)==49){
                contDom.enviar("011A");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField1A().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(26)==49){
                contDom.enviar("011B");
                int voltios=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                int porcentaje=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                VMed.getJTextField1B().setText(df.format(voltios*(0.005))+"V "+df.format((porcentaje-128)*(100/128))+"%");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(27)==49){
                contDom.enviar("011C");
                String valorHex=contDom.devuelvePid().substring(7,9);
                if(valorHex.compareTo("01")==0){
                    VMed.getJTextField1C().setText("OBD-II as defined by the CARB");
                }else if(valorHex.compareTo("02")==0){
                    VMed.getJTextField1C().setText("OBD as defined by the EPA");
                }else if(valorHex.compareTo("03")==0){
                    VMed.getJTextField1C().setText("OBD ''and'' OBD-II");
                }else if(valorHex.compareTo("04")==0){
                    VMed.getJTextField1C().setText("OBD-I");
                }else if(valorHex.compareTo("05")==0){
                    VMed.getJTextField1C().setText("Not meant to comply with any OBD standard");
                }else if(valorHex.compareTo("06")==0){
                    VMed.getJTextField1C().setText("EOBD");

                }else{
                    VMed.getJTextField1C().setText("Unknown");
                }
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(29)==49){
                contDom.enviar("011E");
                StringBuffer trama_binaria=new StringBuffer();
                int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                String pids_binario=Integer.toString(valorDec, 2);
                for(int f=0;f<(8-pids_binario.length());f++){//introduce un 0 al principio
//de las tramas de menos de 8 bits
                    trama_binaria.append("0");
                }
                trama_binaria.append(pids_binario);
                if(trama_binaria.codePointAt(7)==49){
                    VMed.getJTextField1E().setText("Active");
                }else{
                    VMed.getJTextField1E().setText("No active");
                }
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(30)==49){//Tiempo desde que se puso en marcha el motor
                contDom.enviar("011F");
                int a=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                int b=Integer.parseInt(contDom.devuelvePid().substring(4,5), 16);
                VMed.getJTextField1F().setText(df.format((a*256)+b)+"segs");
                if(pararHilo==true){
                    indica_hilo_destruido=true;
                    break;
                }
            }
            if(pids_disponibles.codePointAt(31)==49){//Distancia recorrida con la luz indicadora de falla (Malfunction Indicator Lamp, MIL) encendida
                if(pids_disponibles.codePointAt(32)==49){
                    contDom.enviar("0121");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(4,5), 16);
                    VMed.getJTextField21().setText(Integer.toString((a*256)+b)+"km");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(33)==49){
                    contDom.enviar("0122");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField22().setText(df.format(((a*256)+b)*(0.079))+"kPa");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(34)==49){
                    contDom.enviar("0123");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);

                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField23().setText(Integer.toString(((a*256)+b)*10)+"kPa");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(35)==49){
                    contDom.enviar("0124");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField24().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(36)==49){
                    contDom.enviar("0125");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField25().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(37)==49){
                    contDom.enviar("0126");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField26().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(38)==49){
                    contDom.enviar("0127");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField27().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(29)==49){
                    contDom.enviar("0128");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField28().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(40)==49){
                    contDom.enviar("0129");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField29().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){

                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(41)==49){
                    contDom.enviar("012A");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField2A().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(42)==49){
                    contDom.enviar("012B");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField2B().setText(df.format(((c*256)+d)*(0.000122))+"V");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(43)==49){
                    contDom.enviar("012C");
                    int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    VMed.getJTextField2C().setText(df.format((valorDec*100)/255)+"%");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(44)==49){
                    contDom.enviar("012D");
                    int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    VMed.getJTextField2D().setText(df.format((valorDec*(0.78125))-100)+"%");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(45)==49){
                    contDom.enviar("012E");
                    int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    VMed.getJTextField2E().setText(df.format((valorDec*100)/255)+"%");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(46)==49){//Nivel de entrada del tanque de combustible
                    contDom.enviar("012F");
                    int valorDec=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                    VMed.getJTextField2F().setText(df.format((valorDec*100)/255)+"%");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }

                if(pids_disponibles.codePointAt(48)==49){
                    contDom.enviar("0131");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField31().setText(Integer.toString((a*256)+b)+"km");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(49)==49){
                    contDom.enviar("0132");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField32().setText(df.format((((a*256)+b)/4)-(8.192))+"Pa");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(50)==49){//Presión barométrica absoluta
                    contDom.enviar("0133");
                    int valorDec=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                    VMed.getJTextField33().setText(Integer.toString(valorDec)+"kPa");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(51)==49){
                    contDom.enviar("0134");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField34().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(52)==49){
                    contDom.enviar("0135");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField35().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(53)==49){
                    contDom.enviar("0136");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField36().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(54)==49){
                    contDom.enviar("0137");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);

                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField37().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(55)==49){
                    contDom.enviar("0138");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField38().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(56)==49){
                    contDom.enviar("0139");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField39().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(57)==49){
                    contDom.enviar("013A");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField3A().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(58)==49){
                    contDom.enviar("013B");
                    int c=Integer.parseInt(contDom.devuelvePid().substring(13,15), 16);
                    int d=Integer.parseInt(contDom.devuelvePid().substring(16,18), 16);
                    VMed.getJTextField3B().setText(df.format(((c*256)+d)*(0.00390625)-128)+"mA");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(59)==49){
                    contDom.enviar("013C");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField3C().setText(df.format((((a*256)+b)/10)-40)+"ºC");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(60)==49){
                    contDom.enviar("013D");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField3D().setText(df.format((((a*256)+b)/10)-40)+"ºC");
                    if(pararHilo==true){

                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(61)==49){
                    contDom.enviar("013E");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField3E().setText(df.format((((a*256)+b)/10)-40)+"ºC");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(62)==49){
                    contDom.enviar("013F");
                    int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                    int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                    VMed.getJTextField3F().setText(df.format((((a*256)+b)/10)-40)+"ºC");
                    if(pararHilo==true){
                        indica_hilo_destruido=true;
                        break;
                    }
                }
                if(pids_disponibles.codePointAt(63)==49){//Voltaje del módulo de control
                    if(pids_disponibles.codePointAt(65)==49){
                        contDom.enviar("0142");
                        int a=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                        int b=Integer.parseInt(contDom.devuelvePid().substring(4,5), 16);
                        VMed.getJTextField42().setText(df.format(((a*256)+b)/1000)+"V");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(66)==49){
                        contDom.enviar("0143");
                        int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                        VMed.getJTextField43().setText(df.format(((a*256)+b)*(100/255))+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(67)==49){
                        contDom.enviar("0144");
                        int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                        VMed.getJTextField44().setText(df.format(((a*256)+b)*(0.0000305)));
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(68)==49){
                        contDom.enviar("0145");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField45().setText(df.format((valorDec*100)/255)+"%");

                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(69)==49){//Temperatura del aire ambiental
                        contDom.enviar("0146");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(1,2), 16);
                        VMed.getJTextField46().setText(Integer.toString(valorDec-40)+"ºC");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(70)==49){
                        contDom.enviar("0147");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9),
                                16);
                        VMed.getJTextField47().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(71)==49){
                        contDom.enviar("0148");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField48().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(72)==49){
                        contDom.enviar("0149");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField49().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(73)==49){
                        contDom.enviar("014A");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField4A().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(74)==49){

                        contDom.enviar("014B");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField4B().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(75)==49){
                        contDom.enviar("014C");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField4C().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(76)==49){
                        contDom.enviar("014D");
                        int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                        VMed.getJTextField4D().setText(Integer.toString((a*256)+b)+"min");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(77)==49){
                        contDom.enviar("014E");
                        int a=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        int b=Integer.parseInt(contDom.devuelvePid().substring(10,12), 16);
                        VMed.getJTextField4E().setText(Integer.toString((a*256)+b)+"min");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(80)==49){
                        contDom.enviar("0151");
                        if(contDom.devuelvePid().substring(7,9).compareTo("01")==0){
                            VMed.getJTextField51().setText("Gasoline");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("02")==0){
                            VMed.getJTextField51().setText("Methanol");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("03")==0){
                            VMed.getJTextField51().setText("Ethanol");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("04")==0){
                            VMed.getJTextField51().setText("Diesel");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("05")==0){
                            VMed.getJTextField51().setText("LPG");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("06")==0){
                            VMed.getJTextField51().setText("CNG");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("07")==0){
                            VMed.getJTextField51().setText("Propane");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("08")==0){
                            VMed.getJTextField51().setText("Electric");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("09")==0){
                            VMed.getJTextField51().setText("Bifuel running Gasoline");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0A")==0){
                            VMed.getJTextField51().setText("Bifuel running Methanol");

                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0B")==0){
                            VMed.getJTextField51().setText("Bifuel running Ethanol");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0C")==0){
                            VMed.getJTextField51().setText("Bifuel running LPG");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0D")==0){
                            VMed.getJTextField51().setText("Bifuel running CNG");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0E")==0){
                            VMed.getJTextField51().setText("Bifuel running Propane");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("0F")==0){
                            VMed.getJTextField51().setText("Bifuel running Electricity");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("10")==0){
                            VMed.getJTextField51().setText("Bifuel mixed gas/electric");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("11")==0){
                            VMed.getJTextField51().setText("Hybrid gasoline");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("12")==0){
                            VMed.getJTextField51().setText("Hybrid Ethanol");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("13")==0){
                            VMed.getJTextField51().setText("Hybrid Diesel");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("14")==0){
                            VMed.getJTextField51().setText("Hybrid Electric");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("15")==0){
                            VMed.getJTextField51().setText("Hybrid Mixed fuel");
                        }else if(contDom.devuelvePid().substring(7,9).compareTo("16")==0){
                            VMed.getJTextField51().setText("Hybrid Regenerative");
                        }
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                    if(pids_disponibles.codePointAt(81)==49){
                        contDom.enviar("0152");
                        int valorDec=Integer.parseInt(contDom.devuelvePid().substring(7,9), 16);
                        VMed.getJTextField52().setText(df.format((valorDec*100)/255)+"%");
                        if(pararHilo==true){
                            indica_hilo_destruido=true;
                            break;
                        }
                    }
                }
            }
        }
    }
}
