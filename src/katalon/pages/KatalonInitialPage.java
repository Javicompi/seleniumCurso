package katalon.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KatalonInitialPage extends PageObject {

    @FindBy(id = "btn-make-appointment")
    private WebElement makeAppointmentButton;

    public KatalonInitialPage(WebDriver driver) {
        super(driver);
    }

    public KatalonLoginPage submit() {
        makeAppointmentButton.click();
        return new KatalonLoginPage(driver);
    }

    public boolean isInitialized() {
        return makeAppointmentButton.isDisplayed();
    }
}
