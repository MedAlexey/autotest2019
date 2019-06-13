package pages;

public interface IGoodsPage {

    // открыть "Мои заказы"
    MyOrdersPage openMyOrders();

    // открыть страницу пользователя
    UserMainPage openUserMainPage();

    // написать в поиск по товарам
    GoodsPageSearch writeSearchQuery(String query);

}
