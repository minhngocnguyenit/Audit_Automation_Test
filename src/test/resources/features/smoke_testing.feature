Feature: VSA

  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page
    When I enter user and password
    Then View page after login successful

  # Create new engagement
    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | SmokeTest Engagement |
      | Financial Statement Audit |
      | 2020-01-31                |
      | Canada                    |
      | Amos                      |
      | Step                      |
      | 123456                    |
      | Automotive                |
      | IFRS - Canada                   |
      | CA Tier 4 - Canadian GAAS Very Small Audits |
      | Engagement Owner               |
    Then View page after create successful
      | SmokeTest Engagement| Step |
    Then I check status Engagement "SmokeTest Engagement" is "Pending Creation"

    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | Archive Engagement |
      | Financial Statement Audit |
      | 2020-01-31                |
      | Canada                    |
      | Amos                      |
      | Step1                      |
      | 123456                    |
      | Automotive                |
      | IFRS - Canada                   |
      | CA Tier 4 - Canadian GAAS Very Small Audits |
      | Engagement Owner               |
    Then View page after create successful
      | Archive Engagement| Step1 |
    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | Deletion Engagement |
      | Financial Statement Audit |
      | 2020-01-31                |
      | Canada                    |
      | Amos                      |
      | Step2                      |
      | 123456                    |
      | Automotive                |
      | IFRS - Canada                   |
      | CA Tier 4 - Canadian GAAS Very Small Audits |
      | Engagement Owner               |
    Then View page after create successful
      | Deletion Engagement| Step2 |

  And I wait until engagement "SmokeTest Engagement" status is changed from "Pending Creation" to "In Progress" in 20 minutes
#
#  # Open an existing engagement
  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "SmokeTest Engagement"
    And I confirm independence
    Then Navigate to global navigation successfully

