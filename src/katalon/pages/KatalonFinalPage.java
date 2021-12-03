package katalon.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KatalonFinalPage extends PageObject {

    @FindBy(xpath = "//h2")
    private WebElement confirmationHeader;

    public KatalonFinalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return confirmationHeader.isDisplayed();
    }
}
