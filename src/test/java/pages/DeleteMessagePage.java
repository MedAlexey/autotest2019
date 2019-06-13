package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteMessagePage extends BasePage {

    private static final By DELETE_BUTTON = By.xpath(".//*[@class='button-pro form-actions_yes']");

    DeleteMessagePage(WebDriver driver) {
        super(driver);
    }

    //поддверждает удаление
    public DialogPage pressDelete(){
        click(DELETE_BUTTON);
        return new DialogPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver, 3,DELETE_BUTTON,"кнопка удалить не загрузилась", "кнопка удалить загрузилась");
    }
}
