package org.example;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToDatabase {
    public static void main(String[] args) throws SQLException, IOException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");
        Statement st =con.createStatement();

        String sql = "CREATE TABLE Employeess(Emp_id VARCHAR(10) PRIMARY KEY , NAME VARCHAR (20), JOB VARCHAR(20))";
        st.execute(sql);

        //to read data from excel
        FileInputStream fis = new FileInputStream(".\\datafiles\\employee.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Employees Info");

        int rows = sheet.getLastRowNum();

        for(int r=1; r<rows; r++)
        {
            XSSFRow row=sheet.getRow(r);
            String id = row.getCell(0).getStringCellValue();
            String name =row.getCell(1).getStringCellValue();
           String job = row.getCell(2).getStringCellValue();

           sql= "insert into Employeess values ('"+id+"', '"+name+"', '"+job+"')";
           st.execute(sql);
           st.execute("commit");
        }

        workbook.close();
        fis.close();
        con.close();
    }



}
