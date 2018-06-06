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
public class Telefono {

    private int idt;
    private String nombre;
    private String direccion;
    private String telefono;
    private String movil;
    private String obseTelefonos;
    private boolean nueva;

    public Telefono() {
        this(0,"", "", "", "", "");
    }

    public Telefono(int idt,String nombre, String direccion, String telefono, String movil, String obseTelefonos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.movil = movil;
        this.obseTelefonos = obseTelefonos;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }

   
    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
       // v.add(this.idt);// lo ocultamos de la tabla
        v.add(this.nombre);
        v.add(this.direccion);
        v.add(this.telefono);
        v.add(this.movil);
        v.add(this.obseTelefonos);
      
        return v;
    }
    
    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getObseTelefonos() {
        return obseTelefonos;
    }

    public void setObseTelefonos(String obseTelefonos) {
        this.obseTelefonos = obseTelefonos;
    }

    @Override
    public String toString() {
        return "Telefono{" + "idt=" + idt + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", movil=" + movil + ", obseTelefonos=" + obseTelefonos + '}';
    }

    
}
