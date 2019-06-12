package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.DialogPageTransformer;
import wrappers.DialogPageWrapper;

import java.util.List;

public class DialogPage extends BasePage {

    private static final By DIALOG_CARD = By.xpath(".//*[contains(@class,'chats_i h-mod')]");
    private static final By NAME_SHARE_PRODUCT = By.xpath(".//*[@class='media-link_h_a']");
    private static final By DELETE_BUTTON = By.xpath(".//*[@data-l='t,deleteMsg']");
    private static final By DELETE_MENU= By.xpath(".//*[@class='inlineBlock']");

    public DialogPage(WebDriver driver) {
        super(driver);
    }

    //возвращает обернутые диалоги
    public List<DialogPageWrapper> getWrapDialog(){
        List<WebElement> elements = driver.findElements(DIALOG_CARD);
        return DialogPageTransformer.wrapDialog(elements,driver);
    }

    //открывает диалог по имени
    //тут нужно ассерт придумать
    public DialogPage openDialogByName(List<DialogPageWrapper> dialogCards, String nameFriend) {
        for (DialogPageWrapper card : dialogCards) {
            if (card.getName().equals(nameFriend)) {
                return card.openDialog();
            }
        }
        return null;
    }

    //возвращает название продукта которым с вами поделились
    public String getNameShareProduct(){
        return driver.findElement(NAME_SHARE_PRODUCT).getText();
    }

    //нажимает на кнопку удаления
    public DeleteMessagePage deleteShareMessage(){
        driver.findElement(DELETE_MENU).click();
        driver.findElement(DELETE_BUTTON).click();
        return new DeleteMessagePage(driver);
    }

    @Override
    protected void check(WebDriver driver) {

    }
}
