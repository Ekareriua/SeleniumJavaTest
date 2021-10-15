import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signInButton = By.xpath("//a[contains(text(), 'Sign in')]");

    private By signUpButton = By.xpath("//div[@class='d-lg-flex flex-items-center px-3 px-lg-0 text-center text-lg-left']/*[contains(text(), 'Sign up')]");

    private By emailAddress = By.xpath("//dd/input[@id='user_email']");

    private By signUpButtonByEmail = By.xpath("//button[contains(text(), 'Sign up for GitHub')]");

//    @FindBy (xpath = "//input[@type='text']")
//    private WebElement searchField;

    private By searchField = By.xpath("//input[@type='text']");
    

    public LoginPage clickSignIn(){
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUp(){
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }

    public SignUpPage clickSignUpButtonByEmail(){
        driver.findElement(signUpButtonByEmail).click();
        return new SignUpPage(driver);
    }

    public MainPage typeEmailAddress(String email) {
        driver.findElement(emailAddress).sendKeys(email);
        return this;
    }

    public MainPage typeTextToSearchField(String textField) {
        driver.findElement(searchField).sendKeys(textField);
        driver.findElement(searchField).sendKeys(Keys.ENTER);
        return this;
    }


    public SignUpPage register(String email) {
        this.typeEmailAddress(email);
        this.clickSignUpButtonByEmail();
        return new SignUpPage(driver);
    }


}
