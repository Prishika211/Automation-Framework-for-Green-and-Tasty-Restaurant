package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.utils.RequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAvailableTablesSteps {

    private static final Logger logger = LogManager.getLogger(ViewAvailableTablesSteps.class);
    private final APIContext apiContext;
    private final ObjectMapper objectMapper;

    public ViewAvailableTablesSteps(APIContext apiContext) {
        this.apiContext = apiContext;
        this.objectMapper = new ObjectMapper();
    }

    @When("I send a GET request to {string} with query params locationId {string} date {string} time {string} guests {string}")
    public void iSendAGETRequestToWithQueryParamsLocationIdDateTimeGuests(String endpoint, String locationID, String date, String time, String guests) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("locationId", locationID);
        queryParams.put("date", date);
        queryParams.put("time", time);
        queryParams.put("guests", guests);

        apiContext.setResponse(RequestHelper.getWithQueryParams(endpoint, queryParams));
        logger.info("GET request sent to {} with query params: {}", endpoint, queryParams);
        logger.debug("Response received: {}", apiContext.getResponse().asString());
    }

    @And("the response should contain a list of available tables {string} filtered by all query parameters")
    public void theResponseShouldContainAListOfAvailableTablesFilteredByAllQueryParameters(String expectedTableCount) {
        try {
            List<Map<String, Object>> tables =
                    apiContext.getResponse().jsonPath().getList("$");
            Assert.assertNotNull(tables, "Table list is null.");
            int expected = Integer.parseInt(expectedTableCount);
            Assert.assertEquals(tables.size(), expected, "Mismatch in expected table count.");
            logger.info("Validated available tables list with expected size: {}", expected);
        } catch (Exception e) {
            logger.error("Error validating available tables list", e);
            throw new RuntimeException("Failed to validate table list", e);
        }

    }

}
