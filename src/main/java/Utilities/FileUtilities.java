package Utilities;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Utilities.DriverUtilities.*;

public class FileUtilities {
    public static FileOutputStream fo;
    public static XWPFDocument document;
    public static File out;
    public static List<File> imgList=new ArrayList();
    public static List<String> cmntList=new ArrayList<>();
    public static List<FileInputStream> ipStreamList=new ArrayList<>();
    public static FileInputStream fi;
    public static String currentDateTime;
    public static String path=null;

    public static void screenShot(String filePath,String comment) throws IOException, InvalidFormatException, ParseException {
        int count=0;
        LocalDateTime dt=LocalDateTime.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd_MM_YYYY_HH_mm_SS");
        currentDateTime=dt.format(dtf);
        File destination=new File(config_File().getProperty("Screenshots")+"ss_"+currentDateTime+".png");
        imgList.add(destination);
        path=filePath;
        cmntList.add(comment);
        TakesScreenshot ts=(TakesScreenshot) driver();
        File source=ts.getScreenshotAs(OutputType.FILE);
        source.renameTo(destination);
        fi=new FileInputStream(destination);
        ipStreamList.add(fi);
    }
    public static void finishScreenshot() throws IOException, ParseException, InvalidFormatException {
        document=new XWPFDocument();
        for(int i=0;i<cmntList.size();i++){
            XWPFParagraph newParagraph = document.createParagraph();
            XWPFRun run=newParagraph.createRun();
            run.setText(cmntList.get(i));
            run.setBold(true);
            run.setTextHighlightColor("yellow");
            run.addPicture(ipStreamList.get(i),XWPFDocument.PICTURE_TYPE_PNG,imgList.get(i).getName(), Units.toEMU(500),Units.toEMU(250));
        }
        out=new File(readJson().get(path).toString()+currentDateTime+".docx");
        fo=new FileOutputStream(out);
        document.write(fo);
        cmntList.clear();
        ipStreamList.clear();
        imgList.clear();
    }
}
