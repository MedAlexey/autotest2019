package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.*;


/**
 *  логинимся
 *  переходим в товары-> мои заказы -> адреса доставки
 *  создаем адрес
 *  сравниваем все поля, всех адресов, проверяем правильность созданных элементов
 *  удаляем добавленнй адрес
 *
 */
public class TestAddingNewAddress extends BaseTest {

    private static final String NAME = "my NAME";
    private static final String PHONE_NUMBER = "(880)555-35-35";
    private static final String INDEX = "123456";
    private static final String APARTMENT_NUMBER = "myStreet 2";
    private static final String HOUSE_NUMBER = "15/a";
    private static final String SETTLEMENT = "Kerch";

    @Test
    public void addingNewAddress() {

        // логинимся и  переходим в адреса доставки
        DeliveryAddressPage deliveryAddressPage = new LoginPage(driver).
                login(config.getLogin(), config.getPassword()).
                openGoodsPage().
                openMyOrders().
                openAddresses();

        // добавление адрес
        AddressFrame addressFrame = deliveryAddressPage.addAddress();
        addressFrame.enterData(
                NAME,
                PHONE_NUMBER,
                INDEX,
                APARTMENT_NUMBER,
                HOUSE_NUMBER,
                SETTLEMENT
        );
        addressFrame.save();

        //new EventFiringWebDriver(driver).navigate().refresh();

        // проверяем адрес
        Assert.assertTrue(deliveryAddressPage.addressIsCorrect(
                NAME,
                PHONE_NUMBER,
                INDEX,
                APARTMENT_NUMBER,
                HOUSE_NUMBER,
                SETTLEMENT,
                deliveryAddressPage.getWrapAddresses())
        );

    }


    @After
    public void sweep() {
        // удаление тестируемого адреса
        new DeliveryAddressPage(driver).deleteAddress().deleteAddress();

        // закрытие браузера
        driver.quit();

    }



}
