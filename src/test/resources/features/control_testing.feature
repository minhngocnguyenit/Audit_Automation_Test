Feature: Login and Create Engagement

  Scenario: Create new Engagement
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu44 |
    Then View page after login successful

  # Open an existing engagement
  Scenario: Open an existing engagement
    Given Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully

  # Control testing
  Scenario: Control Testing
    Given I open Working Paper "Review of purchase order cut-off (AP.C03)"
    And Enable editing Working Paper
    And Control Testing - I open "Overview" section
    Then Control Testing - I check control details data on Overview
      | controlId | controlTitle                     | controlDescription                                                                                                                                                                                                                     |
      | AP.C03    | Review of purchase order cut-off | Appropriate level of management reviews that all purchase orders and services are approved and accounted for in the right period, and, if not, that the unmatched deliveries or services performed are accrued until matched/approved. |
    Then Control Testing - I check control information on Overview
      | nature | frequency | approach | type | oeTestingStrategy | oeDateLastTested |
      | Manual |           |          |      |                   |                  |
    Then Control Testing - I check control conclusion on Overview
      | controlConclusion | implementationConclusion | oeConclusion |
      |                   |                          |              |
    Then Control Testing - T check Risk Associated on Overview
      | riskID | title                                                                                                                                 | assertion                                                              |
      | AP.R02 | Trade accounts payable and other payables of which the entity is obliged to pay are incomplete and/or have been incorrectly recorded. | Valuation and Allocation (B),Rights and Obligations (B),Completeness (B) |
      | AP.R07 | Accrued expenses (other than payroll) that are recorded do not relate to valid transactions and/or are recorded at the incorrect amount. | Existence (B),Valuation and Allocation (B) |
    And Control Testing - I open "Perform" section
    And Control Testing - I open file on Attach Common tool
    And Control - I close Attachment GCT
#    And Control Testing - I download Attach File
    And Control Testing - I open "IUC" section
    Then Control Testing - I answer "Yes" to Tailoring question "Did we use any IUC as audit evidence?"
    And I save the Working paper
    Then I close Control Testing wp

  Scenario: Custom Control Testing
    Given I open Working Paper "Control title (CTRL.CC01)"
    And Enable editing Working Paper
    And Control Testing - I open "Overview" section
    Then Control Testing - I check control details data on Overview
      | controlId | controlTitle | controlDescription |
      | CTRL.CC01 | Control title | Control description |
    Then Control Testing - I check control information on Overview
      | nature | frequency | approach | type | oeTestingStrategy | oeDateLastTested |
      | Automated | Monthly | Preventive | Verifications   | Testing Not Planned | 2019 |
    Then Control Testing - I check control conclusion on Overview
      | controlConclusion | implementationConclusion | oeConclusion |
      |           Effective        |         Implemented                 |       Effective       |
    Then Control Testing - T check Risk Associated on Overview
      | riskID | title | assertion |
      | REV.R01 | Revenue is not classified appropriately. | Classification (T) |
      | REV.R02 | Revenue recorded does not represent actual transactions that occurred. | Occurrence (T)	 |
    And Control Testing - I open "Perform" section
    And Control Testing - I open file on Attach Common tool
    And Control - I close Attachment GCT
#    And Control Testing - I download Attach File
    And Control Testing - I open "IUC" section
    Then Control Testing - I answer "Yes" to Tailoring question "Did we use any IUC as audit evidence?"
    And I save the Working paper
    Then I close Control Testing wp