package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Rebu;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public interface IRebuDAO {
    public void grabar(Rebu rebu)throws SQLException, ClassNotFoundException;
    //public void borrar(Rebu rebu)throws SQLException, ClassNotFoundException;
    public  void borrar(int idr) throws SQLException;
    public void modificar(Rebu rebu)throws SQLException, ClassNotFoundException;
    public Rebu cargarDatos(int idr)throws SQLException, ClassNotFoundException;
    public Vector<Vector<Object>> listar()throws SQLException, ClassNotFoundException;
    public Vector<Vector<Object>> listar(int idr)throws SQLException, ClassNotFoundException;
    public Vector<Vector<Object>> listar(Date ini,Date fin)throws SQLException, ClassNotFoundException;
    public boolean existe(int idr) throws SQLException;
    public int getNFACTURA() throws SQLException;

    Double sumCompras() throws SQLException;
    Double sumVentas() throws SQLException;

}
