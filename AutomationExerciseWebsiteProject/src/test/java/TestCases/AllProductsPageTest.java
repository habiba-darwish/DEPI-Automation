package TestCases;

import Helper.HelperClass;
import Helper.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AllProductsPage;
import pages.BrandPage;
import pages.HomePage;
import selenuim.SelenuimFramework;

@Epic("Automation Exercise Website")
@Feature("Products Page Functionality")
@Owner("Habiba")
public class AllProductsPageTest {
    SelenuimFramework framework;
    HomePage homePage;
    AllProductsPage allProductsPage;
    BrandPage brandPage;
    private Product[] products;

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        this.products = HelperClass.ReadProducts("Products.json");
    }

    @DataProvider(name = "productData")
    public Object[][] provideProductData() {
        return Arrays.stream(this.products)
                .map(p -> new Object[]{p.getName()})
                .toArray(Object[][]::new);
    }

    @BeforeMethod
    public void beforeMethod() {
        this.framework = new SelenuimFramework();
        this.framework.initializeBrowser();
        this.framework.navigateToURL("https://automationexercise.com/");
        this.homePage = new HomePage(this.framework);
        this.allProductsPage = new AllProductsPage(this.framework);
        this.brandPage = new BrandPage(this.framework);
    }

    @AfterMethod
    public void afterMethod() {
        this.framework.closeBrowser();
    }

    @Test(dataProvider = "productData")
    @Story("Search Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can search for a specific product.")
    public void searchProduct_TC9(String productToSearchFor) {
        String ActualTitle = this.homePage.getHomePageTitle();
        Assert.assertTrue(ActualTitle.contains("Automation Exercise"), "User is not in home page");

        this.homePage.clickProductsButton();

        String ExpectedText = "ALL PRODUCTS";
        String ActualText = this.allProductsPage.getAllProductsText();
        Assert.assertTrue(ActualText.contains(ExpectedText), "User is not in all products page");

        this.allProductsPage.sendSearchedProductName(productToSearchFor);
        this.allProductsPage.clickSearchButton();

        Assert.assertTrue(this.allProductsPage.getSearchedProductsText().contains("SEARCHED PRODUCTS"),
                "'SEARCHED PRODUCTS' text is not visible");

        List<String> actualResults = this.allProductsPage.getSearchResultProducts();
        Assert.assertTrue(actualResults.stream().anyMatch(actual -> actual.equalsIgnoreCase(productToSearchFor)),
                "Expected product not found in search results: " + productToSearchFor);
    }

    @Test
    @Story("Different Brands Accessibility")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that a user can access different brands as a filter from products page and any brand page.")
    public void ViewAndCartBrandProducts_TC19() {
        this.homePage.clickProductsButton();

        this.allProductsPage.scrollToViewBrandsSection();
        Assert.assertTrue(this.allProductsPage.isBrandsSectionVisible(), "Brands Section is not visible");

        this.allProductsPage.clickBrandPOLOButton();
        String ActualFirstBrandPageTitleText = this.brandPage.getBrandPageTitleTextLocator();
        String ExpectedFirstBrandPageTitleText = "BRAND - POLO PRODUCTS";
        Assert.assertTrue(ActualFirstBrandPageTitleText.contains(ExpectedFirstBrandPageTitleText),
                ActualFirstBrandPageTitleText + " Should Have Contained " + ExpectedFirstBrandPageTitleText);
        Assert.assertTrue(this.brandPage.isBrandProductsSectionVisible(), "Brand products Section is not visible");

        this.allProductsPage.scrollToViewBrandsSection();
        this.brandPage.clickBrandHAndMButton();
        String ActualSecondBrandPageTitleText = this.brandPage.getBrandPageTitleTextLocator();
        String ExpectedSecondBrandPageTitleText = "BRAND - H&M PRODUCTS";
        Assert.assertTrue(ActualSecondBrandPageTitleText.contains(ExpectedSecondBrandPageTitleText),
                ActualSecondBrandPageTitleText + " Should Have Contained " + ExpectedSecondBrandPageTitleText);
        Assert.assertTrue(this.brandPage.isBrandProductsSectionVisible(), "Brand products Section is not visible");
    }


}