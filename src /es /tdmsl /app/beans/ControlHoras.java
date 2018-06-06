 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class ControlHoras {
  private int ido;
  private Date fecha;
  private Time entrada; 
  private Time salida;

    public ControlHoras() {
        this(0,new Date(),new Time(0),new Time(0));
    }

    public ControlHoras(int ido, Date fecha, Time entrada,Time salida) {
        this.ido = ido;
        this.fecha = fecha;
        this.entrada = entrada;
        this.salida = salida;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getEntrada() {
        return entrada;
    }

    public void setEntrada(Time entrada) {
        this.entrada = entrada;
    }

    public Time getSalida() {
        return salida;
    }

    public void setSalida(Time salida) {
        this.salida = salida;
    }

   

    
        
    public Vector<Object> getVector() {
         Vector<Object> v = new Vector<Object>();
        v.add(this.ido);
        v.add(this.fecha);
        v.add(this.entrada);
        v.add(this.salida);
       
        return v;
    }

    @Override
    public String toString() {
        return "ControlHoras{" + "ido=" + ido + ", fecha=" + fecha + ", entrada=" + entrada + ", salida=" + salida + '}';
    }
    
    
  
  
  
}
