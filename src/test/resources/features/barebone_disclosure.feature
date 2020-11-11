Feature: VSA

  Scenario: Barebone FSL
    Given I navigate to Login page "https://qlevvia.aaps.deloitte.com/"
    When I enter user and password
      | tainguyen@deloitte.com | Technology@20008$$$uuuu45 |
    Then View page after login successful
    And Open an existing engagement "SmokeTest Engagement"
    Then Navigate to global navigation successfully
    And I open Working Paper "Test presentation and disclosure (DIS.P01) - Final"
    And Enable editing Working Paper of Barebone Disclosure
    Then I check amount on Materiality widget of Barebone Disclosure
      | Overall | Materiality | PerformanceMateriality | CTT |
      | Overall | 400000  | 160000      | 20000 |
    Then I check data on Risk widget of Barebone Disclosure
      | riskID | title | assertion | classification |
      | DIS.R01  |  There's a presumed risk of material misstatement due to fraud related to management override of controls. Management is in a unique position to perpetrate fraud because of management’s ability to manipulate accounting records andFinancial statements do not include the required disclosures for material ABCOTD(s); or disclosed amounts are not accurate or classified appropriately; or the disclosed events, transactions, management assertions, and other matters may not have occurred or pertain to the entity. prepare fraudulent financial statements by overriding controls that otherwise appear to be operating effectively. |Presentation and Disclosure (D)|Higher|
    Then I check data on Procedure widget of Barebone Disclosure
      | procedureID | title |
      | DIS.P01    | Test the financial statement presentation and disclosure |
    And I open "Perform" section on Barebone Disclosure Working Paper
    And I answer "Yes" to Tailoring question "Is there any, or should there be any, information related to accounting estimates that gives rise to significant risks in the disclosures?"
#    Then I check the Complex Memo will be displayed in Barebone Disclosure
    And I open "IPE" section on Barebone Disclosure Working Paper
    And I answer "Yes" to Tailoring question "Did we use any IPE as audit evidence?"
    Then I check the Vertical will be displayed in Barebone Disclosure
    And I open "Conclusion" section on Barebone Disclosure Working Paper
    And I answer "Misstatements identified; however, they are clearly trivial or qualitatively insignificant." to Tailoring question "Have any misstatements been identified?"
    Then I check the Complex Text "Document explanation" will be displayed in Barebone Disclosure
    And I save the Barebone Disclosure Working paper
    Then I close the Barebone Disclosure Working paper
    Then Close browser