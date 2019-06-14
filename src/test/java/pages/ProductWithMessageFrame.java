package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductWithMessageFrame extends BasePage {

    public ProductWithMessageFrame (WebDriver driver) {
        super(driver);
    }

    // получить текст комментария
    public String getCommentText(){
        String com = driver.findElement(By.cssSelector(".//*[@class='media-text_cnt']/*")).getText();
        return com;
    }

    @Override
    protected void check(WebDriver driver) {

    }
}
