Feature: Login and Create Engagement

  Scenario: Create new Engagement
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu44 |
    Then View page after login successful

  # Open an existing engagement
#  Scenario: Open an existing engagement
#    Given Open an existing engagement "SmokeTest Engagement"
#    Then Navigate to global navigation successfully

#  Scenario: Custom Risk Assessment
#    Given I open Working Paper "CustomCA - Identify and assess ROMMs and plan further audit procedures"
#    And Enable editing Working Paper
#    And I open "ROMMs" section on Risk Assessment
#    Then I should see list ABCOTD Material as following
#      | Future employment obligation |
#      | Lease obligation |
#      | Revenue on long term contracts |
#    And I create a custom ROMMs on Risk Assessment with following data
#      | type | ABCOTD | Title | Description | Assertions | Classification | Classification  Rationale |
#      | 1    | Future employment obligation | Custom ROMM | Custom ROMM Description | Valuation and Allocation (B), Completeness (B) | Higher | Test |
#    Then I should see the custom ROMM on ABCOTDs "Future employment obligation" via following information
#      | ID | Title | Assertion(s) | Risk Classification |
#      | FEO.CR01 | Custom ROMM | Valuation and Allocation (B), Completeness (B) | Higher |
#    And I open "Controls" section on Risk Assessment
#    And I create a custom Control on Risk Assessment with following data
#      | Associate With | Associate ABCOTD | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
#      | 1              | Future employment obligation | True | Control title | Control description | True   | Monthly | Preventive | Verifications | FEO.CR01 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
#    Then I should see the custom Control on Risk Assessment with folowwing data
#      | ID | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
#      | CTRL.CC02            | True | Control title | Control description | True   | Monthly | Preventive | Verifications | FEO.CR01 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
#    And I select controls
#      | CTRL.CC02 |
#    When I open "Generate working papers" section on Risk Assessment
#    And I generate Control Testing on RA
#      | CTRL.CC02 |
#    And I open "Procedures" section on Risk Assessment
#    And I create a custom Procedure
#      | Title | Description | Task |
#      | Custom Procedure Title | Custom Procedure Description | Task |
#    And I mark associate ROMM "FEO.CR01" with list custom procedures
#      | CA_28.CP1 |
#    Then I should see list associate procedure with ROMM "FEO.CR01"
#      | CA_28.CP1 |
#    When I select procedure to generate Working Paper
#      | romm | procedure | timing | rollforward |
#      | FEO.CR01 | CA_28.CP1 | 1     |             |
#    When I open "Procedure account mapping" section on Risk Assessment
#    When I select account mapping
#      | abcotd | procedure | account |
#      | Future employment obligation | CA_28.CP1 | All |
#    When I open "Generate working papers" section on Risk Assessment
#    When I generate Working Paper in RA
#    And I save the Working paper
#    And I close Risk Assessment

#    Then I check existing Subphase
#      | Substantive testing - CA_28 |
#
#    Then I check existing wp
#      | Control title (CTRL.CC02) |
#      | CA_28 - Leadsheet         |
#      | Custom Procedure Title (CA_28.CP1) - Final |
#      | Custom Procedure Title (CA_28.CP1) - Interim |
#
#  Scenario: Custom Risk Assessment
#    Given I open Working Paper "Custom Procedure Title (REV.CP1) - Interim"
#    And Enable editing Working Paper
#
#  Scenario: Custom Risk Assessment
#    Given I open Working Paper "CustomCA - Identify and assess ROMMs and plan further audit procedures"
#    And Enable editing Working Paper

  Scenario: Close browser
    Given Close browser