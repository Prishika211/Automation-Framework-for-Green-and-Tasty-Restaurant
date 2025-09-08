package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.utils.RequestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class LocationFeedback {

    private static final Logger logger = LogManager.getLogger(LocationFeedback.class);
    private final APIContext apiContext;

    public LocationFeedback(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @And("the response should contain a list of feedbacks")
    public void theResponseShouldContainAListOfFeedbacks() {
        apiContext.getResponse().then().assertThat()
                .body("data.feedbacks.content.size()", greaterThanOrEqualTo(0));
        logger.info("Feedback list validation successful.");
    }

    @When("I send a GET request to {string} with filter type {string}")
    public void iSendAGETRequestToWithFilterType(String endpoint, String type) {
        logger.info("Sending GET request to {} with filter type '{}'", endpoint, type);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("type", type);
        apiContext.setResponse(RequestHelper.getWithQueryParams(endpoint, queryParams));
    }

    @And("the response should contain a list of feedbacks with pagination \\(size={int}, page={int})")
    public void theResponseShouldContainAListOfFeedbacksWithPaginationSizePage(int size, int page) throws JsonProcessingException {
        logger.info("Validating feedback pagination: expected size={}, expected page={}", size, page);

        int actualSize = apiContext.getResponse().jsonPath().get("data.feedbacks.size");
        Assert.assertEquals(actualSize, size,
                "Expected page size "+size+" but got "+actualSize);

        int actualPage = apiContext.getResponse().jsonPath().get("data.feedbacks.number");
        Assert.assertEquals(actualPage, page,
                "Expected page number "+page+"but got "+ actualPage);

        boolean paged = apiContext.getResponse().jsonPath().get("data.feedbacks.pageable.paged");
        Assert.assertTrue(paged, "Expected paged=true but was false");

        logger.info("Pagination details validated successfully.");
    }

    @When("I send a GET request to {string} with filter type {string} \\(page={int}, size={int})")
    public void iSendAGETRequestToWithFilterTypePageSize(String endpoint, String type, int page, int size) {
        logger.info("Sending GET request to {} with type='{}', page={}, size={}", endpoint, type, page, size);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("type", type);
        queryParams.put("page", page);
        queryParams.put("size", size);
        apiContext.setResponse(RequestHelper.getWithQueryParams(endpoint, queryParams));
    }

    @Given("I send a POST request to {string} with filter type {string}")
    public void iSendAPOSTRequestToWithFilterType(String endpoint, String type) {
        logger.info("Sending POST request to {} with filter type '{}'", endpoint, type);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("type", type);
        apiContext.setResponse(RequestHelper.postWithQueryParams(endpoint, "", queryParams));
    }
}
