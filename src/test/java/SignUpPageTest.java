import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignUpPageTest{

    WebDriver driver;
    SignUpPage signUpPage;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void signUpWithIncorrectEmail() {
        signUpPage.typeEmailAddress("rrrrertrr");
        String errorEmail = signUpPage.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", errorEmail);
    }

    @Test
    public void signUpWithShortPass() {
        signUpPage.typeEmailAddress("rrrrertrr@r.com");
        signUpPage.typePassword("sss");
        String errorPassword = signUpPage.getPasswordErrorText();
        Assert.assertEquals("Password is too short", errorPassword);
    }

    @Test
    public void signUpWithShortUserName() {
        signUpPage.typeEmailAddress("rrrrertrr@r.com");
        signUpPage.typePassword("ssFA234JIOMs");
        signUpPage.typeUserName("aa");
        String errorUserName = signUpPage.getUserNameErrorText();
        Assert.assertEquals("Username aa is not available.", errorUserName);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
