@AcceptanceTest
Feature: Cucumber run test

  I want to run a sample feature file.

  Scenario: Cucumber setup

    Given one security "WSB" and two users "Diamond" and "Paper" exist
    When user "Diamond" puts a "buy" order for security "WSB" with a price of "101" and quantity of "50"
    And user "Paper" puts a "sell" order for security "WSB" with a price of "100" and quantity of "100"
#    Then a trade occurs with the price of "100" and quantity of "50"