/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Banco;
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
public class BancoDAO extends DAO implements IBancoDAO {

    public BancoDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Banco banco) throws SQLException {
        System.out.println("ingreso"+banco.getIngreso());
        String sql = "insert into BANCOS(fecha,ingreso,salida,tipo,concepto,user)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        //pstmt.setInt(1, caja.getId());
        pstmt.setDate(1, Util.dateUtiltodateSQL(banco.getFecha()));
        pstmt.setDouble(2, banco.getIngreso());
        pstmt.setDouble(3, banco.getSalida());
        pstmt.setString(4, banco.getTipo());
        pstmt.setString(5, banco.getConcepto().toUpperCase());
        pstmt.setInt(6, banco.getUser());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Banco banco) throws SQLException {
        int id = banco.getId();
        String sql = "UPDATE bancos SET "
                + "fecha = ?,"
                + "ingreso = ?,"
                + "salida = ?,"
                + "tipo = ?,"
                + "concepto = ?,"
                + "user = ?"
                + " WHERE id = '" + id + "'";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, banco.getId());
        pstmt.setDate(1, Util.dateUtiltodateSQL(banco.getFecha()));
        pstmt.setDouble(2, banco.getIngreso());
        pstmt.setDouble(3, banco.getSalida());
        pstmt.setString(4, banco.getTipo());
        pstmt.setString(5, banco.getConcepto());
        pstmt.setInt(6, banco.getUser());
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int id) throws SQLException {
        String sql = "DELETE FROM bancos WHERE id = " + id + "";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Override
    public Banco cargarDatos(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(Date fecha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(String tipo, Date fecha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(Date ini, Date fin, int user) throws SQLException {
        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);

        Vector<Vector<Object>> movimientos = new Vector<Vector<Object>>();
        String sql = "select * from Bancos  "
                + "where (user = 0)  and fecha >= ? and fecha <= ?   ";

        PreparedStatement ps;
        ResultSet rst;

        ps = con.prepareStatement(sql);
        ps.setDate(1, date1);
        ps.setDate(2, date2);
        //System.out.println("sentencia " + ps);
        //System.out.println("Sentencia SQL : "+ps);
        rst = ps.executeQuery();
        int i = 0;
        while (rst.next()) {
            Vector<Object> mov = new Vector<>();
            mov.add(rst.getInt("id"));
            mov.add(Util.dateToString(rst.getDate("fecha")));
            mov.add(rst.getDouble("ingreso"));
            mov.add(rst.getDouble("salida"));
            mov.add(rst.getString("tipo"));
            mov.add(rst.getString("concepto"));
            movimientos.addElement(mov);
            //System.out.println(""+ operarios.elementAt(i));
            i++;
        }
        //System.out.println(""+ operarios);
        return movimientos;
    }

    @Override
    public Vector<Vector<Object>> movCaja(Date fecha) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double sumaMov(String tipo, Date ini, Date fin, int user) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);
        sql = "select sum(" + tipo + ") from BANCOS as total "
                + " where FECHA >= ? and FECHA <= ?";
        ps = con.prepareStatement(sql);
        ps.setDate(1, date1);
        ps.setDate(2, date2);
        //System.out.println("sql: " + ps);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = Math.round(rst.getDouble(1));
        }
        rst.close();
        return valor;
    }
}
