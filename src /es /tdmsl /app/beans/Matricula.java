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
public class Matricula {

    private String matricula;
    private int idClientes;
    private String marca;
    //private Marca marca;
    private String modelo;
    //private Modelo modelo;
    private String bastidor;
    private String codigo;
    private String obseMatricula;
    private boolean nueva;
    public Matricula() {
        this("",0,"Nueva","Nuevo","","","");
    }

    public Matricula(String matricula, int idClientes,String marca, String modelo, String bastidor, String codigo, String obseMatricula) {
        this.nueva = true;
        this.matricula = matricula;
        this.idClientes = idClientes;
        this.marca = marca;
        this.modelo = modelo;
        this.bastidor = bastidor;
        this.codigo = codigo;
        this.obseMatricula = obseMatricula;
    }

    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        v.add(this.matricula);
        v.add(this.idClientes);
        v.add(this.marca);
        //v.add(this.modelo.getMarca().getMarca());
         v.add(this.modelo);
        //v.add(this.modelo.getModelo());
        v.add(this.bastidor);
       // v.add(this.codigo);
        v.add(this.obseMatricula);
        return v;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }
    
    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
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

    

    

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObseMaticula() {
        return obseMatricula;
    }

    public void setObseMaticula(String obseMaticula) {
        this.obseMatricula = obseMaticula;
    }

    @Override
    public String toString() {
        return "Matricula{" + "matricula=" + matricula + ", idClientes=" + idClientes + ", marca=" + marca + ", modelo=" + modelo + ", bastidor=" + bastidor + ", codigo=" + codigo + ", obseMaticula=" + obseMatricula + ", nueva=" + nueva + '}';
    }

    
}
