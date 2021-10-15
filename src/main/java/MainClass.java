import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {

        String pathGeckoDriver = "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", pathGeckoDriver);
        String pathChromeDriver = "C:\\Users\\Master\\IdeaProjects\\1testSelemium\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://github.com/");
//        MainPage mainPage = new MainPage(driver);
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        mainPage.register("dfsdfs2@sdf.com");




    }

}
