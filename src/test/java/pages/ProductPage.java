package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProductPage extends BasePage implements IProductPage {

    private static final By ADD_TO_CART_BUTTON = By.className("js-mall-card-button-to-basket");
    private static final By BUY_BUTTON = By.className("js-mall-card-button-buy");
    private static final By MALL_CARD = By.className("mall-card_section");
    private static final By SHARE_BUTTON = By.xpath(".//*[@data-type='RESHARE']");
    private static final By SHARE_NOW_BUTTON = By.xpath(".//*[@data-l='t,now']");
    private static final By SHARE_IN_GROUP = By.xpath(".//*[@data-l='t,group']");
    private static final By SHARE_WITH_FEED = By.xpath(".//*[@data-l='t,feed']");
    private static final By SHARE_WITH_MESSAGE = By.xpath(".//*[@data-l='t,msg']");


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public void addToCart() {
        click(ADD_TO_CART_BUTTON);
    }

    @Override
    public void chooseSize() {
        //todo
    }

    @Override
    public void chooseColor() {
        //todo
    }

    @Override
    public ShareWithTextFrame shareWithText() {
        click(SHARE_BUTTON);
        click(SHARE_WITH_FEED);
        return new ShareWithTextFrame(driver);
    }

    @Override
    public ShareInGroupFrame shareInGroup() {
        click(SHARE_BUTTON);
        click(SHARE_IN_GROUP);
        return new ShareInGroupFrame(driver);
    }

    @Override
    public void shareNow() {
        click(SHARE_BUTTON);
        click(SHARE_NOW_BUTTON);
    }

    @Override
    public ShareWithMessageFrame shareInMessage() {
        click(SHARE_BUTTON);
        click(SHARE_WITH_MESSAGE);
        return new ShareWithMessageFrame(driver);
    }

    @Override
    public FivePercentDiscountPromice checkFivePercentDiscount() {
        return new FivePercentDiscountPromice();
    }

    @Override
    public void refresh() {
        new EventFiringWebDriver(driver).navigate().refresh();
    }

    @Override
    void check(WebDriver driver) {
        driver.findElement(BUY_BUTTON);
        driver.findElement(MALL_CARD);
        driver.findElement(SHARE_BUTTON);
    }
}
