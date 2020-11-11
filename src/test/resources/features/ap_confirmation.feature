Feature: VSA

  Scenario: Barebone Account
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully
    And I open Working Paper "TOD AP supplier confirmations (AP.P02.b) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
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
    And I type some data to AP Perform table
      | Sent | Received | Amount per confirmation | FX | Reconciling items | Explanation | Alternative procedures |
      | True | True     | 20000                   | 1  | 1000.54           | Test        | True                   |
    And I update alternative procedure on AP Confirmation
    Then I check the "Alternative procedures" section will be activated
    And I open "Alternative procedures" section on Working Paper
    And I open Testing Sheet 1 on AP Confirmation
    Then I should see the AP Testing Sheet
    And I type some data to AP Testing Sheet
      | Unique-ID | Date | Text | Amount per sample/selection | Add a column | Unexplained difference | Were any exceptions noted? |
      | 1         | 25/02/1990  | Test | 2000                 |      Test    | 2000.54                | Yes                        |
    And I save and close AP Testing sheet
    Then I check value on AP Balance Summary table after saving Testing Sheet
      | Sheet Index | Unexplained difference | Were any exceptions noted? |
      | 1           | 2000.54                | Yes                        |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed
    And I save the Working paper
    Then I close the Working paper
    Then Close browser


