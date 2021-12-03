package selenium;

import common.WebDriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestNG1 {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = WebDriverConfig.createWebDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Login")
    public void login() {
        driver.findElement(By.id("btn-make-appointment")).click();
        WebElement loginButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("btn-login")));
        String resultText = driver.findElement(By.xpath("//p[@class='lead']")).getText();
        Assert.assertEquals(resultText, "Please login to make appointment.");
        String xpathUsername = "//div[@class='alert alert-info']//input[@placeholder='Username']";
        String username = driver.findElement(By.xpath(xpathUsername)).getAttribute("value");
        Assert.assertFalse(username.isEmpty());
        String xpathPassword = "//div[@class='alert alert-info']//input[@placeholder='Password']";
        String password = driver.findElement(By.xpath(xpathPassword)).getAttribute("value");
        Assert.assertFalse(password.isEmpty());
        driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(password);
        //driver.findElement(By.id("btn-login")).click();
        loginButton.click();
        WebElement appointmentButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("btn-book-appointment")));
        //WebElement appointmentButton = driver.findElement(By.id("btn-book-appointment"));
        Assert.assertNotNull(appointmentButton);
        Select facility = new Select(driver.findElement(By.id("combo_facility")));
        facility.selectByIndex(0);
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        driver.findElement(By.id("radio_program_medicaid")).click();
        LocalDate currentDate = LocalDate.now();
        currentDate = currentDate.plusWeeks(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateText = dateFormat.format(currentDate);
        driver.findElement(By.id("txt_visit_date")).sendKeys(dateText);
        driver.findElement(By.id("txt_comment")).sendKeys("TEST");
        driver.findElement(By.id("btn-book-appointment")).click();
        String confirmation = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(confirmation, "Appointment Confirmation");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    @Test(description = "Fill form", enabled = false)
    public void fillForm() throws InterruptedException {
        Thread.sleep(2000);
        Select facility = new Select(driver.findElement(By.id("combo_facility")));
        facility.selectByIndex(0);
        LocalDate currentDate = LocalDate.now();
        currentDate = currentDate.plusWeeks(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = dateFormat.format(currentDate);
        System.out.println(dateText);
    }

    @Test(description = "Make appointment", enabled = false)
    public void pruebaPrimera() throws InterruptedException {
        driver.get("https://katalon-demo-cura.herokuapp.com");
        Thread.sleep(2000);
        driver.findElement(By.id("btn-make-appointment")).click();
        Thread.sleep(2000);
        String resultText = driver.findElement(By.xpath("//p[@class='lead']")).getText();
        Assert.assertEquals(resultText, "Please login to make appointment.");
    }

    @Test(description = "Fa bars", enabled = false)
    public void pruebaSegunda() throws InterruptedException {
        driver.get("https://katalon-demo-cura.herokuapp.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
        Thread.sleep(2000);
    }
}
