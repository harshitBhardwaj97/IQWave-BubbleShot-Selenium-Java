package com.harshitbhardwaj.base;

import com.harshitbhardwaj.driver.DriverFactory;
import com.harshitbhardwaj.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.harshitbhardwaj.config.ConfigurationManager.configuration;
import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

/**
 * BaseTest class which initializes WebDriver and manages the lifecycle of the WebDriver.
 * This class is inherited by test classes.
 */
public class BaseTest {

    // ThreadLocal ensures each test method has its own WebDriver instance
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Setup method to initialize WebDriver before each test.
     */
    @BeforeMethod
    public void setup() {
        // Initialize the WebDriver based on the configuration (e.g., browser type)
        WebDriver initializedDriver = DriverFactory.initLocalWebDriver(configuration().browser());

        // Set the WebDriver to WebDriverManager for further use
        WebDriverManager.setDriver(initializedDriver);

        // Assign to ThreadLocal driver for current thread/test
        driver.set(WebDriverManager.getDriver());

        // Log the WebDriver instance for debugging purposes
        System.out.println("WebDriver initialized: " + driver.get());

        // Navigate to base URL
        driver.get().get(BASE_URL);
    }

    /**
     * Tear down method to quit the WebDriver after each test.
     */
    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            // Quit the WebDriver session after the test is complete
            WebDriverManager.quitDriver();
        }
    }
}
