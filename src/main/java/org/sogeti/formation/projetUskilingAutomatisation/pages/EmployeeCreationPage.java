package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class EmployeeCreationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.cssSelector("oxd-input oxd-input--active orangehrm-firstname");
    private By middleNameInput = By.cssSelector("oxd-input oxd-input--active orangehrm-middlename");
    private By lastNameInput = By.cssSelector("oxd-input oxd-input--active orangehrm-lastname");
    private By saveButton = By.cssSelector("oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space");

    public EmployeeCreationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public EmployeeCreationPage enterFirstName(String firstName) {
        WebElement firstNameElement = waitForElement(firstNameInput);
        firstNameElement.sendKeys(firstName);
        return this;
    }

    public EmployeeCreationPage enterMiddleName(String middleName) {
        WebElement middleNameElement = waitForElement(middleNameInput);
        middleNameElement.sendKeys(middleName);
        return this;
    }

    public EmployeeCreationPage enterLastName(String lastName) {
        WebElement lastNameElement = waitForElement(lastNameInput);
        lastNameElement.sendKeys(lastName);
        return this;
    }

    public void clickSave() {
        WebElement saveButtonElement = waitForElement(saveButton);
        waitForClickable(saveButton);
        saveButtonElement.click();
    }

    public void createEmployee(String firstName, String middleName, String lastName) {
        enterFirstName(firstName).enterMiddleName(middleName).enterLastName(lastName).clickSave();
    }
}
