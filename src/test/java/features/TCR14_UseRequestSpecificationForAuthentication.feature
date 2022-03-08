Feature: Use a RequestSpecification to pass authorisation check

  Scenario: Successfully use a RequestSpecification object to pass authorisation check
    Given I setup a RequestSpecification object
    When I submit a GET request with no explicit authorisation specified
    Then the submit request is successful