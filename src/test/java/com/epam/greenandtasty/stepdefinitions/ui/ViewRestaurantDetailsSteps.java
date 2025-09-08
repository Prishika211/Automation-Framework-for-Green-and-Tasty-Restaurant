package com.epam.greenandtasty.stepdefinitions.ui;

import com.epam.greenandtasty.context.UIContext;
import com.epam.greenandtasty.ui.pages.HomePage;
import com.epam.greenandtasty.ui.pages.LocationDetailsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ViewRestaurantDetailsSteps {
    private static final Logger logger =
            LogManager.getLogger(ViewRestaurantDetailsSteps.class);

    private final HomePage homePage;
    private LocationDetailsPage locationDetailsPage;

    public ViewRestaurantDetailsSteps(UIContext uiContext) {
        this.homePage = new HomePage(uiContext.getWebDriver());
        this.locationDetailsPage =
                new LocationDetailsPage(uiContext.getWebDriver());
    }

    @When("I check the Nav elements")
    public void iCheckTheNavElements() {
        homePage.verifyNavElements();
        logger.info("Verified nav elements.");
    }

    @Then("the following elements should be present:")
    public void theFollowingElementsShouldBePresent(DataTable elements) {
        elements.asList().forEach(element -> {
            Assert.assertTrue(homePage.isNavElementDisplayed(element),
                    element + " is not displayed.");
            logger.info(element + " is displayed.");
        });
    }


    @When("I view the general restaurant section")
    public void iViewTheGeneralRestaurantSection() {
        Assert.assertTrue(homePage.isGeneralInformationDisplayed(), "General " +
                "information section is missing.");
        logger.info("Verified general restaurant information section.");
    }


    @Then("the page should display the following information:")
    public void thePageShouldDisplayTheFollowingInformation(DataTable info) {
        info.asList().forEach(data -> {
            Assert.assertTrue(homePage.isGeneralInfoPresent(data), data + " is" +
                    " missing.");
            logger.info(data + " is present on the page.");
        });
    }

    @When("I scroll to the {string} section")
    public void iScrollToTheSection(String location){
        homePage.scrollToSection(location);
        logger.info("Scrolled to section: " + location);
    }

    @Then("I should see each restaurant location with:")
    public void iShouldSeeEachRestaurantLocationWith(DataTable data) {
        data.asList().forEach(detail -> {
            Assert.assertTrue(homePage.isLocationDetailPresent(detail),
                    detail + " is missing in locations.");
            logger.info(detail + " is present in location details.");
        });

    }

    @When("I click on a location card")
    public void iClickOnALocationCard() {
        homePage.clickOnLocationCard();
        logger.info("Clicked on a location card.");
    }

    @Then("I should be navigated to the Location Overview page")
    public void iShouldBeNavigatedToTheLocationOverviewPage() {
        locationDetailsPage = homePage.isLocationOverviewDisplayed();
        logger.info("Navigated to Location Overview Page.");
    }


    @And("I view a restaurant's location details")
    public void iViewARestaurantSLocationDetails() {
        locationDetailsPage.isRestaurantDetailsDisplayed();
        logger.info("Verified restaurant details.");
    }

    @Then("the page should display the following:")
    public void thePageShouldDisplayTheFollowing(DataTable details) {
        details.asList().forEach(detail -> {
            Assert.assertTrue(locationDetailsPage.isDetailDisplayed(detail),
                    detail + " is missing.");
            logger.info(detail + " is displayed in restaurant details.");
        });
    }

    @And("I view the {string} section")
    public void iViewTheSection(String section) {
        if (section.equalsIgnoreCase("Specialty Dishes")) {
            Assert.assertTrue(locationDetailsPage.isSpecialityDishesSectionDisplayed(),
                    "Specialty Dishes section is not visible on the location page.");
            logger.info("Verified that Specialty Dishes section is displayed.");
        } else {
            Assert.fail("Unknown section: " + section);
        }

    }

    @Then("the following dishes should be displayed for each dish:")
    public void theFollowingDishesShouldBeDisplayedForEachDish(DataTable dishDetails) {
        boolean isValid = locationDetailsPage.isDishDetailValid();
        Assert.assertTrue(isValid, "Dish details are missing or invalid in " +
                "the Specialty Dishes section.");
        logger.info("Verified that all dish details (Name, Image, Weight, Price) are present and valid.");
    }

    @And("I filter reviews by {string} and {string}")
    public void iFilterReviewsByServiceAndCuisineExperience(String filterType1, String filterType2) {
        locationDetailsPage.filterReviews(filterType1);
        logger.info("Applied filter for: " + filterType1);

        locationDetailsPage.filterReviews(filterType2);
        logger.info("Applied filter for: " + filterType2);
    }

    @Then("The review list should update accordingly")
    public void theReviewListShouldUpdateAccordingly() {
        Assert.assertTrue(locationDetailsPage.isReviewListUpdated(),
                "The review list did not update correctly after applying filters.");
        logger.info("Review list updated correctly after applying filters.");
    }

    @And("I sort the reviews using the dropdown with the following options " +
            "{string}")
    public void iSortTheReviewsUsingTheDropdownWithTheFollowingOptions(String sortingOptions) {
        locationDetailsPage.sortReviews(sortingOptions);
    }

    @Then("the reviews should be displayed in the selected order " +
            "{string}")
    public void theReviewsShouldBeDisplayedInTheSelectedOrder(String sortingOption) {
        Assert.assertTrue(locationDetailsPage.isReviewListSorted(sortingOption),
                "The reviews are not displayed in the correct order after sorting.");
        logger.info("Reviews successfully sorted as per the chosen options.");
    }

    @And("I navigate through review pages using pagination controls")
    public void iNavigateThroughReviewPagesUsingPaginationControls() {
        locationDetailsPage.goToNextPage();
        logger.info("Navigated to the next page of reviews.");
    }

    @Then("I should be taken to the corresponding reviews page")
    public void iShouldBeTakenToTheCorrespondingReviewsPage() {
        Assert.assertTrue(locationDetailsPage.isNextPaginationDisplayed(),
                "Failed to navigate to the next page of reviews.");
        logger.info("Verified navigation to the next page of reviews using pagination controls.");
    }

    @And("I scroll to {string} section")
    public void iScrollToSection(String reviews) {
        locationDetailsPage.scrollToReviewsSection(reviews);
        logger.info("Scrolled to reviews section: " + reviews);
    }

}
