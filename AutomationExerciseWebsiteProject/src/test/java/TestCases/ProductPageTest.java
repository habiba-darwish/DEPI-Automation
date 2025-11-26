package TestCases;

import Helper.HelperClass;
import Helper.Reviews;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.FileNotFoundException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AllProductsPage;
import pages.FirstProductPage;
import pages.HomePage;
import selenuim.SelenuimFramework;

@Epic("Automation Exercise Website")
@Feature("Product Page Data & Reviews")
@Owner("Habiba")
public class ProductPageTest {
    static Reviews[] ListOfReviews;
    SelenuimFramework framework;
    HomePage homePage;
    AllProductsPage allProductsPage;
    FirstProductPage firstProductPage;

    @BeforeMethod
    public void beforeMethod() {
        this.framework = new SelenuimFramework();
        this.framework.initializeBrowser();
        this.framework.navigateToURL("https://automationexercise.com/");
        this.homePage = new HomePage(this.framework);
        this.allProductsPage = new AllProductsPage(this.framework);
        this.firstProductPage = new FirstProductPage(this.framework);
    }

    @AfterMethod
    public void afterMethod() {
        this.framework.closeBrowser();
    }

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        ListOfReviews = HelperClass.ReadReviews("Review.json");
        System.out.println("Number of reviews loaded: " + ListOfReviews.length);
    }

    @DataProvider(name = "reviewsData")
    public Reviews[] reviewsDataProvider() {
        return ListOfReviews;
    }


    @Test
    @Story("Product Page Details Verification")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can view product details page with all details visible.")
    public void verifyProductsAndProductDetailPage_TC8() {
        String ActualTitle = this.homePage.getHomePageTitle();
        Assert.assertTrue(ActualTitle.contains("Automation Exercise"), "User is not in home page");

        this.homePage.clickProductsButton();
        String ExpectedText = "ALL PRODUCTS";
        String ActualText = this.allProductsPage.getAllProductsText();
        Assert.assertTrue(ActualText.contains(ExpectedText), "User is not in all products page");

        List<WebElement> products = this.allProductsPage.getAllProductElements();
        Assert.assertFalse(products.isEmpty(), "Products list is empty");

        for(WebElement product : products) {
            Assert.assertTrue(product.isDisplayed(), "Product is not visible: " + product.getText());
        }

        this.allProductsPage.scrollToView();
        this.allProductsPage.clickViewProductButton();

        Assert.assertTrue(this.firstProductPage.getProductName(), "Product name is not visible");
        Assert.assertTrue(this.firstProductPage.getProductPrice(), "Product price is not visible");
        Assert.assertTrue(this.firstProductPage.getProductCategory(), "Product category is not visible");
        Assert.assertTrue(this.firstProductPage.getProductBrand(), "Product brand is not visible");
        Assert.assertTrue(this.firstProductPage.getProductAvailability(), "Product availability is not visible");
        Assert.assertTrue(this.firstProductPage.getProductCondition(), "Product condition is not visible");
    }


}