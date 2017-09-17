package Pages;

import driver.boot.Driver;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;


public class LoginPage {

    public Driver driver;

    public LoginPage(Driver driver) {
        this.driver = driver;
    }


    public void goTo(String URL) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.webDriver.get(URL);
        System.out.println("Navigating to: " + URL);

    }

    public void loginToAccount(String mail, String password) {
        driver.findElement(By.id("user_email")).sendKeys(mail);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.cssSelector(".btn")).click();

    }


}