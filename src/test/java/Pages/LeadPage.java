package Pages;

import driver.boot.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class LeadPage {

    public Driver driver;

    public LeadPage(Driver driver) {
        this.driver = driver;
    }


    public void openLeadsTab() {
        driver.findElement(By.id("nav-leads")).click();
    }

    public void openAddNewLeadForm() {
        driver.findElement((By.id("leads-new"))).click();
    }

    public void addNewLead(String surname) {
        driver.findElement((By.id("lead-last-name"))).sendKeys(surname);
        driver.findElement(By.cssSelector(".save")).click();
    }

    public void verifyStatus(String status) {
        String leadStatus = driver.findElement(By.cssSelector("#details > div > ul > div.status > div > a > span.lead-status")).getText();
        Assert.assertTrue(leadStatus.equals(status));
    }

    public void verifyNewStatusOfTheLead(String status, String surname) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
       WebElement listOfLeads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div/div[2]/div[2]/section[1]/div/div/div/ul/li")));
        List<WebElement> el = (ArrayList<WebElement>) driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div/div[2]/div[2]/section[1]/div/div/div/ul/li"));

        for (int i = 0; i < el.size(); i++) {
            String leadName1 = el.get(i).getText();

            if (leadName1.equals(surname)) {
                WebElement leadLine = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        (By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div/div[2]/div[2]/section[1]/div/div/div/ul/li[" +
                                (i + 1) + "]/div/div[1]/div/div[1]/a[1]"))));
                leadLine.click();
                break;
            }
        }

        WebElement leadStatusView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("lead-status")));
        String newLeadStatus = leadStatusView.getText();

        Assert.assertTrue(newLeadStatus.equals(status));
    }

    public void removeAllLeads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-leads")).click();
        driver.findElement(By.cssSelector("#title-bar-container > div > div > div > div.row-fluid.title-container > div.span10 > div > div > a > input[type='checkbox']")).click();
        driver.findElement(By.cssSelector("#leads-more-actions > span > a")).click();
        driver.findElement(By.cssSelector("#leads-delete")).click();
        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div:nth-child(20) > div > div.modal-footer > a.btn.btn-primary.btn-danger.confirm")));
        removeButton.click();
    }
}