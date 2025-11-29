package stepsdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import pages.AllProductsPage;
import pages.FirstProductPage;
import pages.HomePage;

import java.util.List;

public class ProductSteps {
    private final HomePage homePage;
    private final AllProductsPage allProductsPage;
    private final FirstProductPage firstProductPage;

    public ProductSteps() {
        this.homePage = TestContext.getHomePage();
        this.allProductsPage = TestContext.getAllProductsPage();
        this.firstProductPage = TestContext.getFirstProductPage();
    }


    @Then("I should see {string} text")
    public void iShouldSeeText(String expectedText) {
        String actualText = allProductsPage.getAllProductsText();
        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' not found");
    }

    @When("I search for {string}")
    public void iSearchFor(String productName) {
        allProductsPage.sendSearchedProductName(productName);
        allProductsPage.clickSearchButton();
    }

    @Then("search results should contain {string}")
    public void searchResultsShouldContain(String productName) {
        List<String> results = allProductsPage.getSearchResultProducts();
        Assert.assertTrue(
                results.stream().anyMatch(r -> r.equalsIgnoreCase(productName)),
                "Product '" + productName + "' not found in results"
        );
    }

    @When("I scroll to view product section")
    public void iScrollToViewProductSection() {
        allProductsPage.scrollToView();
    }

    @When("I click on view first product button")
    public void iClickOnViewFirstProductButton() {
        allProductsPage.clickViewProductButton();
    }

    @Then("product details should be visible")
    public void productDetailsShouldBeVisible(DataTable dataTable) {
        List<String> details = dataTable.asList();
        for (String detail : details) {
            switch (detail.toLowerCase()) {
                case "name":
                    Assert.assertTrue(firstProductPage.getProductName(),
                            "Product name not visible");
                    break;
                case "category":
                    Assert.assertTrue(firstProductPage.getProductCategory(),
                            "Product category not visible");
                    break;
                case "price":
                    Assert.assertTrue(firstProductPage.getProductPrice(),
                            "Product price not visible");
                    break;
                case "availability":
                    Assert.assertTrue(firstProductPage.getProductAvailability(),
                            "Product availability not visible");
                    break;
                case "condition":
                    Assert.assertTrue(firstProductPage.getProductCondition(),
                            "Product condition not visible");
                    break;
                case "brand":
                    Assert.assertTrue(firstProductPage.getProductBrand(),
                            "Product brand not visible");
                    break;
            }
        }
    }
}