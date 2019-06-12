package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CartPageWrapper extends BaseCartAndBookmarkWrapper {

    private static final By NUMBER = By.xpath(".//*[@class='mall-items-counter_item __input']/input");

    public CartPageWrapper(WebDriver driver, WebElement cardCart) {
        super(driver, cardCart);
    }

    //возвращает кол-во
    public String getNumber(){return cardCart.findElement(NUMBER).getAttribute("value");}
}