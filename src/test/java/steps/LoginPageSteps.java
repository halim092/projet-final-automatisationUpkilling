package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;

public class LoginPageSteps {
    LoginPage pageLogin;

    public LoginPageSteps(StandardSteps std) {

        WebDriver driver = std.getDriver();
        pageLogin = PageFactory.initElements(driver, LoginPage.class);
    }



    @And("j'entre un username {string}")
    public void j_entre_un_username(String string) {
        pageLogin.enterUsername( string);

    }

    @And("j'entre un mot de passe {string}")
    public void j_entre_un_mdp(String string) {
        pageLogin.enterPassword(string);

    }

    @When("je clique sur le bouton login")
    public void je_clique_sur_le_bouton_login() {
        pageLogin.clickLogin();
    }


    @And("j'entre le username avec les initiales {string}")
    public void jEntreLeUsernameAvecLesInitiales(String string) {
        pageLogin.enterUsername(string);
    }

    @And("j'entre le passwordAdmin {string}")
    public void jEntreLePasswordAdmin(String string) {
        pageLogin.enterPassword(string);
    }
}

