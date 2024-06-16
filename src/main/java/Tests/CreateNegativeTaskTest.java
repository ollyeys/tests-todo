package Tests;

import Pages.LoginPage;
import Pages.ProfilePage;
import Pages.NewTaskPage;
import Helpers.ConfProperties;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CreateNegativeTaskTest {


    public static LoginPage loginPage;
    public static NewTaskPage newTaskPage;
    public static ProfilePage profilePage;

    public static RemoteWebDriver driver;

    public static ChromeOptions options;

    public static URL browser_url;



    @BeforeClass
    public static void setup() throws MalformedURLException {

        options = new ChromeOptions();

        options.addArguments("--headless"); // Запуск в headless режиме
        options.addArguments("--no-sandbox"); // Отключение sandbox режима
        options.addArguments("--disable-dev-shm-usage"); // Уменьшение использования /dev/shm
        options.addArguments("--disable-gpu"); // Отключение GPU
        options.addArguments("--remote-debugging-port=9222"); // Необходимо для запуска в Docker


        browser_url = new URL(ConfProperties.getProperty("BROWSER_URL"));

        driver = new RemoteWebDriver(browser_url, options);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        newTaskPage = new NewTaskPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("LOGIN_PAGE"));
    }

    @Test
    public void createNegativeTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("LOGIN"));
        loginPage.inputPassword(ConfProperties.getProperty("PASSWORD"));
        loginPage.clickLoginBtn();
        profilePage.clickAddBtn();

        String taskTitle = ConfProperties.getProperty("TASK_TITLE");
        String taskDescription = ConfProperties.getProperty("TASK_DESCRIPTION");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("TASK_STATUS"));
        String taskDate = ConfProperties.getProperty("TASK_DATE");

        newTaskPage.createInvalidTask(taskTitle, taskDescription, taskStatus);
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
