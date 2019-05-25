package pages;

public interface IProductPage {

    String title = null;
    String size = null;
    String color = null;


    // добавить в корзину
    void addToCart();

    // выбрать размер
    void chooseSize();

    // увеличить количество
    void increaseQuantity();

    // уменьшить количество
    void decreaseQuantity();

    // выбрать цвет
    void chooseColor();

    // поделиться в группе
    ShareInGroupFrame shareInGroup();

    // поделиться с текстом
    ShareWithTextFrame shareWithText();

    // поделиться сейчас
    void shareNow();

    // поделиться в сообщении
    ShareWithMessageFrame shareInMessage();

    // проверить наличие кнопки "Получить скидку 5%"
    FivePercentDiscountPromice checkFivePercentDiscount();

    // обновить страницу
    void refresh();

}
