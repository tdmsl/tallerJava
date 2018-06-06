/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Marca;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IMarcaDAO {
    
    public Marca cargarDatos(String matricula) throws SQLException;

    public void grabar(Marca marca)throws SQLException;

    public void modificar(Marca marca)throws SQLException;

    public void borrar(String marca)throws SQLException;
    
    public void borrar(int idMarcas)throws SQLException;

    public Vector<Vector<Object>> listar()throws SQLException;
     
    public Vector<String> listarColumna()throws SQLException;
    
    public Vector<String> listarColumna(String marc)throws SQLException;
    
    public String buscar(String marca)throws SQLException;
    
}
