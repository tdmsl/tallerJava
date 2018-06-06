/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Personalizacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Manu
 */
public class PersonalizacionDAO extends DAO implements IPersonalizacionDAO {

    public PersonalizacionDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void grabar(Personalizacion personalizacion) throws SQLException {
        String sql = "insert into personalizacion(nombreempresa,cif,direccion,codigopostal,ciudad,telefono,email,iva,valormo,notas,logo,regind,regesp,cuentabanco,textoorden,textopresupuesto,textofactura,rutainformes,rutaimgfondo,rutaagenda,nfactura)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, personalizacion.getNombreEmpresa());
        pstmt.setString(2, personalizacion.getCif());
        pstmt.setString(3, personalizacion.getDireccion());
        pstmt.setString(4, personalizacion.getCodigoPostal());
        pstmt.setString(5, personalizacion.getCiudad());
        pstmt.setString(6, personalizacion.getTelefono());
        pstmt.setString(7, personalizacion.geteMail());
        pstmt.setInt(8, personalizacion.getIva());
        pstmt.setInt(9, personalizacion.getValorMO());
        pstmt.setString(10, personalizacion.getNotas());
        pstmt.setString(11, personalizacion.getLogo());
        pstmt.setString(12, personalizacion.getRegInd());
        pstmt.setString(13, personalizacion.getRegEsp());
        pstmt.setString(14, personalizacion.getCuentaBanco());
        pstmt.setString(15, personalizacion.getTextoOrden());
        pstmt.setString(16, personalizacion.getTextoPresupuesto());
        pstmt.setString(17, personalizacion.getTextoFactura());
        pstmt.setString(18, personalizacion.getRutaInformes());
        pstmt.setString(19, personalizacion.getRutaImgFondo());
        pstmt.setString(20, personalizacion.getRutaAgenda());
        pstmt.setInt(21, personalizacion.getnFactura());

        System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void modificar(Personalizacion personalizacion) throws SQLException {
        //JOptionPane.showMessageDialog(null, "estoy actualizando");
        String sql = "UPDATE    personalizacion SET id= ?,"
                + "NombreEmpresa = ?,"
                + "cif = ?,"
                + "direccion = ?,"
                + "codigopostal = ?,"
                + "ciudad = ?,"
                + "telefono = ?,"
                + "email = ?,"
                + "iva = ?,"
                + "valormo = ?,"
                + "notas = ?,"
                + "logo = ?,"
                + "regind = ?,"
                + "regesp= ?,"
                + "cuentabanco = ?,"
                + "textoorden = ?,"
                + "textopresupuesto = ?,"
                + "textofactura = ?,"
                + "rutainformes = ?,"
                + "rutaimgfondo = ?,"
                + "rutaagenda = ?,"
                + "rutabanco = ?,"
                + "rutaexcel = ?,"
                + "rutadrive = ?,"
                + "rutacorreo = ?,"
                + "nfactura = ?,"
                + "lookandfeel = ?"
                + " WHERE id = " + personalizacion.getId();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, personalizacion.getId());
        pstmt.setString(2, personalizacion.getNombreEmpresa());
        pstmt.setString(3, personalizacion.getCif());
        pstmt.setString(4, personalizacion.getDireccion());
        pstmt.setString(5, personalizacion.getCodigoPostal());
        pstmt.setString(6, personalizacion.getCiudad());
        pstmt.setString(7, personalizacion.getTelefono());
        pstmt.setString(8, personalizacion.geteMail());
        pstmt.setInt(9, personalizacion.getIva());
        pstmt.setInt(10, personalizacion.getValorMO());
        pstmt.setString(11, personalizacion.getNotas());
        pstmt.setString(12, personalizacion.getLogo());
        pstmt.setString(13, personalizacion.getRegInd());
        pstmt.setString(14, personalizacion.getRegEsp());
        pstmt.setString(15, personalizacion.getCuentaBanco());
        pstmt.setString(16, personalizacion.getTextoOrden());
        pstmt.setString(17, personalizacion.getTextoPresupuesto());
        pstmt.setString(18, personalizacion.getTextoFactura());
        pstmt.setString(19, personalizacion.getRutaInformes());
        pstmt.setString(20, personalizacion.getRutaImgFondo());
        pstmt.setString(21, personalizacion.getRutaAgenda());
        pstmt.setString(22, personalizacion.getRutaBanco());
        pstmt.setString(23, personalizacion.getRutaExcel());
        pstmt.setString(24, personalizacion.getRutaDrive());
        pstmt.setString(25, personalizacion.getRutaCorreo());
        pstmt.setInt(26, personalizacion.getnFactura());
        pstmt.setString(27, personalizacion.getLookandfeel());
        
        System.out.println(pstmt);
        pstmt.executeUpdate();

    }

