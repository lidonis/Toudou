Feature: Toudous

  Scenario: Empty Toudous dont have toudou
    Given Empty Toudous
    When I list all toudous
    Then my toudous are empty