package Steps;

import Pages.LeadPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import driver.boot.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LeadSteps {


    public Driver driver;


    public LeadSteps(Driver driver) {
        this.driver = driver;
    }

    @And("^I open Leads tab$")
    public void i_open_leads_tab() {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.openLeadsTab();
    }

    @And("^I click Add New Lead button$")
    public void i_click_add_new_lead_button() {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.openAddNewLeadForm();
    }

    @And("^I add new lead \"([^\"]*)\"$")
    public void i_add_new_lead(String surname) {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.addNewLead(surname);
    }

    @And("^I see \"([^\"]*)\" status$")
    public void i_see_status(String status) {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.verifyStatus(status);
    }

    @Then("^I see new status \"([^\"]*)\" of the lead \"([^\"]*)\"$")
    public void iSeeNewStatusOfTheLead(String status, String surname) {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.verifyNewStatusOfTheLead(status, surname);
    }

    @And("^I remove all leads$")
    public void i_remove_all_leads() {
        LeadPage leadPage = new LeadPage(driver);
        leadPage.removeAllLeads();
    }
}

