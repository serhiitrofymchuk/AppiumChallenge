@Login @Sample
Feature: Login

  Background:
    Given the user is on the Login screen

  Scenario: Successful Login with the Default Credentials
    When the user taps the Login button
    Then the user should be on the Samples List screen

  Scenario: Successful Login with the Valid Credentials
    When the user enters the valid credentials
    And the user taps the Login button
    Then the user should be on the Samples List screen

  Scenario: Invalid Login
    When the user enters username 'invalidusername' and password 'invalidpassword'
    And the user taps the Login button with invalid credentials
    Then the user should see a Message Popup with 'Oops' title and 'Invalid  Credentials' message on the Login screen
    When the user taps the Ok button on the Message Popup of the Login screen
    Then the Message Popup should disappear from the Login screen
