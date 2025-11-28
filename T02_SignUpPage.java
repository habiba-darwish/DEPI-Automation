package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T02_SignUpPage {
    WebDriver driver;

    public T02_SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    private By UserName = By.xpath("//*[@data-qa='signup-name']");
    private By EmailAddress = By.xpath("//*[@data-qa='signup-email']");
    private By SignUpButton = By.xpath("//*[@data-qa='signup-button']");

    public void insertUserName(String username){
        driver.findElement(UserName).sendKeys(username);
    }

    public void insertEmail(String Email){
        driver.findElement(EmailAddress).sendKeys(Email);
    }

    public void clicklonSignUP(){
        driver.findElement(SignUpButton).click();
    }

    private By gendre = By.id("id_gender2");
    public void ClickOnGendre() {
        driver.findElement(gendre).click();
    }

    private By Password = By.xpath("//*[@data-qa='password']");
    public void InsrtPasswod(String password){
        driver.findElement(Password).sendKeys(password);
    }

    private By FisrtName = By.xpath("//*[@data-qa='first_name']");
    public void InsrtFirstName(String fisrtName) {
        driver.findElement(FisrtName).sendKeys(fisrtName);
    }

    private By LastName = By.xpath("//*[@data-qa='last_name']");
    public void InsrtLastName(String lastName){
        driver.findElement(LastName).sendKeys(lastName);
    }

    private By Address1 = By.xpath("//*[@data-qa='address']");
    public void InsrtAddress1(String add1) {
        driver.findElement(Address1).sendKeys(add1);
    }

    private By Address2 = By.xpath("//*[@data-qa='address2']");
    public void InsrtAddress2(String add2) {
        driver.findElement(Address2).sendKeys(add2);
    }

    private By Country = By.xpath("//*[@data-qa='country']");
    private By NewZealand = By.xpath("//*[@value='New Zealand']");
    public void ChooseCountry() {
        driver.findElement(Country).click();
        driver.findElement(NewZealand).click();
    }

    private By State = By.xpath("//*[@data-qa='state']");
    public void InsertState(String state) {
        driver.findElement(State).sendKeys(state);
    }

    private By City = By.xpath("//*[@data-qa='city']");
    public void InsrtCity(String city) {
        driver.findElement(City).sendKeys(city);
    }

    private By ZipCode = By.xpath("//*[@data-qa='zipcode']");
    public void InsrtZipCode(String Zipcode) {
        driver.findElement(ZipCode).sendKeys(Zipcode);
    }

    private By PhoneNumber = By.xpath("//*[@data-qa='mobile_number']");
    public void InsrtPhoneNumber(String phoneNumber) {
        driver.findElement(PhoneNumber).sendKeys(phoneNumber);
    }

    private By CreateAccount = By.xpath("//*[@data-qa='create-account']");
    public void clicklonCreateAccount() {
        driver.findElement(CreateAccount).click();
    }

    private By ContinueBotton = By.xpath("//*[@data-qa='continue-button']");
    public T01_HomePage ClickOncONcontinueBotton() {
        driver.findElement(ContinueBotton).click();
        return new T01_HomePage(driver);
    }
}
