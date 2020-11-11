Feature: VSA

  Scenario: SAP
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful

#        # Create new engagement
#    And I create new engagement
#      | Canada |
#      | English |
#      | SmokeTest Engagement |
#      | Financial Statement Audit |
#      | 2020-01-31                |
#      | Canada                    |
#      | Amos                      |
#      | Step                      |
#      | 123456                    |
#      | Automotive                |
#      | IFRS - Canada                   |
#      | CA Tier 4 - Canadian GAAS Very Small Audits |
#      | Engagement Owner               |
#    Then View page after create successful
#      | SmokeTest Engagement| Step |
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully

    And I open Working Paper "SAP Asset disposals (PPE.P08.a) - Interim"
    And Enable editing Working Paper
    Then I check amount on Materiality widget
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000      | 160000                 | 20000 |
    Then I check data on Risk widget
      | riskID | title | assertion | classification |
      | PPE.R06  | The sale, disposal or theft of property, plant, and equipment has not been recorded, the gain/loss has not been completely and/or accurately recorded.     |  	Existence (B)   | Lower          |
    Then I check data on Procedure widget
      | procedureID | title |
      | PPE.P08.a   | Perform substantive analytical procedures on PPE disposals |
    And I open "Plan" section on Working Paper
    And I answer "Yes" to Tailoring question "Are you testing the reconciling items in this working paper? (If there are no reconciling items or if the reconciling items are tested elsewhere answer no)"
    Then I check the widget table "Schedules from entity" will be displayed in SAP
    Then I check the widget table "Reconciled difference" will be displayed in SAP
    And I add 2 rows to widget table "Schedules from entity" in SAP
    And I type some data to widget table "Schedules from entity" in SAP
      | Record | Description | Amount |
      | 1      | Test        | 1000.54 |
      | 2      | Test        | 1000.54 |
      | 3      | Test        | 1000.54 |
    And I add 2 rows to widget table "Reconciled difference" in SAP
    And I type some data to widget table "Reconciled difference" in SAP
      | Record | Description | Amount |
      | 1      | Test        | 1000.64 |
      | 2      | Test        | 1000.64 |
      | 3      | Test        | 1000.64 |
    And I answer "Relying on controls" to Tailoring question "Control reliance approach" in SAP

    And I generate 4 rows in SAP table
    And I open "Perform SAP" section on Working Paper
    And I open row 1 of testing sheet
    And I input value to Testing sheet in SAP
           | Title | Amount | Expectation | ExplainedDifference |
           | Row1  | 120000 | 75000 | 12345 |
    And I save the Testing Sheet in SAP
    And I close the Testing Sheet in Sap

    And I open "IPE" section on SAP Working Paper
    And I answer "Yes" to Tailoring question "Did we use any IPE as audit evidence?" in SAP
    Then I check the Vertical will be displayed in SAP
    And I open "Conclusion" section on SAP Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?" in SAP
    Then I check the Complex Text "Document explanation" will be displayed in SAP
    And I save the SAP Working paper
    Then I close the SAP Working paper
    Then Close browser
