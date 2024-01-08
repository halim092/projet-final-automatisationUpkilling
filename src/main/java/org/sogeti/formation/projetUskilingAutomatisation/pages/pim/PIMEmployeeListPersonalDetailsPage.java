package org.sogeti.formation.projetUskilingAutomatisation.pages.pim;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

@Log4j2
public class PIMEmployeeListPersonalDetailsPage  {
    WebDriver driver;
    WebDriverWait wait;
    String filePath;
    @FindBy(css = ".--active")
    private WebElement myInfoTab;

    @FindBy(css = "div:nth-child(1)>div>div>div>div>input[placeholder=yyyy-mm-dd]")
    private WebElement inputDateBirthday; //input[class='oxd-input oxd-input--focus']
    @FindBy(css =  "input[type=\"radio\"][value=\"1\"]")
    private WebElement genderSelectMale;
    @FindBy(css = "input[type=\"radio\"][value=\"2\"]")
    private WebElement genderSelectFem;

    @FindBy(css=".oxd-radio-input")
    private List<WebElement> genders ;

    @FindBy(css="div.oxd-select-option")
    private List<WebElement> bloodTypeOption;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[3]")
    private WebElement bloodListBtn;////div[contains(text(),'-- Select --')]

    @FindBy(css="i.oxd-icon.bi-plus.oxd-button-icon")
    private WebElement btnAddAttachment;

    @FindBy(css="input.oxd-file-input[type=\"file\"]")//div[@class='oxd-file-button'] .oxd-file-button
    private WebElement btnBrowse;

