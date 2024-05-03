package GUI;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;
import java.util.ArrayList;

public class Chart {
    public JPanel createChartPanel(ArrayList<String> cp, ArrayList<String> bs, ArrayList<Double> oprice) {
        JPanel panel = new JPanel();
        panel.setBounds(642, 173, 633, 292);

        XYSeries series = new XYSeries("Profit/Loss vs. Index Spot");

        double strikePrice = 22500;

        for (int i = 20500; i <= 25000; i += 50) { // Adjust the range as needed
            double pnl = Backend.calculateProfitLoss(strikePrice, i, cp, bs, oprice);
            series.add(i, pnl);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Profit/Loss vs. Index Spot", // Chart title
                "Index Spot",                  // X-axis label
                "Profit/Loss",                 // Y-axis label
                dataset,                       // Dataset
                PlotOrientation.VERTICAL,
                true,                          // Show legend
                true,                          // Show tooltips
                false                          // Show URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        return panel;
    }
}
