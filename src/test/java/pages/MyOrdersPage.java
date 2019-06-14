package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyOrdersPage extends BasePage{

    private static final By CART = By.xpath(".//*[@class='tico_txt' and contains(text(),'Корзина')]");
    private static final By MY_ADDRESSES = By.xpath(".//*[@class='tico_txt' and contains(text(),'Адреса доставки')]");
    private static final By BOOKMARKS = By.xpath(".//*[@class='tico_txt' and contains(text(),'Закладки')]");

    public MyOrdersPage(WebDriver driver) {
        super(driver);
    }

    // открываем корзину
    public CartPage openCart(){
        click(CART);
        return new CartPage(driver);
    }

    //открываем адреса доставки
    public DeliveryAddressPage openAddresses(){
        click(MY_ADDRESSES);
        return new DeliveryAddressPage(driver);
    }

    //открываем закладки
    public BookmarksPage openBookmarks(){
        click(BOOKMARKS);
        return new BookmarksPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3, CART, "Корзина не загрузилась", "Корзина загрузилась");
        assertTrue(driver,3,MY_ADDRESSES,"Адресс не загрузился", "Адресс загрузился");
        //assertTrue(driver, 5, BOOKMARKS, "Закладки загрузились","Закладки не загрузились");
    }
}
