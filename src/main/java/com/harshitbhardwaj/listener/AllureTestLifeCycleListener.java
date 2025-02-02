package com.harshitbhardwaj.listener;

import com.harshitbhardwaj.driver.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author Harshit Bhardwaj
 */
public class AllureTestLifeCycleListener implements ITestListener {

    public AllureTestLifeCycleListener() {
    }

    // Attach logs as text
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // Attach screenshots
    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // This method is called when the test fails or is broken.
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            WebDriver driver = WebDriverManager.getDriver();
            if (driver != null) {
                saveScreenshot(driver); // Attach the screenshot to Allure
            }
            saveTextLog(result.getName() + " failed"); // Attach log of failure
            Allure.addAttachment("Test Failure", "Test " + result.getName() + " failed.");
        }
    }

    // This method is called when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            Allure.addAttachment("Test Skipped", "Test " + result.getName() + " was skipped.");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            Allure.addAttachment("Test Success", "Test " + result.getName() + " completed successfully.");
        }
    }

    // This method is called when a test starts
    @Override
    public void onTestStart(ITestResult result) {
        Allure.addAttachment("Test Started", "Test " + result.getName() + " started.");
    }
}
