package EditTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditPage {

    public WebDriver driver;

    public EditPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'title')]")
    private WebElement taskTitle;

    @FindBy(xpath = "//*[contains(@id, 'description')]")
    private WebElement taskDescription;

    @FindBy(xpath = "//*[contains(@id, 'isDone')]")
    private WebElement taskStatus;

    @FindBy(xpath = "//*[contains(@id, 'targetDate')]")
    private WebElement taskDate;

    @FindBy(xpath = "//*[contains(text(), 'Save Changes')]")
    private WebElement editTaskBtn;

    public void inputTitle(String title) {taskTitle.sendKeys(title);}

    public void inputDescription(String description) {taskDescription.sendKeys(description);}

    public void selectStatus(Boolean value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Select statusSelect = new Select(taskStatus);
        wait.until(ExpectedConditions.visibilityOf(taskStatus));
        statusSelect.selectByValue(String.valueOf(value));
    }


    public void selectDate(String date) {
        taskDate.sendKeys(date);
        System.out.println(taskDate);
    }

    public void clickEditBtn() {editTaskBtn.click();}

    public void clearFields() {
        if (taskTitle.isDisplayed() && taskTitle.isEnabled()) {
            taskTitle.clear();}
        if (taskDescription.isDisplayed() && taskDescription.isEnabled()) {
            taskDescription.clear();}
    }


    public void editTask(String title, String description, Boolean status, String  date) {
        clearFields();
//        taskDate.click();
        inputTitle(title);
        inputDescription(description);
        selectStatus(status);
        taskDate.click();
        selectDate(date);
        clickEditBtn();
    }

    public void editInvalidTask(String title, String description, Boolean status) {
        clearFields();
        inputTitle(title);
        inputDescription(description);
        selectStatus(status);
        clickEditBtn();
    }


}
