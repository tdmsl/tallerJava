/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Operario;
import es.tdmsl.app.exceptions.OperarioException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author manu
 */
public interface IOperarioDAO {

    public void grabar(Operario operario)throws SQLException;

    public void modificar(Operario operario)throws SQLException;

    public void borrar(int ido)throws SQLException;

    public Operario cargarDatos(int ido) throws SQLException,OperarioException;
    public Vector<Operario> creaComboBoxOperarios()throws SQLException, OperarioException; 
    public Vector<Vector<Object>> listar()throws SQLException, OperarioException;
    public Vector<Vector<Object>> listar(String nombre)throws SQLException, OperarioException;
    public Vector<Vector<Object>> listar(int id)throws SQLException, OperarioException;
    public Vector<Vector<Object>> listarCampoNombre(String campo,String nombre)throws SQLException,OperarioException;
    public Vector<Vector<Object>> listarNombre(Date ini, Date fin)throws SQLException,OperarioException;
    public int getNumero(String nombre)throws SQLException,OperarioException;
    public String getNombre(int años)throws SQLException,OperarioException;
}