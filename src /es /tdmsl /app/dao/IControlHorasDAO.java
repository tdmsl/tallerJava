/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.ControlHoras;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public interface IControlHorasDAO {
    public void grabar(ControlHoras  ControlHoras) throws SQLException;

    public void modificar(ControlHoras  ControlHoras) throws SQLException;

    //public void borrar(int ido) throws SQLException;

    public ControlHoras cargarDatos(Date fecha) throws SQLException;

    public Vector<Vector<Object>> listar(Date fecha, int ido) throws SQLException;

    
    public Time lastIn(Date fecha)throws SQLException;
    
    public double sumaJobs(Date fechaIn,Date fechaOut,int ido)throws SQLException;
}
