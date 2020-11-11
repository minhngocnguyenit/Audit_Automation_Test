Feature: VSA

  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page
    When I enter user and password
    Then View page after login successful
      # Create new engagement
    And I create new engagement
      | Venezuela |
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
    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | Deletion Engagement |
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
      | Deletion Engagement| Step1 |
    And I create new engagement
      | Singapore |
      | Canada |
      | English |
      | Archive Engagement |
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
      | Archive Engagement| Step2 |
    And I wait until engagement "03SmokeTest Engagement" status is changed from "Pending Creation" to "In Progress" in 20 minutes

      # Open an existing engagement
  @Portfolio
  Scenario: Open an existing engagement
      Given Open an existing engagement "03SmokeTest Engagement"
      And I confirm independence
      Then Navigate to global navigation successfully

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
