package Tests;

import Pages.LoginPage;
import Pages.ProfilePage;
import Helpers.ConfProperties;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LogoutTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;

    public static RemoteWebDriver driver;

    public static URL browser_url;

    public static ChromeOptions options;
    private static WebDriverWait wait;



    @BeforeClass
    public static void setup() throws MalformedURLException {

        browser_url = new URL(ConfProperties.getProperty("BROWSER_URL"));

        options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме
        options.addArguments("--no-sandbox"); // Отключение sandbox режима
        options.addArguments("--disable-dev-shm-usage"); // Уменьшение использования /dev/shm
        options.addArguments("--disable-gpu"); // Отключение GPU
        options.addArguments("--window-size=1366,768");


        driver = new RemoteWebDriver(browser_url, options);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("LOGIN_PAGE"));
    }

    @Test
    public void logoutTest() {


        loginPage.inputLogin(ConfProperties.getProperty("LOGIN"));
        loginPage.inputPassword(ConfProperties.getProperty("PASSWORD"));
        loginPage.clickLoginBtn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        profilePage.clickLogoutBtn();
        WebElement loginPageElement = driver.findElement(By.cssSelector("a.nav-link[href='/login']"));
        Assert.assertTrue(loginPageElement.isDisplayed());

    }


    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
