@signup @api
Feature: User signup API
  As a user
  I want to be able to sign up using my email
  So that I can create my account

  @positive @smoke
  Scenario Outline: Successful user signup with different data
    Given the user has firstName "<firstName>", lastName "<lastName>", email "<email>", and password "<password>"
    When the user sends a signup request
    Then the response status code should be 201

    Examples:
      | firstName | lastName  | email              | password |
      | Mahendra    | Bahubali | bahul123456@example.com | B@hubali15 |


  @positive @regression
  Scenario Outline: Successful user signup with different data
    Given the user has firstName "<firstName>", lastName "<lastName>", email "<email>", and password "<password>"
    When the user sends a signup request
    Then the response status code should be 201
    And the response should contain message "<message>"
    And the response should match the JSON schema "schemas/authentication/userRegistrationSchema.json"

    Examples:
      | firstName | lastName  | email              | password | message                      |
      | Mahendra    | Bahubali | bahul1234567@example.com | B@hubali15 | User registered successfully |


  @negative @regression
  Scenario Outline: Unsuccessful user signup with invalid data
    Given the user has firstName "<firstName>", lastName "<lastName>", email "<email>", and password "<password>"
    When the user sends a signup request
    Then the response status code should be 400
    And the response should contain errorMessage "<errorMessage>"

    Examples:
      | firstName                                              | lastName                                               | email                | password         | errorMessage                                                    |
      |                                                        | Smith                                                  | alice@example.com    | SecurePaas2025!  | First name is required. Please enter your first name to continue. |
      | ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123 | Smith                                                  | bobs@example.com     | SecurePass2025!  | First name cannot exceed 50 characters.                         |
      | Alice                                                  |                                                        | alice@example.com    | Aashi@123      | Last name is required. Please enter your last name to continue. |
      | Alice                                                  | ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123 | bobs@example.com     | SecurePass2025!  | Last name cannot exceed 50 characters.                          |
      | Alice                                                  | Smith                                                  |                      | Aashi@123      | Email address is required. Please enter your email to continue. |
      | Alice                                                  | Smith                                                  | invalidemail.com     | SecurePass2025!  | Invalid email format. Please enter a valid email address.       |
      | Alice                                                  | Smith                                                  | alice@example.com    | lowercase123!    | Password must contain at least one uppercase letter.            |
      | Alice                                                  | Smith                                                  | alice@example.com    | UPPERCASE123!    | Password must contain at least one lowercase letter.            |
      | Alice                                                  | Smith                                                  | alice@example.com    | SecurePass123    | Password must contain at least one special character.           |
      | Alice                                                  | Smith                                                  | alice@example.com    | SecurePass!      | Password must contain at least one number.                      |
      | Alice                                                  | Smith                                                  | alice@example.com    | Sh0rt!           | Password must be at least 8 characters long.                    |


  @negative @regression
  Scenario Outline: Existing user trying to register
    Given the user has firstName "<firstName>", lastName "<lastName>", email "<email>", and password "<password>"
    When the user sends a signup request
    Then the response status code should be 409
    And the response should contain errorMessage "<errorMessage>"

    Examples:
      | firstName | lastName | email              | password  | errorMessage                                   |
      | Deepak    | Sheri    | deep12@example.com | Paass@456 | A user with this email address already exists. |
