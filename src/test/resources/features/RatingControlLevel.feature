Feature: Rating Control

  Scenario: Rating Control level decision to read book

    Given I am a customer with a set rating control level of 12
    When I request to read an equal level book B1234
    Then I should get decision to read the book

  Scenario: Rating Control level decision not to read book

    Given I am a customer with a set rating control level of 12
    When I request to read a higher level book BH1234
    Then I should get decision to not read the book