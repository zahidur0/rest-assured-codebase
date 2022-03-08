Feature: A user is able to send a post request and validate the response

  Scenario: A user successfully posts an entry
    Given I submit a post request
    When The post request was successful
    Then I validate the entry has been added