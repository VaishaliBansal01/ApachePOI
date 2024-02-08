package org.example.exceloperations;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingExcel {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Salary Info");

        Object empdata[][]= {
                {"Salay","Bonus","Total"},
                {"10000","100","10100"},
                {"20000","200","20200"},
                {"30000","300","30300"}
        };

        int rows = empdata.length;
        int cols = empdata[0].length;

        System.out.println(rows);
        System.out.println(cols);

        for(int r=0;r<rows; r++)
        {
            XSSFRow row =sheet.createRow(r);
            for(int c=0; c<cols; c++)
            {
                   XSSFCell cell = row.createCell(c);

                  Object value = empdata[r][c];
                  if(value instanceof String)
                      cell.setCellValue((String) value);
                  if(value instanceof Integer)
                      cell.setCellValue((Boolean) value);
                  if(value instanceof Boolean)
                      cell.setCellValue((Boolean) value);
            }
        }

 /*int row
        for(Object emp[] :empdata)
        {

        }*/
        String filePath = ".\\datafiles\\salary.xlsx";

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
workbook.write(fileOutputStream);

 fileOutputStream.close();

        System.out.println("salary.xlsx written successfully");


    }
}
