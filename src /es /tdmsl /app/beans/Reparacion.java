/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;

import es.tdmsl.app.dao.MatriculaDAO;
import es.tdmsl.app.utilidades.Util;
import java.sql.SQLException;
 import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class Reparacion {

    private int idr;
    private int cuentaCliente;
    private String matricula;
    private int km;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String nFactura;
    private int receptor;
    private String trabajosRealizar;
    private int cuentaCliente2;
    private String nSiniestro;
    private boolean nueva;
    private  Vector<Material> materiales;
    private  Vector<ManoObra> manosObra;
    private  Vector<TrabajoExt> trabajosExt;
    
    
    
   
    public Reparacion() {      
        this(0,0, "", 0, new Date(),null, null, 0, "",0,null,new Vector<Material>(), new Vector<ManoObra>(),new Vector<TrabajoExt>(), false);  
    }
    
    public Reparacion(int idr, int cuentaCliente, String matricula, int km, Date fechaEntrada, Date fechaSalida, String nFactura, int receptor, String trabajosRealizar, int cuentaCliente2, String nSiniestro, Vector<Material> materiales, Vector<ManoObra> manosObra, Vector<TrabajoExt> trabajosExt,boolean nueva) {
        this.idr = idr;
        this.cuentaCliente = cuentaCliente;
        this.matricula = matricula;
        this.km = km;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.nFactura = nFactura;
        this.receptor = receptor;
        this.trabajosRealizar = trabajosRealizar;
        this.cuentaCliente2 = cuentaCliente2;
        this.nSiniestro = nSiniestro;
        this.materiales = materiales;
        this.manosObra = manosObra;
        this.trabajosExt = trabajosExt;
        this.nueva = nueva;
    }

    public void añadeMaterial(Material m) {
        this.materiales.add(m);
    }

    public void añadeManoDeObra(ManoObra mo) {
        this.manosObra.add(mo);
    }

    public void añadeTrabajoExterior(TrabajoExt te) {
        this.trabajosExt.add(te);
    }
    
     public Matricula añadeMatricula(String matri) throws SQLException, ClassNotFoundException {
        MatriculaDAO matriculaDAO = new MatriculaDAO();  
        Matricula matricula = matriculaDAO.cargarDatos(matri);
        
        return matricula;
    }
    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.idr);
        v.add(this.cuentaCliente);
        v.add(this.matricula);
        v.add(this.km);
        v.add(Util.dateToString(this.fechaEntrada));
        v.add(Util.dateToString(this.fechaSalida));
        v.add(this.nFactura);
        v.add(this.receptor);
        v.add(this.trabajosRealizar);
        v.add(this.cuentaCliente2);
        v.add(this.nSiniestro);

        return v;
    }

    public boolean isNueva() {
        return nueva;
    }

    public void setNueva(boolean nueva) {
        this.nueva = nueva;
    }
    
    
    
    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public int getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(int cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

   
    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getnFactura() {
        return nFactura;
    }

    public void setnFactura(String nFactura) {
        this.nFactura = nFactura;
    }

    public int getReceptor() {
        return receptor;
    }

    public void setReceptor(int receptor) {
        this.receptor = receptor;
    }

    public String getTrabajosRealizar() {
        return trabajosRealizar;
    }

    public void setTrabajosRealizar(String trabajosRealizar) {
        this.trabajosRealizar = trabajosRealizar;
    }

     public Vector<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(Vector<Material> materiales) {
        this.materiales = materiales;
    }

    public Vector<ManoObra> getManosObra() {
        return manosObra;
    }

    public void setManosObra(Vector<ManoObra> manosObra) {
        this.manosObra = manosObra;
    }

    public Vector<TrabajoExt> getTrabajosExt() {
        return trabajosExt;
    }

    public void setTrabajosExt(Vector<TrabajoExt> trabajosExt) {
        this.trabajosExt = trabajosExt;
    }

    public int getCuentaCliente2() {
        return cuentaCliente2;
    }

    public void setCuentaCliente2(int cuentaCliente2) {
        this.cuentaCliente2 = cuentaCliente2;
    }

    public String getnSiniestro() {
        return nSiniestro;
    }

    public void setnSiniestro(String nSiniestro) {
        this.nSiniestro = nSiniestro;
    }

    @Override
    public String toString() {
        return "Reparacion{" + "idr=" + idr + ", cuentaCliente=" + cuentaCliente + ", matricula=" + matricula + ", km=" + km + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", nFactura=" + nFactura + ", receptor=" + receptor + ", trabajosRealizar=" + trabajosRealizar + ", materiales=" + materiales + ", manosObra=" + manosObra + ", trabajosExt=" + trabajosExt + ", cuentaCliente2=" + cuentaCliente2 + ", nSiniestro=" + nSiniestro + ", nueva=" + nueva + '}';
    }

    

    public boolean facturada() {
        if (this.fechaSalida != null) {
         return true ;  
        }
       return false ;
    }

    
    

    

 
    

    

    
}
