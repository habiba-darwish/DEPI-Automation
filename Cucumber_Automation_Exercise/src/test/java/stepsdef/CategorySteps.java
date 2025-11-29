package stepsdef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CategoryPage;
import pages.HomePage;

public class CategorySteps {
    private final HomePage homePage;
    private final CategoryPage categoryPage;

    public CategorySteps() {
        this.homePage = TestContext.getHomePage();
        this.categoryPage = TestContext.getCategoryPage();
    }

    @Then("category section should be visible")
    public void categorySectionShouldBeVisible() {
        Assert.assertTrue(homePage.isCategorySectionVisible(),
                "Category section should be visible");
    }

    @When("I scroll to category section")
    public void iScrollToCategorySection() {
        homePage.scrollToViewCategorySection();
    }

    @When("I click on {string} category")
    public void iClickOnCategory(String categoryName) {
        if (categoryName.equalsIgnoreCase("Women")) {
            homePage.clickWomenCategory();
        } else if (categoryName.equalsIgnoreCase("Men")) {
            categoryPage.clickMenCategory();
        }
    }

    @When("I click on {string} subcategory")
    public void iClickOnSubcategory(String subcategoryName) {
        if (subcategoryName.equalsIgnoreCase("Dress")) {
            homePage.clickDressCategory();
        } else if (subcategoryName.equalsIgnoreCase("Tshirts")) {
            categoryPage.clickTshirtsCategory();
        }
    }

    @Then("category title should be {string}")
    public void categoryTitleShouldBe(String expectedTitle) {
        String actualTitle = categoryPage.getCategoryPageTitleText();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Category title should be: " + expectedTitle);
    }

    @Then("category products should be displayed")
    public void categoryProductsShouldBeDisplayed() {
        // This is validated by checking the page title/content
        String pageTitle = categoryPage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Products"),
                "Category products page should be displayed");
    }

    @Then("page title should contain {string}")
    public void pageTitleShouldContain(String expectedTitlePart) {
        String actualTitle = categoryPage.getPageTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitlePart),
                "Expected page title to contain: " + expectedTitlePart +
                        " but got: " + actualTitle);
    }

}