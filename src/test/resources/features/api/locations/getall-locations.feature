@locations @api

Feature: Fetch All Locations

  @smoke
  Scenario: Successfully fetch all locations
    When I send a GET request to "locations"
    Then the response status code should be 200

  @regression
  Scenario: Successfully fetch all locations
    When I send a GET request to "locations"
    Then the response status code should be 200
    And the response should contain a list of locations
    And the response should match the JSON schema "schemas/locations/allLocationsSchema.json"
