Feature: Testing mobile app with Appium

  #@Instalogin
  #Scenario Outline: Verify a specific element on the screen
    #Given I launch the app
    #When I should see the element
    #Then Enter the "<Username>" and "<password>"
    #And Click the Login button
    #
#
    #Examples: 
      #| Username    | password      |
      #| stepin_hope | Priya@2024    |
    
 @Instapost
  Scenario: Post in instagram
    Given I launch the app
    When click the create post btn
    Then select the image
    And post the image with tag
    

  
    