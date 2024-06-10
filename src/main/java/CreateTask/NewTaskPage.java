package CreateTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NewTaskPage {

    public WebDriver driver;

    public NewTaskPage(WebDriver driver) {
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

    @FindBy(xpath = "//*[contains(text(), 'Create Task')]")
    private WebElement createTaskBtn;


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

    public void clickCreateTaskBtn() {createTaskBtn.click();}

    public void createTask(String title, String description, Boolean status, String  date) {
        inputTitle(title);
        inputDescription(description);
        selectStatus(status);
        taskDate.click();
        selectDate(date);
        clickCreateTaskBtn();
    }

    public void createInvalidTask(String title, String description, Boolean status) {
        inputTitle(title);
        inputDescription(description);
        selectStatus(status);
        clickCreateTaskBtn();
    }







}
