package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class PIMAddEmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.cssSelector(".orangehrm-firstname");
    private By middleNameInput = By.cssSelector(".orangehrm-middlename");
    private By lastNameInput = By.cssSelector(".orangehrm-lastname");
    private By saveButton = By.cssSelector(".oxd-button--secondary");
    private By employeeIdInput = By.cssSelector(".oxd-grid-2 > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input");
    private By personalDetails = By.cssSelector(".--active");
    private By switchCreateLoginDetails = By.className("oxd-switch-input");
    private By  profileElement= By.cssSelector("h6.--strong");

    public PIMAddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public PIMAddEmployeePage enterFirstName(String firstName) {
        log.info("Entering First Name: [{}]", firstName);
        WebElement firstNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(firstNameInput));
        firstNameElement.sendKeys(firstName);
        log.info("First Name entered successfully.");
        return this;
    }

    public PIMAddEmployeePage enterMiddleName(String middleName) {
        log.info("Entering Middle Name: [{}]", middleName);
        WebElement middleNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(middleNameInput));
        middleNameElement.sendKeys(middleName);
        log.info("Middle Name entered successfully.");
        return this;
    }

    public PIMAddEmployeePage enterLastName(String lastName) {
        log.info("Entering Last Name: [{}]", lastName);
        WebElement lastNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(lastNameInput));
        lastNameElement.sendKeys(lastName);
        log.info("Last Name entered successfully.");
        return this;
    }

    public PIMAddEmployeePage enterEmployeeID(String employeeID) {
        log.info("Entering EmployeeID: [{}]", employeeID);
        WebElement employeeIDElement = wait.until(ExpectedConditions.presenceOfElementLocated(employeeIdInput));
        employeeIDElement.sendKeys(employeeID);
        log.info("EmployeeID entered successfully.");
        return this;
    }

    public PIMAddEmployeePage clickSave() {
        log.info("Clicking Save button...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        button.click();
        log.info("Save button clicked successfully.");
        return new PIMAddEmployeePage(driver);
    }

    public PIMAddEmployeePage createEmployee(String firstName, String middleName, String lastName, String employeeID) {
        log.info("Creating Employee - First Name: [{}], Middle Name: [{}], Last Name: [{}], Employee ID : [{}]", firstName, middleName, lastName, employeeID);
        enterFirstName(firstName).enterMiddleName(middleName).enterLastName(lastName).enterEmployeeID(employeeID).clickSave();
        log.info("Employee created successfully.");
        return this;
    }

    public PIMEmployeeListPersonalDetailsPage goToPersonalDetails() {
        log.info("Navigating to the Personal Details Page.");
        WebElement clickMyInfoTab = wait.until(ExpectedConditions.elementToBeClickable(personalDetails));
        clickMyInfoTab.click();
        log.info("Navigated to the Personal Details Page successfully.");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PIMEmployeeListPersonalDetailsPage(driver);
    }

    public AdminManagementPage goToCreateLoginDetails() {
        try {
            log.info("Waiting for the switch to be active...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-switch-input--active")));

            log.info("Waiting for the form loader to be invisible...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

            log.info("Waiting for the Create Login Details button to be clickable...");
            WebElement createLoginDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(switchCreateLoginDetails));

            log.info("Clicking on the Create Login Details button...");
            createLoginDetailsButton.click();

            log.info("Clicked on the Create Login Details button successfully.");
        } catch (TimeoutException e) {
            log.error("Timeout waiting for element visibility or clickability: " + e.getMessage());

        }

        return new AdminManagementPage(driver);
    }

    public String getProfileText() {
        log.info("Getting profile text...");
        WebElement profileWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(profileElement));
        String profileText = profileWebElement.getText().trim();
        log.info("Profile text: " + profileText);
        return profileText;
    }


}
