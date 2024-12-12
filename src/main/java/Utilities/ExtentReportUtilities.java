package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Utilities.DriverUtilities.*;

public class ExtentReportUtilities {
    public static FileOutputStream fo;
    public static XWPFDocument document;
    public static File out;
    public static List<File> imgList=new ArrayList();
    public static List<String> cmntList=new ArrayList<>();
    public static List<FileInputStream> ipStreamList=new ArrayList<>();
    public static FileInputStream fi;
    public static String currentDateTime;
    public static String path=null;
    static ExtentSparkReporter reporter;
    static ExtentReports reports;
    public static ExtentReports createReport() throws IOException, ParseException {
        LocalDateTime dt=LocalDateTime.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_SS");
        String currentDateTime= dt.format(dtf);
        File f=new File(readJson().get("Report_Path").toString()+currentDateTime+".html");
        if(reports==null){
            reporter=new ExtentSparkReporter(f);
            reports=new ExtentReports();
            reports.attachReporter(reporter);}
        return reports;
    }
    public static ExtentTest reportTest(String testName) throws IOException, ParseException{
        ExtentTest test=createReport().createTest(testName);
        return test;
    }
    public static String reportScreenShot() throws IOException, InvalidFormatException, ParseException {
        //int count=0;
        LocalDateTime dt=LocalDateTime.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_SS");
        currentDateTime=dt.format(dtf);
        File destination=new File(config_File().getProperty("Screenshots")+"ss_"+currentDateTime+".png");
        TakesScreenshot ts=(TakesScreenshot) driver();
        File source=ts.getScreenshotAs(OutputType.FILE);
        source.renameTo(destination);
        return destination.getPath();
    }


    public static void finishReport() throws IOException, ParseException{
        createReport().flush();
    }
}
