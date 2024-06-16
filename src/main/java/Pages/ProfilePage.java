package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProfilePage {

    private static RemoteWebDriver wait;

    public RemoteWebDriver driver;
    public ProfilePage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @FindBy(xpath = "//*[contains(@id, 'todolist')]")
    private WebElement todolist;


    @FindBy(xpath = "//*[contains(text(), 'Add To-Do')]")
    private WebElement addTaskBtn;

    @FindBy(xpath = "//*[@href='/edit?id=4']")
    private WebElement editTaskBtn;

    @FindBy(xpath = "//*[@href='/delete?id=4']")
    private WebElement deleteTaskBtn;

    @FindBy(xpath = "//*[@class='nav-link' and contains(@href, '/login')]")
    private WebElement LogoutBtn;


    public void clickLogoutBtn() {
        LogoutBtn.click();
    }

    public String getToDoListHeader() {
        String header = todolist.getText();
        return header;
    }
//    public void clickLogoutBtn() {LogoutBtn.click();
//    }

    public void clickAddBtn() {addTaskBtn.click();}

    public void clickEditBtn() {editTaskBtn.click();}

    public void clickDeleteBtn() {deleteTaskBtn.click();}




}
