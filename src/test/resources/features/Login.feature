@All
Feature: Sauce demo functionality

  Scenario: Ensure user successfully Login
    Given user is on url homepage
    When user fill username
    And user fill password
    And user click enter to login
    Then user verify success login result

  Scenario: Ensure user successfully Logout
    Given user is on url homepage
    When user fill username
    And user fill password
    And user click enter to login
    Then user verify success login result
    When user click logout
    Then user successfully logout