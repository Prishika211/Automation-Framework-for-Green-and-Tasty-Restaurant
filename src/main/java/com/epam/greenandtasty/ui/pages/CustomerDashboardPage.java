package com.epam.greenandtasty.ui.pages;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerDashboardPage extends BasePage {
    private static final Logger logger =
            LogManager.getLogger(CustomerDashboardPage.class);

    @FindBy(xpath = "//a[.='Book a Table']")
    private WebElement bookTableLink;
    @Getter
    @FindBy(xpath = "//a[.='Main Page']")
    private WebElement mainPage;

    @FindBy(xpath="//button[normalize-space()='View Menu']")
    private WebElement viewMenuButton;

    public WebElement getViewMenuButton(){
        return viewMenuButton;
    }



    public CustomerDashboardPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public boolean isTabVisible(String section) {
        try {
            waitForElementBy(By.xpath("//a[text" +
                    "()='" + section + "']"));
            WebElement tabElement = webDriver.findElement(By.xpath("//a[text" +
                    "()='" + section + "']"));
            return tabElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public BookTablePage navigateToSection(String section){
        try{
            if("Book a Table".equals(section)){
                click(bookTableLink);
                logger.info("Navigated to Book a Table section");
            }else{
                logger.warn("Unknown section: {}", section);
            }
        } catch(Exception e){
            logger.error("Failed to navigate to section {}: {}", section,
                    e.getMessage());
            throw e;
        }
        return new BookTablePage(webDriver);
    }
}
