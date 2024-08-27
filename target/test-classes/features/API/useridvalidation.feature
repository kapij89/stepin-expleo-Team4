Feature: Get User By ID using API

@userid
  Scenario: Get User by ID
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain "Leanne Graham"

  Scenario: X Post via API
    Given the API base URL is "https://x.com/i/api/1.1/jot/client_event.json"
    When I send a POST request
 #   Then the response status code should be 200

  Scenario: SAMPLE post api
    Given api BASE url IS "https://dummy.restapiexample.com/api/v1"
    When user sends POST request to "/create"
    Then verifies response status code as 200

  Scenario: SAMPLE GET api
    Given api BASE url IS "https://dummy.restapiexample.com/api/v1"
    When user sends POST request to "/employees"
    Then verifies response status code as 200

  Scenario: SAMPLE GET api
    Given api BASE url IS "https://dummy.restapiexample.com/api/v1"
    When user sends POST request to "/employees"
    Then verifies response status code as 200

  Scenario: SAMPLE PUT api
    Given api BASE url IS "https://dummy.restapiexample.com/api/v1"
    When user sends PUT request to "/update/21"
    Then verifies response status code as 200

  Scenario: POST request for CreateTweet
    Given the API base URL is "https://x.com/i/api/graphql/xT36w0XM3A8jDynpkram2A"
    When user sends POST request for the endpoint "/CreateTweet"
    Then verifies response status code as 200

