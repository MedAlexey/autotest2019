package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressRemovalFrame extends BasePage {

    private static final By DELETE_BUTTON = By.xpath(".//*[@value='Удалить']");

    AddressRemovalFrame(WebDriver driver) {
        super(driver);
    }

    //нажимаем кнопку "удалить"
    public DeliveryAddressPage deleteAddress(){
        click(DELETE_BUTTON);
        return new DeliveryAddressPage(driver);
    }

    // проверяем наличие кнопки "удалить"
    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'Удалить'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(DELETE_BUTTON)));

    }
}
