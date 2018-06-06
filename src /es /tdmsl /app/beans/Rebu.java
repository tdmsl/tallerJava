package es.tdmsl.app.beans;

import es.tdmsl.app.utilidades.Util;

import java.util.Date;
import java.util.Vector;

public class Rebu {
    private int idr;
    private int NFactura;
    private String modelo;
    private Date fechaCompra;
    private  double valorCompra;
    private Date fechaVenta;
    private double valorVenta;

    public Rebu() {
        this(0,0,"",null,0,null,0);
    }

    public Rebu( int idr,int NFactura,String modelo,Date fechaCompra,double valorCompra,Date fechaVenta ,double valorVenta) {
        this.idr = idr;
        this.NFactura=NFactura;
        this.modelo = modelo;
        this.fechaCompra = fechaCompra ;
        this.valorCompra = valorCompra;
        this.fechaVenta = fechaVenta;
        this.valorVenta = valorVenta;

    }


    public int getNFactura() {
        return NFactura;
    }

    public void setNFactura(int NFactura) {
        this.NFactura = NFactura;
    }

    public int getIdr() {
        return idr;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Rebu{" +
                "idr=" + idr +
                ", NFactura=" + NFactura +
                ", modelo='" + modelo + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", valorCompra=" + valorCompra +
                ", fechaVenta=" + fechaVenta +
                ", valorVenta=" + valorVenta +
                '}';
    }

    public Vector<Object> getVector() {
        Vector<Object> v = new Vector<Object>();
        v.add(this.idr);
        v.add(this.NFactura);
        v.add(this.modelo);
        v.add(Util.dateToString(this.fechaCompra));
        v.add(this.valorCompra);
        v.add(Util.dateToString(this.fechaVenta));
        v.add(this.valorVenta);


        return v;

    }
}
