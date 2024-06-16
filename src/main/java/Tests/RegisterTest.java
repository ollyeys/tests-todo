package Tests;

import Helpers.DatabaseHelper;
import Pages.LoginPage;
import Pages.RegisterPage;
import Helpers.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class RegisterTest {

    public static RegisterPage registerPage;
    public static LoginPage loginPage;

    public static RemoteWebDriver driver;

    public static URL browser_url;
    public static ChromeOptions options;


    @BeforeClass
    public static void setup() throws MalformedURLException {

        browser_url = new URL(ConfProperties.getProperty("BROWSER_URL"));


        options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме
        options.addArguments("--no-sandbox"); // Отключение sandbox режима
        options.addArguments("--window-size=1366,768");
//        options.addArguments("--disable-dev-shm-usage"); // Уменьшение использования /dev/shm
//        options.addArguments("--disable-gpu"); // Отключение GPU


        driver = new RemoteWebDriver(browser_url, options);

        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("LOGIN_PAGE"));
    }

    @Test
    public void registerTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage.clickSignUpBtn();
        String name = ConfProperties.getProperty("NAME");
        String surname = ConfProperties.getProperty("SURNAME");
        String login = ConfProperties.getProperty("LOGIN");
        String password = ConfProperties.getProperty("PASSWORD");

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
