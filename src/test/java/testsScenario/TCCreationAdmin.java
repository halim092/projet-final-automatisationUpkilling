package testsScenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.sogeti.formation.projetUskilingAutomatisation.pages.DashboardPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMAddEmployeePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Log4j2
public class TCCreationAdmin {
    public final static String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        driver = new FirefoxDriver(firefoxOptions);
        driver.get(URL);
        driver.manage().window().maximize();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        String reportName = "TC2_"  + currentDateTime + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC2_CreationAdmin/" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void TestCreationAdmin() throws InterruptedException {
        extentTest = extentReports.createTest("cas de test: creation Admin", "");
        // Arrange
        String username = "Admin";
        String password = "admin123";
        String firstName = "Joe";
        String middleName = "";
        String employeeID = "1992";
        String lastName = "Klay";
        String passwordAdmin = "Halim1992$";
        String initialUsername = "jk.autom";
        String role = "Admin";
        String status = "Enabled";

        // Act
        extentTest = extentReports.createTest("testCreationAdmin", "Verify Admin creation functionality");
        PIMAddEmployeePage addingEmployeePage = new PIMAddEmployeePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage creationAdmin = loginPage.login(username, password);

        creationAdmin
                .goToPIMScreen()
                .goToAddingEmployeePage()
                .createEmployee(firstName, middleName, lastName, employeeID)
                .goToAdminScreen()
                .openAdminRegistrationPage()
                .selectUserRole(role)
                .selectStatus(status)
                .enterEmployeeNameInput(lastName)
                .selectEmployeeName()
                .enterInitialUsername(initialUsername)
                .enterPasswordAdmin(passwordAdmin)
                .enterConfirmPasswordAdmin(passwordAdmin)
                .clickSaveAdmin()
                .clickProfil()
                .toLogoutLogin()
                .enterUsername(initialUsername)
                .enterPassword(passwordAdmin)
                .clickLogin();
      /* LoginPage loginPage = new LoginPage(driver);
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
                .toLogoutLogin()
                .login(initialUsername, passwordAdmin);*/
        // Assert

        String expectedProfileName = firstName + " " + lastName;
        String actualProfileName = creationAdmin.getProfileName();
        Assert.assertEquals(actualProfileName, expectedProfileName, "Profile name mismatch");

        // Log the result
        if (actualProfileName.equals(expectedProfileName)) {
            log.info("Test Passed: Profile name matches the expected value.");
        } else {
            log.info("Test Failed: Profile name does not match the expected value.");
        }
    }

    @AfterMethod
    public void resultScreenshot(ITestResult result) {
        captureAndSaveScreenshot(result, "SC_CreationAdmin");
    }

    private void captureAndSaveScreenshot(ITestResult result, String folderName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Obtenez le statut du test (Passed, Failed, etc.)
        String resultStatus = getResultStatus(result);

        // Construisez le nom du fichier en utilisant le statut et le timestamp
        String name = "creationAdminScreenshot_" + resultStatus + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(scrFile, new File("test-output/screenshots/" + folderName + "/" + name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResultStatus(ITestResult result) {
        int status = result.getStatus();
        if (status == ITestResult.SUCCESS) {
            return "Passed";
        } else if (status == ITestResult.FAILURE) {
            return "Failed";
        } else if (status == ITestResult.SKIP) {
            return "Skipped";
        } else {
            return "Unknown";
        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        // Gérer le statut du test (réussite/échec)
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Échec du test", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test réussi", ExtentColor.GREEN));
        } else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test ignoré", ExtentColor.YELLOW));
            extentTest.skip(result.getThrowable());
        }
       // driver.quit();
        extentReports.flush();
    }

}
