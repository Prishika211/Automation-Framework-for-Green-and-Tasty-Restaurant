package com.epam.greenandtasty.exceptions.apiexceptions;

public class AuthorizationException extends ApiException{
    public AuthorizationException(String message, int statusCode,
                                  String responseBody) {
        super(message, statusCode, responseBody);
    }
}
