Feature: Serialise XML as a POJO

  Scenario: Successfully serialise XML as a POJO
    Given I receive a XML response
    When I serialise an object using the XML response
    Then I am able to validate the XML has successfully been serialised as a POJO

