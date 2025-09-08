package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.models.UserLogin;
import com.epam.greenandtasty.utils.RequestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.*;


public class UserLoginSteps {
    private final APIContext apiContext;
    private static final Logger logger =
            LogManager.getLogger(UserLoginSteps.class);



    public UserLoginSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }



    @And("the response should contain a valid authentication token")
    public void theResponseShouldContainAValidAuthenticationToken() {
        apiContext.getResponse().then().body("data.accessToken", notNullValue());
        logger.info("The response contain valid auth token");

    }

    @And("the username of user should be {string}")
    public void theUsernameOfUserShouldBe(String username) {
        apiContext.getResponse().then().body("data.username", equalTo(username));

    }

    @And("the role of user should be {string}")
    public void theRoleOfUserShouldBe(String role) {
        apiContext.getResponse().then().body("data.role", equalTo(role));
    }




    @Given("existing user trying to login")
    public void existingUserTryingToLogin() {
        logger.info("The existing user trying to login");
    }

    @When("I send a POST request to {string} with username {string} and {string}")
    public void iSendAPOSTRequestToWithUsernameAnd(String endpoint, String email, String password) {
        UserLogin user = new UserLogin.UserBuilder()
                .setEmail(email)
                .setPassword(password)
                .builder();
        apiContext.setPayload(user);
        apiContext.setResponse(RequestHelper.post(endpoint, user));
        logger.info("I send post request to {} with data {}",endpoint, user);




    }
}