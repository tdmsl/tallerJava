/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Marca;
import es.tdmsl.app.beans.Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class ModeloDAO extends DAO implements IModeloDAO {

    public ModeloDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Modelo modelo) throws SQLException {
        String sql = "insert into modelos(marca,modelo)"
                + "values(?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, modelo.getMarca().toUpperCase());
        pstmt.setString(2, modelo.getModelo().toUpperCase());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Modelo modelo) throws SQLException {
        String sql = "UPDATE    modelos SET idmarcas= ?,"
                + "modelo = ?"
                + " WHERE id = " + modelo.getID();

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, modelo.getMarca().toUpperCase());
        pstmt.setString(2, modelo.getModelo().toUpperCase());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrarModelo(String modelo) throws SQLException {
        String sql = "DELETE FROM modelos WHERE modelo = '" + modelo + "'";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("Borrado modelo");
    }

    @Override
    public void borrarMarca(int idmarcas) throws SQLException {
        String sql = "DELETE FROM modelos WHERE idmarcas = " + idmarcas;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();

        System.out.println("Borrada marca");
    }


    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
      Vector<Vector<Object>> modelos = new Vector<Vector<Object>>();
        String sql = "select * from modelos";
        PreparedStatement ps;
        ResultSet rst;
        Modelo modelo;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            modelo = new Modelo();
            modelo.setMarca(rst.getString("marca"));
             modelo.setModelo(rst.getString("modelo"));
            
            modelos.add(modelo.getVector());
        }
        return modelos;
    }

    @Override
    public Vector<Vector<Object>> listar(String marca) throws SQLException {
        Vector<Vector<Object>> modelos = new Vector<Vector<Object>>();
        String sql = "select modelo from modelos where marca like '" + marca + "'";
        PreparedStatement ps;
        ResultSet rst;
        Modelo modelo;
        //System.out.println(sql);
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
             modelo = new Modelo();
            modelo.setModelo(rst.getString("modelo"));
           modelos.add(modelo.getVector()); 
            
        }
        return modelos;
    }

    @Override
    public Vector<String> listarColumna(String marca) throws SQLException {
        Vector<String> modelos = new Vector<String>();
        //String sql = "select * from modelos";
        String sql = "select modelo from modelos where marca like '" + marca + "'";       
        PreparedStatement ps;
        ResultSet rst;
      
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            modelos.add(rst.getString("modelo"));
        }
        return modelos;
    }



    @Override
    public Modelo cargarDatos(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement ps;
        String sql = "select * from modelos where id=?";
        ResultSet rst;
        Modelo modelo = null;
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rst = ps.executeQuery();
        IMarcaDAO marcaDAO = new MarcaDAO();
        Marca marca = null;

        if (rst.next()) {
            modelo = new Modelo();
            modelo.setMarca(rst.getString("marca"));
            modelo.setModelo(rst.getString("modelo"));
            modelo.setID(rst.getInt("id"));
        }
        return modelo;
    }
}
