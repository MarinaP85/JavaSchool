import com.codeborne.selenide.Configuration;
import com.sber.selenium.MainPage;
import com.sber.selenium.PremierePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public WebDriver driver;
    public MainPage mainPage;
    public PremierePage premierePage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        premierePage = new PremierePage();
        Configuration.headless=true;
        Configuration.timeout=30000;
    }

    @AfterTest
    public void tearDriver() {
        driver.quit();
    }
}
