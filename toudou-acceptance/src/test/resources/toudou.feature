Feature: Toudous

  Scenario: Empty Toudous dont have toudou
    Given Empty toudous
    When I list all toudous
    Then my toudous are empty

  Scenario: Add a new toudou
    Given A toudous
    When I add a toudou with label "my first toudou"
    Then a toudou should be created
    And the toudou should have an Id
    And the toudou should have a label "my first toudou"