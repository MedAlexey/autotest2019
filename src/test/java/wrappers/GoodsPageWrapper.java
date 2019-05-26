package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoodsPageWrapper {
    private final WebElement cardProduct;
    private final WebDriver driver;
    private final By CARD_PRICE = By.xpath(".//span [contains(@class,'mall-price __rub')]");
    private final By CARD_NAME=By.xpath(".//*[@class='mall-title __item lp']");

    public GoodsPageWrapper(WebDriver driver, WebElement cardProduct){
        this.cardProduct = cardProduct;
        this.driver = driver;
    }

    public String getPrice(){
        return cardProduct.findElement(CARD_PRICE).getText();
    }

    public String getName(){
        return cardProduct.findElement(CARD_NAME).getText();
    }

    public ProductPage openProduct(){
        cardProduct.findElement(CARD_NAME).click();
        return new ProductPage(driver);
    }

}
