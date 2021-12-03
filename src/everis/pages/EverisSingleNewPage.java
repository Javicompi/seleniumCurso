package everis.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EverisSingleNewPage extends PageObject {

    @FindBy(xpath = "//h1")
    WebElement newTitle;

    public EverisSingleNewPage(WebDriver driver) {
        super(driver);
    }

    public String getNewTitleText() {
        return newTitle.getText();
    }

    public boolean isInitialized() {
        return newTitle.isDisplayed();
    }
}
