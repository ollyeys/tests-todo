package Tests;

import Helpers.DatabaseHelper;
import Pages.LoginPage;
import Pages.ProfilePage;
import Pages.NewTaskPage;
import Helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateTaskTest {

    public static LoginPage loginPage;
    public static NewTaskPage newTaskPage;
    public static ProfilePage profilePage;

    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        newTaskPage = new NewTaskPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void createTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        profilePage.clickAddBtn();

        String taskTitle = ConfProperties.getProperty("taskTitle");
        String taskDescription = ConfProperties.getProperty("taskDescription");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("taskStatus"));
        String taskDate = ConfProperties.getProperty("taskDate");

        System.out.println(taskTitle + taskDescription + taskStatus + taskDate);

        newTaskPage.createTask(taskTitle, taskDescription, taskStatus, taskDate);
        boolean taskExists = DatabaseHelper.isTaskExists(taskTitle, taskDescription, taskStatus);
        Assert.assertTrue(taskExists, "Task isn't in DB");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
