package com.automation.mypreparation.utiities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static XSSFWorkbook xssfwb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;
	static int includeIndex;
	
	public static int getRowCount(String path, String sheetName) throws IOException
	{
		fis=new FileInputStream(new File(path));
		System.out.println(path);
		System.out.println(sheetName);
		xssfwb=new XSSFWorkbook(fis);
		sheet=xssfwb.getSheet(sheetName);
		int rowCount= sheet.getLastRowNum();
		xssfwb.close();
		fis.close();
		return rowCount;
	}
	
	public static int getColCount(String path, String sheetName) throws IOException
	{
		fis=new FileInputStream(new File(path));
		xssfwb=new XSSFWorkbook(fis);
		sheet=xssfwb.getSheet(sheetName);
		row= sheet.getRow(1);
		int colCount=row.getLastCellNum();
		xssfwb.close();
		fis.close();
		return colCount;
	}
	
	
	public static String[][] getExcelData(String path, String sheetName) throws IOException
	{
		fis=new FileInputStream(new File(path));
		xssfwb=new XSSFWorkbook(fis);
		sheet=xssfwb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		row= sheet.getRow(1);
		int colCount= row.getLastCellNum();
		
		int rowInd=0;
		int colInd=0;
		CellType type;
		
			for(int j=0;j<colCount;j++)
			{
				cell=sheet.getRow(0).getCell(j);
			    type=cell.getCellType();
			    if(type==CellType.STRING)
			    {
			    	if(cell.getStringCellValue().equalsIgnoreCase("include"))
			    	{
			    		includeIndex=j;
			    		for(int i=1;i<=rowCount;i++)
			    		{
			    			Boolean rowBool=false;
			    			cell=sheet.getRow(i).getCell(j);
			    			type=cell.getCellType();
			    			if(type==CellType.BOOLEAN)
			    			{
			    			rowBool=cell.getBooleanCellValue();
			    			}
			    			else if(type==CellType.STRING)
			    			{
			    				if(cell.getStringCellValue().equalsIgnoreCase("true"))
			    				{
			    					rowBool=true;
			    				}
			    			}
			    			if(rowBool==true)
			    			{
			    				rowInd++;
			    			}
			    			

			    		}
			    		break;
			    	}
			    }
			    
			}

			String[][] drivingTable=new String[rowInd][colCount-1];
			rowInd=0;
		for(int i=1;i<=rowCount;i++)
		{
		
			Boolean rowBool=false;
			cell = sheet.getRow(i).getCell(includeIndex);
			type=cell.getCellType();
			if(type==CellType.BOOLEAN)
			{
			rowBool=cell.getBooleanCellValue();
			}
			else if(type==CellType.STRING)
			{
				if(cell.getStringCellValue().equalsIgnoreCase("true"))
				{
					rowBool=true;
				}
			}
			
			if(rowBool==true)
			{
				colInd=0;
			for(int j=0;j<colCount;j++)
			{
			    if(j!=includeIndex)
			    {
			    DataFormatter df=new DataFormatter();
			    String str=df.formatCellValue(sheet.getRow(i).getCell(j));
			    System.out.println("Row "+rowInd+" Col "+colInd+ " value is "+str);
				drivingTable[rowInd][colInd]=str;
				colInd++;
			    }
			}
			rowInd++;
			}
		}
		
		xssfwb.close();
		fis.close();
		
		return drivingTable;
	}
	
	
	

}
