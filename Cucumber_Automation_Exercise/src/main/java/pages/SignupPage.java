package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenuim.SelenuimFramework;
import java.time.Duration;

public class SignupPage {

    private final SelenuimFramework framework;

    public SignupPage(SelenuimFramework framework) {
        this.framework = framework;
    }

    // ------------------- Locators -------------------
    private final By radioButtonTitleMr = By.cssSelector("input#id_gender1");
    private final By radioButtonTitleMrs = By.cssSelector("input#id_gender2");
    private final By nameField = By.cssSelector("input#name");
    private final By emailField = By.cssSelector("input#email");
    private final By passwordField = By.cssSelector("input#password");
    private final By firstNameField = By.cssSelector("input#first_name");
    private final By lastNameField = By.cssSelector("input#last_name");
    private final By newsletterCheckbox = By.cssSelector("input#newsletter");
    private final By specialOffersCheckbox = By.cssSelector("input#optin");
    private final By companyField = By.cssSelector("input#company");
    private final By address1Field = By.cssSelector("input#address1");
    private final By address2Field = By.cssSelector("input#address2");
    private final By countryDropdown = By.cssSelector("select#country");
    private final By stateField = By.cssSelector("input#state");
    private final By cityField = By.cssSelector("input#city");
    private final By zipcodeField = By.cssSelector("input#zipcode");
    private final By mobileNumberField = By.cssSelector("input#mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private final By accountCreatedHeader = By.cssSelector("h2[data-qa='account-created']");
    private final By enterAccountInformationText = By.cssSelector("h2.title b");
    private final By dateOfBirthDay = By.cssSelector("select#days");
    private final By dateOfBirthMonth = By.cssSelector("select#months");
    private final By dateOfBirthYear = By.cssSelector("select#years");

    // ------------------- Actions -------------------
    public void sendName(String name) { framework.sendKeys(nameField, name); }
    public void sendEmail(String email) { framework.sendKeys(emailField, email); }
    public void sendPassword(String password) { framework.sendKeys(passwordField, password); }
    public void sendFirstName(String firstName) { framework.sendKeys(firstNameField, firstName); }
    public void sendLastName(String lastName) { framework.sendKeys(lastNameField, lastName); }
    public void sendCompany(String company) { framework.sendKeys(companyField, company); }
    public void sendAddress(String address) { framework.sendKeys(address1Field, address); }
    public void sendAddress2(String address2) { framework.sendKeys(address2Field, address2); }
    public void selectCountry(String country) { framework.selectDropdownByVisibleText(countryDropdown, country); }
    public void sendState(String state) { framework.sendKeys(stateField, state); }
    public void sendCity(String city) { framework.sendKeys(cityField, city); }
    public void sendZipcode(String zipcode) { framework.sendKeys(zipcodeField, zipcode); }
    public void sendMobileNumber(String mobileNumber) { framework.sendKeys(mobileNumberField, mobileNumber); }
    public void clickCreateAccountButton() { framework.click(createAccountButton); }
    public void clickContinueButton() { framework.click(continueButton); }

    public String getAccountCreatedText() { return framework.getText(accountCreatedHeader); }
    public String getEnterAccountInformationText() { return framework.getText(enterAccountInformationText); }

    public void checkSignupForNewsletterCheckBox() { framework.checkCheckbox(newsletterCheckbox); }
    public void checkReceiveSpecialOffersCheckBox() { framework.checkCheckbox(specialOffersCheckbox); }
    public void selectSignupRadioButtonTitleMr() { framework.selectRadioButton(radioButtonTitleMr); }
    public void selectSignupRadioButtonTitleMrs() { framework.selectRadioButton(radioButtonTitleMrs); }
    public void selectDateOfBirthDay(String day) { framework.selectDropdownByVisibleText(dateOfBirthDay, day); }
    public void selectDateOfBirthMonth(String month) { framework.selectDropdownByVisibleText(dateOfBirthMonth, month); }
    public void selectDateOfBirthYear(String year) { framework.selectDropdownByVisibleText(dateOfBirthYear, year); }

    public void waitForAccountCreatedPage() {
        new WebDriverWait(framework.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.textToBePresentInElementLocated(accountCreatedHeader, "ACCOUNT CREATED!"));
    }

    public boolean isElementVisible(By locator) { return framework.isDisplayed(locator); }

    public void scrollToCreateAccountButton() { framework.scrollToElement(createAccountButton); }
    public void waitHelper() { framework.implicitWait(15); }

    public void waitForStateFieldToBeClickable() {
        new WebDriverWait(framework.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(stateField));
    }
}
