package everis.pages;

import common.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EverisNewsPage extends PageObject {

    @FindBy(xpath = "//div[@class='cards-wrapper']//a")
    List<WebElement> everisNews;

    public EverisNewsPage(WebDriver driver) {
        super(driver);
    }

    public void scrollDown() {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,400)", "");
    }

    public String getNewTitle(int index) {
        return everisNews.get(index).getText().replace("\n", "");
    }

    public EverisSingleNewPage submit(int index) {
        everisNews.get(index).click();
        return new EverisSingleNewPage(driver);
    }

    public boolean isInitialized() {
        return (everisNews.size() > 0);
    }
}
