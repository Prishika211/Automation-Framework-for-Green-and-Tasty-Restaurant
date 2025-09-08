package com.epam.greenandtasty.ui.pages;

import com.epam.greenandtasty.utils.Waits;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.greenandtasty.constants.Constant.WAITING_TIME;

public class UserProfileRegistration extends BasePage{
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(xpath="//button[normalize-space()='Create an Account']")
    WebElement createAnAccountButton;

    @FindBy(xpath = "//h2[normalize-space()='Sign In to Your Account']")
    WebElement redirectedSignInPage;

    @FindBy(xpath="//h3[normalize-space()='Create an Account']")
    WebElement registrationHeading;

    public UserProfileRegistration(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillFirstName(String firstName) {
        waitForElement(firstNameField);
        type(firstNameField, firstName);
    }

    public void fillLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void fillEmail(String email) {
        type(emailField, email);
    }

    public void fillPassword(String password) {
        type(passwordField, password);
    }

    public void fillConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    public SignInPage clickCreateAccount(){
        waitForElement(createAnAccountButton);
        click(createAnAccountButton);
        return new SignInPage(webDriver);

    }

    public boolean verifyPage() {
        try {
            waitForElement(registrationHeading);
            return registrationHeading.getText().equals("Create an Account");
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

}
