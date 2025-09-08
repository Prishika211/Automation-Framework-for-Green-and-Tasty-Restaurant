package com.epam.greenandtasty.exceptions.apiexceptions.reservationsexceptions;

import com.epam.greenandtasty.exceptions.apiexceptions.ApiException;
import io.restassured.response.Response;

public class ReservationCreationException extends ApiException {
    public ReservationCreationException(String message, Response response) {
        super(message, "/bookings/waiter", response);
    }

    public ReservationCreationException(String message, int statusCode,
                                        String responseBody){
        super(message, "/bookings/waiter", statusCode, responseBody);
    }
}
