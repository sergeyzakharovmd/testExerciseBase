package Steps;

import Pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import driver.boot.Driver;

public class LoginSteps {


    public Driver driver;


    public LoginSteps(Driver driver) {
        this.driver = driver;
    }

    @When("^I open login page$")
    public void i_open_login_page() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo("https://core.futuresimple.com/users/login");
    }

    @And("^I log in to account \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void i_log_in_to_account_with_password(String mail, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToAccount(mail, password);
    }
}

