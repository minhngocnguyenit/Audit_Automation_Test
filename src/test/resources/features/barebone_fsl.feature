Feature: VSA

  Scenario: Barebone FSL
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully
    And I open Working Paper "Journal entries testing (FSL.P01) - Final"
    And Enable editing Working Paper of Barebone FSL
    Then I check amount on Materiality widget of Barebone FSL
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
    Then I check data on Risk widget of Barebone FSL
      | riskID   |  title |classification |
      | FSL.R01  |  There's a presumed risk of material misstatement due to fraud related to management override of controls. Management is in a unique position to perpetrate fraud because of management’s ability to manipulate accounting records and prepare fraudulent financial statements by overriding controls that otherwise appear to be operating effectively. |Significant          |
    Then I check data on Procedure widget of Barebone FSL
      | procedureID | title |
      | FSL.P01    | Test the journal entries recorded in the general ledger and other adjustments made in the preparation of the financial statements. |
    And I open "Perform" section on Barebone FSL Working Paper
    And I answer "Use a text field to document a narrative." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Complex Memo will be displayed in Barebone FSL
    And I answer "Use a spreadsheet to document a numeric analysis." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Freeform Table will be displayed in Barebone FSL
    And I open "IPE" section on Barebone FSL Working Paper
    And I answer "Yes" to Tailoring question "Did we use any IPE as audit evidence?"
    Then I check the Vertical will be displayed in Barebone FSL
    And I open "Conclusion" section on Barebone FSL Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed in Barebone FSL
    And I save the Barebone FSL Working paper
    Then I close the Barebone FSL Working paper
    Then Close browser