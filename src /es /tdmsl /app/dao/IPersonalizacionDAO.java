/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Personalizacion;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IPersonalizacionDAO {
   public void grabar(Personalizacion personalizacion)throws SQLException;

    public void modificar(Personalizacion personalizacion)throws SQLException;

    public void borrar(int id)throws SQLException;
    
    public Personalizacion cargarDatos(int id)throws SQLException;

    public Vector<Vector<Object>> listar()throws SQLException;
    public Vector<Vector<Object>> listar(int idp)throws SQLException;
    public Vector<Vector<Object>> listar(String nombre)throws SQLException; 
}
