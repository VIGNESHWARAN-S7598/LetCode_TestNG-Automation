package Utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverUtilities {
public static WebDriver driver;
public static String browser;
    public static Properties config_File() throws IOException {
        Properties p=new Properties();
        FileInputStream fi=new FileInputStream("C:\\Users\\svign\\IdeaProjects\\testNG_web_Automation\\src\\main\\resources\\Config.properties");
        p.load(fi);
        return p;
    }
public static String setDriver(String browser) throws IOException {
    System.out.println(config_File().getProperty("Chrome"));
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",config_File().getProperty("Chrome"));
        }

        DriverUtilities.browser=browser;
        return browser;
        }
        public static String getConfigProperty(String key) throws IOException {
        String path = null;
        if(key.equalsIgnoreCase("forms")){
                path=new String(config_File().getProperty("Forms"));
            }
            return path;
        }
        public static WebDriver driver() throws IOException {
            if(DriverUtilities.browser.equalsIgnoreCase("Chrome")){
                if (driver == null) {
                    driver = new ChromeDriver();
                }
           }
            return driver;
        }
}

