package stepsdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import Helper.HelperClass;
import Helper.FullUserData;
import Helper.RegisteredUser;
import Helper.User;
import pages.HomePage;
import pages.SignupLoginPage;
import pages.SignupPage;
/**
 * Step Definitions for User Authentication and Account Management (TC1-TC5).
 */
public class UserAuthSteps extends BaseSteps {

    private FullUserData newUser;
    private RegisteredUser existingUser;
    private User invalidCredentials;

    // Constructor required by Cucumber
    public UserAuthSteps(TestContext context) {
        super(context);
    }


    @Given("The user is on the Home Page")
    public void user_is_on_the_home_page() {
        context.getFramework().navigateToURL("https://automationexercise.com/");
        String actualTitle = context.getHomePage().getPageTitle();
        Assert.assertTrue(actualTitle.contains("Automation Exercise"), "Navigation to Home Page failed.");
    }

    @When("User clicks {string} button")
    public void user_clicks_button(String buttonName) {
        HomePage homePage = context.getHomePage();
        SignupLoginPage signupLoginPage = context.getSignupLoginPage();

        if (buttonName.equals("Signup / Login")) {
            homePage.clickSignupLoginButton();
        } else if (buttonName.equals("Signup")) {
            signupLoginPage.clickSignupButton();
        } else if (buttonName.equals("Logout")) {
            homePage.clicklogoutButton();
        } else if (buttonName.equals("Delete Account")) {
            homePage.clickDeleteAccountButton();
        }
    }

    // --- DATA LOADING STEPS ---

    @When("User provides new name and email for registration from {string}")
    public void user_provides_new_name_and_email_for_registration(String fileName) throws Exception {
        newUser = HelperClass.ReadFullUser(fileName)[0];

        String uniqueEmail = "syada" + System.currentTimeMillis() + "@test.com";

        context.getSignupLoginPage().sendSignupName(newUser.firstName + " " + newUser.lastName);
        context.getSignupLoginPage().sendSignupEmail(uniqueEmail);

        newUser.email = uniqueEmail;
    }

    @When("User provides existing user data from {string} for registration")
    public void user_provides_existing_user_data_for_registration(String fileName) throws Exception {
        existingUser = HelperClass.ReadRegisteredUsers(fileName)[0];
        context.getSignupLoginPage().sendSignupName(existingUser.name);
        context.getSignupLoginPage().sendSignupEmail(existingUser.email);
    }

    // --- REGISTRATION ACTIONS (TC1) ---

    @Then("User is navigated to {string} page")
    public void user_is_navigated_to_page(String expectedText) {
        SignupPage signupPage = context.getSignupPage();
        String actualText = signupPage.getEnterAccountInformationText();
        Assert.assertEquals(actualText, expectedText, "Navigation to Account Info page failed.");
    }

    @When("User fills mandatory account details from {string} and clicks Create Account")
    public void user_fills_mandatory_account_details_and_clicks_create_account(String fileName) {
        SignupPage signupPage = context.getSignupPage();

        signupPage.selectSignupRadioButtonTitleMr();
        signupPage.sendPassword(newUser.password);
        signupPage.selectDateOfBirthDay(newUser.day);
        signupPage.selectDateOfBirthMonth(newUser.month);
        signupPage.selectDateOfBirthYear(newUser.year);

        signupPage.sendFirstName(newUser.firstName);
        signupPage.sendLastName(newUser.lastName);
        signupPage.sendAddress(newUser.address);
        signupPage.selectCountry(newUser.country);
        signupPage.sendMobileNumber(newUser.mobileNumber);

        signupPage.clickCreateAccountButton();
    }

    // --- LOGIN VALID/INVALID STEPS (TC2, TC3) ---

    @When("User logs in with valid credentials from {string}")
    public void user_logs_in_with_valid_credentials_from(String fileName) throws Exception {
        existingUser = HelperClass.ReadRegisteredUsers(fileName)[0];
        context.getSignupLoginPage().sendLoginEmail(existingUser.email);
        context.getSignupLoginPage().sendLoginPassword(existingUser.password);
        context.getSignupLoginPage().clickLoginButton();
    }

    @When("User attempts to log in with invalid credentials from {string}")
    public void user_attempts_to_log_in_with_invalid_credentials_from(String fileName) throws Exception {
        invalidCredentials = HelperClass.ReadUsers(fileName)[0];
        context.getSignupLoginPage().sendLoginEmail(invalidCredentials.email);
        context.getSignupLoginPage().sendLoginPassword(invalidCredentials.password);
        context.getSignupLoginPage().clickLoginButton();
    }

    // --- VERIFICATION STEPS ---

    @Then("User is successfully logged in as the new user from {string}")
    public void user_is_successfully_logged_in_as_new_user(String fileName) {
        HomePage homePage = context.getHomePage();
        String expectedUserName = "Logged in as " + newUser.firstName;
        Assert.assertTrue(homePage.getLoggedInText().contains(expectedUserName),
                "TC1: User was not logged in after registration.");
    }

    @Then("User is successfully logged in as the existing user from {string}")
    public void user_is_successfully_logged_in_as_existing_user(String fileName) {
        HomePage homePage = context.getHomePage();
        String expectedUserName = "Logged in as " + existingUser.name;
        Assert.assertTrue(homePage.getLoggedInText().contains(expectedUserName),
                "TC2: User was not logged in.");
    }

    @Then("User is redirected to the Login page")
    public void user_is_redirected_to_the_login_page() {
        SignupLoginPage signupLoginPage = context.getSignupLoginPage();
        String actualText = signupLoginPage.getLoginToYourAccountText();
        Assert.assertTrue(actualText.contains("Login to your account"), "User was not redirected after logout.");
    }

    @Then("User sees the error message {string}")
    public void user_sees_the_error_message(String expectedErrorMessage) {
        SignupLoginPage signupLoginPage = context.getSignupLoginPage();
        String actualErrorMessage = signupLoginPage.getSignupErrorMessageText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Error message mismatch.");
    }

    @Then("User sees {string} confirmation message")
    public void user_sees_confirmation_message(String expectedMessage) {
        HomePage homePage = context.getHomePage();
        String actualMessage = homePage.getDeletedAccountText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Account deletion confirmation failed.");
    }

    // --- PRE-CONDITIONS (For TC4) ---

    @Given("User is already logged in with valid credentials from {string}")
    public void user_is_already_logged_in_with_valid_credentials_from(String fileName) throws Exception {
        user_is_on_the_home_page();
        user_clicks_button("Signup / Login");
        user_logs_in_with_valid_credentials_from(fileName);
        user_is_successfully_logged_in_as_existing_user(fileName);
    }
}