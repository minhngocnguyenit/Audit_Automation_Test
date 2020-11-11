Feature: VSA

  Scenario: Create new Engagement
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
    | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful

      # Create new engagement
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