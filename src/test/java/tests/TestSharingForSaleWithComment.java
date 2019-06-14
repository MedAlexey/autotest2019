package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.*;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class TestSharingForSaleWithComment extends BaseTest {

    String comment = "My comment for sale";
    String google = "Товар";
    /*
    * логинимся
    * переходим в товары-> переходим в товар (рандомный)
    * делимся на стену с комментарием
    * обновляем страницу, сравниваем цены, ожидаем скидку
    */

    @Test
    public void sharingForSaleWithComment(){

        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());

        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        GoodsPageSearch goodsPageSearch = goodsPage.writeSearchQuery(google);

        int random = new Random().nextInt(20);
        List<GoodsPageWrapper> gp =  goodsPageSearch.getProducts();
        gp.get(random).openProduct();
        ProductPageFrame productPageFrame = new ProductPageFrame(driver);
        productPageFrame.isFivePercentDiscountPresent();
        int before = productPageFrame.getPrice();
        SaleFrame saleFrame = productPageFrame.getFivePercentDiscount();
        saleFrame.writeComment(comment);
        saleFrame.share();
        productPageFrame.refresh();
        new Actions(driver).sendKeys(Keys.PAGE_UP).build().perform();
        int after = new ProductPage(driver).getPrice();
        System.out.println(before + " " + after);
        Assert.assertTrue(before > after);
        System.out.println("Скидка получена");
    }

    @After
    public void out(){
        driver.quit();
    }
}
