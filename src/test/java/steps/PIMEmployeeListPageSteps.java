package steps;

import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMAddEmployeePage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPage;

@Log4j2
public class PIMEmployeeListPageSteps {
    PIMEmployeeListPage pimEmployeeListPage;
    LoginPage pageLogin ;
    private LoginPage loginPage;
    private PIMAddEmployeePage pimAddEmployeePage;

    public PIMEmployeeListPageSteps(StandardSteps std) {

        WebDriver driver = std.getDriver();
        pimEmployeeListPage = PageFactory.initElements(driver, PIMEmployeeListPage.class);
    }



@And("je clique sur le button Add")
    public void je_clique_sur_le_button_Add(){
    pimEmployeeListPage.goToAddingEmployeePage();
        }


    @And("le titre de la page est {string}")
    public void leTitreDeLaPageEst(String arg0) {
        pimEmployeeListPage.getTitleText();
    }
}
