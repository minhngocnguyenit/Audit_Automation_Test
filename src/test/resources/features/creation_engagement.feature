Feature: VSA

  @Engagement_Creation
  Scenario: Create new Engagement
    Given I navigate to Login page
    When I enter user and password
    Then View page after login successful

  # Create new engagement
#    And I create new engagement
#      | Singapore |
#      | Canada |
#      | English |
#      | 03SmokeTest Engagement11 |
#      | Financial Statement Audit |
#      | 2020-01-31                |
#      | Canada                    |
#      | Amos                      |
#      | Step                      |
#      | 123456                    |
#      | Automotive                |
#      | IFRS - Canada                   |
#      | CA Tier 4 - Canadian GAAS Very Small Audits |
#      | Engagement Owner               |
#    Then View page after create successful
#      | 03SmokeTest Engagement11| Step |
#    Then I check status Engagement "03SmokeTest Engagement11" is "Pending Creation"

#    And I create new engagement
#      | Singapore |
#      | Canada |
#      | English |
#      | Archive Engagement |
#      | Financial Statement Audit |
#      | 2020-01-31                |
#      | Canada                    |
#      | Amos                      |
#      | Step1                      |
#      | 123456                    |
#      | Automotive                |
#      | IFRS - Canada                   |
#      | CA Tier 4 - Canadian GAAS Very Small Audits |
#      | Engagement Owner               |
#    Then View page after create successful
#      | Archive Engagement| Step1 |
#    And I create new engagement
#      | Singapore |
#      | Canada |
#      | English |
#      | Deletion Engagement |
#      | Financial Statement Audit |
#      | 2020-01-31                |
#      | Canada                    |
#      | Amos                      |
#      | Step2                      |
#      | 123456                    |
#      | Automotive                |
#      | IFRS - Canada                   |
#      | CA Tier 4 - Canadian GAAS Very Small Audits |
#      | Engagement Owner               |
#    Then View page after create successful
#      | Deletion Engagement| Step2 |

#    And I wait until engagement "03SmokeTest Engagement11" status is changed from "Pending Creation" to "In Progress" in 20 minutes

  @Portfolio
  Scenario: Open an existing engagement
    Given Open an existing engagement "03SmokeTest Engagement11"
#    And I confirm independence
    Then Navigate to global navigation successfully

  Scenario: Close browser
    Given Close browser