package org.sogeti.formation.projetUskilingAutomatisation.pages.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Log4j2
public class TimePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By btnReports = By.cssSelector("li.oxd-topbar-body-nav-tab:nth-child(3)");
    private By btnReportstProjects =By.cssSelector(".oxd-dropdown-menu > li:nth-child(1) > a");
    public TimePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public TimePage clickOnReportsBtn(){
        log.info("We are clicking on  the Reports button ..");
        WebElement clickBtnReports =wait.until(ExpectedConditions.elementToBeClickable(btnReports));
        clickBtnReports.click();
        log.info("Button Reports is clicked  sucessfully");
        return  this;
    }
    public ReportsPage goToReportsProjectsPage(){
        log.info("Navigating to the Reports Projects Page..");
        WebElement clickBtnReports =wait.until(ExpectedConditions.elementToBeClickable(btnReportstProjects));
        clickBtnReports.click();
        log.info("Navigated to he Reports Page sucessfully");
        return new ReportsPage(driver);
    }

}
