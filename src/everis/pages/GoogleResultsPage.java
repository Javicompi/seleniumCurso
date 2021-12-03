package everis.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleResultsPage extends PageObject {

    @FindBy(xpath = "//div[@class='g']//a")
    WebElement firstSearchResult;

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }

    public EverisMainPage submit() {
        firstSearchResult.click();
        return new EverisMainPage(driver);
    }

    public boolean isInitialized() {
        return firstSearchResult.isDisplayed();
    }
}
