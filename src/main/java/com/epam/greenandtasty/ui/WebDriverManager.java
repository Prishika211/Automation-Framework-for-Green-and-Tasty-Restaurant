package com.epam.greenandtasty.ui;

import com.epam.greenandtasty.exceptions.WebDriverNotFoundException;
import com.epam.greenandtasty.ui.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.epam.greenandtasty.utils.ConfigReader.getValue;

public class WebDriverManager {
    private WebDriverManager() {
    }
    public static WebDriver getWebDriver(String browser, String os) throws IOException,
            WebDriverNotFoundException {

        String executionEnvironment = getValue("execution_environment").toLowerCase();

        switch (executionEnvironment) {
            case "remote" -> {
                return WebDriverFactory.getRemoteWebDriverInstanceForRemote(browser, os);
            }
            case "grid" -> {
                return WebDriverFactory.getRemoteWebDriverInstanceForGrid(browser, os);
            }
            case "local" -> {
                return WebDriverFactory.getWebDriverInstance(browser);
            }
            default -> throw new IllegalArgumentException("Unsupported execution environment: " + executionEnvironment);
        }
    }

    public static void quitWebDriver() {
        WebDriverFactory.quitDriver();
    }
}