    @FindBy(css="div[class='orangehrm-attachment'] button[type='submit']")
    private WebElement btnSaveUpload;
    @FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
    private WebElement successMessagePopup;
    @FindBy(css = "div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(2) div")
    private WebElement nameUploadFile;
    @FindBy(css = "div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(6) div:nth-child(1)")
    private WebElement dateFileUpload;
    @FindBy(css = "[type=submit]")
    public List<WebElement> btnSave;
    public PIMEmployeeListPersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       // scrollDown(500);
    }

    public PIMEmployeeListPersonalDetailsPage scrollPageDown() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 4000);");
        log.info("Scrolling the page down.");

        return this;
    }
    public PIMEmployeeListPersonalDetailsPage scrollPageDownEnd() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        log.info("Scrolling the page down End.");

        return this;
    }


    public PIMEmployeeListPersonalDetailsPage scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolling to the element.");
        return this;
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor jsExecutor;
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public PIMEmployeeListPersonalDetailsPage enterDateOfBirth(String dateOfBirth) {
        log.info("Entering date of birth: {}", dateOfBirth);
        scrollDown(500);
        wait.until(ExpectedConditions.elementToBeClickable(inputDateBirthday));
        inputDateBirthday.sendKeys(dateOfBirth);
        log.info(" Date of birth Entered  ");

        return this;
    }


    public PIMEmployeeListPersonalDetailsPage selectGender(String gender) {
        scrollDown(200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-radio-input--active")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        log.info("Selecting gender: {}", gender);
        if (gender.equals("Male")) {
            genders.get(0).click();
        } else if (gender.equals("Female")) {
            genders.get(1).click();
        } else {
            genders.get(0).click();
        }
        log.info("Gender selected: {}", gender);
        return this;
    }

    public PIMEmployeeListPersonalDetailsPage clickSubmitAfterGender(){
       log.info("We are clicking on Save after Selection Gender...");
        btnSave.get(0).click();
        log.info("The btn Save after Selection Gender is clicked");
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage clickSubmitAfterBlood(){
        log.info("We are clicking on Save after Selection Blood");
        btnSave.get(1).click();
        log.info("The btn Save after Selection Gender is clicked");
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage clickSelectBlood(){
        log.info("Were are clicking on bloody List ...");
        wait.until(ExpectedConditions.elementToBeClickable(bloodListBtn));
        bloodListBtn.click();
        log.info("We clicked .");
        return this;
    }

    public PIMEmployeeListPersonalDetailsPage clickRandomBloodTypeOption() {
        log.info("Select blood type");

        String randomBloodType = getRandomBloodType();
        WebElement bloodTypeElement = getBloodTypeElement(randomBloodType);
        bloodTypeElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        log.info("The blood type selected is: " + randomBloodType);
        return this;
    }

    private WebElement getBloodTypeElement(String bloodType) {
        String xpathSelector = getBloodTypeXPath(bloodType);


        return driver.findElement(By.xpath(xpathSelector));
    }

    private String getBloodTypeXPath(String bloodType) {
        switch (bloodType) {
            case "A+":
                return "//div[.='A+']";
            case "A-":
                return "//div[.='A-']";
            case "B+":
                return "//div[.='B+']";
            case "B-":
                return "//div[.='B-']";
            case "O+":
                return "//div[.='O+']";
            case "O-":
                return "//div[.='O-']";
            case "AB+":
                return "//div[.='AB+']";
            case "AB-":
                return "//div[.='AB-']";
            default:
                throw new IllegalArgumentException("Blood type not recognized: " + bloodType);
        }
    }

    public String getRandomBloodType() {
        String[] bloodTypes = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        int randomIndex = (int) (Math.random() * bloodTypes.length);
        return bloodTypes[randomIndex];
    }

    public PIMEmployeeListPersonalDetailsPage refreshPage() {

        driver.navigate().refresh();
        return this;
    }


    public String getDateOfBirth() {
        log.info("Get the actual Date Of Birth");


        wait.until(ExpectedConditions.visibilityOf(inputDateBirthday));
        log.info("actualDateOfBirth := " +inputDateBirthday.getAttribute("value")  );
         return inputDateBirthday.getAttribute("value");

    }



    public String getSelectedGender() {
        if (isRadioButtonMaleSelected()) {
            return "Male";
        } else if (isRadioButtonFemaleSelected()) {
            return "Female";
        } else {
            return "Unknown";
        }
    }

    public Boolean isRadioButtonMaleSelected() {
        log.info("Vérifier si l'utilisateur est un homme");
        Boolean isMaleSelected = genderSelectMale.isSelected();
        Assert.assertTrue(isMaleSelected, "Le bouton radio Homme n'est pas sélectionné.");
        return isMaleSelected;
    }

    public Boolean isRadioButtonFemaleSelected() {
        log.info("Vérifier si l'utilisateur est une femme");
        Boolean isFemaleSelected = genderSelectFem.isSelected();
        Assert.assertTrue(isFemaleSelected, "Le bouton radio Femme n'est pas sélectionné.");
        return isFemaleSelected;
    }
    public String getBloodType() {
        log.info("Get the actual Blood Type ");
        String actualBloodType = wait.until(ExpectedConditions.visibilityOf(bloodListBtn)).getText();
        return actualBloodType;
    }

    public PIMEmployeeListPersonalDetailsPage clickOnBtnAddAttachment(){
       // scrollDown(10000);
       // jsExecutor.executeScript("arguments[0].scrollIntoView();", btnBrowse);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        log.info("Were are clicking on the Button Add Attachment ...");
        wait.until(ExpectedConditions.visibilityOf(btnAddAttachment));
        btnAddAttachment.click();
        log.info("We clicked on the Button Add Attachment .");
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage clickOnBtnBrowse(){

        log.info("We are clicking on the Button Browse ...");
        wait.until(ExpectedConditions.elementToBeClickable(btnBrowse));
        log.info("We are clicked on the Button Browse Successfully  ...");
        btnBrowse.click();
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage uploadDoc(String filePath){
        log.info("We are uploading  ...");
       // js.executeScript("arguments[0].scrollIntoView();", btnBrowse);

        wait.until(ExpectedConditions.visibilityOf(btnBrowse));

        btnBrowse.sendKeys(filePath);
        log.info("We upload  successfully  .");
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage uploadFileDoc(String filePath) {
        log.info("Uploading file: {}", filePath);
        File file = new File(filePath);
        String absoluteFilePath = file.getAbsolutePath();
        btnBrowse.sendKeys(absoluteFilePath);
        log.info("File uploaded successfully");
        return this;
    }
    public PIMEmployeeListPersonalDetailsPage clickSaveUpload() {
        log.info("We Are Cliking on Btn Save Upload Doc .... ");
        wait.until(ExpectedConditions.elementToBeClickable(btnSaveUpload));
        log.info("We are clicked on the Button Save Upload DocSuccessfully  ...");
        btnSaveUpload.click();
        return this;

    }
    public String getFileName() {
        log.info("We are looking for the name of the File ...");
        wait.until(ExpectedConditions.visibilityOf(nameUploadFile));
        String fileName = nameUploadFile.getText();
        log.info("File name retrieved: {}", fileName);
        return fileName;
    }

    public String getDateFileUpload() {
        log.info("We are looking for the date of the File ...");
        wait.until(ExpectedConditions.visibilityOf(dateFileUpload));
        String date = dateFileUpload.getText();
        log.info("File date retrieved: {}", date);
        return date;
    }





}
