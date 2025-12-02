package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import selenuim.SelenuimFramework;
import stepsdef.TestContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        SelenuimFramework framework = new SelenuimFramework();
        framework.initializeBrowser();
        framework.navigateToURL("https://automationexercise.com/");
        TestContext.initialize(framework);
    }

    @After
    public void afterScenario(Scenario scenario) {
        SelenuimFramework framework = TestContext.getFramework();
        System.out.println("Scenario " + scenario.getName() + " finished with status: " + scenario.getStatus());

        if (scenario.isFailed() && framework != null) {
            byte[] screenshot = ((TakesScreenshot) framework.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        if (framework != null) framework.closeBrowser();
        TestContext.cleanup();
    }
}
