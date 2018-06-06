/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Operario;
import es.tdmsl.app.exceptions.OperarioException;
import es.tdmsl.app.utilidades.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Manu
 */
public class OperarioDAO extends DAO implements IOperarioDAO {

    Operario operario;

    public OperarioDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Operario operario) throws SQLException {
        String sql = "insert into operarios(nombre,direccion,codigopostal,telefono,fechaalta,sueldoneto,sueldobruto,observaciones)"
                + "values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, operario.getNombre().toUpperCase());
        pstmt.setString(2, operario.getDireccion().toUpperCase());
        pstmt.setInt(3, operario.getCodigoPostal());
        pstmt.setString(4, operario.getTelefono().toUpperCase());
        pstmt.setDate(5, new java.sql.Date(operario.getFechaAlta().getTime()));
        pstmt.setInt(6, operario.getSueldoNeto());
        pstmt.setInt(7, operario.getSueldoBruto());
        pstmt.setString(8, operario.getObseOperarios().toUpperCase());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Operario operario) throws SQLException {
        String sql = "UPDATE    operarios SET ido= ?,"
                + "nombre = ?,"
                + "direccion = ?,"
                + "codigopostal = ?,"
                + "telefono = ?,"
                + "fechaalta = ?,"
                + "sueldoneto = ?,"
                + "sueldobruto = ?,"
                + "observaciones = ?"
                + " WHERE ido = " + operario.getIdo();

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, operario.getIdo());
        pstmt.setString(2, operario.getNombre());
        pstmt.setString(3, operario.getDireccion());
        pstmt.setInt(4, operario.getCodigoPostal());
        pstmt.setString(5, operario.getTelefono());
        pstmt.setDate(6, new java.sql.Date(operario.getFechaAlta().getTime()));
        pstmt.setInt(7, operario.getSueldoNeto());
        pstmt.setInt(8, operario.getSueldoBruto());
        pstmt.setString(9, operario.getObseOperarios());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int ido) throws SQLException {
        String sql = "DELETE FROM operarios WHERE ido = '" + ido + "'";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("Borrado operario");
    }

    @Override
    public Operario cargarDatos(int ido) throws SQLException, OperarioException {

        String sql = "select * from OPERARIOS where ido =" + ido;
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        Operario operario;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        operario = new Operario();

        if (rst.next()) {
            operario.setIdo(rst.getInt("ido"));
            operario.setNombre(rst.getString("nombre"));
            operario.setDireccion(rst.getString("direccion"));
            operario.setCodigoPostal(rst.getInt("codigoPostal"));
            operario.setTelefono(rst.getString("telefono"));
            operario.setFechaAlta(rst.getDate("fechaAlta"));
            operario.setSueldoBruto(rst.getInt("sueldobruto"));
            operario.setSueldoNeto(rst.getInt("sueldoNeto"));
            operario.setObseOperarios(rst.getString("observaciones"));

        } else {
            System.out.println("error");

        }

        // System.out.println(operario.toString());
        return operario;



    }

    @Override
    public Vector<Operario> creaComboBoxOperarios() throws SQLException, OperarioException {
        Vector<Operario> operarios = new Vector();
        String sql = "select * from operarios";
        PreparedStatement ps;
        ResultSet rst;
        String ido = null;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        Operario operario;

        while (rst.next()) {
            operario = new Operario();
            operario.setIdo(rst.getInt("ido"));
            operario.setNombre(rst.getString("nombre"));

            operarios.add(operario);//acordarse de mofificar getVector()al cambiar el numero de columnas
        }

        return operarios;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException, OperarioException {
        Vector<Vector<Object>> operarios = new Vector<Vector<Object>>();
        String sql = "select * from operarios";
        PreparedStatement ps;
        ResultSet rst;
        Operario operario;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            operario = new Operario();
            operario.setIdo(rst.getInt("ido"));
            operario.setNombre(rst.getString("nombre"));
            operario.setDireccion(rst.getString("direccion"));
            operario.setCodigoPostal(rst.getInt("codigoPostal"));
            operario.setTelefono(rst.getString("telefono"));
            operario.setFechaAlta(rst.getDate("fechaAlta"));
            operario.setSueldoNeto(rst.getInt("sueldoNeto"));
            operario.setSueldoBruto(rst.getInt("sueldoBruto"));
            operario.setObseOperarios(rst.getString("observaciones"));

            operarios.add(operario.getVector());//acordarse de mofificar getVector()al cambiar el numero de columnas
        }

        return operarios;
    }

    @Override
    public Vector<Vector<Object>> listar(String nombre) throws SQLException, OperarioException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(int id) throws SQLException, OperarioException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException, OperarioException {
        Vector<Vector<Object>> operarios = new Vector<Vector<Object>>();
        String sql = "select * from operarios " + "where " + campo + " like '" + nombre.toUpperCase() + "%'";
        //System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            operario = new Operario();
            operario.setIdo(rst.getInt("ido"));
            operario.setNombre(rst.getString("nombre"));
            operario.setDireccion(rst.getString("direccion"));
            operario.setCodigoPostal(rst.getInt("codigoPostal"));
            operario.setTelefono(rst.getString("telefono"));
            operario.setFechaAlta(rst.getDate("fechaAlta"));
            operario.setSueldoNeto(rst.getInt("sueldoNeto"));
            operario.setSueldoBruto(rst.getInt("sueldoBruto"));
            operario.setObseOperarios(rst.getString("observaciones"));

            operarios.add(operario.getVector());//acordarse de mofificar getVector()al cambiar el numero de columnas
        }

        return operarios;
    }

    @Override
    public Vector<Vector<Object>> listarNombre(Date ini, Date fin) throws SQLException, OperarioException {

        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);

        Vector<Vector<Object>> operarios = new Vector<Vector<Object>>();
