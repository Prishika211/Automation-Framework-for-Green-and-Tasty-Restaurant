package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.HomePage;
import com.epam.greenandtasty.ui.pages.SignInPage;
import com.epam.greenandtasty.ui.pages.UserProfileRegistration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserProfileRegistrationSteps {
    private static final Logger logger =
            LogManager.getLogger(UserProfileRegistrationSteps.class);

    private UserProfileRegistration userProfileRegistration;
    private HomePage homePage;
    private SignInPage signInPage;
    private final UIContext uiContext;

    public UserProfileRegistrationSteps(UIContext uiContext) {
        this.uiContext = uiContext;
    }

    @Given("I am on the Home page")
    public void iAmOnTheHomePage() {
        homePage = new HomePage(uiContext.getWebDriver());

        logger.info("User is on the Home page");
    }

    @And("I click on the Sign In button on HomePage")
    public void iClickOnTheButton() {
        signInPage = homePage.clickSignIn();
        logger.info("Clicked on Sign In button and navigated to Sign In page");
    }

    @And("I click on the Create an Account link")
    public void iClickOnTheLink() {
        userProfileRegistration = signInPage.clickCreateAnAccountLink();
    }

    @When("I fill in the {string} field with {string}")
    public void iFillInTheFieldWith(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "first name":
                userProfileRegistration.fillFirstName(value);
                break;
            case "last name":
                userProfileRegistration.fillLastName(value);
                break;
            case "email":
                userProfileRegistration.fillEmail(value);
                break;
            case "password":
                userProfileRegistration.fillPassword(value);
                break;
            case "confirm password":
                userProfileRegistration.fillConfirmPassword(value);
                break;
        }
    }

    @And("I click on the Create an Account button")
    public void iClickOnTheCreateAnAccountButton() throws InterruptedException {
        signInPage = userProfileRegistration.clickCreateAccount();
    }

    @Then("I should be redirected to the Sign In page")
    public void iShouldBeRedirectedToTheSignInPage() {
        boolean isSignInPageDisplayed = signInPage.verifyPage();
        Assert.assertTrue(isSignInPageDisplayed, "The Sign In page was not displayed successfully.");
    }

    @Then("I should see an error message of {string}")
    public void iShouldSeeAnErrorMessageSaying(String expectedMessage) {
        // Locate the element using the expected message text
        By errorMessageLocator = By.xpath("//*[contains(text(),'" + expectedMessage + "')]");

        // Check if the error message element exists
        boolean isMessageDisplayed = false;
        String actualMessage = "";

        try {
            WebElement errorElement = uiContext.getWebDriver().findElement(errorMessageLocator);
            actualMessage = errorElement.getText(); // Fetch the actual text displayed on the page
            isMessageDisplayed = actualMessage.equals(expectedMessage); // Compare actual and expected messages
        } catch (NoSuchElementException e) {
            System.out.println("Error message element was not found on the page.");
        }

        // Debugging: Log the actual message for verification
        System.out.println("Actual error message displayed: " + actualMessage);

        // Assert with precise details
        Assert.assertTrue(isMessageDisplayed, "Error message incorrect. Expected: '" + expectedMessage + "', but Found: '" + actualMessage + "'");
    }

    @Then("I shouldn't  be redirected to the Sign In page")
    public void iShouldnTBeRedirectedToTheSignInPage() {
        boolean isRegistrationPageDisplayed = userProfileRegistration.verifyPage();
        Assert.assertTrue(isRegistrationPageDisplayed, "The Sign In page was not " +
                "displayed successfully.");
    }
}