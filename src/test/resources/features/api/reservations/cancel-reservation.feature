@api @cancelReservation
Feature: Cancel reservation by ID

  @smoke
  Scenario: Verify that a reservation can be canceled successfully
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    And I send a GET request to "/reservations" with authentication
    And I capture reservation id
    When I send a DELETE request to cancel reservation endpoint
    Then the response status code should be 200

  @regression
  Scenario: Verify that a reservation can be canceled successfully
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    And I send a GET request to "/reservations" with authentication
    And I capture reservation id
    When I send a DELETE request to cancel reservation endpoint
    Then the response status code should be 200
    And the response should contain message "reservation is canceled successfully"

  @regression
  Scenario: Verify error when trying to cancel a reservation without logging in
    Given I am not logged in as a customer
    When I send a DELETE request to cancel reservation endpoint
    Then the response status code should be 401
    And the response should contain message "Unauthorized"

  @regression
  Scenario: Verify error when trying to cancel a reservation that does not exist
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    And no reservation exists with ID "nonExistentReservationId"
    When I send a DELETE request to cancel reservation endpoint
    Then the response status code should be 404
    And the response should contain message "Reservation not found"
