@WebView
Feature: Web View

  @Navigation
  Scenario: Navigation to the Web View Screen
    Given the user is on the Samples List screen
    When the user taps the Web View sample
    Then the user should see the Web View screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Web View screen
    When the user taps the Back button on the Web View screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Web Content Accessibility
    Given the user is on the Web View screen
    Then the user should see the Hacker News web content