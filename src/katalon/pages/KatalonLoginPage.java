package katalon.pages;

import common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KatalonLoginPage extends PageObject {

    @FindBy(xpath = "//div[@class='alert alert-info']//input[@placeholder='Username']")
    private WebElement usernameField;

    @FindBy(xpath = "//div[@class='alert alert-info']//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='txt-username']")
    private WebElement inputUsernameField;

    @FindBy(xpath = "//input[@id='txt-password']")
    private WebElement inputPasswordField;

    @FindBy(id = "btn-login")
    private WebElement loginButton;

    public KatalonLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername() {
        String username = usernameField.getAttribute("value");
        inputUsernameField.clear();
        inputUsernameField.sendKeys(username);
    }

    public void enterPassword() {
        String password = passwordField.getAttribute("value");
        inputPasswordField.clear();
        inputPasswordField.sendKeys(password);
    }

    public KatalonAppointmentPage submit() {
        loginButton.click();
        return new KatalonAppointmentPage(driver);
    }

    public boolean isInitialized() {
        return loginButton.isDisplayed();
    }
}
