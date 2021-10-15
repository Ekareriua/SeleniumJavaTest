import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    WebDriver driver;
    static WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By headingH1 = By.xpath("//div[@class='d-flex flex-auto flex-justify-center pt-12']//h1");
    private By headingSpan = By.xpath("//span[@class='text-mono text-gray-mktg']");

    private By emailField = By.cssSelector("#email");
    private By emailContinue = By.xpath("//div[@id='email-container']//button");
    private By emailError = By.xpath("//p[@id='email-err']/p");

    private By passwordField = By.cssSelector("#password");
//    private By passwordField = By.id("password");
    private By passwordContinue = By.xpath("//*[@id='password-container']//button[contains(text(), 'Continue')]");
    private By passwordError = By.xpath("//p[@id='password-err']/p[1]");

    private By usernameField = By.cssSelector("#login");
//    private By username = By.id("login");
    private By usernameContinue = By.xpath("//div[@id='username-container']//button[contains(text(), 'Continue')]");
    private By usernameError = By.xpath("//p[@id='login-err']/div/div");

//    Would you like to receive product updates and announcements via email?
    private By optionField = By.cssSelector("#opt_in");
    private By optionContinue = By.xpath("//div[@id='opt-in-container']//button");


    public SignUpPage typeEmailAddress(String email) {
        wait = new WebDriverWait(driver, 10);
        driver.findElement(emailField).sendKeys(email);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(emailContinue));
        } catch (Exception e) {
            e.printStackTrace();
        }
            driver.findElement(emailContinue).click();

        return this;
    }

    public SignUpPage typePassword(String password) {
        wait = new WebDriverWait(driver, 10);
        driver.findElement(passwordField).sendKeys(password);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(passwordContinue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(passwordContinue).click();
        return this;
    }

    public SignUpPage typeUserName(String username) {
        wait = new WebDriverWait(driver, 5);
        driver.findElement(usernameField).sendKeys(username);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(usernameContinue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(usernameContinue).click();
        return this;
    }

    public SignUpPage typeOption(String option) {
        driver.findElement(optionField).sendKeys(option);
        driver.findElement(optionContinue).click();
        return this;
    }

    public SignUpPage registerWithInvalidCredits(String email, String password, String username, String option) {
        this.typeEmailAddress(email);
        this.typePassword(password);
        this.typeUserName(username);
        this.typeOption(option);
        return new SignUpPage(driver);

    }

    public String getHeadingText() {
        return driver.findElement(headingH1).getText();
//        return driver.findElement(headingSpan).getText();
    }

    public String getEmailErrorText() {
        return driver.findElement(emailError).getText();
    }

    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }

    public String getUserNameErrorText() {
        return driver.findElement(usernameError).getText();
    }

}
