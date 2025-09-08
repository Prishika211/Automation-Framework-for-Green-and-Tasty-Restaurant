package com.epam.greenandtasty.exceptions.apiexceptions;

public class ServerException extends ApiException {
    public ServerException(String message, int statusCode,
                           String responseBody) {
        super(message, statusCode, responseBody);
    }
}
