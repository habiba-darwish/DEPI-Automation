package stepsdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AllProductsPage;
import pages.BrandPage;
import pages.HomePage;

public class BrandSteps {
    private final HomePage homePage;
    private final AllProductsPage allProductsPage;
    private final BrandPage brandPage;

    public BrandSteps() {
        this.homePage = TestContext.getHomePage();
        this.allProductsPage = TestContext.getAllProductsPage();
        this.brandPage = TestContext.getBrandPage();
    }

    @When("I scroll to brands section")
    public void iScrollToBrandsSection() {
        allProductsPage.scrollToViewBrandsSection();  // ✅ FIXED
    }

    @Then("brands section should be visible")
    public void brandsSectionShouldBeVisible() {
        Assert.assertTrue(allProductsPage.isBrandsSectionVisible(),
                "Brands section should be visible");
    }

    @When("I click on {string} brand")
    public void iClickOnBrand(String brandName) {
        if (brandName.equalsIgnoreCase("POLO")) {
            allProductsPage.clickBrandPOLOButton();
        } else if (brandName.equalsIgnoreCase("H&M")) {
            brandPage.scrollToViewBrandsSection();
            brandPage.clickBrandHAndMButton();
        }
        // Add more brands as needed
    }

    @Then("I should see {string} title")
    public void iShouldSeeTitle(String expectedTitle) {
        String actualTitle = brandPage.getBrandPageTitleTextLocator();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Expected title: " + expectedTitle + ", but got: " + actualTitle);
    }

    @Then("brand products section should be visible")
    public void brandProductsSectionShouldBeVisible() {
        Assert.assertTrue(brandPage.isBrandProductsSectionVisible(),
                "Brand products section should be visible");
    }
}