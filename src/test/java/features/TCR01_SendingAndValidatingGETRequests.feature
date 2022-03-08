Feature: A user is able to send a get request to an API and validate the response

  Scenario: A user gets a single object back in a response
    Given I send a request for a single object
    When The request is successful
    Then I am able to validate the returned object

  Scenario: A user gets multiple objects from the request
    Given I send a request for multiple objects
    When The request is successful
    Then I am able to validate specific objects are present