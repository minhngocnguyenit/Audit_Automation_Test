Feature: Setup Par&Scoping
  Scenario: Cash
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "tttt"
    Then Navigate to global navigation successfully
    And I open Working Paper "TOD Bank confirmations (CAS.P01.a) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I type 5 to field "How many bank accounts are you testing"
    And I answer "Yes" to Tailoring question "Do any of the bank accounts include foreign currency?"
    And I generate Samples/selections for testing on Cash
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    Then I should see the Cash Perform Yes Foreign Currency table
    And I type data to Cash Perform yes foreign currency table
      | Unique-ID | Text | Balance | Sent | Received | Balance per confirmation/statement | Additive item | Subtractive Items | FX | Other item noted on the confirmation |
      | 1         | Test | 1000    | True | True     | 1000.01                            | 1000.02       | 1000.03           | 1  | Test                                 |
      | 2         | Test | 1000    | True | True     | 1000.01                            | 1000.02       | 1000.03           | 1  | Test                                 |
    And I open "Plan" section on Working Paper
    And I answer "No" to Tailoring question "Do any of the bank accounts include foreign currency?"
    And I generate Samples/selections for testing on Cash
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    Then I should see the Cash Perform No Foreign Currency table
    And I open "IPE" section on Working Paper
    And I answer "Yes" to Tailoring question "Did we use any IPE as audit evidence?"
    Then I check the Vertical will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed
  #    And I save the Working paper
    And I close the Working paper
    Then Close browser