Feature: VSA

  Scenario: Barebone Account
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu44 |
    Then View page after login successful
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully

  Scenario: Barebone Account with custom procedure
    Given I open Working Paper "Custom Procedure Title (REV.CP1) - Interim"
    And Enable editing Working Paper
    Then I check amount on Materiality widget
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
    Then I check data on Risk widget
      | riskID | title | assertion | classification |
      | REV.CR01  | Revenue Custom ROMM     |     		Valuation and Allocation (B), Completeness (B)      | Lower          |
    Then I check data on Procedure widget
      | procedureID | title |
      | REV.CP1    | Custom Procedure Description |
    And I open "Perform" section on Working Paper
    And I answer "Use a text field to document a narrative." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Complex Memo will be displayed
    And I answer "Use a spreadsheet to document a numeric analysis." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Freeform Table will be displayed
    And I open "IPE" section on Working Paper
    And I answer "Yes" to Tailoring question "Did we use any IPE as audit evidence?"
    Then I check the Vertical will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper