@bookingsByClient @api
Feature: Table Reservation by Client

  @smoke @positive
  Scenario: Successful reservation with valid data
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-22 |
      | guestNumber | 4          |
      | timeFrom    | 12:00      |
      | timeTo      | 13:00      |
    Then the response status code should be 201

  @regression @positive
  Scenario: Successful reservation with valid data
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-22 |
      | guestNumber | 4          |
      | timeFrom    | 14:00      |
      | timeTo      | 15:00      |
    Then the response status code should be 201
    And the response should match the JSON schema "schemas/bookings/bookingByClient.json"

  @regression
  Scenario: Error when reserving table without logging in
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-22 |
      | guestNumber | 4          |
      | timeFrom    | 12:15      |
      | timeTo      | 13:45      |
    Then the response status code should be 401
    And the response should contain message "Please login"

  @regression
  Scenario: Error with invalid table number
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T00        |
      | date        | 2025-05-10 |
      | guestNumber | 4          |
      | timeFrom    | 12:15      |
      | timeTo      | 13:45      |
    Then the response status code should be 400
    And the response should contain message "No Such Table"

  @regression
  Scenario: Error with overlapping time reservation
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-10 |
      | guestNumber | 4          |
      | timeFrom    | 12:15      |
      | timeTo      | 13:45      |
    Then the response status code should be 400
    And the response should contain message "Table is not available for the requested time"

  @regression
  Scenario: Error due to insufficient gap between reservations
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-22 |
      | guestNumber | 4          |
      | timeFrom    | 12:00      |
      | timeTo      | 14:00      |
    Then the response status code should be 400
    And the response should contain message "Table is not available for the requested time"

  @regression
  Scenario: Error with invalid date format
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 10-05-2025 |
      | guestNumber | 4          |
      | timeFrom    | 12:15      |
      | timeTo      | 13:45      |
    Then the response status code should be 400
    And the response should contain message "Invalid date Format"

  @regression
  Scenario: Error with Invalid Time Format
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-10 |
      | guestNumber | 4          |
      | timeFrom    | 1:00       |
      | timeTo      | 14:00      |
    Then the response status code should be 400
    And the response should contain message "Invalid Time Format"

  @regression
  Scenario: Error with guests exceeding maximum table capacity
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC001     |
      | tableNumber | T001       |
      | date        | 2025-05-10 |
      | guestNumber | 25         |
      | timeFrom    | 12:15      |
      | timeTo      | 14:00      |
    Then the response status code should be 400
    And the response should contain message "Guests exceed the maximum capacity for the selected table."

  @regression
  Scenario: Error with non-existent location
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/client" with :
      | locationId  | LOC006     |
      | tableNumber | T001       |
      | date        | 2025-05-10 |
      | guestNumber | 4          |
      | timeFrom    | 12:15      |
      | timeTo      | 14:00      |
    Then the response status code should be 404
    And the response should contain message "Location not found."
