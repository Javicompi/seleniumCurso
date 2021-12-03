package everis.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EverisMainPage extends PageObject {

    @FindBy(xpath = "//img[@class='navbar-link-icon']")
    WebElement searchIcon;

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchInput;

    @FindBy(id = "button-search")
    WebElement searchButton;

    public EverisMainPage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }

    public void enterSearchInput(String searchText) {
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }

    public EverisNewsPage submit() {
        searchButton.click();
        return  new EverisNewsPage(driver);
    }

    public boolean isInitialized() {
        return searchIcon.isDisplayed();
    }
}
