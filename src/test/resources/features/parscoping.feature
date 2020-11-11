Feature: Setup Par&Scoping
  Scenario: Login & Set up Par&Scoping
    Given I navigate to Login page "https://dlevvia.aaps.deloitte.com/"
    When I enter user and password
      | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful
    # Open an existing engagement
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully

    And I open Working Paper "Materiality"
    And Enable editing Working Paper
    And I save the Working paper
    And I close Materiality

    And I open Working Paper "Preliminary analytical procedures and identification of material ABCOTDs"
    And Enable editing Working Paper
    And I save the Working paper
    And I close PAR
    Then Close browser