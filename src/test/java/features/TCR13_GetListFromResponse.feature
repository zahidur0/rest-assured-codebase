Feature: Return a List from a GET response

  Scenario: Successfully return a List from a GET response
    Given I submit a GET request for users information
    When GET request for user information is successful
    Then I can retrieve a list of user names