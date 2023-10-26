@WheelPicker
Feature: Wheel Picker

  @Navigation
  Scenario: Navigation to the Wheel Picker Screen
    Given the user is on the Samples List screen
    When the user taps the Wheel Picker sample
    Then the user should see the Wheel Picker screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Wheel Picker screen
    When the user taps the Back button on the Wheel Picker screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario Outline: Selecting a Color
    Given the user is on the Wheel Picker screen
    When the user selects '<color>' from the dropdown
    Then the dropdown should have '<color>' selected
    And the current color should be '<color>'

    Examples:
      | color |
      | red   |
      | green |
      | blue  |
      | black |