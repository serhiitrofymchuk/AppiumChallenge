@LongPress
Feature: Long Press

  @Navigation
  Scenario: Navigation to the Long Press Screen
    Given the user is on the Samples List screen
    When the user taps the Long Press sample
    Then the user should see the Long Press screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Long Press screen
    When the user taps the Back button on the Long Press screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Validation of the Long Press Functionality
    Given the user is on the Long Press screen
    When the user does a long press on the button
    Then the user should see a Message Popup with 'Long Pressed' title and 'you pressed me hard :P' message on the Long Press screen
    When the user taps the Ok button on the Message Popup of the Long Press screen
    Then the Message Popup should disappear from the Long Press screen
