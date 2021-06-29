import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {
    @Test
    public void test1() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@type=\"text\"]"));
        searchInput.sendKeys("sberbank");
        searchInput.submit();
//      searchInput.sendKeys(Keys.RETURN);
        WebElement targetText = driver.findElement(By.xpath("//h3[text()=\"Частным клиентам — СберБанк\"]"));
        Assert.assertTrue(targetText.isDisplayed());
        driver.quit();
    }
}
