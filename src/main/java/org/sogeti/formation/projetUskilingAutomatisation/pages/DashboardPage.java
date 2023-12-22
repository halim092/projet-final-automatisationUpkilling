package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.cssSelector("li.oxd-main-menu-item-wrapper:nth-child(2) > a:nth-child(1) > span");
    private By TimeBtn=By.cssSelector("a[href*='viewTimeModule']");
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public PIMEmployeeListPage goToPIMScreen() {
        log.info("Navigating to the PIM Screen...");
        WebElement clickPimMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(pimMenu));
        clickPimMenu.click();
        log.info("Navigated to the PIM Screen successfully.");
        return new PIMEmployeeListPage(driver);
    }
    public TimePage goToTimePage() {
        log.info("Navigating to the Time Page...");
        WebElement clickBtnTime =wait.until(ExpectedConditions.elementToBeClickable(TimeBtn));
        clickBtnTime.click();
        log.info("Navigated to Time Page sucessfully");

        return new TimePage(driver);
    }
}
