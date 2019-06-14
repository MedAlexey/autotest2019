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

        int random = new Random().nextInt(20);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(random).openProduct();
        ProductPageFrame productPageFrame = new ProductPageFrame(driver);
        productPageFrame.shareNow();
        productPageFrame.close();
        String nameBefore = goodsPageSearch.getProducts().get(random).getName();

        String nameAfter = goodsPageSearch.openUserMainPage().openShareFromUserMainPage().getProductName();
        Assert.assertEquals(nameBefore, nameAfter);
        System.out.println("Товары совпадают");

    }

    @After
    public void out(){
        driver.quit();
    }
}