    @Override
    public void borrar(int id) throws SQLException {
        String sql = "DELETE FROM personalizacion WHERE id = '" + id + "'";
        System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        System.out.println("Borrado Personalizacion");
    }

    @Override
    public Personalizacion cargarDatos(int id) throws SQLException {
        String sql = "select * from personalizacion";
        PreparedStatement ps;
        ResultSet rst;
        Personalizacion personalizacion;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        personalizacion = new Personalizacion();

        if (rst.next()) {
            personalizacion.setId(rst.getInt("id"));
            personalizacion.setNombreEmpresa(rst.getString("nombreempresa"));
            personalizacion.setCif(rst.getString("cif"));
            personalizacion.setDireccion(rst.getString("direccion"));
            personalizacion.setCodigoPostal(rst.getString("codigopostal"));
            personalizacion.setCiudad(rst.getString("ciudad"));
            personalizacion.setTelefono(rst.getString("telefono"));
            personalizacion.seteMail(rst.getString("email"));
            personalizacion.setIva(rst.getInt("iva"));
            personalizacion.setValorMO(rst.getInt("valormo"));
            personalizacion.setNotas(rst.getString("notas"));
            personalizacion.setLogo(rst.getString("logo"));
            personalizacion.setRegInd(rst.getString("regind"));
            personalizacion.setRegEsp(rst.getString("regesp"));
            personalizacion.setCuentaBanco(rst.getString("cuentabanco"));
            personalizacion.setTextoOrden(rst.getString("textoorden"));
            personalizacion.setTextoPresupuesto(rst.getString("textopresupuesto"));
            personalizacion.setTextoFactura(rst.getString("textofactura"));
            personalizacion.setRutaInformes(rst.getString("rutainformes"));
            personalizacion.setRutaImgFondo(rst.getString("rutaimgfondo"));
            personalizacion.setRutaAgenda(rst.getString("rutaagenda"));
            personalizacion.setRutaBanco(rst.getString("rutabanco"));
            personalizacion.setRutaExcel(rst.getString("rutaexcel"));
            personalizacion.setRutaDrive(rst.getString("rutadrive"));
            personalizacion.setRutaCorreo(rst.getString("rutacorreo"));
            personalizacion.setnFactura(rst.getInt("nfactura"));
            personalizacion.setLookandfeel(rst.getString("lookandfeel"));

        } else {
            System.out.println("rst vacio");
        }


        return personalizacion;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException {
        Vector<Vector<Object>> personalizaciones = new Vector<Vector<Object>>();
        String sql = "select * from personalizacion";
        PreparedStatement ps;
        ResultSet rst;
        Personalizacion personalizacion;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();

        while (rst.next()) {
            personalizacion = new Personalizacion();
            personalizacion.setId(rst.getInt("id"));
            personalizacion.setNombreEmpresa(rst.getString("nombreempresa"));
            personalizacion.setCif(rst.getString("cif"));
            personalizacion.setDireccion(rst.getString("direccion"));
            personalizacion.setCiudad(rst.getString("codigopostal"));
            personalizacion.setCiudad(rst.getString("ciudad"));
            personalizacion.setTelefono(rst.getString("telefono"));
            personalizacion.seteMail(rst.getString("email"));
            personalizacion.setIva(rst.getInt("iva"));
            personalizacion.setValorMO(rst.getInt("valormo"));
            personalizacion.setNotas(rst.getString("notas"));
            personalizacion.setLogo(rst.getString("logo"));
            personalizacion.setRegInd(rst.getString("regind"));
            personalizacion.setRegEsp(rst.getString("regesp"));
            personalizacion.setCuentaBanco(rst.getString("cuentabanco"));
            personalizacion.setTextoOrden(rst.getString("textoorden"));
            personalizacion.setTextoPresupuesto(rst.getString("textopresupuesto"));
            personalizacion.setTextoFactura(rst.getString("textofactura"));
            personalizacion.setRutaInformes(rst.getString("rutainformes"));
            personalizacion.setRutaImgFondo(rst.getString("rutaimgfondo"));
            personalizacion.setRutaAgenda(rst.getString("rutaagenda"));
            personalizacion.setnFactura(rst.getInt("nfactura"));

            personalizaciones.add(personalizacion.getVector());

        }
        return personalizaciones;
    }

    @Override
    public Vector<Vector<Object>> listar(int idp) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector<Vector<Object>> listar(String nombre) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
