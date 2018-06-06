/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Material;
import es.tdmsl.app.beans.Reparacion;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IMaterialDAO {

    public void grabar(Material material)throws SQLException;

    public void modificar(Material material)throws SQLException;

    public void modificarMateriales(Reparacion reparacion) throws SQLException, ClassNotFoundException;

    public void borrarLinea(int id)throws SQLException;

    public void borrarReparacion(int idr)throws SQLException;

    public Vector<Vector<Object>> listar()throws SQLException;

    public Vector<Vector<Object>> listar(int idr)throws SQLException;

    public int getUltimoIdt() throws SQLException;
   
}
