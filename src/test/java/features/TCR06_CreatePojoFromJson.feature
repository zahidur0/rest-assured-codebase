Feature: Serialise JSON as a POJO

  Scenario: Successfully serialise JSON as a POJO
    Given I receive a JSON response
    When I serialise an object using the JSON response
    Then I am able to verify the JSON has successfully been serialised as a POJO

  Scenario: Successfully serialise nested JSON as a POJO
    Given I receive a nested JSON response
    When I serialise a nested object using the JSON response
    Then I can access nested data using pojos

