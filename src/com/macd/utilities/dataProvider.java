/**
 * 
 */
package com.macd.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

/**
 * @author sarvagya.singhal
 *
 */
public class dataProvider {
	
	@DataProvider(name = "addresstest")
	public String[][] getexceldata() throws IOException {
		String [][] data = new String[2][3];
		
		String excelpath= System.getProperty("user.dir") +"//src//com//macd//testdata//MacD.xlsx";
		FileInputStream input1=new FileInputStream(excelpath);
		 
		XSSFWorkbook workbook=new XSSFWorkbook(input1);
		XSSFSheet sheet=workbook.getSheet("Sheet3");
		int rowcount=sheet.getPhysicalNumberOfRows();
		 

		for(int i=0;i<2;i++)
		{
		 
		data[i][0]=sheet.getRow(i).getCell(0).getStringCellValue();
		System.out.println(data[i][0]);
		data[i][1]= String.valueOf(sheet.getRow(i).getCell(1).getRawValue());
		System.out.println(data[i][1]);
		data[i][2]=sheet.getRow(i).getCell(2).getStringCellValue();
		System.out.println(data[i][2]);
	}
		return data;
	}
	
	@DataProvider(name = "Address")
	public String[][]getexceldata1() throws IOException {
		String [][] data = new String[3][2];
		
		String excelpath= System.getProperty("user.dir") +"//src//com//macd//testdata//MacD.xlsx";
		FileInputStream input1=new FileInputStream(excelpath);
		 
		XSSFWorkbook workbook=new XSSFWorkbook(input1);
		XSSFSheet sheet=workbook.getSheet("Sheet4");
		int rowcount=sheet.getPhysicalNumberOfRows();
		 

		System.out.println(rowcount);
		for(int i=0;i<rowcount;i++)
		{
		 
		data[i][0]=sheet.getRow(i).getCell(0).getStringCellValue();
		System.out.println(data[i][0]);
		data[i][1] = sheet.getRow(i).getCell(1).getStringCellValue();
		System.out.println(data[i][1]);
	}
		return data;
	}
	
	@DataProvider(name = "foodItem")
	public String[]getexceldata2() throws IOException {
		String [] data = new String[1];
		
		String excelpath= System.getProperty("user.dir") +"//src//com//macd//testdata//MacD.xlsx";
		FileInputStream input1=new FileInputStream(excelpath);
		 
		XSSFWorkbook workbook=new XSSFWorkbook(input1);
		XSSFSheet sheet=workbook.getSheet("Sheet5");
		int rowcount=sheet.getPhysicalNumberOfRows();
		 

		System.out.println(rowcount);
		for(int i=0;i<rowcount;i++)
		{
		 
		data[i]=sheet.getRow(i).getCell(0).getStringCellValue();
		System.out.println(data[i]);
		
	}
		return data;
	}
	
	
	@DataProvider(name = "invaliditem")
	public String[][]getexceldata3() throws IOException {
		String [][] data = new String[2][3];
		
		String excelpath= System.getProperty("user.dir") +"//src//com//macd//testdata//MacD.xlsx";
		FileInputStream input1=new FileInputStream(excelpath);
		 
		XSSFWorkbook workbook=new XSSFWorkbook(input1);
		XSSFSheet sheet=workbook.getSheet("Sheet6");
		int rowcount=sheet.getPhysicalNumberOfRows();
		 

		System.out.println(rowcount);
		for(int i=0;i<rowcount;i++)
		{
		 
		data[i][0]=sheet.getRow(i).getCell(0).getStringCellValue();
		System.out.println(data[i][0]);
		data[i][1]= String.valueOf(sheet.getRow(i).getCell(1).getRawValue());
		System.out.println(data[i][1]);
		data[i][2]=sheet.getRow(i).getCell(2).getStringCellValue();
		System.out.println(data[i][2]);
	}
		return data;
	}
	
}

