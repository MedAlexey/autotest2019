package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseCartAndBookmarkWrapper {
    final WebElement cardCart;
    private final WebDriver driver;

    private static final By NAME = By.xpath(".//*[@class='mall-title']");
    private static final By PRICE = By.xpath(".//*[@class='mall-price __rub']");
    private static final By COLOR = By.xpath(".//span[1][@class='mall-item_el']");
    private static final By SIZE = By.xpath(".//span[last()][@class='mall-item_el']");

    public BaseCartAndBookmarkWrapper(WebDriver driver, WebElement cardCart){
        this.cardCart = cardCart;
        this.driver = driver;
    }


    //возвращает название товара
    public String getName(){return cardCart.findElement(NAME).getText();}

    //возвращает цену товара
    public String getPrice(){return cardCart.findElement(PRICE).getText();}

    //возварщает цвет товара
    //Если работает долго уменьши ожидание
    //У поля "Цвет" и "Размер" одинаковые xpath
    /*проверяем наличие элемента в карде
     * если true, проверяем наличие в строке "Цвет:"
     * если true, удаляем "Цвет:"(строку без "Цвет:" вытащить не получается), если false return null
     * return все после "Цвет:"
     * надеемся что никто в поле "Цвет" не будет писать "Цвет:" :D*/
    public String getColor(){
        if (cardCart.findElements(COLOR).size() > 0){
            String color = cardCart.findElement(COLOR).getText();
            if (color.contains("Цвет:")) {
                return color.replaceAll("Цвет:","");
            }
        }
        return null;
    }

    //возваращает размер
    //У поля "Цвет" и "Размер" одинаковые xpath
    //Если работает долго уменьши ожидание
    /*проверяем наличие элемента в карде
     * если true, проверяем наличие в строке "Размер"
     * если true, удаляем "Размер"(строку без "Размер" вытащить не получается), если false return null
     * return все после "Размер"
     * надеемся что никто в поле "Размер" не будет писать "Размер" :D*/
    public String getSize(){
        if (cardCart.findElements(SIZE).size() > 0){
            String size = cardCart.findElement(SIZE).getText();
            if (size.contains("Размер")) {
                return size.replaceAll("Размер", "");
            }
        }
        return null;
    }
}
