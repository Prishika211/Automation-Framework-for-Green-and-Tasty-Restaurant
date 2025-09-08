@ui
Feature: User Profile Registration
  As a user
  I want to have the ability to register
  So that I can create an account with customized settings.

  Background:
    Given I am on the Home page
    And I click on the Sign In button on HomePage
    And I click on the Create an Account link

    @smoke
  Scenario: Successful user registration
    When I fill in the "First name" field with "Man"
    And I fill in the "Last name" field with "Kap"
    And I fill in the "Email" field with "man1256789@example.com"
    And I fill in the "password" field with "StrongPass123!"
    And I fill in the "Confirm password" field with "StrongPass123!"
    And I click on the Create an Account button
    Then I should be redirected to the Sign In page
 @regression
  Scenario: Unsuccessful registration - Invalid first name
    When I fill in the "First name" field with "John!"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "@123abcABC"
    And I fill in the "Confirm password" field with "@123abcABC"
    And I click on the Create an Account button
    Then I should see an error message of "First name must be up to 50 characters. Only Latin letters, hyphens, and apostrophes are allowed."
@regression
  Scenario: Unsuccessful registration - Invalid last name
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe@"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "@123abcABC"
    And I fill in the "Confirm password" field with "@123abcABC"
    And I click on the Create an Account button
    Then I should see an error message of "Last name must be up to 50 characters. Only Latin letters, hyphens, and apostrophes are allowed."

  @regression
  Scenario: Unsuccessful registration - Invalid email address
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "john.doeexample.com"
    And I fill in the "password" field with "@123abcABC"
    And I fill in the "Confirm password" field with "@123abcABC"
    And I click on the Create an Account button
    Then I should see an error message of "Invalid email address. Please ensure it follows the format: username@domain.com."

    @regression
  Scenario: Unsuccessful registration - Empty password
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with ""
    And I fill in the "Confirm password" field with "@123abcABC"
    And I click on the Create an Account button
    Then I should see an error message of "Password is required."

    @regression
  Scenario: Unsuccessful registration - Password too short
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "Aa@123"
    And I fill in the "Confirm password" field with "Aa@123"
    And I click on the Create an Account button
    Then I should see an error message of "Password must be at least 8-16 characters long"
@regression
  Scenario: Unsuccessful registration - Password too long
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "1234567Aaaahshahahaa!"
    And I fill in the "Confirm password" field with "1234567Aaaahshahahaa!"
    And I click on the Create an Account button
    Then I should see an error message of "Password must be at least 8-16 characters long"

  @regression
  Scenario: Unsuccessful registration - Missing uppercase letter in password
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "johndoe@1234"
    And I fill in the "Confirm password" field with "johndoe@1234"
    And I click on the Create an Account button
    Then I should see an error message of "At least one uppercase letter required"

    @regression
  Scenario: Unsuccessful registration - Missing lowercase letter in password
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "JOHNDOE@1234"
    And I fill in the "Confirm password" field with "JOHNDOE@1234"
    And I click on the Create an Account button
    Then I should see an error message of "At least one lowercase letter required"

      @regression
  Scenario: Unsuccessful registration - Missing number in password
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "Johndoe@"
    And I fill in the "Confirm password" field with "Johndoe@"
    And I click on the Create an Account button
    Then I should see an error message of "At least one number required"

  @regression
  Scenario: Unsuccessful registration - Missing special character in password
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "Johndoe1234"
    And I fill in the "Confirm password" field with "Johndoe1234"
    And I click on the Create an Account button
    Then I should see an error message of "At least one special character required"

  @regression
  Scenario: Unsuccessful registration - Mismatched passwords
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "Johndoe@1234"
    And I fill in the "Confirm password" field with "@1234"
    And I click on the Create an Account button
    Then I should see an error message of "Confirm password must match new password"

  @regression
  Scenario: Unsuccessful registration - Email already exists
    When I fill in the "First name" field with "John"
    And I fill in the "Last name" field with "Doe"
    And I fill in the "Email" field with "Harshitha_b@gmail.com"
    And I fill in the "password" field with "Johndoe@1234"
    And I fill in the "Confirm password" field with "Johndoe@1234"
    And I click on the Create an Account button
    Then I shouldn't  be redirected to the Sign In page