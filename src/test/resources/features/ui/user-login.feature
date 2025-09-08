@ui@uilogin
Feature: User Login
  As a user
  I want to be able to Sign In to access my account
  So that I can interact with the application

  Background:
    Given I am on the Home page
    And I click on the Sign In button on HomePage

    @smoke
  Scenario: Validate form error for missing fields
    When I click on the Sign In button with empty fields
    Then I should see the following error messages:
      | Email    | Email is required. |
      | Password | Password is required. |
@regression
  Scenario: Verify Sign In with invalid password
    When I enter "Harshitha_b@gmail.com" into the Email field
    And I enter "wrongpassword" into the Password field
    And I click on the Sign In button
    Then I should not see the "Reservations" tab on the dashboard page

  @regression
  Scenario: Verify Sign In with invalid email and valid password
    When I enter "invalid.email@ex.com" into the Email field
    And I enter "Johndoe@1234" into the Password field
    And I click on the Sign In button
    Then I should not see the "Reservations" tab on the dashboard page

    @regression
    Scenario: Verify Sign In with valid credentials
      When I enter "Harshitha_b@gmail.com" into the Email field
      And I enter "Harshitha@123" into the Password field
      And I click on the Sign In button
      Then I should see the "Reservations" tab on the dashboard page

      @regression
  Scenario: Verify Sign In with invalid email format
    When I enter "john.doeexample.com" into the Email field
    And I enter "Johndoe@1234" into the Password field
    And I click on the Sign In button
    Then I should not see the "Reservations" tab on the dashboard page

