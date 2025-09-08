package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.BookTablePage;
import com.epam.greenandtasty.ui.pages.CustomerDashboardPage;
import com.epam.greenandtasty.ui.pages.HomePage;
import com.epam.greenandtasty.ui.pages.SignInPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ViewAvailableTablesSteps {
    private static final Logger logger =
            LogManager.getLogger(ViewAvailableTablesSteps.class);

    private final HomePage homePage;
    private final SignInPage signInPage;
    private final CustomerDashboardPage customerDashboardPage;
    private BookTablePage bookTablePage;

    public ViewAvailableTablesSteps(UIContext uiContext){
        this.homePage = new HomePage(uiContext.getWebDriver());
        this.signInPage = new SignInPage(uiContext.getWebDriver());
        this.customerDashboardPage =
                new CustomerDashboardPage(uiContext.getWebDriver());
        this.bookTablePage = new BookTablePage(uiContext.getWebDriver());
    }

    @And("I navigate to the {string} section")
    public void iNavigateToTheSection(String section) {
        bookTablePage = customerDashboardPage.navigateToSection(section);
        Assert.assertTrue(bookTablePage.isPageLoaded(), "Book a Table section" +
                " is not loaded");
        logger.info("Navigated to the {} section", section);
    }

    @Then("the following elements should be present: locations, date, time, guests and Find a Table")
    public void theFollowingElementsShouldBePresentLocationsDateTimeGuestsAndFindATable(DataTable elements) {
        elements.asList().forEach(element -> {
            Assert.assertTrue(bookTablePage.isElementDisplayed(element),
                    element+" is not displayed.");
            logger.info("{} is displayed on the Book a Table page", element);
        });
    }

    @When("I click on the {string} dropdown")
    public void iClickOnTheDropdown(String dropdownName) {
        if("Select Location".equals(dropdownName)){
            bookTablePage.clickLocationDropdown();
            logger.info("Clicked on the {} dropdown",dropdownName);
        }else if ("time".equals(dropdownName)) {
            bookTablePage.clickTimeDropdown();
            logger.info("Clicked on the {} dropdown", dropdownName);
        }
    }

    @Then("the following {string} gets filtered by location")
    public void theFollowingGetsFilteredByLocation(String location) {
        bookTablePage.selectLocation(location);
        logger.info("Location {} is selected and displayed", location);
    }

    @When("I click on the guests input field")
    public void iClickOnTheGuestsInputField() {
        bookTablePage.clickGuestsField();
        logger.info("Clicked on the guests input field");
    }

    @And("I clear the guests field")
    public void iClearTheGuestsField() {
        bookTablePage.clearGuestsField();
        logger.info("Cleared the guests field");
    }

    @And("I type {string} into the guests field")
    public void iTypeIntoTheGuestsField(String guests) {
        bookTablePage.enterGuests(guests);
        logger.info("Typed {} into date field",guests);
    }

    @When("I click on the date input field")
    public void iClickOnTheDateInputField() {
        bookTablePage.clickDateField();
        logger.info("Clicked on date input field");
    }

    @And("I clear the date field")
    public void iClearTheDateField() {
        bookTablePage.clearDateField();
        logger.info("Cleared date field");
    }

    @And("I type {string} into the date field")
    public void iTypeIntoTheDateField(String date) {
        bookTablePage.enterDate(date);
        logger.info("Typed {} into date field", date);
    }

    @And("I select {string} from the time options")
    public void iSelectFromTheTimeOptions(String time) {
        bookTablePage.selectTime(time);
        logger.info("Selected {} from time options", time);
    }

    @And("I select {string} from the locations list")
    public void iSelectFromTheLocationsList(String location) {
        bookTablePage.selectLocation(location);
        logger.info("Location {} is selected and displayed", location);
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String buttonName) {
        if("Find a Table".equals(buttonName)){
            bookTablePage.clickFindTableButton();
            logger.info("Clicked on Find a Table button");
        }
    }

    @Then("I should see the {string} section")
    public void iShouldSeeTheSection(String sectionName) {
        if("Available Tables".equals(sectionName)){
            Assert.assertTrue(bookTablePage.isTablesCardDisplayed(),
                    "Available Tables " +
                    "section is not displayed.");
            logger.info("Available Tables section is displayed");
        }
    }



}
