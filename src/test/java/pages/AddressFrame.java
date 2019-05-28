package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressFrame extends BasePage {

    private static final By SAVE_BUTTON = By.xpath(".//*[@name='button_save' and @value='Сохранить']");
    private static final By USER_NAME_INPUT_FIELD = By.xpath(".//*[@id='field_userName']");
    private static final By SETTLEMENT_INPUT_FIELD = By.xpath(".//*[@id='field_addressEdit_SearchInput']");
    private static final By HOUSE_NUMBER_INPUT_FIELD = By.xpath(".//*[@id='field_adrFL']");
    private static final By APARTMENT_INPUT_NUMBER = By.xpath(".//*[@id='field_adrSL']");
    private static final By INDEX_INPUT_NUMBER = By.xpath(".//*[@id='field_zipcode']");
    private static final By PHONE_NUMBER_INPUT_FIELD =By.xpath(".//*[@id='field_userPhone']");
    private static final By CLOSE_BUTTON = By.xpath(".//*[@id='nohook_modal_close']");

    public AddressFrame(WebDriver driver) {
        super(driver);
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
    }

    // сохранить данные
    public void save() {
        click(SAVE_BUTTON);
    }

    @Override
    void check(WebDriver driver) {
        driver.findElement(SAVE_BUTTON);
        driver.findElement(USER_NAME_INPUT_FIELD);
        driver.findElement(SETTLEMENT_INPUT_FIELD);
        driver.findElement(HOUSE_NUMBER_INPUT_FIELD);
        driver.findElement(APARTMENT_INPUT_NUMBER);
        driver.findElement(INDEX_INPUT_NUMBER);
        driver.findElement(PHONE_NUMBER_INPUT_FIELD);
        driver.findElement(CLOSE_BUTTON);
    }
}
