package testsScenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sogeti.formation.projetUskilingAutomatisation.pages.HomePage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestScenarioCasTest1 {
    public final static String URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void testScenario() {
        // Arrange
        String username = "Admin";
        String password = "admin123";
        String firstName = "Halim";
        String middleName = "";
        String lastName = "Allouche";

        // Act
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(username, password);

        homePage
                .goToPIMScreen()
                .goToEmployeeCreationPage()
                .enterFirstName(firstName)
                .enterMiddleName(middleName)
                .enterLastName(lastName)
                .clickSave()
                .createEmployee(firstName, middleName, lastName);

        // Assert
        assertTrue(loginPage.isLogoutButtonDisplayed(), "Login failed.");
        assertTrue(loginPage.isEmployeeCreatedSuccessfully(), "Employee creation failed.");
    }

    @AfterMethod
    public void tearDown() {
        // driver.quit();
    }
}
