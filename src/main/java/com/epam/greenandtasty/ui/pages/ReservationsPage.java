package com.epam.greenandtasty.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ReservationsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(BookTablePage.class);

    @FindBy(xpath = "//span[.='RESERVED']")
    private WebElement reservedStatus;

    @FindBy(xpath = "//button[.='Cancel']")
    private WebElement cancelOptions;

    @FindBy(xpath = "//button[.='Yes, Cancel']")
    private WebElement yesCancel;

    public ReservationsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void cancelReservation() {
        try {
            waitForElement(cancelOptions);
            click(cancelOptions);
            logger.info("Checked if Cancel button is enabled");
        } catch (TimeoutException e) {
            logger.warn("Status RESERVED not found");
        }
    }

    public void clickYesCancel() {
        waitForElement(yesCancel);
        click(yesCancel);
        logger.info("Clicked on Yes,Cancel button");
    }

    public String getReservationStatus() {
        try {
            waitForElement(reservedStatus);
            return reservedStatus.getText();
        } catch (Exception e) {
            logger.error("Error getting reservation status: {}", e.getMessage());
            return "";
        }
    }
}
