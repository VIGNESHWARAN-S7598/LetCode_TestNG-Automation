package Utilities;

import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

import static Utilities.DriverUtilities.*;

public class WebPageUtilities {
    public static void openWebpage(String key) throws IOException {
        if(key.equalsIgnoreCase("Forms")){
            driver().get(config_File().getProperty("Forms_WebPage"));
        }
        if(key.equalsIgnoreCase("Inputs")){
            driver().get(config_File().getProperty("Inputs_WebPage"));
        }
        if(key.equalsIgnoreCase("Slider")){
            driver().get(config_File().getProperty("Slider"));
        }

    }
    public static void maximizeWindow() throws IOException {
        driver().manage().window().maximize();
    }
    public static void scrollBy(int upwards,int downwards) throws IOException {
        JavascriptExecutor js=(JavascriptExecutor)driver();
        js.executeScript("window.scroll("+upwards+","+downwards+")");
    }

}
