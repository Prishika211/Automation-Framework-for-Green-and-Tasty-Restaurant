package com.epam.greenandtasty.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestExecutionListener implements ITestListener {
    private static final Logger logger =
            LogManager.getLogger(APITestExecutionListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.info("Starting test: " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.info("Test passed: " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        String methodName = result.getMethod().getMethodName();
        logger.error("Test failed: " + methodName, result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName());
    }
}
