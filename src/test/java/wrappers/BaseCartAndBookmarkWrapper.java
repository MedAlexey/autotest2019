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
    //Вытащить содержимое поля ЦВЕТ: без "Цвет:" нельзя
    public String getColor(){
        if (cardCart.findElements(COLOR).size() > 0){
            String color = cardCart.findElement(COLOR).getText();
            if (color.contains("Цвет:")) {
                return color.replaceAll("Цвет:","");
            }
        }
        return "";
    }

    //возваращает размер
    //У поля "Цвет" и "Размер" одинаковые xpath
    //Если работает долго уменьши ожидание
    //Вытащить содержимое поля РАЗМЕР: без "Размер" нельзя
    public String getSize(){
        if (cardCart.findElements(SIZE).size() > 0){
            String size = cardCart.findElement(SIZE).getText();
            if (size.contains("Размер")) {
                return size.replaceAll("Размер", "");
            }
        }
        return "";
    }
}
