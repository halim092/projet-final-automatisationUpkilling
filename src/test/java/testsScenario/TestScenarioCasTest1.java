package testsScenario;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenarioCasTest1 {
    public final static String URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL);
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


    }

    @AfterMethod
    public void tearDown() {
      //  driver.quit();
    }
}
