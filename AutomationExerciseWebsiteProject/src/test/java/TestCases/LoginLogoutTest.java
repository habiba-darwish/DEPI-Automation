package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenuim.SelenuimFramework;
import pages.HomePage;
import pages.SignupLoginPage;
import Helper.HelperClass;
import Helper.RegisteredUser;
import Helper.User;
import io.qameta.allure.*;

/**
 * Test cases for User Login and Logout functionality (TC2, TC3, TC4).
 */
@Epic("User Authentication & Profile Management")
@Feature("Login and Logout")
@Owner("Syada")
public class LoginLogoutTest {

    private SelenuimFramework framework;
    private final String BASE_URL = "https://automationexercise.com/";

    private RegisteredUser validUser;
    private User invalidUser;


    public LoginLogoutTest() {}

    @BeforeMethod
    public void setup() throws Exception {

        framework = new SelenuimFramework();


        framework.initializeBrowser();
        framework.navigateToURL(BASE_URL);


        validUser = HelperClass.ReadRegisteredUsers("RegisteredUser.json")[0];
        invalidUser = HelperClass.ReadUsers("InvalidLoginCredentials.json")[0];
    }

    @AfterMethod
    public void teardown() {
        framework.closeBrowser();
    }

    // --- TC2: Login User with correct email and password ---
    @Test(priority = 1, description = "TC2: Verify successful login with valid credentials")
    @Story("Valid Login")
    @Severity(SeverityLevel.BLOCKER)
    public void TC2_loginUserWithCorrectCredentials() {

        HomePage homePage = new HomePage(framework);
        SignupLoginPage signupLoginPage = new SignupLoginPage(framework);

        homePage.clickSignupLoginButton();

        Assert.assertEquals(signupLoginPage.getLoginToYourAccountText(), "Login to your account",
                "Not on Login Page.");

        signupLoginPage.sendLoginEmail(validUser.email);
        signupLoginPage.sendLoginPassword(validUser.password);
        signupLoginPage.clickLoginButton();

        Assert.assertTrue(homePage.getLoggedInText().contains("Logged in as " + validUser.name),
                "Login failed or 'Logged in as' text is missing in header.");
    }

    // --- TC3: Login User with incorrect email and password ---
    @Test(priority = 2, description = "TC3: Verify failure to login with incorrect credentials")
    @Story("Invalid Login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC3_loginUserWithIncorrectCredentials() {

        HomePage homePage = new HomePage(framework);
        SignupLoginPage signupLoginPage = new SignupLoginPage(framework);

        homePage.clickSignupLoginButton();

        signupLoginPage.sendLoginEmail(invalidUser.email);
        signupLoginPage.sendLoginPassword(invalidUser.password);
        signupLoginPage.clickLoginButton();


        String expectedErrorMessage = "Your email or password is incorrect!";
        Assert.assertTrue(signupLoginPage.getIncorrectLoginText().contains(expectedErrorMessage),
                "Error message for invalid login is not displayed or incorrect.");
    }

    // --- TC4: Logout User ---
    @Test(priority = 3, description = "TC4: Verify successful user logout")
    @Story("Logout")
    @Severity(SeverityLevel.CRITICAL)
    public void TC4_logoutUser() {

        HomePage homePage = new HomePage(framework);
        SignupLoginPage signupLoginPage = new SignupLoginPage(framework);
        homePage.clickSignupLoginButton();


        signupLoginPage.sendLoginEmail(validUser.email);
        signupLoginPage.sendLoginPassword(validUser.password);
        signupLoginPage.clickLoginButton();

        Assert.assertTrue(homePage.getLoggedInText().contains(validUser.name));

        homePage.clicklogoutButton();

        Assert.assertTrue(signupLoginPage.getLoginToYourAccountText()
                        .contains("Login to your account"),
                "Logout failed.");
    }
}