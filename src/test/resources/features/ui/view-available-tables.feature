@ui
Feature: View Available Tables for Reservation
  As a user
  I want to have the possibility to see available tables
  So that I could make a reservation

  Background:
    Given I am on the Home page
    And I click on the Sign In button on HomePage
    And I enter "Harshitha_b@gmail.com" into the Email field
    And I enter "Harshitha@123" into the Password field
    And I click on the Sign In button
    And I should see the "Reservations" tab on the dashboard page
    And I navigate to the "Book a Table" section

    @smoke
  Scenario: Verify that available tables are visible from the Customer dashboard
    Then the following elements should be present: locations, date, time, guests and Find a Table
      | Location     |
      | Date         |
      | Time         |
      | Guests       |
      | Find a Table |

  Scenario Outline: Verify that user can filter tables by location
    When I click on the "Select Location" dropdown
    Then the following "<Locations>" gets filtered by location
    Examples:
      | Locations                                                 |
      | Diagon Alley, Umbridge Street, Platform 9 and 3 quarters. |
      | 123 Main Street, Downtown, Metropolis, 12345              |
      | 789 Beach Road, Oceanview, Coastalville, 67890            |

    @regression
  Scenario: Verify that user can filter tables by guests' quantity
    When I click on the guests input field
    And I clear the guests field
    And I type "04" into the guests field

  @regression
  Scenario: Verify that user can filter table by typing the date directly
    When I click on the date input field
    And I clear the date field
    And I type "29-04-2025" into the date field

  @regression
  Scenario Outline: Verify that user can filter tables by available timeslot
    When I click on the "time" dropdown
    And I select "<time>" from the time options
    Examples:
    | time |
    | 10:30  |
    | 12:15  |
    | 14:00  |
    | 15:45  |
    | 17:30  |
    | 19:15  |
    | 21:00  |

  @regression
  Scenario: Complete booking flow with table availability verification
    When I click on the "Select Location" dropdown
    And I select "123 Main Street, Downtown, Metropolis, 12345" from the locations list

    When I click on the date input field
    And I clear the date field
    And I type "02-05-2025" into the date field

    When I click on the "time" dropdown
    And I select "17:30" from the time options

    When I click on the guests input field
    And I clear the guests field
    And I type "04" into the guests field

    When I click on the "Find a Table" button
    Then I should see the "Available Tables" section




