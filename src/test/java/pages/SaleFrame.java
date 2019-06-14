package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaleFrame extends BasePage {

    private static final By TEXT_HOLDER =
            By.xpath(".//*[@class='posting_itx emoji-tx h-mod js-ok-e js-posting-itx ok-posting-handler']");
    private static final By SHARE = By.xpath(".//*[@class='posting_f_ac' and contains(text(), 'Поделиться')]");

    public SaleFrame(WebDriver driver) {
        super(driver);
    }

    public void writeComment(String text) {
        sendKeys(TEXT_HOLDER, text);
    }

    public void share(){
        click(SHARE);
    }

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3, SHARE,"Кнопка Поделиться не загрузилась", "Кнопка Поделиться загружена");
    }


}
