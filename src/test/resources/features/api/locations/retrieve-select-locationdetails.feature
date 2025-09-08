@location @api
Feature: Retrieve location details
  As a client of the Location API
  I want to retrieve location dropdown options
  So that I can ensure the API behaves correctly across various use cases

  @smoke
  Scenario: Successfully retrieve location details
    When I send a GET request to "locations/select-options"
    Then the response status code should be 200

  @regression
  Scenario: Successfully retrieve location details
    When I send a GET request to "locations/select-options"
    Then the response status code should be 200
    And the response body should contain a list of location details
    And the response should match the JSON schema "schemas/locations/selectLocationSchema.json"

