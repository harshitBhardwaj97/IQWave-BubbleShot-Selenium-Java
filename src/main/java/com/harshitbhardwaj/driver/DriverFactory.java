package com.harshitbhardwaj.driver;

import org.openqa.selenium.WebDriver;

/**
 * @author Harshit Bhardwaj
 */
public class DriverFactory {

    private DriverFactory() {
        throw new AssertionError("Can't instantiate DriverFactory class.");
    }

    public static WebDriver initLocalWebDriver(String browser) {
        System.out.println("Initializing WebDriver for browser: " + browser);
        WebDriver driver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        System.out.println("Driver created: " + driver);
        return driver;
    }
}
