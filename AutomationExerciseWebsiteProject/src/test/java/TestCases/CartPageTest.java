package TestCases;

import Helper.HelperClass;
import Helper.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.AllProductsPage;
import pages.FirstProductPage;
import selenuim.SelenuimFramework;
import io.qameta.allure.*;

import java.io.FileNotFoundException;
import java.util.List;


@Epic("Automation Exercise Website")
@Feature("Cart Management Functionality")
@Owner("Habiba")
public class CartPageTest {

    SelenuimFramework framework;
    HomePage homePage;
    CartPage cartPage;
    AllProductsPage allProductsPage;
    FirstProductPage firstProductPage;

    @BeforeMethod
    public void beforeMethod() {
        framework = new SelenuimFramework();
        framework.initializeBrowser();
        framework.navigateToURL("https://automationexercise.com/");
        homePage = new HomePage(framework);
        cartPage = new CartPage(framework);
        allProductsPage = new AllProductsPage(framework);
        firstProductPage = new FirstProductPage(framework);
    }

    @AfterMethod
    public void afterMethod() {
        framework.closeBrowser();
    }

@Test
@Story("Verify Products in Cart")
@Severity(SeverityLevel.CRITICAL)
@Description("Validate that added products are correctly displayed in cart")
public void TC12_VerifyProductsInCart() {
    // Step 1-3: Verify home page
    Assert.assertTrue(homePage.getHomePageTitle().contains("Automation Exercise"),
            "Home page should be visible");

    // Step 4: Navigate to products page
    homePage.clickProductsButton();
    Assert.assertTrue(allProductsPage.getAllProductsText().contains("ALL PRODUCTS"),
            "Should be on products page");

    // Get first two products directly from page
    List<WebElement> allProducts = allProductsPage.getAllProductElements();
    Assert.assertTrue(allProducts.size() >= 2, "Should have at least 2 products on page");

    String firstProductName = allProducts.get(0).findElement(By.cssSelector("p")).getText().trim();
    String secondProductName = allProducts.get(1).findElement(By.cssSelector("p")).getText().trim();

    System.out.println("Adding first two products from page:");
    System.out.println("  - " + firstProductName);
    System.out.println("  - " + secondProductName);

    // Add products to cart
    homePage.scrollToViewFirstProductAddToCartButton();
    homePage.clickFirstProductAddToCartButton();
    homePage.clickContinueShoppingButton();
    homePage.clickSecondProductAddToCartButton();
    homePage.clickViewCartButton();

    // Verify cart
    verifyCartHasValidTwoProducts(firstProductName, secondProductName);
}

    private void verifyCartHasValidTwoProducts(String firstProductName, String secondProductName) {
        // Verify cart page
        Assert.assertTrue(cartPage.getShoppingCartText().contains("Cart"), "Should be on cart page");
        Assert.assertEquals(cartPage.getNumberOfItemsInCart(), 2, "Should have 2 products in cart");

        // Get cart data
        List<String> cartNames = cartPage.getCartProductNames();
        List<Double> cartPrices = cartPage.getProductPrices();
        List<Integer> cartQuantities = cartPage.getProductQuantities();
        List<Double> cartTotals = cartPage.getProductTotals();

        System.out.println("Products in cart: " + cartNames);

        // Verify both products are in cart
        Assert.assertTrue(cartNames.contains(firstProductName),
                "First product '" + firstProductName + "' should be in cart");
        Assert.assertTrue(cartNames.contains(secondProductName),
                "Second product '" + secondProductName + "' should be in cart");

        // Verify all cart items have valid data
        for (int i = 0; i < cartNames.size(); i++) {
            Assert.assertTrue(cartPrices.get(i) > 0, "Price should be positive for: " + cartNames.get(i));
            Assert.assertEquals(cartQuantities.get(i), Integer.valueOf(1),
                    "Quantity should be 1 for: " + cartNames.get(i));

            double expectedTotal = cartPrices.get(i) * cartQuantities.get(i);
            Assert.assertEquals(cartTotals.get(i), expectedTotal,
                    "Total should equal price * quantity for: " + cartNames.get(i));
        }
    }


    @Test
    @Story("Verify Product Quantity in Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that product quantity is correctly reflected in cart")
    public void TC13_VerifyProductQuantityInCart() {
        // Navigate to product detail page and add specific quantity
        homePage.clickViewProduct1Button();
        firstProductPage.sendQuantity("4");
        firstProductPage.clickAddToCartButton();
        firstProductPage.clickViewCartButton();

        // Verify the quantity in cart
        List<Integer> quantities = cartPage.getProductQuantities();
        boolean quantityFound = false;
        for (Integer quantity : quantities) {
            if (quantity == 4) {
                quantityFound = true;
                break;
            }
        }
        Assert.assertTrue(quantityFound,
                "Product with quantity 4 not found in cart");
    }


    @Test
    @Story("Remove Products from Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate that user can remove products from cart")
    public void TC17_RemoveProductsFromCart() {
        homePage.clickHomeButton();
        homePage.scrollToViewFirstProductAddToCartButton();
        homePage.clickFirstProductAddToCartButton();
        homePage.clickViewCartButton();

        String cartText = cartPage.getShoppingCartText();
        Assert.assertTrue(cartText.contains("Cart"), "Should be on cart page");

        int initialItemCount = cartPage.getNumberOfItemsInCart();
        List<String> initialProductNames = cartPage.getCartProductNames();

        Assert.assertEquals(initialItemCount, 1, "Should have 1 products in cart");

        String firstProductName = initialProductNames.get(0);

        cartPage.clickXButtonForFirstProductButton();

        cartPage.waitForProductToBeRemoved();

        int finalItemCount = cartPage.getNumberOfItemsInCart();
        List<String> finalProductNames = cartPage.getCartProductNames();

        Assert.assertEquals(finalItemCount, initialItemCount - 1,
                "Item count should decrease by 1 after removal");
        Assert.assertFalse(finalProductNames.contains(firstProductName),
                "Removed product should not be in cart");
        Assert.assertEquals(finalProductNames.size(), 0,
                "Should have 1 product remaining in cart");
    }


    @Test
    @Story("Recommended Items in Cart")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify user can add recommended product to cart.")
    public void AddToCartFromRecommendedItems_TC22() {
        // Navigate to home page
        homePage.clickHomeButton();

        // Scroll to recommended items section
        homePage.scrollToViewRecommendedItemsText();

        // Verify recommended items section is visible
        String recommendedText = homePage.getRecommendedItemsText();
        Assert.assertTrue(recommendedText.contains("RECOMMENDED ITEMS"),
                "Recommended items section should be visible");

        // Scroll to section for better visibility
        homePage.scrollToViewRecommendedItemsSection();

        // Get product name before adding to cart
        String productName = homePage.getRecommendedProduct5Name();

        // Add product to cart
        homePage.clickProduct5InRecommendedProductAddToCartButton();
        homePage.clickViewCartButton();

        // Verify the specific product was added to cart
        List<String> cartProducts = cartPage.getCartProductNames();
        Assert.assertFalse(cartProducts.isEmpty(), "Cart should not be empty");
        Assert.assertEquals(cartProducts.get(0), productName,
                "The product in cart should match the added recommended product");
    }
}
