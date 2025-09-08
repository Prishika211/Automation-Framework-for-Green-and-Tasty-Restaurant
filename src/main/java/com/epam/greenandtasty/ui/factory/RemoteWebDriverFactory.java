package com.epam.greenandtasty.ui.factory;

import com.epam.greenandtasty.exceptions.WebDriverNotFoundException;
import com.epam.greenandtasty.utils.ConfigReader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.epam.greenandtasty.constants.Constant.LAMBDA_TEST_URL;
import static com.epam.greenandtasty.constants.Constant.NODE_URL;

public class RemoteWebDriverFactory {
    private RemoteWebDriverFactory() {}

    public static WebDriver getGridDriver(String browser, String os) throws WebDriverNotFoundException, IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(normalize(browser));
        capabilities.setPlatform(getPlatform(os));
        return new RemoteWebDriver(new URL(NODE_URL), capabilities);
    }

    public static WebDriver getRemoteDriver(String browser, String os) throws WebDriverNotFoundException, IOException {
        return createRemoteDriver(browser, os, LAMBDA_TEST_URL, getLambdaOptions());
    }

    private static WebDriver createRemoteDriver(String browser, String os, String remoteURL, HashMap<String, Object> options) throws WebDriverNotFoundException, IOException {
        WebDriver driver;
        URL url = new URL(remoteURL);

        switch (normalize(browser)) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(getOperatingSystemName(os));
                chromeOptions.setCapability("LT:Options", options);
                driver = new RemoteWebDriver(url, chromeOptions);
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(getOperatingSystemName(os));
                firefoxOptions.setCapability("LT:Options", options);
                driver = new RemoteWebDriver(url, firefoxOptions);
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName(getOperatingSystemName(os));
                edgeOptions.setCapability("LT:Options", options);
                driver = new RemoteWebDriver(url, edgeOptions);
            }
            default -> throw new WebDriverNotFoundException("Unsupported WebDriver type: " + browser);
        }
        return driver;
    }

    private static String normalize(String input) {
        return input.trim().toLowerCase();
    }

    private static HashMap<String, Object> getLambdaOptions() throws IOException {
        HashMap<String, Object> options = new HashMap<>();
        options.put("username", ConfigReader.getValue("lambdaTestUserName"));
        options.put("accessKey", ConfigReader.getValue("lambdaTestAccessKey"));
        options.put("build", "greenandtastyTesting-1.0");
        options.put("project", "greenandtastyTesting");
        options.put("w3c", true);
        options.put("plugin", "java-testNG");
        options.put("tunnel", true);
        return options;
    }

    private static String getOperatingSystemName(String os) throws WebDriverNotFoundException {
        return switch (normalize(os)) {
            case "windows" -> "Windows 11";
            case "mac" -> "macOS Big sur";
            case "linux" -> "Linux";
            default -> throw new WebDriverNotFoundException("Unsupported Operating system type: " + os);
        };
    }

    private static Platform getPlatform(String os) throws WebDriverNotFoundException {
        return switch (normalize(os)) {
            case "windows" -> Platform.WINDOWS;
            case "mac" -> Platform.MAC;
            case "linux" -> Platform.LINUX;
            default -> throw new WebDriverNotFoundException("Unsupported Operating system type: " + os);
        };
    }
}
