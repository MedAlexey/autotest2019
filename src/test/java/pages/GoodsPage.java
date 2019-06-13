package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.GoodsPageTransformer;
import wrappers.GoodsPageWrapper;

import java.util.List;

public class GoodsPage extends BasePage implements IGoodsPage {

    private static final By ORDERS = By.xpath(".//*[@class='filter_ac' and contains(text(), 'Мои заказы')]");
    private static final By USER_PAGE = By.xpath(".//*[@class='toolbar_logo_img']");
    private static final By PRODUCT_CARD = By.xpath(".//*[@class='ugrid_i']");
    private final static By FOR_WAIT = By.xpath(".//*[@class='ugrid_cnt']/div[79]");

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

        Assert.assertTrue("Не дождались кнопки сначала дорогие",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(FOR_WAIT)));

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

    //возвращает список обернутых товаров
    public List<GoodsPageWrapper> getWrapGoods(){
        List<WebElement> elements = driver.findElements(PRODUCT_CARD);
        return GoodsPageTransformer.wrapProducts(elements,driver);
    }
}
