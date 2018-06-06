 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Material;
import es.tdmsl.app.beans.Reparacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class MaterialDAO extends DAO implements IMaterialDAO {

    public MaterialDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void grabar(Material material) throws SQLException {
        String sql = "insert into materiales(idr,referencia,descripcion,pvp,cantidad,descuento,total,idt)"
                + "values(?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, material.getIdr());
        pstmt.setString(2, material.getReferencia().toUpperCase());
        pstmt.setString(3, material.getDescripcion().toUpperCase());
        pstmt.setDouble(4, material.getPvp());
        pstmt.setDouble(5, material.getCantidad());
        pstmt.setInt(6, material.getDescuento());
        pstmt.setDouble(7, material.getTotal());
        pstmt.setInt(8, material.getIdt());
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Material material) throws SQLException {
        int ID = material.getId();
        String sql = "UPDATE    materiales SET "
                + "referencia = ?,"
                + "descripcion = ?,"
                + "pvp = ?,"
                + "cantidad = ?,"
                + "descuento = ?,"
                + "total = ?"
                + " WHERE id = " + ID;
        //System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, material.getReferencia());
        pstmt.setString(2, material.getDescripcion());
        pstmt.setDouble(3, material.getPvp());
        pstmt.setDouble(4, material.getCantidad());
        pstmt.setInt(5, material.getDescuento());
        pstmt.setDouble(6, material.getTotal());
        //System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificarMateriales(Reparacion reparacion) throws SQLException, ClassNotFoundException {
        String sql;
        PreparedStatement pstmt;
       // MaterialDAO materialDAO = new MaterialDAO();
//        int idt = materialDAO.getUltimoIdt();
        //materialDAO.borrarReparacion(reparacion.getIdr());

        // Borrar los materiales de la reparacion:

        borrarReparacion(reparacion.getIdr());
        // Grabar los materiales de nuevo:
        for (int i = 0; i < reparacion.getMateriales().size(); i++) {
            sql = "insert into materiales ("
                    + "idt ,"
                    + "idr ,"
                    + "referencia ,"
                    + "descripcion ,"
                    + "pvp ,"
                    + "cantidad ,"
                    + "descuento ,"
                    + "total )"
                    + " values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reparacion.getMateriales().get(i).getIdt());//aqui habria que buscar el eltimo idt
            pstmt.setInt(2, reparacion.getMateriales().get(i).getIdr());
            pstmt.setString(3, reparacion.getMateriales().get(i).getReferencia().toUpperCase());
            pstmt.setString(4, reparacion.getMateriales().get(i).getDescripcion().toUpperCase());
            pstmt.setDouble(5, reparacion.getMateriales().get(i).getPvp());
            pstmt.setDouble(6, reparacion.getMateriales().get(i).getCantidad());
            pstmt.setInt(7, reparacion.getMateriales().get(i).getDescuento());
            pstmt.setDouble(8, reparacion.getMateriales().get(i).getTotal());
            System.out.println(" linea " + i + "   " + pstmt);
            int n = pstmt.executeUpdate();

        }

    }

    @Override
    public void borrarLinea(int id) throws SQLException {
        String sql = "DELETE FROM materiales WHERE id = " + id;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Override
    public void borrarReparacion(int idr) throws SQLException {
        String sql = "DELETE FROM materiales WHERE idr = " + idr;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    private Vector<Vector<Object>> getList(String sql) throws SQLException {
        Vector<Vector<Object>> material = new Vector<Vector<Object>>();
        PreparedStatement ps;
        ResultSet rst;
        Material mat;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            mat = new Material();
            mat.setId(rst.getInt("id"));
            mat.setIdt(rst.getInt("idt"));
            mat.setIdr(rst.getInt("idr"));
            mat.setReferencia(rst.getString("referencia"));
            mat.setDescripcion(rst.getString("descripcion"));
            mat.setPvp(rst.getDouble("pvp"));
            mat.setCantidad(rst.getDouble("cantidad"));
            mat.setDescuento(rst.getInt("descuento"));
            mat.setTotal(rst.getDouble("total"));
            material.add(mat.getVector());
        }
        return material;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> material = new Vector<Vector<Object>>();
        String sql = "select * from materiales";
        material = this.getList(sql);
        return material;

    }

    @Override
    public Vector<Vector<Object>> listar(int idr) throws SQLException {

        Vector<Vector<Object>> material = new Vector<Vector<Object>>();
        String sql = "select * from materiales where idr = " + idr + "order by idt";
//        System.out.println(sql);
        material = this.getList(sql);
        return material;
    }

    @Override
    public int getUltimoIdt() throws SQLException {

        int ultimo = 0;

        String sql = "select max(idt) as ultimo from materiales";
        PreparedStatement ps;
        ResultSet rst;

        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        if (rst.next()) {
            ultimo = rst.getInt("ultimo");
        }
        return ultimo;
    }
}
