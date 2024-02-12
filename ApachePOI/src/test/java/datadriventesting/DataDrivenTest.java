package datadriventesting;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.Duration;

public class DataDrivenTest {

    WebDriver driver;
    @BeforeClass
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
         driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }

    @Test(dataProvider ="LoginData")
    public void loginTest(String user, String pwd, String exp) throws InterruptedException {
        driver.get("https://admin-demo.nopcommerce.com/login");
//        System.out.println(user+pwd+exp);
        Thread.sleep(1000);
        WebElement emailelement =driver.findElement(By.id("Email"));
        emailelement.clear();
        emailelement.sendKeys(user);


        //for selecting option from a drop-down
//        Select s = new Select(emailelement);
//        s.selectByVisibleText("option2");


        //dropdown is multi select dropdown or not
//        s.isMultiple();

//       Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//               .withTimeout(Duration.ofSeconds(10))
//               .pollingEvery(Duration.ofSeconds(1))
//               .ignoring(NoSuchElementException.class);
//
//       WebElement e = wait.until(driver1 -> {return driver.findElement(By.id(""));});





        WebElement passwordelement = driver.findElement(By.id("Password"));
        passwordelement.clear();
        passwordelement.sendKeys(pwd);

        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")).click();

        String expTitle = "Dashboard / nopCommerce administration";
        String actTitle = driver.getTitle();

        if(exp.equals("valid"))
        {
            if(expTitle.equals(actTitle))
            {
                Thread.sleep(1000);
                driver.findElement(By.linkText("Logout")).click();
                Assert.assertTrue(true);
            }
            else
            {
                Assert.assertTrue(false);
            }
        } else if (exp.equals("invalid")) {
            if(expTitle.equals(actTitle))
            {
                Thread.sleep(1000);

                driver.findElement(By.linkText("Logout")).click();
                Assert.assertTrue(false);
            }
            else
            {
                Assert.assertTrue(true);

            }

        }


    }

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
      /*String loginData[][]={
             {"admin@yourstore.com",	"admin",	"valid"},
        {"admin@yourstore.com","adm",	"invalid"},
        {"adm@yourstore.com",	"admin",	"invalid"},
        {"ad@yourstore.com",	"ad"	,"invalid"}
      };*/

        String path = ".\\datafiles\\logintestdata.xlsx";

        XLUtility xlutil = new XLUtility(path);

       int rows = xlutil.getRowCount("Sheet1");
      int cols =  xlutil.getCellCount("Sheet1",1);

      String logindata[][] = new String[rows][cols];


      for(int i=1; i<rows; i++ )
      {
          for(int j= 0; j<cols; j++)
          {
              logindata[i-1][j] = xlutil.getCellData("Sheet1",i,j);
          }
      }


        return logindata;
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }
}
