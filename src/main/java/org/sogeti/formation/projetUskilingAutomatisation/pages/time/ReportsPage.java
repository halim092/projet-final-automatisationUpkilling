package org.sogeti.formation.projetUskilingAutomatisation.pages.time;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Log4j2
public class ReportsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By hintsInput = By.cssSelector("input[placeholder=\"Type for hints...\"]");
    private By selectProject = By.cssSelector("div.oxd-autocomplete-dropdown[role=\"listbox\"]");
    private By viewBtn = By.cssSelector(".oxd-button");

    private By listTime = By.cssSelector("div.col-alt.rgCell[data-rgrow]");
    private By zoomBtn = By.cssSelector("i.oxd-icon.bi-arrows-fullscreen");
    private By closeZoomBtn = By.cssSelector("i.oxd-icon.bi-fullscreen-exit");

    private By totalTimeText = By.cssSelector("span.oxd-text.oxd-text--span.oxd-text--footer");

    private double totalTime = 0;

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ReportsPage clickOnZoomBtn() {
        log.info("We are clicking on the Zoom Button ... ");
        WebElement zoomBtnElement = wait.until(ExpectedConditions.presenceOfElementLocated(zoomBtn));
        zoomBtnElement.click();
        log.info("Zoom Button is clicked ");
        return this;
    }

    public ReportsPage clickOnCloseZoomBtn() {
        log.info("We are closing the Zoom  ... ");
        WebElement closeZoomBtnElement = wait.until(ExpectedConditions.presenceOfElementLocated(closeZoomBtn));
        closeZoomBtnElement.click();
        log.info("The Zoom is closed ");
        return this;
    }

    public ReportsPage enterHints(String nameProjects) {
        log.info("We are entering the Name of the projects in Hints Input  ..");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement enterHintsInput = wait.until(ExpectedConditions.presenceOfElementLocated(hintsInput));
        enterHintsInput.sendKeys(nameProjects);
        log.info("The Name of the projects is entered   successfully");
        return this;
    }

    public ReportsPage selectProject() {
        log.info("Selecting the project ....");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

        WebElement clickSelectingProject = wait.until(ExpectedConditions.presenceOfElementLocated(selectProject));
        List<WebElement> rowProject = driver.findElements(selectProject);
        rowProject.get(0).click();
        log.info("The project is selected .");
        return this;
    }

    public ReportsPage clickViewProject() {
        log.info("Clicking on the Button View ...");
        WebElement clickViewProject = wait.until(ExpectedConditions.presenceOfElementLocated(viewBtn));
        clickViewProject.click();
        log.info("The button View is clicked .");
        return this;
    }

    public ReportsPage calculateTotalTime() {
        log.info("Calculating the total time of Activity ...");

        List<WebElement> rows = driver.findElements(listTime);

        double time;
        for (WebElement row : rows) {
            time = Double.parseDouble(row.getText().replace(",", "."));
            totalTime += time;
        }

        log.info("The total Time is: " + totalTime);

        return this;
    }

    public double getTotalTime(){
        return totalTime;
    }

    public ReportsPage getTotalTimeText() {
        log.info("We are looking for the total amount displayed ...  ");
        WebElement TotalDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(totalTimeText));
        log.info("The total amount displayed is : " + TotalDisplayed.getText());
        return this;
    }
    public String retrieveTotalTimeText() {
        log.info("We are looking for the total amount displayed ...  ");
        WebElement totalTimeElement = wait.until(ExpectedConditions.presenceOfElementLocated(totalTimeText));
        String totalTimeText = totalTimeElement.getText();

        // Extract numeric part from the string
        String numericPart = totalTimeText.replaceAll("[^\\d.]", "");
        totalTime = Double.parseDouble(numericPart);

        log.info("The total amount displayed is : " + totalTimeText);
        return totalTimeText;
    }


    public boolean checkTotalTimeMatch() {
        log.info("Verifying that the times match...");

        String testTotalDisplayed = driver.findElement(totalTimeText).getText();
        double totalDisplayed = Double.parseDouble(testTotalDisplayed.substring(testTotalDisplayed.lastIndexOf(" ")));

        boolean isMatch = Double.compare(totalTime, totalDisplayed) == 0;

        if (isMatch) {
            log.info("The times match.");
        } else {
            log.error("The times do not match. Calculated time: " + totalTime + ", Displayed time: " + totalDisplayed);
        }

        return isMatch;
    }

}
