@Feature1
Feature: Validate products

  @ScenarioTag1
  Scenario Outline: Validate search product by brand.
    Given User is on home page
    When User searches "<brand>"
    Then "<brand>" is displayed
    Examples:
    |brand|
    |Levis|