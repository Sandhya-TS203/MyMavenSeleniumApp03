package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class App {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://automationexercise.com");

            // Click Products page
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

            // Find first Add to Cart button
            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@data-product-id='1']")));

            // Scroll and click using JavaScript (avoids ad overlay)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            // Wait for confirmation modal
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

            // Click View Cart
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Cart"))).click();

            System.out.println("Product successfully added to cart!");

        } catch (Exception e) {
            System.out.println("TEST FAILED: " + e.getMessage());
        }

        System.out.println("Browser will remain open for inspection.");
    }
}
