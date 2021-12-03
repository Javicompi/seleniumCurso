package katalon.pages;

import common.PageObject;
import katalon.functions.KatalonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class KatalonAppointmentPage extends PageObject {

    @FindBy(id = "combo_facility")
    private WebElement facilitySelect;

    @FindBy(id = "chk_hospotal_readmission")
    private WebElement hopstalReadmissionCheck;

    @FindBy(id = "radio_program_medicaid")
    private WebElement programMedicaidRadio;

    @FindBy(id = "txt_visit_date")
    private WebElement visitDateInput;

    @FindBy(id = "txt_comment")
    private WebElement textCommentInput;

    @FindBy(id = "btn-book-appointment")
    private WebElement bookAppointmentButton;

    public KatalonAppointmentPage(WebDriver driver) {
        super(driver);
    }

    public void selectFacility() {
        Select select = new Select(facilitySelect);
        select.selectByIndex(0);
    }

    public void selectProgramMedicaid() {
        programMedicaidRadio.click();
    }

    public void checkHospotalReadmission() {
        hopstalReadmissionCheck.click();
    }

    public void inputVisitDate() {
        String dateText = KatalonFunctions.dateToString(7);
        visitDateInput.clear();
        visitDateInput.sendKeys(dateText);
    }

    public void inputTextComment() {
        textCommentInput.sendKeys("TEST COMMENT");
    }

    public KatalonFinalPage submit() {
        bookAppointmentButton.click();
        return new KatalonFinalPage(driver);
    }

    public boolean isInitialized() {
        return bookAppointmentButton.isDisplayed();
    }
}
