/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.ManoObra;
import es.tdmsl.app.beans.Reparacion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface IManoObraDAO {

    public void grabar(ManoObra mo)throws SQLException;

    public void modificar(ManoObra mo)throws SQLException ;

    public  void modificarManoObra(Reparacion reparacion) throws SQLException, ClassNotFoundException;

    public void borrarLinea(int id)throws SQLException;
    
    public void borrarReparacion(int idr)throws SQLException;
    
    public Vector<Vector<Object>> listar()throws SQLException,ClassNotFoundException;
    public Vector<Vector<Object>> listar(int idr)throws SQLException,ClassNotFoundException;
    public Vector<Vector<Object>> listarOperario(int operario)throws SQLException;
    public Vector<Vector<Object>> listarOperariosFecha(int operario, Date inicio,Date fin);
}
