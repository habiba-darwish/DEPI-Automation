package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class T08_DownloadInvoice {
    WebDriver driver;

    public T08_DownloadInvoice(WebDriver driver) {
        this.driver = driver;
    }

    private By DownloadInvoice = By.xpath("//*[contains(text(),'Download Invoice')]");

    public void ClickONDownloadInvoice(){
        driver.findElement(DownloadInvoice).click();
    }

    private By DeletAcoount = By.linkText("Delete Account");
    public void ClickonDeleteAcoount() {
        driver.findElement(DeletAcoount).click();
    }
}
