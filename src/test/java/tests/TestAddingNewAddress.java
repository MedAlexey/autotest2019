package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.*;
import wrappers.AddressesPageWrapper;

import java.util.List;

/**
 *  логинимся
 *  переходим в товары-> мои заказы -> адреса доставки
 *  создаем адрес
 *  сравниваем все поля, всех адресов, проверяем правильность созданных элементов
 *  удаляем добавленнй адрес
 *
 */
public class TestAddingNewAddress extends BaseTest {

    private String name = "my name";
    private String phoneNumber = "(880)555-35-35";
    private String index = "123456";
    private String apartmentNumber = "myStreet 2";
    private String houseNumber = "15/a";
    private String settlement = "Kerch";

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getLogin(), config.getPassword());
    }

    @Test
    public void addingNewAddress() {
        // делаем окно на полный экран
        driver.manage().window().maximize();

        // переходим в адреса доставки
        UserMainPage userMainPage = new UserMainPage(driver);
        GoodsPage goodsPage = userMainPage.openGoodsPage();
        MyOrdersPage myOrdersPage = goodsPage.openMyOrders();
        DeliveryAddressPage deliveryAddressPage = myOrdersPage.openAddresses();

        // добавление адрес
        AddressFrame addressFrame = deliveryAddressPage.addAddress();
        addressFrame.enterData(
                name,
                phoneNumber,
                index,
                apartmentNumber,
                houseNumber,
                settlement
        );
        addressFrame.save();

        new EventFiringWebDriver(driver).navigate().refresh();

        // проверяем адрес
        Assert.assertTrue(addressIsCorrect(deliveryAddressPage.getWrapAddresses()));

    }

    // поиск нужного адреса среди имеющихся
    private boolean addressIsCorrect(List<AddressesPageWrapper> addresses) {
        boolean result = false;

        for (AddressesPageWrapper address: addresses) {
            String curName = address.getName();
            String curPhoneNumber = address.getPhone();
            String curIndex = address.getAddress().split(", ")[4];
            String curApartmentNumber = address.getAddress().split(", ")[3];
            String curHouseNumber = address.getAddress().split(", ")[2];
            String curSettlement = address.getAddress().split(", ")[1];

            if (curName.equals(name) &&
                    curPhoneNumber.equals("+7" + phoneNumber) &&
                    curIndex.equals(index) &&
                    curApartmentNumber.equals(apartmentNumber) &&
                    curHouseNumber.equals(houseNumber) &&
                    curSettlement.equals(settlement)) {
                return result = true;
            }
        }

        return result;
    }


    @After
    public void sweep() {
        // удаление тестируемого адреса
        DeliveryAddressPage deliveryAddressPage = new DeliveryAddressPage(driver);
        AddressRemovalFrame addressRemovalFrame = deliveryAddressPage.deleteAddress();
        addressRemovalFrame.deleteAddress();

        // закрытие браузера
        driver.quit();

    }



}
