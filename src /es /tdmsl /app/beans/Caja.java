/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import es.tdmsl.app.utilidades.Util;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Caja {
    private int id;
    private Date fecha;
    private double  ingreso;
    private double  salida;
    private String tipo;
    private String concepto;

    public Caja() {
        this(0,new Date(),0,0,"","");
    }

    public Caja(int id, Date fecha, double ingreso, double salida, String tipo, String concepto) {
        this.id = id;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.salida = salida;
        this.tipo = tipo;
        this.concepto = concepto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getSalida() {
        return salida;
    }

    public void setSalida(double salida) {
        this.salida = salida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public String toString() {
        return "Caja{" + "id=" + id + ", fecha=" + fecha + ", ingreso=" + ingreso + ", salida=" + salida + ", tipo=" + tipo + ", concepto=" + concepto + '}';
    }

    public Vector<Object> getVector() {
         Vector<Object> v = new Vector<Object>();
        v.add(this.id);
        v.add(Util.dateToString(this.fecha));
        v.add(this.ingreso);
        v.add(this.salida);
        v.add(this.tipo);
        v.add(this.concepto);
        return v;
    }

    
   
    
    
}
