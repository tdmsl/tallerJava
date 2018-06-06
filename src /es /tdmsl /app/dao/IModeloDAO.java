/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Modelo;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IModeloDAO {
    
    public Modelo cargarDatos(int id) throws SQLException, ClassNotFoundException;

    public void grabar(Modelo modelo)throws SQLException;

    public void modificar(Modelo modelo)throws SQLException;

    public void borrarModelo(String modelo)throws SQLException;
    
    public void borrarMarca(int idmarcas)throws SQLException;
     
    public Vector<Vector<Object>> listar()throws SQLException;

    public Vector<Vector<Object>> listar(String marca)throws SQLException;
    
     public Vector<String> listarColumna(String marca)throws SQLException;
     
    // public String buscar(String modelo)throws SQLException;
}
