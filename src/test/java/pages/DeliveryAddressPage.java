package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // поиск нужного адреса среди имеющихся
    public boolean addressIsCorrect(String expName,
                                    String expPhoneNumber,
                                    String expIndex,
                                    String expApartmentNumber,
                                    String expHouseNumber,
                                    String expSettlement,
                                    List<AddressesPageWrapper> addresses) {

        for (AddressesPageWrapper address: addresses) {
            String curName = address.getName();
            String curPhoneNumber = address.getPhone();
            String curIndex = address.getAddress().split(", ")[4];
            String curApartmentNumber = address.getAddress().split(", ")[3];
            String curHouseNumber = address.getAddress().split(", ")[2];
            String curSettlement = address.getAddress().split(", ")[1];

            if (curName.equals(expName) &&
                    curPhoneNumber.equals("+7" + expPhoneNumber) &&
                    curIndex.equals(expIndex) &&
                    curApartmentNumber.equals(expApartmentNumber) &&
                    curHouseNumber.equals(expHouseNumber) &&
                    curSettlement.equals(expSettlement)) {
                return true;
            }
        }

        return false;
    }

    // проверка на наличия кнопок
    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver, 10, ADD_ADDRESS,
                "Не дождались кнопки 'Добавить адрес'",
                "Кнопка 'Добавить адрес' загружена");

    }
}
