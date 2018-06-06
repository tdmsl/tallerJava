/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.ControlHoras;
import es.tdmsl.app.utilidades.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class ControlHorasDAO extends DAO implements IControlHorasDAO {

    public ControlHorasDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(ControlHoras controlHoras) throws SQLException {
        String sql;
        Time auxEntrada;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        sql = "select entrada, salida from CONTROLHORAS where fecha = '" + Util.dateUtiltodateSQL(controlHoras.getFecha()) + "' "
                + " AND IDO = " + controlHoras.getIdo() + " and salida is null";

        Statement stmt;
        ResultSet rst;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        if (rst.next()) {
            //if (rst.getDate("salida") == null) {
                System.out.println("salida = null");
                sql = "UPDATE CONTROLHORAS SET SALIDA = ? "
                        + "WHERE ENTRADA = ?  AND SALIDA IS NULL "
                        + "AND FECHA = ? and ido = ?";
                PreparedStatement pstmt;
                pstmt = con.prepareStatement(sql);
                //pstmt.setTime(1, controlHoras.getSalida());
                //pstmt.setTime(2,controlHoras.getEntrada());

                System.out.println("controlhoras: " + controlHoras);

                pstmt.setString(1, sdf.format(controlHoras.getSalida()));               
                // La fecha de entrada es la de la BD:
                auxEntrada = rst.getTime("entrada");
                System.out.println("entrada bd: " + rst.getString("entrada"));
                
                pstmt.setString(2, sdf.format(auxEntrada));
                pstmt.setDate(3, Util.dateUtiltodateSQL(controlHoras.getFecha()));
                pstmt.setInt(4, controlHoras.getIdo());
                System.out.println("pstmt : " + pstmt);
                System.out.println("" + controlHoras.getEntrada());
                pstmt.executeUpdate();
//            } else {
//                System.out.println("salida tiene valor");
//                sql = "insert into CONTROLHORAS(ido,fecha,entrada)"
//                        + "values(?,?,?)";
//                PreparedStatement pstmt;
//                pstmt = con.prepareStatement(sql);
//                pstmt.setInt(1, controlHoras.getIdo());
//                pstmt.setDate(2, Util.dateUtiltodateSQL(controlHoras.getFecha()));
//                pstmt.setTime(3, controlHoras.getEntrada());
//                pstmt.executeUpdate();
//            }
        } else {//si es el 1º apunte
            sql = "insert into CONTROLHORAS(ido,fecha,entrada)"
                    + "values(?,?,?)";
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, controlHoras.getIdo());
            pstmt.setDate(2, Util.dateUtiltodateSQL(controlHoras.getFecha()));
            pstmt.setString(3, sdf.format(controlHoras.getEntrada()));
            pstmt.executeUpdate();
        }
    }

    @Override
    public void modificar(es.tdmsl.app.beans.ControlHoras ControlHoras) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public es.tdmsl.app.beans.ControlHoras cargarDatos(Date fecha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(Date date, int ido) throws SQLException {
        Vector<Vector<Object>> controlesHoras = new Vector<Vector<Object>>();
        String sql = "select * from controlhoras where fecha = ? and ido = ? order by fecha";
        PreparedStatement ps;
        ResultSet rst;
        ControlHoras controlHoras;
        ps = con.prepareStatement(sql);
        ps.setDate(1, Util.dateUtiltodateSQL(new Date()));
        ps.setInt(2, ido);
        rst = ps.executeQuery();

        while (rst.next()) {
            controlHoras = new ControlHoras();
            controlHoras.setIdo(rst.getInt("ido"));
            controlHoras.setFecha(rst.getDate("fecha"));
            controlHoras.setEntrada(rst.getTime("entrada"));
            controlHoras.setSalida(rst.getTime("salida"));
            controlesHoras.add(controlHoras.getVector());//acordarse de mofificar getVector()al cambiar el numero de columnas
        }
        return controlesHoras;
    }

    @Override
    public Time lastIn(Date fecha) throws SQLException {

        String sql = ("select int from controlhoras where fecha ='" + Util.dateUtiltodateSQL(fecha) + "'");
        System.out.println("sentencia  " + sql);
        PreparedStatement pstmt;
        ResultSet rst;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        if (rst.last()) {
            System.out.println("Hora ultima entrada " + rst.getTime("int"));
            return rst.getTime("int");
        } else {
            return new Time(new Date().getTime());
        }


    }

    @Override
    public double sumaJobs(Date fechaIn, Date fechaOut, int ido) throws SQLException {
        String sql;
        PreparedStatement ps;
        ResultSet rst;
        java.sql.Date date1 = Util.dateUtiltodateSQL(fechaIn);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fechaOut);
        sql = "select sum(datediff('minute',entrada, salida)/60.0) as horas "
                + "from CONTROLHORAS "
                + "where IDO= ? AND FECHA between ? AND ? "
                + "group by FECHA "
                + "order by FECHA";
        ps = con.prepareStatement(sql);
        ps.setInt(1, ido);
        ps.setDate(2, date1);
        ps.setDate(3, date2);
        
        System.out.println("sql sumaJobs : " + ps);
        rst = ps.executeQuery();
        double sum = 0;
        while (rst.next()) {
            sum += rst.getDouble("horas");

        }
        return redondear(sum);
    }

    public double redondear(double numero) {
        int decimales = 2;
        return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }
}
