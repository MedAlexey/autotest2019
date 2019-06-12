package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FivePercentDiscount extends BasePage implements IProductPage{

    private static final By SALE = By.xpath(".//*[@class='mall-card_section' and contains(text(), 'Получить скидку 5%')]");

    public FivePercentDiscount(WebDriver driver) {
        super(driver);
    }

    public boolean isAvailable() {
        return isElementPresent(SALE);
    }

    @Override
    public FivePercentDiscount checkFivePercentDiscount() {
        isAvailable();
        return new FivePercentDiscount(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'скидка 5% на товар'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SALE)));
    }

    @Override
    public void addToCart() {

    }

    @Override
    public void chooseSize() {

    }

    @Override
    public void increaseQuantity() {

    }

    @Override
    public void decreaseQuantity() {

    }

    @Override
    public void chooseColor() {

    }

    @Override
    public ShareInGroupFrame shareInGroup() {
        return null;
    }

    @Override
    public ShareWithTextFrame shareWithText() {
        return null;
    }

    @Override
    public void shareNow() {

    }

    @Override
    public ShareWithMessageFrame shareInMessage() {
        return null;
    }

    @Override
    public void refresh() {

    }
}
