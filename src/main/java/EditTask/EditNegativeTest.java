package EditTask;

import CreateTask.NewTaskPage;
import Helpers.DatabaseHelper;
import LoginTest.LoginPage;
import LoginTest.ProfilePage;
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

public class EditNegativeTest {
    public static LoginPage loginPage;
    public static NewTaskPage newTaskPage;
    public static ProfilePage profilePage;

    public static EditPage editPage;

    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        editPage = new EditPage(driver);
        newTaskPage = new NewTaskPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void editNegativeTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        profilePage.clickEditBtn();


        String taskTitle = ConfProperties.getProperty("taskEditTitle");
        String taskDescription = ConfProperties.getProperty("taskEditDescription");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("taskEditStatus"));

        editPage.editInvalidTask(taskTitle, taskDescription, taskStatus);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#targetDate:invalid")));
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
