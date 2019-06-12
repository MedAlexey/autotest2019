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

    public BookmarksPage(WebDriver driver) {
        super(driver);
    }

    //возвращает список обернутых закладок
    public List<BookmarksPageWrapper> getWrapBookmarks(){
        List<WebElement> elements = driver.findElements(BOOKMARKS_CARD);
        return wrapBookmarks(elements, driver);
    }

    @Override
    protected void check(WebDriver driver) {
        //explicitWaiter(driver,5,FOR_WAIT);
    }
}
