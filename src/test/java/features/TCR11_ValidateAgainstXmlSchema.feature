Feature: Check response against XML schema

  Scenario: Successfully verify response against XML schema
    Given I have a XML response
    And I have a XML schema
    Then I find the XML response agrees with the schema