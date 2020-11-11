Feature: Login and Create Engagement
  Scenario: Login & Create Engagement
    Given I navigate to Login page "https://d3levvia.aaps.deloitte.com/"
    When I enter user and password
      | toannguyen1@deloitte.com | Technology@20008$$$uuuu44 |
    Then View page after login successful
    And Open an existing engagement "20-03-2020_b"
    Then Navigate to global navigation successfully
    #Open LeadSheet

#  Scenario: Leadsheet
#    Given I open Working Paper "Receivables - trade accounts - Leadsheet"
#    Then I check overall in LeadSheet
#      | Overall | PM | CTT |
#      | 920000 | 368000 | 7500000 |
#    And I open PAR from LeadSheet
#    Then I navigate to PAR from LeadSheet successfully
#    And I close PAR working paper tab
#    And I open RA from LeadSheet
#    Then I navigate to RA from LeadSheet successfully
#    And I close RA working paper tab

  @Custom, @Leadsheet
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


    Then Close browser