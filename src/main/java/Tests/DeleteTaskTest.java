package Tests;

import Helpers.DatabaseHelper;
import Pages.LoginPage;
import Pages.ProfilePage;
import Helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DeleteTaskTest {

    public static WebDriver driver;

    public static LoginPage loginPage;
    public static ProfilePage profilePage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }


    @Test
    public static void deleteTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();

        String taskTitle = ConfProperties.getProperty("taskEditTitle");
        String taskDescription = ConfProperties.getProperty("taskEditDescription");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("taskEditStatus"));
        String taskDate = ConfProperties.getProperty("taskEditDate");

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
