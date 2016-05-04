package others;
//package ReportStructure;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot3D;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.util.Rotation;
//import fr.aliacom.cms.services.AbstractServiceAction;
//public class HitsByModuleStatsGrapher
//extends AbstractServiceAction implements IGrapher {
//	public void writeGraph(OutputStream out, Date start, Date end) throws IOException, StatException {  
//		DefaultPieDataset dataset = createDataset(start,end);  
//		JFreeChart chart = createChart(dataset);
//
//		ChartUtilities.writeChartAsPNG(out, chart, 500, 400);
//		private JFreeChart createChart(DefaultPieDataset dataset)throws StatException {
//			String title = "Consultations par rubrique";
//			JFreeChart chart =
//					ChartFactory.createPieChart3D(title, dataset,true, false, false);
//			chart.setBackgroundPaint(java.awt.Color.white);
//			PiePlot3D plot = (PiePlot3D) chart.getPlot();
//			plot.setStartAngle(270);
//			plot.setDirection(Rotation.CLOCKWISE);
//			plot.setForegroundAlpha(0.5f);
//			plot.setNoDataMessage("Pas de données.");
//			plot.setLabelGenerator(new HitsByModuleLabelGenerator());
//			return chart;
//		}
//		private DefaultPieDataset createDataset(Date start, Date end) throws StatException {
//			String label;
//			int val;
//			DefaultPieDataset dataset = new DefaultPieDataset();
//
//			List l = StatHome.getInstance().getHitsByModule(start,end);
//			Iterator it = l.iterator();
//			Object[] tmp;
//			while (it.hasNext()) {
//				tmp = (Object[]) it.next();
//				label = (String) tmp[0];
//				val = ((Integer) tmp[1]).intValue();
//				dataset.setValue(label , val);
//				logger.debug("val:" + val + ", label" + label);
//			}
//			return dataset;
//		}
//	}