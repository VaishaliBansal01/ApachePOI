package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import static java.lang.constant.ConstantDescs.NULL;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        String excelFilePath = ".//datafiles//SuperStoreUS-2015.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet =workbook.getSheetAt(0);

       int row = sheet.getLastRowNum();
//       System.out.println(row);

       int cols = sheet.getRow(1).getLastCellNum();
//        System.out.println(cols);

       /* for(int r=0; r<row; r++)
        {
           XSSFRow xssfRow = sheet.getRow(r);
            for(int c=0; c<cols; c++)
            {
              XSSFCell cell = xssfRow.getCell(c);

              switch(cell.getCellType())
              {
                  case STRING: System.out.println(cell.getStringCellValue()); break;
                  case NUMERIC:
                      System.out.println(cell.getNumericCellValue()); break;

               case BOOLEAN:
                      System.out.println(cell.getBooleanCellValue()); break;
              }
                System.out.print("  |  ");
            }

            System.out.println();
        }*/


        Iterator iterator = sheet.iterator();
        while(iterator.hasNext())
        {
            XSSFRow row1 = (XSSFRow) iterator.next();
            Iterator cellIterator = row1.cellIterator();
            while(cellIterator.hasNext())
            {
                XSSFCell cell = (XSSFCell) cellIterator.next();
                switch(cell.getCellType())
                {
                    case STRING: System.out.print(cell.getStringCellValue()); break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue()); break;

                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue()); break;
                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }
}