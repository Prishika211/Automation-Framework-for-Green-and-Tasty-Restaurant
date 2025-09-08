package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import io.cucumber.java.en.And;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class MenuBrowsingSteps {
    private final APIContext apiContext;
    private static final Logger logger =
            LogManager.getLogger(MenuBrowsingSteps.class);

    public MenuBrowsingSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response should contain a list of dishes with details")
    public void theResponseShouldContainAListOfDishesWithDetails(){
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> dishes = jsonPath.getList("data.dishes");

        logger.info("Checking if the dishes list is not empty: {}", dishes);
        Assert.assertNotNull(dishes, "Dishes list is null");
        Assert.assertFalse(dishes.isEmpty(), "Expected a non-empty list of " +
                "dishes.");
    }

    @And("the response should contain dish details with details")
    public void theResponseShouldContainDishDetailsWithDetails() {
        JsonPath jsonPath = apiContext.getResponse().jsonPath();
        List<?> dish = jsonPath.getList("data.dishes");

        logger.info("Checking if the dish description list is not empty: {}",
                dish);
        Assert.assertNotNull(dish, "Dish description list is null");
        Assert.assertFalse(dish.isEmpty(), "Expected a non-empty list of " +
                "dish description.");
    }
}
