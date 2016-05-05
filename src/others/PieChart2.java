package others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 
 * @author Basil Bourque
 *         © 2012 Basil Bourque. This source code may be used freely forever by anyone taking full responsibility for doing so.
 */
public class PieChart2 {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main( String[] args ) throws IOException {
    	PieChart2 PC2 = new PieChart2();
    	PC2.getChart();
    }
    public JFreeChart getChart() {
    	System.out.println(" Pie Chart");
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Ford", 23.3);
		dataset.setValue("Chevy", 32.4);
		dataset.setValue("Yugo", 44.2);

		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createPieChart("Cars", dataset, legend, tooltips, urls);

		chart.setBorderPaint(Color.GREEN);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);
		return chart;
	}
}