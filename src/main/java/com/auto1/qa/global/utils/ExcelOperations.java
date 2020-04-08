package com.auto1.qa.global.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/***
 * This class provides instance of ExcelOperations for reading values from "RegistrationTestData.xlsx"
*/
public class ExcelOperations {

	static String filePath = System.getProperty("user.dir") + ConfigReader.getInstance().getProperty("REGISTRATION_TEST_DATA");
	
	/*
	 * This method reads whole row as a single dictionary record from excel sheet
	 * it takes two parameters "sheetName" sheet to refer and "currTestName" row to refer
	 */
	public static Dictionary<String, String> readRecordFromExcel(String sheetName, String currTestName) {

		FileInputStream fis = null;
		Dictionary <String, String> testDataRecord = null;

		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount =sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			Row matchedRow =null;

			int i;
			for( i=0; i<=rowCount; i++){
				matchedRow = sheet.getRow(i);
				Cell cell = matchedRow.getCell(0);

				try{

					String testName = cell.getStringCellValue();
					if(testName.contains(currTestName)){
						break;
					};

				}
				catch(NullPointerException e){

					e.printStackTrace();
				}

			}

			testDataRecord = new Hashtable<String, String>();

			for(int j=0 ;j <colCount; j++){
				try{
					testDataRecord.put(sheet.getRow(0).getCell(j).toString(), matchedRow.getCell(j).toString());
				}
				catch(NullPointerException e){
					DataFormatter df = new DataFormatter();
					String value = df.formatCellValue(matchedRow.getCell(j));
					testDataRecord.put(sheet.getRow(0).getCell(j).toString(), value);
					
				}
				catch(IllegalStateException e){    
					DataFormatter df = new DataFormatter();
					String value = df.formatCellValue(matchedRow.getCell(j));
					testDataRecord.put(sheet.getRow(0).getCell(j).toString(), value);
					
				}
			}

			fis.close();
			workbook.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}



		return testDataRecord;

	}
	
	//reading single cell
	public static String readFromExcel(String fileName) {

		FileInputStream fis;
		
		try {
			fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			Row row = sheet.getRow(1);
			Cell cell = row.getCell(1);
			fis.close();
			workbook.close();
			
			return cell.getStringCellValue();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;



	}


}