#  # Create Standard and Custom WP in Global Nav
  @Standard_WP
  Scenario: Create standard
    Given I create Standard working paper
      | Risk assessment analytics and visualizations template |
      | Budget analysis                                       |
      | Additional consultation                               |
      | Other information and supplementary information presented with the financial statements |
    Then I check existing wp
      | Risk assessment analytics and visualizations template |
      | Budget analysis                                       |
      | Additional consultation                               |
      | Other information and supplementary information presented with the financial statements |

  @Custom_WP
  Scenario:  Create custom WP
    Given I create Custom working paper "Automation Test"
     #Check existing wp
    Then I check existing wp
      | Automation test |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Engagement risk, client acceptance and continuance, independence, terms of the engagement"
    And Enable editing Working Paper
    And I open "Acceptance and continuance decision" section on Working Paper
    And I answer "Yes" to Tailoring question "Is this an initial audit engagement?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Initial audit - audit of opening balances |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Engagement risk, client acceptance and continuance, independence, terms of the engagement"
    And Enable editing Working Paper
    And I open "Acceptance and continuance decision" section on Working Paper
    And I answer "No" to Tailoring question "Is this an initial audit engagement?"
    And I save the Working paper
    And I close the Working paper
    Then I check non existing wp
      | Initial audit - audit of opening balances |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Assign engagement teams and hold engagement team discussions"
    And Enable editing Working Paper
    And I answer "Yes" to Tailoring question "Will an IT Specialist be involved in the audit engagement?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Using the work of an auditor's specialist |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Assign engagement teams and hold engagement team discussions"
    And Enable editing Working Paper
    And I answer "No" to Tailoring question "Will an IT Specialist be involved in the audit engagement?"
    And I answer "Yes" to Tailoring question "Will there be other specialists involved?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Using the work of an auditor's specialist |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Assign engagement teams and hold engagement team discussions"
    And Enable editing Working Paper
    And I answer "No" to Tailoring question "Will an IT Specialist be involved in the audit engagement?"
    And I answer "No" to Tailoring question "Will there be other specialists involved?"
    And I save the Working paper
    And I close the Working paper
    Then I check non existing wp
      | Using the work of an auditor's specialist |

  @Activation
  Scenario: Activation
    Given I open Working Paper "IT assessment and understanding"
    And Enable editing Working Paper
    And I answer "No" to Tailoring question "Does the engagement team meet certain specified criteria as decided by the member firm Professional Practice Director or "
    Then I check the Tailoring Question "Did the engagement partner have a discussion with an IT Specialist in one of the prior two audits and " will be displayed
    And I answer "No" to Tailoring question "Did the engagement partner have a discussion with an IT Specialist in one of the prior two audits and"
    Then I check the Tailoring Question "Based on the conclusion in Form 1415ISA, will an IT Specialist be involved in the audit?" will be displayed
    And I answer "Yes" to Tailoring question "Based on the conclusion in Form 1415ISA, will an IT Specialist be involved in the audit?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Using the work of an auditor's specialist |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Assign engagement teams and hold engagement team discussions"
    And Enable editing Working Paper
    Then I should see the answer "Yes" is selected on Tailoring question "Will an IT Specialist be involved in the audit engagement?" by another Working Paper "IT assessment and understanding"
    And I save the Working paper
    And I close the Working paper
    Given I open Working Paper "IT assessment and understanding"
    And Enable editing Working Paper
    And I answer "Yes" to Tailoring question "Does the engagement team meet certain specified criteria as decided by the member firm Professional Practice Director or "
    And I save the Working paper
    And I close the Working paper
    Then I check non existing wp
      | Using the work of an auditor's specialist |

  @Activation
  Scenario: Activation
    Given I open Working Paper "Assign engagement teams and hold engagement team discussions"
    And Enable editing Working Paper
    And I answer "No" to Tailoring question "Is the engagement exempt from the EQCR process?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Engagement quality control review |
    Given I open Working Paper "Engagement quality control review"
    And Enable editing Working Paper
    And I answer "No" to Tailoring question "Does the Engagement Quality Control Reviewer (\"EQC Reviewer\") perform the review using a form/template the member firm requires to be used? "
    And I answer "Yes" to Tailoring question "Were there any differences of opinion between the EQC Reviewer and the engagement team (or any other)?"
    And I save the Working paper
    And I close the Working paper
    Then I check existing wp
      | Additional consultation |

   # Generate Control Testing and WP on FSL
  @FSL
  Scenario: Generate Control Testing and WP on FSL Risk Assessment
    Given I open Working Paper "Financial statement level - Identify and assess ROMMs and plan further audit procedures"
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

  # Open Trial balance and update amount file
  @Trial_Balance
  Scenario: Upload TB Mapping and CP Interim data
    Given I open Trial Balance working paper
    Then I should be at Trial Balance working paper successfully
    And I upload a Trial Balance mapping file
            | type | path | PeriodEnd | accessMateriality |
            | 1    | data\trial_balance\MappingFile.xlsx |Now | 1 |
    Then Checking file is uploaded correctly
            | UploadType | FileName | Period | PeriodEnd | DateUploaded | Status |
            | Trial balance mapping | MappingFile.xlsx | N/A | N/A | Now | In-use |
    And I uploaded a CP Interim file
            | type | path | PeriodEnd | accessMateriality |
            | 2    | data\trial_balance\CPInterim.xlsx | Now | 1 |
    Then Checking file is uploaded correctly
      | UploadType | FileName | Period | PeriodEnd | DateUploaded | Status |
      | Trial balance amounts | CPInterim.xlsx | CP Interim| Now | Now | In-use |
    And I close trial balance tab

  #Create custom CA
  @Trial_Balance
  Scenario: create custom CA and map custom ABCOTDs to custom CA
    Given I open Trial Balance working paper
    Then I should be at Trial Balance working paper successfully
    And I create Custom Content Area
      | Name |
      | CustomCA |
    And I select linked custom ABCOTDs to custom CA
      | Future employment obligation |
      | Lease obligation |
      | Revenue on long term contracts |
    And I map custom ABCOTDs to Linked content Areas
      | abcotd | contentAreas |
      | Custom Income or Loss on Investments IS | Cash and cash equivalents |
    And I close Manage custom components
    Then I should be at Trial Balance working paper successfully
    And I close trial balance tab

  #Open Materiality wp and input value on Planing section
  @Materiality
  Scenario: Materiality
    Given I open Working Paper "Materiality"
    And Enable editing Working Paper
    And I set up Materiality working paper
    | Overall | PM | CTT |
    | 400000  | 40 | 20000 |
    And I answer "Yes" to Tailoring question "Do we need to determine materiality for particular classes of transactions, account balances or disclosures?"
    And I set up ABCOTDs Materiality
    | abcotdName | determinedMateriality | determinedPM |
    | Receivables - trade accounts | 20000 | 10000 |
    | Payroll | 20000 | 10000 |
    And I save the Working paper
    And I close Materiality

  #Open PAR wp and set Material
  @PAR
  Scenario: PAR
    Given I open Working Paper "Preliminary analytical procedures and identification of material ABCOTDs"
    And Enable editing Working Paper
    Then Checking overall is shown correctly
      | Overall | PM | CTT |
      | 400000  | 160000 | 20000 |
    And I set up Par&Scoping Working Paper
      | Amount | Percent |
      | 920000  | 20 |
    And I save the Working paper
    And I close PAR
    #Check existing wp
    Then I check existing wp
      | Receivables - trade accounts - Identify and assess ROMMs and plan further audit procedures |
      | Cash and cash equivalents - Identify and assess ROMMs and plan further audit procedures    |
      | Property, plant, and equipment - Identify and assess ROMMs and plan further audit procedures |
      | Payroll - Identify and assess ROMMs and plan further audit procedures                       |
      | Revenue - Identify and assess ROMMs and plan further audit procedures                       |
      | Accounts payable and accruals - Identify and assess ROMMs and plan further audit procedures |
      | CustomCA - Identify and assess ROMMs and plan further audit procedures |

    #Generate wp from PPE
  @Risk_Assessment
  Scenario: Risk Assessment - Property, plant and equipment - Generate DTWP
    Given I open Working Paper "Property, plant, and equipment - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | PPE.TQ01 | Yes |
      | PPE.TQ02  | Yes |
      | PPE.TQ03 | Yes |
      | PPE.TQ04 | Yes |
      | PPE.TQ05   | Yes |
      | PPE.TQ06   | Yes |
      | PPE.TQ07   | Yes |
      | PPE.TQ08   | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    When I open "Controls" section on Risk Assessment
    And I select controls
      | AP.C01 |
      | AP.C03 |
      | PPE.C06 |
    When I open "Generate working papers" section on Risk Assessment
    And I generate Control Testing on RA
      | AP.C01 |
      | AP.C03 |
      | PPE.C06 |
    When I open "Procedures" section on Risk Assessment
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | PPE.R01 | PPE.P01 | 3     |             |
      | PPE.R04 | PPE.P04  | 1      |             |
      | PPE.R06 | PPE.P08.a  | 1      |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Property, plant, and equipment | PPE.P01 | TEST EQUIPMENT |
      | Property, plant, and equipment | PPE.P01 | OFFICE EQUIPMENT |
      | Property, plant, and equipment | PPE.P04 | TEST EQUIPMENT |
      | Property, plant, and equipment | PPE.P04 | DISPLAY BOARD - BOOTH AM	 |
      | Property, plant, and equipment | RPT.P01 | All	 |
      | Gain or loss on property, plant, and equipment | RPT.P01 | All |
      | Property, plant, and equipment | PPE.P08.a | All |
    When I open "Generate working papers" section on Risk Assessment
    Then I generate Working Paper in RA
    Then I close Risk Assessment
    #Check existing wp
    Then I check existing wp
      | Property, plant, and equipment - Leadsheet |
      | PPE Rollforward schedule testing (PPE.P01) - Final |
      | TOD Repairs and maintenance exp (PPE.P04) - Final    |
      | TOD Repairs and maintenance exp (PPE.P04) - Interim    |
      | Purchase three-way match (AP.C01) |
      | Review of purchase order cutoff (AP.C03) |
      | SAP Asset disposals (PPE) (PPE.P08.a) - Interim                                        |

  #Generate WP from Receivables
  @Risk_Assessment
  Scenario: Risk Assessment - Account Receivable - Generate DTWP
    Given I open Working Paper "Receivables - trade accounts - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | AR.TQ01 | Yes |
      | AR.TQ02  | Yes |
      | AR.TQ03 | Yes |
      | AR.TQ04 | Yes |
      | AR.TQ05 | Yes |
      | AR.TQ06 | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    When I open "Procedures" section on Risk Assessment
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | AR.R03 | AR.P02.a | 3     |             |
      | AR.R03 | AR.P02.b  | 1      |             |
      | AR.R04 | AR.P04  | 1      |             |
