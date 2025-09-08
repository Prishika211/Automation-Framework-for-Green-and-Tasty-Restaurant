package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.models.feeback.FeedbackRequest;
import com.epam.greenandtasty.utils.RequestHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CustomerFeedbackSteps {
    private static final Logger logger = LogManager.getLogger(CustomerFeedbackSteps.class);
    private final APIContext apiContext;

    public CustomerFeedbackSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    @When("I send a POST request to {string} with the following feedback details:")
    public void iSendAPOSTRequestToWithTheFollowingFeedbackDetails(String endpoint, DataTable dataTable) {
        Map<String, String> feedbackData = dataTable.asMap(String.class, String.class);

        FeedbackRequest feedbackRequest = new FeedbackRequest.FeedbackRequestBuilder()
                .setReservationId(feedbackData.get("reservationId"))
                .setServiceRating(feedbackData.get("serviceRating"))
                .setServiceComment(feedbackData.get("serviceComment"))
                .setCuisineRating(feedbackData.get("cuisineRating"))
                .setCuisineComment(feedbackData.get("cuisineComment"))
                .build();

        apiContext.setPayload(feedbackRequest);

        if (apiContext.getAuthToken() != null) {
            apiContext.setResponse(RequestHelper.postWithAuth(endpoint, feedbackRequest, apiContext.getAuthToken()));
            logger.info("Sent POST request to {} with authentication and feedback payload: {}", endpoint, feedbackRequest);
        } else {
            apiContext.setResponse(RequestHelper.post(endpoint, feedbackRequest));
            logger.info("Sent POST request to {} without authentication and feedback payload: {}", endpoint, feedbackRequest);
        }
    }
}
