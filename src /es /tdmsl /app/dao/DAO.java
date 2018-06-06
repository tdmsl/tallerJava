/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tdmsl.app.dao;

/**
 *
 * @author Manu
 */
import java.sql.Connection;
import java.sql.SQLException;


public class DAO {
    
     protected Connection con;

    public DAO() throws SQLException,  ClassNotFoundException {
        con = ConexionBD.getConexionBD().getCon();
       
    }
    
   
    
       
}
