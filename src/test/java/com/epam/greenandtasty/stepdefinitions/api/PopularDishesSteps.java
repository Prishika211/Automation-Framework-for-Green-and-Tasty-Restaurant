package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PopularDishesSteps {

    private final APIContext apiContext;
    private static final Logger logger = LogManager.getLogger(PopularDishesSteps.class);

    public PopularDishesSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response body should contain a list of popular dishes")
    public void theResponseShouldContainListOfPopularDishes() {

        List<Object> dishes = apiContext.getResponse().jsonPath().getList("data.dishes");

        if (dishes == null) {
            logger.error("Failed to extract dishes. JSON path 'data.dishes' returned null.");
            return;
        }

        logger.debug("Number of popular dishes received: {}", dishes.size());

        assertTrue(!dishes.isEmpty(), "Expected non-empty popular dishes list, but got "+dishes.size()+"items.");

        logger.info("Popular dishes list validation successful.");
    }
}
