Feature: CLick on WebPart of IndianExpress

  @autothon
  Scenario Outline: Website Navigation and Data Extraction
    Given User launches IndianExpress
    When User click on linklabels

    Examples: 
      | label    |
      | India    |
      | Sports   |
      | Business |
