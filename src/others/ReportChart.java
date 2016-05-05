package others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

@SuppressWarnings("serial")
public class ReportChart extends JFrame{
	public JFreeChart Chart(String chartTitle) {
		// This will create the dataset 
		PieDataset dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		setContentPane(chartPanel);
		return chart;
	}

    /**
     * @param collection
     *            of report titles.
     * @throws IOException 
     */
    public void render ( ) throws IOException{                      			
        JFreeChart chart = Chart("Chart Title");                          
        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        final File file1 = new File("C:\\Users\\A553450\\Documents\\Chart\\piechart100.png");
        ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);         
        final File file2 = new File("C:\\Users\\A553450\\Documents\\Chart\\piechart100.html");
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(file2));
        final PrintWriter writer = new PrintWriter(out);
        writer.println("<HTML>");
        writer.println("<HEAD><TITLE>JFreeChart Image Map Demo 2</TITLE></HEAD>");
        writer.println("<BODY>");        
        writer.println("<IMG SRC=\"piechart100.png\" "
                       + "WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
        writer.println("</BODY>");
        writer.println("</HTML>");
        writer.close(); 
        Desktop.getDesktop().browse(file2.toURI());	
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

	/**
	 * Creates a sample dataset 
	 */

	private  PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Linux", 33);
		result.setValue("Mac", 45);
		result.setValue("Windows", 162);
		return result;
	}


	/**
	 * Creates a chart
	 */

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
				dataset,                // data
				true,                   // include legend
				true,
				false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}
    public static void main(String[] args) throws IOException{
    	ReportChart RC =new ReportChart();
    	RC.render();
    }
}    