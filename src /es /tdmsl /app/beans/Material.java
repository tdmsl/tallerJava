/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class Material {

    private int id;
    private int idt;
    private int idr;
    private String referencia;
    private String descripcion;
    private double pvp;
    private double cantidad;
    private int descuento;
    private double total;
    
  //  private List<Material> Nrep;

    public Material() {
        this(0, 0, 0, "", "", 0, 0, 0, 0);
        
    }

    public Material(int id,int idt, int idr, String referencia, String descripcion, double cantidad, double pvp, int descuento, double total) {
        this.idt = 0;
        this.idr = idr;
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.pvp =  pvp;
        this.descuento = descuento;
        this.total = total;
    }

     


    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.id);
        v.add(this.idt);
        v.add(this.idr);
        v.add(this.referencia);
        v.add(this.descripcion);
        v.add(this.pvp);
        v.add(this.cantidad);
        v.add(this.descuento);
        v.add(this.total);
        return v;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }
    
    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", idt=" + idt + ", idr=" + idr + ", referencia=" + referencia + ", descripcion=" + descripcion + ", pvp=" + pvp + ", cantidad=" + cantidad + ", descuento=" + descuento + ", total=" + total + '}';
    }
    
    
}
