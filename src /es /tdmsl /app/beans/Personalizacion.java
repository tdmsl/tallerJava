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
public class Personalizacion {
    int id;
    private String nombreEmpresa;
    private String cif;       
    private String direccion;
    private String codigoPostal;
    private String ciudad;       
    private String telefono;       
    private String eMail;
    private int iva;       
    private int valorMO;
    private int nFactura;
    private String notas; 
    private String logo;       
    private String regInd;
    private String regEsp;
    private String cuentaBanco;       
    private String textoOrden;       
    private String textoPresupuesto; 
    private String textoFactura;       
    private String rutaInformes;
    private String rutaImgFondo;
    private String rutaAgenda;
    private String rutaBanco;
    private String rutaExcel;
    private String rutaDrive;
    private String rutaCorreo;
    private String pasword;
    private String lookandfeel;
    
    public Personalizacion() {
        this(0,"","","","","","","",21,35,"","","","","","","","","","","","","","","","","");
    }
    
    

    public Personalizacion(int id,String nombreEmpresa, String cif, String direccion, String codigoPostal, String ciudad, String telefono, String eMail, int iva,int MO, String notas, String logo, String regInd, String regEsp, String cuentaBanco, String textoOrden, String textoPresupuesto, String textoFactura, String rutaInformes, String rutaImgFondo, String rutaAgenda, String rutaBanco, String rutaExcel,String rutaDrive,String rutaCorreo,String pasword,String lookandfeel) {
        this.nombreEmpresa = nombreEmpresa;
        this.cif = cif;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.eMail = eMail;
        this.iva = iva;
        this.valorMO = MO;
        this.notas = notas;
        this.logo = logo;
        this.regInd = regInd;
        this.regEsp = regEsp;
        this.cuentaBanco = cuentaBanco;
        this.textoOrden = textoOrden;
        this.textoPresupuesto = textoPresupuesto;
        this.textoFactura = textoFactura;
        this.rutaInformes = rutaInformes;
        this.rutaImgFondo = rutaImgFondo;
        this.rutaAgenda = rutaAgenda;
        this.rutaBanco = rutaBanco;
        this.rutaExcel = rutaExcel;
        this.rutaDrive = rutaDrive;
        this.rutaCorreo = rutaCorreo;
        this.pasword = pasword;
        this.lookandfeel = lookandfeel;
    }

    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.id);
        v.add(this.nombreEmpresa);
        v.add(this.cif);
        v.add(this.direccion);
        v.add(this.codigoPostal);
        v.add(this.ciudad);
        v.add(this.telefono);
        v.add(this.eMail);
        v.add(this.iva);
        v.add(this.valorMO);
        v.add(this.notas);
        v.add(this.logo);
        v.add(this.regInd);
        v.add(this.regEsp);
        v.add(this.cuentaBanco);
        v.add(this.textoOrden);
        v.add(this.textoPresupuesto);
        v.add(this.textoFactura);
        v.add(this.rutaInformes);
        v.add(this.rutaImgFondo);
        v.add(this.rutaAgenda);
        v.add(this.rutaBanco);
        v.add(this.rutaExcel);
        v.add(this.rutaDrive);
        v.add(this.rutaCorreo);
        v.add(this.pasword);
        v.add(this.lookandfeel);
        return v;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getValorMO() {
        return valorMO;
    }

    public void setValorMO(int valorMO) {
        this.valorMO = valorMO;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRegInd() {
        return regInd;
    }

    public void setRegInd(String regInd) {
        this.regInd = regInd;
    }

    public String getRegEsp() {
        return regEsp;
    }

    public void setRegEsp(String regEsp) {
        this.regEsp = regEsp;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getTextoOrden() {
        return textoOrden;
    }

    public void setTextoOrden(String textoOrden) {
        this.textoOrden = textoOrden;
    }

    public String getTextoPresupuesto() {
        return textoPresupuesto;
    }

    public void setTextoPresupuesto(String textoPresupuesto) {
        this.textoPresupuesto = textoPresupuesto;
    }

    public String getTextoFactura() {
        return textoFactura;
    }

    public void setTextoFactura(String textoFactura) {
        this.textoFactura = textoFactura;
    }

    public String getRutaInformes() {
        return rutaInformes;
    }

    public void setRutaInformes(String rutaInformes) {
        this.rutaInformes = rutaInformes;
    }

    public String getRutaImgFondo() {
        return rutaImgFondo;
    }

    public void setRutaImgFondo(String rutaImgFondo) {
        this.rutaImgFondo = rutaImgFondo;
    }

    public String getRutaAgenda() {
        return rutaAgenda;
    }

    public void setRutaAgenda(String rutaAgenda) {
        this.rutaAgenda = rutaAgenda;
    }

    public String getRutaBanco() {
        return rutaBanco;
    }

    public void setRutaBanco(String rutaBanco) {
        this.rutaBanco = rutaBanco;
    }

    public String getRutaExcel() {
        return rutaExcel;
    }

    public void setRutaExcel(String rutaExcel) {
        this.rutaExcel = rutaExcel;
    }

    public String getRutaDrive() {
        return rutaDrive;
    }

    public void setRutaDrive(String rutaDrive) {
        this.rutaDrive = rutaDrive;
    }

    public String getRutaCorreo() {
        return rutaCorreo;
    }

    public void setRutaCorreo(String rutaCorreo) {
        this.rutaCorreo = rutaCorreo;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public int getnFactura() {
        return nFactura;
    }

    public void setnFactura(int nFactura) {
        this.nFactura = nFactura;
    }

    public String getLookandfeel() {
        return lookandfeel;
    }

    public void setLookandfeel(String lookandfeel) {
        this.lookandfeel = lookandfeel;
    }

    @Override
    public String toString() {
        return "Personalizacion{" + "id=" + id + ", nombreEmpresa=" + nombreEmpresa + ", cif=" + cif + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + ", telefono=" + telefono + ", eMail=" + eMail + ", iva=" + iva + ", valorMO=" + valorMO + ", nFactura=" + nFactura + ", notas=" + notas + ", logo=" + logo + ", regInd=" + regInd + ", regEsp=" + regEsp + ", cuentaBanco=" + cuentaBanco + ", textoOrden=" + textoOrden + ", textoPresupuesto=" + textoPresupuesto + ", textoFactura=" + textoFactura + ", rutaInformes=" + rutaInformes + ", rutaImgFondo=" + rutaImgFondo + ", rutaAgenda=" + rutaAgenda + ", rutaBanco=" + rutaBanco + ", rutaExcel=" + rutaExcel + ", rutaDrive=" + rutaDrive + ", rutaCorreo=" + rutaCorreo + ", lookandfeel=" + lookandfeel + '}';
    }
    
    

    
    
    

    
    

    

    
    
    
    
           
}
