package com.epam.greenandtasty.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class UITestExecutionListener implements ITestListener {
    private static final Logger logger =
            LogManager.getLogger(UITestExecutionListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {} in class {}",
                result.getMethod().getMethodName(),
                result.getTestClass().getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {} in class {}",
                result.getMethod().getMethodName(),
                result.getTestClass().getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("Test failed: {} in class {}. Exception: {}",
                result.getMethod().getMethodName(),
                result.getTestClass().getName(),
                result.getThrowable().getMessage(),
                result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {} in class {}",
                result.getMethod().getMethodName(),
                result.getTestClass().getName());

    }
}

