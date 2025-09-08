package com.epam.greenandtasty.ui.factory;

import com.epam.greenandtasty.constants.BrowserWindowSize;
import com.epam.greenandtasty.exceptions.WebDriverNotFoundException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class WebDriverFactory {
    public static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    private WebDriverFactory() {
    }

    public static WebDriver getWebDriverInstance(String webDriverType) throws WebDriverNotFoundException, IOException {
        if (threadLocalWebDriver.get() == null) {
            threadLocalWebDriver.set(BrowserFactory.createWebDriver(webDriverType));
        }
        return threadLocalWebDriver.get();
    }

    public static WebDriver getRemoteWebDriverInstanceForGrid(String browser, String os) throws WebDriverNotFoundException, IOException {
        return RemoteWebDriverFactory.getGridDriver(browser, os);
    }

    public static WebDriver getRemoteWebDriverInstanceForRemote(String browser, String os) throws WebDriverNotFoundException, IOException {
        return RemoteWebDriverFactory.getRemoteDriver(browser, os);
    }

    public static void withWindowSize(BrowserWindowSize size) {
        WebDriverManagerHandler.manageWindowSize(threadLocalWebDriver.get(), size);
    }

    public static void quitDriver() {
        WebDriverManagerHandler.quitDriver(threadLocalWebDriver);
    }
}
