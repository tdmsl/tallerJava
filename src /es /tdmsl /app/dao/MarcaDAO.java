/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class MarcaDAO extends DAO implements IMarcaDAO {
      
    public MarcaDAO() throws SQLException, ClassNotFoundException {
        super();
    }
      
    
    @Override
    public void grabar(Marca marca) throws SQLException{
        String sql = "insert into marcas(marca)"
                + "values(?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1,marca.getMarca().toUpperCase());
        pstmt.executeUpdate();
        System.out.println("pstn = "+pstmt);
    }
    
    @Override
    public void modificar(Marca marca) throws SQLException {
        // String ref = marca.getMarca();
        String ref = "marca";
        String sql = "UPDATE Marcas SET marca = ?"
                 + " WHERE marca like " + ref;

        
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, marca.getMarca());
        System.out.println(pstmt.toString());
        pstmt.executeUpdate();
        System.out.println("modificando marcas");
    }
    
    @Override
    public void borrar(String marca) throws SQLException {
        String sql = "DELETE FROM Marcas WHERE marca like '" + marca+"'";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int idMarcas) throws SQLException {
        String sql = "DELETE FROM Marcas WHERE idMarcas = " + idMarcas;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }
    
    
    
    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> marcas = new Vector<Vector<Object>>();
        String sql = "select * from marcas";
        PreparedStatement ps;
        ResultSet rst;
        Marca marca;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            marca = new Marca();
            marca.setMarca(rst.getString("marca"));
            marcas.add(marca.getVector());
        }
        return marcas;
    }

    @Override
    public Vector<String> listarColumna() throws SQLException {
         Vector<String> marcas = new Vector<String>();
        String sql = "select * from marcas";
        PreparedStatement ps;
        ResultSet rst;
       
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            marcas.add(rst.getString("marca"));
        }
        return marcas;
    }

    @Override
    public Vector<String> listarColumna(String marc) throws SQLException {
      
        Vector<String> marcas = new Vector<String>();
        String sql = "select * from marcas where marca like '"+marc+"'";
        PreparedStatement ps;
        ResultSet rst;
      
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {          
            marcas.add(rst.getString("marca"));
        }
        return marcas;
    }

    
    
    @Override
    public String buscar(String marca) throws SQLException {
         
        String sql = "select * from marcas where marca like '"+marca+"'";
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        if (rst.next()) {
          return rst.getString("marca");   
        }
        return null;
    }

    @Override
    public Marca cargarDatos(String marcaParam) throws SQLException {
        Marca marca = null;
        String sql = "select * from marcas where marca=?";
        ResultSet rst;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, marcaParam);
        System.out.println(ps);
           rst = ps.executeQuery();
        if (rst.next()) {
            marca = new Marca();
            marca.setIdmarcas(rst.getInt("idmarcas"));
            marca.setMarca(rst.getString("marca"));
           
        }
//        System.out.println("desde casrgar datos "+marca.getMarca());
        return marca;
    }

   
   
    
    
    
}
