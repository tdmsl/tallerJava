package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Rebu;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class RebuDAO extends DAO implements IRebuDAO {
    private Rebu rebu;

    public RebuDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Rebu rebu) throws SQLException {

        String sql = "insert into REBU(NFACTURA,IDR,VALOR_COMPRA,VALOR_VENTA,FECHA_COMPRA)"
                + "values(?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,rebu.getNFactura());
        pstmt.setInt(2, rebu.getIdr());
        pstmt.setDouble(3, rebu.getValorCompra());
        pstmt.setDouble(4, rebu.getValorVenta());
        pstmt.setDate(5,new java.sql.Date(new Date().getTime()));
        System.out.println(pstmt);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(null,
                "GRABANDO REBU"+cargarDatos(rebu.getIdr()).toString());
    }

    @Override
    public void borrar(int idr) throws SQLException {
        String sql = "DELETE FROM REBU WHERE IDR = " + idr;
        //System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("Apunte Rebu borrado");
    }

    @Override
    public void modificar(Rebu rebu) throws SQLException {
       // String ref = a.getReferencia();
        String sql = "UPDATE REBU SET NFACTURA = ?,"
                +"IDR = ?,"
                + "VALOR_COMPRA = ?,"
                + "VALOR_VENTA = ?,"
                + "FECHA_COMPRA = ?,"
                + "FECHA_VENTA = ?"
                + " WHERE REBU.IDR  = " + rebu.getIdr() ;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, rebu.getNFactura());
        pstmt.setInt(2, rebu.getIdr());
        pstmt.setDouble(3, rebu.getValorCompra());
        pstmt.setDouble(4, rebu.getValorVenta());
        if (rebu.getFechaCompra()==null){
            pstmt.setDate(5, null);
        }else {
            pstmt.setDate(5, new java.sql.Date(rebu.getFechaCompra().getTime()));
        }
        if (rebu.getFechaVenta()==null){
            pstmt.setDate(6, null);
        }else {
            pstmt.setDate(6, new java.sql.Date(rebu.getFechaVenta().getTime()));
        }
        System.out.println(pstmt);
        pstmt.executeUpdate();

    }

    @Override
    public Rebu cargarDatos(int idr) throws SQLException {
        String sql = "select * from REBU where IDR like'" + idr + "'";
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        rebu=new Rebu();
        if (rst.next()){
            rebu.setNFactura(rst.getInt("NFACTURA"));
            rebu.setIdr(rst.getInt("IDR"));
            rebu.setValorVenta(rst.getDouble("VALOR_VENTA"));
            rebu.setValorCompra(rst.getDouble("VALOR_COMPRA"));
            rebu.setFechaCompra(rst.getDate("FECHA_COMPRA"));
            rebu.setFechaVenta(rst.getDate("FECHA_VENTA"));
        } else {
            rebu.setNFactura(0);
            rebu.setIdr(0);
            rebu.setValorVenta(0);
            rebu.setValorCompra(0);
            rebu.setFechaCompra(new Date());
            rebu.setFechaVenta(new Date());
        }
        return rebu;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> rebus = new Vector<Vector<Object>>();
        String sql = "select * from REBU JOIN MATRICULAS,REPARACIONES " +
                "WHERE REBU.IDR =REPARACIONES.IDR AND MATRICULAS.MATRICULA=REPARACIONES.MATRICULA ORDER BY REBU.NFACTURA";
        PreparedStatement ps;
        ResultSet rst;
        Rebu rebu;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            rebu = new Rebu();
            rebu.setIdr(rst.getInt("IDR"));
            rebu.setNFactura(rst.getInt("NFACTURA"));
            rebu.setModelo(rst.getString("MATRICULAS.MODELO"));
            rebu.setFechaCompra(rst.getDate("FECHA_COMPRA"));
            rebu.setValorCompra(rst.getDouble("VALOR_COMPRA"));
            rebu.setFechaVenta(rst.getDate("FECHA_VENTA"));
            rebu.setValorVenta(rst.getDouble("VALOR_VENTA"));
            rebus.add(rebu.getVector());
            System.out.println();
        }

        return rebus;
    }

    @Override
    public Vector<Vector<Object>> listar(int idr) {
        return null;
    }

    @Override
    public Vector<Vector<Object>> listar(Date ini, Date fin) {
        return null;
    }

    @Override
    public boolean existe(int idr) throws SQLException {
        String sql = "select IDR from REBU WHERE IDR ="+idr;
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        if (rst.first()){
            return true;
        }
        return false;
    }

    @Override
    public int getNFACTURA() throws SQLException {
        String sql = "SELECT MAX(NFACTURA) FROM REBU";
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        rst.next();
        return  rst.getInt("MAX(NFACTURA)");
    }

    @Override
    public Double sumCompras() throws SQLException {
        String sql = "SELECT SUM(VALOR_COMPRA)FROM REBU WHERE VALOR_VENTA = '0.0'";
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        rst.next();
        return rst.getDouble("SUM(VALOR_COMPRA)");
    }

    @Override
    public Double sumVentas() throws SQLException {
        String sql = "SELECT SUM(VALOR_VENTA)FROM REBU WHERE NFACTURA ='0'";
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        rst.next();
        return rst.getDouble("SUM(VALOR_VENTA)");
    }
}
