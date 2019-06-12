package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.DialogPage;

public class DialogPageWrapper {

    private static final By NAME_FRIEND = By.xpath(".//*[@class='chats_i_h ellip']");
    private static final By FOR_CLICK = By.xpath(".//*[@class='chats_i_ovr']");

    WebDriver driver;
    WebElement cardDialog;

    public DialogPageWrapper(WebDriver driver, WebElement cardDialog){
        this.driver = driver;
        this.cardDialog=cardDialog;
    }

    public String getName(){
        return cardDialog.findElement(NAME_FRIEND).getText();
    }

    public DialogPage openDialog(){
        cardDialog.findElement(FOR_CLICK).click();
        return new DialogPage(driver);
    }
}