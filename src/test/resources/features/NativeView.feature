@NativeView
Feature: Native View

  @Navigation
  Scenario: Navigation to the Native View Screen
    Given the user is on the Samples List screen
    When the user taps the Native View sample
    Then the user should see the Native View screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Native View screen
    When the user taps the Back button on the Native View screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Reviewing the Native View Screen
    Given the user is on the Native View screen
    Then the user should see the expected views
