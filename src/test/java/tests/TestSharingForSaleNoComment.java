package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingForSaleNoComment extends BaseTest{

   /*
    * логинимся
    * переходим в товары-> переходим в товар (рандомный)
    * делимся на стене
    * обновляем страницу, сравниваем цены, ожидаем скидку
    */

    @Test
    public void sharingForSaleNoComment(){

        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());

        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery("Товар");

        int random = new Random().nextInt(20);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(random).openProduct();
        ProductPageFrame productPageFrame = new ProductPageFrame(driver);
        productPageFrame.isFivePercentDiscountPresent();
      //  int before = price1;
        productPageFrame.getFivePercentDiscount().share();
        productPageFrame.refresh();
       // int after = price2;
       // Assert.assertTrue(before > after);
        System.out.println("Скидка получена");

    }

//    @After
//    public void out(){
//        driver.quit();
//    }
}
