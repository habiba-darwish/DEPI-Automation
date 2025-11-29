package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecureAreaPage extends BasePage {

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "flash")
    private WebElement successMessage;

    public String getSuccessMessage() {
        return successMessage.getText().trim();
    }
}