#      | AR.R01 | AR.P07  | 1      |             |
#      | AR.R01 | AR.P06  | 1      |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Receivables - trade accounts | AR.P02.a | All |
      | Receivables - trade accounts | AR.P02.b | All |
      | Receivables - trade accounts | AR.P04 | A/R TRADE -PRODUCT SALES |
      | Receivables - trade accounts | AR.P04 | A/R TRADE,AUSTRALIA, |
#      | Receivables - trade accounts | AR.P07 | All |
#      | Receivables - trade accounts | AR.P06 | All |
    When I open "Generate working papers" section on Risk Assessment
    When I generate Working Paper in RA
    Then I close Risk Assessment
    # I check list wp exist after I generated WP in RA Account Receivable
    Then I check existing wp
      | Receivables - trade accounts - Leadsheet |
      | TOD Customer confirmations (AR.P02.a) - Final |
      | TOD AR subsequent receipts (AR.P02.b) - Final    |
      | TOD AR subsequent receipts (AR.P02.b) - Interim    |
#      | AFDA testing (AR.P07) - Final    |
#      | AFDA testing (AR.P07) - Interim    |
      | TOD Forex translations (AR) (AR.P04) - Interim |
      | TOD Forex translations (AR) (AR.P04) - Final   |
#      | TOD Reconciliation of AR (AR.P06) - Interim    |

  #Generea Cash Confirmation RA on Cash RA
  @Risk_Assessment
  Scenario: Risk Assessment Cash - Generate DTWP
    Given I open Working Paper "Cash and cash equivalents - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | CAS.TQ01 | Yes |
      | CAS.TQ02  | Yes |
      | CAS.TQ03 | Yes |
      | CAS.TQ04 | Yes |
      | CAS.TQ05   | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    When I open "Procedures" section on Risk Assessment
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | CAS.R01 | CAS.P01.a | 1     |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Cash and cash equivalents | CAS.P01.a | All |
    When I open "Generate working papers" section on Risk Assessment
    When I generate Working Paper in RA
    Then I close Risk Assessment
    Then I check existing wp
      | Cash and cash equivalents - Leadsheet |
      | TOD Bank confirmations (CAS.P01.a) - Final |
      | TOD Bank confirmations (CAS.P01.a) - Interim    |

  #Generete AP Confirmation from AP RA
  @Risk_Assessment
  Scenario: Risk Assessment - Accounts payable and accruals - Generate DTWP
    Given I open Working Paper "Accounts payable and accruals - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | AP.TQ01 | Yes |
      | AP.TQ02  | Yes |
      | AP.TQ03 | Yes |
      | AP.TQ04 | Yes |
      | AP.TQ05   | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    When I open "Procedures" section on Risk Assessment
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | AP.R05 | AP.P02.b | 1     |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Payables - trade accounts | AP.P02.b | A/P TRADE,K-SINGAPORE, |
    When I open "Generate working papers" section on Risk Assessment
    When I generate Working Paper in RA
    Then I close Risk Assessment
    Then I check existing wp
      | Accounts payable and accruals - Leadsheet |
      | TOD AP supplier confirmations (AP.P02.b) - Interim |
      | TOD AP supplier confirmations (AP.P02.b) - Final    |

  @Risk_Assessment
  Scenario: Risk Assessment - Create a custom Control and generate Control Testing
    Given I open Working Paper "Revenue - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | REV.TQ01 | Yes |
      | REV.TQ02 | Yes |
      | REV.TQ03 | Yes |
      | REV.TQ04 | Yes |
      | REV.TQ05 | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    And I open "Controls" section on Risk Assessment
    And I create a custom Control on Risk Assessment with following data
      | Associate With | Associate ABCOTD | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
      | 1              | Revenue | True | Control title | Control description | True   | Monthly | Preventive | Verifications | REV.R01, REV.R02 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
    Then I should see the custom Control on Risk Assessment with folowwing data
      | ID | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
      | CTRL.CC01            | True | Control title | Control description | True   | Monthly | Preventive | Verifications | REV.R01, REV.R02 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
    And I select controls
      | CTRL.CC01 |
    When I open "Generate working papers" section on Risk Assessment
    And I generate Control Testing on RA
      | CTRL.CC01 |
    And I save the Working paper
    And I close Risk Assessment
    Then I check existing wp
      | Control title (CTRL.CC01) |

  @Risk_Assessment
  Scenario: Risk Assessment - Create a custom ROMM and generate custom DTWP
    Given I open Working Paper "Revenue - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | REV.TQ01 | Yes |
      | REV.TQ02 | Yes |
      | REV.TQ03 | Yes |
      | REV.TQ04 | Yes |
      | REV.TQ05 | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    And I open "ROMMs" section on Risk Assessment
    And I create a custom ROMMs on Risk Assessment with following data
      | type | ABCOTD | Title | Description | Assertions | Classification | Classification  Rationale |
      | 1    | Revenue | Revenue Custom ROMM | Revenue Custom ROMM Description | Valuation and Allocation (B), Completeness (B) | Lower | Test |
    Then I should see the custom ROMM on ABCOTDs "Revenue" via following information
      | ID | Title | Assertion(s) | Risk Classification |
      | REV.CR01 | Revenue Custom ROMM | Valuation and Allocation (B), Completeness (B) | Lower |
    And I open "Procedures" section on Risk Assessment
    And I create a custom Procedure
      | Title | Description | Task |
      | Custom Procedure Title | Custom Procedure Description | Task |
    And I mark associate ROMM "REV.CR01" with list standard procedures
      | REV.P02.b |
      | REV.P02.c |
    And I mark associate ROMM "REV.CR01" with list custom procedures
      | REV.CP1 |
    Then I should see list associate procedure with ROMM "REV.CR01"
      | REV.P02.b |
      | REV.P02.c |
      | REV.CP1 |
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | REV.CR01 | REV.CP1 | 1     |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Revenue | REV.CP1 | All |
    When I open "Generate working papers" section on Risk Assessment
    When I generate Working Paper in RA
    And I save the Working paper
    And I close Risk Assessment
    Then I check existing wp
      | Revenue - Leadsheet |
      | Custom Procedure Title (REV.CP1) - Final |
      | Custom Procedure Title (REV.CP1) - Interim |

  @Custom @Risk_Assessment
  Scenario: Custom Risk Assessment
    Given I open Working Paper "CustomCA - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    And I open "ROMMs" section on Risk Assessment
    Then I should see list ABCOTD Material as following
      | Future employment obligation |
      | Lease obligation |
      | Revenue on long term contracts |
    And I create a custom ROMMs on Risk Assessment with following data
      | type | ABCOTD | Title | Description | Assertions | Classification | Classification  Rationale |
      | 1    | Future employment obligation | Custom ROMM | Custom ROMM Description | Valuation and Allocation (B), Completeness (B) | Higher | Test |
    Then I should see the custom ROMM on ABCOTDs "Future employment obligation" via following information
      | ID | Title | Assertion(s) | Risk Classification |
      | FEO.CR01 | Custom ROMM | Valuation and Allocation (B), Completeness (B) | Higher |
    And I open "Controls" section on Risk Assessment
    And I create a custom Control on Risk Assessment with following data
      | Associate With | Associate ABCOTD | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
      | 1              | Future employment obligation | True | Control title | Control description | True   | Monthly | Preventive | Verifications | FEO.CR01 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
    Then I should see the custom Control on Risk Assessment with folowwing data
      | ID | Relevant Control | Control Title | Control Description | Automated | Frequency | Approach | Type | Associated ROMMs | Design Conclusion | Implementation Conclusion | OE Testing Strategy | OE Date Last Tested | OE Conclusion |
      | CTRL.CC02            | True | Control title | Control description | True   | Monthly | Preventive | Verifications | FEO.CR01 | Effective | Implemented              | Testing Not Planned | 2019                | Effective     |
    And I select controls
      | CTRL.CC02 |
    When I open "Generate working papers" section on Risk Assessment
    And I generate Control Testing on RA
      | CTRL.CC02 |
    And I open "Procedures" section on Risk Assessment
    And I create a custom Procedure
      | Title | Description | Task |
      | Custom Procedure Title | Custom Procedure Description | Task |
    And I mark associate ROMM "FEO.CR01" with list custom procedures
      | CA_28.CP1 |
    Then I should see list associate procedure with ROMM "FEO.CR01"
      | CA_28.CP1 |
    When I select procedure to generate Working Paper
      | romm | procedure | timing | rollforward |
      | FEO.CR01 | CA_28.CP1 | 1     |             |
    When I open "Procedure account mapping" section on Risk Assessment
    When I select account mapping
      | abcotd | procedure | account |
      | Future employment obligation | CA_28.CP1 | All |
    When I open "Generate working papers" section on Risk Assessment
    When I generate Working Paper in RA
    And I save the Working paper
    And I close Risk Assessment

    Then I check existing Subphase
      | Substantive testing - CA_28 |

    Then I check existing wp
      | Control title (CTRL.CC02) |
      | CA_28 - Leadsheet         |
      | Custom Procedure Title (CA_28.CP1) - Final |
      | Custom Procedure Title (CA_28.CP1) - Interim |

  # Open Trial balance and update amount file
  @Trial_Balance
  Scenario: TB Mapping - Upload CP Final data
    Given I open Trial Balance working paper
    Then I should be at Trial Balance working paper successfully
    And I uploaded a CP Final file
      | type | path | PeriodEnd | accessMateriality |
      | 3    | data\trial_balance\CPFinal.xlsx | Now | 1 |
    Then Checking file is uploaded correctly
      | UploadType | FileName | Period | PeriodEnd | DateUploaded | Status |
      | Trial balance amounts | CPFinal.xlsx | CP Final| Now | Now | In-use |
    And I close trial balance tab

  #Open Disclosures
  @Disclosure
  Scenario: Risk Assessment - Disclosures
    Given I open Working Paper "Disclosures - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all Disclosures tailoring question
      | id | answered |
      | DIS.TQ01 | Yes |
      | DIS.TQ02 | Yes |
    When I open "Controls" section on Disclosures
    And I select Disclosures controls
      | DIS.C01 |
      | DIS.C02 |
    When I open "Generate working papers" section on Disclosures
    And I generate Control Testing on Disclosures
      | DIS.C01 |
      | DIS.C02 |
    When I open "Procedures" section on Disclosures
    When I select procedure to generate Disclosures Working Paper
      | romm | procedure | timing | rollforward |
      | DIS.R01 | DIS.P01 | 3     |             |
      | DIS.R02 | DIS.P02 | 3     |             |
    When I open "Generate working papers" section on Disclosures
    Then I generate Working Paper in Disclosures
    Then I close Disclosures
    #Check existing wp
    Then I check existing wp
      | Review of the Financial Statements (DIS.C01) |
      | Review of the Cash Flow Statement (DIS.C02) |
      | Test presentation and disclosure (DIS.P01) - Final |
      | Test cash flow statement (DIS.P02) - Final |

  # Control testing
  @Control_Testing
  Scenario: Control Testing
    Given I open Working Paper "Review of purchase order cutoff (AP.C03)"
    And Enable editing Working Paper
    And Control Testing - I open "Overview" section
    Then Control Testing - I check control details data on Overview
      | controlId | controlTitle                     | controlDescription                                                                                                                                                                                                                     |
      | AP.C03    | Review of purchase order cut-off | An appropriate level of management reviews that all purchase orders and services are approved and accounted for in the right period, and, if not, that the unmatched deliveries or services performed are accrued until matched/approved. |
    Then Control Testing - I check control information on Overview
      | nature | frequency | approach | type | oeTestingStrategy | oeDateLastTested |
      | Manual |           |          |      |                   |                  |
    Then Control Testing - I check control conclusion on Overview
      | controlConclusion | implementationConclusion | oeConclusion |
      |                   |                          |              |
    Then Control Testing - T check Risk Associated on Overview
      | riskID | title                                                                                                                                 | assertion                                                              |
      | AP.R02 | Trade accounts payable and other payables are incomplete, and/or have been incorrectly recorded. | Valuation and Allocation (B),Rights and Obligations (B),Completeness (B) |
      | AP.R07 | Accrued expenses (other than payroll) that are recorded do not relate to valid transactions and/or are recorded at the incorrect amount, and/or incorrectly valued because the selected method, data, and/or assumptions that form the estimate have been inappropriately determined or applied, and/or the entity does not have obligations for the recorded amount. | Existence (B),Valuation and Allocation (B) |
    And Control Testing - I open "Perform" section
    And Control Testing - I open file on Attach Common tool
    And Control - I close Attachment GCT
