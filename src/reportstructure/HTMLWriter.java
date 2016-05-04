package reportstructure;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import chartobjects.Charts;

public class HTMLWriter {
	public static void GenerateHTML(String CurrentDateandTime, String chartTitle, String[] arrchartSubjects, int[] arrchartSubjectValues) throws IOException {
		//***************** Creates a HTML File and Opens it *************************
		File FilePath = new File("D:\\ChartReport\\RUN_"+CurrentDateandTime);       
		FilePath.mkdir();
		File htmlFile = new File(FilePath+"\\Result.html");	        
		JFreeChart chart = Charts.Chart(chartTitle, arrchartSubjects, arrchartSubjectValues);                          
		final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		final File pngFile = new File(FilePath+"\\piechart.png");
		ChartUtilities.saveChartAsPNG(pngFile, chart, 600, 400, info);      
		final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
		final PrintWriter writer = new PrintWriter(out);
		writer.println("<HTML>");
		writer.println("<HEAD><TITLE>JFreeChart Image Map Demo 2</TITLE></HEAD>");
		writer.println("<BODY >");		
		writer.println("<IMG SRC=\"piechart.png\" "
				+ "WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");     		
		writer.println("</BODY>");
		writer.println("</HTML>");
		writer.close(); 
		Desktop.getDesktop().browse(htmlFile.toURI());	
	}
}