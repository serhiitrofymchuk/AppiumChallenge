@VerticalSwiping
Feature: Vertical Swiping

  @Navigation
  Scenario: Navigation to the Vertical Swiping Screen
    Given the user is on the Samples List screen
    When the user taps the Vertical Swiping sample
    Then the user should see the Vertical Swiping screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Vertical Swiping screen
    When the user taps the Back button on the Vertical Swiping screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Swiping Up the Vertical Swiping Screen
    Given the user is on the Vertical Swiping screen
    When the user swipes up the screen till he reaches the bottom
    Then the user should have seen all of the screen's items
