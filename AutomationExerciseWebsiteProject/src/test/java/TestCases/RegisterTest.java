package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenuim.SelenuimFramework;
import pages.HomePage;
import pages.SignupLoginPage;
import pages.SignupPage;
import Helper.HelperClass;
import Helper.FullUserData;
import Helper.RegisteredUser;
import io.qameta.allure.*;
/**
 * Test cases for User Registration functionality (TC1, TC5).
 */
@Epic("User Authentication & Profile Management")
@Feature("User Registration")
@Owner("Syada")
public class RegisterTest {

    private SelenuimFramework framework;
    private final String BASE_URL = "https://automationexercise.com/";


    private FullUserData newUser;
    private RegisteredUser existingUser;


    public RegisterTest() {}

    @BeforeMethod
    public void setup() throws Exception {

        framework = new SelenuimFramework();
        framework.initializeBrowser();
        framework.navigateToURL(BASE_URL);


        newUser = HelperClass.ReadFullUser("FullUserData.json")[0];
        existingUser = HelperClass.ReadRegisteredUsers("RegisteredUser.json")[0];
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }

    // --- TC1: Register User ---
    @Test(priority = 1, description = "TC1: Verify successful user registration")
    @Story("New User Registration")
    @Severity(SeverityLevel.BLOCKER)
    public void TC1_registerNewUser() {

        HomePage homePage = new HomePage(framework);
        SignupLoginPage signupLoginPage = new SignupLoginPage(framework);
        SignupPage signupPage = new SignupPage(framework);

        homePage.clickSignupLoginButton();


        signupLoginPage.sendSignupName(newUser.firstName + " " + newUser.lastName);
        String uniqueEmail = "john" + System.currentTimeMillis() + "@gmail.com";
        signupLoginPage.sendSignupEmail(uniqueEmail);

        signupLoginPage.clickSignupButton();

        Assert.assertEquals(signupPage.getEnterAccountInformationText(), "ENTER ACCOUNT INFORMATION",
                "Did not navigate to account information page.");

        signupPage.selectSignupRadioButtonTitleMr();
        signupPage.sendPassword(newUser.password);

        signupPage.selectDateOfBirthDay("15");
        signupPage.selectDateOfBirthMonth("May");
        signupPage.selectDateOfBirthYear("1990");


        signupPage.sendFirstName(newUser.firstName);
        signupPage.sendLastName(newUser.lastName);
        signupPage.sendCompany(newUser.company);
        signupPage.sendAddress(newUser.address);
        signupPage.selectCountry(newUser.country);
        signupPage.sendState(newUser.state);
        signupPage.sendCity(newUser.city);
        signupPage.sendZipcode(newUser.zipcode);
        signupPage.sendMobileNumber(newUser.mobileNumber);

        signupPage.clickCreateAccountButton();

        Assert.assertEquals(signupPage.getAccountCreatedText(), "ACCOUNT CREATED!",
                "Account creation failed.");

        signupPage.clickContinueButton();
        Assert.assertTrue(homePage.getLoggedInText().contains("Logged in as " + newUser.firstName),
                "User is not logged in after registration.");
    }

    // --- TC5: Register User with existing email ---
    @Test(priority = 2, description = "TC5: Verify registration fails with an email that already exists")
    @Story("Existing Email Validation")
    @Severity(SeverityLevel.CRITICAL)
    public void TC5_registerUserWithExistingEmail() {
        HomePage homePage = new HomePage(framework);
        SignupLoginPage signupLoginPage = new SignupLoginPage(framework);

        homePage.clickSignupLoginButton();

        signupLoginPage.sendSignupName("Dummy Name");
        signupLoginPage.sendSignupEmail(existingUser.email);
        signupLoginPage.clickSignupButton();
        String expectedErrorMessage = "Email Address already exist!";
        Assert.assertEquals(signupLoginPage.getSignupErrorMessageText(), expectedErrorMessage,
                "Error message for existing email is not displayed or incorrect.");
    }
}