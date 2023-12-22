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
public class PIMEmployeeListPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By btnAddEmployee = By.cssSelector("button.oxd-button--secondary:nth-child(1)");
    //private By btnAddEmployee = By.cssSelector("button.oxd-button--secondary:first-child");
    private By adminMenu = By.cssSelector("li.oxd-main-menu-item-wrapper:nth-child(1) > a:nth-child(1) > span");

    private By personalDetails = By.cssSelector(".--active");


    public PIMEmployeeListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public PIMAddEmployeePage goToAddingEmployeePage() {
        log.info("Navigating to the Employee Creation Page.");
        WebElement clickBtnAddEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(btnAddEmployee));
        clickBtnAddEmployee.click();
        log.info("Navigated to the Employee Creation Page successfully.");
        return new PIMAddEmployeePage(driver);
    }

    public PIMEmployeeListPersonalDetailsPage goToPersonalDetails(){
        log.info("Navigating to the Personal Details Page.");
        WebElement ClickMyInfoTab = wait.until(ExpectedConditions.elementToBeClickable(personalDetails));
        ClickMyInfoTab.click();
        log.info("Navigated to the Personal Details Page successfully.");
        return new PIMEmployeeListPersonalDetailsPage(driver);
    }
}
