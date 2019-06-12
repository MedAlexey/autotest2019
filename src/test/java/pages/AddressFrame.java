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
    protected void check(WebDriver driver) {
        Assert.assertTrue( "Не дождались кнопки сохранения",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SAVE_BUTTON)));

        Assert.assertTrue( "Не дождались поля ввода ФИО",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(USER_NAME_INPUT_FIELD)));

        Assert.assertTrue( "Не дождались поля ввода населённого пункта",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SETTLEMENT_INPUT_FIELD)));

        Assert.assertTrue( "Не дождались кнопки созданиия новой группы",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(HOUSE_NUMBER_INPUT_FIELD)));

        Assert.assertTrue( "Не дождались поля ввода номера квартиры/офиса",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(APARTMENT_INPUT_NUMBER)));

        Assert.assertTrue( "Не дождались поля ввода индекса",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(INDEX_INPUT_NUMBER)));

        Assert.assertTrue( "Не дождались поля ввода номера телефона",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(PHONE_NUMBER_INPUT_FIELD)));

        Assert.assertTrue( "Не дождались кнопки закрытия фрейма",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(CLOSE_BUTTON)));
    }
}
