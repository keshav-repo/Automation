package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParallelTestDemo {

    protected ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        threadDriver.set(new ChromeDriver());
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            driver.quit();
        }
        threadDriver.remove();
    }

    @Test(description = "Google Search Test", groups = {"regression"})
    public void googleSearchTest() {
        WebDriver driver = threadDriver.get();
        driver.get("https://www.google.co.in/");

        driver.findElement(By.name("q")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().threadId() + " - " + driver.getTitle());
    }

    @Test(description = "Yahoo Search Test", groups = {"regression"})
    public void yahooSearchTest() {
        WebDriver driver = threadDriver.get();
        driver.get("https://in.search.yahoo.com/");

        driver.findElement(By.name("p")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().threadId() + " - " + driver.getTitle());
    }
}
