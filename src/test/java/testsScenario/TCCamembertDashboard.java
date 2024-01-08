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

public class TCCamembertDashboard {
    public final static String URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
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
        String reportName = "TC5_"  + currentDateTime + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC5_Camembert/" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Test
    public void TestCamembert() throws InterruptedException {
        // Arrange
        String username = "Admin";
        String password = "admin123";


        // Act
        extentTest = extentReports.createTest("testCamembert", "Verify that the Camembert reproportioned correctly");

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage camembertTest = loginPage.login(username, password);

        camembertTest.clickOnLegendNewYorkSalesOffice();

        // Assert
        Assert.assertTrue(camembertTest.isLegendStrikethrough(), "Camembert did not reproportion correctly.");



    }
    @AfterMethod
    public void resultScreenshot(ITestResult result) {
        captureAndSaveScreenshot(result, "SC_Camembert");
    }

    private void captureAndSaveScreenshot(ITestResult result, String folderName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Obtenez le statut du test (Passed, Failed, etc.)
        String resultStatus = getResultStatus(result);

        // Construisez le nom du fichier en utilisant le statut et le timestamp
        String name = "CamembertScreenshot_" + resultStatus + "_" + timestamp + ".png";

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
        extentReports.flush();
        driver.quit();
    }
}
