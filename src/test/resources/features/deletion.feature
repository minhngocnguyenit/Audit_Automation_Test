Feature: VSA

  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page
    When I enter user and password
    Then View page after login successful

   #Create new engagement
    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | 03SmokeTest Engagement |
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
      | 03SmokeTest Engagement| Step |
    Then I check status Engagement "03SmokeTest Engagement" is "Pending Creation"
    And I wait until engagement "03SmokeTest Engagement" status is changed from "Pending Creation" to "In Progress" in 20 minutes

   Scenario: Submit Deletion
     And I submit Deletion on Engagement "03SmokeTest Engagement"
     Then I check status Engagement "03SmokeTest Engagement" is "Submitted for Deletion"

   Scenario: Reject Deletion
     And I reject Deletion on Engagement "03SmokeTest Engagement"
     Then I check status Engagement "03SmokeTest Engagement" is "In Progress"

   Scenario: Approve Deletion
     And I submit Deletion on Engagement "03SmokeTest Engagement"
     Then I check status Engagement "03SmokeTest Engagement" is "Submitted for Deletion"
     Then I approve Deletion on Engagement "03SmokeTest Engagement"

  Scenario: Close browser
    Given Close browser
