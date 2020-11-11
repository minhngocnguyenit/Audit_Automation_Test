Feature: Login and Create Engagement
  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page "https://d3levvia.aaps.deloitte.com/"
    When I enter user and password
    Then View page after login successful

  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement"
    Then Navigate to global navigation successfully

  @Risk_Summary
  Scenario: Risk Summary
    And I open ROMM Summary
    And I set filter in "RoMMs" table
      | type | Column | Select | Value |
      | 1  | Associated ABCOTD | Is | Accrued expenses |
    And I set filter in "Controls" table
      | type | Column | Select | Value |
      | 2  | Associated ABCOTD | Is |Inventory |
    And I export file in "RoMMs" table
      | type |
      | 1  |
    And I export "Filter" file in all table
      | type |
      | 1    |
    And I export "UnFilter" file in all table
      | type |
      | 2    |
    Then ROMM Summary - I check file "ROMM_Summary" is downloaded successfully

  Scenario: Close browser
    Given Close browser