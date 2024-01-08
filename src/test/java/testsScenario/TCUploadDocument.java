package testsScenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.sogeti.formation.projetUskilingAutomatisation.pages.DashboardPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.LoginPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPersonalDetailsPage;
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

import static org.testng.Assert.assertEquals;

public class TCUploadDocument {

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
        String reportName = "TC6_"  + currentDateTime + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC6_UploadDoc/" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void TestUpload() throws InterruptedException {
        // Arrange
        String username = "Admin";
        String password = "admin123";
        String filePath = "src/main/resources/PJ/PJ1.jpg";
        String nameFile = "PJ2.jpg";


        // Act
        extentTest = extentReports.createTest("testUploadDoc", "Verify that Check that the upload has been successful\n" +
                "Verify that the file name and upload date are correct.");
        PIMEmployeeListPersonalDetailsPage uploadDoc = new PIMEmployeeListPersonalDetailsPage(driver);
        LoginPage lp = new LoginPage(driver);
        DashboardPage dashboardPage = lp.login(username, password);
        dashboardPage.goToMyInfo()
                .clickOnBtnAddAttachment()
                .uploadFileDoc(filePath)
                .clickSaveUpload();

        // Assert

        String nameFileUploaded = uploadDoc.getFileName();
        String dateUploaded = uploadDoc.getDateFileUpload();
        String currentDate = java.time.LocalDate.now().toString();

        try {
            assertEquals(nameFile, nameFileUploaded, "Uploaded file name is not as expected. Expected: " + nameFile + ", Actual: " + nameFileUploaded);
            extentTest.pass("Assertion passed for uploaded file name. Expected: " + nameFile + ", Actual: " + nameFileUploaded);
        } catch (AssertionError e) {
            extentTest.fail("Assertion Error - Uploaded file name: " + e.getMessage());
        }

        try {
            assertEquals(currentDate, dateUploaded, "Uploaded date is not as expected. Expected: " + currentDate + ", Actual: " + dateUploaded);
            extentTest.pass("Assertion passed for uploaded date. Expected: " + currentDate + ", Actual: " + dateUploaded);
        } catch (AssertionError e) {
            extentTest.fail("Assertion Error - Uploaded date: " + e.getMessage());
        }



    }
    @AfterMethod
    public void resultScreenshot(ITestResult result) {
        captureAndSaveScreenshot(result, "SC_UploadDoc");
    }

    private void captureAndSaveScreenshot(ITestResult result, String folderName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Obtenez le statut du test (Passed, Failed, etc.)
        String resultStatus = getResultStatus(result);

        // Construisez le nom du fichier en utilisant le statut et le timestamp
        String name = "uploadDocScreenshot_" + resultStatus + "_" + timestamp + ".png";

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
    public void tearDown() {
      //  driver.quit();
        extentReports.flush();
    }

}

