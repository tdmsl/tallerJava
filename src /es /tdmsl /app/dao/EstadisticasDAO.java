package es.tdmsl.app.dao;

import es.tdmsl.app.utilidades.Util;
import es.tdmsl.app.ventanas.Formularios.FormEstadisticas;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Manu on 30/03/2017.
 */
public class EstadisticasDAO extends DAO implements IEstadisticasDAO {

    public EstadisticasDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public void desgloseMO() throws SQLException {

    }

    @Override
    public void factOperarioMes() throws SQLException {

    }

    @Override
    public DefaultCategoryDataset factTotMesesAños() throws SQLException {
        //Date fechaInicioA = new Date(new Date().getTime() - 94670700000L);
        Date fechaInicioA = sumarRestarDiasFecha(new Date(),-360);
        //Date fechaFinalA = new Date();
        java.sql.Date date1 = Util.dateUtiltodateSQL(fechaInicioA);
        //java.sql.Date date2 = Util.dateUtiltodateSQL(fechaFinalA);
        //JOptionPane.showMessageDialog(null,años);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //
       /* SELECT YEAR(REPARACIONES.FECHASALIDA) AS AÑO,
                MONTH(REPARACIONES.FECHASALIDA) AS MES,
        sum( SELECT COALESCE (sum(MATERIALES.TOTAL),0)
                from MATERIALES
                WHERE MATERIALES.IDR = REPARACIONES.IDR) as TOTAL_MAT,
        SUM( SELECT COALESCE (sum(TRABAJOSEXT.PVP),0)
                from TRABAJOSEXT
                where TRABAJOSEXT.IDR = REPARACIONES.IDR) AS TOTAL_EXT,
        SUM( SELECT COALESCE (sum(MANOOBRA.TOTAL),0)
                from MANOOBRA
                WHERE MANOOBRA.IDR = REPARACIONES.IDR) AS TOTAL_MO
        FROM REPARACIONES
        WHERE REPARACIONES.FECHASALIDA IS NOT NULL
        and REPARACIONES.FECHASALIDA < CURRENT_TIMESTAMP()
        GROUP BY YEAR(REPARACIONES.FECHASALIDA),
                MONTH(REPARACIONES.FECHASALIDA)
        ORDER BY MES*/
        //
        String sql ="SELECT YEAR(REPARACIONES.FECHASALIDA) AS AÑO,MONTH(REPARACIONES.FECHASALIDA) AS MES,\n" +
                "  sum( SELECT COALESCE (sum(MATERIALES.TOTAL),0)from MATERIALES WHERE MATERIALES.IDR = REPARACIONES.IDR) as TOTAL_MAT,SUM( SELECT COALESCE (sum(TRABAJOSEXT.PVP),0)from TRABAJOSEXT\n" +
                "  where TRABAJOSEXT.IDR = REPARACIONES.IDR) AS TOTAL_EXT,SUM( SELECT COALESCE (sum(MANOOBRA.TOTAL),0)from MANOOBRA\n" +
                "  WHERE MANOOBRA.IDR = REPARACIONES.IDR) AS TOTAL_MO\n" +
                "  FROM REPARACIONES\n" +
                "  WHERE REPARACIONES.FECHASALIDA IS NOT NULL\n" +
                "  and REPARACIONES.FECHASALIDA between '2016-1-1' and CURRENT_TIMESTAMP()\n" +
                "  GROUP BY YEAR(REPARACIONES.FECHASALIDA),\n" +
                "  MONTH(REPARACIONES.FECHASALIDA)\n" +
                "ORDER BY MES,AÑO ";
        /*String sql = "SELECT "
                + "YEAR(REPARACIONES.FECHASALIDA) AS AÑO,"
                + "MONTH(REPARACIONES.FECHASALIDA) AS MES),"
                + "  sum( SELECT COALESCE (sum(MATERIALES.TOTAL),0)"
                + "  from MATERIALES "
                + "  WHERE MATERIALES.IDR = REPARACIONES.IDR) as TOTAL_MAT,  "
                + "  SUM( SELECT COALESCE (sum(TRABAJOSEXT.PVP),0)"
                + "  from TRABAJOSEXT"
                + "  where TRABAJOSEXT.IDR = REPARACIONES.IDR) AS TOTAL_EXT,"
                + "  SUM( SELECT COALESCE (sum(MANOOBRA.TOTAL),0)"
                + "   from MANOOBRA"
                + "  WHERE MANOOBRA.IDR = REPARACIONES.IDR) AS TOTAL_MO"
                + "  FROM REPARACIONES "
                + "WHERE REPARACIONES.FECHASALIDA IS NOT NULL  "
                + "and REPARACIONES.FECHASALIDA between '2016-1-1' and CURRENT_TIMESTAMP()"
                + " GROUP BY YEAR(REPARACIONES.FECHASALIDA),"
                + "MONTH(REPARACIONES.FECHASALIDA)"
                + "ORDER BY MES,AÑO  ";*/
        //System.out.println(sql);

        try {

            PreparedStatement ps;
             ps = con.prepareStatement(sql);
            ResultSet rst;
            rst = ps.executeQuery();
            String[] meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

            while (rst.next()) {
                String año = rst.getString("AÑO");
                int mes = rst.getInt("MES");
                double total = (rst.getDouble("TOTAL_MAT") + rst.getDouble("TOTAL_EXT") + rst.getDouble("TOTAL_MO"));
                total = redondear(total);
                //dataset.addValue(total, año,mes);
                dataset.addValue(total, año, meses[mes - 1]);
                //System.out.println(mes);
                //System.out.println("Parametros dataset  " + total + "," + año + "," + meses[mes - 1]);
            }
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormEstadisticas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;

    }

    public double redondear(double numero) {
        int decimales = 2;
        return Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias){// Suma los días recibidos a la fecha
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }
}
