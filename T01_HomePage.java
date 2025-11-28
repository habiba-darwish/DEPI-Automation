package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class T01_HomePage {
    WebDriver driver;

    public T01_HomePage(WebDriver driver){
        this.driver = driver;
    }

    private By signuputton = By.xpath("//*[contains(text(),' Signup / Login')]" );
    private By product = By.xpath("//p[contains(text(),'Sleeveless Dress')]/ancestor::div[contains(@class,'product')]");

    public T03_CartPage addSleevelessDressToCart() {
        WebElement productElement = driver.findElement(product);
        productElement.findElement(By.linkText("Add to cart")).click();
        return new T03_CartPage(driver);
    }

    public T02_SignUpPage ClickOnSignupbutton (){
        driver.findElement(signuputton).click();
        return new T02_SignUpPage(driver);
    }


}
