package PageObjects;

import Utilities.DriverUtilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static Utilities.FileUtilities.finishScreenshot;
import static Utilities.FileUtilities.screenShot;
import static Utilities.WebPageUtilities.openWebpage;

public class Table extends DriverUtilities {
    @FindBy(xpath = "//table[@id='shopping']/tbody/tr/td[2]")
    public static List<WebElement> prices;
    @FindBy(xpath = "//table[@id='shopping']/thead/tr/th")
    public static WebElement startOftheTable;

    @FindBy(xpath = "//table[@id='shopping']/tfoot/td[2]/b")
    public static WebElement totalPrice;

    @Test(priority = 1)
    public static void openTablePage() throws IOException {
        openWebpage("table");
        driver().manage().window().maximize();
    }
    @Test(priority = 2)
    public void verifyingTotalPrice() throws IOException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        int sum=0;
        Actions a=new Actions(driver());
        a.clickAndHold(startOftheTable).moveToElement(totalPrice).release().build().perform();
        screenShot("Table_Path","checking the prices of shopping table");
        for(WebElement we:prices){
            sum+=Integer.parseInt(we.getText());
        }
        if(sum==Integer.parseInt(totalPrice.getText())){
            System.out.println("total of all prices are matching");
        }
        else{
            System.out.println("total of all prices are not matching");
        }
        finishScreenshot();
    }
}
