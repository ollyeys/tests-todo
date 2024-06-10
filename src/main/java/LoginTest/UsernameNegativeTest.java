package LoginTest;

import Properties.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UsernameNegativeTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;

    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void usernameNegativeTest() {
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#username:invalid")));
        String validationText = validationMessage.getText();
        Assert.assertNotNull("Validation error should be displayed for empty date field", validationText);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
