package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public RemoteWebDriver driver;

    public LoginPage(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'username')]")
    private WebElement usernameField;


    @FindBy(xpath = "//*[contains(@id, 'password')]")
    private WebElement pswdField;


    @FindBy(xpath = "//*[contains(text(), 'Submit')]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(text(), 'Sign up')]")
    private WebElement signupBtn;

    @FindBy(xpath = "//*[contains(text(), 'Login')]")
    private WebElement loginHeader;


    public void inputLogin(String login) {
        usernameField.sendKeys(login);
    }

    public void inputPassword(String password) {
        pswdField.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }

    public void clickSignUpBtn() {
        signupBtn.click();
    }


}
