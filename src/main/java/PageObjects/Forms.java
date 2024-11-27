package PageObjects;

import Utilities.DriverUtilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static Utilities.ExcelUtilities.*;
import static Utilities.WaitUtilities.*;
import static Utilities.WebPageUtilities.maximizeWindow;
import static Utilities.WebPageUtilities.openWebpage;

public class Forms extends DriverUtilities {
    @FindBy(linkText = "All in One")
    public static WebElement formsPageLink;

    @FindBy(id = "firstname")
    public static WebElement firstNameInputField;
    @FindBy(id = "lasttname")
    public static WebElement lastNameInputField;
    @FindBy(id = "email")
    public static WebElement emailInputField;
    @FindBy(xpath = "//option[@data-countrycode or @disabled]")
    public static List<WebElement> countryCodes;
    //label[text()='Country code']/following::div[1]/div/select/option
    @Test(priority=6)
    public void openFormspage() throws IOException {
       // setDriver("Chrome");
        PageFactory.initElements(driver(),this);
        formsPageLink.click();
       /* openWebpage("Forms");
        maximizeWindow();*/
    }
    @Test(priority = 7)
    public void enteringDetails() throws IOException, InterruptedException {
        PageFactory.initElements(driver(),this);
        setExcel(config_File().getProperty("Forms"));
        for(int i=0;i<getData("FirstName").size();i++){
            firstNameInputField.sendKeys(getData("FirstName").get(i));
            waitFor(2000);
            lastNameInputField.sendKeys(getData("LastName").get(i));
            waitFor(2000);
            emailInputField.clear();
            emailInputField.sendKeys(getData("Email").get(i));
            waitFor(2000);
            for(WebElement we:countryCodes){
                if(we.getText().equalsIgnoreCase(getData("Country_Code").get(i))){
                    we.click();
                }
            }

        }
    }

}
