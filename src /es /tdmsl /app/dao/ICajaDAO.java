/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Caja;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface ICajaDAO {

    public void grabar(Caja caja) throws SQLException;

    public void modificar(Caja caja) throws SQLException;

    public void borrar(int id) throws SQLException;

    public Caja cargarDatos(int id) throws SQLException;

    public Vector<Vector<Object>> listar() throws SQLException;

    public Vector<Vector<Object>> listar(Date fecha) throws SQLException;

    public Vector<Vector<Object>> listar(String tipo, Date fecha) throws SQLException;

    public Vector<Vector<Object>> movCaja(Date fecha);
}
