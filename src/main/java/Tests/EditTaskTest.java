package Tests;

import Helpers.DatabaseHelper;
import Pages.EditPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Helpers.ConfProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class EditTaskTest {

    public static LoginPage loginPage;
    public static EditPage editPage;
    public static ProfilePage profilePage;

    public static RemoteWebDriver driver;

    public static URL browser_url;

    public static ChromeOptions options;
    protected static final Logger LOGGER = LogManager.getLogger();


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
        editPage = new EditPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("LOGIN_PAGE"));
    }

    @Test
    public void editTaskTest() {


        loginPage.inputLogin(ConfProperties.getProperty("LOGIN"));
        loginPage.inputPassword(ConfProperties.getProperty("PASSWORD"));
        loginPage.clickLoginBtn();

        profilePage.clickEditBtn();


        String taskTitle = ConfProperties.getProperty("TASK_EDIT_TITLE");
        String taskDescription = ConfProperties.getProperty("TASK_EDIT_DESCRIPTION");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("TASK_EDIT_STATUS"));
        String taskDate = ConfProperties.getProperty("TASK_EDIT_DATE");


        editPage.editTask(taskTitle, taskDescription, taskStatus);
        boolean taskExists = DatabaseHelper.isTaskExists(taskTitle, taskDescription, taskStatus);
        System.out.println(taskExists);
        LOGGER.info(taskExists);
        Assert.assertTrue(taskExists, "Task isn't in DB");
    }






}
