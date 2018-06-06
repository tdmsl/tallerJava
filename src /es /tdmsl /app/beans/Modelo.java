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
public class Modelo {
  private int ID;
  private String marca;
  private String modelo;

    public Modelo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public Modelo() {
        this("","");
    }

  

    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
       // v.add(this.marca);
        v.add(this.modelo);
        
        return v;
    }
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

   

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Modelo{" + "ID=" + ID + ", marca=" + marca + ", modelo=" + modelo + '}';
    }
    
  
}
