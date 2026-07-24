package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static volatile boolean wdmConfigured;

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
        ensureWebDriverManagerConfigured();
        drivers.set(new ChromeDriver());
    }

    public static void quitDriver() {
        WebDriver driver = drivers.get();
        if (driver != null) {
            driver.quit();
        }
        drivers.remove();
    }

    private static void ensureWebDriverManagerConfigured() {
        if (!wdmConfigured) {
            synchronized (DriverManager.class) {
                if (!wdmConfigured) {
                    WebDriverManager.chromedriver().setup();
                    wdmConfigured = true;
                }
            }
        }
    }
}
