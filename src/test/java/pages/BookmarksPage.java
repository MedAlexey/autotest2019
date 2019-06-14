package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import wrappers.BookmarksPageWrapper;

import java.util.List;

import static wrappers.BookmarksPageTransformer.wrapBookmarks;

public class BookmarksPage extends BasePage {

    private static final By BOOKMARKS_CARD = By.xpath(".//*[@class='mall-item __separator-basket __basket __flex']");
    private static final By DELETE_BUTTON = By.xpath(".//*[text()='Удалить']");
    private static final By CART = By.xpath(".//*[@class='tico_txt' and contains(text(),'Корзина')]");
    private static final By MY_ADDRESSES = By.xpath(".//*[@class='tico_txt' and contains(text(),'Адреса доставки')]");


    public BookmarksPage(WebDriver driver) {
        super(driver);
    }

    //возвращает список обернутых закладок
    public List<BookmarksPageWrapper> getWrapBookmarks(){
        List<WebElement> elements = driver.findElements(BOOKMARKS_CARD);
        return wrapBookmarks(elements, driver);
    }

    //удаляет первую закладку
    public BookmarksPage deleteFirstBookmark(){
        click(DELETE_BUTTON);
        return new BookmarksPage(driver);
    }


    // обновить страницу
    public BookmarksPage refresh() {
        new EventFiringWebDriver(driver).navigate().refresh();
        return new BookmarksPage(driver);
    }

    public boolean contains(String expectedName) {

        for (BookmarksPageWrapper bookmark: getWrapBookmarks()) {

            if (bookmark.getName().equals(expectedName)) {
                return true;
            }
        }

        return false;
    }


    @Override
    protected void check(WebDriver driver) {
        //assertTrue(driver,3, CART, "Корзина не загрузилась", "Корзина загрузилась");
        //assertTrue(driver,3,MY_ADDRESSES,"Адресс не загрузился", "Адресс загрузился");
    }
}
