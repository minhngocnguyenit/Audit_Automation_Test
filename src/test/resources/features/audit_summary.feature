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
    
  @Audit_Summary
    Scenario: Audit Summary
    Given I open Working Paper "Audit Planning SV"
    Then I should see the "Audit Planning SV" is loaded successfully
    And I close the Working paper


  @Audit_Summary
  Scenario: Audit Summary
    Given I open Working Paper "Audit summary SV"
    Then I should see the "Audit summary SV" is loaded successfully
    And I close the Working paper

  Scenario: Close browser
    Given Close browser