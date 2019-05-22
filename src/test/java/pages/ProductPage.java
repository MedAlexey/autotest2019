package pages;

public interface ProductPage {

    String title = null;
    String size = null;
    String color = null;

    // добавить в закладки
    void addToBookmark();

    // добавить в корзину
    void addToCart();

    // выбрать размер
    void chooseSize();

    //выбрать кол-во
    void chooseQuantity();

    // выбрать цвет
    void chooseColor();

    // поделиться в группе
    void shareInGroup();

    // поделиться с текстом
    void shareWithText();

    // поделиться сейчас
    void shareNow();

    // поделиться в сообщении
    void shareInMessage();

    // проверить наличие кнопки "Получить скидку 5%"
    void checkFivePercentDiscount();

    // обновить страницу
    void refresh();

}
