package es.tdmsl.app.dao;

import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.SQLException;

/**
 * Created by Manu on 30/03/2017.
 */
public interface IEstadisticasDAO {
    void desgloseMO()throws SQLException;
    void factOperarioMes()throws SQLException;
    DefaultCategoryDataset factTotMesesAños() throws SQLException;
}
