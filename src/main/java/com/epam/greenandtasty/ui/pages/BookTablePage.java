package com.epam.greenandtasty.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class BookTablePage extends BasePage {
    private static final Logger logger =
            LogManager.getLogger(BookTablePage.class);

    @FindBy(xpath = "//a[.='Book a Table']")
    private WebElement bookTableLink;

    @FindBy(xpath = "//option[.='Diagon Alley, Umbridge Street, Platform 9 and 3 quarters.']/parent::select")
    private WebElement locationDropdown;

    @FindBy(xpath = "//option[starts-with(@value, 'LOC')]")
    private List<WebElement> locationOptions;

    @FindBy(xpath = "//input[@type='date' and @class='w-full outline-none']")
    private WebElement dateField;

    @FindBy(xpath = "//option[.='Select Time']/parent::select")
    private WebElement timeDropdown;

    @FindBy(css = "select.w-full.outline-none > option[value *= ':']")
    private List<WebElement> timeOptions;

    @FindBy(css = "input[type='number']")
    private WebElement guestsField;

    @FindBy(xpath = "//button[.='Find a Table']")
    private WebElement findTableButton;

    @FindBy(xpath = "//h3[.='Avaliable Tables']")
    private WebElement availableTablesSection;

    @FindBy(xpath = "//h3[.='Avaliable Tables']/following-sibling::div")
    private WebElement tableCards;

    @FindBy(xpath = "//p[.='Please select a particular location, date, and " +
            "time to know the available tables.']")
    private WebElement tableNotFoundMessage;

    @FindBy(xpath = "//button[.='+ Show all']")
    private WebElement clickShowAllButton;

    @FindBy(xpath = "//div[@class='data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 data-[state=closed]:zoom-out-95 data-[state=open]:zoom-in-95 fixed top-[50%] left-[50%] z-50 grid w-full translate-x-[-50%] translate-y-[-50%] gap-4 border duration-200 sm:max-w-lg max-w-md p-4 rounded-lg bg-white shadow-lg']")
    private WebElement allSlotsAvailable;

    @FindBy(xpath = "//h2[.='Available slots']/following::button[contains(., ':')]")
    private List<WebElement> availableTimeSlots;

    @FindBy(xpath = "//h2[.='Make a Reservation']/ancestor::div[@role = 'dialog']")
    private WebElement reservationForm;

    @FindBy(xpath = "//nav//a[text()='Reservations']")
    private WebElement reservationsTab;

    @FindBy(xpath = "//button[.='Make a Reservation']")
    private WebElement reservationButton;

    @FindBy(xpath = "//h2[.='Reservation Confirmed!']/ancestor::div[@role='dialog']")
    private WebElement confirmationMessage;

    public BookTablePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        try {
            return waitForElement(bookTableLink).isDisplayed();
        } catch (Exception e) {
            logger.error("Book Table page is not loaded: {}", e.getMessage());
            return false;
        }
    }

    public boolean isElementDisplayed(String elementName) {
        try {
            return switch (elementName) {
                case "Location" ->
                        waitForElement(locationDropdown).isDisplayed();
                case "Date" -> waitForElement(dateField).isDisplayed();
                case "Time" -> waitForElement(timeDropdown).isDisplayed();
                case "Guests" -> waitForElement(guestsField).isDisplayed();
                case "Find a Table" ->
                        waitForElement(findTableButton).isDisplayed();
                default -> {
                    logger.warn("Unknown element: {}", elementName);
                    yield false;
                }
            };
        } catch (Exception e) {
            logger.error("Failed to check if element is displayed: {}",
                    e.getMessage());
            return false;
        }
    }

    public void clickLocationDropdown() {
        waitForElement(locationDropdown);
        click(locationDropdown);
        logger.info("Clicked on location dropdown");
    }

    public void selectLocation(String location) {
        clickLocationDropdown();
        for (WebElement option : locationOptions) {
            if (option.getText().equals(location)) {
                click(option);
                logger.info("Selected location: {}", location);
                return;
            }
        }
        logger.warn("Location not found: {}", location);
    }

    public void clickDateField() {
        waitForElement(dateField);
        click(dateField);
        logger.info("Clicked on date field");
    }

    public void clearDateField() {
        dateField.clear();
        logger.info("Clear date field");
    }

    public void enterDate(String date) {
        typeDate(dateField, date);
        logger.info("Entered date: {}", date);
    }

    public void clickTimeDropdown() {
        waitForElement(timeDropdown);
        click(timeDropdown);
        logger.info("Clicked on the time dropdown");
    }

    public void selectTime(String time) {
        clickTimeDropdown();
        for (WebElement option : timeOptions) {
            if (option.getText().equals(time)) {
                click(option);
                logger.info("Selected time: {}", time);
                return;
            }
        }
        logger.warn("Time not found: {}", time);
    }

    public void clickGuestsField() {
        waitForElement(guestsField);
        click(guestsField);
        logger.info("Clicked on guests field");
    }

    public void clearGuestsField() {
        guestsField.clear();
        logger.info("Clear guests field");
    }

    public void enterGuests(String guests) {
        type(guestsField, guests);
        logger.info("Entered guests: {}", guests);
    }

    public void clickFindTableButton() {
        waitForElement(findTableButton);
        click(findTableButton);
        logger.info("Clicked Find a Table button");
    }

    public boolean isTablesCardNotDisplayed() {
        try {
            return waitForElement(availableTablesSection).isDisplayed() && waitForElement(tableNotFoundMessage).isDisplayed();
        } catch (Exception e) {
            logger.error("Available Tables section is not displayed: {}",
                    e.getMessage());
            return false;
        }
    }

    public boolean isTablesCardDisplayed() {
        try {
            return waitForElement(availableTablesSection).isDisplayed() && !waitForElement(tableNotFoundMessage).isDisplayed();
        } catch (Exception e) {
            logger.error("Available Tables section is displayed: {}",
                    e.getMessage());
            return true;
        }
    }

    public void clickShowAllOptions() {
        waitForElement(clickShowAllButton);
        click(clickShowAllButton);
        logger.info("Clicked on Show all options button");
    }

    public boolean areAllSlotsVisible() {
        try {
            waitForElement(allSlotsAvailable);
            return allSlotsAvailable.isDisplayed();
        } catch (Exception e) {
            logger.error("Error checking if all slots are visible: {}", e.getMessage());
            return false;
        }
    }

    public void clickOnFirstAvailableTimeSlot() {
        try {
            if (!availableTimeSlots.isEmpty()) {
                click(availableTimeSlots.get(4));
                logger.info("Clicked on fifth available time slot");
            } else {
                logger.warn("No available time slots found");
                throw new RuntimeException("No available time slots found");
            }
        } catch (Exception e) {
            logger.error("Error clicking on first available time slot: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isReservationFormDisplayed() {
        try {
            return waitForElement(reservationForm).isDisplayed();
        } catch (Exception e) {
            logger.error("Reservation form is not displayed: {}",
                    e.getMessage());
            return false;
        }
    }

    public void submitReservation() {
        click(reservationButton);
        logger.info("Submitted reservation");
    }

    public boolean isConfirmationMessageDisplayed() {
        try {
            return waitForElement(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            logger.error("Confirmation message is not loaded: {}",
                    e.getMessage());
            return false;
        }

    }

    public ReservationsPage isRedirectedToReservationsPage() {
        waitForElement(reservationsTab);
        click(reservationsTab);
        return new ReservationsPage(webDriver);
    }
}