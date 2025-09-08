package com.epam.greenandtasty.stepdefinitions.api;

import com.epam.greenandtasty.context.APIContext;
import com.epam.greenandtasty.models.UserRegistration;
import com.epam.greenandtasty.utils.ApiReader;
import com.epam.greenandtasty.utils.RequestHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


public class UserRegistrationSteps {
    private final APIContext apiContext;

    public UserRegistrationSteps(APIContext apiContext) {
        this.apiContext = apiContext;
    }



    @Given("the user has firstName {string}, lastName {string}, email {string}, and password {string}")
    public void theUserHasFirstNameLastNameEmailAndPassword(String firstName, String lastName, String email, String password) {
        UserRegistration user = new UserRegistration.UserBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .builder();

        apiContext.setPayload(user);
    }

    @When("the user sends a signup request")
    public void theUserSendsASignupRequest() {

        Response response = RequestHelper.post(ApiReader.getSignUp(), apiContext.getPayload());

        apiContext.setResponse(response);
    }





}
