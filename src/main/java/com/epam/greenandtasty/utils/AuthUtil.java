package com.epam.greenandtasty.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.epam.greenandtasty.utils.ApiReader.*;

public class AuthUtil {
    private static final Logger logger = LogManager.getLogger(AuthUtil.class);

    private static String waiterToken;
    private static String customerToken;
    private static String waiterUsername;
    private static String customerUsername;

    private AuthUtil(){}

    public static void login(String email, String password, boolean isWaiter){
        logger.info("Logging in with email: {}", email);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        Response response = RestAssured.given()
                .baseUri(ApiReader.getBaseUrl())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(getSignIn());

        if(response.getStatusCode() == 200){
            String token = response.jsonPath().getString("data.accessToken");
            String username = response.jsonPath().getString("data.username");
            String role = response.jsonPath().getString("data.role");

            logger.info("Successfully obtained authentication token for role:" +
                    " {}", role);

            if(isWaiter){
                waiterToken = token;
                waiterUsername = username;
            }else{
                customerToken = token;
                customerUsername = username;
            }
        }else{
            logger.error("Failed to login. Status code: {}",
                    response.getStatusCode());
            throw new RuntimeException("Failed to login: "+ response.getBody().asString());
        }
    }

    public static String getWaiterToken(){
        if(waiterToken == null){
            login(getWaiterEmail(), getWaiterPassword(), true);
        }
        return waiterToken;
    }

    public static String getCustomerToken(){
        if(customerToken == null){
            login(getCustomerEmail(), getCustomerPassword(), false);
        }
        return customerToken;
    }

    public static String getWaiterUsername(){
        if(waiterUsername == null){
            getWaiterToken();
        }
        return waiterUsername;
    }

    public static String getCustomerUsername(){
        if(customerUsername == null){
            getCustomerToken();
        }
        return customerUsername;
    }

    public static void resetTokens(){
        waiterToken = null;
        customerToken = null;
        waiterUsername = null;
        customerUsername = null;
        logger.info("Authentication tokens and usernames have been reset");
    }
}
