package others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

@SuppressWarnings("serial")
public class PC5  extends HttpServlet  { 
	public void DrawChart(HttpServlet request, HttpServletResponse response) throws IOException {  
		response.setContentType("text/html");
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
//		BufferedWriter BIF = new BufferedWriter();
    	int width = 500;
    	int height = 450;		
//    			ChartUtilities.writeChartAsPNG( outputStream, chart, width, height);
//    			ChartUtilities.saveChartAsPNG(file, chart, CHART_WIDTH, CHART_HEIGHT);
    	JFreeChart chart = getChart();     
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ChartUtilities.writeChartAsPNG(bos, chart, width, height);

//    	response.setContentType("image/png");
    	
    	out.write(bos.toByteArray());
    	out.flush();
    	out.close();
//        html.append(chart);        
    }
    public JFreeChart getChart() {
    	System.out.println("Pie Chart");
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