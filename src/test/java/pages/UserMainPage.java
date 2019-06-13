//Добавить ожидания, безопасные клики

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class UserMainPage extends BasePage {

    private static final By GOODS_BUTTON = By.xpath(".//*[@class = 'tico null' and contains(text(),'Товары')]");
    private static final By MESSAGES_BUTTON = By.xpath(".//* [@id='msg_toolbar_button']");
    private static final By CHECK_PRODUCT = By.xpath(".//* [@class='mall-media-link_a']");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    //Открываем страницу с товароми
    public GoodsPage openGoodsPage(){
        click(GOODS_BUTTON);
        return new GoodsPage(driver);
    }

    //открываем страницу с сообщениями
    public DialogPage openMessagesPage(){
        click(MESSAGES_BUTTON);
        return new DialogPage (driver);
    }

    //открываем репост товара со стены юзера
    public ProductPageFrame openShareFromUserMainPage(){
        click(CHECK_PRODUCT);
        return new ProductPageFrame(driver);
    }

    @Override
    protected void check(WebDriver driver){
        assertTrue(driver,3,GOODS_BUTTON,"Кнопка Товары не загрузилась","Кнопка товары загрузилась");
        assertTrue(driver,3,MESSAGES_BUTTON,"Кнопка сообщения не загрузилась","Кнопка загрузилась");
        assertTrue(driver,3,CHECK_PRODUCT, "репост не загрузился", "Репост загрузился");
    }
}
