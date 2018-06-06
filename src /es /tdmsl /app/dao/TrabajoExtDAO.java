/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Reparacion;
import es.tdmsl.app.beans.TrabajoExt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author manu
 */
public class TrabajoExtDAO extends DAO implements ITrabajoExtDAO {

    public TrabajoExtDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(TrabajoExt trabajoExt) throws SQLException {
        String sql = "insert into trabajosext(idt,idr,descripcion,pvp,operario)"
                + "values(?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,trabajoExt.getIdt() );
        pstmt.setInt(2,trabajoExt.getIdr() );
        pstmt.setString(3,trabajoExt.getDescripcion().toUpperCase());
        pstmt.setInt(4,trabajoExt.getPvp() );
        pstmt.setInt(5,trabajoExt.getOperario() );
        
        System.out.println(pstmt);
        int n = pstmt.executeUpdate() ;
     
    }

    @Override
    public void modificar(TrabajoExt trabajoExt) throws SQLException {
        String sql = "UPDATE    trabajosext SET "
                + "idr = ?,"
                + "descripcion = ?,"
                + "pvp = ?,"
                + "operario = ?"
                + " WHERE id = " + trabajoExt.getId() ;

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,trabajoExt.getIdr() );
        pstmt.setString(2,trabajoExt.getDescripcion() );
        pstmt.setInt(3,trabajoExt.getPvp() );
        pstmt.setInt(4,trabajoExt.getOperario() );
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificarTrabajosExt(Reparacion reparacion) throws SQLException, ClassNotFoundException {
        String sql;
        PreparedStatement pstmt;
        //TrabajoExtDAO trabajoExtDAO = new TrabajoExtDAO();
        //trabajoExtDAO.borrarReparacion(reparacion.getIdr());

        // Borrar los materiales de la reparacion:
        borrarReparacion(reparacion.getIdr());

        // Grabar los materiales de nuevo:
        for (int i = 0; i < reparacion.getTrabajosExt().size(); i++) {
            sql = "insert into trabajosext ("
                    + "idt ,"
                    + "idr ,"
                    + "descripcion ,"
                    + "pvp ,"
                    + "operario )"
                    + " values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reparacion.getTrabajosExt().get(i).getIdt());//aqui habria que buscar el eltimo idt
            pstmt.setInt(2, reparacion.getTrabajosExt().get(i).getIdr());
            pstmt.setString(3, reparacion.getTrabajosExt().get(i).getDescripcion().toUpperCase());
            pstmt.setDouble(4, reparacion.getTrabajosExt().get(i).getPvp());
            pstmt.setInt(5, reparacion.getTrabajosExt().get(i).getOperario());

            System.out.println(" linea " + i + "   " + pstmt);
            int n = pstmt.executeUpdate();
        }
    }

    @Override
    public void borrarLinea(int id) throws SQLException {
        String sql = "DELETE FROM trabajosext WHERE id = " + id;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("TrabajoExt  borrado");
    }

    @Override
    public void borrarReparacion(int idr) throws SQLException {
        String sql = "DELETE FROM trabajosext WHERE idr = " + idr;
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("TrabajosExt  borrados");
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> trabajosExt = new Vector<Vector<Object>>();
        String sql = "select * from trabajosExt";
        PreparedStatement ps;
        ResultSet rst;
        TrabajoExt trabajoExt;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            trabajoExt = new TrabajoExt();
            trabajoExt.setId(rst.getInt("id"));
            trabajoExt.setIdr(rst.getInt("idr"));
            trabajoExt.setDescripcion(rst.getString("descripcion"));
            trabajoExt.setPvp(rst.getInt("pvp"));
            trabajoExt.setOperario(rst.getInt("operario"));

            trabajosExt.add(trabajoExt.getVector());
        }
        return trabajosExt;
    }

    @Override
    public Vector<Vector<Object>> listar(int idr) throws SQLException {
       Vector<Vector<Object>> trabajosExt = new Vector<Vector<Object>>();
        String sql = "select * from trabajosExt WHERE IDR =" + idr +"order by idt";
        PreparedStatement ps;
        ResultSet rst;
        TrabajoExt trabajoExt;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            trabajoExt = new TrabajoExt();
            trabajoExt.setId(rst.getInt("id"));
            trabajoExt.setIdr(rst.getInt("idr"));
            trabajoExt.setDescripcion(rst.getString("descripcion"));
            trabajoExt.setPvp(rst.getInt("pvp"));
            trabajoExt.setOperario(rst.getInt("operario"));

            trabajosExt.add(trabajoExt.getVector());
        }
        return trabajosExt;
    }
}
