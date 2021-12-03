package everis.test;

import common.WebDriverConfig;
import everis.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCaseEveris {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = WebDriverConfig.createWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.es/");
    }

    @AfterTest
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchEverisNavigateToNew() {
        String everisText = "EVERIS";
        String everisNewSearch = "NTTDATA";
        int newIndex = 1;

        GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        Assert.assertTrue(googleSearchPage.isInitialized());

        googleSearchPage.inputSearchText(everisText);
        GoogleResultsPage googleResultsPage = googleSearchPage.submit();
        Assert.assertTrue(googleResultsPage.isInitialized());

        EverisMainPage everisMainPage = googleResultsPage.submit();
        Assert.assertTrue(everisMainPage.isInitialized());
        everisMainPage.clickSearchIcon();
        everisMainPage.enterSearchInput(everisNewSearch);

        EverisNewsPage everisNewsPage = everisMainPage.submit();
        Assert.assertTrue(everisNewsPage.isInitialized());
        everisNewsPage.scrollDown();
        String newTitle = everisNewsPage.getNewTitle(newIndex).substring(0, 10);

        EverisSingleNewPage everisSingleNewPage = everisNewsPage.submit(newIndex);
        Assert.assertTrue(everisSingleNewPage.isInitialized());

        Assert.assertTrue(everisSingleNewPage.getNewTitleText().contains(newTitle));
    }

    @Test(description = "Buscar Everis en Google, abre la web y busca la segunda noticia", enabled = true)
    public void searchNavigateOpenFind() {
        List<WebElement> acceptButton = driver.findElements(By.xpath("//button[contains(.,'Acepto')]"));
        if (acceptButton.size() != 0) acceptButton.get(0).click();
        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q' and @type='text']"));
        searchInput.sendKeys("everis");
        searchInput.sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//div[@class='g']//a")).click();
        driver.findElement(By.xpath("//img[@class='navbar-link-icon']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("NTTDATA");
        driver.findElement(By.id("button-search")).click();
        List<WebElement> news = driver.findElements(By.xpath("//div[@class='cards-wrapper']//a"));
        String newTitle = news.get(1).getText();
        news.get(1).click();
        String newResult = driver.findElement(By.xpath("//title")).getText();
        Assert.assertEquals(newResult, newTitle);
    }
}
