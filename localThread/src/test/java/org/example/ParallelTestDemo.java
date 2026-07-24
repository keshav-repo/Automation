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

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        threadDriver.set(new ChromeDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            driver.quit();
        }
        threadDriver.remove();
    }

    private WebDriver getDriver() {
        WebDriver driver = threadDriver.get();
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            threadDriver.set(driver);
        }
        return driver;
    }

    @Test(description = "Google Search Test", groups = {"regression"})
    public void googleSearchTest() {
        WebDriver driver = getDriver();
        driver.get("https://www.google.co.in/");

        driver.findElement(By.name("q")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().getId() + " - " + driver.getTitle());
    }

    @Test(description = "Yahoo Search Test", groups = {"regression"})
    public void yahooSearchTest() {
        WebDriver driver = getDriver();
        driver.get("https://in.search.yahoo.com/");

        driver.findElement(By.name("p")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().getId() + " - " + driver.getTitle());
    }
}
