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

  @FileCheck
  Scenario: File
    Given I open working paper tab
    And I open File Check
    Then I should see the File Check is loaded successfully
    Then I should see the modal if the reported date is not set
    And I close File Check Modal
    And I export file in File Check
    Then File Check - I check file "FC_03SmokeTest Engagement" is downloaded successfully
    And I close file check

  Scenario: Close browser
    Given Close browser