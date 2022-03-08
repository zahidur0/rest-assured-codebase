Feature: Create JSON from a POJO

  Scenario: Successfully create JSON from a POJO
    Given I create a pojo for json creation
    When I set the pojo fields for json creation
    Then I create an json entry
