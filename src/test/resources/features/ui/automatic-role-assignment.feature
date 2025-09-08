@ui
Feature: Automatic Role Assignment
  As an Admin
  I want the application to automatically assign roles to new users based on predefined criteria
  So that users can have appropriate access and permissions

  Background:
    Given I am on the Home page
    And I click on the Sign In button on HomePage

  @smoke
  Scenario: Verify if the user is navigated to waiter portal if logged in using waiter credentials
    When I enter "waiter10@gmail.com" into the Email field
    And I enter "Harshitha@123" into the Password field
    And I click on the Sign In button
    Then I should be redirected to waiter dashboard page

  @regression
  Scenario: Verify if the user is navigated to customer portal if logged in using customer credentials
    When I enter "Harshitha_b@gmail.com" into the Email field
    And I enter "Harshitha@123" into the Password field
    And I click on the Sign In button
    Then I should see the "Reservations" tab on the dashboard page

