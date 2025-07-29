package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Simple example matching the original BrowserCommands structure
 * Updated for Selenium 4 with WebDriverManager
 */
public class SimpleBrowserExample {

    public static void main(String[] args) throws InterruptedException {

        // WebDriverManager automatically downloads and sets up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create an instance of ChromeDriver (launch the Chrome browser)
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Define the URL to visit
        String URL = "https://www.geeksforgeeks.org/";

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Open the specified URL in the browser
        driver.get(URL);
        System.out.println("Visited GeeksForGeeks");

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Getting the Title of the URL
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Getting the Page Source
        String pageSource = driver.getPageSource();
        System.out.println("Page Source Length: " + pageSource.length() + " characters");
        System.out.println("Page Source Preview (first 200 chars): "
                + pageSource.substring(0, Math.min(200, pageSource.length())) + "...");

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Getting current URL before clicking
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentURL);

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Getting the current URL after the click
        currentURL = driver.getCurrentUrl();
        System.out.println("Current URL after click: " + currentURL);

        // Waiting for 2 seconds
        Thread.sleep(2000);

        // Close the current tab
        driver.close();
        System.out.println("Browser tab closed");

        // Close all the tabs or windows of the browser
        driver.quit();
        System.out.println("All browser windows closed");
    }
}