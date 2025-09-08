package com.epam.greenandtasty.utils.filters;

import com.epam.greenandtasty.exceptions.apiexceptions.*;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RetryFilter implements Filter {
    private static final Logger logger =
            LogManager.getLogger(RetryFilter.class);

    private final int maxRetries;
    private final int retryDelayMs;
    private final List<Integer> retryStatusCodes;

    public RetryFilter(int maxRetries, int retryDelayMs,
                       List<Integer> retryStatusCodes){
        this.maxRetries = maxRetries;
        this.retryDelayMs = retryDelayMs;
        this.retryStatusCodes = retryStatusCodes;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx){
        Response response = ctx.next(requestSpec, responseSpec);

        int retryCount = 0;
        while(retryStatusCodes.contains(response.statusCode()) && retryCount < maxRetries){
            logger.warn("Received status code {} from {}. Retrying ({}/{}).." +
                    ".", response.statusCode(), requestSpec.getURI(),
                    retryCount+1, maxRetries);

            try{
                Thread.sleep(retryDelayMs);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

            response = ctx.next(requestSpec, responseSpec);
            retryCount++;
        }

        if(retryCount > 0 && !retryStatusCodes.contains(response.statusCode())){
            logger.info("Request succeeded after {} retries", retryCount);
        } else if(retryCount == maxRetries && retryStatusCodes.contains(response.statusCode())){
            logger.error("Request failed after {} retries", maxRetries);
        }
        return response;
    }
}
