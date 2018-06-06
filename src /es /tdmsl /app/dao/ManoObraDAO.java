/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.ManoObra;
import es.tdmsl.app.beans.Reparacion;
import es.tdmsl.app.utilidades.Util;

import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class ManoObraDAO extends DAO implements IManoObraDAO {
    public SimpleDateFormat dfUSER = new SimpleDateFormat("dd-MM-yyyy");
    public SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd");

    public ManoObraDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void grabar(ManoObra mo) throws SQLException {
        String sql = " insert into manoobra(idr,concepto,horastrabajo,valorhora,total,fecha,operario) "
                + " values(?,?,?,?,?,?,?,?)";
          
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, mo.getIdr());
        pstmt.setString(2, mo.getConcepto().toUpperCase());
        pstmt.setDouble(3, mo.getHorasTrabajo());
        pstmt.setInt(4, mo.getValorHora());
        pstmt.setDouble(5, mo.getTotal());
        pstmt.setDate(6, new java.sql.Date(mo.getFecha().getTime()));
        pstmt.setString(7, Integer.toString(mo.getOperario()));
        pstmt.setString(8, Integer.toString(mo.getIdt()));
        System.out.println(pstmt.toString());
        int n = pstmt.executeUpdate();
      
    }

    @Override
    public void modificar(ManoObra mo) throws SQLException {
       int ID = mo.getId();
        String sql = "UPDATE MANOOBRA SET idr= ?,"
                + "Concepto = ?,"
                + "horasTrabajo = ?,"
                + "valorHora = ?,"
                + "total = ?,"
                + "operario = ?,"
                + "fecha = ?"
                + " WHERE id = " + ID;
        //System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, mo.getIdr());
        pstmt.setString(2, mo.getConcepto());
        pstmt.setDouble(3, mo.getHorasTrabajo());
        pstmt.setInt(4, mo.getValorHora());
        pstmt.setDouble(5, mo.getTotal());
        pstmt.setInt(6, mo.getOperario());
        pstmt.setDate(7,new java.sql.Date( mo.getFecha().getTime()));
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificarManoObra(Reparacion reparacion) throws SQLException, ClassNotFoundException {
        String sql;
        PreparedStatement pstmt;
        ManoObraDAO manoObraDAO = new ManoObraDAO();
//        int idt = materialDAO.getUltimoIdt();

        // Borrar los materiales de la reparacion:
        manoObraDAO.borrarReparacion(reparacion.getIdr());

        // Grabar los materiales de nuevo:
        for (int i = 0; i < reparacion.getManosObra().size(); i++) {
            sql = "insert into manoobra ("
                    + "idt ,"
                    + "idr ,"
                    + "concepto ,"
                    + "horastrabajo ,"
                    + "valorhora ,"
                    + "total ,"
                    + "fecha ,"
                    + "operario )"
                    + " values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, reparacion.getManosObra().get(i).getIdt());//aqui habria que buscar el eltimo idt
            pstmt.setInt(2, reparacion.getManosObra().get(i).getIdr());
            pstmt.setString(3, reparacion.getManosObra().get(i).getConcepto().toUpperCase());
            pstmt.setDouble(4, reparacion.getManosObra().get(i).getHorasTrabajo());
            pstmt.setDouble(5, reparacion.getManosObra().get(i).getValorHora());
            pstmt.setDouble(6, reparacion.getManosObra().get(i).getTotal());
            pstmt.setDate(7, Util.dateUtiltodateSQL(reparacion.getManosObra().get(i).getFecha()));
            pstmt.setInt(8, reparacion.getManosObra().get(i).getOperario());
            System.out.println(" linea " + i + "   " + pstmt);
            int n = pstmt.executeUpdate();
        }
    }

    @Override
    public void borrarLinea(int id) throws SQLException  {
         String sql = "DELETE FROM manoobra WHERE id = " +id ;
            System.out.println("DELETE FROM manoobra WHERE id = " + id);
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
    }

    @Override
    public void borrarReparacion(int idr) throws SQLException {
        String sql = "DELETE FROM manoobra WHERE idr = " +idr ;
            System.out.println("DELETE FROM manoobra WHERE idr = " + idr);
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException, ClassNotFoundException  {
        Vector<Vector<Object>> manoObra = new Vector<Vector<Object>>();
        Date fechaSql;
        java.util.Date fechaUser = null;
        String sql = "select * from manoobra";
        PreparedStatement ps;
        ResultSet rst;
        ManoObra ma;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            
            ma = new ManoObra();
            ma.setId(rst.getInt("id"));
            ma.setIdr(rst.getInt("idr"));
            ma.setConcepto(rst.getString("concepto"));
            ma.setHorasTrabajo(rst.getInt("horastrabajo"));
            ma.setHorasTrabajo(rst.getInt("horastrabajo"));
            ma.setValorHora(rst.getInt("valorhora"));
            ma.setTotal(rst.getInt("total"));
            // Capturo la fecha de la BD como java.sql.Date
            fechaSql = rst.getDate("fecha");
            // Crear un objeto java.util.Date a partir de la fecha de la _BD.
            if (fechaSql != null)
                fechaUser = new Date(fechaSql.getTime());
            else
                System.out.println("Hay una fecha a null");
            // Cargar al objeto.
            ma.setFecha(fechaUser);
            manoObra.add(ma.getVector());
        }
        return manoObra;
    }

    @Override
    public Vector<Vector<Object>> listar(int idr) throws SQLException, ClassNotFoundException {
         Vector<Vector<Object>> manoObra = new Vector<Vector<Object>>();
        Date fechaSql;
        java.util.Date fechaUser = null;
        String sql = "select * from manoobra where idr ="+ idr +"order by idt";
        PreparedStatement ps;
        ResultSet rst;
        ManoObra ma;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            ma = new ManoObra();
            ma.setId(rst.getInt("id"));
            ma.setIdr(rst.getInt("idr"));
            ma.setConcepto(rst.getString("concepto"));
            ma.setHorasTrabajo(rst.getDouble("horastrabajo"));
            ma.setValorHora(rst.getInt("valorhora"));
            ma.setTotal(rst.getDouble("total"));
            ma.setOperario(rst.getInt("operario"));
            // Capturo la fecha de la BD como java.sql.Date
            fechaSql = rst.getDate("fecha");
            // Crear un objeto java.util.Date a partir de la fecha de la _BD.
            if (fechaSql != null)
                fechaUser = new Date(fechaSql.getTime());
            else
                System.out.println("Hay una fecha a null");
            // Cargar al objeto.
            ma.setFecha(fechaUser);
            manoObra.add(ma.getVector());
        }
        return manoObra;
    }

    @Override
    public Vector<Vector<Object>> listarOperario(int operario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarOperariosFecha(int operario, Date inicio, Date fin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
