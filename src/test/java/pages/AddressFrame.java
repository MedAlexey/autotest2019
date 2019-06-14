package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressFrame extends BasePage {

    private static final By SAVE_BUTTON = By.xpath(".//*[@name='button_save' and @value='Сохранить']");
    private static final By USER_NAME_INPUT_FIELD = By.xpath(".//*[@id='field_userName']");
    private static final By SETTLEMENT_INPUT_FIELD = By.xpath(".//*[@id='field_addressEdit_SearchInput']");
    private static final By HOUSE_NUMBER_INPUT_FIELD = By.xpath(".//*[@id='field_adrFL']");
    private static final By APARTMENT_INPUT_NUMBER = By.xpath(".//*[@id='field_adrSL']");
    private static final By INDEX_INPUT_NUMBER = By.xpath(".//*[@id='field_zipcode']");
    private static final By PHONE_NUMBER_INPUT_FIELD =By.xpath(".//*[@id='field_userPhone']");
    private static final By CLOSE_BUTTON = By.xpath(".//*[@id='nohook_modal_close']");
    private static final By FRAME_IS_CLOSED = By.xpath(".//*[@id='hook_Block_PopLayer' and @style='display: none;']");

    public AddressFrame(WebDriver driver) {
        super(driver);
    }

    // ввод всех даннных
    public void enterData(String name,
                          String phoneNumber,
                          String index,
                          String apartmentNumber,
                          String houseNumber,
                          String settlement) {
        enterName(name);
        enterPhoneNumber(phoneNumber);
        enterIndex(index);
        enterApartmentNumber(apartmentNumber);
        enterHouseNumber(houseNumber);
        enterSettlement(settlement);
    }

    // ввод ФИО пользователя
    public void enterName(String name) {
        sendKeys(USER_NAME_INPUT_FIELD, name);
    }

    // ввод номера телефона
    public void enterPhoneNumber(String number) {
        sendKeys(PHONE_NUMBER_INPUT_FIELD, number);
    }

    // ввод индекса
    public void enterIndex(String index) {
        sendKeys(INDEX_INPUT_NUMBER, index);
    }

    // ввод номера квартиры/офиса
    public void enterApartmentNumber(String number) {
        sendKeys(APARTMENT_INPUT_NUMBER, number);
    }

    // ввод улицы, дома
    public void enterHouseNumber(String number) {
        sendKeys(HOUSE_NUMBER_INPUT_FIELD, number);
    }

    // ввод населённого пункта
    public void enterSettlement(String settlement) {
        sendKeys(SETTLEMENT_INPUT_FIELD, settlement);
    }

    // закрыть окно
    public void close() {
        click(CLOSE_BUTTON);
        // ждём закрытия фрейма
        assertTrue(driver, 10, FRAME_IS_CLOSED, "Не дождались закрытия фрейма", "Фрейм закрылся");
    }


    // сохранить данные
    public DeliveryAddressPage save() {
        click(SAVE_BUTTON);
        assertTrue(driver, 10, FRAME_IS_CLOSED, "Не дождались закрытия фрейма", "Фрейм закрыт");
        return new DeliveryAddressPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver, 3, SAVE_BUTTON,
                "Не дождались кнопки сохранения",
                "Кнопка сохранения загрузилась");

        assertTrue(driver, 3, USER_NAME_INPUT_FIELD,
                "Не дождались поля ввода ФИО",
                "Поле ввода ФИО загружено");

        assertTrue(driver, 3, SETTLEMENT_INPUT_FIELD,
                "Не дождались поля ввода населённого пункта",
                "Полеввода населённого пункта загружено");

        assertTrue(driver, 3, HOUSE_NUMBER_INPUT_FIELD,
                "Не дождались кнопки созданиия новой группы",
                "Кнопка создания новой группы загружена");

        assertTrue(driver, 3, APARTMENT_INPUT_NUMBER,
                "Не дождались поля ввода номера квартиры/офиса",
                "Поле ввода номера квартиры/офиса загружено");

        assertTrue(driver, 3, INDEX_INPUT_NUMBER,
                "Не дождались поля ввода индекса",
                "Поле ввода индекса загружено");

        assertTrue(driver, 3, PHONE_NUMBER_INPUT_FIELD,
                "Не дождались поля ввода номера телефона",
                "Поле ввода номера телефона загружено");

        assertTrue(driver, 3, CLOSE_BUTTON,
                "Не дождались кнопки закрытия фрейма",
                "Кнопка закрытия фрейма загружена");
    }
}
