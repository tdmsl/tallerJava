/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Almacen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class AlmacenDAO extends DAO implements IAlmacenDAO {

    private Almacen almacen;

    public AlmacenDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void grabar(Almacen a) throws SQLException {
        String sql = "insert into almacen(referencia,descripcion,marca,pts_venta,pts_costo,descuento,pts_ventaE)"
                + "values(?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, a.getReferencia().toUpperCase());
        pstmt.setString(2, a.getDescripcion().toUpperCase());
        pstmt.setString(3, a.getMarca().toUpperCase());
        pstmt.setDouble(4, a.getPtsVenta());
        pstmt.setDouble(5, a.getPtsCosto());
        pstmt.setInt(6, a.getDescuento());
        pstmt.setDouble(7, a.getPtsVentaE());
        //System.out.println(pstmt);
        pstmt.executeUpdate();

    }

    @Override
    public void modificar(Almacen a) throws SQLException {

        String ref = a.getReferencia();
        String sql = "UPDATE ALMACEN SET descripcion = ?,"
                + "marca = ?,"
                + "pts_venta = ?,"
                + "pts_costo = ?,"
                + "descuento = ?,"
                + "pts_ventae = ?"
                + " WHERE referencia like '" + ref + "'";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, a.getDescripcion().toUpperCase());
        pstmt.setString(2, a.getMarca().toUpperCase());
        pstmt.setDouble(3, a.getPtsVenta());
        pstmt.setDouble(4, a.getPtsCosto());
        pstmt.setInt(5, a.getDescuento());
        pstmt.setDouble(6, a.getPtsVentaE());
        //System.out.println(pstmt.toString());
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(String ref) throws SQLException {
        //System.out.println(ref);

        String sql = "DELETE FROM almacen WHERE referencia like '" + ref + "'";
        System.out.println("DELETE FROM almacen WHERE referencia like " + ref);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();

    }

    @Override
    public Almacen cargarDatos(String ref) throws SQLException {
        String sql = "select * from almacen where referencia like'" + ref + "'";
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        almacen = new Almacen();
        if (rst.next()) {
            almacen.setNueva(false);
            almacen.setReferencia(rst.getString("referencia"));
            almacen.setDescripcion(rst.getString("descripcion"));
            almacen.setMarca(rst.getString("marca"));
            almacen.setPtsVenta(rst.getDouble("pts_venta"));
            almacen.setDescuento(rst.getInt("descuento"));
            almacen.setPtsCosto(rst.getDouble("pts_costo"));
            almacen.setPtsVentaE(rst.getDouble("pts_ventae"));
        } else {
            System.out.println("error");
        }
        //System.out.println(almacen.toString());
        return almacen;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> almacen = new Vector<Vector<Object>>();
        String sql = "select * from almacen";
        PreparedStatement pstmt;
        ResultSet rst;
        Almacen a;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            a = new Almacen();
            a.setReferencia(rst.getString("referencia"));
            a.setDescripcion(rst.getString("descripcion"));
            a.setMarca(rst.getString("marca"));
            a.setPtsVenta(rst.getInt("pts_venta"));
            a.setPtsCosto(rst.getInt("pts_costo"));
            a.setDescuento(rst.getInt("descuento"));
            a.setPtsVentaE(rst.getInt("pts_ventae"));
            almacen.add(a.getVector());
        }
        return almacen;
    }

    @Override
    public Vector<Vector<Object>> listarRef(String referencia) throws SQLException {
        return this.listarRef(referencia, "=");
    }

    public Vector<Vector<Object>> listarRef(String referencia, String op) throws SQLException {
        String comodin = "";

        if (op.equalsIgnoreCase("like")) {
            comodin = "%";
        }
        Vector<Vector<Object>> almacen = new Vector<Vector<Object>>();
        String sql = "select * from almacen where referencia " + op + " '" + referencia.toUpperCase() + comodin + "'";
        PreparedStatement pstmt;
        ResultSet rst;
        Almacen a;
        pstmt = con.prepareStatement(sql);
        System.out.println(pstmt);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            a = new Almacen();
            a.setReferencia(rst.getString("referencia"));
            a.setDescripcion(rst.getString("descripcion"));
            a.setMarca(rst.getString("marca"));
            a.setPtsVenta(rst.getInt("pts_venta"));
            a.setPtsCosto(rst.getInt("pts_costo"));
            a.setDescuento(rst.getInt("descuento"));
            a.setPtsVentaE(rst.getInt("pts_ventae"));
            almacen.add(a.getVector());
        }
        // System.out.println("retorna "+almacen.get(0));
        return almacen;
    }

    @Override
    public Vector<Vector<Object>> listarRef(String referencia, Vector<String> nombresColumnasVector) throws SQLException {
        int i = 0;
        String nombresColum = "";

        for (i = 0; i < nombresColumnasVector.size(); i++) {

            nombresColum += nombresColumnasVector.get(i);
            if (i + 1 < nombresColumnasVector.size()) {
                nombresColum += ",";
            }

        }

        Vector<Vector<Object>> almacen = new Vector<Vector<Object>>();
        String sql = "select " + nombresColum + " from almacen where referencia like '" + referencia.toUpperCase() + "%'";
        //System.out.println(sql);
        PreparedStatement pstmt;
        ResultSet rst;
        Almacen a;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            a = new Almacen();
            a.setReferencia(rst.getString("referencia"));
            a.setDescripcion(rst.getString("descripcion"));
            almacen.add(a.getVector(nombresColumnasVector));
        }
        //System.out.println(almacen);
        return almacen;
    }

    @Override
    public Vector<Vector<Object>> listarDecripcion(String descripcion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    
    public Vector<Vector<Object>> listarDecripcion(String descripcion, Vector<String> nombresColumnasVector) throws SQLException {
         int i = 0;
        String nombresColum = "";

        for (i = 0; i < nombresColumnasVector.size(); i++) {

            nombresColum += nombresColumnasVector.get(i);
            if (i + 1 < nombresColumnasVector.size()) {
                nombresColum += ",";
            }

        }

        Vector<Vector<Object>> almacen = new Vector<Vector<Object>>();
        String sql = "select " + nombresColum + " from almacen where descripcion like '" + descripcion.toUpperCase() + "%'";
        //System.out.println(sql);
        PreparedStatement pstmt;
        ResultSet rst;
        Almacen a;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            a = new Almacen();
            a.setReferencia(rst.getString("referencia"));
            a.setDescripcion(rst.getString("descripcion"));
            almacen.add(a.getVector(nombresColumnasVector));
        }
        //System.out.println(almacen);
        return almacen;
    }
    
     @Override
    public Vector<Vector<Object>> listarMarca(String marca) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException {
        Vector<Vector<Object>> almacenes = new Vector<Vector<Object>>();
        String sql = "select * from almacen " + "where " + campo + " like '" + nombre.toUpperCase() + "%'";
        // System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Almacen a;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            a = new Almacen();
            a.setReferencia(rst.getString("referencia"));
            a.setDescripcion(rst.getString("descripcion"));
            a.setMarca(rst.getString("marca"));
            a.setPtsVenta(rst.getDouble("pts_venta"));
            a.setDescuento(rst.getInt("descuento"));
            a.setPtsCosto(rst.getDouble("pts_costo"));
            a.setPtsVentaE(rst.getDouble("pts_ventae"));
            almacenes.add(a.getVector());
        }

        return almacenes;
    }

    @Override
    public Vector<String> listarMarcas() throws SQLException {
         Vector<String> marcas = new Vector<String>();
        String sql = "select distinct marca from almacen";
        PreparedStatement pstmt;
        ResultSet rst;
        pstmt = con.prepareStatement(sql);
        rst = pstmt.executeQuery();
        while (rst.next()) {
            marcas.add(rst.getString("marca"));
        }
        return marcas;
    }

    @Override
    public void eliminar(String marca) throws SQLException {
        String sql = "DELETE FROM almacen WHERE marca like '" + marca + "'";
        System.out.println("DELETE FROM almacen WHERE marca like " + marca);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    
    
}
