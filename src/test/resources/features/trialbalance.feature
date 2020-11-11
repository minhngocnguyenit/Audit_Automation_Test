Feature: Trial balance

    Scenario: Upload Trial balance mapping
        Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
        When I enter user and password
            | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
        Then View page after login successful
  # Open an existing engagement
    Scenario: Open an existing engagement
        Given Open an existing engagement "03SmokeTest Engagement"
        Then Navigate to global navigation successfully

  # Open Trial balance and create custom CA, map custom ABCOTDs to custom CA
    Scenario: create custom CA and map custom ABCOTDs to custom CA
        Given I open Trial Balance working paper
        Then I should be at Trial Balance working paper successfully
#        And I create Custom Content Area
#            | Name |
#            | CustomCA |
#        And I select linked custom ABCOTDs to custom CA
#            | Future employment obligation |
#            | Lease obligation |
#            | Revenue on long term contracts |
#        And I map custom ABCOTDs to Linked content Areas
#            | abcotd | contentAreas |
#            | 50 | Cash and cash equivalents |
#
#        And I close Manage custom components
#        Then I should be at Trial Balance working paper successfully

        And I select some accounts in Account balance view
            | HSBC - Operating CAD |
            | HSBC - USD |
        And I add journal entry with "AJE" type
        And I input amount into journal entry
            | Amount | Text |
            | 1200 | SmokeTest |
        # User select Post or Save as proposed (Post = 2 / SaveAsProposed = 1)
        And I "Post" journal entry
            | type |
            | 2 |
#        And I close trial balance tab
#        Then Close browser


