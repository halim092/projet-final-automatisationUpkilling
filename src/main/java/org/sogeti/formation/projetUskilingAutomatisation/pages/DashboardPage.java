package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sogeti.formation.projetUskilingAutomatisation.pages.admin.AdminManagementPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPersonalDetailsPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.time.TimePage;

import java.time.Duration;

@Log4j2
public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimAdmin = By.cssSelector("a[href*='viewAdminModule']");
    private By profileName =By.cssSelector(".oxd-userdropdown-name[data-v-bdd6d943]");
    private By pimMenu = By.cssSelector("a[href*='viewPimModule']");
    private By TimeBtn=By.cssSelector("a[href*='viewTimeModule']");
    private By myInfoMenu=By.cssSelector("a[href*='viewMyDetails']");
    private By camembertLocationEmploye=By.xpath("//p[normalize-space()='Employee Distribution by Location']");
    private  By legendNewYorkSalesOffice = By.cssSelector("span[title='New York Sales Office']");
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

    public String getProfileName(){
        WebElement actualProfilName = wait.until(ExpectedConditions.presenceOfElementLocated(profileName));
        return actualProfilName.getText();
    }
    public AdminManagementPage goToAdminScreen() {
        log.info("Navigating to the ADMIN Screen...");
        WebElement clickAdminMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(pimAdmin));
        clickAdminMenu.click();
        log.info("Navigated to the ADMIN Screen successfully.");
        return new AdminManagementPage(driver);
    }
    public TimePage goToTimePage() {
        log.info("Navigating to the Time Page...");
        WebElement clickBtnTime =wait.until(ExpectedConditions.elementToBeClickable(TimeBtn));
        clickBtnTime.click();
        log.info("Navigated to Time Page sucessfully");

        return new TimePage(driver);
    }
    public PIMEmployeeListPersonalDetailsPage goToMyInfo() {
        log.info("Navigating to the My Info Screen...");
        WebElement clickMyInfoMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoMenu));
        clickMyInfoMenu.click();
        log.info("Navigated to the My Info Screen successfully.");
        return new PIMEmployeeListPersonalDetailsPage(driver);
    }
    public DashboardPage clickOnLegendNewYorkSalesOffice(){
        log.info("We are clicking on legend New York Sales Office  ...");
        WebElement clickOnNewYork = wait.until(ExpectedConditions.elementToBeClickable(legendNewYorkSalesOffice));
        clickOnNewYork.click();
        log.info("We clicked on Legend New York Sales Office successfully  .");
        return this;
    }
    public boolean isLegendStrikethrough() {
        WebElement legendElement = wait.until(ExpectedConditions.visibilityOfElementLocated(legendNewYorkSalesOffice));
        String textDecoration = legendElement.getCssValue("text-decoration");
        return textDecoration.contains("line-through");
    }

}
