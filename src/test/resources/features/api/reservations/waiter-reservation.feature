@api @waiter-reservation
Feature: Reservation of the table by Waiter
  As a Waiter
  I want to be able to manage reservations
  So I could control workload of the Restaurant

  @smoke @positive
  Scenario: Allow waiter to make a booking
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER             |
      | customerEmail | Harshitha_b@gmail.com |
      | date          | 2025-05-22           |
      | guestNumber   | 4                    |
      | locationId    | LOC001               |
      | tableNumber   | T002                 |
      | timeFrom      | 18:00                |
      | timeTo        | 19:00                |
    Then the response status code should be 201

  @regression @positive
  Scenario: Allow waiter to make a booking
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER             |
      | customerEmail | Harshitha_b@gmail.com |
      | date          | 2025-05-22           |
      | guestNumber   | 4                    |
      | locationId    | LOC001               |
      | tableNumber   | T002                 |
      | timeFrom      | 16:00                |
      | timeTo        | 17:00                |
    Then the response status code should be 201
    And the response should contain status "RESERVED"

  @regression @negative
  Scenario: Block customer from booking via this API
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER      |
      | customerEmail | abhinav@a.com |
      | date          | 2025-05-03    |
      | guestNumber   | 4             |
      | locationId    | LOC001        |
      | tableNumber   | T001          |
      | timeFrom      | 14:00         |
      | timeTo        | 15:00         |
    Then the response status code should be 401
    And the response should contain message "User is not a waiter"

  @regression
  Scenario: Missing required fields
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER      |
      | customerEmail | abhinav@a.com |
      | date          | 2025-05-03    |
      | guestNumber   | 4             |
      | locationId    |               |
      | tableNumber   | T001          |
      | timeFrom      | 14:00         |
      | timeTo        | 15:00         |
    Then the response status code should be 401
    And the response should contain message "Waiter is not assigned to this location"

  @regression
  Scenario Outline: Invalid date and time formats
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER      |
      | customerEmail | abhinav@a.com |
      | date          | <date>        |
      | guestNumber   | 4             |
      | locationId    | LOC001        |
      | tableNumber   | T001          |
      | timeFrom      | <timeFrom>    |
      | timeTo        | <timeTo>      |
    Then the response status code should be <statusCode>
    And the response should contain message "<message>"

    Examples:
      | date       | timeFrom | timeTo | statusCode | message             |
      | 20-4-2025  | 12:00    | 14:00  | 400        | Invalid Date Format |
      | 2025-05-02 | 1200     | 14:00  | 400        | Invalid Time Format |

  @regression
  Scenario: Booking overlaps with another
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER             |
      | customerEmail | Harshitha_b@gmail.com |
      | date          | 2025-05-22           |
      | guestNumber   | 4                    |
      | locationId    | LOC001               |
      | tableNumber   | T002                 |
      | timeFrom      | 16:00                |
      | timeTo        | 17:00                |
    Then the response status code should be 409
    And the response should contain message "Table is not available for the requested time"

  @regression
  Scenario: Waiter cannot book for other locations
    Given I am logged in as a waiter with "aashijain06@gmail.com" "Aashi@123"
    When I send a POST request to "/bookings/waiter" with the following details:
      | clientType    | CUSTOMER      |
      | customerEmail | abhinav@a.com |
      | date          | 2025-05-03    |
      | guestNumber   | 4             |
      | locationId    | LOC001        |
      | tableNumber   | T001          |
      | timeFrom      | 14:00         |
      | timeTo        | 15:00         |
    Then the response status code should be 401
    And the response should contain message "Waiter is not assigned to this location"

