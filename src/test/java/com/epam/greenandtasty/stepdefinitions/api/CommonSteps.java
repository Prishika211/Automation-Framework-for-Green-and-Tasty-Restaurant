package com.epam.greenandtasty.stepdefinitions.api;


import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.utils.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class CommonSteps {
    private static final Logger logger =
            LogManager.getLogger(CommonSteps.class);
    private final APIContext apiContext;

    public CommonSteps(APIContext apiContext) {
        this.apiContext = apiContext;
        
    }

    @Given("I am logged in as a customer with {string} {string}")
    public void CustomerLogin(String email, String password) {
        AuthUtil.login(email,password, false);
        apiContext.setAuthToken(AuthUtil.getCustomerToken());
        logger.info("Logged in as customer {}",ApiReader.getCustomerEmail());
    }

    @Given("I am logged in as a waiter with {string} {string}")
    public void iAmLoggedInAsAWaiterWith(String email, String password) {
        AuthUtil.login(email,password, true);
        apiContext.setAuthToken(AuthUtil.getWaiterToken());
        logger.info("Logged in as waiter {}",ApiReader.getWaiterEmail());
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        apiContext.setResponse(RequestHelper.get(endpoint)); // Use GET method for request
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
       ResponseValidator.validateStatusCode(apiContext.getResponse(), expectedStatusCode); // Check for expected status code
    }

    @And("the response should contain message {string}")
    public void theResponseShouldContainMessage(String expectedMessage) {
        ResponseValidator.validateResponseMessage(apiContext.getResponse(), expectedMessage);
    }

    @And("the response should match the JSON schema {string}")
    public void theResponseShouldMatchTheJSONSchema(String filename) {
        apiContext.getResponse()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(filename));

    }

    @And("the response should contain errorMessage {string}")
    public void theResponseShouldContainErrorMessage(String error) {
        apiContext.getResponse()
                .then()
                .assertThat()
                .body("error", equalTo(error));
    }

    @When("I send a POST request to {string} with random payload")
    public void iSendAPOSTRequestToWithRandomPayload(String endpoint) {
        apiContext.setPayload("");
        apiContext.setResponse(RequestHelper.post(endpoint, apiContext.getPayload()));
    }

    @When("I send a GET request to {string} with authentication")
    public void iSendAGETRequestToWithAuthentication(String endpoint) {
        apiContext.setResponse(RequestHelper.getWithAuth(endpoint, apiContext.getAuthToken()));
        logger.info("I send get request to {} with auth token", endpoint);
    }
}