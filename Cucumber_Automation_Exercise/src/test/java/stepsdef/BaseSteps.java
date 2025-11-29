package stepsdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;
import selenuim.SelenuimFramework;

public class BaseSteps {
    private final SelenuimFramework framework;
    private final HomePage homePage;

    public BaseSteps() {
        this.framework = TestContext.getFramework();
        this.homePage = TestContext.getHomePage();
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        // Already handled in hooks
    }

    @Given("I verify home page is displayed")
    public void iVerifyHomePageIsDisplayed() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertTrue(actualTitle.contains("Automation Exercise"),
                "Home page should be visible");
    }


}