#    And Control Testing - I download Attach File
    And Control Testing - I open "IUC" section
    Then Control Testing - I answer "Yes" to Tailoring question "Did we use any IUC as audit evidence?"
    And I save the Working paper
    Then I close Control Testing wp

  @Custom @Control_Testing
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
      |  Effective        |         Implemented      | Effective    |
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

  #Open LeadSheet
  @Leadsheet
  Scenario: Leadsheet
    Given I open Working Paper "Receivables - trade accounts - Leadsheet"
    Then I check overall in LeadSheet
      | Overall | PM | CTT |
      | 400000  | 160000 | 20000 |
    And I open PAR from LeadSheet
    Then I navigate to PAR from LeadSheet successfully
    And I close PAR working paper tab
    And I open RA from LeadSheet
    Then I navigate to RA from LeadSheet successfully
    And I close RA working paper tab
    Then I close LeadSheet

  @Custom @Leadsheet
  Scenario: Custom Leadsheet
    Given I open Working Paper "CA_28 - Leadsheet"
    Then I check overall in LeadSheet
      | Overall | PM | CTT |
      | 400000  | 160000 | 20000 |
    Then I should see list ABCOTD Material on Leadsheet as following
      | Future employment obligation |
      | Lease obligation |
      | Revenue on long term contracts |
    And I open PAR from LeadSheet
    Then I navigate to PAR from LeadSheet successfully
    And I close PAR working paper tab
    And I open RA from LeadSheet
    Then I navigate to RA from LeadSheet successfully
    And I close RA working paper tab
    Then I close LeadSheet

  #For BB Disclosure
  @Disclosure_Barebone
  Scenario: Barebone Disclosure
    Given I open Working Paper "Test presentation and disclosure (DIS.P01) - Final"
    And Enable editing Working Paper of Barebone Disclosure
    Then I check amount on Materiality widget of Barebone Disclosure
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
    Then I check data on Risk widget of Barebone Disclosure
      | riskID | title | assertion | classification |
      | DIS.R01  |  Financial statements do not include the required disclosures for material ABCOTD(s); or disclosed amounts are not accurate or classified appropriately; or the disclosed events, transactions, management assertions, and other matters may not have occurred or pertain to the entity. |Presentation and Disclosure (D)|Higher|
    Then I check data on Procedure widget of Barebone Disclosure
      | procedureID | title |
      | DIS.P01    | Test the financial statement presentation and disclosure |
    And I open "Perform" section on Barebone Disclosure Working Paper
