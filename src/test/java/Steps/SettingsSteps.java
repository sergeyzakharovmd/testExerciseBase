package Steps;

import Pages.SettingsPage;
import cucumber.api.java.en.And;
import driver.boot.Driver;

public class SettingsSteps {


    public Driver driver;


    public SettingsSteps(Driver driver) {
        this.driver = driver;
    }


    @And("^I navigate to settings$")
    public void i_navigate_to_settings() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.openSettingsScreen();
    }

    @And("^I open item Leads$")
    public void i_open_item_leads() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.selectLeadsitem();
    }

    @And("^I open Lead statuses$")
    public void i_open_lead_statuses() {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.openLeadStatusesTab();
    }

    @And("^I change old status name \"([^\"]*)\" to new \"([^\"]*)\"$")
    public void i_change_status_name_to(String oldStatus, String newStatus) {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.changeStatusName(oldStatus, newStatus);
    }

    @And("^I revert new status \"([^\"]*)\" back to old \"([^\"]*)\"$")
    public void i_revert_status_back_to(String newStatus, String oldStatus) {
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.revertStatusToInitialValue(newStatus, oldStatus);
    }
}

