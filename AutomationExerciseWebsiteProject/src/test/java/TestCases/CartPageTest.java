package TestCases;

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
        // Add products to cart first
        homePage.clickHomeButton();
        homePage.scrollToViewFirstProductAddToCartButton();
        homePage.clickFirstProductAddToCartButton();
        homePage.clickContinueShoppingButton();
        homePage.clickSecondProductAddToCartButton();
        homePage.clickViewCartButton();

        // Verify cart page is displayed
        String cartText = cartPage.getShoppingCartText();
        Assert.assertTrue(cartText.contains("Shopping Cart"),
                "Cart page not displayed");

        // Verify both products are in cart
        int numberOfItems = cartPage.getNumberOfItemsInCart();
        Assert.assertEquals(numberOfItems, 2,
                "Number of items in cart doesn't match");

        // Verify product details
        List<String> productNames = cartPage.getCartProductNames();
        Assert.assertTrue(productNames.size() >= 2,
                "Not enough products in cart");
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
        try {
            // Ensure cart has products first - add a product directly
            homePage.clickHomeButton();
            homePage.scrollToViewFirstProductAddToCartButton();
            homePage.clickFirstProductAddToCartButton();
            homePage.clickViewCartButton();

            // Get initial number of items
            int initialItemCount = cartPage.getNumberOfItemsInCart();
            System.out.println("Initial items in cart: " + initialItemCount);

            if (initialItemCount == 0) {
                // If cart is empty, add a product first
                homePage.clickHomeButton();
                homePage.scrollToViewFirstProductAddToCartButton();
                homePage.clickFirstProductAddToCartButton();
                homePage.clickViewCartButton();
                initialItemCount = cartPage.getNumberOfItemsInCart();
            }

            Assert.assertTrue(initialItemCount > 0,
                    "No products in cart to remove. Initial count: " + initialItemCount);

            // Store product names before removal
            List<String> initialProductNames = cartPage.getCartProductNames();
            System.out.println("Products before removal: " + initialProductNames);

            // Click 'X' button to remove first product
            cartPage.clickXButtonForFirstProductButton();

            // Wait for product to be removed with explicit wait
            try {
                Thread.sleep(2000); // Wait for removal to process
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Refresh page to ensure cart is updated
            framework.refreshPage();

            // Verify that product is removed from the cart
            int finalItemCount = cartPage.getNumberOfItemsInCart();
            System.out.println("Final items in cart: " + finalItemCount);

            // Allow for the possibility that there might be other items in cart
            Assert.assertTrue(finalItemCount < initialItemCount,
                    "Product was not removed from cart. Initial: " + initialItemCount + ", Final: " + finalItemCount);

        } catch (Exception e) {
            Assert.fail("Test TC17 failed with exception: " + e.getMessage());
        }
    }


    @Test
    @Story("Recommended Items in Cart")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that a user can add a product from the 'Recommended Items' section to the cart and that it is displayed correctly.")
    public void AddToCartFromRecommendedItems_TC22() {
        try {

            this.homePage.clickHomeButton();


            this.homePage.scrollToViewRecommendedItemsText();


            String ExpectedRecommendedItemsText = "RECOMMENDED ITEMS";
            String ActualRecommendedItemsText = this.homePage.getRecommendedItemsText();
            Assert.assertTrue(ActualRecommendedItemsText.contains(ExpectedRecommendedItemsText),
                    ActualRecommendedItemsText + " Should Have Contained " + ExpectedRecommendedItemsText);


            debugRecommendedItemsDetails();


            this.homePage.scrollToViewRecommendedItemsSection();


            String productName = getRecommendedProduct5Name();
            Assert.assertNotNull(productName, "Could not find product name for recommended product 5");
            System.out.println("Product name to be added: " + productName);

            this.homePage.clickProduct5InRecommendedProductAddToCartButton();

            handleCartModalAfterAdd();

            this.homePage.clickViewCartButton();

            Thread.sleep(3000);

            List<String> names = this.cartPage.getCartProductNames();
            System.out.println("Products in cart: " + names);

            Assert.assertFalse(names.isEmpty(), "Cart is empty");
            Assert.assertEquals(names.get(0), productName, "Recommended item is not in cart");

            System.out.println("SUCCESS: Recommended product '" + productName + "' was added to cart successfully!");

        } catch (Exception e) {
            Assert.fail("Test TC22 failed with exception: " + e.getMessage());
        }
    }

    private String getRecommendedProduct5Name() {
        try {
            By product5NameLocator = By.xpath("//div[@class='recommended_items']//a[@data-product-id='5']/ancestor::div[contains(@class,'productinfo')]//p");
            WebElement productNameElement = framework.findElement(product5NameLocator);
            return productNameElement.getText().trim();
        } catch (Exception e) {
            System.out.println("Could not find product name using precise locator: " + e.getMessage());

            try {
                By anyProductNameLocator = By.cssSelector("div.recommended_items div.productinfo p");
                List<WebElement> productNames = framework.findElements(anyProductNameLocator);
                if (!productNames.isEmpty()) {
                    System.out.println("Found " + productNames.size() + " product names in recommended items");
                    for (int i = 0; i < productNames.size(); i++) {
                        System.out.println("Product " + i + ": " + productNames.get(i).getText());
                    }
                    return productNames.get(0).getText().trim(); // Return first product name
                }
            } catch (Exception e2) {
                System.out.println("Fallback also failed: " + e2.getMessage());
            }

            return "Recommended Product"; // Default fallback
        }
    }

    private void handleCartModalAfterAdd() {
        try {
            System.out.println("Handling cart modal after adding product...");

            Thread.sleep(2000);

            By viewCartModalButton = By.cssSelector("div.modal-dialog a[href='/view_cart']");
            By continueShoppingButton = By.cssSelector("div.modal-dialog button.btn-success");

            try {
                WebElement viewCartBtn = framework.findElement(viewCartModalButton);
                if (viewCartBtn.isDisplayed()) {
                    System.out.println("Found View Cart button in modal, clicking it");
                    framework.executeJavaScript("arguments[0].click();", viewCartBtn);
                    return;
                }
            } catch (Exception e) {
                System.out.println("View Cart button not found in modal: " + e.getMessage());
            }

            try {
                WebElement continueBtn = framework.findElement(continueShoppingButton);
                if (continueBtn.isDisplayed()) {
                    System.out.println("Found Continue Shopping button, clicking it");
                    framework.executeJavaScript("arguments[0].click();", continueBtn);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("Continue Shopping button also not found: " + e.getMessage());
            }

            Thread.sleep(2000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void debugRecommendedItemsDetails() {
        try {
            System.out.println("=== DEBUG RECOMMENDED ITEMS ===");

            By recommendedProducts = By.cssSelector("div.recommended_items div.productinfo");
            List<WebElement> products = framework.findElements(recommendedProducts);
            System.out.println("Total recommended products found: " + products.size());

            for (int i = 0; i < products.size(); i++) {
                WebElement product = products.get(i);
                try {
                    WebElement nameElement = product.findElement(By.cssSelector("p"));
                    String productName = nameElement.getText();

                    WebElement addToCartBtn = product.findElement(By.cssSelector("a.add-to-cart"));
                    String productId = addToCartBtn.getAttribute("data-product-id");
                    String buttonText = addToCartBtn.getText();

                    System.out.println("Product " + i + ":");
                    System.out.println("  Name: " + productName);
                    System.out.println("  Product ID: " + productId);
                    System.out.println("  Button Text: " + buttonText);
                    System.out.println("  Displayed: " + addToCartBtn.isDisplayed());
                    System.out.println("  Enabled: " + addToCartBtn.isEnabled());

                } catch (Exception e) {
                    System.out.println("Error inspecting product " + i + ": " + e.getMessage());
                }
            }

            By product5Button = By.cssSelector("div.recommended_items a[data-product-id='5']");
            try {
                WebElement p5Button = framework.findElement(product5Button);
                System.out.println("Product 5 button found: " + p5Button.isDisplayed());
            } catch (Exception e) {
                System.out.println("Product 5 button NOT found: " + e.getMessage());
            }

            System.out.println("=================================");

        } catch (Exception e) {
            System.out.println("Debug failed: " + e.getMessage());
        }
    }
}