#    And I answer "Yes" to Tailoring question "Is there any, or should there be any, information related to accounting estimates that gives rise to significant risks in the disclosures?"
#    Then I check the Complex Memo will be displayed in Barebone Disclosure
    And I open "Information used in the audit" section on Barebone Disclosure Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?"
    Then I check the Vertical will be displayed in Barebone Disclosure
    And I open "Conclusion" section on Barebone Disclosure Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed in Barebone Disclosure
    And I save the Barebone Disclosure Working paper
    Then I close the Barebone Disclosure Working paper

  #For BB FSL
  @FSL_Barebone
  Scenario: Barebone FSL
    Given I open Working Paper "Journal entries testing (FSL.P01) - Final"
    And Enable editing Working Paper of Barebone FSL
    Then I check amount on Materiality widget of Barebone FSL
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
    Then I check data on Risk widget of Barebone FSL
      | riskID   |  title |classification |
      | FSL.R01  |  There's a presumed risk of material misstatement due to fraud related to the management override of controls. Management is in a unique position to perpetrate fraud because of management’s ability to manipulate accounting records and prepare fraudulent financial statements by overriding controls that otherwise appear to be operating effectively. |Significant          |
    Then I check data on Procedure widget of Barebone FSL
      | procedureID | title |
      | FSL.P01    | Test the journal entries recorded in the general ledger and other adjustments made in the preparation of the financial statements. |
    And I open "Perform" section on Barebone FSL Working Paper
    And I answer "Use a text field to document a narrative." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Complex Memo will be displayed in Barebone FSL
    And I answer "Use a spreadsheet to document a numeric analysis." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Freeform Table will be displayed in Barebone FSL
    And I open "Information used in the audit" section on Barebone FSL Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?"
    Then I check the Vertical will be displayed in Barebone FSL
    And I open "Conclusion" section on Barebone FSL Working Paper
