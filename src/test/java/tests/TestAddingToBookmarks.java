package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.*;
import wrappers.BookmarksPageWrapper;
import wrappers.GoodsPageWrapper;

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


    @Test
    public void addingToBookmarksTest() {
        // делаем окно на полный экран
        driver.manage().window().maximize();

        // логинимся и переходим в товары
        GoodsPage goodsPage = new LoginPage(driver).login(config.getLogin(), config.getPassword()).openGoodsPage();
        List<GoodsPageWrapper> goods = goodsPage.getWrapGoods();

        // сохраняем информацию первого товара и добавляем его в закладки
        ProductPageFrame firstProduct = goods.get(0).openProduct();
        String firstProductName = firstProduct.getProductName();
        String firstProductColor = firstProduct.chooseColor();
        String firstProductSize = firstProduct.chooseSize();
        firstProduct.addToBookmarks();
        firstProduct.close();

        // сохраняем информацию второго товара и добавляем его в закладки
        ProductPageFrame secondProduct = goods.get(1).openProduct();
        String secondProductName = secondProduct.getProductName();
        String secondProductColor = secondProduct.chooseColor();
        String secondProductSize = secondProduct.chooseSize();
        secondProduct.addToBookmarks();
        secondProduct.close();

        // сохраняем информацию третьего товара и добавляем его в закладки
        ProductPageFrame thirdProduct = goods.get(2).openProduct();
        String thirdProductName = thirdProduct.getProductName();
        String thirdProductColor = thirdProduct.chooseColor();
        String thirdProductSize = thirdProduct.chooseSize();
        thirdProduct.addToBookmarks();
        thirdProduct.close();

        // сохраняем информацию четвёртого товара и добавляем его в закладки
        ProductPageFrame fourthProduct = goods.get(3).openProduct();
        String fourthProductName = fourthProduct.getProductName();
        String fourthProductColor = fourthProduct.chooseColor();
        String fourthProductSize = fourthProduct.chooseSize();
        fourthProduct.addToBookmarks();
        fourthProduct.close();

        // сохраняем информацию пятого товара и добавляем его в закладки
        ProductPageFrame fifthProduct = goods.get(4).openProduct();
        String fifthProductName = fifthProduct.getProductName();
        String fifthProductColor = fifthProduct.chooseColor();
        String fifthProductSize = fifthProduct.chooseSize();
        fifthProduct.addToBookmarks();
        fifthProduct.close();

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-1000);");

        // переходим в закладки
        BookmarksPage bookmarksPage = goodsPage.openMyOrders().openBookmarks();


        Assert.assertTrue(bookmarksPage.contains(firstProductName, firstProductColor, firstProductSize));
        Assert.assertTrue(bookmarksPage.contains(secondProductName, secondProductColor, secondProductSize));
        Assert.assertTrue(bookmarksPage.contains(thirdProductName, thirdProductColor, thirdProductSize));
        Assert.assertTrue(bookmarksPage.contains(fourthProductName, fourthProductColor, fourthProductSize));
        Assert.assertTrue(bookmarksPage.contains(fifthProductName, fifthProductColor, fifthProductSize));

    }

    @After
    public void sweep() {
        BookmarksPage bookmarksPage = new BookmarksPage(driver);

        bookmarksPage.
                deleteFirstBookmark().
                deleteFirstBookmark().
                deleteFirstBookmark().
                deleteFirstBookmark().
                deleteFirstBookmark();
    }

}
