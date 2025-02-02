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
public class CSSLocatorsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CSSLocatorsTest.class);

    private final By nameInput = By.cssSelector("[placeholder='Your Name']");
    private final By startGameButton = By.xpath("//button[.='Start Game']");
    private final By leaderBoardHeading = By.cssSelector("[href='/leader-board'] h1");

    // To dynamically locate the target element
    private final String currentTargetTemplate =
            "//div[contains(@class,'w-10 h-10 flex')]/p[.='%s']";

    private final By currentScore = By.cssSelector(".min-w-full ul li:nth-child(3) span");
    private final By finalScore = By.cssSelector(".bg-green-200.px-2");
    private By target = By.cssSelector(".min-w-full ul li:nth-child(1) span");
    private By currentTimeLeft = By.cssSelector(".min-w-full ul li:nth-child(2) span");

    private JavascriptExecutor jsExecutor;

    @Test
    public void getScoreUsingCSSLocators() throws InterruptedException {
        logger.info("******** getScoreUsingCSSLocators test started ********");
        jsExecutor = (JavascriptExecutor) WebDriverManager.getDriver();
        WebDriverManager.getDriver().findElement(nameInput).sendKeys("Harshit");
        WebDriverManager.getDriver().findElement(startGameButton).click();

        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(leaderBoardHeading));

        boolean isTimeUp = false;

        while (!isTimeUp) {
            try {
                currentTimeLeft = By.cssSelector(".min-w-full ul li:nth-child(2) span");
                target = By.cssSelector(".min-w-full ul li:nth-child(1) span");

                isTimeUp = WebDriverManager.getDriver().findElement(currentTimeLeft).getText().equals("0");

                // Dynamically create the target element's XPath and click it
                WebElement elementToHit = WebDriverManager.getDriver()
                        .findElement(By.xpath(String.format(currentTargetTemplate,
                                WebDriverManager.getDriver().findElement(target).getText())));

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
        logger.info("Final Score using css locators - {}",
                WebDriverManager.getDriver().findElement(finalScore).getText());
        logger.info("-------------------------------------------------------------");
        logger.info("******** getScoreUsingCSSLocators test completed ********");
    }
}
