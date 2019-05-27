package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShareInGroupFrame extends BasePage implements IShareFrame {

    private static final By PORTLET_NAME = By.xpath(".//*[@class='portlet_h_name_t']");
    private static final By CLOSE_ICON = By.xpath(".//*[@class='ic modal-new_close_ico']");
    private static final By POSTING_SUBMIT_BUTTON = By.xpath(".//*[@class='posting_submit button-pro']");
    private static final By SEARCH_INPUT = By.xpath(".//*[@class='it_w search-input']");
    private static final By TEXT_HOLDER =
            By.xpath(".//*[@class='posting_itx emoji-tx h-mod js-ok-e js-posting-itx ok-posting-handler']");


    public ShareInGroupFrame(WebDriver driver) {
        super(driver);
    }

    public void chooseGroup(String groupName) {
        driver.findElement(SEARCH_INPUT).click();
        driver.findElement(
                By.xpath(".//*[@class='ucard-mini_cnt_i ellip' and contains(text(), '" + groupName + "')]")).click();
    }

    @Override
    void check(WebDriver driver) {
        driver.findElement(PORTLET_NAME);
        driver.findElement(CLOSE_ICON);
        driver.findElement(POSTING_SUBMIT_BUTTON);
        driver.findElement(SEARCH_INPUT);
        driver.findElement(TEXT_HOLDER);
    }

    @Override
    public void closeFrame() {
        click(CLOSE_ICON);
    }

    @Override
    public void share() {
        click(POSTING_SUBMIT_BUTTON);
    }

    @Override
    public void writeText(String text) {
        sendKeys(TEXT_HOLDER, text);
    }
}
