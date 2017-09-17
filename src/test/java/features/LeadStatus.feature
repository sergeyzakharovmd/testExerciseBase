@LeadStatus
Feature: Verification of opportunity to change status for newly added lead

  Scenario: Login to base
    When I open login page
    And I log in to account "zamuel7@uapproves.com" with password "13test13"
    And I open Leads tab
    And I click Add New Lead button
    And I add new lead "Testlead"
    And I see "New" status
    And I navigate to settings
    And I open item Leads
    And I open Lead statuses
    And I change old status name "New" to new "New Status"
    And I open Leads tab
    Then I see new status "New Status" of the lead "Testlead"
    And I remove all leads
    And I revert new status "New Status" back to old "New"



