//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TestCases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import selenuim.SelenuimFramework;

@Epic("Automation Exercise Website")
@Feature("Home Page & Navigation")
@Owner("Habiba")
public class HomePageTest {
    SelenuimFramework framework;
    HomePage homePage;
    CategoryPage categoryPage;
    String subscriptionEmail = "reemadel@gmail.com";

    @BeforeMethod
    public void beforeMethod() {
        this.framework = new SelenuimFramework();
        this.framework.initializeBrowser();
        this.framework.navigateToURL("https://automationexercise.com/");
        this.homePage = new HomePage(this.framework);
        this.categoryPage = new CategoryPage(this.framework);
    }

    @AfterMethod
    public void afterMethod() {
        this.framework.closeBrowser();
    }


    @Test
    @Story("View Category Products")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can view products by selecting categories and subcategories from the left sidebar.")
    public void viewCategoryProducts_TC18() {
        Assert.assertTrue(this.homePage.isCategorySectionVisible(), "Category Section is not visible");
        this.homePage.scrollToViewCategorySection();
        this.homePage.clickWomenCategory();
        this.homePage.clickDressCategory();
        String ActualPageTitleText = this.categoryPage.getPageTitle();
        String ExpectedPageTitleText = "Automation Exercise - Dress Products";
        Assert.assertTrue(ActualPageTitleText.contains(ExpectedPageTitleText), ActualPageTitleText + " Should Have Contained " + ExpectedPageTitleText);
        String ActualDressCategoryPageTitleText = this.categoryPage.getCategoryPageTitleText();
        String ExpectedDressCategoryPageTitleText = "WOMEN - DRESS PRODUCTS";
        Assert.assertTrue(ActualDressCategoryPageTitleText.contains(ExpectedDressCategoryPageTitleText), ActualDressCategoryPageTitleText + " Should Have Contained " + ExpectedDressCategoryPageTitleText);
        this.categoryPage.clickMenCategory();
        this.categoryPage.clickTshirtsCategory();
        String ActualTshirtsCategoryPageTitleText = this.categoryPage.getPageTitle();
        String ExpectedTshirtsCategoryPageTitleText = "Automation Exercise - Tshirts Products";
        Assert.assertTrue(ActualTshirtsCategoryPageTitleText.contains(ExpectedTshirtsCategoryPageTitleText), ActualTshirtsCategoryPageTitleText + " Should Have Contained " + ExpectedTshirtsCategoryPageTitleText);
    }
}
