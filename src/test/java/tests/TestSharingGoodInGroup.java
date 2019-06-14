package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingGoodInGroup extends BaseTest{

    /*
    * логинимся
    * переходим в товары-> переходим в товар (рандомный)
    * делимся в группе
    * проверяем пост в группе
    */

    String GROUP_NAME = "products test";

    @Test
    public void sharingGoodInGroup(){

        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());

        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery(GROUP_NAME);

        int random = new Random().nextInt(10);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(random).openProduct().shareInGroup();
        String nameBefore = goodsPageSearch.getProducts().get(random).getName();

        ShareInGroupFrame shareInGroupFrame = new ShareInGroupFrame(driver);
        shareInGroupFrame.chooseGroup(GROUP_NAME);
        shareInGroupFrame.share();
        shareInGroupFrame.closeFrame();

        String nameAfter = goodsPageSearch.openUserMainPage()
                .openGroups()
                .openGroupModeration(driver)
                .openGroup(driver)
                .checkProductOnTheWall()
                .getProductName();
        Assert.assertEquals(nameBefore, nameAfter);
        System.out.println("Товары совпадают");

    }

    @After
    public void out(){
        driver.quit();
    }

}
