package Pages;

import driver.boot.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SettingsPage {

    public Driver driver;

    public SettingsPage(Driver driver) {
        this.driver = driver;
    }

    public void openSettingsScreen() {
        driver.findElement(By.cssSelector("#user-dd > a > span > div > svg > text")).click();
        driver.findElement(By.cssSelector("#user-dd > ul > li:nth-child(3) > a > strong")).click();
    }

    public void selectLeadsitem() {
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[14]/a")).click();
    }

    public void openLeadStatusesTab() {
        driver.findElement(By.cssSelector("#leads-settings-tabs > li:nth-child(3) > a")).click();
    }

    public void changeStatusName(String oldStatus, String newStatus) {
        //find field with "New" status and click button "edit"
        String fieldText = driver.findElement(By.cssSelector
                ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > label > h4")).getText();

        if (fieldText.equals(oldStatus)) {
            driver.findElement(By.cssSelector
                    ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > div > div > span.main-toolbar > button"))
                    .click();
        }

        //provide new status name
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[5]/div[1]/span[1]/div/div/form/fieldset/div[2]/div/input")));
        textField.clear();
        textField.sendKeys(newStatus);
        driver.findElement(By.cssSelector
                ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > form > fieldset > div:nth-child(3) > div > button"))
                .click();
    }

    public void revertStatusToInitialValue(String newStatus, String oldStatus) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.cssSelector("#user-dd > a > span > div > svg > text")).click();
        driver.findElement(By.cssSelector("#user-dd > ul > li:nth-child(3) > a > strong")).click();
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[14]/a")).click();
        driver.findElement(By.cssSelector("#leads-settings-tabs > li:nth-child(3) > a")).click();

        String fieldText = driver.findElement(By.cssSelector
                ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > label > h4")).getText();

        if (fieldText.equals(newStatus)) {
            driver.findElement(By.cssSelector
                    ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > div > div > span.main-toolbar > button"))
                    .click();
        }

        //revert status name back

        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[2]/div/div[2]/div[5]/div[1]/span[1]/div/div/form/fieldset/div[2]/div/input")));
        textField.clear();
        textField.sendKeys(oldStatus);
        driver.findElement(By.cssSelector
                ("#lead-status > div.named-objects-list.named-objects-lead.ui-sortable > span:nth-child(1) > div > div > form > fieldset > div:nth-child(3) > div > button"))
                .click();
    }
}