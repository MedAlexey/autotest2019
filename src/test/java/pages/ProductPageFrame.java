package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductPageFrame extends ProductPage{

    private static final By SHARE_BUTTON = By.xpath(".//*[@title='Поделиться' and @data-l='t,reshareOverProduct']");
    // кнопка "добавить в закладки"
    private static final By ADD_TO_BOOKMARKS_BUTTON = By.xpath(".//*[@title='Добавить в закладки']");
    // кнопка "закрыть"
    private static final By CLOSE_BUTTON = By.xpath(".//*[@class='ic modal-new_close_ico']");
    private static final By FRAME_IS_CLOSED = By.xpath(".//*[@id='hook_Block_PopLayer' and @style='display: none;']");


    public ProductPageFrame(WebDriver driver) {
        super(driver);
    }

    public void close() {
        click(CLOSE_BUTTON);
        assertTrue(driver, 10, FRAME_IS_CLOSED, "Не дождались закрытия фрейма", "Фрейм закрылся");
    }


    // добавление товара в закладки
    public void addToBookmarks() {
        click(ADD_TO_BOOKMARKS_BUTTON);
    }

    @Override
    protected void check(WebDriver driver) {
    assertTrue(driver, 10, SHARE_BUTTON,
            "Не дождались кнопки \"поделиться\"",
            "Кнопка \"поделиться\" загружена");

    assertTrue(driver, 10, ADD_TO_BOOKMARKS_BUTTON,
            "Не дождались кнопки \"добавить в закладки\"",
            "Кнопка \"добавить в закладки\" загружена");
    }


}
