package PageObjects;

import Utilities.DriverUtilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Scanner;

import static Utilities.WaitUtilities.waitFor;
import static Utilities.WebPageUtilities.openWebpage;


public class Slider extends DriverUtilities{
    @FindBy (xpath = "//input[@type='range']")
    public static WebElement slider;
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
    public void moveSlider(int range) throws IOException, InterruptedException {
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
        }waitFor(2000);

    }



}
