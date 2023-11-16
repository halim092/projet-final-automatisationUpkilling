package org.sogeti.formation.projetUskilingAutomatisation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By usernameInput = By.cssSelector("input[name='username']");
    private By passwordInput = By.cssSelector("input[name='password']");
    private By loginButton = By.xpath("//button[contains(@class, 'oxd-button--main')]"); // XPath corrigé
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Spécifiez la durée d'attente comme une instance de Duration
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
        usernameElement.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        passwordElement.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButtonElement.click();
    }

    public void login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLogin();
    }
}
