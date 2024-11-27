package Utilities;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

public class WaitUtilities extends DriverUtilities {
    public static void waitFor(int milliSeconds) throws InterruptedException {
        Thread.sleep(milliSeconds);
    }
    public static void implicitWait(int seconds) throws InterruptedException, IOException {
        driver().manage().timeouts().implicitlyWait(Duration.of(seconds, SECONDS));
    }
    public static void explicitWait(WebElement we,int seconds) throws InterruptedException, IOException {
        WebDriverWait wait=new WebDriverWait(driver(),Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(we));
    }
}
