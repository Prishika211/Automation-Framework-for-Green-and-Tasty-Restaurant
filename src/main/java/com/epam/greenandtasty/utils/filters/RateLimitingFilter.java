package com.epam.greenandtasty.utils.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RateLimitingFilter implements Filter {
    private static final Logger logger =
            LogManager.getLogger(RateLimitingFilter.class);

    private final int requestsPerSecond;
    private final Queue<Long> requestTimestamps = new ConcurrentLinkedDeque<>();

    public RateLimitingFilter(int requestsPerSecond){
        this.requestsPerSecond = requestsPerSecond;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx){
        long currentTime = System.currentTimeMillis();

        while(!requestTimestamps.isEmpty() && requestTimestamps.peek() < currentTime - 1000){
            requestTimestamps.poll();
        }

        if(requestTimestamps.size() >= requestsPerSecond){
            long oldestTimestamp = requestTimestamps.peek();
            long waitTime = 1000 - (currentTime - oldestTimestamp);

            if (waitTime > 0) {
                logger.info("Rate limit reached. Waiting for {} ms before next request", waitTime);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                currentTime = System.currentTimeMillis();
            }
        }

        // Add current timestamp to the queue
        requestTimestamps.add(currentTime);

        // Proceed with the request
        return ctx.next(requestSpec, responseSpec);
    }

}
