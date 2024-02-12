package datadriventesting;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.*;

public class XLUtility {

    FileInputStream fis;
    FileOutputStream fos;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;

    CellStyle style;
 String path = null;
   XLUtility(String path){
       this.path = path;

    }

    public int getRowCount(String sheetName) throws IOException {
      fis = new FileInputStream(path);
      workbook=new XSSFWorkbook(fis);
      sheet =  workbook.getSheet(sheetName);

      int rowCount = sheet.getLastRowNum();
      workbook.close();
      fis.close();

        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fis = new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet =  workbook.getSheet(sheetName);

        int cellCount = sheet.getRow(rownum).getLastCellNum();
        workbook.close();
        fis.close();

        return cellCount;
    }

public  String getCellData(String sheetName, int rownum, int colnum) throws IOException {
    fis = new FileInputStream(path);
    workbook=new XSSFWorkbook(fis);
    sheet =  workbook.getSheet(sheetName);
    row = sheet.getRow(rownum);
    cell= row.getCell(colnum);
     String data;
    DataFormatter formatter = new DataFormatter();
    try {
         data = formatter.formatCellValue(cell);
    }
    catch(Exception e)
    {
        data = " ";
    }

    workbook.close();

    fis.close();
    return data;
   }

   public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

       File xlfile = new File(path);
       if(!xlfile.exists())
       {
           workbook = new XSSFWorkbook();
           fos=new FileOutputStream(path);
           workbook.write(fos);
       }

//       fis = new FileInputStream(path);
//       workbook = new XSSFWorkbook(fis);
       fis = new FileInputStream(path);
       workbook=new XSSFWorkbook(fis);

       if(workbook.getSheetIndex(sheetName) == -1)
           workbook.createSheet(sheetName);
       sheet =  workbook.getSheet(sheetName);

       if(sheet.getRow(rownum)==null)
          sheet.createRow(rownum);
       row = sheet.getRow(rownum);

       cell= row.createCell(colnum);

       cell.setCellValue(data);

       fos= new FileOutputStream(path);
       workbook.write(fos);
       workbook.close();
       fis.close();
       fos.close();
   }






}
