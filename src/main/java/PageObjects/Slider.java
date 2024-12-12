package PageObjects;

import Utilities.DriverUtilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;

import static Utilities.FileUtilities.*;
import static Utilities.WaitUtilities.waitFor;
import static Utilities.WebPageUtilities.*;


public class Slider extends DriverUtilities{
    @FindBy (xpath = "//input[@type='range']")
    public static WebElement slider;
    @FindBy (xpath = "//button[text()='Get Countries']")
    public static WebElement getCountriesButton;

    @FindBy (xpath = "//p[@_ngcontent-serverapp-c68]")
    public static WebElement countries;
    //public static int range=30;
    @Test(priority = 1)
    public void openSliderPage() throws IOException {
        /*Scanner sc=new Scanner(System.in);
        System.out.println("Enter the range for the slider");
        range=sc.nextInt();*/
        openWebpage("Slider");
        driver().manage().window().maximize();
    }
    @Test(priority = 2)
    @Parameters({"range"})
    public void moveSlider(int range) throws IOException, InterruptedException, InvalidFormatException, AWTException, ParseException {
        PageFactory.initElements(driver(),this);
        Actions action=new Actions(driver());
        slider.click();
        waitFor(5000);

        if(range< 26){
            int temp=26-range;
            for (int i=0;i<temp;i++){
                action.keyDown(Keys.ARROW_LEFT).build().perform();

            }
        }else{
            int temp1=range-26;
            for(int i=0;i<temp1;i++){
                action.keyDown(Keys.ARROW_RIGHT).build().perform();
            }
        }if(range==26){
            action.keyDown(Keys.ARROW_RIGHT).build().perform();
            action.keyDown(Keys.ARROW_LEFT).build().perform();
        }

        waitFor(2000);
        screenShot("Slider_Path","Before Clicking button");
        //finishScreenshot();
        getCountriesButton.click();
        scrollBy(countries);
        screenShot("Slider_Path","After clicking on get Countries");
        finishScreenshot();
        String countries_para=countries.getText();
        List<String> countries= Arrays.stream(countries_para.split(" - ")).toList();
        System.out.println("no of Countries: "+countries.size());
        if(range==countries.size()){
            System.out.println("countries are generated as per slider values");
        }
        clearFiles(readJson().get("Slider_Path").toString());

        //finishScreenshot();
    }



}
