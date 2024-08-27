Feature: Instagram login
    
@insta
  Scenario: Login to instagram and send a post

    Given User launched Instagram
    When user is able to login with user cred
    And user is able to create a post

#    Then user should be able to log in


  Scenario: Shadow root element
    Given User tests shadowroot


