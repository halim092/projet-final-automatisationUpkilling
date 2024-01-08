package steps;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.formation.projetUskilingAutomatisation.pages.admin.AdminManagementPage;


public class AdminManagementPageSteps {
    AdminManagementPage adminManagementPage;
    public AdminManagementPageSteps(StandardSteps std){
        WebDriver driver = std.getDriver();
        adminManagementPage = PageFactory.initElements(driver, AdminManagementPage.class);

    }
    @And("je clique sur le bouton Add Admin")
    public void jeCliqueSurLeBoutonAddAdmin() {
        adminManagementPage.openAdminRegistrationPage();
    }

    @And("je selectionne le UserRole {string}")
    public void jeSelectionneLeUserRole(String string) {
        adminManagementPage.selectUserRole(string );
    }

    @And("je selectionne le status {string}")
    public void jeSelectionneLeStatus(String string) {
        adminManagementPage.selectStatus(string);
    }



    @And("je selectionne le premier nom dans la liste")
    public void jeSelectionneLePremierNomDansLaListe() {
        adminManagementPage.selectEmployeeName();
    }

    @And("j'entre un username Admin avec mes initiales{string}")
    public void jEntreUnUsernameAdminAvecMesInitiales(String string) {
        adminManagementPage.enterInitialUsername(string);
    }

    @And("jentre un mot de passe admin {string}")
    public void jentreUnMotDePasseAdmin(String string) {
        adminManagementPage.enterPasswordAdmin(string);
    }

    @And("je confirme le mote de passe {string}")
    public void jeConfirmeLeMoteDePasse(String string) {
        adminManagementPage.enterConfirmPasswordAdmin(string);
    }

    @And("je clique sur le bouton Save")
    public void jeCliqueSurLeBoutonSave() {
        adminManagementPage.clickSaveAdmin();
    }

    @And("je clique sur le profil")
    public void jeCliqueSurLeProfil() {
        adminManagementPage.clickProfil();
    }

    @And("je clique sur le bouton Logout")
    public void jeCliqueSurLeBoutonLogout() {
        adminManagementPage.toLogoutLogin();
    }


    @And("j'entre employee Name {string}")
    public void jEntreEmployeeName(String string) {
        adminManagementPage.enterEmployeeNameInput(string);
    }
}
