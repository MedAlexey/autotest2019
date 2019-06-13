package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.BookmarksPageWrapper;

import java.util.List;

import static wrappers.BookmarksPageTransformer.wrapBookmarks;

public class BookmarksPage extends BasePage {

    private static final By FOR_WAIT = By.xpath(".//div[5] [@class='mall-item __separator-basket __basket __flex'] ");
    private static final By BOOKMARKS_CARD = By.xpath(".//*[@class='mall-item __separator-basket __basket __flex']");
    private static final By DELETE_BUTTON = By.xpath(".//*[text()='Удалить']");

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

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3,FOR_WAIT,"Не дождались всех кард закладок", "Карды загруженны");
        assertTrue(driver,3,DELETE_BUTTON,"Кнопка удалить не найдена","Кнопка удалить загруженна");
    }
}