#    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed in Barebone FSL
    And I save the Barebone FSL Working paper
    Then I close the Barebone FSL Working paper

  #Barebone account DTWP
  @DTWP @Barebone_Account
  Scenario: Barebone Account
    Given I open Working Paper "TOD Forex translations (AR) (AR.P04) - Final"
    And Enable editing Working Paper
    Then I check amount on Materiality widget
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
      | Receivables - trade accounts | 20000 | 10000 |                      |
    Then I check data on Risk widget
      | riskID | title | assertion | classification |
      | AR.R04  | Trade accounts receivables denominated in foreign currency have been incorrectly translated.     |     	Valuation and Allocation (B)      | Lower          |
    Then I check data on Procedure widget
      | procedureID | title |
      | AR.P04    | Test translation of foreign currency within trade accounts receivable |
    And I open "Perform" section on Working Paper
    And I answer "Use a text field to document a narrative." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Complex Memo will be displayed
    And I answer "Use a spreadsheet to document a numeric analysis." to Tailoring question "Select one or more of the following options to document your work, and/or attach a file using the attachment common tool:"
    Then I check the Freeform Table will be displayed
    And I open "Information used in the audit" section on Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?"
    Then I check the Vertical will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

  #Barebone account DTWP with custom Procedure
  @DTWP @Barebone_Account @Custom
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
    And I open "Information used in the audit" section on Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?"
    Then I check the Vertical will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

#  #Reconciliation DTWP
#  @DTWP @DTWP_Reconcialition
#  Scenario: Reconciliation - DTWP
#    Given I open Working Paper "TOD Reconciliation of AR (AR.P06) - Interim"
#    And Enable editing Working Paper
#    And I open "Reconciliation" section on Working Paper
#    Then I check the Agree Summary will be displayed
#    Then I check the widget table "Entity schedules" will be displayed
#    Then I check the widget table "Reconciled difference" will be displayed
#    And I add 2 rows to widget table "Entity schedules"
#    And I type some data to widget table "Entity schedules"
#      | Record | Description | Amount |
#      | 1      | Test        | 1000.54 |
#    And I add 2 rows to widget table "Reconciled difference"
#    And I type some data to widget table "Reconciled difference"
#      | Record | Description | Amount |
#      | 1      | Test        | 1000.64 |
#    Then I verify data on Agree Summary card
#    And I save the Working paper
#    Then I close the Working paper

  #For TOD DTWP
  @DTWP @Test_Of_Details
  Scenario: Test of details - DTWP
    Given I open Working Paper "TOD Repairs and maintenance exp (PPE.P04) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I answer "Yes" to Tailoring question "Does the population being tested differ from the sum of all the account totals listed in the Overview section?"
    Then I check the Tailoring Question "Is the population reconciled to a record other than the trial balance (e.g., additions listing from the fixed asset register)?" will be displayed
    Then I check the Agree Summary will be displayed
    Then I check the widget table "Entity schedules" will be displayed
    Then I check the widget table "Reconciled difference" will be displayed
    And I add 2 rows to widget table "Entity schedules"
    And I type some data to widget table "Entity schedules"
      | Record | Description | Amount |
      | 1      | Test        | 1000.54 |
    And I add 2 rows to widget table "Reconciled difference"
    And I type some data to widget table "Reconciled difference"
      | Record | Description | Amount |
      | 1      | Test        | 1000.64 |
    Then I verify data on Agree Summary card
    And I answer "Yes" to Tailoring question "Do we need to determine a specific period for our population (e.g., for cutoff testing)?"
    Then I check the Complex Text "Document how the period for the population was determined" will be displayed
    And I answer "Test of all items in the population" to Tailoring question "Determine your testing approach."
    And I type 2 to field "Document the number of rows needed for your selections."
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
    And I answer "1" to Tailoring question "How many source documents will be tested?"
    And I enter "Revenue" to Source Document "What is the name of source document 1?"
    And I generate Samples/selections for testing
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    And I type data to widget Testing Table
      | Were any exceptions noted? | Revenue | Tested | Total unexplained difference |
      | No                         | Test    | True      | 10000.54                     |
      | Yes                         | Test    | False      | 10000.54                     |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

    #For Judgmental
