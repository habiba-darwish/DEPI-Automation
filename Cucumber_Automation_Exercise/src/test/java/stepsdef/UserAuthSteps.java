package stepsdef;

import io.cucumber.java.en.*;
import org.testng.Assert;
import Helper.*;
import pages.*;

public class UserAuthSteps {

    private FullUserData newUser;
    private RegisteredUser existingUser;
    private User invalidCredentials;

    public UserAuthSteps() {
        // No inheritance, no super()
    }

    @Given("The user is on the Home Page")
    public void the_user_is_on_the_home_page() {
        TestContext.getFramework().navigateToURL("https://automationexercise.com/");
        String actualTitle = TestContext.getHomePage().getHomePageTitle();
        Assert.assertTrue(actualTitle.contains("Automation Exercise"));
    }

    @When("User clicks {string} button after account creation")
    public void user_clicks_continue_button_after_account_creation(String buttonName) {
        if (buttonName.equals("Continue")) {
            TestContext.getSignupPage().clickContinueButton();
        }
    }

    @When("User clicks {string} button")
    public void user_clicks_button(String buttonName) {
        if (buttonName.equals("Signup / Login")) {
            TestContext.getHomePage().clickSignupLoginButton();
        } else if (buttonName.equals("Signup")) {
            TestContext.getSignupLoginPage().clickSignupButton();
        } else if (buttonName.equals("Logout")) {
            TestContext.getHomePage().clicklogoutButton();
        } else if (buttonName.equals("Delete Account")) {
            TestContext.getHomePage().clickDeleteAccountButton();
        }
    }

    @Then("User sees {string} confirmation page")
    public void user_sees_account_created_confirmation_page(String expectedText) {
        // The wait is now robustly handled inside the page object using the correct locator
        TestContext.getSignupPage().waitForAccountCreatedPage();
        String actualText = TestContext.getSignupPage().getAccountCreatedText();
        Assert.assertEquals(actualText.trim(), expectedText, "Account creation confirmation message mismatch.");
    }

    @And("User provides new name and email for registration from {string}")
    public void user_provides_new_name_and_email_for_registration(String fileName) throws Exception {
        newUser = HelperClass.ReadFullUser(fileName)[0];
        // Ensure email is unique using a timestamp
        String uniqueEmail = "syada" + System.currentTimeMillis() + "@test.com";

        TestContext.getSignupLoginPage().sendSignupName(newUser.firstName + " " + newUser.lastName);
        TestContext.getSignupLoginPage().sendSignupEmail(uniqueEmail);

        newUser.email = uniqueEmail;
    }

    @Then("User is navigated to {string} page")
    public void user_is_navigated_to_page(String expectedText) {
        String actualText = TestContext.getSignupPage().getEnterAccountInformationText();
        Assert.assertEquals(actualText, expectedText);
    }


    @When("User fills mandatory account details from {string} and clicks Create Account")
    public void user_fills_mandatory_account_details_from_and_clicks_create_account(String fileName) {
        SignupPage signupPage = TestContext.getSignupPage();

        // Fill all required fields
        signupPage.selectSignupRadioButtonTitleMr();
        signupPage.sendPassword(newUser.password);
        signupPage.selectDateOfBirthDay(newUser.day);
        signupPage.selectDateOfBirthMonth(newUser.month);
        signupPage.selectDateOfBirthYear(newUser.year);
        signupPage.checkSignupForNewsletterCheckBox();
        signupPage.sendFirstName(newUser.firstName);
        signupPage.sendLastName(newUser.lastName);
        signupPage.sendCompany("Test Company");
        signupPage.sendAddress(newUser.address);
        signupPage.sendAddress2("Apt 1");
        signupPage.selectCountry(newUser.country);
//        signupPage.waitForStateFieldToBeClickable();
        signupPage.sendState(newUser.state);
        signupPage.sendCity(newUser.city);
        signupPage.sendZipcode(newUser.zipcode);
        signupPage.sendMobileNumber(newUser.mobileNumber);


        // Scroll and click
        signupPage.scrollToCreateAccountButton();
        signupPage.waitHelper();

        signupPage.clickCreateAccountButton();

    }

    @Then("User is successfully logged in as the new user from {string}")
    public void user_is_successfully_logged_in_as_the_new_user_from(String fileName) {
        String expectedUserName = "Logged in as " + newUser.firstName + " " + newUser.lastName;
        Assert.assertTrue(TestContext.getHomePage().getLoggedInText().contains(expectedUserName));
    }

    @Then("User sees {string} confirmation message")
    public void user_sees_confirmation_message(String expectedMessage) {
        Assert.assertTrue(TestContext.getHomePage().getDeletedAccountText().contains(expectedMessage));
    }

    @And("User provides existing user data from {string} for registration")
    public void user_provides_existing_user_data_from_for_registration(String fileName) throws Exception {
        existingUser = HelperClass.ReadRegisteredUsers(fileName)[0];
        TestContext.getSignupLoginPage().sendSignupName(existingUser.name);
        TestContext.getSignupLoginPage().sendSignupEmail(existingUser.email);
    }

    @Then("User sees the error message {string}")
    public void user_sees_the_error_message(String expectedErrorMessage) {
        String actualErrorMessage;

        if (expectedErrorMessage.contains("Email Address already exist!")) {
            actualErrorMessage = TestContext.getSignupLoginPage().getSignupErrorMessageText();
        } else if (expectedErrorMessage.contains("Your email or password is incorrect!")) {
            actualErrorMessage = TestContext.getSignupLoginPage().getIncorrectLoginText();
        } else {
            throw new IllegalArgumentException("Unknown error message expected: " + expectedErrorMessage);
        }

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Expected error: '" + expectedErrorMessage + "' but found: '" + actualErrorMessage + "'");
    }

    @And("User logs in with valid credentials from {string}")
    public void user_logs_in_with_valid_credentials_from(String fileName) throws Exception {
        existingUser = HelperClass.ReadRegisteredUsers(fileName)[0];
        TestContext.getSignupLoginPage().sendLoginEmail(existingUser.email);
        TestContext.getSignupLoginPage().sendLoginPassword(existingUser.password);
        TestContext.getSignupLoginPage().clickLoginButton();
    }

    @Then("User is successfully logged in as the existing user from {string}")
    public void user_is_successfully_logged_in_as_the_existing_user_from(String fileName) {
        String expectedUserName = "Logged in as " + existingUser.name;
        Assert.assertTrue(TestContext.getHomePage().getLoggedInText().contains(expectedUserName));
    }

    @Given("User is already logged in with valid credentials from {string}")
    public void user_is_already_logged_in_with_valid_credentials_from(String fileName) throws Exception {
        the_user_is_on_the_home_page();
        user_clicks_button("Signup / Login");
        user_logs_in_with_valid_credentials_from(fileName);
        user_is_successfully_logged_in_as_the_existing_user_from(fileName);
    }

    @Then("User is redirected to the Login page")
    public void user_is_redirected_to_the_login_page() {
        String actualText = TestContext.getSignupLoginPage().getLoginToYourAccountText();
        Assert.assertTrue(actualText.contains("Login to your account"));
    }

    @And("User attempts to log in with invalid credentials from {string}")
    public void user_attempts_to_log_in_with_invalid_credentials_from(String fileName) throws Exception {
        invalidCredentials = HelperClass.ReadUsers(fileName)[0];
        TestContext.getSignupLoginPage().sendLoginEmail(invalidCredentials.email);
        TestContext.getSignupLoginPage().sendLoginPassword(invalidCredentials.password);
        TestContext.getSignupLoginPage().clickLoginButton();
    }
}