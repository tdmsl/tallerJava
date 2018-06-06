/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Telefono;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface ITelefonoDAO {

    public void grabar(Telefono telefono)throws SQLException;

    public void modificar(Telefono telefono)throws SQLException;

    public void borrar(int idt)throws SQLException;

    public Telefono cargarDatos(String nombre) throws SQLException;
    
    public Vector<Vector<Object>> listar()throws SQLException;
    public Vector<Vector<Object>> listar(String nombre )throws SQLException;
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException;
}
