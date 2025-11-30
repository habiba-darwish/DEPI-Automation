package stepsdef;

import pages.HomePage;
import selenuim.SelenuimFramework;

public class BaseSteps {
    // جعل المتغيرات protected لتسهيل الوصول إليها من الـ Steps الأخرى (إذا أردتِ استخدام الوراثة مرة أخرى).
    protected SelenuimFramework framework;
    protected HomePage homePage;

    public BaseSteps() {
        // الـ TestContext أصبح الآن مُهيأ ويتم سحب الكائنات منه.
        this.framework = TestContext.getFramework();
        this.homePage = TestContext.getHomePage();
    }

    // لا يوجد أي دوال عليها @Given أو @When أو @Then هنا.
}