import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    private By loginField = By.cssSelector("#login_field");
    private By passwordField = By.cssSelector("#password");
    private By signInButton = By.xpath("//input[@value='Sign in']");
    private By heading = By.xpath("//div[contains(@class, 'auth-form-header p-0')]/h1");
    private By error = By.xpath("//div[@id='js-flash-container']//div[@class='container-lg px-2']");
    private By createAccLink = By.xpath("//a[text()='Create an account']");

    public LoginPage typeUsername(String username) {
        driver.findElement(loginField).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage loginWithInvalidCredits(String username,String password) {
        this.typeUsername(username);
        this.typePassword(password);
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }

    public String getErrorText(){
        return driver.findElement(error).getText();
    }

    public SignUpPage createAccount(){
        driver.findElement(createAccLink).click();
        return new SignUpPage(driver);
    }


}
