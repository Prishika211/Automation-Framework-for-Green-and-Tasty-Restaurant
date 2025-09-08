package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.WaiterDashboardPage;
import com.epam.greenandtasty.ui.pages.WaiterReservationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TableReservationWaiter {
    private final UIContext uiContext;
    private final WaiterDashboardPage waiterDashboardPage;
    private final WaiterReservationPage waiterReservationPage;
    private static final Logger logger =
            LogManager.getLogger(TableReservationWaiter.class);


    public TableReservationWaiter(UIContext uiContext){
        this.uiContext = uiContext;
        this.waiterDashboardPage = new WaiterDashboardPage(uiContext.getWebDriver());
        this.waiterReservationPage = new WaiterReservationPage(uiContext.getWebDriver());

    }

    @When("Waiter navigates to reservation management panel")
    public void Waiter_navigates_to_reservation_management_panel(){
        waiterDashboardPage.isHeadingVisible();
        waiterDashboardPage.clickOnReservations();
    }



    @Then("Waiter sees reservation controls create new reservation")
    public void waiterSeesReservationControlsCreateNewReservation() {
        Assert.assertEquals(waiterReservationPage.getCreateNewReservationBtn().getText(), "+ Create New Reservation");

    }






    @When("Waiter clicks {string}")
    public void waiterClicks(String buttonName) {
        waiterReservationPage.getCreateNewReservationBtn().click();
        System.out.println(waiterReservationPage.getCreateNewReservationBtn().isEnabled());
        logger.info("click on create new reservation");

    }

    @And("waiter selects the date {string}")
    public void waiterSelectsTheDate(String date) {
        waiterReservationPage.getDate().click();
        waiterReservationPage.getDate().sendKeys(date);
        logger.info("select date");
    }

    @And("Waiter chooses {string}")
    public void waiterChooses(String customertype) {
        if(customertype.equals("Customer"))
            waiterReservationPage.getCustomerRadioBtn().click();
        else
            waiterReservationPage.getVisitorRadioBtn().click();
        logger.info("select customer type {}", customertype);

    }



    @And("Waiter chooses number of guests {int}")
    public void waiterChoosesNumberOfGuests(int guests) {
        while(guests>0) {
            waiterReservationPage.getIncreaseGuestsCount().click();
            guests--;
        }
        logger.info("select guests {}",guests);

    }

    @And("Waiter selects from time {string}")
    public void waiterSelectsFromTime(String time) {
        waiterReservationPage.selectTime(time);
      logger.info("select time {}",time);


    }

    @And("Waiter choose a table {string}")
    public void waiterChooseATable(String tableNumber) {
        waiterReservationPage.selectTable(tableNumber);
        logger.info("select table {}",tableNumber);


    }

    @And("Waiter clicks on make reservationBtn")
    public void waiterClicksOnMakeReservationBtn() {
        waiterReservationPage.getMakeAReservationBtn().click();

    }

    @Then("Reservation is created and marked as {string}")
    public void reservationIsCreatedAndMarkedAs(String arg0) {

    }

    @And("waiter enter customer email {string}")
    public void waiterEnterCustomerEmail(String email) {
        waiterReservationPage.getEmailInput().click();
        waiterReservationPage.getEmailInput().sendKeys(email);

    }
}
