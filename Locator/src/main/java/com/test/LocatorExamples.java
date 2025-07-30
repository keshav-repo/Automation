package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

/**
 * Java program to demonstrate all Selenium Locator strategies
 * This example shows how to use all 8 types of locators in Selenium 4
 */
public class LocatorExamples {

    public static void main(String[] args) throws InterruptedException {

        // WebDriverManager automatically downloads and sets up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Create Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        // Create WebDriver instance
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Load the demo HTML file
            File htmlFile = new File("src/test/resources/demo.html");
            if (!htmlFile.exists()) {
                // Try alternative paths
                htmlFile = new File("Locator/src/test/resources/demo.html");
                if (!htmlFile.exists()) {
                    htmlFile = new File("target/test-classes/demo.html");
                }
            }

            if (htmlFile.exists()) {
                String filePath = htmlFile.getAbsolutePath();
                driver.get("file://" + filePath);
                System.out.println("Loaded HTML file from: " + filePath);
            } else {
                // If file not found, use a simple online page for demonstration
                System.out.println("HTML file not found, using online demo page instead...");
                driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            }

            System.out.println("=== Selenium Locator Examples ===\n");

            // 1. ID Locator
            demonstrateIdLocator(driver, wait);

            // 2. Name Locator
            demonstrateNameLocator(driver, wait);

            // 3. Class Name Locator
            demonstrateClassNameLocator(driver, wait);

            // 4. Tag Name Locator
            demonstrateTagNameLocator(driver, wait);

            // 5. Link Text Locator
            demonstrateLinkTextLocator(driver, wait);

            // 6. Partial Link Text Locator
            demonstratePartialLinkTextLocator(driver, wait);

            // 7. CSS Selector Locator
            demonstrateCssSelectorLocator(driver, wait);

            // 8. XPath Locator
            demonstrateXPathLocator(driver, wait);

            // Additional examples with form elements
            demonstrateFormElements(driver, wait);

            // Demonstrate hidden elements
            demonstrateHiddenElements(driver, wait);

            // Demonstrate dynamic elements
            demonstrateDynamicElements(driver, wait);

        } finally {
            Thread.sleep(3000);
            driver.quit();
            System.out.println("\nBrowser closed successfully!");
        }
    }

    /**
     * 1. ID Locator - Locates elements whose ID attribute matches the search value
     */
    public static void demonstrateIdLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("1. ID Locator Examples:");
        System.out.println("------------------------");

        try {
            // Find element by ID
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("testuser");
            System.out.println("✓ Found username field by ID and entered text");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("password123");
            System.out.println("✓ Found password field by ID and entered text");

            WebElement loginButton = driver.findElement(By.id("login-btn"));
            System.out.println("✓ Found login button by ID: " + loginButton.getText());

            WebElement submitButton = driver.findElement(By.id("submit-btn"));
            System.out.println("✓ Found submit button by ID: " + submitButton.getText());

            WebElement mainTitle = driver.findElement(By.id("main-title"));
            System.out.println("✓ Found main title by ID: " + mainTitle.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with ID locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 2. Name Locator - Locates elements whose NAME attribute matches the search
     * value
     */
    public static void demonstrateNameLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("2. Name Locator Examples:");
        System.out.println("-------------------------");

        try {
            // Find elements by name
            WebElement fullnameField = driver.findElement(By.name("fullname"));
            fullnameField.sendKeys("John Doe");
            System.out.println("✓ Found fullname field by name and entered text");

            WebElement emailField = driver.findElement(By.name("email"));
            emailField.sendKeys("john.doe@example.com");
            System.out.println("✓ Found email field by name and entered text");

            WebElement messageField = driver.findElement(By.name("message"));
            messageField.sendKeys("This is a test message");
            System.out.println("✓ Found message field by name and entered text");

            // Find multiple elements with same name (like radio buttons)
            List<WebElement> genderOptions = driver.findElements(By.name("gender"));
            System.out.println("✓ Found " + genderOptions.size() + " gender radio buttons by name");

        } catch (Exception e) {
            System.out.println("✗ Error with Name locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 3. Class Name Locator - Locates elements whose class name contains the search
     * value
     */
    public static void demonstrateClassNameLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("3. Class Name Locator Examples:");
        System.out.println("-------------------------------");

        try {
            // Find elements by class name
            List<WebElement> highlightElements = driver.findElements(By.className("highlight"));
            System.out.println("✓ Found " + highlightElements.size() + " highlight elements by class name");

            for (WebElement element : highlightElements) {
                System.out.println("  - " + element.getText());
            }

            List<WebElement> buttons = driver.findElements(By.className("btn-primary"));
            System.out.println("✓ Found " + buttons.size() + " primary buttons by class name");

            List<WebElement> secondaryButtons = driver.findElements(By.className("btn-secondary"));
            System.out.println("✓ Found " + secondaryButtons.size() + " secondary buttons by class name");

        } catch (Exception e) {
            System.out.println("✗ Error with Class Name locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 4. Tag Name Locator - Locates elements whose tag name matches the search
     * value
     */
    public static void demonstrateTagNameLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("4. Tag Name Locator Examples:");
        System.out.println("-----------------------------");

        try {
            // Find elements by tag name
            List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
            System.out.println("✓ Found " + paragraphs.size() + " paragraph elements by tag name");

            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            System.out.println("✓ Found " + buttons.size() + " button elements by tag name");

            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            System.out.println("✓ Found " + inputs.size() + " input elements by tag name");

            List<WebElement> links = driver.findElements(By.tagName("a"));
            System.out.println("✓ Found " + links.size() + " link elements by tag name");

            List<WebElement> headings = driver.findElements(By.tagName("h1"));
            System.out.println("✓ Found " + headings.size() + " h1 elements by tag name");

        } catch (Exception e) {
            System.out.println("✗ Error with Tag Name locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 5. Link Text Locator - Locates anchor elements whose visible text matches the
     * search value
     */
    public static void demonstrateLinkTextLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("5. Link Text Locator Examples:");
        System.out.println("------------------------------");

        try {
            // Find links by exact text
            WebElement googleLink = driver.findElement(By.linkText("Google Search"));
            System.out.println("✓ Found Google link by exact text: " + googleLink.getAttribute("href"));

            WebElement githubLink = driver.findElement(By.linkText("GitHub Repository"));
            System.out.println("✓ Found GitHub link by exact text: " + githubLink.getAttribute("href"));

            WebElement seleniumLink = driver.findElement(By.linkText("Selenium Documentation"));
            System.out.println("✓ Found Selenium link by exact text: " + seleniumLink.getAttribute("href"));

            WebElement geeksLink = driver.findElement(By.linkText("GeeksForGeeks Tutorial"));
            System.out.println("✓ Found GeeksForGeeks link by exact text: " + geeksLink.getAttribute("href"));

        } catch (Exception e) {
            System.out.println("✗ Error with Link Text locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 6. Partial Link Text Locator - Locates anchor elements whose visible text
     * contains the search value
     */
    public static void demonstratePartialLinkTextLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("6. Partial Link Text Locator Examples:");
        System.out.println("--------------------------------------");

        try {
            // Find links by partial text
            WebElement googlePartialLink = driver.findElement(By.partialLinkText("Google"));
            System.out.println("✓ Found link containing 'Google': " + googlePartialLink.getText());

            WebElement githubPartialLink = driver.findElement(By.partialLinkText("GitHub"));
            System.out.println("✓ Found link containing 'GitHub': " + githubPartialLink.getText());

            WebElement seleniumPartialLink = driver.findElement(By.partialLinkText("Selenium"));
            System.out.println("✓ Found link containing 'Selenium': " + seleniumPartialLink.getText());

            WebElement geeksPartialLink = driver.findElement(By.partialLinkText("GeeksForGeeks"));
            System.out.println("✓ Found link containing 'GeeksForGeeks': " + geeksPartialLink.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with Partial Link Text locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 7. CSS Selector Locator - Locates elements matching a CSS selector
     */
    public static void demonstrateCssSelectorLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("7. CSS Selector Locator Examples:");
        System.out.println("--------------------------------");

        try {
            // CSS Selector examples
            WebElement cssInput = driver.findElement(By.cssSelector(".css-input"));
            cssInput.sendKeys("CSS selector test");
            System.out.println("✓ Found CSS input by class selector");

            WebElement cssButton = driver.findElement(By.cssSelector(".css-button"));
            System.out.println("✓ Found CSS button by class selector: " + cssButton.getText());

            WebElement nestedText = driver.findElement(By.cssSelector(".nested-element .nested-text"));
            System.out.println("✓ Found nested text by descendant selector: " + nestedText.getText());

            List<WebElement> listItems = driver.findElements(By.cssSelector(".css-list .list-item"));
            System.out.println("✓ Found " + listItems.size() + " list items by CSS selector");

            WebElement successMessage = driver.findElement(By.cssSelector(".highlight.success"));
            System.out.println("✓ Found success message by compound class selector: " + successMessage.getText());

            WebElement errorMessage = driver.findElement(By.cssSelector(".highlight.error"));
            System.out.println("✓ Found error message by compound class selector: " + errorMessage.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with CSS Selector locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 8. XPath Locator - Locates elements matching an XPath expression
     */
    public static void demonstrateXPathLocator(WebDriver driver, WebDriverWait wait) {
        System.out.println("8. XPath Locator Examples:");
        System.out.println("--------------------------");

        try {
            // XPath examples
            WebElement xpathInput = driver.findElement(By.xpath("//input[@data-testid='xpath-input']"));
            xpathInput.sendKeys("XPath test input");
            System.out.println("✓ Found XPath input by attribute selector");

            WebElement xpathButton = driver.findElement(By.xpath("//button[@data-testid='xpath-button']"));
            System.out.println("✓ Found XPath button by attribute selector: " + xpathButton.getText());

            WebElement xpathText = driver.findElement(By.xpath("//span[@data-testid='xpath-text']"));
            System.out.println("✓ Found XPath text by attribute selector: " + xpathText.getText());

            // XPath with text content
            WebElement titleByText = driver.findElement(By.xpath("//h1[text()='Selenium Locators Demo Page']"));
            System.out.println("✓ Found title by exact text match: " + titleByText.getText());

            // XPath with contains
            WebElement partialText = driver.findElement(By.xpath("//h2[contains(text(),'ID Locator')]"));
            System.out.println("✓ Found heading by partial text: " + partialText.getText());

            // XPath for table data
            List<WebElement> tableRows = driver.findElements(By.xpath("//table[@class='xpath-table']//tr"));
            System.out.println("✓ Found " + tableRows.size() + " table rows by XPath");

            // XPath for specific table cell
            WebElement cellData = driver.findElement(By.xpath("//table[@class='xpath-table']//tr[2]/td[1]"));
            System.out.println("✓ Found specific table cell by XPath: " + cellData.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with XPath locator: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Additional examples with form elements
     */
    public static void demonstrateFormElements(WebDriver driver, WebDriverWait wait) {
        System.out.println("Form Elements Examples:");
        System.out.println("----------------------");

        try {
            // Select dropdown
            WebElement countrySelect = driver.findElement(By.id("country"));
            countrySelect.click();
            driver.findElement(By.xpath("//option[@value='us']")).click();
            System.out.println("✓ Selected country from dropdown");

            // Radio buttons
            WebElement maleRadio = driver.findElement(By.id("male"));
            maleRadio.click();
            System.out.println("✓ Selected male radio button");

            // Checkboxes
            WebElement codingCheckbox = driver.findElement(By.id("coding"));
            codingCheckbox.click();
            System.out.println("✓ Selected coding checkbox");

            WebElement readingCheckbox = driver.findElement(By.id("reading"));
            readingCheckbox.click();
            System.out.println("✓ Selected reading checkbox");

        } catch (Exception e) {
            System.out.println("✗ Error with form elements: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstrate hidden elements
     */
    public static void demonstrateHiddenElements(WebDriver driver, WebDriverWait wait) {
        System.out.println("Hidden Elements Examples:");
        System.out.println("------------------------");

        try {
            // Hidden elements can still be found by Selenium
            WebElement hiddenDiv = driver.findElement(By.id("hidden-div"));
            System.out.println("✓ Found hidden div: " + hiddenDiv.getText());

            WebElement hiddenInput = driver.findElement(By.id("hidden-input"));
            System.out.println("✓ Found hidden input value: " + hiddenInput.getAttribute("value"));

            WebElement hiddenText = driver.findElement(By.id("hidden-text"));
            System.out.println("✓ Found hidden text: " + hiddenText.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with hidden elements: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstrate dynamic elements
     */
    public static void demonstrateDynamicElements(WebDriver driver, WebDriverWait wait) {
        System.out.println("Dynamic Elements Examples:");
        System.out.println("-------------------------");

        try {
            // Click button to show dynamic content
            WebElement showDynamicButton = driver.findElement(By.id("show-dynamic"));
            showDynamicButton.click();
            System.out.println("✓ Clicked button to show dynamic content");

            // Wait for dynamic content to appear
            WebElement dynamicContent = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamic-content")));
            System.out.println("✓ Dynamic content appeared: " + dynamicContent.getText());

            // Find dynamic button
            WebElement dynamicButton = driver.findElement(By.id("dynamic-button"));
            System.out.println("✓ Found dynamic button: " + dynamicButton.getText());

        } catch (Exception e) {
            System.out.println("✗ Error with dynamic elements: " + e.getMessage());
        }
        System.out.println();
    }
}