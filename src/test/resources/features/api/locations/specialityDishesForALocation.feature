@specialityDishes @api
Feature: Retrieve speciality dishes of a particular location
  As a user
  I want to retrieve the speciality dishes of location
  So that I can view all speciality dishes of location

  @positive @smoke
  Scenario Outline: Retrieve speciality dishes for a valid location
    When I send a GET request to "locations/<ID>/speciality-dishes"
    Then the response status code should be <statusCode>

    Examples:
      | ID | statusCode |
      |LOC001| 200|
      |LOC002| 200|
      |LOC003| 200|

  @positive @regression
  Scenario Outline: Retrieve speciality dishes for a valid location
    When I send a GET request to "locations/<ID>/speciality-dishes"
    Then the response status code should be <statusCode>
    And the response should contain a list of speciality dishes
    And the response should match the JSON schema "schemas/locations/specialityDishesSchema.json"

    Examples:
      | ID | statusCode |
      |LOC001| 200|
      |LOC002| 200|
      |LOC003| 200|

  @negative @regression
  Scenario Outline: Retrieve speciality dishes for an invalid location ID
    When I send a GET request to "locations/<ID>/speciality-dishes"
    Then the response status code should be 404
    And the response should contain message "Location not found"
    Examples:
      | ID |
      |14    |




