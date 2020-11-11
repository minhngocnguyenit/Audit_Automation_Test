Feature: VSA

  Scenario: Judgmental
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu44 |
    Then View page after login successful
    And Open an existing engagement "11-03-2020_ccccccc"
    Then Navigate to global navigation successfully
    And I open Working Paper "AFDA testing (AR.P07) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I answer "Yes" to Tailoring question "Is audit sampling/selections needed for the testing?"
    Then I check the "Test of details - plan" section will be activated
    Then I check the "Test of details - conclude" section will be activated
    And I open "Test of details - plan" section on Working Paper
    And I answer "Nonrepresentative selection" to Tailoring question "Determine your testing approach"
    And I type 2 to field "Document the number of rows needed for your selections"
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
      | 3        | 25/02/2020 | Test | 1000.54               |
      | 4        | 25/02/2020 | Test | 1000.54               |
      | 5        | 25/02/2020 | Test | 1000.54               |
    And I answer "1" to Tailoring question "How many source documents will be tested?"
    And I enter "Revenue 1" to Source Document "What is the name of source document 1?"
    And I generate Samples/selections for testing
    Then I check the "Test of details - perform" section will be activated
    And I open "Test of details - perform" section on Working Paper
    And I type data to widget Testing Table
      | Were any exceptions noted? | Revenue 1 | Tested | Total unexplained difference |
      | No                         | Test     | True     | 10000.54                     |
      | Yes                         | Test1    | False   | 10000.55                     |

    And I open "Test of details - conclude" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed
    And I save the Working paper
    Then I close the Working paper