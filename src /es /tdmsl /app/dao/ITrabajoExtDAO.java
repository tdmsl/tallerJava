/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Reparacion;
import es.tdmsl.app.beans.TrabajoExt;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface ITrabajoExtDAO {

    public void grabar(TrabajoExt trabajosExt)throws SQLException;

    public void modificar(TrabajoExt trabajosExt)throws SQLException;

    public void modificarTrabajosExt(Reparacion reparacion) throws SQLException, ClassNotFoundException ;

    public void borrarLinea(int id)throws SQLException;
    
    public void borrarReparacion(int idr)throws SQLException;

   
    
    public Vector<Vector<Object>> listar()throws SQLException;
    public Vector<Vector<Object>> listar(int idr)throws SQLException;
}
