Feature: Get User By ID using API

  Scenario: POST Request for Item ID
    Given the API base URL is "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000"
    When user sends POST request for the endpoint "/items/"
    Then verifies response status code as 200

  Scenario: GET Request for fetching Item ID
    Given the API base URL is "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000"
    When user sends GET request for the item "4837"
    Then verifies response status code as 200

