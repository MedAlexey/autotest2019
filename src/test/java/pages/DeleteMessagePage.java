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
        driver.findElement(DELETE_BUTTON).click();
        return new DialogPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {

    }
}
