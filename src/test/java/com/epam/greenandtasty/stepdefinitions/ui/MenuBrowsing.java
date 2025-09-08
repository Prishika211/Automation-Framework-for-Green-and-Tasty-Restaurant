package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.CustomerDashboardPage;
import com.epam.greenandtasty.ui.pages.HomePage;
import com.epam.greenandtasty.ui.pages.MainPage;
import com.epam.greenandtasty.ui.pages.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class MenuBrowsing {
    private final UIContext uiContext;
    private CustomerDashboardPage customerDashboardPage;
    private MainPage mainPage;
    private SignInPage signInPage;
    private static final Logger logger =
            LogManager.getLogger(MenuBrowsing.class);

    public MenuBrowsing(UIContext uiContext){
        this.uiContext = uiContext;
        this.signInPage = new SignInPage(uiContext.getWebDriver());
        this.customerDashboardPage = new CustomerDashboardPage(uiContext.getWebDriver());
    }

    @When("I navigate to the main page")
    public void I_navigates_to_the_main_page(){
        customerDashboardPage.getMainPage().click();
        this.mainPage = new MainPage(uiContext.getWebDriver());

    }


    @Then("the static menu should be displayed")
    public void theStaticMainShouldBeDisplayed() {
        Assert.assertTrue(mainPage.getStaticMenu().getFirst().isDisplayed());
        logger.info("the static Main displayed");
    }









    @And("click on view menu")
    public void clickOnViewMenu() {
        customerDashboardPage.getViewMenuButton().click();
        logger.info("Clicked on view menu");
    }

    @When("I navigate to the static menu")
    public void iNavigateToTheStaticMenu() {
        mainPage.scrollToElement(mainPage.getStaticMenu().getFirst());
        logger.info("I navigate to Menu");
    }

    @Then("I should see the dish name, price, weight")
    public void iShouldSeeTheDishNamePriceWeight() {
        Assert.assertTrue(mainPage.verifyTheMenuCard());
        logger.info("Menu details are present");

    }

    @Then("I navigate to the CustomerDashboard Page")
    public void iNavigateToTheCustomerDashboardPage() {
        customerDashboardPage = signInPage.isRedirectedToCustomerDashboardPage();
        System.out.println(uiContext.getWebDriver().getCurrentUrl());
    }
}
