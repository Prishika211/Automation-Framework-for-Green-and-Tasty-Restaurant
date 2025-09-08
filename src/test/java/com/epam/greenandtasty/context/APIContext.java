package com.epam.greenandtasty.context;

import io.restassured.response.Response;

public class APIContext {
    private String authToken;
    private Response response;
    private Object payload;
    private String createdReservationId;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getCreatedReservationId() {
        return createdReservationId;
    }

    public void setCreatedReservationId(String createdReservationId) {
        this.createdReservationId = createdReservationId;
    }
}
