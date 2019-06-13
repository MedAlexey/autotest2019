package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.*;
import wrappers.BookmarksPageWrapper;

import java.util.List;

/**
 *  логинимся
 *  переходим в товары, добавляем первые пять товаров в закладки, запоминая название, цену, цвет, размер
 *  переходим в мои заказы->закладки
 *  сравниваем название, цену, цвет, размер закладок с запомненными значениями товаров
 *  удаляем добавленные товары
 *
 */
public class TestAddingToBookmarks extends BaseTest {

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());
    }

    @Test
    public void addingToBookmarksTest() {
        // делаем окно на полный экран
        driver.manage().window().maximize();

        // переходим в товары
        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();

        // сохраняем информацию первого товара и добавляем его в закладки
        ProductPageFrame firstProduct = goodsPage.getWrapGoods().get(0).openProduct();
        String firstProductName = firstProduct.getProductName();
        String firstProductColor = firstProduct.chooseColor();
        String firstProductSize = firstProduct.chooseSize();
        firstProduct.addToBookmarks();
        firstProduct.close();

        // сохраняем информацию второго товара и добавляем его в закладки
        ProductPageFrame secondProduct = goodsPage.getWrapGoods().get(1).openProduct();
        String secondProductName = secondProduct.getProductName();
        String secondProductColor = secondProduct.chooseColor();
        String secondProductSize = secondProduct.chooseSize();
        firstProduct.addToBookmarks();
        firstProduct.close();

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-1000);");

        // переходим в закладки
        MyOrdersPage myOrdersPage = goodsPage.openMyOrders();
        BookmarksPage bookmarksPage = myOrdersPage.openBookmarks();

        List<BookmarksPageWrapper> bookmarks = bookmarksPage.getWrapBookmarks();

        Assert.assertTrue(contains(firstProductName, firstProductColor, firstProductSize, bookmarks));
        Assert.assertTrue(contains(secondProductName, secondProductColor, secondProductSize, bookmarks));

    }

    private boolean contains(String expectedName,
                             String expectedColor,
                             String expectedSize,
                             List<BookmarksPageWrapper> bookmarks) {
        boolean result = false;

        for (BookmarksPageWrapper bookmark: bookmarks) {
            if ( (bookmark.getColor().equals(expectedColor)) &&
                    (bookmark.getName().equals(expectedName)) &&
                    (bookmark.getSize().equals(expectedSize))
            ) {
                result = true;
            }
        }


        return result;
    }

    @After
    public void sweep() {
        BookmarksPage bookmarksPage = new BookmarksPage(driver);
        bookmarksPage.deleteFirstBookmark().deleteFirstBookmark();
    }

}
