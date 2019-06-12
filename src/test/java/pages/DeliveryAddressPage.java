package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.AddressesPageTransformer;
import wrappers.AddressesPageWrapper;

import java.util.List;

public class DeliveryAddressPage extends BasePage {

    private static final By ADD_ADDRESS = By.xpath(".//*[@class='portlet_h_inf']");
    private static final By DELETE_ICON = By.xpath(".//*[@class='ic12 ic12_delete ico-inline-middle']");
    private static final By ADDRESSES_CARD = By.xpath(".//*[@class='mall-address __shift mall-column __center']");

    public DeliveryAddressPage(WebDriver driver) {
        super(driver);
    }

    // добавление нового адреса
    public AddressFrame addAddress() {
        click(ADD_ADDRESS);

        return new AddressFrame(driver);
    }

    // удаление адреса
    public AddressRemovalFrame deleteAddress() {
        click(DELETE_ICON);

        return new AddressRemovalFrame(driver);

    }

    //возвращает список обернутых адресов
    public List<AddressesPageWrapper> getWrapAddresses(){
        List<WebElement> elements = driver.findElements(ADDRESSES_CARD);
        return AddressesPageTransformer.wrapAddresses(elements, driver);
    }

    // проверка на наличия кнопок
    @Override
    protected void check(WebDriver driver) {

        Assert.assertTrue("Не дождались кнопки 'Добавить адрес'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ADD_ADDRESS)));
    }
}
