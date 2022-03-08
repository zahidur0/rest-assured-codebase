Feature: A user is able to send a delete request and validate the response

  Scenario: A user successfully deletes an entry
    Given I submit a delete request
    When The delete request was successful
    Then I am able to validate the entry has been deleted