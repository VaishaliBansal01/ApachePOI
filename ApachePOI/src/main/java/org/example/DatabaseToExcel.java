package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class DatabaseToExcel {

    public static void main(String[] args) throws SQLException, IOException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");

         Statement st =con.createStatement();
          ResultSet rs  =st.executeQuery("Select * from customer");

        XSSFWorkbook workbook = new XSSFWorkbook();
       XSSFSheet sheet = workbook.createSheet("customerdata");

       XSSFRow row = sheet.createRow(0);
       row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("city");
        row.createCell(2).setCellValue("name");

        int r= 1;
        while(rs.next())
        {
          int id =  rs.getInt("id");
             String city=rs.getString("city");
            String name = rs.getString("name");

           row =sheet.createRow(r++);

           row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(city);
            row.createCell(2).setCellValue(name);
        }

        FileOutputStream fos = new FileOutputStream(".\\datafiles\\customer.xlsx");
        workbook.write(fos);
        workbook.close();
        con.close();

    }
}
