@viewAvailableTables @api
Feature: View available tables for booking

  @smoke
  Scenario Outline: Verify filtering tables by available location Id, guests, time and date
    When I send a GET request to "/bookings/tables" with query params locationId "<locationID>" date "<date>" time "<time>" guests "<guests>"
    Then the response status code should be 200
    Examples:
      | locationID | date       | time  | guests |
      | LOC001     | 2025-05-22 | 10:00 | 4      |
      | LOC002     | 2025-05-22 | 11:00 | 4      |
      | LOC003     | 2025-05-22 | 12:00 | 4      |

  @regression
  Scenario Outline: Verify filtering tables by available location Id, guests, time and date
    When I send a GET request to "/bookings/tables" with query params locationId "<locationID>" date "<date>" time "<time>" guests "<guests>"
    Then the response status code should be 200
    And the response should contain a list of available tables "<tables>" filtered by all query parameters
    And the response should match the JSON schema "schemas/bookings/view-available-tables-for-booking.json"
    Examples:
      | locationID | date       | time  | guests | tables |
      | LOC001     | 2025-05-22 | 10:00 | 4      | 2      |
      | LOC002     | 2025-05-22 | 12:00 | 4      | 2      |
      | LOC003     | 2025-05-22 | 13:03 | 4      | 2      |

  @regression
  Scenario Outline: Verify error when invalid location ID is provided
    When I send a GET request to "/bookings/tables" with query params locationId "<locationID>" date "2025-05-22" time "15:00" guests "4"
    Then the response status code should be 400
    And the response should contain message "Location not found. Please select a valid location."
    Examples:
      | locationID |
      | LOC00      |

  @regression
  Scenario Outline: Verify error when invalid date format is provided
    When I send a GET request to "/bookings/tables" with query params locationId "LOC001" date "<date>" time "15:00" guests "2"
    Then the response status code should be 400
    And the response should contain message "Invalid date format. Please use YYYY-MM-DD format."
    Examples:
      | date       |
      | 02-05-2025 |
      | 05-22-2025 |

  @regression
  Scenario Outline: Verify error when invalid guest quantity is provided
    When I send a GET request to "/bookings/tables" with query params locationId "LOC001" date "2024-05-10" time "15:00" guests "<guests>"
    Then the response status code should be 400
    And the response should contain message "Number of guests must be greater than zero. | Invalid date. Please select a future date."
    Examples:
      | guests |
      | -10    |
      | -5     |
      | 0      |

  @regression
  Scenario Outline: Verify response when filtering by maximum guest quantity
    When I send a GET request to "/bookings/tables" with query params locationId "<locationId>" date "2025-05-22" time "15:00" guests "<guests>"
    Then the response status code should be 400
    And the response should contain message "<message>"
    Examples:
      | locationId | guests | message                                                                     |
      | LOC001     | 29     | For large parties (over 20 guests), please contact the restaurant directly. |
      | LOC002     | 29     | For large parties (over 20 guests), please contact the restaurant directly. |
      | LOC003     | 29     | For large parties (over 20 guests), please contact the restaurant directly. |

  @regression
  Scenario Outline: Verify response when filtering by a past time
    When I send a GET request to "/bookings/tables" with query params locationId "LOC001" date "2025-05-22" time "<pastTime>" guests "4"
    Then the response status code should be 400
    And the response should contain message "Invalid time slot. Please select from available slots: 10:30, 12:15, 14:00, 15:45, 17:30, 19:15, 21:00"
    Examples:
      | pastTime |
      | 09:00    |

  @regression
  Scenario Outline: Verify response when filtering by a past date
    When I send a GET request to "/bookings/tables" with query params locationId "LOC001" date "<pastDate>" time "11:00" guests "4"
    Then the response status code should be 400
    And the response should contain message "Invalid date. Please select a future date."
    Examples:
      | pastDate   |
      | 2025-05-01 |

  @regression
  Scenario Outline: Verify response when filtering by a far future date
    When I send a GET request to "/bookings/tables" with query params locationId "LOC001" date "<farFutureDate>" time "11:00" guests "4"
    Then the response status code should be 400
    And the response should contain message "Invalid date. Please select a date within 31 days of today"
    Examples:
      | farFutureDate |
      | 2025-07-22    |
