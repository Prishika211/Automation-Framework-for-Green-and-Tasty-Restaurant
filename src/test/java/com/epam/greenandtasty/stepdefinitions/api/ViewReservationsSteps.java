package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class ViewReservationsSteps {

    private final APIContext apiContext;
    private static final Logger logger = LogManager.getLogger(ViewReservationsSteps.class);

    public ViewReservationsSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response should contain a list of reservations for the user")
    public void theResponseShouldContainAListOfReservationsForTheUser() throws JsonProcessingException {
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> reservations = jsonPath.getList("$");

        logger.info("Checking if reservations list is not empty: {}", reservations);
        Assert.assertNotNull(reservations, "Reservations list is null.");
        Assert.assertFalse(reservations.isEmpty(), "Expected a non-empty list of reservations.");
    }

    @And("the response should contain an empty list with no reservations")
    public void theResponseShouldContainAnEmptyListWithNoReservations() {
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> reservations = jsonPath.getList("$");

        logger.info("Checking if reservations list is empty: {}", reservations);
        Assert.assertNotNull(reservations, "Reservations list is null.");
        Assert.assertTrue(reservations.isEmpty(), "Expected an empty list of " +
                "reservations.");
    }
}
