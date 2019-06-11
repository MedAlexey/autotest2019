package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShareWithMessageFrame extends BasePage implements IShareFrame {

    private static final By PORTLET_NAME =
            By.xpath(".//*[@class='portlet_h_name_t' and contains(text(), 'Отправить сообщением')]");
    private static final By CLOSE_ICON = By.xpath(".//*[@class='ic modal-new_close_ico']");
    private static final By POSTING_SUBMIT_BUTTON = By.xpath(".//*[@class='posting_submit button-pro']");
    private static final By SEARCH_INPUT = By.xpath(".//*[@class='it_w search-input']");
    private static final By TEXT_HOLDER =
            By.xpath(".//*[@class='posting_itx emoji-tx h-mod js-ok-e js-posting-itx ok-posting-handler']");

    public ShareWithMessageFrame(WebDriver driver) {
        super(driver);
    }

    // выбираем друга из списка
    public void choosePerson(String name) {
        click(SEARCH_INPUT);
        click(By.xpath(".//*[@class='ucard-mini_cnt_i ellip' and contains(text(), '" + name + "')]"));
    }

    // закрыть данный фрейм
    @Override
    public void closeFrame() {
        click(CLOSE_ICON);
    }

    // нажать кнопку "поделиться"
    @Override
    public void share() {
        click(POSTING_SUBMIT_BUTTON);
    }

    // написать текст в поле ввода комментария
    @Override
    public void writeText(String text) {
        sendKeys(TEXT_HOLDER, text);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue( "Не дождались названия фрейма",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(PORTLET_NAME)));

        Assert.assertTrue( "Не дождались кнопки сзакрытия фрейма",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(CLOSE_ICON)));

        Assert.assertTrue( "Не дождались кнопки \"поделиться\"",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(POSTING_SUBMIT_BUTTON)));

        Assert.assertTrue( "Не дождались поля ввода комментария",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(TEXT_HOLDER)));
    }
}
