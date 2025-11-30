package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenuim.SelenuimFramework;
import java.time.Duration;

public class SignupPage {

    // Locators
    private final By radioButtonTitleMrLocator = By.cssSelector("input#id_gender1");
    private final By radioButtonTitleMrsLocator = By.cssSelector("input#id_gender2");
    private final By nameLocator = By.cssSelector("input#name");
    private final By emailLocator = By.cssSelector("input#email");
    private final By passwordLocator = By.cssSelector("input#password");
    private final By firstNameLocator = By.cssSelector("input#first_name");
    private final By lastNameLocator = By.cssSelector("input#last_name");
    private final By dateOfBirthDayLocator = By.cssSelector("select#days");
    private final By dateOfBirthMonthLocator = By.cssSelector("select#months");
    private final By dateOfBirthYearLocator = By.cssSelector("select#years");
    private final By enterAccountInformationTextLocator = By.cssSelector("h2.title b");
    private final By forNewsletterCheckBoxLocator = By.cssSelector("input#newsletter");
    private final By receiveSpecialOffersCheckBoxLocator = By.cssSelector("input#optin");
    private final By companyLocator = By.cssSelector("input#company");
    private final By address1Locator = By.cssSelector("input#address1");
    private final By address2Locator = By.cssSelector("input#address2");
    private final By countryLocator = By.cssSelector("select#country");
    private final By stateLocator = By.cssSelector("input#state");
    private final By cityLocator = By.cssSelector("input#city");
    private final By zipcodeLocator = By.cssSelector("input#zipcode");
    private final By mobileNumberLocator = By.cssSelector("input#mobile_number");
    private final By createAccountButtonLocator = By.cssSelector("button[data-qa=\"create-account\"]");
    private final By accountCreatedTextLocator = By.cssSelector("h2.title b");
    private final By continueButtonLocator = By.cssSelector("a[data-qa=\"continue-button\"]");
    private final By subscriptionAdTextLocator = By.cssSelector("div.col-sm-3.col-sm-offset-1 p");

    private final SelenuimFramework framework;

    public SignupPage(SelenuimFramework framework) {
        this.framework = framework;
    }

    // Actions
    public void sendName(String name) {
        framework.sendKeys(nameLocator, name);
    }

    public void sendEmail(String email) {
        framework.sendKeys(emailLocator, email);
    }

    public void sendPassword(String password) {
        framework.sendKeys(passwordLocator, password);
    }

    public void sendFirstName(String firstname) {
        framework.sendKeys(firstNameLocator, firstname);
    }

    public void sendLastName(String lastName) {
        framework.sendKeys(lastNameLocator, lastName);
    }

    public void sendCompany(String company) {
        framework.sendKeys(companyLocator, company);
    }

    public void sendAddress(String address) {
        framework.sendKeys(address1Locator, address);
    }

    public void sendAddress2(String address2) {
        framework.sendKeys(address2Locator, address2);
    }

    public void selectCountry(String country) {
        framework.selectDropdownByVisibleText(countryLocator, country);
    }

    public void sendState(String state) {
        framework.sendKeys(stateLocator, state);
    }

    public void sendCity(String city) {
        framework.sendKeys(cityLocator, city);
    }

    public void sendZipcode(String zipcode) {
        framework.sendKeys(zipcodeLocator, zipcode);
    }

    public void sendMobileNumber(String mobileNumber) {
        framework.sendKeys(mobileNumberLocator, mobileNumber);
    }

    public void clickCreateAccountButton() {
        framework.click(createAccountButtonLocator);
    }

    public void clickContinueButton() {
        framework.click(continueButtonLocator);
    }

    public String getAccountCreatedText() {
        return framework.getText(accountCreatedTextLocator);
    }

    public String getEnterAccountInformationText() {
        return framework.getText(enterAccountInformationTextLocator);
    }

    public void checkSignupForNewsletterCheckBox() {
        framework.checkCheckbox(forNewsletterCheckBoxLocator);
    }

    public void checkReceiveSpecialOffersCheckBox() {
        framework.checkCheckbox(receiveSpecialOffersCheckBoxLocator);
    }

    public void selectSignupRadioButtonTitleMr() {
        framework.selectRadioButton(radioButtonTitleMrLocator);
    }

    public void selectSignupRadioButtonTitleMrs() {
        framework.selectRadioButton(radioButtonTitleMrsLocator);
    }

    public void selectDateOfBirthDay(String day) {
        framework.selectDropdownByVisibleText(dateOfBirthDayLocator, day);
    }

    public void selectDateOfBirthMonth(String month) {
        framework.selectDropdownByVisibleText(dateOfBirthMonthLocator, month);
    }

    public void selectDateOfBirthYear(String year) {
        framework.selectDropdownByVisibleText(dateOfBirthYearLocator, year);
    }

    public void scrollToAddress2() {
        framework.scrollToElement(address2Locator);
    }

    public void scrollToCreateAccountButton() {
        framework.scrollToElement(createAccountButtonLocator);
    }

    public void scrollToSubscriptionAdText() {
        framework.scrollToElement(subscriptionAdTextLocator);
    }

    public void waitHelper() {
        framework.implicitWait(15);
    }

    // NEW METHOD: Wait for account created page
    public void waitForAccountCreatedPage() {
        try {
            // 🔥 FIXED: Now using framework.getDriver() instead of framework.getDriver()
            WebDriverWait wait = new WebDriverWait(framework.getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedTextLocator));
            System.out.println("Successfully navigated to account created page");
        } catch (Exception e) {
            System.out.println("Failed to navigate to account created page: " + e.getMessage());
            // Check if we're still on the form page
            if (isElementVisible(enterAccountInformationTextLocator)) {
                System.out.println("Still on account information page - creation likely failed");
            }
            throw new RuntimeException("Account creation failed - could not reach success page");
        }
    }

    // NEW METHOD: Check if element is visible
    public boolean isElementVisible(By locator) {
        try {
            // 🔥 FIXED: Now using framework.getDriver()
            return framework.getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void waitForStateFieldToBeClickable() {
        WebDriverWait wait = new WebDriverWait(framework.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(stateLocator));
    }
}