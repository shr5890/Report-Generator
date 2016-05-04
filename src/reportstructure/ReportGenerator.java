package reportstructure;

import java.io.File;
import java.io.IOException;

import reportframe.Interface;

public class ReportGenerator {
	public static void main( String[] args ) throws IOException { 
		String chartTitle = " Automated Test Cases ";
		String[] arrchartSubjects = {"Inbound", "Inventory Management", "Outbound", "Extensions"};
		int[] arrchartSubjectValues = {61,45,93,31};
		String CurrentDateandTime = TimeGenerator.getCurrentDateAndTime();
		HTMLWriter.GenerateHTML(CurrentDateandTime, chartTitle, arrchartSubjects, arrchartSubjectValues);
//		File destFile = new File(destFile));
//		Interface.readExcelSheet(destFile);
	}
}