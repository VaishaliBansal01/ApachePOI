package org.example;

import org.example.utility.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class WebTableToExcel {
      static WebDriver driver;
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

        driver.get("https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population");

        String path = ".\\datafiles\\population.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        xlUtility.setCellData("Sheet1",0,0,"Country");
        xlUtility.setCellData("Sheet1",0,1,"Population");
        xlUtility.setCellData("Sheet1",0,2,"% of world");
        xlUtility.setCellData("Sheet1",0,3,"Date");
        xlUtility.setCellData("Sheet1",0,4,"Source");



       List<WebElement> table = driver.findElements(By.xpath(".//table[contains(@class,'wikitable')]/tbody/tr"));

//       driver.findElement(By.xpath("//tr")).size();
        int rows =  table.size();

        for(int r= 1; r<rows; r++)
        {


        }

    }
}
