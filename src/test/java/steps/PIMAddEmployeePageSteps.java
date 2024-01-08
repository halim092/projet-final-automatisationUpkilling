package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMAddEmployeePage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class PIMAddEmployeePageSteps {
    PIMAddEmployeePage pimAddEmployeePage;

    public PIMAddEmployeePageSteps(StandardSteps std) {
        WebDriver driver = std.getDriver();
        pimAddEmployeePage = PageFactory.initElements(driver, PIMAddEmployeePage.class);
    }

    @And("j'entre le first Name {string}")
    public void jentre_le_first_Name(String string) {
        pimAddEmployeePage.enterFirstName(string);
    }

    @And("j'entre le Middle Name {string}")
    public void jentre_le_Middle_Name(String string) {
        pimAddEmployeePage.enterMiddleName(string);
    }

    @And("j'entre le Last Name {string}")
    public void jentre_le_Last_Name(String string) {
        pimAddEmployeePage.enterLastName(string);
    }

    @And("j'entre le employee Id {string}")
    public void jentre_le_employee_id(String string) {
        pimAddEmployeePage.enterEmployeeID(string);
    }

    @When("je clique sur le bouton save")
    public void jeCliqueSurLeBoutonSave() {
        pimAddEmployeePage.clickSave();
    }
    @Then("l'employee est cree")
    public void lEmployeeEstCree() {

        // Assert

        if (pimAddEmployeePage.isEmployeeCreatedSuccessfully()) {
            assertTrue(false, "Employee created successfully.");
            log.info("Test Passed: Employee created successfully.");
        } else {
            assertTrue(true, "Employee creation failed.");
            log.info("Test Failed: Employee creation failed.");
        }


    }

    @And("je clique sur le menu Admin")
    public void jeCliqueSurLeMenuAdmin() {
        pimAddEmployeePage.goToAdminScreen();
    }


}
