package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T05_AdressDetails {
    WebDriver driver;

    public T05_AdressDetails(WebDriver driver) {
        this.driver = driver;
    }

    private By Adressdetails = By.xpath("//h2[contains(text(),'Address Details')]");

    public T06_PlaceOrder getAdressdetails(){
        driver.findElement(Adressdetails).getText();
        return new T06_PlaceOrder(driver);
    }
}
