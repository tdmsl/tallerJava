/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

/**
 *
 * @author Manu
 */
import es.tdmsl.app.beans.Cliente;
import es.tdmsl.app.exceptions.ClienteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface IClienteDAO {

    public void grabar(Cliente c) throws SQLException, ClassNotFoundException;
   
    public void modificar(Cliente c) throws SQLException, ClassNotFoundException;
    
    public void borrar(int idc) throws SQLException, ClassNotFoundException;

    public Cliente cargarDatos(int idc) throws SQLException, ClassNotFoundException,ClienteException;
    
    public Vector<Vector<Object>> listar() throws SQLException, ClienteException;
    public Vector<Vector<Object>> listarIdc(int idc)throws SQLException, ClienteException;
    public Vector<Vector<Object>> listarNombre(String nombre)throws SQLException, ClienteException;
    public Vector<Vector<Object>> listarCampoNombre(String campo,String nombre)throws SQLException, ClienteException;
    public Vector<Vector<Object>> listarNif(String nif)throws SQLException, ClienteException;
    public Vector<Vector<Object>> listarMatriculas(String provincia) throws SQLException, ClienteException;
}
 