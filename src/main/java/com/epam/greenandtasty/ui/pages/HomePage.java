package com.epam.greenandtasty.ui.pages;

import com.epam.greenandtasty.utils.Waits;
import io.cucumber.datatable.DataTable;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.greenandtasty.constants.Constant.WAITING_TIME;

public class HomePage extends BasePage{
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    @FindBy(xpath="//button[.='Sign In']")
    private WebElement signInButton;

    @FindBy(xpath="//a[normalize-space()='Create an Account']")
    private WebElement createAnAccountLink;

    @FindBy(xpath="//img[@alt='Green & Tasty']")
    private WebElement navLogo;

    @Getter
    @FindBy(xpath="//a[.='Main Page']")
    private WebElement mainPage;

    @FindBy(xpath="//a[.='Book a Table']")
    private WebElement bookATableMenu;

    @FindBy(xpath="//div[@class='hidden md:block bg-opacity-50 p-6 rounded-md max-w-md text-white md:pl-0']")
    private WebElement generalInformationSection;

    @FindBy(xpath="//h2[normalize-space()='Green & Tasty']")
    private WebElement generalInformationHeading;

    @FindBy(xpath="//p[normalize-space()='Fresh, healthy, and sustainable cuisine']")
    private WebElement generalInformationParagraph;

    @FindBy(xpath="//button[normalize-space()='View Menu']")
    private WebElement viewMenuButton;

    @FindBy(xpath="//h3[normalize-space()='Locations']")
    private WebElement locationCards;

    @FindBy(xpath="//div[contains(text(),'789 Beach Road, Oceanview, Coastalville, 67890')]")
    private WebElement address;

    @FindBy(xpath="//span[contains(text(), 'Total capacity: ')]")
    private WebElement totalCapacity;

    @FindBy(xpath="//span[contains(text(), 'Average occupancy: ')]")
    private WebElement averageOccupancy;

    @FindBy(xpath = "//section[@class='px-4 py-6 md:px-6']//child::h3[.='Most Popular Dishes']")
    private WebElement staticMenu;



    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getViewMenuButton(){
        return waitForElement(viewMenuButton);
    }



    public SignInPage clickSignIn() {
        waitForElement(signInButton);
        click(signInButton);
        return new SignInPage(webDriver);
    }

    public void verifyNavElements(){
        waitForElement(navLogo);
        waitForElement(mainPage);
        waitForElement(bookATableMenu);
        waitForElement(signInButton);
    }

    public boolean isNavElementDisplayed(String element){
        return switch(element){
            case "Logo" -> navLogo.isDisplayed();
            case "Main Page" -> mainPage.isDisplayed();
            case "Book a Table" -> bookATableMenu.isDisplayed();
            case "Sign In" -> signInButton.isDisplayed();
            default -> false;
        };
    }

    public boolean isGeneralInformationDisplayed(){
        return generalInformationSection.isDisplayed();
    }

    public boolean isGeneralInfoPresent(String element){
        return switch(element){
            case "Name" -> generalInformationHeading.isDisplayed();
            case "Description" -> generalInformationParagraph.isDisplayed();
            case "View Menu" -> viewMenuButton.isDisplayed();
            default -> false;
        };
    }

    public void scrollToSection(String sectionName){
        if("Locations".equals(sectionName)){
            scrollToElement(locationCards);
        }
    }

    public boolean isLocationDetailPresent(String element){
        try {
            return switch(element) {
                case "Address" -> {
                    waitForElement(address);
                    yield address.isDisplayed();
                }
                case "Capacity" -> {
                    waitForElement(totalCapacity);
                    yield totalCapacity.isDisplayed();
                }
                case "Occupancy" -> {
                    waitForElement(averageOccupancy);
                    yield averageOccupancy.isDisplayed();
                }
                default -> false;
            };
        } catch (Exception e) {
            logger.error("Error checking if {} is present: {}", element, e.getMessage());
            return false;
        }
    }

    public void clickOnLocationCard(){
        waitForElement(address);
        click(address);
    }

    public LocationDetailsPage isLocationOverviewDisplayed(){
        return new LocationDetailsPage(webDriver);
    }
//    @Override
//    public boolean verifyPage() {
//        return false;
//    }
}
