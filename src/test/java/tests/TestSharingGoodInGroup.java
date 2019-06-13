package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingGoodInGroup extends BaseTest{
    String GROUP_NAME = "Group";

    @Before
    public void login(){
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());
    }

    @Test
    public void sharingGoodInGroup(){
        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery("Product");

        int RANDOM = new Random().nextInt(10);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(RANDOM).openProduct().shareInGroup();
        String NAME_BEFORE = goodsPageSearch.getProducts().get(RANDOM).getName();

        ShareInGroupFrame shareInGroupFrame = new ShareInGroupFrame(driver);
        shareInGroupFrame.chooseGroup(GROUP_NAME);
        shareInGroupFrame.share();
        shareInGroupFrame.closeFrame();

        String NAME_AFTER = goodsPageSearch.openUserMainPage().openGroups().openGroup(driver).
                checkProductOnTheWall().getProductName();
        Assert.assertEquals(NAME_BEFORE, NAME_AFTER);
        System.out.println("Товары совпадают");

        // нужна зачистка?
    }

    @After
    public void out(){
        driver.quit();
    }

}
