  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.beans;


import es.tdmsl.app.dao.PersonalizacionDAO;
import es.tdmsl.app.utilidades.Util;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class ManoObra {
//    public Calendar fechaActual = Calendar.getInstance();
//    public Date date= new Date(); 
//    public SimpleDateFormat dfUSER = new SimpleDateFormat("dd-MM-yyyy");
//    public SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd");
    private int id;
    private int idt;
    private int idr;
    private String concepto;
    private double horasTrabajo;
    private int valorHora;
    private double total;
    private int operario;
    private Date fecha;
    
    

    public ManoObra() throws SQLException, ClassNotFoundException {
        this(0,0,0,"",0,0,0,0,new Date());
        PersonalizacionDAO personalizacionDAO = new PersonalizacionDAO();
        Personalizacion personalizacion = personalizacionDAO.cargarDatos(1);
        this.valorHora = personalizacion.getValorMO();
        //System.out.println("valor mano de obra = "+personalizacion.getValorMO());
    }

    public ManoObra(int id,int idt, int idr, String concepto, double horasTrabajo, int valorHora, double total, int operario, Date fecha) {
        //this.id = id;
        this.idt = 0;
        this.idr = idr;
        this.concepto = concepto;
        this.horasTrabajo = horasTrabajo;
        this.valorHora = valorHora;
        this.total = total;
        this.operario = operario;
        this.fecha = fecha;
    }

   

   
   

    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        v.add(this.id);
        v.add(this.idt);
        v.add(this.idr);
        v.add(this.concepto);
        v.add(this.horasTrabajo);
        v.add(this.valorHora);
        v.add(this.total);
        v.add(this.operario);
        v.add(Util.dateToString(this.fecha));
        
        return v;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(double horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public double getTotal() {
         return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getOperario() {
        return operario;
    }

    public void setOperario(int operario) {
        this.operario = operario;
    }

    @Override
    public String toString() {
        return "ManoObra{" + "id=" + id + ", idr=" + idr + ", concepto=" + concepto + ", horasTrabajo=" + horasTrabajo + ", valorHora=" + valorHora + ", total=" + total + ", operario=" + operario + ", fecha=" + fecha + '}';
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }
  
    
}
