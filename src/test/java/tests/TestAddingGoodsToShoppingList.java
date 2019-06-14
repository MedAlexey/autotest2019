package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;

public class TestAddingGoodsToShoppingList extends BaseTest {

    /*
    логинимся
    переходим в товары
    добавляем несколько товаров разных цветов, размеров, кол-во (рандомных) (желательно добавить один товар разных цветов или размеров)
    переходим в корзину
    проверяем название, цену, размер, цвет, кол-во имеющихся товаров с теми, которые добавляли
    удаляем добавленные товары
     */
    @Test
    public void addingGoodsToShoppingListTest() {

        // логинимся и переходим в товары
        GoodsPage goodsPage = new LoginPage(driver).login(config.getLogin(), config.getPassword()).openGoodsPage();

        // берём товары
        List<GoodsPageWrapper> wrappers = goodsPage.getWrapGoods();

        // выбираем и запоминаем характеристики товара
        ProductPageFrame firstProduct = wrappers.get(0).openProduct();
        String firstProductName = firstProduct.getProductName();
        String firstProductColor = firstProduct.chooseColor();
        String firstProductSize = firstProduct.chooseSize();
        firstProduct.increaseQuantity();
        firstProduct.addToCart();
        firstProduct.close();

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-1000);");

        //переходим в карзину
        CartPage cartPage = goodsPage.openMyOrders().openCart();

        Assert.assertTrue(cartPage.contains(firstProductName, "2", firstProductSize, firstProductColor));

    }

    @After
    public void sweep() {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteFirstCart();
    }


}
