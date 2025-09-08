package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class SpecialityDishesInALocationSteps {

    private static final Logger logger = LogManager.getLogger(SpecialityDishesInALocationSteps.class);
    private final APIContext apiContext;

    public SpecialityDishesInALocationSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response should contain a list of speciality dishes")
    public void theResponseShouldContainAListOfSpecialityDishes() {
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> dishes = jsonPath.getList("data.dishes");

        logger.info("Checking if speciality dishes list is not empty: {}", dishes);
        Assert.assertNotNull(dishes, "Speciality dishes list is null.");
        Assert.assertFalse(dishes.isEmpty(), "Expected a non-empty list of speciality dishes.");
    }

    @And("the response should contain an empty list")
    public void theResponseShouldContainAnEmptyList() {
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?>  dishes = jsonPath.getList("data.dishes");

        logger.info("Checking if speciality dishes list is empty: {}", dishes);
        Assert.assertNotNull(dishes, "Speciality dishes list is null.");
        Assert.assertTrue(dishes.isEmpty(), "Expected an empty list of speciality dishes.");
    }
}
