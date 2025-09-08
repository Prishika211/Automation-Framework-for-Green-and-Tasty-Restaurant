package com.epam.greenandtasty.exceptions.apiexceptions;

import io.restassured.response.Response;

public class ApiException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;
    private String endpoint;
    private Response response;

    public ApiException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public ApiException(String message, String endpoint, Response response){
        super(message);
        this.endpoint = endpoint;
        this.statusCode = response.getStatusCode();
        this.responseBody = response.getBody().asString();
        this.response = response;
    }

    public ApiException(String message, String endpoint, int statusCode, String responseBody) {
        super(message);
        this.endpoint = endpoint;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.response = null;
    }

    @Override
    public String toString(){
        return String.format("ApiException: %s [Endpoint: %s, Status Code: " +
                "%d, Response: %s]", getMessage(), endpoint, statusCode,
                responseBody);
    }
    public Response getResponse() {
        return response;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String getResponseBody(){
        return responseBody;
    }
}
