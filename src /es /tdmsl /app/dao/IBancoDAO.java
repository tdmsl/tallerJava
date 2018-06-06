/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Banco;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface IBancoDAO {
  public void grabar(Banco banco) throws SQLException;

    public void modificar(Banco banco) throws SQLException;

    public void borrar(int id) throws SQLException;

    public Banco cargarDatos(int id) throws SQLException;

    public Vector<Vector<Object>> listar() throws SQLException;

    public Vector<Vector<Object>> listar(Date fecha) throws SQLException;

    public Vector<Vector<Object>> listar(String tipo, Date fecha) throws SQLException;
    
    public Vector<Vector<Object>> listar(Date ini,Date fin,int user) throws SQLException;

    public Vector<Vector<Object>> movCaja(Date fecha)throws SQLException;
    
    public double  sumaMov(String tipo,Date ini,Date fin,int user) throws SQLException;
}
