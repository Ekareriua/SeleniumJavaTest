import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;
    static WebDriverWait wait;

    @Before
    public void setUp() {
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
        driver.get("https://github.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Sign in')]")));
        mainPage = new MainPage(driver);
    }

    @Test
    public void signInTest() {
        LoginPage loginPage = mainPage.clickSignIn();
        String heading = loginPage.getHeadingText();
        Assert.assertEquals("Sign in to GitHub", heading);
    }

    @Test
    public void registerFailTest() {
        SignUpPage signUpPage = mainPage.clickSignUp();
        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        signUpPage.typeEmailAddress("aaaaaaaa");
        String error = signUpPage.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", error);
    }

    @Test
    public void signUpEmptyEmailTest() {
        SignUpPage signUpPage = mainPage.clickSignUp();
        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        WebElement emailContinue = driver.findElement(By.xpath("//div[@id='email-container']//button"));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        signUpPage.typeEmailAddress("");
        boolean emailContinueButton = emailContinue.isEnabled();
        Assert.assertFalse("email Continue Button is Enabled", emailContinueButton);
    }

    @Test
    public void registerSignUpInvalidPasswordTest() {
        SignUpPage signUpPage = mainPage.register("fsgd@sds.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='email-container']//button")));
        driver.findElement(By.xpath("//div[@id='email-container']//button")).click();
        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        signUpPage.typePassword("ssss");
        String error = signUpPage.getPasswordErrorText();
        Assert.assertEquals("Password is too short", error);
    }

    @Test
    public void signUpWithInvalidEmailTest() {
        SignUpPage signUpPage = mainPage.clickSignUp();
        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        signUpPage.typeEmailAddress("rrrrertrr");
        String errorEmail = signUpPage.getEmailErrorText();
        Assert.assertEquals("Email is invalid or already taken", errorEmail); //rrrrrr
    }

    @Test
    public void signUpWithInvalidPasswordTest() {
        SignUpPage signUpPage = mainPage.clickSignUp();
        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        signUpPage.typeEmailAddress("rrrrertrr@r.com");

        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        signUpPage.typePassword("sss");
        String errorPassword = signUpPage.getPasswordErrorText();
        Assert.assertEquals("Password is too short", errorPassword); //aa
    }

    @Test
    public void signUpWithInvalidUserNameTest() {
        SignUpPage signUpPage = mainPage.clickSignUp();
        WebElement emailField = driver.findElement(By.cssSelector("#email"));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        signUpPage.typeEmailAddress("rrrrertrr@r.com");

        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        signUpPage.typePassword("ssFA234JIOMs");

        WebElement usernameField = driver.findElement(By.cssSelector("#login"));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        signUpPage.typeUserName("aa");
        String errorUserName = signUpPage.getUserNameErrorText();
        Assert.assertEquals("Username aa is not available.", errorUserName);
//
//        By usernameContinue = By.xpath("//div[@id='username-container']//button[contains(text(), 'Continue')]");
//        boolean usernameContinueEn = driver.findElement(usernameContinue).isEnabled();
//        Assert.assertFalse("username Continue is Enabled", usernameContinueEn);

//        Assert.assertTrue("username Continue is DisEnabled", usernameContinueEn);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
