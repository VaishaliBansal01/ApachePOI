package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashmapToExcel {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("student_data");

        Map<String, String> map = new HashMap<String, String>();
        map.put("10","john");
        map.put("20","abc");
        map.put("30","xyz");
        map.put("40","bn");
        map.put("50","kl");

        int rownum = 0;
        for(Map.Entry entry : map.entrySet())
        {
         XSSFRow row = sheet.createRow(rownum++);
         row.createCell(0).setCellValue((String) entry.getKey());
         row.createCell(1).setCellValue((String) entry.getValue());
        }
        FileOutputStream fos = new FileOutputStream(".\\datafiles\\student.xlsx");
        workbook.write(fos);
        fos.close();
        System.out.println("file created");

    }
}
