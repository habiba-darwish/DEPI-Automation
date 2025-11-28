package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T06_PlaceOrder {
    WebDriver driver;

    public T06_PlaceOrder(WebDriver driver){
        this.driver = driver;
    }

    private By PlaceOrderButton = By.linkText("Place Order");

    public void ClickOnPlaceOrderButton(){
        driver.findElement(PlaceOrderButton).click();
    }

    private By NameOnCard = By.xpath("//*[@data-qa='name-on-card']");
    public void InsertNameOnCard(){
        driver.findElement(NameOnCard).sendKeys("Menna Yasser");
    }

    public By CardNumber = By.xpath("//*[@name='card_number']");
    public void InsertCardNumber(){
        driver.findElement(CardNumber).sendKeys("2742071709845243");
    }

    public By CVC = By.xpath("//*[@name='cvc']");
    public void InsertCVC(){
        driver.findElement(CVC).sendKeys("789");
    }

    private By ExpirationMonth = By.xpath("//*[@name='expiry_month']");
    public void InsertExpirationMonth(){
        driver.findElement(ExpirationMonth).sendKeys("10");
    }

    private By ExpirationYear = By.xpath("//*[@name='expiry_year']");
    public void InsertExpirationYear(){
        driver.findElement(ExpirationYear).sendKeys("2029");
    }

    private By PaYandConfirm = By.xpath("//*[@data-qa='pay-button']");
    public T08_DownloadInvoice ClickonPaYandConfirm(){
        driver.findElement(PaYandConfirm).click();
        return new T08_DownloadInvoice(driver);
    }
}
