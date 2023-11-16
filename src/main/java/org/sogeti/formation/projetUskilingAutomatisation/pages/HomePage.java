package org.sogeti.formation.projetUskilingAutomatisation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath("//span[contains(@class, 'menu-item-text') and text()='PIM']\n");

    public HomePage(WebDriver driver) {
    }

    public void goToPIMScreen() {
        WebElement clickPimMneu = wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        clickPimMneu.click();
    }
}
