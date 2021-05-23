Feature: Search for “iPhone 11” on the website as Amazon and Ebay
  Scenario: Displays results in ascending order by price
#    Given Access to the Amazon system
#    When Search "iphone 11" on Amazon
    And Access to the Tiki system
    And When Search "iphone11" on Tiki
#    Then Displays results for two websites in ascending order by price