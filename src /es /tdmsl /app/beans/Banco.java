/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import java.util.Date;

/**
 *
 * @author Manu
 */
public class Banco {

    private int id;
    private Date fecha;
    private double ingreso;
    private double salida;
    private String tipo;
    private String concepto;
    private int user;

    public Banco() {
        this(0,new Date(),0,0,"","",0);
    }

    public Banco(int id, Date fecha, double ingreso, double salida, String tipo, String concepto, int user) {
        this.id = id;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.salida = salida;
        this.tipo = tipo;
        this.concepto = concepto;
        this.user = user;
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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Banco{" + "id=" + id + ", fecha=" + fecha + ", ingreso=" + ingreso + ", salida=" + salida + ", tipo=" + tipo + ", concepto=" + concepto + ", user=" + user + '}';
    }
}
