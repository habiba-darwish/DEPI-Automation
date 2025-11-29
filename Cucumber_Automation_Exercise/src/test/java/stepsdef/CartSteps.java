package stepsdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.CartPage;
import pages.FirstProductPage;
import pages.HomePage;
import pages.AllProductsPage;

import java.util.List;

public class CartSteps {
    private final HomePage homePage;
    private final CartPage cartPage;
    private final FirstProductPage firstProductPage;
    private final AllProductsPage allProductsPage;
    private String recommendedProductName;

    public CartSteps() {
        this.homePage = TestContext.getHomePage();
        this.cartPage = TestContext.getCartPage();
        this.firstProductPage = TestContext.getFirstProductPage();
        this.allProductsPage = TestContext.getAllProductsPage();
    }
    @When("I navigate to products page")
    public void iNavigateToProductsPage() {
        homePage.clickProductsButton();
    }

    @When("I scroll to view products")
    public void iScrollToViewProducts() {
        homePage.scrollToViewFirstProductAddToCartButton();
    }

    @When("I add first product to cart")
    public void iAddFirstProductToCart() {
        homePage.clickFirstProductAddToCartButton();
    }

    @When("I click continue shopping")
    public void iClickContinueShopping() {
        homePage.clickContinueShoppingButton();
    }

    @When("I add second product to cart")
    public void iAddSecondProductToCart() {
        homePage.clickSecondProductAddToCartButton();
    }

    @When("I view cart")
    public void iViewCart() {
        homePage.clickViewCartButton();
    }

    @Then("cart page should be displayed")
    public void cartPageShouldBeDisplayed() {
        String cartText = cartPage.getShoppingCartText();
        Assert.assertTrue(cartText.contains("Cart"),
                "Cart page should be displayed");
    }

    @Then("cart should contain {int} products")
    public void cartShouldContainProducts(int expectedCount) {
        int actualCount = cartPage.getNumberOfItemsInCart();
        Assert.assertEquals(actualCount, expectedCount,
                "Cart should contain " + expectedCount + " products");
    }

    @Then("product prices and totals should be correct")
    public void productPricesAndTotalsShouldBeCorrect() {

        List<Double> prices = cartPage.getProductPrices();
        List<Integer> quantities = cartPage.getProductQuantities();
        List<Double> totals = cartPage.getProductTotals();

        for (int i = 0; i < prices.size(); i++) {
            double expectedTotal = prices.get(i) * quantities.get(i);
            Assert.assertEquals(totals.get(i), expectedTotal,
                    "Total calculation incorrect for item " + (i + 1));
        }
    }

    @When("I click view first product")
    public void iClickViewFirstProduct() {
        homePage.clickViewProduct1Button();
    }

    @When("I set product quantity to {string}")
    public void iSetProductQuantityTo(String quantity) {
        firstProductPage.sendQuantity(quantity);
    }

    @When("I click add to cart button")
    public void iClickAddToCartButton() {
        firstProductPage.clickAddToCartButton();
    }

    @Then("product quantity should be {string}")
    public void productQuantityShouldBe(String expectedQuantity) {
        List<Integer> quantities = cartPage.getProductQuantities();
        Assert.assertTrue(
                quantities.contains(Integer.parseInt(expectedQuantity)),
                "Expected quantity " + expectedQuantity + " not found"
        );
    }

    @When("I remove first product from cart")
    public void iRemoveFirstProductFromCart() {
        cartPage.clickXButtonForFirstProductButton();
        cartPage.waitForProductToBeRemoved();
    }

    @Then("cart should be empty")
    public void cartShouldBeEmpty() {
        int count = cartPage.getNumberOfItemsInCart();
        Assert.assertEquals(count, 0, "Cart should be empty");
    }

    @When("I scroll to recommended items")
    public void iScrollToRecommendedItems() {
        homePage.scrollToViewRecommendedItemsText();
        homePage.scrollToViewRecommendedItemsSection();
    }

    @Then("{string} text should be visible")
    public void textShouldBeVisible(String expectedText) {
        String actualText = homePage.getRecommendedItemsText();
        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' not visible");
    }

    @When("I add recommended product to cart")
    public void iAddRecommendedProductToCart() {
        recommendedProductName = homePage.getRecommendedProduct5Name();
        homePage.clickProduct5InRecommendedProductAddToCartButton();
    }

    @Then("cart should contain the recommended product")
    public void cartShouldContainTheRecommendedProduct() {
        List<String> cartProducts = cartPage.getCartProductNames();
        Assert.assertFalse(cartProducts.isEmpty(), "Cart should not be empty");
        Assert.assertEquals(cartProducts.get(0), recommendedProductName,
                "Cart should contain the recommended product");
    }
}