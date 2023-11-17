package org.sogeti.formation.projetUskilingAutomatisation.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

   private By pimMenu = By.xpath("//span[contains(@class, 'menu-item-text') and text()='PIM']\n");
  // @FindBy(xpath = "//span[contains(@class, 'menu-item-text')][contains(., 'PIM')]");
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver , this);

    }

    public PIMPage goToPIMScreen() {
        log.info("Navigating to the PIM Screen...");
        WebElement clickPimMneu = wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        clickPimMneu.click();
        log.info("Navigated to the PIM Screen successfully.");
        return new PIMPage(driver);
    }
}
