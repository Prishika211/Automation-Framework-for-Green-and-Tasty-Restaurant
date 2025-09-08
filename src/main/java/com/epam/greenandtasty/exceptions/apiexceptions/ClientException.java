package com.epam.greenandtasty.exceptions.apiexceptions;

public class ClientException extends ApiException {
    public ClientException(String message, int statusCode,
                           String responseBody) {
        super(message, statusCode, responseBody);
    }
}
