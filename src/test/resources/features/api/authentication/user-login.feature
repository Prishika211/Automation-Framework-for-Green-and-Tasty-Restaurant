@signin @api
Feature: Sign In with Email and Password
  As a registered user
  I want to be able to sign in using my email and password
  So that I can access my account securely

  @positive @smoke
  Scenario Outline: Valid user logs in successfully with correct email and password
    Given existing user trying to login
    When I send a POST request to "/auth/signin" with username "<email>" and "<password>"
    Then the response status code should be <status_code>

    Examples:
      | email                 | password      | status_code |
      | Harshitha_b@gmail.com | Harshitha@123 | 200         |


  @positive @regression
  Scenario Outline: Valid user logs in successfully with correct email and password
    Given existing user trying to login
    When I send a POST request to "/auth/signin" with username "<email>" and "<password>"
    Then the response status code should be <status_code>
    And the response should contain a valid authentication token
    And the username of user should be "<username>"
    And the role of user should be "<role>"
    And the response should match the JSON schema "schemas/authentication/userlogin.json"
    Examples:
      | email                 | password      | status_code | username    | role     |
      | Harshitha_b@gmail.com | Harshitha@123 | 200         | Harshitha B | CUSTOMER |
      | aashijain06@gmail.com | Aashi@123     | 200         | Aashi Jain  | WAITER   |


  @negative @regression
  Scenario Outline: User login fails with invalid or missing credentials
    When I send a POST request to "/auth/signin" with username "<email>" and "<password>"
    Then the response status code should be <status_code>
    And the response should contain errorMessage "<message>"
    Examples:
      | email                   | password     | status_code | message                                                         |
      | Harshitha_b@gmail.com   | Harshit@123  | 401         | Invalid credentials                                             |
      | sachin@example.com      |              | 400         | Password is required. Please enter your password to continue.   |
      | nonexistent@example.com | Wrong10!     | 401         | Authentication failed: User not exist                           |
      |                         | PassWrong10! | 400         | Email address is required. Please enter your email to continue. |
      | invalidformat.com       | PassWord10!  | 400         | Invalid email format. Please enter a valid email address.       |
