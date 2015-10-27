package pkg;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYSeriesCollection;

public class Calculadora {

    public static double EfetuarOperacoes(final String str) {
        Funcao f = new Funcao(str);
        return f.parse();
    }

    public static XYDataset createDataset(Double[] resultados) {
        XYSeries series1 = new XYSeries("Curva da função");

        series1.add(-5.0, resultados[0]);
        series1.add(-4.0, resultados[1]);
        series1.add(-3.0, resultados[2]);
        series1.add(-2.0, resultados[3]);
        series1.add(-1.0, resultados[4]);
        
        series1.add(0.0, resultados[5]);
        
        series1.add(1.0, resultados[6]);
        series1.add(2.0, resultados[7]);
        series1.add(3.0, resultados[8]);
        series1.add(4.0, resultados[9]);
        series1.add(5.0, resultados[10]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        return dataset;
    }

    public static JFreeChart plot(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Calculadora gráfica", // Title
                "X", // x-axis Label
                "Y", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        return chart;
    }

    public static void Export(JFreeChart chart) {
        try {
            ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
            System.out.println("Grafico gerado com sucesso");
        } catch (IOException e) 
        {
            System.err.println("Ocorreu um erro ao criar grafico: " + e.getMessage());
        }
    }

}
