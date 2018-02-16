package com.yash.poc.configuration;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author siddiqui.mahboob This class helps user to read the data from excel
 *         file
 *
 */
public class ExcelReader {
	Logger log = Logger.getLogger(ExcelReader.class);
	public String cellValue;
	String filePath = "Test_Data\\Test_Dataset_Keyword_Collection.xlsx";

	/**
	 * @param rowID
	 *            this parameter is row of excel file
	 * @param columnID
	 *            this parameter is column of excel file
	 * @return this method will return string value.
	 */
	public String readCell(int rowID, int columnID) {
		try {
			File sourceFile = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			cellValue = sheet.getRow(rowID).getCell(columnID).getStringCellValue();
		} catch (Exception exception) {
			log.error("File Not found in given location" + exception.getMessage());
		}
		
		return cellValue;
	}
}
