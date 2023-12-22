package testsScenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sogeti.formation.projetUskilingAutomatisation.pages.DashboardPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.PIMAddEmployeePage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertTrue;

public class TCCreationPIM {
    public final static String URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        // Initialiser ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC1_CreationEmploye/Report1.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void TestCreationPIM() throws InterruptedException {
        // Arrange
        String username = "Admin";
        String password = "admin123";
        String firstName = "feuzu";
        String middleName = "uriz";
        String lastName = "fueh";
        String employeeID="1992";

        // Act
        extentTest = extentReports.createTest("testCreationPIM", "Verify PIM creation functionality");
         PIMAddEmployeePage addingEmployeePage =new PIMAddEmployeePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage creationPIM = loginPage.login(username, password);

        creationPIM
                .goToPIMScreen()
                .goToAddingEmployeePage()
                .enterFirstName(firstName)
                .enterMiddleName(middleName)
                .enterLastName(lastName)
                .enterEmployeeID(employeeID)
                .clickSave();



        // Assert
        if (loginPage.isEmployeeCreatedSuccessfully()) {
            assertTrue(false, "Employee created successfully.");
        } else {
            assertTrue(true, "Employee creation failed.");
        }
        String expectedProfileText = firstName + " " + lastName;
        String actualProfileText = addingEmployeePage.getProfileText();

        assertTrue(actualProfileText.equals(expectedProfileText),
                "Profile text doesn't match. Expected: " + expectedProfileText + ", Actual: " + actualProfileText);

    }
    @AfterMethod

    public void resultScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Generate a timestamp to make the filename unique
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());

            String name = "screenshot_" + timestamp + ".png";

            try {
                FileUtils.copyFile(scrFile, new File("test-output/screenshots/SC_CreationEmploye" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        // driver.quit();
        extentReports.flush();

    }
}
