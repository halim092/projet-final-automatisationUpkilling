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
public class AdminManagementPage {

    private WebDriver driver ;
    private WebDriverWait wait;


   /* private By btnAddAdmin = By.cssSelector("button.oxd-button--secondary:nth-child(1)");
    private By userRoleDropdownAdmin = By.cssSelector(".oxd-select-text-input[tabindex='0']");

    private By statusDropdownEnabled = By.cssSelector("oxd-select-text-input[tabindex=\"0\"]");
    private By inputEmployeeName = By.cssSelector(".oxd-autocomplete-text-input > input"); */
    private By InitialUsernameInput = By.cssSelector("div.oxd-form-row:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input");
    private By passwordAdminInput = By.cssSelector(".user-password-cell > div:nth-child(1) > div:nth-child(2) > input");
    private By confirmPasswordAdminInput = By.cssSelector("div.oxd-form-row:nth-child(5) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input");
    private By btnSaveAdmin = By.cssSelector("button[type=\"submit\"]");
    private By btnLogout = By.cssSelector("a[href*='logout']");
        private By profileBtn = By.cssSelector(".oxd-userdropdown-icon");
    public AdminManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   /* public AdminManagementPage openAdminRegistrationPage() {
        log.info("Navigating to the Admin Registration Page...");
        WebElement clickBtnAddAdmin = wait.until(ExpectedConditions.visibilityOfElementLocated(btnAddAdmin));
        clickBtnAddAdmin.click();
        log.info("Navigated to the Admin Registration Page successfully.");
        return this;

    } */
    /*public AdminManagementPage selectUserRole(String role) {
        WebElement userRoleDropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(userRoleDropdownAdmin));
        Select selectUserRole = new Select(userRoleDropdownElement);
        selectUserRole.selectByVisibleText(role);
        selectUserRole.selectByIndex(0);
        log.info("Selected user role: [{}]", role);
        return this;
    }

    public AdminManagementPage selectStatus(String status) {
        WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(statusDropdownEnabled));
        Select selectStatus = new Select(statusElement);
        selectStatus.selectByVisibleText(status);
        selectStatus.selectByIndex(0);

        log.info("Selected status: [{}]", status);
        return this;
    }

    public AdminManagementPage enterEmployeeNameInput(String lastName) {
        WebElement webElementEmployeeName = wait.until(ExpectedConditions.presenceOfElementLocated(inputEmployeeName));
        webElementEmployeeName.sendKeys(lastName);
        log.info("Entered employee name: [{}]", lastName);
        return this;
    } */

    public AdminManagementPage enterInitialUsername(String initialUsername) {
        log.info("Entering initial Username: [{}]", initialUsername);
        WebElement InitialUsernameElement = wait.until(ExpectedConditions.presenceOfElementLocated(InitialUsernameInput));
        InitialUsernameElement.sendKeys(initialUsername);
        return this;
    }

    public AdminManagementPage enterPasswordAdmin(String PasswordAdmin) {
        log.info("Entering Password Admin: [{}]", PasswordAdmin);
        WebElement PasswordAdminElement = wait.until(ExpectedConditions.presenceOfElementLocated(passwordAdminInput));
        PasswordAdminElement.sendKeys(PasswordAdmin);
        return this;
    }

    public AdminManagementPage enterConfirmPasswordAdmin(String PasswordAdmin) {
        log.info("Entering Confirm Password Admin: [{}]", PasswordAdmin);
        WebElement ConfirmPasswordAdminElement = wait.until(ExpectedConditions.presenceOfElementLocated(confirmPasswordAdminInput));
        ConfirmPasswordAdminElement.sendKeys(PasswordAdmin);
        return this;
    }

    public AdminManagementPage clickSaveAdmin() {
        log.info("Clicking Save button...");
        WebElement btnSaveAdminElement = wait.until(ExpectedConditions.presenceOfElementLocated(btnSaveAdmin));
        btnSaveAdminElement.click();
        log.info("Save button clicked successfully.");
        return this;
    }

    public AdminManagementPage clickProfil(){
        WebElement btnProfileBtn = wait.until(ExpectedConditions.presenceOfElementLocated(profileBtn));

       // wait.until(ExpectedConditions.visibilityOf(profileBtn));
        log.info("clicking on profil buttom...");
        btnProfileBtn.click();
        log.info("Clicked profile button.");
        return this;

    }
    public LoginPage toLogoutLogin() {

        WebElement btnLogoutElement = wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogout));
        log.info("clicking on Logout buttom...");
        btnLogoutElement.click();
        log.info("Clicked Logout button.");
        return new LoginPage(driver);
    }
}
