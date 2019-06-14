package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.GoodsPageSearch;
import pages.LoginPage;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSearchingGoodsByPrice extends BaseTest {

    @Test
    public void testSearchingGoodsByPrice(){

        final String MIN_PRICE = "3000";
        final String MAX_PRICE = "5000";
        final String SEARCH_TEXT= "Кроссовки";

        LoginPage loginPage = new LoginPage(driver);

        GoodsPageSearch goodsPageSearch = loginPage.login(config.getLogin(), config.getPassword())
                .openGoodsPage()
                .writeSearchQuery(SEARCH_TEXT)
                .setMinAndMaxPrice(MIN_PRICE,MAX_PRICE);


        List<GoodsPageWrapper> productsList = goodsPageSearch.getProducts();

        Assert.assertTrue("Цена товаров не соответствует диапозну",
                goodsPageSearch.isGoodPrice(Integer.parseInt(MIN_PRICE),Integer.parseInt(MAX_PRICE),productsList));
        System.out.println("Цены соответсвуют диапозону");
    }
}
