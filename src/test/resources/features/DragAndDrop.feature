@DragAndDrop
Feature: Drag & Drop

  @Navigation
  Scenario: Navigation to the Drag & Drop Screen
    Given the user is on the Samples List screen
    When the user taps the Drag & Drop sample
    Then the user should see the Drag & Drop screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Drag & Drop screen
    When the user taps the Back button on the Drag & Drop screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Validation of the Drag & Drop Functionality
    Given the user is on the Drag & Drop screen
    When the user drags the circle and drops it on the drop zone
    Then the user should see the success label and the circle should disappear
