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

        // Spécifiez la durée d'attente comme une instance de Duration
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage enterUsername(String username) {
        if (username.isEmpty()) {
            log.warn("Username is empty");
        } else {
            log.info("Entering Username: [{}]", username);
        }
        WebElement usernameElement = wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
        usernameElement.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        if (password.isEmpty()) log.warn("username is empty");
        WebElement passwordElement = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        passwordElement.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButtonElement.click();
        return this;
    }

    public HomePage login(String username, String password) {
        enterUsername(username).enterPassword(password).clickLogin();
        return new HomePage(driver);
    }
}
