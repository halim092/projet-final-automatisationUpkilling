package testsScenario;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPage;
import org.sogeti.formation.projetUskilingAutomatisation.pages.pim.PIMEmployeeListPersonalDetailsPage;
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
public class TCRemplirFormulaire {
    public final static String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        driver = new FirefoxDriver(firefoxOptions);         driver.get(URL);
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
        String gender = "Female";
        String employeeId = "3817";

       /* JsonNode testData = loadTestData("src/test/resources/dataTests/TCRemplirFormulaireTestData.json");

        // Utilisez les données
        String username = testData.get("username").asText();
        String password = testData.get("password").asText();
        String firstName = testData.get("firstName").asText();
        String middleName = testData.get("middleName").asText();
        String lastName = testData.get("lastName").asText();
        String dateOfBirth = testData.get("dateOfBirth").asText();
        String gender = testData.get("gender").asText();
        String employeeId = testData.get("employeeId").asText();*/

        // Act
        extentTest = extentReports.createTest("testRemplirFormulaire", "Verify that the information has been saved");

        //PIMAddEmployeePage rempliFormulaire = new PIMAddEmployeePage(driver);
        PIMEmployeeListPersonalDetailsPage pIMEmployeeListPersonalDetailsPage =new PIMEmployeeListPersonalDetailsPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(username, password);
        dashboardPage.goToPIMScreen();
        PIMEmployeeListPage pIMEmployeeListPage = new PIMEmployeeListPage(driver);
        PIMEmployeeListPersonalDetailsPage actualDateOfBirth = new PIMEmployeeListPersonalDetailsPage(driver);

        PIMEmployeeListPersonalDetailsPage remplirFormulaire = pIMEmployeeListPage.goToAddingEmployeePage()

                .createEmployee(firstName, middleName, lastName, employeeId)
                .goToPersonalDetails()
               .scrollPageDown()
                .enterDateOfBirth(dateOfBirth)
                .selectGender(gender)
                .clickSubmitAfterGender()
                .scrollPageDown()
                .clickSelectBlood()
                .clickRandomBloodTypeOption()
                        .clickSubmitAfterBlood();
                //.getDateOfBirth();
               // .refreshPage();
               // .getDateOfBirth();


       // pIMEmployeeListPersonalDetailsPage.refreshPage();



      /*String actualDateOfBirth = pIMEmployeeListPersonalDetailsPage.getDateOfBirth();
        String actualGender = pIMEmployeeListPersonalDetailsPage.getSelectedGender();
        String actualBloodType = pIMEmployeeListPersonalDetailsPage.getBloodType();
*/
        // Assert
// Assert for Date of Birth
       String expectedDateOfBirth = dateOfBirth;
        String actualDateOfBirthValue = actualDateOfBirth.getDateOfBirth();

        Assert.assertEquals(actualDateOfBirthValue, expectedDateOfBirth, "Date of birth does not match. Expected: " + expectedDateOfBirth + ", Actual: " + actualDateOfBirthValue + ".");
        log.info("Assertion Passed: Date of birth matches. Expected: " + expectedDateOfBirth + ", Actual: " + actualDateOfBirthValue + ".");

// Assert for Gender
        // Assumez que vous avez déjà récupéré la valeur du genre depuis votre source de données
        String genderFromTestData = "M"; // Remplacez cela par la valeur réelle obtenue de vos données de test

        if (gender.equals("M")) {
            try {
                Assert.assertTrue(pIMEmployeeListPersonalDetailsPage.isRadioButtonMaleSelected());
                log.info("The Male radio button is selected.");
            } catch (AssertionError e) {
                log.error("Error: The Male radio button is not selected.");
                throw e; // Rethrow the assertion error to mark the test as failed
            }
        } else {
            try {
                Assert.assertTrue(pIMEmployeeListPersonalDetailsPage.isRadioButtonFemaleSelected());
                log.info("The Female radio button is selected.");
            } catch (AssertionError e) {
                log.error("Error: The Female radio button is not selected.");
                throw e; // Rethrow the assertion error to mark the test as failed
            }
        }

        Assert.assertNotNull(pIMEmployeeListPersonalDetailsPage.getBloodType());


// Assert for Random Blood Type
        String actualBloodTypeValue = remplirFormulaire.getBloodType();
        Assert.assertNotNull(actualBloodTypeValue, "Random blood type is null.");
        log.info("Assertion Passed: Random blood type is not null. Value: " + actualBloodTypeValue + ".");

    }


    @AfterMethod
    public void resultScreenshot(ITestResult result) {
        captureAndSaveScreenshot(result, "SC_RemplirFormulaire");
    }

    private void captureAndSaveScreenshot(ITestResult result, String folderName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Obtenez le statut du test (Passed, Failed, etc.)
        String resultStatus = getResultStatus(result);

        // Construisez le nom du fichier en utilisant le statut et le timestamp
        String name = "formulaireScreenshot_" + resultStatus + "_" + timestamp + ".png";

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
       // driver.quit();
        extentReports.flush();
    }
}
