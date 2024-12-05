package Utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class ActionUtilities extends DriverUtilities {
    public static Actions action() throws IOException {
        Actions a=new Actions(driver());
        return a;
    }
    public static void clickAndHold(WebElement fromElement,WebElement toElement) throws IOException {
        action().clickAndHold(fromElement).moveToElement(toElement).release().build().perform();
    }

}
