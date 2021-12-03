package katalon.functions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class KatalonFunctions {

    private static final String SCREENSHOT_PATH = "\\katalon\\screenshots";

    private KatalonFunctions() {}

    public static String dateToString(int daysAhead) {
        LocalDate currentDate = LocalDate.now().plusDays(daysAhead);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormat.format(currentDate);
    }

    public static void takeScreenshot(WebDriver driver, String path) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + SCREENSHOT_PATH + path;
        File destFile = new File(filePath + ".jpg");
        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
