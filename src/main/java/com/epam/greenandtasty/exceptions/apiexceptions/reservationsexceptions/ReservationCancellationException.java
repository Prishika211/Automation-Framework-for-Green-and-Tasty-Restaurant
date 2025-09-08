package com.epam.greenandtasty.exceptions.apiexceptions.reservationsexceptions;

import com.epam.greenandtasty.exceptions.apiexceptions.ApiException;
import io.restassured.response.Response;

public class ReservationCancellationException extends ApiException {
    public ReservationCancellationException(String reservationId, Response response) {
        super("Failed to cancel reservation with ID: " + reservationId,
                "/bookings/waiter/" + reservationId,
                response);
    }

    public ReservationCancellationException(String message, String reservationId, int statusCode, String responseBody) {
        super(message, "/bookings/waiter/" + reservationId, statusCode, responseBody);
    }
}
