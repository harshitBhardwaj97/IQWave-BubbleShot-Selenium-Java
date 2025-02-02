package com.harshitbhardwaj.driver;

import org.openqa.selenium.WebDriver;

/**
 * @author Harshit Bhardwaj
 */
public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverManager() {
        throw new AssertionError("Can't instantiate DriverManager class.");
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalArgumentException("Set appropriate driver first, using setDriver");
        }
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        System.out.println("Setting WebDriver: " + driver);
        if (driver == null) {
            throw new IllegalArgumentException("driver cannot be null");
        }
        WebDriverManager.driver.set(driver);
    }

    public static void quitDriver() {
        WebDriverManager.driver.get().quit();
        driver.remove();
    }
}
