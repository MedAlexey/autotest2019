package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.BookmarksPageWrapper;

import java.util.List;

import static wrappers.BookmarksPageTransformer.wrapBookmarks;

public class BookmarksPage extends BasePage {

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


    public boolean contains(String expectedName,
                             String expectedColor,
                             String expectedSize) {

        for (BookmarksPageWrapper bookmark: getWrapBookmarks()) {
            if ( (bookmark.getColor().equals(expectedColor)) &&
                    (bookmark.getName().equals(expectedName)) &&
                    (bookmark.getSize().equals(expectedSize))
            ) {
                return true;
            }
        }

        return false;
    }


    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3,DELETE_BUTTON,"Кнопка удалить не найдена","Кнопка удалить загруженна");
    }
}
