Feature: Setup Materiality
  Scenario: Login & Set up Materiality
    Given I navigate to Login page
    When I enter user and password
      | nhuyx@deloitte.com | Technology@11:35am |
    Then View page after login successful
    And Open an existing engagement "Automation Engagement"
    Then Navigate to global navigation successfully
    And I open Materiality working paper
    And I set up Materiality working paper
    Then Close browser