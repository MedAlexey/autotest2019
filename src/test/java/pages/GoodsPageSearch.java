package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.GoodsPageWrapper;

import java.util.List;

public class GoodsPageSearch extends BasePage implements IGoodsPage{

    private static final By ORDERS = By.xpath(".//*[@class='filter_ac' and contains(text(), 'Мои заказы')]");
    private static final By USER_PAGE = By.xpath(".//*[@class='toolbar_logo_img']");
    private static final By CATEGORY = By.xpath(".//*[@class='category-menu_i' and contains(text(), 'Дом и интерьер')]");
    private static final By PRODUCT_CARD = By.xpath(".//*[@class='ugrid_i']");
    private static final By COLOR = By.xpath(".//*[@class='mall-card_label' and contains(text(), 'background-color: #000000')]");
    private String COLOR_OF_PRODUCT = "NULL";
    private String CATEGORY_OF_PRODUCT = "NULL";

    public GoodsPageSearch(WebDriver driver) {
        super(driver);
    }

    // проверка наличия кнопок
    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'Мои заказы'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ORDERS)));

        Assert.assertTrue("Не дождались кнопки перехода на страницу пользователя",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(USER_PAGE)));

        Assert.assertTrue("Не дождались кнопки 'Дом и интерьер'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(CATEGORY)));

    }

    // выбор цвета
    public void chooseColor(){
        click(COLOR);
        COLOR_OF_PRODUCT = "Black";
    }

    // выбор способа сортировки товаров
    public void chooseSort(){
        click(CATEGORY);
        CATEGORY_OF_PRODUCT = "Дом и интерьер";
    }

    // получение обёрнутых товаров
    public List<GoodsPageWrapper> getGetProducts(){
        List<WebElement> elements = driver.findElements(PRODUCT_CARD);
        return wrappers.GoodsPageTransformer.wrapProducts(elements,driver);
    }

    @Override
    public MyOrdersPage openMyOrders() {
        click(ORDERS);
        return new MyOrdersPage(driver);
    }

    @Override
    public UserMainPage openUserMainPage() {
        click(USER_PAGE);
        return new UserMainPage(driver);
    }

}