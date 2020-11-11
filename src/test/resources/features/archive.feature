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

  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement"
    Then Navigate to global navigation successfully

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
    And I close trial balance tab


   Scenario: Submit Archive
     Given Open the Engagement Setting
     And I set Report Date "27-03-2020"
     And I set Archive Control Date "27-03-2020"
     And I navigate to Portfolio page
     And I submit Archive on Engagement "03SmokeTest Engagement"
     And I process the submit archive flow
     Then I should see the Portfolio page
     Then I check status Engagement "03SmokeTest Engagement" is "Archive integrity check in progress"
     And I wait until engagement "03SmokeTest Engagement" status is changed from "Archive integrity check in progress" to "Archive pending approval" in 10 minutes

   Scenario: Reject Archive
     Given I navigate to Portfolio page
     And I reject Archive on Engagement "03SmokeTest Engagement"
     Then I check status Engagement "03SmokeTest Engagement" is "Archive rejected"
     And I open an archive rejected engagement "03SmokeTest Engagement"
     Then I check status Engagement "03SmokeTest Engagement" is "In Progress"

   Scenario: Approve Archive
     And I submit Archive on Engagement "03SmokeTest Engagement"
     And I process the submit archive flow
     Then I should see the Portfolio page
     Then I check status Engagement "03SmokeTest Engagement" is "Archive integrity check in progress"
     And I wait until engagement "03SmokeTest Engagement" status is changed from "Archive integrity check in progress" to "Archive pending approval" in 10 minutes
     And I approve Archive on Engagement "03SmokeTest Engagement"
     And I process approve archive
     Then I should see the Portfolio page
     Then I check status Engagement "03SmokeTest Engagement" is "Archive in progress"

  Scenario: Close browser
    Given Close browser
