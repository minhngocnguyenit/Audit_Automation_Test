Feature: VSA

  Scenario: Barebone Account
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "tttt"
    Then Navigate to global navigation successfully
    And I open Working Paper "TOD AR subsequent receipts (AR.P02.b) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
#    And I answer "Yes" to Tailoring question "Are you testing the reconciling items in this working paper?"
#    Then I check the Tailoring Question "Is the population reconciling to something other than the trial balance?" will be displayed
#    Then I check the Agree Summary will be displayed
#    Then I check the widget table "Schedules from entity" will be displayed
#    Then I check the widget table "Reconciled difference" will be displayed
#    And I add 2 rows to widget table "Schedules from entity"
#    And I type some data to widget table "Schedules from entity"
#      | Record | Description | Amount |
#      | 1      | Test        | 1000.54 |
#      | 2     | Test        | 1000.54 |
#      | 3      | Test        | 1000.54 |
#    And I add 2 rows to widget table "Reconciled difference"
#    And I type some data to widget table "Reconciled difference"
#      | Record | Description | Amount |
#      | 1      | Test        | 1000.64 |
#      | 2      | Test        | 1000.64 |
#      | 3      | Test        | 1000.64 |
#    Then I verify data on Agree Summary card
#    And I answer "Yes" to Tailoring question "Are there adjustments to the population?"
#    And I type some data to widget table "Adjustments to population"
#      | Description of adjustment | Amount |
#      | Test                      | 20000  |
    And I answer "Test of all items in the population" to Tailoring question "Determine your testing approach"
    And I type 5 to field "Document the number of rows needed for your selections"
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
      | 3        | 25/02/2020 | Test | 1000.54               |
      | 4        | 25/02/2020 | Test | 1000.54               |
      | 5        | 25/02/2020 | Test | 1000.54               |
    And I generate Samples/selections for testing
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    And I type some data to Subsequent Receipt Perform table
      | Agreed to documentation of payment? | payment date | Payment amount | FX | Amount | ID Number | Service/delivery date | Correct period? | Explanation | Were any exceptions noted?|
      | True                                | 25/02/2020   | 1000.43        | 1  | 20000.43 | 1       | 26/02/2009            | True            | test        | Yes                       |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed
#    And I save the Working paper
    Then I close the Working paper
    Then Close browser
    

