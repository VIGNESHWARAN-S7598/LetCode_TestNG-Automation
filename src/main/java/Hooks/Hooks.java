package Hooks;

import Utilities.DriverUtilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;

import static Utilities.WaitUtilities.waitFor;

public class Hooks extends DriverUtilities {
    public static String coursePageURL;
    @FindBy(xpath="//a[@class='button is-info'][@title='Courses']")
    public static WebElement coursePage;
    @BeforeSuite
    public  void beforesuite() throws IOException {
        setDriver("Chrome");
       /* driver().get(config_File().getProperty("WebPageLink"));
        driver().manage().window().maximize();
        PageFactory.initElements(driver(),this);
        coursePage.click();
        coursePageURL=driver().getCurrentUrl();*/
    }
    @AfterSuite
    public void quitDriver() throws IOException {
        driver().quit();
    }
    @BeforeClass
    @Test
    public void beforeClass() throws IOException {
       // setDriver("Chrome");
        /*setDriver("Chrome");
        driver().get(config_File().getProperty("WebPageLink"));
        driver().manage().window().maximize();
        PageFactory.initElements(driver(),this);
        coursePage.click();
        coursePageURL=driver().getCurrentUrl();*/
    }
    @AfterClass
    @Test
    public void afterClass() throws IOException, InterruptedException {
        //driver().navigate().back();
       /* PageFactory.initElements(driver(),this);
        waitFor(4000);
        driver().get(config_File().getProperty("WebPageLink"));
        coursePage.click();
        waitFor(4000);*/
          /* String currentPage=driver().getWindowHandle();
     driver().switchTo().window(currentPage);
        System.out.println("After Class");*/

    }

}
