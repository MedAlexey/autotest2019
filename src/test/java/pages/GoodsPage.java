package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoodsPage extends BasePage implements IGoodsPage {

    private static final By ORDERS = By.xpath(".//*[@class='filter_ac' and contains(text(), 'Мои заказы')]");
    private static final By USER_PAGE = By.xpath(".//*[@class='toolbar_logo_img']");

    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'Мои заказы'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ORDERS)));

        Assert.assertTrue("Не дождались кнопки перехода на страницу пользователя",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(USER_PAGE)));

    }

    // открыть "Мои заказы"
    @Override
    public MyOrdersPage openMyOrders() {
        click(ORDERS);
        return new MyOrdersPage(driver);
    }

    // открыть страницу пользователя
    @Override
    public UserMainPage openUserMainPage() {
        click(USER_PAGE);
        return new UserMainPage(driver);
    }
}
