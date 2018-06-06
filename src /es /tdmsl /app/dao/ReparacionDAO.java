/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.ManoObra;
import es.tdmsl.app.beans.Material;
import es.tdmsl.app.beans.Reparacion;
import es.tdmsl.app.beans.TrabajoExt;
import es.tdmsl.app.utilidades.Util;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.tdmsl.app.utilidades.*;

/**
 * @author Manu
 */
public class ReparacionDAO extends DAO implements IReparacionDAO {

    public ReparacionDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Reparacion reparacion) throws SQLException, ClassNotFoundException {

        if (con.isClosed()){
            Util.jOptionPaneTimeOut(null,"Se ha perdido la conexion\nReconectando","Reconectando",2);
            con=ConexionBD.getConexionBD().getCon();
        }
        con.setAutoCommit(false);
        java.sql.Date d = null;
        String sql;
        PreparedStatement pstmt;
        sql = " insert into reparaciones("
                + "idr,"
                + "cuentacliente,"
                + "matricula,"
                + "km,"
                + "fechaentrada,"
                + "fechasalida,"
                + "nfactura,"
                + "receptor,"
                + "trabajosrealizar) "
                + " values(?,?,?,?,?,?,?,?,?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, reparacion.getIdr());
        pstmt.setInt(2, reparacion.getCuentaCliente());
        pstmt.setString(3, reparacion.getMatricula().toUpperCase());
        pstmt.setInt(4, reparacion.getKm());

        if (reparacion.getFechaEntrada() != null) {
            d = new java.sql.Date(reparacion.getFechaEntrada().getTime());
            System.out.println("fecha entrada : " + d);
        }
        pstmt.setDate(5, d);
        pstmt.setDate(6, null);
        pstmt.setString(7, reparacion.getnFactura());
        pstmt.setInt(8, reparacion.getReceptor());
        pstmt.setString(9, reparacion.getTrabajosRealizar().toUpperCase());
        //System.out.println(pstmt.toString());
        int n = pstmt.executeUpdate();

        try {
            //modificamos los materiales de la reparacion

            MaterialDAO materialDAO = new MaterialDAO();
            materialDAO.modificarMateriales(reparacion);

            TrabajoExtDAO trabajoExtDAO = new TrabajoExtDAO();
            trabajoExtDAO.modificarTrabajosExt(reparacion);

            ManoObraDAO manoObraDAO = new ManoObraDAO();
            manoObraDAO.modificarManoObra(reparacion);

            con.commit();

        } catch (ClassNotFoundException ex) {
            con.rollback();
            JOptionPane.showMessageDialog(null, "Error " + ex);
            Logger.getLogger(ReparacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null, "Se ha grabado la reparacion, correctamente ");
    }

    @Override
    public void modificar(Reparacion reparacion) throws SQLException, ClassNotFoundException  {

        if (!con.isValid(1)){
            JOptionPane.showMessageDialog(null,"Conexion invalida\n" +
                    "hablar con manu\n" +
                    "ReparacionDAO page 100");

        }
        if (con.isClosed()){
            Util.jOptionPaneTimeOut(null,"Se ha perdido la conexion\nReconectando","Reconectando",2);
            con=ConexionBD.getConexionBD().getCon();
        }
        con.setAutoCommit(false);
        String sql;
        PreparedStatement pstmt;
        sql = "UPDATE    REPARACIONES SET "
                + "cuentacliente = ?,"
                + "matricula = ?,"
                + "km = ?,"
                + "fechaentrada = ?,"
                + "fechasalida = ?,"
                + "nfactura = ?,"
                + "receptor = ?,"
                + "trabajosrealizar = ?,"
                + "cuentacliente2 = ?,"
                + " nsiniestro = ?"
                + " WHERE idr = " + reparacion.getIdr();

        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, reparacion.getCuentaCliente());
        pstmt.setString(2, reparacion.getMatricula());
        pstmt.setInt(3, reparacion.getKm());
        pstmt.setDate(4, new java.sql.Date(reparacion.getFechaEntrada().getTime()));
        java.sql.Date d = null;
        if (reparacion.getFechaSalida() != null) {
            d = new java.sql.Date(reparacion.getFechaSalida().getTime());
        }
        pstmt.setDate(5, d);
        pstmt.setString(6, reparacion.getnFactura());
        pstmt.setInt(7, reparacion.getReceptor());
        pstmt.setString(8, reparacion.getTrabajosRealizar());
        pstmt.setInt(9, reparacion.getCuentaCliente2());
        pstmt.setString(10, reparacion.getnSiniestro());
        System.out.println(pstmt);
        pstmt.executeUpdate();

        //modificamos los materiales de la reparacion

        try {
            MaterialDAO materialDAO = new MaterialDAO();
            materialDAO.modificarMateriales(reparacion);

            TrabajoExtDAO trabajoExtDAO = new TrabajoExtDAO();
            trabajoExtDAO.modificarTrabajosExt(reparacion);

            ManoObraDAO manoObraDAO = new ManoObraDAO();
            manoObraDAO.modificarManoObra(reparacion);

            con.commit();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }


    }

    @Override
    public void modificar(String antigua, String nueva) throws SQLException, ClassNotFoundException  {
        if (con.isClosed()){
            Util.jOptionPaneTimeOut(null,"Se ha perdido la conexion\nReconectando","Reconectando",2);
            con=ConexionBD.getConexionBD().getCon();
        }
        String sql = "UPDATE    REPARACIONES SET "
                + "matricula = ?"
                + " WHERE matricula = '" + antigua + "'";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, nueva.toUpperCase());
        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(int idr) throws SQLException {
        String sql;
        con.setAutoCommit(false);
        PreparedStatement pstmt;

        try {

            sql = "DELETE FROM materiales WHERE idr = " + idr;
            //System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "DELETE FROM trabajosExt WHERE idr = " + idr;
            //System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "DELETE FROM manoobra WHERE idr = " + idr;
            //System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

            sql = "DELETE FROM reparaciones WHERE idr = " + idr;
            //System.out.println(sql);
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            //System.out.println("reparacion  borrada");

            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Reparacion cargarDatos(int idr) throws SQLException {
        String sql = "select * from REPARACIONES where idr =" + idr;
        PreparedStatement ps, psMat, psText, psMo;
        ResultSet rst, rstMat, rstText, rstMo;
        Reparacion reparacion;
        Material material;
        TrabajoExt trabajoext;
        ManoObra manoobra;

        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        reparacion = new Reparacion();
        if (rst.next()) {
            reparacion.setIdr(rst.getInt("idr"));
            reparacion.setCuentaCliente(rst.getInt("cuentaCliente"));
            reparacion.setMatricula(rst.getString("matricula"));
            reparacion.setKm(rst.getInt("km"));
            reparacion.setFechaEntrada(rst.getDate("fechaEntrada"));
            reparacion.setFechaSalida(rst.getDate("fechaSalida"));
            reparacion.setnFactura(rst.getString("nfactura"));
            reparacion.setReceptor(rst.getInt("receptor"));
            reparacion.setTrabajosRealizar(rst.getString("trabajosRealizar"));
            reparacion.setCuentaCliente2(rst.getInt("cuentacliente2"));
            reparacion.setnSiniestro(rst.getString("nsiniestro"));
            // System.out.println(reparacion.toString());

            // Cargar materiales:
            sql = "select * from materiales where idr = " + idr + " order by idt";
            psMat = con.prepareStatement(sql);
            rstMat = psMat.executeQuery();

            while (rstMat.next()) {
                material = new Material();
                material.setId(rstMat.getInt("ID"));
                material.setIdr(rstMat.getInt("idr"));
                material.setReferencia(rstMat.getString("REFERENCIA"));
                material.setDescripcion(rstMat.getString("DESCRIPCION"));
                material.setPvp(rstMat.getDouble("PVP"));
                material.setCantidad(rstMat.getDouble("cantidad"));
                material.setDescuento(rstMat.getInt("descuento"));
                material.setTotal(rstMat.getDouble("total"));
                material.setIdt(rstMat.getInt("idt"));

                reparacion.añadeMaterial(material);
            }

            // Cargar Trabajos ext.
            sql = "select * from trabajosext where idr = " + idr + " order by idt";
            psText = con.prepareStatement(sql);
            rstText = psText.executeQuery();

            while (rstText.next()) {
                trabajoext = new TrabajoExt();
                trabajoext.setId(rstText.getInt("ID"));
                trabajoext.setIdr(rstText.getInt("idr"));
                trabajoext.setDescripcion(rstText.getString("DESCRIPCION"));
                trabajoext.setPvp(rstText.getInt("PVP"));
                trabajoext.setOperario(rstText.getInt("operario"));
                trabajoext.setIdt(rstText.getInt("idt"));

                reparacion.añadeTrabajoExterior(trabajoext);
            }

            // Cargar Mano de obra
            sql = "select * from manoobra where idr = " + idr + " order by idt";
            psMo = con.prepareStatement(sql);
            rstMo = psMo.executeQuery();

            while (rstMo.next()) {
                try {
                    manoobra = new ManoObra();
                    manoobra.setId(rstMo.getInt("ID"));
                    manoobra.setIdr(rstMo.getInt("idr"));
                    manoobra.setConcepto(rstMo.getString("concepto"));
                    manoobra.setHorasTrabajo(rstMo.getDouble("horastrabajo"));
                    manoobra.setValorHora(rstMo.getInt("valorhora"));
                    manoobra.setTotal(rstMo.getDouble("total"));
                    manoobra.setFecha(rstMo.getDate("fecha"));
                    manoobra.setOperario(rstMo.getInt("operario"));
                    manoobra.setIdt(rstMo.getInt("idt"));

                    reparacion.añadeManoDeObra(manoobra);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReparacionDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return reparacion;
    }

    public int getUltimoIDR() throws SQLException {
        int ultimo = 0;

        String sql = "select max(idr) as ultimo from REPARACIONES";
        PreparedStatement ps;
        ResultSet rst;

        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        if (rst.next()) {
            ultimo = rst.getInt("ultimo");
        }

        return ultimo;

    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException, NoSuchElementException {
        Vector<Vector<Object>> reparaciones = new Vector<Vector<Object>>();
        String sql = "select * from reparaciones order by idr";
        PreparedStatement ps;
        ResultSet rst;
        Reparacion reparacion;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            reparacion = new Reparacion();
            reparacion.setIdr(rst.getInt("idr"));
            reparacion.setCuentaCliente(rst.getInt("cuentaCliente"));
            reparacion.setMatricula(rst.getString("matricula"));
            reparacion.setKm(rst.getInt("km"));
            reparacion.setFechaEntrada(rst.getDate("fechaentrada"));
            reparacion.setFechaSalida(rst.getDate("fechasalida"));
            reparacion.setnFactura(rst.getString("nfactura"));
            reparacion.setReceptor(rst.getInt("receptor"));
            reparacion.setTrabajosRealizar(rst.getString("trabajosrealizar"));
            reparacion.setCuentaCliente2(rst.getInt("cuentacliente2"));
            reparacion.setnSiniestro(rst.getString("nsiniestro"));
            reparaciones.add(reparacion.getVector());
        }
        return reparaciones;
    }

    @Override
    public Vector<Vector<Object>> listar(int idr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarIDC(int cuentaCliente) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(String matricula) throws SQLException {
        Vector<Vector<Object>> reparaciones = new Vector<Vector<Object>>();
        String sql = "select * from reparaciones where matricula like'" + matricula + "'";
        PreparedStatement ps;
        ResultSet rst;
        Reparacion reparacion;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            reparacion = new Reparacion();
            reparacion.setIdr(rst.getInt("idr"));
            reparacion.setCuentaCliente(rst.getInt("cuentaCliente"));
            reparacion.setMatricula(rst.getString("matricula"));
            reparacion.setKm(rst.getInt("km"));
            reparacion.setFechaEntrada(rst.getDate("fechaentrada"));
            reparacion.setFechaSalida(rst.getDate("fechasalida"));
            reparacion.setnFactura(rst.getString("nfactura"));
            reparacion.setReceptor(rst.getInt("receptor"));
            reparacion.setTrabajosRealizar(rst.getString("trabajosrealizar"));
            reparacion.setCuentaCliente2(rst.getInt("cuentacliente2"));
            reparacion.setnSiniestro(rst.getString("nsiniestro"));
            reparaciones.add(reparacion.getVector());
        }
        return reparaciones;
    }

    @Override
    public Vector<Vector<Object>> listar(Date inicio, Date fin) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarFactura(int factura) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException {
        Vector<Vector<Object>> reparaciones = new Vector<Vector<Object>>();
//        String sql = "select * from reparaciones " + "where " + campo + " like '" + nombre.toUpperCase() + "%'";
        String sql = "select * from reparaciones "
                + "where " + campo + " like '" + nombre.toUpperCase() + "%'"
                + "order by " + campo, fechaentrada;
        //System.out.println(sql);                                                  
        PreparedStatement ps;
        ResultSet rst;
        Reparacion reparacion;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            reparacion = new Reparacion();
            reparacion.setIdr(rst.getInt("idr"));
            reparacion.setCuentaCliente(rst.getInt("cuentaCliente"));
            reparacion.setMatricula(rst.getString("matricula"));
            reparacion.setKm(rst.getInt("km"));
            reparacion.setFechaEntrada(rst.getDate("fechaentrada"));
            reparacion.setFechaSalida(rst.getDate("fechasalida"));
            reparacion.setnFactura(rst.getString("nfactura"));
            reparacion.setReceptor(rst.getInt("receptor"));
            reparacion.setTrabajosRealizar(rst.getString("trabajosrealizar"));
            reparacion.setCuentaCliente2(rst.getInt("cuentacliente2"));
            reparacion.setnSiniestro(rst.getString("nsiniestro"));
            reparaciones.add(reparacion.getVector());
        }


        return reparaciones;
    }

    @Override
    public double totaltrabajosExteriores(Date ini, Date fin) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;

        if (ini == null || fin == null) {
            return 0.0;
        }
        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);

        sql = "select sum(te.pvp) as total from reparaciones r inner join trabajosext te on r.idr = te.idr "
                + " where r.fechasalida >= ? and fechaSalida <= ?";
        ps = con.prepareStatement(sql);

        ps.setDate(1, date1);
        ps.setDate(2, date2);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = rst.getDouble(1);
        }

        rst.close();
        return valor;
    }

    @Override
    public double totalMateriales(Date ini, Date fin) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        if (ini == null || fin == null) {
            return 0.0;
        }
        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);
