/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Almacen;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface IAlmacenDAO {

    public void grabar(Almacen a)throws SQLException;

    public void modificar(Almacen a)throws SQLException;

    public void borrar(String ref)throws SQLException ;
    
    public void eliminar(String marca)throws SQLException ;

    public Almacen cargarDatos(String ref)throws SQLException ; 
    
    public Vector<Vector<Object>> listar()throws SQLException;
    public Vector<Vector<Object>> listarRef(String referencia)throws SQLException;
    public Vector<Vector<Object>> listarRef(String referencia,Vector<String> nombresColumnasVector)throws SQLException;
    public Vector<Vector<Object>> listarDecripcion(String descripcion)throws SQLException;
    public Vector<Vector<Object>> listarDecripcion(String descripcion,Vector<String> nombresColumnasVector)throws SQLException;
    public Vector<Vector<Object>> listarMarca(String marca)throws SQLException;
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException;
    public Vector<String> listarMarcas()throws SQLException;
}
