Feature: VSA

  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page
    When I enter user and password
    Then View page after login successful

  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement"
    Then Navigate to global navigation successfully

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


    