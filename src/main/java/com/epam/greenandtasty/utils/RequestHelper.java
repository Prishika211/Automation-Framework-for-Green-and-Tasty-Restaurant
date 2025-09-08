package com.epam.greenandtasty.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.epam.greenandtasty.utils.ApiReader.getBaseUrl;
import static io.restassured.RestAssured.given;

public class RequestHelper {
    private static final Logger logger =
            LogManager.getLogger(RequestHelper.class);
    public static final RequestSpecification requestSpecification;

    static {
        logger.info("Initializing RestAssured with base URL: {}", getBaseUrl());
        requestSpecification = given()
                .header("Content-Type", "application/json")
                .baseUri(ApiReader.getBaseUrl());

    }

    public static Response getWithAuth(String api, String accessToken ){
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(api);

    }
    public static Response postWithAuth(String api, Object payload, String accessToken){
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + accessToken)
                .body(payload)
                .when()
                .post(api);

    }

    public static Response get(String api){
        return given()
                .spec(requestSpecification)
                .when()
                .get(api);
    }

    public static Response post(String api, Object object){
        return given()
                .spec(requestSpecification)
                .body(object)
                .when()
                .post(api);
    }

    public static Response getWithQueryParams(String api, Map<String, Object> queryParams) {
        return given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .when()
                .get(api);
    }

    public static Response postWithQueryParams(String api, Object payload,Map<String, Object> queryParams) {
        return given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .body(payload)
                .when()
                .post(api);
    }

    public static Response deleteWithAuth(String api, String accessToken){
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(api);


    }
    public static Response delete(String api){

                return given()
                        .spec(requestSpecification)
                        .when()
                        .delete(api);


    }
}
