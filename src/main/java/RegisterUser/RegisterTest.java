package RegisterUser;

import Helpers.DatabaseHelper;
import LoginTest.LoginPage;
import LoginTest.ProfilePage;
import Properties.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterTest {

    public static RegisterPage registerPage;
    public static LoginPage loginPage;

    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void registerTest() {
        loginPage.clickSignUpBtn();
        String name = ConfProperties.getProperty("name");
        String surname = ConfProperties.getProperty("surname");
        String login = ConfProperties.getProperty("login");
        String password = ConfProperties.getProperty("password");

        registerPage.registerUser(name,surname,login,password);

        WebElement registerPageModal = driver.findElement(By.id("alert"));
        Assert.assertTrue(registerPageModal.isDisplayed());

        boolean userExists = DatabaseHelper.isUserExists(name, surname, login, password);
        Assert.assertTrue(userExists, "User isn't in DB");



    }

    @AfterClass
    public void tearDown(){
//        boolean isDeleted = DatabaseHelper.deleteUsers();
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
