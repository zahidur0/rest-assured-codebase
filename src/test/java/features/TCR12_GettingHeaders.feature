Feature: Getting headers from a response

  Scenario: Successfully get all headers from a response
    Given I submit a GET request
    When GET request is successful
    Then I can extract all headers

  Scenario: Successfully get one header from a response
    Given I submit a GET request
    When GET request is successful
    Then I can extract one header

