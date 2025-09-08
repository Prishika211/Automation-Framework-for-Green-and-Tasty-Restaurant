@ui@menuBrowsing
Feature: Browsing Menu Validation

  Background: I am a Customer
    Given I am on the Home page
    And I click on the Sign In button on HomePage
    And I enter "Harshitha_b@gmail.com" into the Email field
    And I enter "Harshitha@123" into the Password field
    And I click on the Sign In button
    And I should see the "Reservations" tab on the dashboard page

  @smoke
  Scenario: Verify that the static menu is displayed after the user logs in
    When I navigate to the main page
    And click on view menu
    Then the static menu should be displayed

@regression
  Scenario: Verify that the description of displayed dishes includes name, price, and weight
  When I navigate to the main page
  And click on view menu
  When I navigate to the static menu
  Then I should see the dish name, price, weight


