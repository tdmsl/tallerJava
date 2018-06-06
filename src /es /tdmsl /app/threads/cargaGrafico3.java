package es.tdmsl.app.threads;

import es.tdmsl.app.dao.EstadisticasDAO;
import es.tdmsl.app.ventanas.Formularios.FormEstadisticas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Manu on 12/04/2017.
 */
public class cargaGrafico3 extends Thread {

    @Override
    public void run() {
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("entando en threadGrafico3");
        EstadisticasDAO estadisticasDAO = null;
        try {
            estadisticasDAO = new EstadisticasDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // dataset.addValue(5.0, series3, category11);
        // create the chart...
        JFreeChart chart = null;

        try {
            chart = ChartFactory.createBarChart(
                    "Facturación meses - años", // chart title
                    "Años", // domain axis label
                    "Euros", // range axis label
                    estadisticasDAO.factTotMesesAños(), // data****************************************************************
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips?
                    false); // URLs?


            // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
            // set the background color for the chart...
            chart.setBackgroundPaint(FormEstadisticas.grafico3.getBackground());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setSize(FormEstadisticas.grafico3.getSize());
        FormEstadisticas.grafico3.removeAll();
        FormEstadisticas.grafico3.add(chartPanel);
        FormEstadisticas.grafico3.repaint();
        System.out.println("Hemos llegado al final del Thread grafico 3");
        FormEstadisticas.cronometroActivo=false;
        //////////////////////////////////

    }
}