#  @DTWP @Judgemental
#  Scenario: Judgemental - DTWP
#    Given I open Working Paper "AFDA testing (AR.P07) - Interim"
#    And Enable editing Working Paper
#    And I open "Plan" section on Working Paper
#    And I answer "Yes" to Tailoring question "Is audit sampling/selections needed for the testing?"
#    Then I check the "Test of details - plan" section will be activated
#    Then I check the "Test of details - conclude" section will be activated
#    And I open "Test of details - plan" section on Working Paper
#    And I answer "Nonrepresentative selection" to Tailoring question "Determine your testing approach"
#    And I type 2 to field "Document the number of rows needed for your selections"
#    And I update sample/selections table
#    And I type some data to widget table Samples/selections
#      | UniqueID | Date | Text | Amount per sample/selection |
#      | 1        | 25/02/2020 | Test | 1000.54               |
#      | 2        | 25/02/2020 | Test | 1000.54               |
#    And I answer "3" to Tailoring question "How many source documents will be tested?"
#    And I enter "Revenue 1" to Source Document "What is the name of source document 1?"
#    And I enter "Revenue 2" to Source Document "What is the name of source document 2?"
#    And I enter "Revenue 3" to Source Document "What is the name of source document 3?"
#    And I generate Samples/selections for testing
#    Then I check the "Test of details - perform" section will be activated
#    And I open "Test of details - perform" section on Working Paper
#    And I type data to widget Testing Table
#      | Were any exceptions noted? | Revenue 1 | Tested | Revenue 2 | Tested | Revenue 3 | Tested | Total unexplained difference |
#      | No                         | Test     | True     | Test    | True      | Test    | True    | 10000.54                     |
#    And I open "Test of details - conclude" section on Working Paper
#    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
#    Then I check the Agree Summary will be displayed
#    And I open "Conclusion" section on Working Paper
#    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed
#    And I save the Working paper
#    Then I close the Working paper

    #For subsequent receipt working paper
  @DTWP @Confirmation @Subsequent_Receipt
  Scenario: Subsequent Receipt - Confirmation - DTWP
    Given I open Working Paper "TOD AR subsequent receipts (AR.P02.b) - Interim"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I answer "Yes" to Tailoring question "Does the population being tested differ from the sum of all the account totals listed in the Overview section?"
    Then I check the Tailoring Question "Is the population reconciled to a record other than the trial balance (e.g., additions listing from the fixed asset register)?" will be displayed
    Then I check the Agree Summary will be displayed
    Then I check the widget table "Entity schedules" will be displayed
    Then I check the widget table "Reconciled difference" will be displayed
    And I add 2 rows to widget table "Entity schedules"
    And I type some data to widget table "Entity schedules"
      | Record | Description | Amount |
      | 1      | Test        | 1000.54 |
    And I add 2 rows to widget table "Reconciled difference"
    And I type some data to widget table "Reconciled difference"
      | Record | Description | Amount |
      | 1      | Test        | 1000.64 |
    Then I verify data on Agree Summary card
    And I answer "Yes" to Tailoring question "Are there adjustments to the population?"
    And I type some data to widget table "Adjustments to population"
      | Description of adjustment | Amount |
      | Test                      | 20000  |
    And I answer "Test of all items in the population" to Tailoring question "Determine your testing approach"
    And I type 2 to field "Document the number of rows needed for your selections"
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
    And I generate Samples/selections for testing
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    And I type some data to Subsequent Receipt Perform table
      | Agreed to documentation of payment? | payment date | Payment amount | FX | Amount | ID Number | Service/delivery date | Correct period? | Explanation | Were any exceptions noted?|
      | True                                | 25/02/2020   | 1000.43        | 1  | 20000.43 | 1       | 26/02/2009            | True            | test        | Yes                       |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

    #Cash Confirmation
  @DTWP @Confirmation @Cash
  Scenario: Cash Confirmation - DTWP
    Given I open Working Paper "TOD Bank confirmations (CAS.P01.a) - Interim"
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
    And I open "Plan" section on Working Paper
    And I answer "No" to Tailoring question "Do any of the bank accounts include foreign currency?"
    And I generate Samples/selections for testing on Cash
    Then I check the "Perform" section will be activated
    And I open "Perform" section on Working Paper
    Then I should see the Cash Perform No Foreign Currency table
    And I open "Information used in the audit" section on Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?"
    Then I check the Vertical will be displayed
    And I open "Conclusion" section on Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    And I close the Working paper

    #For AP Confirmation
  @DTWP @Confirmation @Account_Payable
  Scenario: AP Confirmation - DTWP
    Given I open Working Paper "TOD AP supplier confirmations (AP.P02.b) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I answer "Test of all items in the population" to Tailoring question "Determine your testing approach"
    And I type 2 to field "Document the number of rows needed for your selections"
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
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
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

  #For AR Confirmation
  @DTWP @Confirmation @Account_Receivable
  Scenario: AR Confirmation - DTWP
    Given I open Working Paper "TOD Customer confirmations (AR.P02.a) - Final"
    And Enable editing Working Paper
    And I open "Plan" section on Working Paper
    And I answer "Test of all items in the population" to Tailoring question "Determine your testing approach"
    And I type 2 to field "Document the number of rows needed for your selections"
    And I update sample/selections table
    And I type some data to widget table Samples/selections
      | UniqueID | Date | Text | Amount per sample/selection |
      | 1        | 25/02/2020 | Test | 1000.54               |
      | 2        | 25/02/2020 | Test | 1000.54               |
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
      | 1           | -999.56                | Yes                        |
    And I open "Conclusion" section on Working Paper
    And I answer "Yes" to Tailoring question "Are there any misstatements that need to be extrapolated?"
    Then I check the Agree Summary will be displayed
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
#    Then I check the Complex Text "Document the explanation." will be displayed
    And I save the Working paper
    Then I close the Working paper

  @DTWP @SAP
  Scenario: SAP - DTWP
    Given I open Working Paper "SAP Asset disposals (PPE) (PPE.P08.a) - Interim"
    And Enable editing Working Paper
    Then I check amount on Materiality widget
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000      | 160000                 | 20000 |
    Then I check data on Risk widget
      | riskID | title | assertion | classification |
      | PPE.R06  | The sale, disposal, or theft of property, plant, and equipment has not been recorded; the gain/loss has not been completely, and/or accurately recorded.     |  	Existence (B)   | Lower          |
    Then I check data on Procedure widget
      | procedureID | title |
      | PPE.P08.a   | Perform substantive analytical procedures on PPE disposals |
    And I open "Plan" section on Working Paper
    And I answer "Yes" to Tailoring question "Does the population being tested differ from the sum of all the account totals listed in the Overview section?"
    Then I check the widget table "Entity schedules" will be displayed in SAP
    Then I check the widget table "Reconciled difference" will be displayed in SAP
    And I add 2 rows to widget table "Entity schedules" in SAP
    And I type some data to widget table "Entity schedules" in SAP
      | Record | Description | Amount |
      | 3      | Test        | 1000.54 |
    And I add 2 rows to widget table "Reconciled difference" in SAP
    And I type some data to widget table "Reconciled difference" in SAP
      | Record | Description | Amount |
      | 1      | Test        | 1000.64 |
    And I answer "Relying on controls" to Tailoring question "Control reliance approach" in SAP

    And I generate 4 rows in SAP table
    And I open "Perform SAP" section on Working Paper
    And I open row 1 of testing sheet
    And I input value to Testing sheet in SAP
      | Title | Amount | Expectation | ExplainedDifference |
      | Row1  | 120000 | 75000 | 12345 |
    And I save the Testing Sheet in SAP
    And I close the Testing Sheet in Sap

    And I open "Information used in the audit" section on SAP Working Paper
    And I answer "Yes" to Tailoring question "Did we use any internal and/or external information in the audit?" in SAP
    Then I check the Vertical will be displayed in SAP
    And I open "Conclusion" section on SAP Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial and qualitatively insignificant." to Tailoring question "Have any misstatements been identified?" in SAP
    Then I check the Complex Text "Document the explanation." will be displayed in SAP
    And I save the SAP Working paper
    Then I close the SAP Working paper

  #CAR
  @CAR
  Scenario: Concluding analytical procedures
    Given I open Working Paper "Concluding analytical procedures"
    And Enable editing Working Paper
    Then Checking CAR overall is shown correctly
      | Overall | PM | CTT |
      | 400000  | 160000 | 20000 |
    And I set up CAR Working Paper
      | Amount | Percent |
      | 920000  | 20 |
    And I save the Working paper
    And I close CAR

  @Audit_Summary
  Scenario: Audit Summary
    Given I open Working Paper "Audit planning summary view"
    Then I should see the "Audit planning summary view" is loaded successfully
    And I close the Working paper

  @Audit_Summary
  Scenario: Audit Summary
    Given I open Working Paper "Audit summary view"
    Then I should see the "Audit summary view" is loaded successfully
    And I close the Working paper

  @FileCheck
  Scenario: File Check
    Given I open working paper tab
    And I open File Check
    Then I should see the File Check is loaded successfully
    Then I should see the modal if the reported date is not set
    And I close File Check Modal
    And I export file in File Check
    Then File Check - I check file "FC_SmokeTest Engagement" is downloaded successfully
    And I close file check

  #Risk Summary
  @Risk_Summary
  Scenario: Risk Summary
    And I open ROMM Summary
    And I set filter in "RoMMs" table
      | type | Column | Select | Value |
      | 1  | Associated ABCOTD | Is | Accrued expenses |
    And I set filter in "Controls" table
      | type | Column | Select | Value |
      | 2  | Associated ABCOTD | Is |Property, plant, and equipment |
    And I export file in "RoMMs" table
      | type |
      | 1  |
    And I export "Filter" file in all table
      | type |
      | 1    |
    And I export "UnFilter" file in all table
      | type |
      | 2    |
    Then ROMM Summary - I check file "ROMM_Summary" is downloaded successfully

  @Archive
  Scenario: Submit Archive
    Given I navigate to Portfolio page
    And Open an existing engagement "Archive Engagement"
    And I confirm independence
    Then Navigate to global navigation successfully
    And Open the Engagement Setting
    And I set Report Date "27-03-2020"
    And I set Archive Control Date "27-03-2020"
    And I navigate to Portfolio page
    And I submit Archive on Engagement "Archive Engagement"
    And I process the submit archive flow
    Then I should see the Portfolio page
