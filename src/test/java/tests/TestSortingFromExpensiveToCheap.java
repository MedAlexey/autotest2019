package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.GoodsPageSearch;
import pages.LoginPage;
import wrappers.GoodsPageWrapper;

import java.util.List;

/**логинимся
 * переходим в товары
 * используем поиск
 * сортируем "сначала дещёвые"
 * оборачиваем все товары
 * проверяем сортировку
 */
public class TestSortingFromExpensiveToCheap extends BaseTest {

    @Test
    public void sortingFromExpensiveToCheap() {

        final String SEARCH_TEXT = "Панама";

        LoginPage loginPage = new LoginPage(driver);

        GoodsPageSearch goodsPageSearch = loginPage.login(config.getLogin(), config.getPassword())
                .openGoodsPage()
                .writeSearchQuery(SEARCH_TEXT)
                .chooseSortCheap();

        List<GoodsPageWrapper> productsList = goodsPageSearch.getProducts();

        Assert.assertTrue("Сортировка не корректна", goodsPageSearch.isCheapSorted(productsList));
        System.out.println("Сортировка корректна");
    }

}