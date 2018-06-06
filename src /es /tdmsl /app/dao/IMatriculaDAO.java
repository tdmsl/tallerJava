/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Matricula;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IMatriculaDAO {

    public void grabar(Matricula matricula) throws SQLException;

    public void modificar(Matricula matricula,String matriculaORG) throws SQLException;

    public void borrar(String matricula) throws SQLException;

    public Matricula cargarDatos(String matricula) throws SQLException, ClassNotFoundException;
    
    public Vector<Vector<Object>> listar() throws SQLException, ClassNotFoundException;

    public Vector<Vector<Object>> listarMatriculas(String matricula) throws SQLException, ClassNotFoundException;

    public Vector<Vector<Object>> listarCliente(int idCliente) throws SQLException, ClassNotFoundException;

    public Vector<Vector<Object>> listarMarcas(String marca) throws SQLException, ClassNotFoundException;

    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException, ClassNotFoundException;
}
