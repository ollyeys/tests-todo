package Tests;

import Helpers.DatabaseHelper;
import Pages.LoginPage;
import Pages.ProfilePage;
import Helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeleteTaskTest {

//    public static WebDriver driver;

    public static LoginPage loginPage;
    public static ProfilePage profilePage;

    public static RemoteWebDriver driver;

    public static URL browser_url;

    public static ChromeOptions options;

    @BeforeClass
    public static void setup() throws MalformedURLException {

        browser_url = new URL(ConfProperties.getProperty("BROWSER_URL"));

        options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме
        options.addArguments("--no-sandbox"); // Отключение sandbox режима
        options.addArguments("--disable-dev-shm-usage"); // Уменьшение использования /dev/shm
        options.addArguments("--disable-gpu"); // Отключение GPU

        driver = new RemoteWebDriver(browser_url, options);


        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("LOGIN_PAGE"));
    }


    @Test
    public static void deleteTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("LOGIN"));
        loginPage.inputPassword(ConfProperties.getProperty("PASSWORD"));
        loginPage.clickLoginBtn();

        String taskTitle = ConfProperties.getProperty("TASK_EDIT_TITLE");
        String taskDescription = ConfProperties.getProperty("TASK_EDIT_DESCRIPTION");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("TASK_EDIT_STATUS"));
        String taskDate = ConfProperties.getProperty("TASK_EDIT_DATE");

        profilePage.clickDeleteBtn();

        boolean taskExists = DatabaseHelper.isTaskExists(taskTitle, taskDescription, taskStatus);
        Assert.assertFalse(taskExists, "Task isn't in DB");


    }

    @AfterClass
    public void tearDown() {
//        boolean isDeleted = DatabaseHelper.clearDB();
//        if (isDeleted) {
//            System.out.println("Users successfully deleted from the database.");
//        } else {
//            System.out.println("Failed to delete users from the database.");
//        }
        if (driver != null) {
            driver.quit();
        }
    }

}
