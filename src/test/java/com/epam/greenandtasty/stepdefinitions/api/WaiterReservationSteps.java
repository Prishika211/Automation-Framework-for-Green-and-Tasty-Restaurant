package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.models.reservation.ReservationRequest;
import com.epam.greenandtasty.utils.AuthUtil;
import com.epam.greenandtasty.utils.RequestHelper;
import com.epam.greenandtasty.utils.ResponseValidator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class WaiterReservationSteps {
    private static final Logger logger =
            LogManager.getLogger(WaiterReservationSteps.class);

    private final APIContext apiContext;

    public WaiterReservationSteps(APIContext apiContext){
        this.apiContext = apiContext;
    }


    @When("I send a POST request to {string} with the following details:")
    public void iSendAPOSTRequestToWithTheFollowingDetails(String endpoint,
                                                           DataTable dataTable) {
        Map<String, String> requestData = dataTable.asMap(String.class,
                String.class);

        ReservationRequest reservationRequest =
                new ReservationRequest.ReservationRequestBuilder()
                        .setClientType(requestData.get("clientType"))
                        .setCustomerEmail(requestData.get("customerEmail"))
                        .setDate(requestData.get("date"))
                        .setGuestNumber(requestData.get("guestNumber"))
                        .setLocationId(requestData.get("locationId"))
                        .setTableNumber(requestData.get("tableNumber"))
                        .setTimeFrom(requestData.get("timeFrom"))
                        .setTimeTo(requestData.get("timeTo"))
                                .builder();

        if(apiContext.getAuthToken()==null) {
            apiContext.setResponse(RequestHelper.postWithAuth(endpoint,
                    reservationRequest, AuthUtil.getWaiterToken()));
            logger.info("I send a post request to {} with out login with payload {}", endpoint, reservationRequest);
        }
        else {
            apiContext.setResponse(RequestHelper.postWithAuth(endpoint, reservationRequest, apiContext.getAuthToken()));
            logger.info("I send a post request to {} with payload {}", endpoint, reservationRequest);
        }
    }

    @And("the response should contain status {string}")
    public void theResponseShouldContainStatus(String expectedStatus) {
        Response response = apiContext.getResponse();
        ResponseValidator.validateResponseContainsStatus(response, expectedStatus);
    }

    @And("the response indicate the reservation is of type {string}")
    public void theResponseIndicateTheReservationIsOfType(String expectedType) {
        Response response = apiContext.getResponse();
        ResponseValidator.validateReservationType(response, expectedType);
    }
}
