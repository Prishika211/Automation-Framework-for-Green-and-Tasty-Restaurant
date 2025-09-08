@locationsFeedback @api
Feature: Retrieve feedback for restaurant locations

  @positive @smoke
  Scenario Outline: Retrieve feedback for valid restaurant location
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "<type>"
    Then the response status code should be 200

    Examples:
      | ID     | type               |
      | LOC001 | SERVICE            |
      | LOC002 | SERVICE            |
      | LOC003 | SERVICE            |
      | LOC001 | CUISINE_EXPERIENCE |
      | LOC002 | CUISINE_EXPERIENCE |
      | LOC003 | CUISINE_EXPERIENCE |

  @positive @regression
  Scenario Outline: Retrieve feedback for valid restaurant location
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "<type>"
    Then the response status code should be 200
    And the response should contain a list of feedbacks
    And the response should match the JSON schema "schemas/locations/locationFeedbacksSchema.json"

    Examples:
      | ID     | type               |
      | LOC001 | SERVICE            |
      | LOC002 | SERVICE            |
      | LOC003 | SERVICE            |
      | LOC001 | CUISINE_EXPERIENCE |
      | LOC002 | CUISINE_EXPERIENCE |
      | LOC003 | CUISINE_EXPERIENCE |

  @negative @regression
  Scenario Outline: Retrieve feedback for invalid restaurant location
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "<type>"
    Then the response status code should be 404
    And the response should contain message "Restaurant not found"

    Examples:
      | ID     | type               |
      | LOC00 | SERVICE            |
      | LOC00 | CUISINE_EXPERIENCE |

  @negative @regression
  Scenario Outline: Retrieve feedback with invalid type
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "Food"
    Then the response status code should be 400
    And the response should contain message "Invalid feedback type: Food"

    Examples:
      | ID     |
      | LOC001 |
      | LOC002 |
      | LOC003 |

  @negative @regression
  Scenario Outline: Retrieve feedback with missing type
    When I send a GET request to "/locations/<ID>/feedbacks"
    Then the response status code should be 400
    And the response should contain message "Feedback type is required"

    Examples:
      | ID     |
      | LOC001 |
      | LOC002 |
      | LOC003 |

  @positive @pagination @regression
  Scenario Outline: Retrieve feedback with pagination
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "<type>" (page=1, size=10)
    Then the response status code should be 200
    And the response should contain a list of feedbacks with pagination (size=10, page=1)
    And the response should match the JSON schema "schemas/locations/locationFeedbacksSchema.json"

    Examples:
      | ID     | type               |
      | LOC001 | SERVICE            |
      | LOC002 | SERVICE            |
      | LOC003 | SERVICE            |
      | LOC001 | CUISINE_EXPERIENCE |
      | LOC002 | CUISINE_EXPERIENCE |
      | LOC003 | CUISINE_EXPERIENCE |

  @positive @pagination @regression
  Scenario Outline: Retrieve feedback with default pagination
    When I send a GET request to "/locations/<ID>/feedbacks" with filter type "<type>"
    Then the response status code should be 200
    And the response should contain a list of feedbacks with pagination (size=20, page=0)
    And the response should match the JSON schema "schemas/locations/locationFeedbacksSchema.json"

    Examples:
      | ID     | type               |
      | LOC001 | SERVICE            |
      | LOC002 | SERVICE            |
      | LOC003 | SERVICE            |
      | LOC001 | CUISINE_EXPERIENCE |
      | LOC002 | CUISINE_EXPERIENCE |
      | LOC003 | CUISINE_EXPERIENCE |

