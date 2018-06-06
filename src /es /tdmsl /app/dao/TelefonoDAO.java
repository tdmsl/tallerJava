/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Telefono;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class TelefonoDAO extends DAO implements ITelefonoDAO{
    private Telefono telefono;

    public TelefonoDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Telefono telefono) throws SQLException {
       String sql = "insert into telefonos(nombre,direccion,telefono,movil,observaciones)"
                + "values(?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1,telefono.getNombre().toUpperCase());
        pstmt.setString(2,telefono.getDireccion().toUpperCase());
        pstmt.setString(3,telefono.getTelefono().toUpperCase());
        pstmt.setString(4,telefono.getMovil().toUpperCase());
        pstmt.setString(5,telefono.getObseTelefonos().toUpperCase());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Telefono telefono) throws SQLException {
         String sql = "UPDATE    telefonos SET idt = ?, "
                + "nombre = ?,"
                + "direccion = ?,"
                + "telefono = ?,"
                + "movil = ?,"
                + "observaciones = ?"
                + " WHERE idt = " + telefono.getIdt() ;

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, telefono.getIdt());
        pstmt.setString(2,telefono.getNombre() );
        pstmt.setString(3,telefono.getDireccion() );
        pstmt.setString(4,telefono.getTelefono() );
        pstmt.setString(5,telefono.getMovil());
        pstmt.setString(6,telefono.getObseTelefonos());
        System.out.println(sql);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int idt) throws SQLException {
        String sql = "DELETE FROM telefonos WHERE idt = " + idt;
        //System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("Telefono  borrado");
    }

    @Override
    public Telefono cargarDatos(String nombre) throws SQLException {
         String sql = "select * from telefonos where nombre like'"+ nombre+"'";
        System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        telefono = new Telefono(); 
        
        if (rst.next()) {
            telefono.setNueva(false);
        telefono.setIdt(rst.getInt("idt"));
        telefono.setNombre(rst.getString("nombre"));
        telefono.setDireccion(rst.getString("direccion") );
        telefono.setTelefono(rst.getString("telefono"));
        telefono.setMovil(rst.getString("movil"));
        telefono.setObseTelefonos(rst.getString("observaciones"));
        } else {
            System.out.println("rst vacio");
        }
        return telefono;
    }
    
    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> telefonos = new Vector<Vector<Object>>();
        String sql = "select * from telefonos";
        PreparedStatement ps;
        ResultSet rst;
        Telefono telefono;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            telefono = new Telefono();
            telefono.setIdt(rst.getInt("idt"));
            telefono.setNombre(rst.getString("nombre"));
            telefono.setDireccion(rst.getString("Direccion"));
            telefono.setTelefono(rst.getString("telefono"));
            telefono.setMovil(rst.getString("Movil"));
            telefono.setObseTelefonos(rst.getString("observaciones"));
            telefonos.add(telefono.getVector());
        }
        return telefonos;
    }

    @Override
    public Vector<Vector<Object>> listar(String nombre) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException {
        Vector<Vector<Object>> telefonos = new Vector<Vector<Object>>();
        String sql = "select * from telefonos " + "where " + campo + " like '" + nombre.toUpperCase() + "%'";
        System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Telefono t;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            t = new Telefono();
            t.setIdt(rst.getInt("idt"));
            t.setNombre(rst.getString("nombre"));
            t.setDireccion(rst.getString("direccion"));
            t.setTelefono(rst.getString("telefono"));
            t.setMovil(rst.getString("movil"));
            t.setObseTelefonos(rst.getString("observaciones"));
 
            telefonos.add(t.getVector());
        }

        return telefonos;
    }
    
}
