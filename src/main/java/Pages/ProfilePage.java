package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'todolist')]")
    private WebElement todolist;

    @FindBy(xpath = "//*[contains(text(), 'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[contains(text(), 'Add To-Do')]")
    private WebElement addTaskBtn;

    @FindBy(xpath = "//*[@href='/edit?id=4']")
    private WebElement editTaskBtn;

    @FindBy(xpath = "//*[@href='/delete?id=4']")
    private WebElement deleteTaskBtn;




    public String getToDoListHeader() {
        String header = todolist.getText();
        return header;
    }
    public void clickLogoutBtn() {logoutBtn.click();
    }

    public void clickAddBtn() {addTaskBtn.click();}

    public void clickEditBtn() {editTaskBtn.click();}

    public void clickDeleteBtn() {deleteTaskBtn.click();}




}
