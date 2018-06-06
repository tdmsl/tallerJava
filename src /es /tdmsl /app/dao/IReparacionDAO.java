/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Reparacion;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IReparacionDAO {

    public void grabar(Reparacion reparacion) throws SQLException, ClassNotFoundException;

    public void modificar(Reparacion reparacion) throws SQLException, ClassNotFoundException ;
    
    public void modificar(String antigua,String nueva) throws SQLException, ClassNotFoundException ;

    public void borrar(int idr) throws SQLException;

    public Reparacion cargarDatos(int idr) throws SQLException;
    
    public int getUltimoIDR() throws SQLException;
    
   
    
    public Vector<Vector<Object>> listar() throws SQLException;
    public Vector<Vector<Object>> listar(int idr) throws SQLException;
    public Vector<Vector<Object>> listarIDC(int cuentaCliente) throws SQLException;
    public Vector<Vector<Object>> listar(String matricula) throws SQLException;
    public Vector<Vector<Object>> listar(Date inicio, Date fin) throws SQLException;
    public Vector<Vector<Object>> listarFactura(int factura) throws SQLException;
    public Vector<Vector<Object>> listarCampoNombre(String campo,String nombre)throws SQLException;
    public double totaltrabajosExteriores(Date ini, Date fin) throws SQLException;
    public double totalMateriales(Date ini, Date fin) throws SQLException;
    public double totalManoObra(Date ini, Date fin) throws SQLException;
    public double sumaMat(int idr)throws SQLException;
    public double sumaText(int idr)throws SQLException;
    public double sumaMo(int idr)throws SQLException;
}
