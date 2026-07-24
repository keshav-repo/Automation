package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParallelTestDemo {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Test(description = "Google Search Test", groups = {"regression"})
    public void googleSearchTest() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.google.co.in/");

        driver.findElement(By.name("q")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().getId() + " - " + driver.getTitle());
    }

    @Test(description = "Yahoo Search Test", groups = {"regression"})
    public void yahooSearchTest() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://in.search.yahoo.com/");

        driver.findElement(By.name("p")).sendKeys("Hello World", Keys.ENTER);

        System.out.println("Thread: " + Thread.currentThread().getId() + " - " + driver.getTitle());
    }
}
