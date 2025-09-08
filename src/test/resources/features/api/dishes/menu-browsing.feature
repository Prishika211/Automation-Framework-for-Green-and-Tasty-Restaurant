@menu-browsing @api
Feature: Browsing the menu by Customer
  As a Customer
  I want to be able to browse the food menu through the application
  So that I can view available dishes and their details

  @smoke
  Scenario: Valid menu retrieval without filters
    When I send a GET request to "/dishes"
    Then the response status code should be 200

  @regression
  Scenario: Valid menu retrieval without filters
    When I send a GET request to "/dishes"
    Then the response status code should be 200
    And the response should contain a list of dishes with details
    And the response should match the JSON schema "schemas/dishes/MenuBrowsing.json"

  @smoke
  Scenario Outline: Retrieve dish by valid ID
    When I send a GET request to "/dishes/<dishId>"
    Then the response status code should be <statusCode>

    Examples:
      | dishId | statusCode |
      |DISH001| 200|
      |DISH002| 200|
      |DISH003| 200|
      |DISH004| 200|
      |DISH005| 200|

  @regression
  Scenario Outline: Retrieve dish by valid ID
    When I send a GET request to "/dishes/<dishId>"
    Then the response status code should be <statusCode>
    And the response should contain dish details with details
    And the response should match the JSON schema "schemas/dishes/SingleDish.json"

    Examples:
      | dishId | statusCode |
      |DISH001| 200|
      |DISH002| 200|
      |DISH003| 200|
      |DISH004| 200|
      |DISH005| 200|
