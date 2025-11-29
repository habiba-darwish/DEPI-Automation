Feature: User Authentication and Account Management
  @authentication
  Scenario: TC1 - Successful User Registration
    Given The user is on the Home Page
    When User clicks "Signup / Login" button
    And User provides new name and email for registration from "FullUserData.json"
    And User clicks "Signup" button
    Then User is navigated to "ENTER ACCOUNT INFORMATION" page
    When User fills mandatory account details from "FullUserData.json" and clicks Create Account
    Then User is successfully logged in as the new user from "FullUserData.json"
    And User clicks "Delete Account" button
    Then User sees "ACCOUNT DELETED!" confirmation message

  Scenario: TC5 - Register User with Existing Email
    Given The user is on the Home Page
    When User clicks "Signup / Login" button
    And User provides existing user data from "RegisteredUser.json" for registration
    And User clicks "Signup" button
    Then User sees the error message "Email Address already exist!"

  Scenario: TC2 - Successful Login with Valid Credentials
    Given The user is on the Home Page
    When User clicks "Signup / Login" button
    And User logs in with valid credentials from "RegisteredUser.json"
    Then User is successfully logged in as the existing user from "RegisteredUser.json"

  Scenario: TC4 - Logout User
    Given User is already logged in with valid credentials from "RegisteredUser.json"
    When User clicks "Logout" button
    Then User is redirected to the Login page

  Scenario: TC3 - Login with Invalid Credentials
    Given The user is on the Home Page
    When User clicks "Signup / Login" button
    And User attempts to log in with invalid credentials from "InvalidLoginCredentials.json"
    Then User sees the error message "Your email or password is incorrect!"