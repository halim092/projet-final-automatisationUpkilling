package testsScenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sogeti.formation.projetUskilingAutomatisation.pages.EmployeeCreationPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.HomePage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.PIMPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenarioCasTest1 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Remplacez "webdriver.chrome.driver" par "webdriver.gecko.driver" si vous utilisez Gecko (Firefox)
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testScenario() {
        // Arrange: 1. Connexion à l'application
        new LoginPage(driver).login("Admin", "admin123");

        // 2. Allez à l'écran PIM
        new HomePage(driver).goToPIMScreen();

        // 3. Créez un employé
        new PIMPage(driver).goToEmployeeCreationPage();
        new EmployeeCreationPage(driver).createEmployee("Halim", "Halim", "Allouche");

        // 4. Validez la création
        new EmployeeCreationPage(driver).clickSave();
        // Ajoutez ici le code de validation si nécessaire
    }

    // Vous pouvez ajouter d'autres méthodes de test si nécessaire

    // Il est recommandé d'avoir une méthode de fermeture après chaque méthode de test
    @AfterMethod
    public void tearDown() {
      //  driver.quit();
    }
}
