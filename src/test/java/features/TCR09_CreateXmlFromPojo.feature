Feature: Create XML from a POJO

  Scenario: Successfully create XML from a POJO
    Given I create a pojo
    When I set the pojo fields
    Then I create an XML entry
