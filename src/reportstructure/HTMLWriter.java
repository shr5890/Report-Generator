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
		try{
		//***************** Creates a HTML File and Opens it *************************
		String myCurrentDir = System.getProperty("user.dir")
	            + File.separator
	            + System.getProperty("sun.java.command")
	                    .substring(0, System.getProperty("sun.java.command").lastIndexOf("."))
	                    .replace(".", File.separator);
	    System.out.println(myCurrentDir);
	    File fileToTest = new File(myCurrentDir);
	    String parentDir = fileToTest.getParent();
	    System.out.println(parentDir);
	    String outputDir = parentDir+"\\Output\\ChartReport\\RUN_"+CurrentDateandTime;
		File FilePath = new File(outputDir); 
		FilePath.mkdir();
		File htmlFile = new File(FilePath+"\\Result.html");	        
		JFreeChart chart = Charts.Chart(chartTitle, arrchartSubjects, arrchartSubjectValues);                          
		final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		final File pngFile = new File(FilePath+"\\piechart.png");
		ChartUtilities.saveChartAsPNG(pngFile, chart, 600, 400, info);      
		final OutputStream out = new BufferedOutputStream(new FileOutputStream(htmlFile));
		final PrintWriter writer = new PrintWriter(out);
		writer.println("<HTML>");
		writer.println("<HEAD>");
		writer.println("<style>" +
		".tv {"+
			  //"position: relative;"+
			  "width: 632px;"+
			  "height: 474px;"+
			  "margin: 20px 0;"+
			  //"background: blue;"+
			  "background: linear-gradient(to right, yellow , cyan);"+
			  "border-radius: 50% / 10%;"+
			  "color: white;"+
			  "text-align: center;"+
			  "text-indent: .1em;"+
			"}"+
			".tv:before {"+
			  "content: '';"+
			  //"position: absolute;"+
			  "top: 10%;"+
			  "bottom: 10%;"+
			  "right: -5%;"+
			  "left: -5%;"+
			  "background: inherit;"+
			  "border-radius: 5% / 50%;"+
		".chart {"+
			//"position: relative"+
		    "float: center;"+
		    "margin: 5px;"+		    		   
		    "border: 1px solid black;"+
		    "color: black;"+
		    "background-color: #728C00"+
		"}"+ 
		"</style>");
		writer.println("<TITLE>PIE Chart Report</TITLE></HEAD>");
		writer.println("<BODY bgcolor=\"#38ACEC\">");
		writer.println("<B><Big><center>Report Generator</center></Big></B>");
		writer.println("</BR>");
		writer.println("<center>");
		writer.println("<div class='tv'>");
		writer.println("</BR></BR></BR>");
		writer.println("<div class='chart'>");
		writer.println("<IMG SRC=\"piechart.png\" "
				+ "WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
		writer.println("</div>");
		writer.println("</div>");
		writer.println("</BR>");		
		writer.println("</BODY>");
		writer.println("</HTML>");
		writer.close(); 
		Desktop.getDesktop().browse(htmlFile.toURI());
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
}