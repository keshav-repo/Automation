package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = drivers.get();
        if (driver == null) {
            initDriver();
            driver = drivers.get();
        }
        return driver;
    }

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        drivers.set(new ChromeDriver());
    }

    public static void quitDriver() {
        WebDriver driver = drivers.get();
        if (driver != null) {
            driver.quit();
        }
        drivers.remove();
    }
}
