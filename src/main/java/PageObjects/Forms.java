package PageObjects;

import Utilities.DriverUtilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static Utilities.ExcelUtilities.*;
import static Utilities.FileUtilities.*;
import static Utilities.WaitUtilities.*;
import static Utilities.WebPageUtilities.*;

public class Forms extends DriverUtilities {
    @FindBy(linkText = "All in One")
    public static WebElement formsPageLink;

    @FindBy(id = "firstname")
    public static WebElement firstNameInputField;
    @FindBy(id = "lasttname")
    public static WebElement lastNameInputField;
    @FindBy(id = "email")
    public static WebElement emailInputField;
    @FindBy(id = "Phno")
    public static WebElement phoneNoInputField;
    @FindBy(id = "Addl1")
    public static WebElement addressLine1InputField;
    @FindBy(id = "Addl2")
    public static WebElement addressLine2InputField;
    @FindBy(id = "state")
    public static WebElement stateInputField;
    @FindBy(id = "postalcode")
    public static WebElement postalCodeInputField;
    @FindBy(xpath = "//label[@id='country']/following::div[@class='control'][1]/div/select")
    public static WebElement countries;
    @FindBy(xpath = "//label[@class='radio']")
    public static List<WebElement> genders;

    //label[@id='country']/following::div[@class='control'][1]/div/select
    @FindBy(xpath = "//option[@data-countrycode or @disabled]")
    public static List<WebElement> countryCodes;
    @FindBy(xpath = "//input[@type='checkbox']")
    public static WebElement termsAndConditions;
    @FindBy(xpath = "//input[@type='date']")
    public static WebElement DOBInputField;
    @FindBy(xpath = "//input[@type='submit']")
    public static WebElement submitButton;
    //label[text()='Country code']/following::div[1]/div/select/option
    @Test(priority=6)
    public void openFormspage() throws IOException {
       // setDriver("Chrome");
        PageFactory.initElements(driver(),this);
        //formsPageLink.click();
        openWebpage("Forms");
        maximizeWindow();
    }
    @Test(priority = 7)
    public void enteringDetails() throws IOException, InterruptedException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        setExcel(config_File().getProperty("Forms"));
        Select countryList=new Select(countries);
        for(int i=0;i<getData("FirstName").size();i++){
            firstNameInputField.sendKeys(getData("FirstName").get(i));
            screenShot("Forms_Path","Entering the first name");
            waitFor(2000);
            lastNameInputField.sendKeys(getData("LastName").get(i));
            screenShot("Forms_Path","Entering the last name");
            waitFor(2000);
            emailInputField.clear();
            emailInputField.sendKeys(getData("Email").get(i));
            screenShot("Forms_Path","Entering the email");
            waitFor(2000);
            for(WebElement we:countryCodes){
                if(we.getText().equalsIgnoreCase(getData("Country_Code").get(i))){
                    we.click();
                }
            }
            screenShot("Forms_Path","selecting the Country Code");
            phoneNoInputField.sendKeys(getData("Phone no").get(i));
            screenShot("Forms_Path","Entering the Phone no");
            waitFor(2000);
            addressLine1InputField.sendKeys(getData("Address Line1").get(i));
            waitFor(2000);
            addressLine2InputField.sendKeys(getData("Address Line2").get(i));
            waitFor(2000);
            stateInputField.sendKeys(getData("State").get(i));
            waitFor(2000);
            postalCodeInputField.sendKeys(getData("Postal_Code").get(i));
            waitFor(2000);
            countryList.selectByVisibleText(getData("Country").get(i));
            waitFor(2000);
            screenShot("Forms_Path","Entering the Address");
            DOBInputField.sendKeys(getData("DOB (DDMMYYYY)").get(i));
            screenShot("Forms_Path","Entering the DOB");
            for(WebElement we:genders){
                System.out.println(we.getText());
                if(we.getText().equalsIgnoreCase(getData("Gender").get(i))){
                    we.click();
                }
            }
            screenShot("Forms_Path","Selecting the gender");
            termsAndConditions.click();
            screenShot("Forms_Path","accepting the terms and conditions");
            waitFor(2000);
            scrollBy(submitButton);
            screenShot("Forms_Path","clicking the submit button");
            submitButton.click();
            finishScreenshot();

        }
        clearFiles(readJson().get("Forms_Path").toString());

    }

}
