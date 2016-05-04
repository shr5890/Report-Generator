package others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class ListToHtmlTransformer  extends HttpServlet  {

    /**
     * @param collection
     *            of report titles.
     * @return string containing source code for HTML5.( java.util.Collection< String > reports )
     * @throws IOException 
     */
    public String render ( java.util.Collection< String > reports ) throws IOException{
        // This source code is not yet tested or made bullet-proof. Only meant for demonstrating concepts.
        // Warning: This code is not thread-safe. Changes must be made before serious use.
        // Warning: This code should be modified to produce proper HTML, such as escaping certain characters.
        // This code generates minimal HTML5 as suggested here: http://www.brucelawson.co.uk/2010/a-minimal-html5-document/
        // Big tip: Note that HTML allows the use of apostrophe (single-quote) in place of double-quote. Mixes better with Java source code.
        // The backslash + 'n' is an escape sequence in Java to generate a linefeed (Ascii/Unicode 10) for use here as a NewLine.
        // In real life, you woud test your rendered HTML with an HTML validator such as:
        // • http://html5.validator.nu/
        // • http://validator.w3.org/
        StringBuilder html = new StringBuilder();
        html.append( "<!doctype html>\n" );
        html.append( "<html lang='en'>\n" );

        html.append( "<head>\n" );
        html.append( "<meta charset='utf-8'>\n" );
        html.append( "<title>Report of Reports</title>\n" );
        html.append( "</head>\n\n" );

        html.append( "<body>\n" );
        html.append( "<h1>List of Reports</h1>\n" );
        // Make a list in HTML
        html.append( "<ul>\n" );
        // Loop the list of reports passed as argument.
        for ( String report : reports ) {
            html.append( "<li>" + report + "</li>\n" );
        }
        html.append( "</ul>\n" );
        html.append( "</body>\n\n" );

        html.append( "</html>" );                 		
//		int width = 500;
//		int height = 350;		
//		ChartUtilities.writeChartAsPNG( outputStream, chart, width, height);
//		ChartUtilities.saveChartAsPNG(file, chart, CHART_WIDTH, CHART_HEIGHT);
        JFreeChart chart = getChart();                  
//        html.append(chart);
        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
        final File file1 = new File("piechart100.png");
        ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

        // write an HTML page incorporating the image with an image map
        final File file2 = new File("piechart100.html");
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(file2));
        final PrintWriter writer = new PrintWriter(out);
        writer.println("<HTML>");
        writer.println("<HEAD><TITLE>JFreeChart Image Map Demo 2</TITLE></HEAD>");
        writer.println("<BODY>");
//        ChartUtilities.writeImageMap(writer, "chart", info);
        writer.println("<IMG SRC=\"piechart100.png\" "
                       + "WIDTH=\"600\" HEIGHT=\"400\" BORDER=\"0\" USEMAP=\"#chart\">");
        writer.println("</BODY>");
        writer.println("</HTML>");
        writer.close();
    
        return html.toString();
    }
    public void DrawChart(HttpServletRequest request, HttpServletResponse response) throws IOException {                     	
    	int width = 500;
    	int height = 350;		
    	//		ChartUtilities.writeChartAsPNG( outputStream, chart, width, height);
    	//		ChartUtilities.saveChartAsPNG(file, chart, CHART_WIDTH, CHART_HEIGHT);
    	JFreeChart chart = getChart();     
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ChartUtilities.writeChartAsPNG(bos, chart, width, height);

    	response.setContentType("image/png");
    	OutputStream out = new BufferedOutputStream(response.getOutputStream());
    	out.write(bos.toByteArray());
    	out.flush();
    	out.close();
//        html.append(chart);        
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
//    public static void main(String[] args) throws IOException{
//    	ListToHtmlTransformer LTHT =new ListToHtmlTransformer();
//    	LTHT.DrawChart(request, response);
//    }
}    