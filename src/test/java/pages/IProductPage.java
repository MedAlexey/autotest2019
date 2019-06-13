package pages;

public interface IProductPage {

    String title = null;
    String size = null;
    String color = null;

    // добавить в корзину
    void addToCart();

    // выбрать размер
    String chooseSize();

    // увеличить количество
    void increaseQuantity();

    // уменьшить количество
    void decreaseQuantity();

    // выбрать цвет
    String chooseColor(int number);

    // выбрать цвет
    String chooseColor();

    //  получение количества цветов данного товара
    int getNumberOfColors();

    // поделиться в группе
    ShareInGroupFrame shareInGroup();

    // поделиться с текстом
    ShareWithTextFrame shareWithText();

    // поделиться сейчас
    void shareNow();

    // поделиться в сообщении
    ShareWithMessageFrame shareInMessage();

    // проверить наличие кнопки "Получить скидку 5%"
    FivePercentDiscount checkFivePercentDiscount();

    // обновить страницу
    ProductPage refresh();

    // получение названия товара
    String getProductName();

    void close();

}
