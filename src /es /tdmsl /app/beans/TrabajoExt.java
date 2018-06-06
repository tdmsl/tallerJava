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
public class TrabajoExt {
    private int id;
    private int idt;
    private int idr;
    private String descripcion;
    private int pvp;
    private int operario;
    

    public TrabajoExt() {
    this(0,0,0,"",0,0);
    }

    public TrabajoExt(int id,int idt,int idr, String descripcion, int pvp, int operario) {
        //this.id = id;
        this.idt = 0;
        this.idr = idr;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.operario = operario;
    }

//     public  Vector<String> getCabeceras(){
//         Class c = this.getClass();
//         Field []atributos;
//         atributos = c.getDeclaredFields();
//         int n = atributos.length;
//         Vector <String>cab;
//         
//         cab = new Vector<>();
//         for (int i = 0 ; i < n ; i++) {
//            cab.add(atributos[i].getName());
//        }
//         
//         return cab;              
//     }
    
    public Vector<Object> getVector(){
        Vector<Object> v = new Vector<Object>();
        
        v.add(this.id);
        v.add(this.idt);
        v.add(this.idr);
        v.add(this.descripcion);
        v.add(this.pvp);
        v.add(this.operario);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPvp() {
        return pvp;
    }

    public void setPvp(int pvp) {
        this.pvp = pvp;
    }

    public int getOperario() {
        return operario;
    }

    public void setOperario(int operario) {
        this.operario = operario;
    }

    @Override
    public String toString() {
        return "TrabajoExt{" + "id=" + id + ", idr=" + idr + ", descripcion=" + descripcion + ", pvp=" + pvp + ", operario=" + operario + '}';
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    
    
    
}
