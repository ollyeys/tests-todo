package Tests;

import Pages.LoginPage;
import Pages.ProfilePage;
import Pages.NewTaskPage;
import Helpers.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме
        options.addArguments("--no-sandbox"); // Отключение sandbox режима
        options.addArguments("--disable-dev-shm-usage"); // Уменьшение использования /dev/shm
        options.addArguments("--disable-gpu"); // Отключение GPU
        options.addArguments("--remote-debugging-port=9222"); // Необходимо для запуска в Docker


        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        newTaskPage = new NewTaskPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void createNegativeTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        profilePage.clickAddBtn();

        String taskTitle = ConfProperties.getProperty("taskTitle");
        String taskDescription = ConfProperties.getProperty("taskDescription");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("taskStatus"));
        String taskDate = ConfProperties.getProperty("taskDate");

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
