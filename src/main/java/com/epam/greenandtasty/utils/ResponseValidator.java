package com.epam.greenandtasty.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ResponseValidator {
    private static final Logger logger =
            LogManager.getLogger(ResponseValidator.class);

    private ResponseValidator(){}

    public static void validateStatusCode(Response response,
                                          int expectedStatusCode){
        int actualStatusCode = response.getStatusCode();
        logger.info("Validating status code. Expected: {}, Actual: {}", expectedStatusCode, actualStatusCode);
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + actualStatusCode);
    }

    public static void validateResponseMessage(Response response,
                                               String expectedMessage){
        String actualMessage = response.jsonPath().getString("message");
        logger.info("Validating response message. Expected: {}, Actual: {}",
                expectedMessage, actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage,
                "Expected message '" + expectedMessage + "' but got '" + actualMessage + "'");
    }

    public static void validateResponseContainsStatus(Response response,
                                                      String expectedStatus){
        String actualStatus = response.jsonPath().getString("status");
        logger.info("Validating reservation status. Expected: {}, Actual: {}", expectedStatus, actualStatus);
        Assert.assertEquals(actualStatus, expectedStatus,
                "Expected status '" + expectedStatus + "' but got '" + actualStatus + "'");
    }

    public static void validateReservationType(Response response, String expectedType) {
        String actualType = response.jsonPath().getString("clientType");
        logger.info("Validating reservation type. Expected: {}, Actual: {}", expectedType, actualType);
        Assert.assertEquals(actualType, expectedType,
                "Expected client type '" + expectedType + "' but got '" + actualType + "'");
    }
}