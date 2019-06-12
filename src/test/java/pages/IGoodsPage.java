package pages;

public interface IGoodsPage {

    // открыть "Мои заказы"
    MyOrdersPage openMyOrders();

    // открыть страницу пользователя
    UserMainPage openUserMainPage();

    // открыть страницу групп
    GroupsPage openGroups();

}
