package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@Log4j2
public class LoginPage {
    private WebDriver driver;
    private By usernameInput = By.cssSelector("input[name='username']");
    private By passwordInput = By.cssSelector("input[name='password']");
    private By loginButton = By.xpath("//button[contains(@class, 'oxd-button--main')]"); // XPath corrigé
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;


        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage enterUsername(String username) {
        if (username.isEmpty()) {
            log.warn("Username is empty");
        } else {
            log.info("Entering Username: [{}]", username);
        }
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameElement.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        if (password.isEmpty()) {
            log.warn("Password is empty");
        } else {
            log.info("Entering password");
        }
        //WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'menu-item-text') and text()='PIM']")));

        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordElement.sendKeys(password);
        log.info("{} password entered", password);

        return this;
    }

    public LoginPage clickLogin() {
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButtonElement.click();
        return this;
    }

    public DashboardPage login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLogin();
        return new DashboardPage(driver);
    }

    public boolean isLogoutButtonDisplayed() {
        try {
        WebElement logoutButton = driver.findElement(By.id("logoutButton")); // Remplacez l'ID par l'identifiant réel de votre bouton de déconnexion
        return logoutButton.isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
        return false;
    }
    }

    public boolean isEmployeeCreatedSuccessfully() {
        try {
            WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class, 'success') and contains(text(), 'Employee created successfully')]"));
            return successMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }
}
