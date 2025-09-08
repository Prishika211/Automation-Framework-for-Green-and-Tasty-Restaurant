@ui
Feature: Reservation of the Table by Customer
  As a Customer
  I should be able to make table reservations
  So I could visit the Restaurant on a preferred time slot

  Background:
    Given I am on the Home page
    And I click on the Sign In button on HomePage
    And I enter "Harshitha_b@gmail.com" into the Email field
    And I enter "Harshitha@123" into the Password field
    And I click on the Sign In button
    And I should see the "Reservations" tab on the dashboard page
    And I navigate to the "Book a Table" section

  @smoke
  Scenario: Verify that a logged-in Customer can access the reservation form
    And I click on the "Select Location" dropdown
    And I select "123 Main Street, Downtown, Metropolis, 12345" from the locations list

    And I click on the date input field
    And I clear the date field
    And I type "29-04-2025" into the date field

    And I click on the "time" dropdown
    And I select "14:00" from the time options

    And I click on the guests input field
    And I clear the guests field
    And I type "04" into the guests field

    And I click on the "Find a Table" button

    And I click on "+Show all"
    And all available slots should be visible
    And I click on a preferred time slot

    Then the reservation form should be displayed

  @regression
  Scenario: Verify confirmation message after successful reservation
    And I click on the "Select Location" dropdown
    And I select "123 Main Street, Downtown, Metropolis, 12345" from the locations list

    And I click on the date input field
    And I clear the date field
    And I type "01-05-2025" into the date field

    And I click on the "time" dropdown
    And I select "14:00" from the time options

    And I click on the guests input field
    And I clear the guests field
    And I type "04" into the guests field

    And I click on the "Find a Table" button

    And I click on "+Show all"
    And all available slots should be visible
    And I click on a preferred time slot

    When I submit the reservation
    Then a confirmation message should be displayed

  @regression
  Scenario: Verify system prevents double booking for same table and time
    And I click on the "Select Location" dropdown
    And I select "123 Main Street, Downtown, Metropolis, 12345" from the locations list

    And I click on the date input field
    And I clear the date field
    And I type "01-05-2025" into the date field

    And I click on the "time" dropdown
    And I select "17:30" from the time options

    And I click on the guests input field
    And I clear the guests field
    And I type "04" into the guests field

    And I click on the "Find a Table" button

    Then Then I shouldn't see the "Available Tables" section

  @regression
  Scenario: Verify cancellation is allowed until 30 mins before reservation time
    When I click on the reservations page
    And the cancel option should be enabled
    Then I clicked on Yes,Cancel

  @regression
  Scenario: Verify reservation status shows as 'Reserved' on Customer dashboard
    When I click on the reservations page
    Then the reservation status should be shown as "RESERVED"
