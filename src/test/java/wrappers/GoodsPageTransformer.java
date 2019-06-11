package wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoodsPageTransformer {

    private GoodsPageTransformer(){}

    public static List<GoodsPageWrapper> wrapProducts(List<WebElement> cards, WebDriver driver) {
        if (cards.isEmpty()){
            return Collections.emptyList();
        }
        List<GoodsPageWrapper> wrapProducts = new ArrayList<>();
        for (WebElement card : cards){
            wrapProducts.add(new GoodsPageWrapper(driver, card));
        }
        return wrapProducts;
    }
}
