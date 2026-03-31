import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class App {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://automationexercise.com");

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/products']"))).click();

            WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//a[contains(text(),'Add to cart')])[1]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

            System.out.println("✅ Success");

        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANT
        }

        driver.quit();
    }
}
