package com.epam.greenandtasty.ui.factory;

import com.epam.greenandtasty.constants.BrowserWindowSize;
import org.openqa.selenium.WebDriver;

public class WebDriverManagerHandler {
    private WebDriverManagerHandler() {}

    public static void manageWindowSize(WebDriver driver, BrowserWindowSize size) {
        if (driver == null) return;
        switch (size) {
            case MAXIMUM -> driver.manage().window().maximize();
            case MINIMUM -> driver.manage().window().minimize();
            case FULLSCREEN -> driver.manage().window().fullscreen();
        }
    }

    public static void quitDriver(ThreadLocal<WebDriver> threadLocalWebDriver) {
        if (threadLocalWebDriver.get() != null) {
            threadLocalWebDriver.get().quit();
            threadLocalWebDriver.remove();
        }
    }
}
