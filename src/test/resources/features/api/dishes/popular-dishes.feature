@popularDishes @api
Feature: View popular dishes across locations
  As a client of the Dishes API
  I want to retrieve a list of popular dishes
  So that I can display the most popular dishes in the application

  @smoke
  Scenario: Successfully retrieve popular dishes
    When I send a GET request to "dishes/popular"
    Then the response status code should be 200

  @regression
  Scenario: Successfully retrieve popular dishes
    When I send a GET request to "dishes/popular"
    Then the response status code should be 200
    And the response body should contain a list of popular dishes
    And the response should match the JSON schema "schemas/dishes/popularDishes.json"


