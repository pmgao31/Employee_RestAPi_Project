package com.employee.utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;

	public static int getRowCount(String XLfilepath, String xlsheet) throws Exception {
		fi = new FileInputStream(XLfilepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;

	}	public static int getCellCount(String XLfilepath, String xlsheet, int rowNum) throws Exception {
		fi = new FileInputStream(XLfilepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rowNum);
		int  cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;

	}

	public static String getCellData(String XLfilepath, String xlsheet, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(XLfilepath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rowNum);
		String celldata=row.getCell(colNum).getStringCellValue();

		return celldata;

	}

}
