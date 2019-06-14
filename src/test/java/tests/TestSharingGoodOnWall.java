package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingGoodOnWall extends BaseTest{

   /*
    * логинимся
    * переходим в товары-> переходим в товар (рандомный)
    * делимся на стене
    * проверяем правильность отображения на стене
    */

    @Test
    public void sharingGoodOnWall(){

        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());

        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery("Product");

        int RANDOM = new Random().nextInt(10);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(RANDOM).openProduct().shareNow();
        String NAME_BEFORE = goodsPageSearch.getProducts().get(RANDOM).getName();

        String NAME_AFTER = goodsPageSearch.openUserMainPage().openShareFromUserMainPage().getProductName();
        Assert.assertTrue(NAME_AFTER.contains(NAME_BEFORE));
        System.out.println("Товары совпадают");

    }

    @After
    public void out(){
        driver.quit();
    }
}
