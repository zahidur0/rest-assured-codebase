Feature: Check response against JSON schema

  Scenario: Successfully verify response against JSON schema
    Given I have a JSON response
    And I have a JSON schema
    Then I find the JSON response agrees with the schema