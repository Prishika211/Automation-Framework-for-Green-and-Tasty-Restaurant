package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetAllLocationsSteps {
    private final APIContext apiContext;
    private static final Logger logger = LogManager.getLogger(GetAllLocationsSteps.class);

    public GetAllLocationsSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response should contain a list of locations")
    public void theResponseShouldContainAListOfLocations(){
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> locations = jsonPath.getList("data.locations");

        logger.info("Checking if the locations list is not empty: {}", locations);
        Assert.assertNotNull(locations, "Locations list is null");
        Assert.assertFalse(locations.isEmpty(), "Expected a non-empty list of " +
                "locations.");
    }

}
