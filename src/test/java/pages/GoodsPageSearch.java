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
import java.util.Random;

public class GoodsPageSearch extends BasePage implements IGoodsPage{

    private static final By ORDERS = By.xpath(".//*[@class='filter_i']/./*[contains(text(), 'Мои заказы')]");
    private static final By USER_PAGE = By.xpath(".//*[@id='topPanelLeftCorner']");
    private static final By PRODUCT_CARD = By.xpath(".//*[@class='ugrid_i']");
    private static final By COLOR = By.xpath(".//*[@class='mall-card_choose-item']");
    private static final By SORT = By.xpath(".//*[@id='mallsortby']");
    private static final By CHEAP = By.xpath(".//*[@value='PRICE_ASC']");
    private static final By EXPENSIVE = By.xpath(".//*[@value='PRICE_DSC']");
    private static final By FOR_WAIT = By.xpath(".//*[@class='ugrid_cnt']/div[79]");
    private static final By SEARCH_INPUT = By.xpath(".//*[@name='query']");

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

        Assert.assertTrue("Не дождались кнопки сортировки",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SORT)));

        Assert.assertTrue("Не дождались кнопки сначала дешевые",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(CHEAP)));

        Assert.assertTrue("Не дождались кнопки сначала дорогие",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(EXPENSIVE)));

        Assert.assertTrue("Не дождались последнего товара из списка",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(FOR_WAIT)));
    }

    // написать в поиск по товарам
    @Override
    public GoodsPageSearch writeSearchQuery(String query) {
        sendKeys(SEARCH_INPUT, query);
        return new GoodsPageSearch(driver);
    }

    // выбор цвета
    public String chooseColor(){
        click(COLOR);
        List<WebElement> elements = driver.findElements(COLOR);
        WebElement element = elements.get(new Random().nextInt(elements.size()));
        final String number = element.getCssValue("background-color");
        return number;
    }

    // выбор способа сортировки товаров сначала дешевые
    public GoodsPageSearch chooseSortCheap(){
        click(SORT);
        click(CHEAP);
        return new GoodsPageSearch(driver);
    }

    // выбор способа сортировки товаров сначала дорогие
    public void chooseSortExpensive(){
        click(SORT);
        click(EXPENSIVE);
    }

    // получение обёрнутых товаров
    public List<GoodsPageWrapper> getProducts(){
        List<WebElement> elements = driver.findElements(PRODUCT_CARD);
        return GoodsPageTransformer.wrapProducts(elements,driver);
    }

    //проверяет сортировку, сначала дешевые
    public boolean isCheapSorted(List<GoodsPageWrapper> productsList) {
        for (int i = 0; i < productsList.size() - 1; i++) {
            if (Integer.parseInt(productsList.get(i).getPrice()) >
                    Integer.parseInt(productsList.get(i + 1).getPrice())) {
                return false;
            }
        }
        return true;
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