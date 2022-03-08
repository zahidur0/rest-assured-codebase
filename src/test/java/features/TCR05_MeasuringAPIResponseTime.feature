Feature: Measure the response time of API requests

  Scenario: I compare the time taken for a GET and POST request response
    Given I submit a timed post request
    And I submit a timed get request
    When I return the time taken for both requests
    Then I compare the get api response time to the post api response time