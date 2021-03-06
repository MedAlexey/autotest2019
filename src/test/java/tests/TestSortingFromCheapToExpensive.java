package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.GoodsPageSearch;
import pages.LoginPage;
import wrappers.GoodsPageWrapper;

import java.util.List;

public class TestSortingFromCheapToExpensive extends BaseTest {

    /**логинимся
     * переходим в товары
     * используем поиск
     * сортируем "сначала дорогие"
     * оборачиваем все товары
     * проверяем сортировку
     */
    @Test
    public void sortingFromCheapToExpensive() {

        final String SEARCH_TEXT = "Утка";

        LoginPage loginPage = new LoginPage(driver);

        GoodsPageSearch goodsPageSearch = loginPage.login(config.getLogin(), config.getPassword())
                .openGoodsPage()
                .writeSearchQuery(SEARCH_TEXT)
                .chooseSortCheap()
                ;
        List<GoodsPageWrapper> productsList = goodsPageSearch.getProducts();

        Assert.assertTrue("Сортировка не корректна", goodsPageSearch.isCheapSorted(productsList));
        System.out.println("Сортировка корректна");
    }

    @After
    public void out(){
        driver.quit();
    }
}