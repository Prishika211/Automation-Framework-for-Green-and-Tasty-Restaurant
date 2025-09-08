package com.epam.greenandtasty.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    WebDriver driver;

    public Waits(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElementToBeVisible(WebElement element, int timeInSec){
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(timeInSec));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfElementToBeLocated(By locator,
                                                      int timeInSec){
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(timeInSec));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(WebElement element,
                                            int timeInSec){
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(timeInSec));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}