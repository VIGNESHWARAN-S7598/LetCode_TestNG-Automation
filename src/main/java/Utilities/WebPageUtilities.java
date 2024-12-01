package Utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Utilities.DriverUtilities.*;

public class WebPageUtilities {
    public static FileOutputStream fo;
    public static XWPFDocument document;
    public static File out;
    public static List<File> imgList=new ArrayList();
    public static List<String> cmntList=new ArrayList<>();
    public static List<FileInputStream> ipStreamList=new ArrayList<>();
    public static FileInputStream fi;
    public static String currentDateTime;
    public static String path=null;
   /* static LocalDateTime dt=LocalDateTime.now();
    static DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_ss");
    public static String currentDateTime1=dt.format(dtf);*/

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
    public static void scrollBy(WebElement we) throws IOException {
        JavascriptExecutor js=(JavascriptExecutor)driver();
        js.executeScript("arguments[0].scrollIntoView(false);",we);
    }
}
