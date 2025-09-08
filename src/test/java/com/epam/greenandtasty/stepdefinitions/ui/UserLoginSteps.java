package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.CustomerDashboardPage;
import com.epam.greenandtasty.ui.pages.SignInPage;
import com.epam.greenandtasty.ui.pages.WaiterDashboardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class UserLoginSteps {

    private static final Logger logger = LogManager.getLogger(UserLoginSteps.class);

    private SignInPage signInPage;
    private final UIContext uiContext;
    private CustomerDashboardPage customerDashboardPage;
    private WaiterDashboardPage waiterDashboardPage;

    public UserLoginSteps(UIContext uiContext) {
        this.uiContext = uiContext;
        this.signInPage = new SignInPage(uiContext.getWebDriver());
    }

    @When("I click on the Sign In button with empty fields")
    public void iClickOnTheSignInButtonWithEmptyFields() {
        signInPage.enterEmail(""); // Clear or leave blank the email field
        signInPage.enterPassword("");
        signInPage.clickSignInButton();
        logger.info("Clicked the Sign In button with empty fields.");
    }

    @Then("I should see the following error messages:")
    public void iShouldSeeTheFollowingErrorMessages(DataTable dataTable) {
        dataTable.asMap().entrySet().forEach(errors -> {
            String fieldName = errors.getKey();
            String expectedMessage = errors.getValue();
            System.out.println(fieldName);
            String actualMessage = signInPage.getFieldErrorMessage(fieldName);
            logger.info("Validating error message for '{}' field. Expected: '{}', Actual: '{}'", fieldName, expectedMessage, actualMessage);
            Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch for " + fieldName + " field.");
        });
    }

    @When("I enter {string} into the Email field")
    public void iEnterIntoTheEmailField(String email) {
        signInPage.enterEmail(email);
        logger.info("Entered email: {}", email);
    }

    @And("I enter {string} into the Password field")
    public void iEnterIntoThePasswordField(String password) {
        signInPage.enterPassword(password);
        logger.info("Entered password.");
    }

    @And("I click on the Sign In button")
    public void iClickOnTheSignInButton() {
        signInPage.clickSignInButton();
        logger.info("Clicked the Sign In button.");
    }


    @Then("I should see the {string} tab on the dashboard page")
    public void iShouldSeeTheTabOnTheDashboardPage(String reservations) {
        customerDashboardPage = signInPage.isRedirectedToCustomerDashboardPage();
        Assert.assertTrue(customerDashboardPage.isTabVisible(reservations),
                "The '" + reservations + "' " +
                        "tab is not visible.");
        logger.info("Successfully validated the presence of the '{}' tab.", reservations);
    }

    @Then("I should be redirected to waiter dashboard page")
    public void iShouldBeRedirectedToWaiterDashboardPage() {
        waiterDashboardPage = signInPage.isRedirectedToWaiterDashboardPage();
        Assert.assertTrue(waiterDashboardPage.isHeadingVisible(), "The " +
                "heading is not visible");
        logger.info("Successfully redirected to the Waiter Dashboard page");
    }

    @Then("I should not see the {string} tab on the dashboard page")
    public void iShouldNotSeeTheTabOnTheDashboardPage(String reservations) {
        customerDashboardPage = signInPage.isRedirectedToCustomerDashboardPage();
        Assert.assertFalse(customerDashboardPage.isTabVisible(reservations),
                "The '" + reservations + "' " +
                        "tab is not visible.");
        logger.info("Successfully validated the presence of the '{}' tab.", reservations);
    }

}