@PhotoView
Feature: Photo View

  @Navigation
  Scenario: Navigation to the Photo View Screen
    Given the user is on the Samples List screen
    When the user taps the Photo View sample
    Then the user should see the Photo View screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Photo View screen
    When the user taps the Back button on the Photo View screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Pinch & Zoom the Photo
    Given the user is on the Photo View screen
    When the user zooms in the photo
    Then the photo should be increased in size