#    Then I check status Engagement "Archive Engagement" is "Archive integrity check in progress"
    And I wait until engagement "Archive Engagement" status is changed from "Archive integrity check in progress" to "Archive pending approval" in 10 minutes

  @Archive
  Scenario: Reject Archive
    Given I navigate to Portfolio page
    And I reject Archive on Engagement "Archive Engagement"
    Then I check status Engagement "Archive Engagement" is "Archive rejected"
    And I open an archive rejected engagement "Archive Engagement"
    Then I check status Engagement "Archive Engagement" is "In Progress"

  @Archive
  Scenario: Approve Archive
    And I submit Archive on Engagement "Archive Engagement"
    And I process the submit archive flow
    Then I should see the Portfolio page
#    Then I check status Engagement "Archive Engagement" is "Archive integrity check in progress"
    And I wait until engagement "Archive Engagement" status is changed from "Archive integrity check in progress" to "Archive pending approval" in 10 minutes
    And I approve Archive on Engagement "Archive Engagement"
    And I process approve archive
    Then I should see the Portfolio page
    Then I check status Engagement "Archive Engagement" is "Archive in progress"

  @Deletion
  Scenario: Submit Deletion
    And I submit Deletion on Engagement "Deletion Engagement"
    Then I check status Engagement "Deletion Engagement" is "Submitted for Deletion"

  @Deletion
  Scenario: Reject Deletion
    And I reject Deletion on Engagement "Deletion Engagement"
    Then I check status Engagement "Deletion Engagement" is "In Progress"

  @Deletion
  Scenario: Approve Deletion
    And I submit Deletion on Engagement "Deletion Engagement"
    Then I check status Engagement "Deletion Engagement" is "Submitted for Deletion"
    Then I approve Deletion on Engagement "Deletion Engagement"

  Scenario: Close browser
    Given Close browser