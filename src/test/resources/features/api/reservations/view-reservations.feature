@viewReservations @api
Feature: View Reservations

  @smoke
  Scenario: Verify the list of reservations is displayed for a logged-in user
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a GET request to "/reservations" with authentication
    Then the response status code should be 200

  @regression
  Scenario: Verify the list of reservations is displayed for a logged-in user
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a GET request to "/reservations" with authentication
    Then the response status code should be 200
    And the response should contain a list of reservations for the user
    And the response should match the JSON schema "schemas/reservations/view-reservations-schema.json"

  @regression
  Scenario: Verify error when trying to access reservations without logging in
    When I send a GET request to "/reservations"
    Then the response status code should be 401
    And the response should contain message "Authorization token is missing"

  @regression
  Scenario: Verify response when no reservations exist for the logged-in user
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a GET request to "/reservations" with authentication
    Then the response status code should be 200
    And the response should contain an empty list with no reservations
