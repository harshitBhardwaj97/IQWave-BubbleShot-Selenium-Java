package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * @author Harshit Bhardwaj
 */
public class XpathLocatorsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(XpathLocatorsTest.class);

    private final By nameInput = By.xpath("//input[@placeholder='Your Name']");
    private final By startGameButton = By.xpath("//button[.='Start Game']");
    private final By leaderBoardHeading = By.xpath("//h1[.='LEADERBOARD']");

    // To dynamically locate the target element
    private final String currentTargetTemplate =
            "//div[contains(@class,'w-10 h-10 flex')]/p[.='%s']";

    private final By currentScore =
            By.xpath("//div[@class='min-w-full']//ul//li[3]//span");
    private final By finalScore = By
            .xpath("//span[contains(@class,'bg-green-200 px-2')]");
    private By target = By.xpath("//div[@class='min-w-full']//ul//li[1]//span");
    private By currentTimeLeft =
            By.xpath("//div[@class='min-w-full']//ul//li[2]//span");

    private JavascriptExecutor jsExecutor;

    @Test
    public void getScoreUsingXpathLocators() throws InterruptedException {
        logger.info("******** getScoreUsingXpathLocators test started ********");

        jsExecutor = (JavascriptExecutor) WebDriverManager.getDriver();
        WebDriverManager.getDriver().findElement(nameInput).sendKeys("Harshit");
        WebDriverManager.getDriver().findElement(startGameButton).click();

        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(leaderBoardHeading));

        boolean isTimeUp = false;

        while (!isTimeUp) {
            try {
                currentTimeLeft = By.xpath("//div[@class='min-w-full']//ul//li[2]//span");
                target = By.xpath("//div[@class='min-w-full']//ul//li[1]//span");

                isTimeUp = WebDriverManager.getDriver().findElement(currentTimeLeft).getText().equals("0");

                // Dynamically create the target element's XPath and click it
                WebElement elementToHit = WebDriverManager.getDriver().findElement(By.xpath(String.format(currentTargetTemplate, WebDriverManager.getDriver().findElement(target).getText())));

                // Click the element using JavaScriptExecutor for faster interaction
                jsExecutor.executeScript("arguments[0].click();", elementToHit);

                // Log current status using the logger
                logger.info("{} seconds left, {} scored",
                        WebDriverManager.getDriver().findElement(currentTimeLeft).getText(),
                        WebDriverManager.getDriver().findElement(currentScore).getText());

            } catch (StaleElementReferenceException e) {
                logger.warn("Element became stale, retrying...");
                continue;  // Retry the loop if the element becomes stale
            }
        }

        // Wait for final score and then log it
        wait.until(ExpectedConditions.visibilityOfElementLocated(finalScore));
        logger.info("Final Score using xpath locators - {}",
                WebDriverManager.getDriver().findElement(finalScore).getText());
        logger.info("-------------------------------------------------------------");
        logger.info("******** getScoreUsingXpathLocators test completed ********");
    }

}
