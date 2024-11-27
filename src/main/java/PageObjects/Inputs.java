package PageObjects;

import Utilities.DriverUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

import static Utilities.ExcelUtilities.*;
import static Utilities.WaitUtilities.*;
import static Utilities.WebPageUtilities.*;

public class Inputs extends DriverUtilities {
    public static String errorMessage;
    @FindBy(xpath="//a[text()='Edit']")
    public static WebElement inputPageLink;
    @FindBy(id = "fullName")
    public static WebElement fullNameInput;
    @FindBy(id = "clearMe")
    public static WebElement clearTextField;
    @FindBy(xpath = "//input[@id='noEdit']")
    public static WebElement disabledInput;
    @FindBy(xpath = "//input[@id='dontwrite']")
    public static WebElement readOnlyInput;

    @Test(priority = 1)
    public void openInputPage() throws IOException {
        try {
            //setDriver("Chrome");
       PageFactory.initElements(driver(),this);
        implicitWait(15);
        explicitWait(inputPageLink,7);
        inputPageLink.click();
            //openWebpage("Inputs");
            maximizeWindow();
        }catch (Exception e){
            errorMessage=e.getMessage();
        }
        Assert.assertTrue(driver().getTitle().equalsIgnoreCase("Interact with Input fields"),errorMessage);
    }
    @Test(priority = 2,dependsOnMethods = "Inputs.openInputPage()")
    public  void enterFullName() throws IOException {
        PageFactory.initElements(driver(),this);
        /*setExcel(getConfigProperty("forms"));
        for(String str:getExcelData("Firstame")){
            System.out.println(str);
        }*/
        fullNameInput.sendKeys("Vigneshwaran Somasundaram");
    }
    @Test(priority = 3,dependsOnMethods = "Inputs.openInputPage()")
    public  void checkEnablity() throws IOException {
       // PageFactory.initElements(driver(),this);
        if(disabledInput.isEnabled()){
            System.out.println("text field is enabled");
        }else{
            System.out.println("text field is disabled");
        }
    }
    @Test(priority = 4,dependsOnMethods = "Inputs.openInputPage()")
    public  void checkReadOnly() throws IOException, InterruptedException {
        // PageFactory.initElements(driver(),this);
       if(readOnlyInput.getAttribute("readonly").equals("true")){
           System.out.println("the field is read only");
       }

    }
    @Test(priority = 5,dependsOnMethods = "Inputs.openInputPage()")
    public  void clearText() throws InterruptedException, IOException {
        scrollBy(0,350);
        waitFor(3000);
        clearTextField.clear();
        waitFor(3000);
    }


}
