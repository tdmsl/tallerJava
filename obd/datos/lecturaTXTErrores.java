package es.tdmsl.obd.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class lecturaTXTErrores {
    FileReader fr;
    BufferedReader bf;
    int longitudTrama;
    int numeroErrores;
    StringBuffer valorHex;
    public lecturaTXTErrores(){}

    public String descifraTramaHex(String error){
        //System.out.println(" descifraTramaHex("+error+") ");
        //strDatos="\n41 01 84 4B A5 F1 0A\r";
        //error="1844BA5F10A";
        String estadoErrores;
        int valorDec;
        //System.out.println("error "+error);
        System.out.println("error.substring(1, 3)"+error.substring(6, 8));
        valorDec=Integer.parseInt(error.substring(6, 8), 16);
        //System.out.println(valorDec);
        if(valorDec<128){
            estadoErrores= "La luz del motor de comprobación (MIL) está apagada\n y la ECU no tiene ningún código de error almacenado";
        }else{
            numeroErrores=valorDec-128;
            estadoErrores= "La luz del motor de verificación (MIL) está encendida\n y la ECU "+numeroErrores+" errores almacenados";
        }
        return estadoErrores;
    }
    public String descifraTramaHexDescripcionErrores(String error){
        numeroErrores=6;//tempotal
        //error= "43 06 01 00 02 00 03 00 43 00 82 00 C1 00 00 00 00 00 00 00 43 01 01 01 ";
        //System.out.println("descifraTramaHexDescripcionErrores "+error);
        valorHex = new StringBuffer();
        StringBuffer codigoError=new StringBuffer();
        int incremento=4;
        System.out.println(error);
        for(int s=0;s<numeroErrores;s++){
            System.out.println(incremento+"----"+error.codePointAt(incremento));
            boolean codigo_encontrado=false;
            if(error.codePointAt(incremento)==48){//if = 0
                valorHex.append("P0");
            }else if(error.codePointAt(incremento)==49){//if =1
                valorHex.append("P1");
            }else if(error.codePointAt(incremento)==50){//if =2
                valorHex.append("P2");
            }else if(error.codePointAt(incremento)==51){//if =3
                valorHex.append("P3");
            }else if(error.codePointAt(incremento)==52){//if =4
                valorHex.append("C0");
            }else if(error.codePointAt(incremento)==53){//if =5
                valorHex.append("C1");
            }else if(error.codePointAt(incremento)==54){//if =6
                valorHex.append("C2");
            }else if(error.codePointAt(incremento)==55){//if =7
                valorHex.append("C3");
            }else if(error.codePointAt(incremento)==56){//if =8
                valorHex.append("B0");
            }else if(error.codePointAt(incremento)==57){//if =9
                valorHex.append("B1");
            }else if(error.codePointAt(incremento)==65){//if =A
                valorHex.append("B2");
            }else if(error.codePointAt(incremento)==66){//if =B
                valorHex.append("B3");
            }else if(error.codePointAt(incremento)==67){//if =C
                valorHex.append("U0");
            }else if(error.codePointAt(incremento)==68){//if =D
                valorHex.append("U1");
            }else if(error.codePointAt(incremento)==69){//if =E
                valorHex.append("U2");
            }else if(error.codePointAt(incremento)==70){//if =F
                valorHex.append("U3");
            }
            for(int p=incremento+1;p<(incremento+5);p++){//Introduce el resto denumeros del error
                if(error.codePointAt(p)!=32){// space
                    valorHex.append(error.charAt(p));
                }

            }
            incremento=incremento+6;//Coloca la posicion en la trama para el siguiente//error

            /*if(error.codePointAt(incremento+3)==10){//Si detecta salto de linea se
                                                    // prepara la posicion para el siguiente error en la siguiente linea
                incremento=incremento+7;
            }*/
            String codError=valorHex.toString();
            String sCadena;
            //try {
                //fr = new FileReader("CodigosErrores.txt");
                //bf = new BufferedReader(fr);
                /*while ((sCadena = bf.readLine())!=null) {
                    if(codError.compareTo(sCadena.substring(0, 5))==0){//se compara el error leido con cada linea
                                                                       // del fichero, si lo encuentra se sale del bucle
                        codigoError.append(sCadena+"\n\n");
                        codigo_encontrado=true;
                        break;
                    }
                }*/
            codigoError.append(codError+"\n\n");
                /*if(codigo_encontrado==false){
                    codigoError.append("Unknown "+codError+" error\n\n");
                }*/
            //} catch (IOException e) {
            //    codigoError.append("Database error codes inaccessible");
            //}
            longitudTrama=valorHex.length();//Se deja vacio el buffer que contiene el
//código procedente de la ECU
            valorHex.delete(0, longitudTrama);
        }
        return codigoError.toString();
    }
}
