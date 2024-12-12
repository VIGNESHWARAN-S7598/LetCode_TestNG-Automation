package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilities {
 // static XSSFSheet sheet;
  public static File filePath;
  public static XSSFSheet sheet;
   /* public void setExcel(String Path) throws IOException {
        Path="C:\\Users\\svign\\IdeaProjects\\testNG_web_Automation\\src\\main\\resources\\Forms_data.xlsx";
       File f=new File(Path);
        filePath=new File(Path);
    }*/
    public ExcelUtilities() throws IOException {
    }
    public static XSSFSheet setExcel(String excel_path) throws IOException {
        FileInputStream fi=new FileInputStream(excel_path);
        XSSFWorkbook excel=new XSSFWorkbook(fi);
        sheet=excel.getSheetAt(0);
        return sheet;
    }
    public static List<String> getData(String cellName) throws IOException {

        List<String> data=new ArrayList<>();
        data.clear();
        int rowCount=0;
        int cellCount=0;
        int  cellNumber = 0;
        for(Row row:sheet) {
            //rowCount++;
            for(Cell cell:row){
                if(cell.getRowIndex()==0){
                    if (cell.getStringCellValue().equalsIgnoreCase(cellName)) {
                        cellNumber=cell.getColumnIndex();
                    }
                }else{
                    if(cell.getColumnIndex()==cellNumber){
                        data.add(row.getCell(cellNumber).getStringCellValue());
                    }

                }
            }

        }
        return data;
    }

    }

