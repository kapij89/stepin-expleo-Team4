@MWebobileApi
Feature: Get User By ID using API

  Scenario Outline: Website Navigation and Data Extraction
    Given User launches IndianExpress
    When User click on india linklabel

  Scenario: Test Stepin
    Given I launch the app
    When click the product button it entert to the application
    Then Scroll to the product and get the details
    And Push those details to the backend

  Scenario: POST Request for Item ID
    Given the API base URL is "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000"
    When user sends POST request for the endpoint "/items/"
    Then verifies response status code as 200

  Scenario: GET Request for fetching Item ID
    Given the API base URL is "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000"
    When user sends GET request for the item "5070"
