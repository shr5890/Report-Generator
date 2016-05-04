package reportframe;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Interface{   
public static void readExcelSheet(String destFile) throws BiffException, IOException{						
		try {
			Workbook wb = Workbook.getWorkbook(new File(destFile));			
			System.out.println(wb.getNumberOfSheets());

			Thread.sleep(4000);
		           
				Sheet sheet = wb.getSheet(0);
				int columns = sheet.getColumns();
				int rows = sheet.getRows();
				Cell cell = sheet.getCell(0,0);

				String strcellval=cell.getContents();
			}
		 catch(Exception ioe) {
				ioe.printStackTrace();
			}
		}	
}