//        sql = "select sum(ma.pvp) as total from reparaciones r inner join materiales ma on r.idr = ma.idr "
//                + " where r.fechasalida >= ? and r.fechaSalida <= ?";
        sql = "select sum(ma.total) as total from reparaciones r inner join materiales ma on r.idr = ma.idr "
                + " where r.fechasalida >= ? and r.fechaSalida <= ?";
        ps = con.prepareStatement(sql);
        ps.setDate(1, date1);
        ps.setDate(2, date2);
        // System.out.println("sql: " + ps);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = rst.getDouble(1);
        }
        rst.close();
        return valor;
    }

    @Override
    public double totalManoObra(Date ini, Date fin) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        if (ini == null || fin == null) {
            return 0.0;
        }
        java.sql.Date date1 = Util.dateUtiltodateSQL(ini);
        java.sql.Date date2 = Util.dateUtiltodateSQL(fin);
        sql = "select sum(mo.total) as total from reparaciones r inner join manoobra mo on r.idr = mo.idr "
                + " where r.fechasalida >= ? and fechaSalida <= ?";
        ps = con.prepareStatement(sql);
        ps.setDate(1, date1);
        ps.setDate(2, date2);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = rst.getDouble(1);
        }
        rst.close();
        return valor;
    }

    @Override
    public double sumaMat(int idr) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        sql = "select sum(total) as totalMa\n"
                + "from materiales\n"
                + " where idr = " + idr;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = redondear(rst.getDouble(1));
        }
        rst.close();
        return valor;
    }

    @Override
    public double sumaText(int idr) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        sql = "select sum(pvp) as totalText\n"
                + "from trabajosext\n"
                + " where idr = " + idr;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = redondear(rst.getDouble(1));
        }
        rst.close();
        return valor;
    }

    @Override
    public double sumaMo(int idr) throws SQLException {
        String sql;
        PreparedStatement ps;
        double valor = 0.0;
        ResultSet rst;
        sql = "select sum(total) as totalMo\n"
                + "from manoobra\n"
                + " where idr = " + idr;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        if (rst.next()) {
            valor = redondear(rst.getDouble(1));
        }
        rst.close();
        return valor;
    }

    public double redondear(double numero) {
        int decimales = 2;
        return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }
}
