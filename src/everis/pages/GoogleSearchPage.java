package everis.pages;

import common.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleSearchPage extends PageObject {

    @FindBy(xpath = "//button[contains(.,'Acepto')]")
    List<WebElement> acceptButton;

    @FindBy(name = "q")
    WebElement searchInput;

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchText(String searchText) {
        if (acceptButton.size() > 0) acceptButton.get(0).click();
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }

    public GoogleResultsPage submit() {
        searchInput.sendKeys(Keys.RETURN);
        return new GoogleResultsPage(driver);
    }

    public boolean isInitialized() {
        return ((acceptButton.size() > 0) || (searchInput.isDisplayed()));
    }
}
