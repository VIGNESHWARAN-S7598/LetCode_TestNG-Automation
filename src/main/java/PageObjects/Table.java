package PageObjects;

import Utilities.DriverUtilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utilities.ActionUtilities.clickAndHold;
import static Utilities.ExtentReportUtilities.*;
import static Utilities.ExtentReportUtilities.reportScreenShot;
import static Utilities.FileUtilities.*;
import static Utilities.WaitUtilities.waitFor;
import static Utilities.WebPageUtilities.openWebpage;

public class Table extends DriverUtilities {
    @FindBy(xpath = "//table[@id='shopping']/tbody/tr/td[2]")
    public static List<WebElement> prices;
    @FindBy(xpath = "//table[@id='shopping']/thead/tr/th")
    public static WebElement startOftheTable;

    @FindBy(xpath = "//table[@id='shopping']/tfoot/td[2]/b")
    public static WebElement totalPrice;
    @FindBy(xpath = "//table[@id='simpletable']/tbody/tr/td[2]")
    public static List<WebElement> lastNameList;

    @FindBy(xpath = "//table[@id='simpletable']/tbody/tr/td/input")
    public static List<WebElement> checkBoxes;

    @FindBy(xpath ="//table[@class='mat-sort table is-bordered is-striped is-narrow is-hoverable is-fullwidth']/tr/td[2]" )
    public static List<WebElement> calories;
    @FindBy(xpath ="//table[@class='mat-sort table is-bordered is-striped is-narrow is-hoverable is-fullwidth']/tr/td[3]" )
    public static List<WebElement> fats;
    @FindBy(xpath ="//table[@class='mat-sort table is-bordered is-striped is-narrow is-hoverable is-fullwidth']/tr/td[4]" )
    public static List<WebElement> carbs;
    @FindBy(xpath ="//table[@class='mat-sort table is-bordered is-striped is-narrow is-hoverable is-fullwidth']/tr/td[5]" )
    public static List<WebElement> proteins;
    @FindBy(xpath ="//table[@class='mat-sort table is-bordered is-striped is-narrow is-hoverable is-fullwidth']/tr/td[6]" )
    public static List<WebElement> cholestrols;
    @FindBy(xpath ="//th[@mat-sort-header][position() >= 2 ]" )//div/div[1]
    public static List<WebElement> columnNames;


    @Test(priority = 1)
    public static void openTablePage() throws IOException {
        openWebpage("table");
        driver().manage().window().maximize();
    }
    @Test(priority = 2)
    public void verifyingTotalPrice() throws IOException, ParseException, InvalidFormatException {
        PageFactory.initElements(driver(),this);
        int sum=0;
        clickAndHold(startOftheTable,totalPrice);
        screenShot("Table_Path","checking the prices of shopping table");
        for(WebElement we:prices){
            sum+=Integer.parseInt(we.getText());
        }
        createReport();

        if(sum==Integer.parseInt(totalPrice.getText())){
            screenShot("Table_Path","Making Raj as present");
            System.out.println("total of all prices are matching");

            reportTest("verifying that table is present").addScreenCaptureFromPath(reportScreenShot()).pass("total of all prices are matching");
        }
        else{
            System.out.println("total of all prices are not matching");
            reportTest("verifying that table is present").fail("total of all prices are matching");
        }
        finishReport();

    }
    @Test(priority = 3)
    @Parameters({"lastName"})
    public void markingAsPresent(String lastName) throws IOException, ParseException, InvalidFormatException, InterruptedException {
    for(int i=0;i<lastNameList.size();i++){
        if(lastNameList.get(i).getText().equalsIgnoreCase(lastName)){
            checkBoxes.get(i).click();
        }
    }waitFor(2000);
    screenShot("Table_Path","Making Raj as present");
        finishScreenshot();
}
    @Test(priority = 5)
    public void checkSorting() throws IOException, ParseException, InvalidFormatException, InterruptedException {
        List<List<WebElement>> column_List=new ArrayList<>();
        column_List.add(calories);
        column_List.add(fats);
        column_List.add(carbs);
        column_List.add(proteins);
        column_List.add(cholestrols);
       int listCount=0;
        for(List<WebElement> li:column_List) {
            List<Integer> temp=li.stream().map(n->Integer.parseInt(n.getText())).sorted().collect(Collectors.toList());

            if(temp.retainAll(li)==true){
                System.out.println(columnNames.get(listCount).getText()+" Column is not sorted");
            }
            listCount++;
        }
        clearFiles(readJson().get("Table_Path").toString());


    }
}
