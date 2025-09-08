package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.utils.AuthUtil;
import com.epam.greenandtasty.utils.RequestHelper;
import com.epam.greenandtasty.models.reservation.ReservationRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ReservationByClientSteps {

    private final APIContext apiContext;
    private static final Logger logger = LogManager.getLogger(ReservationByClientSteps.class);

    public ReservationByClientSteps(APIContext apiContext){
        this.apiContext = apiContext;
    }

    @When("I send a POST request to {string} with :")
    public void iSendAPOSTRequestToWith(String endpoint, DataTable dataTable) {
        Map<String, String> requestData = dataTable.asMap(String.class,
                String.class);

        ReservationRequest reservationRequest = new ReservationRequest.ReservationRequestBuilder()
                .setLocationId(requestData.get("locationId"))
                .setDate(requestData.get("date"))
                .setTimeFrom(requestData.get("timeFrom"))
                .setTimeTo(requestData.get("timeTo"))
                .setTableNumber(requestData.get("tableNumber"))
                .setGuestNumber(requestData.get("guestNumber"))
                .builder();
        if(apiContext.getAuthToken()==null) {
            apiContext.setResponse(RequestHelper.postWithAuth(endpoint, reservationRequest, AuthUtil.getCustomerToken()));
            logger.info("I send a post request to {} with out login with payload {}", endpoint, reservationRequest);
        }
        else {
            apiContext.setResponse(RequestHelper.postWithAuth(endpoint, reservationRequest, apiContext.getAuthToken()));
            logger.info("I send a post request to {} with payload {}", endpoint, reservationRequest);
        }

    }
}
