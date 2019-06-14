package pages;

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private static final By FOR_WAIT = By.xpath(".//*[@class='ugrid_cnt']/div[70]");
    private static final By SEARCH_INPUT = By.xpath(".//*[@name='query']");
    private static final By MIN_PRICE = By.xpath(".//*[@name='minPrice']");
    private static final By MAX_PRICE = By.xpath(".//*[@name='maxPrice']");

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

       Assert.assertTrue("Не дождались карду",
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
    public GoodsPageSearch chooseSortExpensive(){
        click(SORT);
        click(EXPENSIVE);
        return new GoodsPageSearch(driver);
    }

    // получение обёрнутых товаров
    public List<GoodsPageWrapper> getProducts(){
        List<WebElement> elements = driver.findElements(PRODUCT_CARD);
        return GoodsPageTransformer.wrapProducts(elements,driver);
    }

    //проверяет сортировку, сначала дорогие
    public boolean isExpensiveSorted(List<GoodsPageWrapper> productsList) {
        for (int i = 0; i < productsList.size() - 1; i++) {
            System.out.println(productsList.get(i).getPrice()+" "+productsList.get(i+1).getPrice());
            if (Integer.parseInt(productsList.get(i).getPrice().replaceAll("\\s+","")) <
                    Integer.parseInt(productsList.get(i + 1).getPrice().replaceAll("\\s+",""))) {
                return false;
            }
        }
        return true;
    }

    //Вводит минимальную и максимальную цену
    public GoodsPageSearch setMinAndMaxPrice(String minPrice, String maxPrice){
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
        try {
            Thread.sleep(1000);                 //тут без вариантов
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return new GoodsPageSearch(driver);
    }

    //вводит минимальную цену
    public GoodsPageSearch setMinPrice(String minPrice){
        sendKeys(MIN_PRICE, minPrice);
        return new GoodsPageSearch(driver);
    }

    //вводит максимальную цену
    public GoodsPageSearch setMaxPrice(String maxPrice){
        sendKeys(MAX_PRICE, maxPrice);
        return new GoodsPageSearch(driver);
    }

    public boolean isGoodPrice(int minPrice,int maxPrice, List<GoodsPageWrapper> productList){
        for (GoodsPageWrapper card : productList){
            if (Integer.parseInt(card.getPrice().replaceAll("\\s+","")) < minPrice ||
            Integer.parseInt(card.getPrice().replaceAll("\\s+","")) > maxPrice){
                return false;
            }
        }
        return true;
    }

    //проверяет сортировку, сначала дешевые
    public boolean isCheapSorted(List<GoodsPageWrapper> productsList) {
        for (int i = 0; i < productsList.size() - 1; i++) {
            System.out.println(productsList.get(i).getPrice()+" "+productsList.get(i+1).getPrice());
            if (Integer.parseInt(productsList.get(i).getPrice().replaceAll("\\s+","")) >
                    Integer.parseInt(productsList.get(i + 1).getPrice().replaceAll("\\s+",""))) {
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