@Carousel
Feature: Carousel

  @Navigation
  Scenario: Navigation to the Carousel Screen
    Given the user is on the Samples List screen
    When the user taps the Carousel sample
    Then the user should see the Carousel screen

  @Navigation
  Scenario: Navigation Back to the Samples List Screen
    Given the user is on the Carousel screen
    When the user taps the Back button on the Carousel screen
    Then the user should be on the Samples List screen

  @Sample
  Scenario: Swiping the Carousel to the Left
    Given the user is on the Carousel screen
    Then the block number 1 should be shown
    And the page number should be 1
    When the user swipes the current block to the left
    Then the block number 2 should be shown
    And the page number should be 2
    When the user swipes the current block to the left
    Then the block number 3 should be shown
    And the page number should be 3
    When the user swipes the current block to the left
    Then the block number 1 should be shown
    And the page number should be 1

  @Sample
  Scenario: Swiping the Carousel to the Right
    Given the user is on the Carousel screen
    Then the block number 1 should be shown
    And the page number should be 1
    When the user swipes the current block to the right
    Then the block number 3 should be shown
    And the page number should be 3
    When the user swipes the current block to the right
    Then the block number 2 should be shown
    And the page number should be 2
    When the user swipes the current block to the right
    Then the block number 1 should be shown
    And the page number should be 1

  @Sample
  Scenario: Automatic Swiping of the Carousel
    Given the user is on the Carousel screen
    Then the blocks should swipe automatically
