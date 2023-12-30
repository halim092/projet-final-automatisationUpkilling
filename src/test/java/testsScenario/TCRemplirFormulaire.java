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
import org.sogeti.formation.projetUskilingAutomatisation.pages.PIMEmployeeListPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.PIMEmployeeListPersonalDetailsPage;
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

public class TCRemplirFormulaire {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        String reportName = "TC3_"  + currentDateTime + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/htmlReporter/TC3_RemplirFormulaire/" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }


    @Test
    public void TestRemplirFormulaire() throws InterruptedException {

        // Arrange
        String username = "Admin";
        String password = "admin123";
        String firstName = "Halim";
        String middleName = "Castro";
        String lastName = "Allouche";
        String dateOfBirth = "1992-01-24";
        String gender = "Male";
        String employeeId = "3817";

        // Act
        extentTest = extentReports.createTest("testRemplirFormulaire", "Verify that the information has been saved");

        //PIMAddEmployeePage rempliFormulaire = new PIMAddEmployeePage(driver);
        PIMEmployeeListPersonalDetailsPage pIMEmployeeListPersonalDetailsPage =new PIMEmployeeListPersonalDetailsPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(username, password);
        dashboardPage.goToPIMScreen();
        PIMEmployeeListPage pIMEmployeeListPage = new PIMEmployeeListPage(driver);

        pIMEmployeeListPage.goToAddingEmployeePage()

                .createEmployee(firstName, middleName, lastName, employeeId)
                .goToPersonalDetails()
               .scrollPageDown()
                .enterDateOfBirth(dateOfBirth)
                .selectGender(gender)
                .scrollPageDown()
                .clickSelectBlood()
                .clickRandomBloodTypeOption();


        pIMEmployeeListPersonalDetailsPage.refreshPage();



      String actualDateOfBirth = pIMEmployeeListPersonalDetailsPage.getDateOfBirth();
        String actualGender = pIMEmployeeListPersonalDetailsPage.getSelectedGender();
        String actualBloodType = pIMEmployeeListPersonalDetailsPage.getBloodType();

        // Assertion
        //Assert.assertEquals(actualDateOfBirth, dateOfBirth, "Date of birth is incorrect");
        //Assert.assertEquals(actualGender, gender, "Gender is incorrect");
        Assert.assertEquals(actualBloodType, pIMEmployeeListPersonalDetailsPage.getRandomBloodType(), "Blood type is incorrect");


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
                FileUtils.copyFile(scrFile, new File("test-output/screenshots/SC_RemplirFormulaire/" + name));
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
