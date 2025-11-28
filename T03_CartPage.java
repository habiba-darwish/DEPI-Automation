package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T03_CartPage {
    WebDriver driver;

    public T03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By Cart = By.linkText("Cart");

    public T04_ProceedToCheckout ClickonCartPage() {
        driver.findElement(Cart).click();
        return  new T04_ProceedToCheckout(driver);
    }

}
