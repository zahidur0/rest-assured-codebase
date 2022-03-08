Feature: A user is able to send a put request and validate the response

  Scenario: A user successfully updates an entry
    Given I submit a put request
    When The put request was successful
    Then I am able to validate the entry has been updated