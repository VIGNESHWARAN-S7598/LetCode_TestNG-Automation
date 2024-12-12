package PageObjects;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

import static Utilities.ActionUtilities.action;
import static Utilities.ActionUtilities.clickAndHold;
import static Utilities.DriverUtilities.config_File;
import static Utilities.DriverUtilities.driver;
import static Utilities.FileUtilities.*;
import static Utilities.WaitUtilities.waitFor;
import static Utilities.WebPageUtilities.scrollBy;

public class Buttons {
    @FindBy(xpath = "//div/button[@id='home']")
    public static WebElement homeButton;
    @FindBy(xpath = "//a[@title='Courses'][@href='/test']")
    public static WebElement courseLink;

    @FindBy(xpath = "//a[@href='/buttons']")
    public static WebElement buttonPageLink;

    @FindBy(xpath = "//button[@id='position']")
    public static WebElement locationButton;
    @FindBy(id="color")
    public static WebElement colorButton;
    @FindBy(id="property")
    public static WebElement sizeButton;
    @FindBy(xpath = "//button[@id='isDisabled'][@title='Disabled button']")
    public static WebElement disabledButton;
    @Test(priority = 1)
    public void openButtonsWebpage() throws IOException {
        driver().get(config_File().getProperty("Buttons_WebPage"));
        driver().manage().window().maximize();

    }
    @Test(priority = 2)
    public void homeButtonnavigation() throws IOException, InterruptedException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        homeButton.click();
        screenShot("Buttons_Path","After clicking HomeButton");
        waitFor(3000);
        courseLink.click();
        waitFor(3000);
        buttonPageLink.click();
        waitFor(3000);
    }
    @Test(priority = 3)
    public void locationOfButton() throws IOException, InterruptedException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        int x=locationButton.getLocation().getX();
        int y=locationButton.getLocation().getY();
        scrollBy(locationButton);
        screenShot("Buttons_Path","Finding \n location of button");
        addscreenShotComment("X Coordinate of a button : "+x);
        addscreenShotComment("Y Coordinate of a button : "+y);
        waitFor(3000);
    }
    @Test(priority = 4)
    public void colorOfButton() throws IOException, InterruptedException {
        PageFactory.initElements(driver(),this);
        scrollBy(colorButton);
        waitFor(3000);
        String color=colorButton.getCssValue("background-color");
        System.out.println("Color code of button: "+color);

    }
    @Test(priority = 5)
    public void sizeOfButton() throws IOException, InterruptedException {
        PageFactory.initElements(driver(),this);
        int height=sizeButton.getSize().getHeight();
        int width=sizeButton.getSize().getWidth();
        scrollBy(sizeButton);
        waitFor(3000);
        System.out.println("height of the button : "+height);
        System.out.println("width of the button : "+width);
    }
    @Test(priority = 6)
    public void disabledButton() throws IOException, InterruptedException {
        PageFactory.initElements(driver(),this);
        scrollBy(disabledButton);
       if(!disabledButton.isEnabled()){
           System.out.println("Button is disabled");
       }else{
           System.out.println("Button is enabled");
       }
       waitFor(3000);
    }

    @FindBy(xpath = "//button[@id='isDisabled'][@class='button is-primary']")
    public static WebElement clickAndHoldButton;
    @Test(priority = 7)
    public void clickAndHoldButton() throws IOException, InterruptedException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        scrollBy(clickAndHoldButton);
        action().clickAndHold(clickAndHoldButton);
        waitFor(3000);
        finishScreenshot();
    }
}
