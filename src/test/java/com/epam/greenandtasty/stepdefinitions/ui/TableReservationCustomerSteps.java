package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.BookTablePage;
import com.epam.greenandtasty.ui.pages.CustomerDashboardPage;
import com.epam.greenandtasty.ui.pages.ReservationsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class TableReservationCustomerSteps {
    private static final Logger logger =
            LogManager.getLogger(TableReservationCustomerSteps.class);

    private CustomerDashboardPage customerDashboardPage;
    private BookTablePage bookTablePage;
    private ReservationsPage reservationsPage;

    public TableReservationCustomerSteps(UIContext uiContext) {
        this.customerDashboardPage =
                new CustomerDashboardPage(uiContext.getWebDriver());
        this.bookTablePage = new BookTablePage(uiContext.getWebDriver());
        this.reservationsPage = new ReservationsPage(uiContext.getWebDriver());
    }

    @When("I click on {string}")
    public void iClickOn(String buttonText) {
        if ("+Show all".equals(buttonText)) {
            bookTablePage.clickShowAllOptions();
            logger.info("Clicked on +Show all options button");
        } else {
            logger.warn("Unknown button text: {}", buttonText);
        }
    }

    @Then("all available slots should be visible")
    public void allAvailableSlotsShouldBeVisible() {
        Assert.assertTrue(bookTablePage.areAllSlotsVisible(), "Not all available slots " +
                "are visible");
        logger.info("All available slots are visible");
    }

    @When("I click on a preferred time slot")
    public void iClickOnAPreferredTimeSlot() {
        bookTablePage.clickOnFirstAvailableTimeSlot();
        logger.info("Clicked on a preferred time slot");
    }

    @Then("the reservation form should be displayed")
    public void theReservationFormShouldBeDisplayed() {
        Assert.assertTrue(bookTablePage.isReservationFormDisplayed(),
                "Reservation form is not " +
                "displayed");
        logger.info("Reservation form is displayed with required fields");
    }

    @When("I submit the reservation")
    public void iSubmitTheReservation() {
        bookTablePage.submitReservation();
        logger.info("Submitted the reservation");
    }

    @Then("a confirmation message should be displayed")
    public void aConfirmationMessageShouldBeDisplayed() {
        Assert.assertTrue(bookTablePage.isConfirmationMessageDisplayed(),
                "Confirmation message is not displayed");
        logger.info("Confirmation message is displayed");
    }

    @Then("Then I shouldn't see the {string} section")
    public void thenIShouldnTSeeTheSection(String sectionName) {
        if("Available Tables".equals(sectionName)){
            Assert.assertTrue(bookTablePage.isTablesCardNotDisplayed(),
                    "Available Tables section is displayed.");
            logger.info("Available Tables section isn't  displayed");
        }
    }

    @When("I click on the reservations page")
    public void iClickOnTheReservationsPage() {
        reservationsPage = bookTablePage.isRedirectedToReservationsPage();
        logger.info("Redirected to Reservations page");
    }

    @And("the cancel option should be enabled")
    public void theCancellationOptionShouldBeEnabled() {
        reservationsPage.cancelReservation();
        logger.info("Clicked on Cancel button");
    }

    @Then("the reservation status should be shown as {string}")
    public void theReservationStatusShouldBeShownAs(String status) {
        String actualStatus = reservationsPage.getReservationStatus();
        Assert.assertEquals(actualStatus, status, "Reservation status is not " +
                "shown as " + status);
        logger.info("Reservation status is correctly shown as {}", status);
    }

    @Then("I clicked on Yes,Cancel")
    public void iClickedOnYesCancel() {
        reservationsPage.clickYesCancel();
        logger.info("Cancelled reservation successfully");
    }
}
