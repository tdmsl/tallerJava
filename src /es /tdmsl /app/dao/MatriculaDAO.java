/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

import es.tdmsl.app.beans.Matricula;
import es.tdmsl.app.ventanas.Listados.ListadoMatriculas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Manu
 */
public class MatriculaDAO extends DAO implements IMatriculaDAO {
    //private Matricula matricula;

    public MatriculaDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void grabar(Matricula matricula) throws SQLException {
        String sql = "insert into matriculas(matricula,idcliente,marca,modelo,bastidor,codigo,observacionesmatricula)"
                + "values(?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, matricula.getMatricula().toUpperCase());
        pstmt.setInt(2, matricula.getIdClientes());
        pstmt.setString(3, matricula.getMarca().toUpperCase());
        pstmt.setString(4, matricula.getModelo().toUpperCase());
        pstmt.setString(5, matricula.getBastidor().toUpperCase());
        pstmt.setString(6, matricula.getCodigo().toUpperCase());
        pstmt.setString(7, matricula.getObseMaticula().toUpperCase());
        //System.out.println(pstmt);
        int n = pstmt.executeUpdate();
        System.out.println("matricula grabada: " + n);
    }

    @Override
    public void modificar(Matricula matricula, String matriculaORG) throws SQLException {
       
        String sql = "UPDATE    matriculas SET matricula= ?,"
                + "idCliente = ?,"
                + "marca = ?,"
                + "modelo = ?,"
                + "bastidor = ?,"
                + "codigo = ?,"
                + "observacionesMatricula = ?"
                + " WHERE matricula = '" +  matriculaORG + "'";

        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        //matricula.setMatricula(ListadoMatriculas.matri.getText().toUpperCase());
        
        pstmt.setString(1, matricula.getMatricula().toUpperCase());
        pstmt.setInt(2, matricula.getIdClientes());
        pstmt.setString(3, matricula.getMarca().toUpperCase());
        pstmt.setString(4, matricula.getModelo().toUpperCase());
        pstmt.setString(5, matricula.getBastidor().toUpperCase());
        pstmt.setString(6, matricula.getCodigo().toUpperCase());
        pstmt.setString(7, matricula.getObseMaticula());
   System.out.println(pstmt);
        pstmt.executeUpdate();
    }

    @Override
    public void borrar(String matricula) throws SQLException {
        String sql = "DELETE FROM matriculas WHERE matricula = '" + matricula + "'";
        //System.out.println(sql);
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Override
    public Matricula cargarDatos(String matriculaParam) throws SQLException, ClassNotFoundException {
        String sql = "select * from MATRICULAS where matricula = '" + matriculaParam + "'";
        //System.out.println("sql matricula: "+sql);
        PreparedStatement ps;
        ResultSet rst;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        Matricula matricula = new Matricula();
        if (rst.next()) {
            matricula.setNueva(false);
            matricula.setMatricula(rst.getString("matricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));
            matricula.setMarca(rst.getString("marca"));
            matricula.setModelo(rst.getString("modelo"));
            matricula.setCodigo(rst.getString("codigo"));
            matricula.setBastidor(rst.getString("bastidor"));
            matricula.setObseMaticula(rst.getString("observacionesMatricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));

            
        } else {
            //JOptionPane.showMessageDialog(null, "No se han encontrado matriculas");
        }
    
        return matricula;
    }

    @Override
    public Vector<Vector<Object>> listar() throws SQLException, ClassNotFoundException {
        Vector<Vector<Object>> matriculas = new Vector<Vector<Object>>();
        String sql = "select * from matriculas order by matricula";
        PreparedStatement ps;
        ResultSet rst;
        Matricula matricula;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            matricula = new Matricula();
            matricula.setMatricula(rst.getString("matricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));
            matricula.setMarca(rst.getString("marca"));
            matricula.setModelo(rst.getString("modelo"));
            matricula.setBastidor(rst.getString("bastidor"));
            matricula.setCodigo(rst.getString("codigo"));
            matricula.setObseMaticula(rst.getString("observacionesmatricula"));
            matriculas.add(matricula.getVector());
        }
        return matriculas;
    }

    @Override
    public Vector<Vector<Object>> listarMatriculas(String matri) throws SQLException, ClassNotFoundException {

        Vector<Vector<Object>> matriculas = new Vector<Vector<Object>>();
        String sql = "select * from matriculas where matricula LIKE '" + matri.toUpperCase() + "%'"
                + "order by matricula" ;
        // System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        Matricula matricula;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            matricula = new Matricula();
            matricula.setMatricula(rst.getString("matricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));
            matricula.setMarca(rst.getString("marca"));
            matricula.setModelo("modelo");
            matricula.setBastidor(rst.getString("bastidor"));
            matricula.setCodigo(rst.getString("codigo"));
            matricula.setObseMaticula(rst.getString("observacionesmatricula"));
            matriculas.add(matricula.getVector());
        }
        return matriculas;
    }

    @Override
    public Vector<Vector<Object>> listarCliente(int idCliente) throws SQLException, ClassNotFoundException {

        Vector<Vector<Object>> matriculas = new Vector<Vector<Object>>();
        String sql = "select * from matriculas where idcliente = " + idCliente;
        //System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        Matricula matricula;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            matricula = new Matricula();
            matricula.setMatricula(rst.getString("matricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));
            matricula.setMarca(rst.getString("marca"));
            matricula.setModelo("modelo");
            matricula.setBastidor(rst.getString("bastidor"));
            matricula.setCodigo(rst.getString("codigo"));
            matricula.setObseMaticula(rst.getString("observacionesmatricula"));
            matriculas.add(matricula.getVector());
        }
        return matriculas;
    }

    @Override
    public Vector<Vector<Object>> listarMarcas(String marca) throws SQLException, ClassNotFoundException {
        Vector<Vector<Object>> matriculas = new Vector<Vector<Object>>();
        String sql = "select * from matriculas where marca like '" + marca + "'";
        System.out.println(sql);
        PreparedStatement ps;
        ResultSet rst;
        Matricula matricula;
        ps = con.prepareStatement(sql);
        rst = ps.executeQuery();
        while (rst.next()) {
            matricula = new Matricula();
            matricula.setMatricula(rst.getString("matricula"));
            matricula.setIdClientes(rst.getInt("idcliente"));
            matricula.setMarca(rst.getString("marca"));
            matricula.setModelo("modelo");
            matricula.setBastidor(rst.getString("bastidor"));
            matricula.setCodigo(rst.getString("codigo"));
            matricula.setObseMaticula(rst.getString("observacionesmatricula"));
            matriculas.add(matricula.getVector());
        }
        return matriculas;
    }

    @Override
    public Vector<Vector<Object>> listarCampoNombre(String campo, String nombre) throws SQLException, ClassNotFoundException {
        Vector<Vector<Object>> matriculas = new Vector<Vector<Object>>();
        String sql = "select * from matriculas "
                + "where " + campo + " like '" + nombre.toUpperCase() + "%'"
                + "order by " + campo;
        //System.out.println(sql);
        Statement stmt;
        ResultSet rst;
        Matricula m;
        stmt = con.createStatement();
        rst = stmt.executeQuery(sql);
        while (rst.next()) {
            m = new Matricula();
            m.setMatricula(rst.getString("matricula"));
            m.setIdClientes(rst.getInt("idCliente"));
            m.setMarca(rst.getString("marca"));
            m.setModelo(rst.getString("modelo"));
            m.setBastidor(rst.getString("bastidor"));
            m.setObseMaticula(rst.getString("observacionesMatricula"));

            matriculas.add(m.getVector());
        }

        return matriculas;
    }
}
