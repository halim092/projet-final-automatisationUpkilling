package org.sogeti.formation.projetUskilingAutomatisation.pages.admin;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;

import java.time.Duration;
import java.util.List;

@Log4j2
public class AdminManagementPage {

    private WebDriver driver ;
    private WebDriverWait wait;


   private By btnAddAdmin = By.cssSelector("button.oxd-button--secondary:nth-child(1)");
    private By userRoleDropdown = By.xpath("//label[contains(., 'User Role')]//following::div[1]//div[@class='oxd-select-text-input']");
   // private By userRoleDropdownOptionAdmin=By.xpath("//div[@role='listbox']//child::div)[1]");
    private By userRoleDropdownOptionAdmin=By.xpath("    //div[@class='oxd-select-option']//span[text()='Admin']\n");
    private By statusDropdown = By.xpath("//label[text()= 'Status']/ancestor::div[@data-v-957b4417]/div/div");
    private By statusDropdownEnabled = By.xpath("//div[@class='oxd-select-option']//span[text()='Enabled']\n");

    private By inputEmployeeName = By.cssSelector(".oxd-autocomplete-text-input > input");
  // private By selectEmployeeName =By.className("div.oxd-autocomplete-dropdown[role=\"listbox\"]");
    private By selectEmployeeName =By.xpath("//div[@role='listbox']//child::div[1]\n");

   private By InitialUsernameInput = By.xpath("//label[text()='Username']/ancestor::div[@data-v-957b4417]/div/input");
    private By passwordAdminInput = By.xpath("//label[text()='Password']/ancestor::div[@data-v-957b4417]/div/input");
    private By confirmPasswordAdminInput = By.xpath("//label[text()='Confirm Password']/ancestor::div[@data-v-957b4417]/div/input");
    private By btnSaveAdmin = By.cssSelector("button[type=\"submit\"]");
    private By btnLogout = By.cssSelector("a[href*='logout']");
        private By profileBtn = By.cssSelector(".oxd-userdropdown-icon");
    public AdminManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   public AdminManagementPage openAdminRegistrationPage() {
        log.info("Navigating to the Admin Registration Page...");
        WebElement clickBtnAddAdmin = wait.until(ExpectedConditions.visibilityOfElementLocated(btnAddAdmin));
        clickBtnAddAdmin.click();
        log.info("Navigated to the Admin Registration Page successfully.");
        return this;

    }
    public AdminManagementPage selectUserRole(String role) {
        log.info("We are selecting on the user role ...");
        WebElement userRoleDropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(userRoleDropdown));
        userRoleDropdownElement.click();
        WebElement userRoleAdmin = wait.until(ExpectedConditions.presenceOfElementLocated(userRoleDropdownOptionAdmin));
        userRoleAdmin.click();

        log.info("the user role selected is : [{}]", role);
        return this;
    }

    public AdminManagementPage selectStatus(String status) {
        log.info("We are selecting on the  ...");
        WebElement statusDropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(statusDropdown));
        statusDropdownElement.click();
        WebElement userRoleAdmin = wait.until(ExpectedConditions.presenceOfElementLocated(statusDropdownEnabled));
        userRoleAdmin.click();

        log.info("the status selected is : [{}]", status);
        return this;
    }

    public AdminManagementPage enterEmployeeNameInput(String lastName) {
        WebElement webElementEmployeeName = wait.until(ExpectedConditions.presenceOfElementLocated(inputEmployeeName));
        webElementEmployeeName.sendKeys(lastName);
        log.info("Entered employee name: [{}]", lastName);
        webElementEmployeeName.sendKeys(Keys.RETURN);
        return this;

    }
    public AdminManagementPage selectEmployeeName() {

        log.info("Selecting the first EmployeeName ....");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

      //  WebElement clickSelectingEmployeeName = wait.until(ExpectedConditions.presenceOfElementLocated(selectEmployeeName));
        List<WebElement> rowEmployeeName = driver.findElements(selectEmployeeName);

        rowEmployeeName.get(0).click();
        log.info("The EmployeeName is selected .");
        return this;
    }

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
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
