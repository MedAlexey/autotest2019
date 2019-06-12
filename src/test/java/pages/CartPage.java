package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.CartPageTransformer;
import wrappers.CartPageWrapper;

import java.util.List;

public class CartPage extends BasePage {

    private static final By DELETE_BUTTON = By.xpath(".//*[@class='mall-item_dsc mt-4x']/a");
    private static final By CART_CARD = By.xpath(".//*[@class='mall-item __separator-basket __basket __flex']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    //возвращает список обернутых товаров
    public List<CartPageWrapper> getWrapCart(){
        List<WebElement> elements = driver.findElements(CART_CARD);
        return CartPageTransformer.wrapCart(elements, driver);
    }

    //удаляет первый товар
    public CartPage deleteFirstCart(){
        click(DELETE_BUTTON);
        return new CartPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {

    }
}
