@DoubleTap
Feature: Double Tap

  @Navigation
  Scenario: Navigation to the Double Tap Screen
    Given the user is on the Samples List screen
    When the user taps the Double Tap sample
    Then the user should see the Double Tap screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Double Tap screen
    When the user taps the Back button on the Double Tap screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Validation of the Double Tap Functionality
    Given the user is on the Double Tap screen
    When the user taps twice the Double Tap button
    Then the user should see a Message Popup with 'Double Tap' title and 'Double tap successful!' message on the Double Tap screen
    When the user taps the Ok button on the Message Popup of the Double Tap screen
    Then the Message Popup should disappear from the Double Tap screen
