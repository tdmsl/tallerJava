/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Almacen {

    private String referencia;
    private String descripcion;
    private String marca;
    private double ptsVenta;
    private double ptsCosto;
    private int descuento;
    private double ptsVentaE;
    private boolean nueva;

    public Almacen() {
        this("", "", "", 0, 0, 0, 0);//Preguntar a Antonio la necesidad de esto
    }

    public Almacen(String referencia, String descripcion, String marca, int ptsVenta) {

        this.referencia = referencia;
        this.descripcion = descripcion;
        this.marca = marca;
        this.ptsVenta = ptsVenta;
    }

    public Almacen(String referencia, String descripcion, String marca, int ptsVenta, int ptsCosto, int descuento, int ptsVentaE) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.marca = marca;
        this.ptsVenta = ptsVenta;
        this.ptsCosto = ptsCosto;
        this.descuento = descuento;
        this.ptsVentaE = ptsVentaE;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }


    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.referencia);
        v.add(this.descripcion);
        v.add(this.marca);
        v.add(this.ptsVenta);
        v.add(this.ptsCosto);
        v.add(this.descuento);
        v.add(this.ptsVentaE);
        return v;
    }

    public Vector<Object> getVector(Vector<String> nombres) {
        Vector<Object> v = new Vector<Object>();
        
        if (nombres.contains("referencia")) {
            v.add(this.referencia);
        }
        if (nombres.contains("descripcion")) {
            v.add(this.descripcion);
        }
         if (nombres.contains("marca")) {
            v.add(this.marca);
        }
         if (nombres.contains("ptsVenta")) {
            v.add(this.ptsVenta);
        }
         if (nombres.contains("ptsCosto")) {
            v.add(this.ptsCosto);
        }
         if (nombres.contains("descuento")) {
            v.add(this.descuento);
        }
         if (nombres.contains("ptsVentaE")) {
            v.add(this.ptsVentaE);
        }
        
        return v;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPtsVenta() {
        return ptsVenta;
    }

    public void setPtsVenta(double ptsVenta) {
        this.ptsVenta = ptsVenta;
    }

    public double getPtsCosto() {
        return ptsCosto;
    }

    public void setPtsCosto(double ptsCosto) {
        this.ptsCosto = ptsCosto;
    }

    

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getPtsVentaE() {
        return ptsVentaE;
    }

    public void setPtsVentaE(double ptsVentaE) {
        this.ptsVentaE = ptsVentaE;
    }

   

   
     
    

    @Override
    public String toString() {
        return "Almacen{" + "referencia=" + referencia + ", descripcion=" + descripcion + ", marca=" + marca + ", ptsVenta=" + ptsVenta + ", ptsCosto=" + ptsCosto + ", descuento=" + descuento + ", ptsVentaE=" + ptsVentaE + '}';
    }

    
}
