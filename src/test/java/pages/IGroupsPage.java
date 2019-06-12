package pages;

public interface IGroupsPage {

    // открыть "Мои заказы"
    MyOrdersPage openMyOrders();

    // открыть страницу пользователя
    UserMainPage openUserMainPage();

    // открыть страницу групп
    GroupsPage openGroups();

}