package com.epam.greenandtasty.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.epam.greenandtasty.constants.Constant.WAITING_TIME;

public abstract class BasePage {
    protected WebDriver webDriver;


    public BasePage(WebDriver webwebDriver) {
        this.webDriver = webwebDriver;
        PageFactory.initElements(webwebDriver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void typeDate(WebElement element, String text) {
//        element.click();
//        element.sendKeys(text);

          String[] parts = text.split("-");

//        String htmlDateFormat = parts[2] + "-" + parts[1] + "-" + parts[0]; // "2025-04-28"
//        Actions actions = new Actions(webDriver);
//        actions.moveToElement(element).click().sendKeys(htmlDateFormat).build().perform();

          element.sendKeys(parts[2]);
          element.sendKeys(Keys.TAB);
          element.sendKeys(parts[0]);
          element.sendKeys(parts[1]);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    protected WebElement waitForElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(WAITING_TIME));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    protected WebElement waitForElementBy(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(WAITING_TIME));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webDriver.findElement(by);
    }

    public void scrollToElement(WebElement element){
        try{
            waitForElement(element);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            jsExecutor.executeScript("arguments[0].scrollIntoView" +
                    "();", element);
        }catch(Exception e){
            throw new RuntimeException("Failed to scroll to element: "+element, e);
        }
    }


//    public abstract boolean verifyPage();
}
