package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;

public class SelectedLocationDetailsSteps {

    private final APIContext apiContext;
    private static final Logger logger = LogManager.getLogger(SelectedLocationDetailsSteps.class);

    public SelectedLocationDetailsSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }



    @Then("the response body should contain a list of location details")
    public void theResponseBodyShouldContainAListOfLocationDetails() {
        apiContext.getResponse().then().body("locations", not(empty()));// Ensure locations list is not empty
        logger.info("The response contain list of locations");

    }

    @Then("the response body should be an empty list")
    public void theResponseBodyShouldBeAnEmptyList() {
        apiContext.getResponse().then().body("locations", is(empty()));// Ensure locations list is empty
        logger.info("The response contain empt locations list");
    }


}
