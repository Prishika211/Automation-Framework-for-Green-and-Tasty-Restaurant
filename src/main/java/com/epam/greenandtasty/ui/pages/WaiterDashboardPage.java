package com.epam.greenandtasty.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WaiterDashboardPage extends BasePage{
    private static final Logger logger =
                LogManager.getLogger(CustomerDashboardPage.class);
    @FindBy(xpath = "//span[@class='bg-muted flex size-full items-center justify-center rounded-full text-xs']")
    private WebElement waiterProfileIcon;

    @FindBy(xpath = "//h2[@class= 'text-xl font-semibold']")
    private WebElement waiterDashboardheading;

    @FindBy(xpath="//a[.='Reservations']")
    private WebElement reservations;

    @FindBy(xpath="//a[.='Menu']")
    private WebElement menu;


    public WaiterDashboardPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHeadingVisible() {
        try {
            waitForElement(waiterDashboardheading);
            return waiterDashboardheading.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickOnReservations(){
        reservations.click();
    }

    public void clickOnMenu(){
        menu.click();
    }



}
