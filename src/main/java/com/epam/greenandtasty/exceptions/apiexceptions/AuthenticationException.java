package com.epam.greenandtasty.exceptions.apiexceptions;

public class AuthenticationException extends ApiException {
  public AuthenticationException(String message, int statusCode,
                                 String responseBody) {
    super(message, statusCode, responseBody);
  }
}
