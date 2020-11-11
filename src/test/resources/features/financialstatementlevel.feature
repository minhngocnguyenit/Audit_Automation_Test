Feature: Login and Create Engagement
  Scenario: Login & Create Engagement
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu33 |
    Then View page after login successful
    And Open an existing engagement "tttt"
    Then Navigate to global navigation successfully
    #Open FSL
    # Generate Control Testing and WP on FSL
    And I open Working Paper "Financial statement level - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all FSL tailoring question
      | id | answered |
      | FSL.TQ01 | Yes |
      | FSL.TQ02 | Yes |
      | FSL.TQ03 | Yes |
    When I open "Controls" section on FSL
    And I select FSL controls
      | FSL.C01 |
      | FSL.C02 |
      | FSL.C03 |
      | FSL.C04 |
    When I open "Generate working papers" section on FSL
    And I generate Control Testing on FSL
      | FSL.C01 |
      | FSL.C02 |
      | FSL.C03 |
      | FSL.C04 |
    When I open "Procedures" section on FSL
    When I select procedure to generate FSL Working Paper
      | romm | procedure | timing | rollforward |
      | FSL.R01 | FSL.P01 | 3     |             |
    When I open "Generate working papers" section on FSL
    Then I generate Working Paper in FSL
    Then I close Financial Statement level

    #Check existing wp
    Then I check existing wp
      | Manual journal entry approval (FSL.C01) |
      | Internal financial report analysis and approval (FSL.C02) |
      | Review of journal entry listing (FSL.C03) |
      | Journal entries testing (FSL.P01) - Final |

    #Open Disclosures
#    And I open Working Paper "Disclosures - Identify and assess ROMMs and plan further audit procedures"
#    When I answer all Disclosures tailoring question
#      | id | answered |
#      | DIS.TQ01 | Yes |
#      | DIS.TQ02 | Yes |
#    When I open "Controls" section
#    And I select Disclosures controls
#      | DIS.C01 |
#      | DIS.C02 |
#    When I open "Generate working papers" section
#    And I generate Control Testing on Disclosures
#      | DIS.C01 |
#      | DIS.C02 |
#    When I open "Procedures" section
#    When I select procedure to generate Disclosures Working Paper
#      | romm | procedure | timing | rollforward |
#      | DIS.R01 | DIS.P01 | 3     |             |
#      | DIS.R02 | DIS.P02 | 3     |             |
#    When I open "Generate working papers" section
#    Then I generate Working Paper in Disclosures
#    Then I close Disclosures
#
#    #Check existing wp
#    Then I check existing wp
#      | Review of the Financial Statements (DIS.C01) |
#      | Review of the Cash Flow Statement (DIS.C02) |
#      | Test presentation and disclosure (DIS.P01) - Final |
#      | Test cash flow statement (DIS.P02) - Final |
    Then Close browser