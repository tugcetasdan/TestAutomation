package TestAutomation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.xpath;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class LogInScenario {
    protected WebDriver driver;
    // url's that i have used during this project
    public static String loginUrl = "https://www.gittigidiyor.com/uye-girisi";
    protected static String url = "https://www.gittigidiyor.com/";
    @Before
    public void setUp() {

        try {
          //path of my chromedriver
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tuğçe\\IdeaProjects\\TestAutomation\\ChromeDriver\\chromedriver.exe");
            driver = new ChromeDriver(capabilities);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            //dynamic wait
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void login() {

        try {
            driver.get(url);
            Assert.assertEquals(driver.getTitle(), "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi");
            sleep(5000);
            System.out.println("Main page loaded successfully.");


            driver.get(loginUrl);

            driver.findElement(xpath("//input[@name='kullanici']")).sendKeys("tugcetestautomation@gmail.com");
            sleep(5000);

            driver.findElement(xpath("//input[@name='sifre']")).sendKeys("123tt123");
            sleep(5000);

            driver.findElement(By.id("gg-login-enter")).click();
            sleep(5000);
            System.out.println("Logged in successfully.");

            driver.findElement(xpath("//input[@name='k']")).sendKeys("bilgisayar");
            sleep(5000);

            driver.findElement(By.xpath("//span[.='BUL']")).click();
            sleep(5000);
            System.out.println("word 'bilgisayar' searched successfully.");
            //couldn't open the second  page
            /*driver.findElement(By.xpath("//div[@class='pager pt30 hidden-m gg-d-24']/a[@href='/arama/?k=bilgisayar&sf=2']")).getAttribute("href");
            sleep(5000);*/

            // I picked a random product
            driver.findElement(By.partialLinkText("2")).click();
            sleep(5000);


            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].click()", driver.findElement(By.id("add-to-basket")));
            sleep(5000);
            System.out.println("Added in to basket successfully.");

            String price1;
            price1 = driver.findElement(By.xpath("//div[@id='sp-price-lowPrice']")).getText();
            System.out.println(price1);

            driver.findElement(By.xpath("//span[.='Sepetim']")).click();
            System.out.println("Reached to the basket page successfully");
            sleep(5000);

            String price2;
            price2= driver.findElement(By.xpath("//div[@class='total-price']")).getText();
            System.out.println(price2);
            sleep(5000);
            if (price1==price2)
            {
                System.out.println("The price on the product page and the price of the product in the basket are the same.");
            }
            else
            {
                System.out.println("The price on the product page and the price of the product in the basket are not the same.");
            }

            Select se = new Select(driver.findElement(By.xpath("//*[@class='amount']")));
            se.selectByValue("2");
            System.out.println("2 pieces selected.");
            sleep(5000);

            driver.findElement(By.xpath(("//a[@title='Sil']"))).click();
            System.out.println("Deleted successfully.");
            sleep(5000);

            driver.quit();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {

    }
}
