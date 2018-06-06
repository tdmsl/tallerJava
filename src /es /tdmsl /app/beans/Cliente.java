/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import es.tdmsl.app.exceptions.ClienteException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Cliente {

    private int idc;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String codPostal;
    private String telefono;
    private String observaciones;
    private String nif;
    private boolean nueva;

    public Cliente() throws ClienteException {
        this(0, "", "", "", "", "", "", "", "");
    }

    public Cliente(int idc, String nombre, String direccion, String ciudad, String provincia, String codPostal, String telefono, String observaciones, String nif) throws ClienteException {
        this.idc = idc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codPostal = codPostal;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.nif = nif;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }

    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        v.add(this.idc);
        v.add(this.nombre);
        v.add(this.direccion);
        v.add(this.ciudad);
        v.add(this.provincia);
        v.add(this.codPostal);
        v.add(this.telefono);
        v.add(this.observaciones);
        v.add(this.nif);
        return v;
    }
    
    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setNif(String nif) throws ClienteException {
//        if (nif.length() != 8) {
//             throw new ClienteException("El nif debe tener 8 posiciones");
//        }
        this.nif = nif;
    }

    public int getIdc() {
        return idc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getNif() {
        return nif;
    }

    
 
    @Override
    public String toString() {
        return "Cliente{" + "idc=" + idc + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + ", provincia=" + provincia + ", codPostal=" + codPostal + ", telefono=" + telefono + ", observaciones=" + observaciones + ", nif=" + nif + '}';
    }
}
