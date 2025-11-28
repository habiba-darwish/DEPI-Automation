package TestCases;

import BaseClass.Base;
import Pages.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCases extends Base {
    T01_HomePage homePage;
    T02_SignUpPage signUpPage;
    T03_CartPage cartPage;
    T04_ProceedToCheckout proceed;
    T05_AdressDetails address;
    T06_PlaceOrder placeOrder;
    T08_DownloadInvoice downloadInvoice;

    @BeforeClass
    public void setupPages() {
        homePage = new T01_HomePage(driver);
    }

    @Test(priority = 1)
    public void registerNewUser() {
        T02_SignUpPage signUpPage = homePage.ClickOnSignupbutton();

        signUpPage.insertUserName("Menna Yasser");
        signUpPage.insertEmail("menna200@gmail.com");
        signUpPage.clicklonSignUP();

        signUpPage.ClickOnGendre();
        signUpPage.InsrtPasswod("1234@psword");
        signUpPage.InsrtFirstName("Menna");
        signUpPage.InsrtLastName("Yasser");
        signUpPage.InsrtAddress1("old Cairo");
        signUpPage.InsrtAddress2("Maadi");
        signUpPage.InsertState("Egypt");
        signUpPage.ChooseCountry();
        signUpPage.InsrtCity("Cairo");
        signUpPage.InsrtZipCode("909");
        signUpPage.InsrtPhoneNumber("01756768989");

        signUpPage.clicklonCreateAccount();
        signUpPage.ClickOncONcontinueBotton();
    }

    @Test( dependsOnMethods = "registerNewUser")
    public void addItemToCart() {
        cartPage = homePage.addSleevelessDressToCart();
    }

    @Test( dependsOnMethods = "addItemToCart")
    public void proceedToShippingAddress() {
        proceed = cartPage.ClickonCartPage();
        address = proceed.ClickonProceedToCheckout();
    }

    @Test( dependsOnMethods = "proceedToShippingAddress")
    public void placeOrderAndDownloadInvoice() {
        placeOrder = address.getAdressdetails();

        placeOrder.ClickOnPlaceOrderButton();
        placeOrder.InsertNameOnCard();
        placeOrder.InsertCardNumber();
        placeOrder.InsertCVC();
        placeOrder.InsertExpirationMonth();
        placeOrder.InsertExpirationYear();

        downloadInvoice = placeOrder.ClickonPaYandConfirm();
        downloadInvoice.ClickONDownloadInvoice();
    }
}
