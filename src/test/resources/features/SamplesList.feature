@SamplesList
Feature: Samples List

  @Navigation
  Scenario: Navigation Back to the Login Screen
    Given the user is on the Samples List screen
    When the user taps the Back button on the Samples List screen
    Then the user should be on the Login screen

  @Sample
  Scenario: Validation of the Samples List Screen
    Given the user is on the Samples List screen
    Then the user should see the Samples List items