package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {

    public static WebDriver createWebDriver()  {
        String path = System.getProperty("user.dir");
        String driverPath = path + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        return new ChromeDriver(options);
    }
}
