package katalon.test;

import common.WebDriverConfig;
import katalon.functions.KatalonFunctions;
import katalon.pages.KatalonAppointmentPage;
import katalon.pages.KatalonFinalPage;
import katalon.pages.KatalonInitialPage;
import katalon.pages.KatalonLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class KatalonTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = WebDriverConfig.createWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://katalon-demo-cura.herokuapp.com");
    }

    @AfterTest
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void goToMakeAppointment() {
        KatalonInitialPage katalonInitialPage = PageFactory.initElements(driver, KatalonInitialPage.class);
        Assert.assertTrue(katalonInitialPage.isInitialized());
        KatalonFunctions.takeScreenshot(driver, "katalonInitialPage");

        KatalonLoginPage katalonLoginPage = katalonInitialPage.submit();
        Assert.assertTrue(katalonLoginPage.isInitialized());
        KatalonFunctions.takeScreenshot(driver, "katalonLoginPage");
        katalonLoginPage.enterUsername();
        katalonLoginPage.enterPassword();

        KatalonAppointmentPage katalonAppointmentPage = katalonLoginPage.submit();
        Assert.assertTrue(katalonAppointmentPage.isInitialized());
        KatalonFunctions.takeScreenshot(driver, "katalonAppointmentPage");
        katalonAppointmentPage.selectFacility();
        katalonAppointmentPage.checkHospotalReadmission();
        katalonAppointmentPage.selectProgramMedicaid();
        katalonAppointmentPage.inputVisitDate();
        katalonAppointmentPage.inputTextComment();

        KatalonFinalPage katalonFinalPage = katalonAppointmentPage.submit();
        Assert.assertTrue(katalonFinalPage.isInitialized());
        KatalonFunctions.takeScreenshot(driver, "katalonFinalPage");
    }
}
