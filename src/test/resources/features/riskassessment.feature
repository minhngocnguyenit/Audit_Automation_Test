Feature: Login and Create Engagement
  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page "https://d3levvia.aaps.deloitte.com/"
    When I enter user and password
    Then View page after login successful

  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement"
    Then Navigate to global navigation successfully

    #Generate WP from Receivables
  @Risk_Assessment
  Scenario: Risk Assessment - Account Receivable - Generate DTWP
    Given I open Working Paper "Inventory - Identify and assess ROMMs and plan further audit procedures"
    And Enable editing Working Paper
    When I answer all tailoring question
      | id | answered |
      | INVT.TQ01| Yes |
      | INVT.TQ02  | Yes |
      | INVT.TQ03 | Yes |
    Then I check the "ROMMs" section will be activated
    Then I check the "Controls" section will be activated
    Then I check the "Procedures" section will be activated
    When I open "Procedures" section on Risk Assessment