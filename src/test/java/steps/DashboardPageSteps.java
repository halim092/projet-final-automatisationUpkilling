package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.formation.projetUskilingAutomatisation.pages.DashboardPage;
import org.testng.Assert;

@Log4j2
public class DashboardPageSteps {
    DashboardPage dashboardPage;
    public DashboardPageSteps(StandardSteps std) {

        WebDriver driver = std.getDriver();
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }


    @And("je clique sur le bouton Pim")
    public void je_clique_sur_le_bouton_Pim() {
        dashboardPage.goToPIMScreen();
    }

    @Then("acceder a Dashboard")
    public void accederADashboard() {
        
    }

    @And("le nom du profil est egal l'employee Name {string} et {string}")
    public void leNomDuProfilEstEgalLEmployeeNameEt(String arg0, String arg1) {
        // Assert

        String expectedProfileName = "first Name" + " " + "lastName";
        String actualProfileName = dashboardPage.getProfileName();
        Assert.assertEquals(actualProfileName, expectedProfileName, "Profile name mismatch");

        // Log the result
        if (actualProfileName.equals(expectedProfileName)) {
            log.info("Test Passed: Profile name matches the expected value.");
        } else {
            log.info("Test Failed: Profile name does not match the expected value.");
        }
    }
}
