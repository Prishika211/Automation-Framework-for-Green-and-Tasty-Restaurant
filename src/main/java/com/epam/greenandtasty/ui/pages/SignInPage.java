package com.epam.greenandtasty.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage{
    @FindBy(xpath="//a[normalize-space()='Create an Account']")
    private WebElement createAnAccountLink;

    @FindBy(xpath = "//h2[normalize-space()='Sign In to Your Account']")
    WebElement redirectedSignInPage;

    @FindBy(xpath = "//input[@placeholder='Enter your Email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Enter your Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Sign In']")
    private WebElement signInButtonForSignInPage;

    public SignInPage(WebDriver webDriver) {
        super(webDriver);

    }

    public UserProfileRegistration clickCreateAnAccountLink() {
        waitForElement(createAnAccountLink);
        click(createAnAccountLink);
        return new UserProfileRegistration(webDriver);
    }

    public boolean verifyPage() {
        try {
            waitForElement(redirectedSignInPage);
            return redirectedSignInPage.getText().equals("Sign In to Your Account");
        } catch (TimeoutException timeoutException) {
            return false;
        }
    }

    public void enterEmail(String email) {
        waitForElement(emailField);
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickSignInButton() {
        waitForElement(signInButtonForSignInPage);
        click(signInButtonForSignInPage);
    }

    public String getFieldErrorMessage(String fieldName) {
        WebElement fieldErrorElement = null;
        if (fieldName.equalsIgnoreCase("Email")) {
            fieldErrorElement = webDriver.findElement(By.xpath("//p[normalize-space()='Email is required.']"));
        } else if (fieldName.equalsIgnoreCase("Password")) {
            fieldErrorElement = webDriver.findElement(By.xpath("//p[normalize-space()='Password is required.']"));
        }

        waitForElement(fieldErrorElement);
        return fieldErrorElement != null ? fieldErrorElement.getText().trim() : null;
    }


    public CustomerDashboardPage isRedirectedToCustomerDashboardPage() {
        System.out.println(webDriver.getCurrentUrl());
        return new CustomerDashboardPage(webDriver);
    }

    public WaiterDashboardPage isRedirectedToWaiterDashboardPage(){
        return new WaiterDashboardPage(webDriver);
    }
}
