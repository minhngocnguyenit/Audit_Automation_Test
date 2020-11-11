Feature: Setup Materiality
  Scenario: Login & Set up Materiality
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful

  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement"
    Then Navigate to global navigation successfully
    And I open ROMM Summary
    And I set filter in "RoMMs" table
      | type | Column | Select | Value |
      | 1  | Associated ABCOTD | Is | Accrued expenses |
    And I set filter in "Controls" table
      | type | Column | Select | Value |
      | 2  | Associated ABCOTD | Is |Inventory |
#    And I set filter in "Procedures" table
#      | type | Column | Select | Value |
#      | 3  | ROMMS | Is | AP.R05 |
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
    Then Close browser