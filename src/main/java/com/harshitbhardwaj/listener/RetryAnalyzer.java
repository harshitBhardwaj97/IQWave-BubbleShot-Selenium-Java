package com.harshitbhardwaj.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Harshit Bhardwaj
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int maxRetryCount = 3;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: " +
                    result.getName() + " for the " + retryCount + " time(s).");
            return true;
        }
        return false;
    }
}
