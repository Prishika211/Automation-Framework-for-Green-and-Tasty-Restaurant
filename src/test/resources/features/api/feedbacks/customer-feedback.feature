@feedback @api
Feature: Leaving feedback for service and cuisine by Customer
  As a Customer
  I want to have an option to leave feedback
  So that I could express my opinion on service and culinary experience

  @positive @smoke
  Scenario: Submit complete valid feedback (cuisine + service)
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 5                        |
      | serviceComment  | Good service, good atmosphere |
      | cuisineRating   | 4                        |
      | cuisineComment  | Good food                |
    Then the response status code should be 201

  @positive @regression
  Scenario: Submit complete valid feedback (cuisine + service)
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 5                        |
      | serviceComment  | Good service, good atmosphere |
      | cuisineRating   | 4                        |
      | cuisineComment  | Good food                |
    Then the response status code should be 201
    And the response should contain message "Feedback has been created."

  @positive @regression
  Scenario: Submit valid feedback with only ratings
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 3                        |
      | cuisineRating   | 4                        |
    Then the response status code should be 201
    And the response should contain message "Feedback has been created."

  @negative @regression
  Scenario: Submit feedback with missing reservationId
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | serviceRating   | 3                        |
      | cuisineRating   | 4                        |
    Then the response status code should be 400
    And the response should contain message "Reservation ID is required"

  @negative @regression
  Scenario: Submit feedback with invalid rating type
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 3                        |
      | cuisineRating   | great                    |
    Then the response status code should be 400
    And the response should contain message "Cuisine rating must be a number between 1 and 5."

  @negative @regression
  Scenario Outline: Submit feedback with rating out of range
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | <serviceRating>          |
      | cuisineRating   | <cuisineRating>          |
    Then the response status code should be 400
    And the response should contain message "<errorMessage>"

    Examples:
      | serviceRating | cuisineRating | errorMessage                                        |
      | 0             | 3             | Service rating must be a number between 1 and 5.    |
      | 3             | 6             | Cuisine rating must be a number between 1 and 5.    |
      | 6             | 0             | Both ratings must be numbers between 1 and 5.       |

  @positive @regression
  Scenario: Submit feedback with empty strings in comments
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 5                        |
      | serviceComment  | Good service, good atmosphere |
      | cuisineRating   | 4                        |
      | cuisineComment  |                          |
    Then the response status code should be 201
    And the response should contain message "Feedback has been created."

  @negative @regression
  Scenario: Submit feedback with missing both ratings
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
    Then the response status code should be 400
    And the response should contain message "At least one rating (cuisine or service) is required"

  @negative @smoke
  Scenario: Submit feedback with invalid reservationId
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | INVALID                  |
      | serviceRating   | 3                        |
      | cuisineRating   | 3                        |
    Then the response status code should be 404

  @negative @regression
  Scenario: Submit feedback before reservation is "in progress"
    Given I am logged in as a customer with "Harshitha_b@gmail.com" "Harshitha@123"
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 3                        |
      | cuisineRating   | 3                        |
    Then the response status code should be 400
    And the response should contain message "Feedback can only be submitted for 'In Progress' or 'Finished' reservations."

  @negative @regression
  Scenario: Submit feedback without logging in
    When I send a POST request to "/feedbacks" with the following feedback details:
      | reservationId   | 672846d5c951184d705b65d7 |
      | serviceRating   | 3                        |
      | cuisineRating   | 3                        |
    Then the response status code should be 401
    And the response should contain message "Please login with valid credentials"