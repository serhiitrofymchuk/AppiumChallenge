@Slider
Feature: Slider

  @Navigation
  Scenario: Navigation to the Slider Screen
    Given the user is on the Samples List screen
    When the user taps the Slider sample
    Then the user should see the Slider screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Slider screen
    When the user taps the Back button on the Slider screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Adjusting the Slider
    Given the user is on the Slider screen
    When the user moves the slider right by 50%
    Then the Slider should be shown righter on 50%