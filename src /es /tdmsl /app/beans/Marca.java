 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Marca {

    private String marca;
    private int idmarcas;

    public Marca() {
        this("",0);
        
        
    }

    public Marca(String marca, int idmarcas) {
        this.marca = marca;
        this.idmarcas = idmarcas;
    }

   

    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        //v.add(idmarcas);
        v.add(this.marca);
        
        return v;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdmarcas() {
        return idmarcas;
    }

    public void setIdmarcas(int idmarcas) {
        this.idmarcas = idmarcas;
    }

    

    @Override
    public String toString() {
        return "Marca{" + "marca=" + marca + ", idmarcas=" + idmarcas + '}';
    }
    
    

    
}
