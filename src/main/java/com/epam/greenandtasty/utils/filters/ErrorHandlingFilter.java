package com.epam.greenandtasty.utils.filters;

import com.epam.greenandtasty.exceptions.apiexceptions.*;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErrorHandlingFilter implements Filter {
    private static final Logger logger =
            LogManager.getLogger(ErrorHandlingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx){
        Response response = ctx.next(requestSpec, responseSpec);

        if(response.statusCode() >= 400){
            String responseBody = response.getBody().asString();
            String requestPath = requestSpec.getURI();

            logger.error("API Error: Status Code{} for request to {}",
                    response.statusCode(), requestPath);

            switch(response.statusCode()){
                case 401:
                    throw new AuthenticationException(
                            "Authentication failed for request to " + requestPath, response.statusCode(), responseBody
                    );
                case 403:
                    throw new AuthorizationException(
                            "Insufficient permissions for request to "+requestPath, response.statusCode(), responseBody
                    );
                case 429:
                    throw new RateLimitException(
                            "Rate limit exceeded for request to  "+requestPath,
                            response.statusCode(), responseBody
                    );
                case 500: case 502: case 503: case 504:
                    throw new ServerException(
                            "Server error occurred for request to "+requestPath,
                            response.statusCode(),
                            responseBody
                    );
                default:
                    if(response.statusCode() >= 400 && response.statusCode() <500){
                        throw new ClientException(
                                "Client error occurred for request to " + requestPath, response.statusCode(), responseBody
                        );
                    }else{
                        throw new ApiException(
                                "Unexpected error occurred for request to " + requestPath, response.statusCode(), responseBody
                        );
                    }
            }
        }
        return response;
    }
}
