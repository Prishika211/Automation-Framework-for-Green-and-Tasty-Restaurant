package com.epam.greenandtasty.exceptions.apiexceptions;

public class RateLimitException extends ApiException {
    public RateLimitException(String message, int statusCode,
                              String responseBody) {
        super(message, statusCode, responseBody);
    }
}
