package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingGoodOnWallWithMessage extends BaseTest{
    /*
     * логинимся
     * переходим в товары-> переходим в товар (рандомный)
     * делимся на стене с комментом
     * проверяем комментарий на стене
     */
    String comment = "My comment";
    int random = new Random().nextInt(20);
    String google = "Product";

    @Test
    public void sharingGoodOnWallWithMessage() {

        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());

        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery(google);

        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(random).openProduct();

        ProductPageFrame productPageFrame = new ProductPageFrame(driver);
        productPageFrame.shareWithText();
        ShareWithTextFrame shareWithTextFrame = new ShareWithTextFrame(driver);
        shareWithTextFrame.writeText(comment);
        shareWithTextFrame.share();
        productPageFrame.close();

        String commentOnWall = goodsPageSearch.openUserMainPage().openProductWithComment().getCommentText();

        Assert.assertEquals(comment, commentOnWall);
        System.out.println("Товары совпадают");

    }

    @After
        public void out(){
            driver.quit();
        }
}
