package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupPage extends BasePage {

    private static final By PRODUCT = By.xpath(".//*[@class='mall-media-link_a']");

    public GroupPage(WebDriver driver) {
        super(driver);
    }

    //проверяем наличие товара на стене
    public ProductPage checkProductOnTheWall() {
        click(PRODUCT);

        return new ProductPage(driver);
    }

    // проверка кликабельности товара
    @Override
    protected void check(WebDriver driver) {
            Assert.assertTrue("Не дождались ссылки на товар",
                    new WebDriverWait(driver, 10).
                            until((ExpectedCondition<Boolean>) d -> isElementPresent(PRODUCT)));
    }
}