//        String sql = "select ido, o.nombre, sum(mo.total) as total_general from operarios o left join manoobra mo "
//                + "on o.ido = mo.operario left join reparaciones r on mo.idr = r.idr "
//                + "where (o.fechabaja is null)  and mo.fecha >= ? and mo.fecha <= ?  and r.fechasalida is not null "
//                + "group by o.nombre "
//                + "order by o.ido";
         String sql = "select ido, o.nombre,ROUND(sum(mo.HORASTRABAJO)) as total_horas, sum(mo.total) as total_general from operarios o inner join manoobra mo "
                + "on o.ido = mo.operario inner join reparaciones r on mo.idr = r.idr "
                + "where (o.fechabaja is null)  and  r.fechasalida >= ? and r.fechaSalida <= ? "
                + "group by o.nombre "
                + "order by o.ido";
//        String sql = "select ido, o.nombre, sum(mo.total) as total_general from operarios o left join manoobra mo "
//                + "on o.ido = mo.operario left join reparaciones r on mo.idr = r.idr "
//                + "where (o.fechabaja is null)  and mo.fecha >= ? and mo.fecha <= ?  and r.fechasalida BETWEEN ini AND fin "
//                + "group by o.nombre "
//                + "order by o.ido";
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        ps.setDate(1, date1);
        ps.setDate(2, date2);
        rst = ps.executeQuery();
        int i = 0;
        while (rst.next()) {
            Vector<Object> op = new Vector<Object>();
            op.add(rst.getInt("ido"));
            op.add(rst.getString("nombre"));
            op.add(rst.getInt("total_horas"));
            op.add(rst.getDouble("total_general"));
            operarios.addElement(op);
            //System.out.println(""+ operarios.elementAt(i));
            i++;
        }
        //con.close();
        return operarios;
    }

    @Override
    public int getNumero(String nombre) throws SQLException, OperarioException {
        int ido = 0;
        String sql = "select ido from operarios where nombre like '" + nombre.toUpperCase() + "'";
        System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        if (rst.next()) {
            ido = rst.getInt("ido");

        }

        return ido;

    }

    @Override
    public String getNombre(int años)throws SQLException, OperarioException {
        String sql = "select NOMBRE from operarios where IDO="+años;
        Statement stmt;
        ResultSet rst;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("NOMBRE");
        }
        return "OPERARIO ELIMINADO";
    }
}
