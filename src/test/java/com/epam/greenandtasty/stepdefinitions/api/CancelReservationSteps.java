package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.models.reservation.ReservationResponse;
import com.epam.greenandtasty.utils.RequestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CancelReservationSteps {

    private static final Logger logger = LoggerFactory.getLogger(CancelReservationSteps.class);

    private final APIContext apiContext;
    private final ObjectMapper objectMapper;

    public CancelReservationSteps(APIContext apiContext) {
        this.apiContext = apiContext;
        this.objectMapper = new ObjectMapper();
    }

    @And("I capture reservation id")
    public void iCaptureReservationId() {
        try {

            ReservationResponse[] response = objectMapper.readValue(apiContext.getResponse().asString(), ReservationResponse[].class);
            String reservationId = (String) response[response.length - 1].getId();
            apiContext.setCreatedReservationId(reservationId);
            logger.info("Captured reservation ID: {}", reservationId);

        } catch (JsonProcessingException e) {
            logger.error("Failed to parse reservation response", e);
            throw new RuntimeException("Failed to parse reservation response", e);
        }
    }



    @Given("I am not logged in as a customer")
    public void iAmNotLoggedInAsACustomer() {
        apiContext.setAuthToken(null);
        logger.info("Simulated 'not logged in' state by clearing auth token.");
    }

    @And("no reservation exists with ID {string}")
    public void noReservationExistsWithID(String nonExistingReservationId) {
        String endpoint = "/reservations/" + nonExistingReservationId;
        logger.info("Attempting to delete non-existing reservation with ID: {}", nonExistingReservationId);
        apiContext.setResponse(RequestHelper.deleteWithAuth(endpoint, apiContext.getAuthToken()));
        logger.info("DELETE request response status for non-existing reservation: {}", apiContext.getResponse().getStatusCode());
    }

    @When("I send a DELETE request to cancel reservation endpoint")
    public void iSendADELETERequestToCancelReservationEndpoint() {
        String fullEndpoint = "/reservations/" + apiContext.getCreatedReservationId();
        logger.info("Sending DELETE request to: {}", fullEndpoint);
        if(apiContext.getAuthToken()==null)
            apiContext.setResponse(RequestHelper.delete(fullEndpoint));
        else
            apiContext.setResponse(RequestHelper.deleteWithAuth(fullEndpoint, apiContext.getAuthToken()));
        logger.info("DELETE request sent. Status code: {}", apiContext.getResponse().getStatusCode());
    }
}
