Feature: VSA

  Scenario: Barebone Account
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "09-03-2020_a"
    Then Navigate to global navigation successfully
    And I open Working Paper "TOD Customer confirmations (AR.P02.a) - Final"
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
    And I answer "Lower level (e.g., invoice)" to Tailoring question "What is your unit of samples/selections?"
    And I generate Samples/selections for testing
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    And I type some data to AR Perform table
      | Sent | Received | Amount per confirmation | FX | Reconciling items | Explanation | Alternative procedures |
      | True | True     | 20000                   | 1  | 1000.54           | Test        | True                   |
    And I update alternative procedure on AR Confirmation
    Then I check the "Alternative procedures" section will be activated
    And I open "Alternative procedures" section on Working Paper
    And I type some data to AR Lower Level table
      | Agreed to documentation of payment? | Payment date | Payment amount | FX | Amount | ID Number | Service/Delivery date | Correct period? | Explanation | Were any exceptions noted? |
      | True                                | 25/02/2020   | 2000           | 1  | 2000   | 1         | 25/03/2020            | True            | Test        | Yes                        |
    And I open "Plan" section on Working Paper
    And I answer "Balance" to Tailoring question "What is your unit of samples/selections?"
    And I generate Samples/selections for testing
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    And I type some data to AR Perform table
      | Sent | Received | Amount per confirmation | FX | Reconciling items | Explanation | Alternative procedures |
      | True | True     | 20000                   | 1  | 1000.54           | Test        | True                   |
    And I update alternative procedure on AR Confirmation
    Then I check the "Alternative procedures" section will be activated
    And I open "Alternative procedures" section on Working Paper
    And I open Testing Sheet 1 on AR Confirmation
    Then I should see the AR Testing Sheet
    And I type some data to AR Testing Sheet
      | Unique-ID | Date | Text | Amount per sample/selection | Agreed to documentation of payment | Payment date | Payment amount | FX | Amount | ID Number | Service/Delivery date | Corret Period? | Explanation | Were any exceptions noted? |
      | 1         | 20/20/2020 | Test | 2000.54               | True                               | 20/20        | 2000.1         | 1  | 1000   | 1         | 20/20                 | True           | Test        | Yes                        |
    And I save and close AR Testing sheet
    Then I check value on AR Balance Summary table after saving Testing Sheet
      | Sheet Index | Unexplained difference | Were any exceptions noted? |
      | 1           | 2000.54                | Yes                        |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper
    Then Close browser