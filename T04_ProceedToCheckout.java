package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T04_ProceedToCheckout {
    WebDriver driver;
    public T04_ProceedToCheckout(WebDriver driver){
        this.driver=driver;
    }

    private By proceedToCheckout = By.partialLinkText("Proceed To Checkout");
    public T05_AdressDetails ClickonProceedToCheckout(){
        driver.findElement(proceedToCheckout).click();
        return new T05_AdressDetails(driver);
    }
}
