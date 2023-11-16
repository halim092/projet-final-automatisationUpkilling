package org.sogeti.formation.projetUskilingAutomatisation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By btnAddEmployee = By.cssSelector("button.oxd-button--secondary:nth-child(1)");



    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToEmployeeCreationPage() {
        WebElement clickBtnAddEmployee = wait.until(ExpectedConditions.elementToBeClickable(btnAddEmployee));
        clickBtnAddEmployee.click();
    }
}
