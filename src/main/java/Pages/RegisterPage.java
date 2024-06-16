package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    public RemoteWebDriver driver;

    public RegisterPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'name')]")
    private WebElement userName;

    @FindBy(xpath = "//*[contains(@id, 'surname')]")
    private WebElement userSurname;

    @FindBy(xpath = "//*[contains(@id, 'username')]")
    private WebElement userLogin;

    @FindBy(xpath = "//*[contains(@id, 'password')]")
    private WebElement userPassword;

    @FindBy(xpath = "//*[contains(text(), 'Submit')]")
    private WebElement registerBtn;

    @FindBy(xpath = "//*[contains(@class, 'alert alert-success')]")
    private WebElement successAlert;


    public void inputName(String name) {userName.sendKeys(name);}

    public void inputSurname(String surname) {userSurname.sendKeys(surname);}

    public void inputLogin(String login) {userLogin.sendKeys(login);}

    public void inputPassword(String password) {userPassword.sendKeys(password);}

    public void clickRegisterBtn() {registerBtn.click();}

    public void registerUser(String name, String surname, String login, String password) {
        inputName(name);
        inputSurname(surname);
        inputLogin(login);
        inputPassword(password);
        clickRegisterBtn();
    }



}
