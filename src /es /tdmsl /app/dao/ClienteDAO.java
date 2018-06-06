/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

/**
 *
 * @author Manu
 */
import es.tdmsl.app.beans.Cliente;
import es.tdmsl.app.exceptions.ClienteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class ClienteDAO extends DAO implements IClienteDAO {//extends DAO
//    protected Connection conexionBD; /////añadido
//    conexionBD = ConexionBD.getConexionBD().getCon();//porque no funciona aqui directamente

    public ClienteDAO() throws SQLException, ClassNotFoundException {
        super();
        //No entiendo porque se recurre a super pudiendo inicializar aqui la conexion    
    }
 

    public void grabar(Cliente c) throws SQLException {
        String sql = " insert into clientes(idc,nombre,direccion,ciudad,provincia,codigopostal,telefono,observacionescliente,nif) "
                + " values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, c.getIdc());
        pstmt.setString(2, c.getNombre().toUpperCase());
        pstmt.setString(3, c.getDireccion().toUpperCase());
        pstmt.setString(4, c.getCiudad().toUpperCase());
        pstmt.setString(5, c.getProvincia().toUpperCase());
        pstmt.setString(6, c.getCodPostal().toUpperCase());
        pstmt.setString(7, c.getTelefono().toUpperCase());
        pstmt.setString(8, c.getObservaciones().toUpperCase());
        pstmt.setString(9, c.getNif());
        
//        System.out.println("greabando cliente "+c.getIdc());
//        System.out.println(pstmt);
        int n = pstmt.executeUpdate();
//        System.out.println("n = " + n);
    }

    @Override
    public void modificar(Cliente c) throws SQLException {
        int IDC = c.getIdc();
        String sql = "UPDATE CLIENTES SET nombre = ?,"
                + "direccion = ?,"
                + "ciudad = ?,"
                + "provincia = ?,"
                + "codigopostal = ?,"
                + "telefono = ?,"
                + "observacionescliente = ?,"
                + "nif = ?"
                + " WHERE IDC = " + IDC;

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);

        pstmt.setString(1, c.getNombre().toUpperCase());
        pstmt.setString(2, c.getDireccion().toUpperCase());
        pstmt.setString(3, c.getCiudad().toUpperCase());
        pstmt.setString(4, c.getProvincia().toUpperCase());
        pstmt.setString(5, c.getCodPostal());
        pstmt.setString(6, c.getTelefono());
        pstmt.setString(7, c.getObservaciones().toUpperCase());
        pstmt.setString(8, c.getNif().toUpperCase());
        //System.out.println(""+pstmt);
        pstmt.executeUpdate();

    }

    @Override
    public void borrar(int idc) throws SQLException {
        String borrarCliente = "DELETE FROM CLIENTES WHERE IDC = " + idc;
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(borrarCliente);
        pstmt.executeUpdate();
    }

    @Override
    public Cliente cargarDatos(int idc) throws SQLException, ClassNotFoundException, ClienteException {
        String sql = "select * from CLIENTES where idc =" + idc;
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        Cliente cliente;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        cliente = new Cliente();
        if (rst.next()) {
            cliente.setIdc(rst.getInt("idc"));
            cliente.setNombre(rst.getString("nombre"));
            cliente.setDireccion(rst.getString("direccion"));
            cliente.setCodPostal(rst.getString("codigopostal"));
            cliente.setCiudad(rst.getString("ciudad"));
            cliente.setProvincia(rst.getString("provincia"));
            cliente.setTelefono(rst.getString("telefono"));
            cliente.setNif(rst.getString("nif"));
            cliente.setObservaciones(rst.getString("observacionesCliente"));
        }
        return cliente;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes order by idc";
        PreparedStatement ps;
        ResultSet rst;
        Cliente c;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;

    }

    @Override
    public Vector<Vector<Object>> listarMatriculas(String provincia) throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes order by provincia";
        PreparedStatement ps;
        ResultSet rst;
        Cliente c;

        if (provincia != null) {
            sql += " where provincia = ?";
        }

        ps = con.prepareStatement(sql);

        if (provincia != null) {
            ps.setString(1, provincia);
        }

        rst = ps.executeQuery();
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;
    }

    @Override
    public Vector<Vector<Object>> listarIdc(int idc) throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes where idc =" + idc;
        // System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Cliente c;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;
    }

    @Override
    public Vector<Vector<Object>> listarNombre(String nombre) throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes " + "where nombre like '" + nombre.toUpperCase() + "%'";
        System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Cliente c;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes " + "where " + campo + " like '" + nombre.toUpperCase() + "%'";
        // System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Cliente c;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;
    }

    @Override
    public Vector<Vector<Object>> listarNif(String nif) throws SQLException, ClienteException {
        Vector<Vector<Object>> clientes = new Vector<Vector<Object>>();
        String sql = "select * from clientes " + "where nif = '" + nif + "'";
        //System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Cliente c;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            c = new Cliente();
            c.setIdc(rst.getInt("idc"));
            c.setNombre(rst.getString("nombre"));
            c.setDireccion(rst.getString("direccion"));
            c.setCiudad(rst.getString("ciudad"));
            c.setProvincia(rst.getString("provincia"));
            c.setCodPostal(rst.getString("codigopostal"));
            c.setTelefono(rst.getString("telefono"));
            c.setObservaciones(rst.getString("observacionescliente"));
            c.setNif(rst.getString("nif"));

            clientes.add(c.getVector());
        }

        return clientes;
    }

    public int getIdc(String nif) throws SQLException {
        String sql = "select IDC from clientes "
                + "where nif = '" + nif.toUpperCase() + "'";
        Statement stmt;
        ResultSet rst;
        int idc = 0;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        if (rst.next()) {
            idc = rst.getInt("idc");
            rst.close();
        }
        return idc;
    }
}
