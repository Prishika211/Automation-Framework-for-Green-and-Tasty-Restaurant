package com.epam.greenandtasty.ui.factory;

import com.epam.greenandtasty.exceptions.WebDriverNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {
    private BrowserFactory() {}

    public static WebDriver createWebDriver(String webDriverType) throws WebDriverNotFoundException {
        String normalizedType = normalize(webDriverType);
        return switch (normalizedType) {
            case "chrome" -> new ChromeDriver();
            case "safari" -> new SafariDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new WebDriverNotFoundException("Unsupported WebDriver type: " + normalizedType);
        };
    }

    private static String normalize(String input) {
        return input.trim().toLowerCase();
    }
}
