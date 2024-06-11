package Tests;

import Helpers.DatabaseHelper;
import Pages.EditPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EditTaskTest {

    public static LoginPage loginPage;
    public static EditPage editPage;
    public static ProfilePage profilePage;

    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        editPage = new EditPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void editTaskTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        profilePage.clickEditBtn();


        String taskTitle = ConfProperties.getProperty("taskEditTitle");
        String taskDescription = ConfProperties.getProperty("taskEditDescription");
        Boolean taskStatus = Boolean.valueOf(ConfProperties.getProperty("taskEditStatus"));
        String taskDate = ConfProperties.getProperty("taskEditDate");



        editPage.editTask(taskTitle, taskDescription, taskStatus, taskDate);
        boolean taskExists = DatabaseHelper.isTaskExists(taskTitle, taskDescription, taskStatus);
        Assert.assertTrue(taskExists, "Task isn't in DB");
    }






}
