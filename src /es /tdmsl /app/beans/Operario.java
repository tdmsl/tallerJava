/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import es.tdmsl.app.exceptions.OperarioException;
import es.tdmsl.app.utilidades.Util;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Operario {

    private int ido;
    private String nombre;
    private String direccion;
    private int codigoPostal;
    private String telefono;
    private Date fechaAlta;
    private Date fechaBaja;
    private int sueldoNeto;
    private int sueldoBruto;
    private String obseOperarios;
    private boolean nueva;

    public Operario() throws OperarioException {


        this(0, "", "", 0, "", new Date(),null, 0, 0, "");
        //throw new OperarioException("error en operarios");
//        try {
//            
//        } catch (OperarioException e) {
//        }
    }

    public Operario(int ido, String nombre, String direccion, int codigoPostal, String telefono, Date fechaAlta,Date fechaBaja, int sueldoNeto, int sueldoBruto, String obseOperarios) {
        this.ido = ido;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.sueldoNeto = sueldoNeto;
        this.sueldoBruto = sueldoBruto;
        this.obseOperarios = obseOperarios;

    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }


    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.ido);
        v.add(this.nombre);
        v.add(this.direccion);
        v.add(this.codigoPostal);
        v.add(this.telefono);
        v.add(Util.dateToString(this.fechaAlta));
        v.add(Util.dateToString(this.fechaBaja));
        v.add(this.sueldoNeto);
        v.add(this.sueldoBruto);
        v.add(this.obseOperarios);
        return v;
    }
    
    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    

    

    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   

   

    public int getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(int sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public int getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(int sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public String getObseOperarios() {
        return obseOperarios;
    }

    public void setObseOperarios(String obseOperarios) {
        this.obseOperarios = obseOperarios;
    }

    @Override
    public String toString() {
        String s;
        
        
        
        s = String.format("%02d", this.getIdo()) + " : " + this.getNombre();
        
        /*
          if (this.getIdo()< 10) {
             s = "0"+this.getIdo(); 
             // System.out.println("ido = "+ido);
            }
            else{
              s = ""+this.getIdo();
              //System.out.println("ido = "+ido);
            }
            s+= " : "+this.getNombre();*/
        return s;
    }

    

}
