package wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartPageTransformer {

    private CartPageTransformer(){}

    public static List<CartPageWrapper> wrapCart(List<WebElement> cards, WebDriver driver) {
        if (cards.isEmpty()){
            return Collections.emptyList();
        }
        List<CartPageWrapper> wrapCart = new ArrayList<CartPageWrapper>();
        for (WebElement card : cards){
            wrapCart.add(new CartPageWrapper(driver, card));
        }
        return wrapCart;
    }
}
