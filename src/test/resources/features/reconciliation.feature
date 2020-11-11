Feature: VSA

  Scenario: Barebone Account
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "24-Feb-2020_a"
    Then Navigate to global navigation successfully
    And I open Working Paper "TOD Reconciliation of AR (AR.P06) - Interim"
    And Enable editing Working Paper
    And I open "Perform" section on Working Paper
    Then I check the Agree Summary will be displayed
    Then I check the widget table "Schedules from entity" will be displayed
    Then I check the widget table "Reconciled difference" will be displayed
    And I add 2 rows to widget table "Schedules from entity"
    And I type some data to widget table "Schedules from entity"
      | Record | Description | Amount |
      | 1      | Test        | 1000.54 |
      | 2     | Test        | 1000.54 |
      | 3      | Test        | 1000.54 |
    And I add 2 rows to widget table "Reconciled difference"
    And I type some data to widget table "Reconciled difference"
      | Record | Description | Amount |
      | 1      | Test        | 1000.64 |
      | 2      | Test        | 1000.64 |
      | 3      | Test        | 1000.64 |
    Then I verify data on Agree Summary card
    And I save the Working paper
    Then I close the Working paper
    Then Close browser

