package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends BasePage {

    private static final By GOODS_BUTTON = By.xpath(".//*[contains(text(),'Товары') and contains(@class,'tico null')]");
    private static final By MESSAGES_BUTTON = By.xpath(".//* [@id='msg_toolbar_button']");
    private static final By CHECK_PRODUCT = By.xpath(".//* [@class='mall-media-link_a']");
    private static final By REALLY_PRODUCT_NAME = By.xpath(".//* [@class='mall-card_t mb-4x']");
    private static final By CLOSE_FRAME_BUTTON = By.xpath(".//* [@class='ic modal-new_close_ico']");

    UserMainPage(WebDriver driver) {
        super(driver);
    }

    public GoodsPage openGoodsPage(){
        click(GOODS_BUTTON);
        return new GoodsPage(driver);
    }

    public MessagesPage openMessagesPage(){
        click(MESSAGES_BUTTON);
        return new MessagesPage(driver);
    }

    public boolean checkProductOnUserMainPage(String expectedProduct){
        click(CHECK_PRODUCT);
        if (driver.findElement(REALLY_PRODUCT_NAME).getText().equals(expectedProduct)){
            click(CLOSE_FRAME_BUTTON);
            return true;}
        click(CLOSE_FRAME_BUTTON);
        return false;
    }

    @Override
    void check(WebDriver driver){

    }
}
