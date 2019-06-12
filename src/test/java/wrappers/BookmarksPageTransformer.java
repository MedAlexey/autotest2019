package wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookmarksPageTransformer {

    private BookmarksPageTransformer(){}

    public static List<BookmarksPageWrapper> wrapBookmarks(List<WebElement> cards, WebDriver driver) {
        if (cards.isEmpty()){
            return Collections.emptyList();
        }
        List<BookmarksPageWrapper> wrapBookmarks = new ArrayList<BookmarksPageWrapper>();
        for (WebElement card : cards){
            wrapBookmarks.add(new BookmarksPageWrapper(driver, card));
        }
        return wrapBookmarks;
    }
}
