package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.BaseTest;
import com.harshitbhardwaj.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Harshit Bhardwaj
 */
public class CommonTests extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CommonTests.class);

    private final By nameRequiredHeading = By
            .xpath("//p[.='Please Enter Your Name']");
    private final By startGameButton = By.xpath("//button[.='Start Game']");

    @Test
    public void nameIsRequiredToStartTheGame() {
        logger.info("******** nameIsRequiredToStartTheGame test started ********");

        WebDriverManager.getDriver().findElement(startGameButton).click();
        Assert.assertTrue(WebDriverManager.getDriver().findElement(nameRequiredHeading).isDisplayed());

        logger.info("******** nameIsRequiredToStartTheGame test completed ********");
    }
}
