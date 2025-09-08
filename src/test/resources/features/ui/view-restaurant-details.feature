@ui
Feature: View Restaurant Details
  As a User
  I want to be able to view restaurant details (e.g., location, ratings, reviews)
  So that I can decide if I want to visit or make a reservation.

  Background:
    Given I am on the Home page

  @smoke
  Scenario: Verify presence of Nav elements
    When I check the Nav elements
    Then the following elements should be present:
      | Logo         |
      | Main Page    |
      | Book a Table |
      | Sign In      |

  @regression
  Scenario: Verify that the main page displays general restaurant information
    When I view the general restaurant section
    Then the page should display the following information:
      | Name        |
      | Description |
      | View Menu   |

  @regression
  Scenario: Validate that restaurant locations are displayed on the main page
    When I scroll to the "Locations" section
    Then I should see each restaurant location with:
      | Address   |
      | Capacity  |
      | Occupancy |

  @regression
  Scenario: Verify navigation to location details page on clicking a location card
    When I click on a location card
    Then I should be navigated to the Location Overview page

  @regression
  Scenario: Check that location overview displays rating, photo, and description
    When I click on a location card
    And I view a restaurant's location details
    Then the page should display the following:
      | Rating      |
      | Photo       |
      | Description |

  @regression
  Scenario: Verify that speciality dishes are displayed on location page
    When I click on a location card
    And I view the "Specialty Dishes" section
    Then the following dishes should be displayed for each dish:
      | Name   |
      | Image  |
      | Weight |
      | Price  |

  @regression
  Scenario: Validate customer review filter functionality
    When I click on a location card
    And I filter reviews by "Service" and "Cuisine experience"
    Then The review list should update accordingly

  @regression
  Scenario Outline: Validate feedback sorting by "Top rated first", "Low rated first", "Oldest first", and "Newest first"
    When I click on a location card
    And I sort the reviews using the dropdown with the following options "<sortingOptions>"
    Then the reviews should be displayed in the selected order "<sortingOptions>"
    Examples:
      | sortingOptions  |
      | Top rated first |
      | Low rated first |
      | Oldest first    |
      | Newest first    |

  @regression
  Scenario: Check pagination in customer reviews section
    When I click on a location card
    And I scroll to "Service" section
    And I filter reviews by "Service" and "Cuisine experience"
    Then The review list should update accordingly
    And I navigate through review pages using pagination controls
    Then I should be taken to the corresponding reviews page