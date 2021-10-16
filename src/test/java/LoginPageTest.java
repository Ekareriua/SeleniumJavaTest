import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    static WebDriverWait wait;

    @Before
    public void setUp(){
//        String pathGeckoDriver = "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\geckodriver.exe";
//        System.setProperty("webdriver.gecko.driver", pathGeckoDriver);
//        String pathChromeDriver = "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
//        driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithEmptyCreditsTest() {
        LoginPage newLoginPage = loginPage.loginWithInvalidCredits("", "");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void loginWithIncorrectCredsTest() {
        LoginPage newLoginPage = loginPage.loginWithInvalidCredits("qweqwe", "");
        String error = newLoginPage.getErrorText();
        Assert.assertEquals("Incorrect username or password.", error);
    }

    @Test
    public void createAccTest() {
        SignUpPage signUpPage = loginPage.createAccount();
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals("Welcome to GitHub! Let's begin the adventure", heading);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
