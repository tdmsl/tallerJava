/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Caja;
import es.tdmsl.app.utilidades.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class CajaDAO extends DAO implements ICajaDAO {

    private Caja caja;

    public CajaDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void grabar(Caja caja) throws SQLException {
         System.out.println("importe ingreso "+caja.getIngreso() );
         System.out.println("importe salida "+caja.getSalida() );
        String sql = "insert into caja(fecha,ingreso,salida,tipo,concepto)"
                + "values(?,?,?,?,?)";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        //pstmt.setInt(1, caja.getId());
        pstmt.setDate(1, Util.dateUtiltodateSQL(caja.getFecha()));
        pstmt.setDouble(2, caja.getIngreso());
        pstmt.setDouble(3, caja.getSalida());
        pstmt.setString(4, caja.getTipo().toUpperCase());
        pstmt.setString(5, caja.getConcepto().toUpperCase());
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Caja caja) throws SQLException {
        int id = caja.getId();
        String sql = "UPDATE CAJA SET "
                + "id = ?,"
                + "fecha = ?,"
                + "ingreso = ?,"
                + "salida = ?,"
                + "tipo = ?,"
                + "concepto = ?"
                + " WHERE id = '" + id + "'";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, caja.getId());
        pstmt.setDate(2, Util.dateUtiltodateSQL(caja.getFecha()));
        pstmt.setDouble(3, caja.getIngreso());
        pstmt.setDouble(4, caja.getSalida());
        pstmt.setString(5, caja.getTipo());
        pstmt.setString(6, caja.getConcepto());
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int id) throws SQLException {
        String sql = "DELETE FROM caja WHERE id = '" + id + "'";
        System.out.println("DELETE FROM caja WHERE id = '" + id + "'  " + id);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Override
    public Caja cargarDatos(int id) throws SQLException {
        String sql = "select * from caja where id ='" + id + "'";
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        caja = new Caja();
        if (rst.next()) {

            caja.setId(rst.getInt("id"));
            caja.setFecha(rst.getDate("fecha"));
            caja.setIngreso(rst.getInt("ingreso"));
            caja.setSalida(rst.getInt("salida"));
            caja.setTipo(rst.getString("tipo"));
            caja.setConcepto(rst.getString("concepto"));
        } else {
            System.out.println("error");
        }
        //System.out.println(almacen.toString());
        return caja;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> caja = new Vector<Vector<Object>>();
        String sql = "select * from caja";
        PreparedStatement pstmt;
        ResultSet rst;

        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            Caja mov = new Caja();
            mov.setId(rst.getInt("id"));
            mov.setFecha(rst.getDate("fecha"));
            mov.setIngreso(rst.getInt("ingreso"));
            mov.setSalida(rst.getInt("salida"));
            mov.setTipo(rst.getString("tipo"));
            mov.setConcepto(rst.getString("concepto"));
            caja.add(mov.getVector());
        }
        return caja;
    }

    @Override
    public Vector<Vector<Object>> listar(Date fecha) throws SQLException {
        Vector<Vector<Object>> caja = new Vector<Vector<Object>>();
        String sql = "select * from caja where fecha = '" +es.tdmsl.app.utilidades.Util.dateUtiltodateSQL(fecha) + "'";

        PreparedStatement pstmt;
        ResultSet rst;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            Caja mov = new Caja();
            mov.setId(rst.getInt("id"));
            mov.setFecha(rst.getDate("fecha"));
            mov.setIngreso(rst.getDouble("ingreso"));
            mov.setSalida(rst.getDouble("salida"));
            mov.setTipo(rst.getString("tipo"));
            mov.setConcepto(rst.getString("concepto"));
            caja.add(mov.getVector());
            //System.out.println("caja"+caja);
        }
        return caja;
    }

    @Override
    public Vector<Vector<Object>> listar(String tipo, Date fecha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> movCaja(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
