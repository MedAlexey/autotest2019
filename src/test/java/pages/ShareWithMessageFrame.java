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
        driver.findElement(
                By.xpath(".//*[@class='ucard-mini_cnt_i ellip' and contains(text(), '" + name + "')]")).click();
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
    protected void check(WebDriver driver) {
        assertTrue(driver, 10, PORTLET_NAME,
                "Не дождались названия фрейма",
                "Название фрейма загружено");

        assertTrue(driver, 10, CLOSE_ICON,
                "Не дождались кнопки закрытия фрейма",
                "Кнопка закрытия фрейма загружена");

        assertTrue(driver, 10, POSTING_SUBMIT_BUTTON,
                "Не дождались кнопки \"поделиться\"",
                "Кнопка \"поделиться\" загружена");

        assertTrue(driver, 10, TEXT_HOLDER,
                "Не дождались поля ввода комментария",
                "Поле ввода комментария загружено");

    }
}
