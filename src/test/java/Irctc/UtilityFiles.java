package Irctc;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityFiles {
	public String getCellValue(String filepath, int sheetIndex, int rowIndex, int cellIndex) throws IOException
	{
		//String path="C:\\Users\\2320850\\Desktop\\Testing\\Apache POI\\Book 2.xlsx";
		FileInputStream file=new FileInputStream(filepath);
		XSSFWorkbook workbook=new XSSFWorkbook(file); 
		XSSFSheet sheet=workbook.getSheetAt(sheetIndex);
		XSSFRow row=sheet.getRow(rowIndex);
		XSSFCell cell=row.getCell(cellIndex);
		
		String cellValue=cell.toString();
		workbook.close();
		file.close(); 
		return cellValue;
	}
}
