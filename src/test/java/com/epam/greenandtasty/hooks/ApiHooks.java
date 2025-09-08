package com.epam.greenandtasty.hooks;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.utils.AuthUtil;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHooks {
    private static final Logger logger = LogManager.getLogger(ApiHooks.class);

    private final APIContext apiContext;

    public ApiHooks(APIContext apiContext){
        this.apiContext = apiContext;
    }


    @After("@api")
    public void tearDownForApi(){
        logger.info("Tearing down API test environment");
        AuthUtil.resetTokens();

        apiContext.setAuthToken(null);
        apiContext.setCreatedReservationId(null);
        apiContext.setResponse(null);
        logger.info("API context has been reset");
    }
}
