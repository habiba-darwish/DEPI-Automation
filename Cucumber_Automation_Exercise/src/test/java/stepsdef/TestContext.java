package stepsdef;

import pages.*;
import selenuim.SelenuimFramework;

public class TestContext {

    private static ThreadLocal<SelenuimFramework> framework = new ThreadLocal<>();

    private static ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    private static ThreadLocal<AllProductsPage> allProductsPage = new ThreadLocal<>();
    private static ThreadLocal<CartPage> cartPage = new ThreadLocal<>();
    private static ThreadLocal<FirstProductPage> firstProductPage = new ThreadLocal<>();
    private static ThreadLocal<BrandPage> brandPage = new ThreadLocal<>();
    private static ThreadLocal<CategoryPage> categoryPage = new ThreadLocal<>();


    private static ThreadLocal<SignupLoginPage> signupLoginPage = new ThreadLocal<>();
    private static ThreadLocal<SignupPage> signupPage = new ThreadLocal<>();



    public static void initialize(SelenuimFramework fw) {
        framework.set(fw);

        homePage.set(new HomePage(fw));
        allProductsPage.set(new AllProductsPage(fw));
        cartPage.set(new CartPage(fw));
        firstProductPage.set(new FirstProductPage(fw));
        brandPage.set(new BrandPage(fw));
        categoryPage.set(new CategoryPage(fw));


        signupLoginPage.set(new SignupLoginPage(fw));
        signupPage.set(new SignupPage(fw));
    }

    public static SelenuimFramework getFramework() {
        return framework.get();
    }

    public static HomePage getHomePage() {
        return homePage.get();
    }

    public static AllProductsPage getAllProductsPage() {
        return allProductsPage.get();
    }

    public static CartPage getCartPage() {
        return cartPage.get();
    }

    public static FirstProductPage getFirstProductPage() {
        return firstProductPage.get();
    }

    public static BrandPage getBrandPage() {
        return brandPage.get();
    }

    public static CategoryPage getCategoryPage() {
        return categoryPage.get();
    }


    public static SignupLoginPage getSignupLoginPage() {
        return signupLoginPage.get();
    }

    public static SignupPage getSignupPage() {
        return signupPage.get();
    }


    public static void cleanup() {
        framework.remove();
        homePage.remove();
        allProductsPage.remove();
        cartPage.remove();
        firstProductPage.remove();
        brandPage.remove();
        categoryPage.remove();
        signupLoginPage.remove();
        signupPage.remove();
    }
}
