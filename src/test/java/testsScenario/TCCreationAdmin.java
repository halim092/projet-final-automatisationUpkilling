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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCCreationAdmin {
    public final static String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC2_CreationAdmin.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void TestCreationAdmin() throws InterruptedException {
        // Arrange
        String username = "Admin";
        String password = "admin123";
        String firstName = "frt";
        String middleName = "";
        String employeeID = "1992";
        String lastName = "tet";
        String passwordAdmin = "Halim1992$";
        String initialUsername = "ha.autom";
        String role = "Admin";
        String status = "Enabled";

        // Act
        /*LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(username, password);

        LoginPage creationAdmin = dashboardPage.goToPIMScreen()

                .goToCreateLoginDetails()
                .openAdminRegistrationPage()
                .selectUserRole(role)
                .selectStatus(status)
                .enterEmployeeNameInput(lastName)
                .enterInitialUsername(initialUsername)
                .enterPasswordAdmin(passwordAdmin)
                .enterConfirmPasswordAdmin(passwordAdmin)
                .toLogoutLogin()
                .enterUsername(initialUsername)
                .enterPassword(passwordAdmin)
                .clickLogin();*/
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(username, password);

        dashboardPage
                .goToPIMScreen()
                .goToAddingEmployeePage()
                .enterFirstName(firstName)
                .enterMiddleName(middleName)
                .enterLastName(lastName)
                .enterEmployeeID(employeeID)
                // .createEmployee(firstName,middleName,lastName,employeeID)
                .goToCreateLoginDetails()
                .enterInitialUsername(initialUsername)
                .enterPasswordAdmin(passwordAdmin)
                .enterConfirmPasswordAdmin(passwordAdmin)
                .clickSaveAdmin()
                .clickProfil()
                .toLogoutLogin().login(initialUsername, passwordAdmin);
        // Assert

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
                FileUtils.copyFile(scrFile, new File("test-output/screenshots/SC_CreationAdmin" + name));
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
