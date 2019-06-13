package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By LOGIN_LOCATOR = By.xpath(".//input[@name='st.email']");
    private static final By PASSWORD_LOCATOR = By.xpath(".//input[@name='st.password']");
    private static final By SUBMIT_LOCATOR = By.xpath(".//input[@data-l='t,sign_in']");
    private static final By LOGIN_CARD_LOCATOR = By.xpath(".//div[@class='anonym_login_cnt js-login-state']");
    private static final By REGISTRATION_LOCATOR = By.xpath(".//a[@class='button-pro __sec __wide mb-3x' and contains(text(), 'Регистрация')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public UserMainPage login(String email, String password) {
        sendKeys(LOGIN_LOCATOR, email);
        sendKeys(PASSWORD_LOCATOR, password);
        click(SUBMIT_LOCATOR);

        return new UserMainPage(driver);
    }

    //Добавить ожидание
    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3, LOGIN_LOCATOR,"Поля логина не загрузилось", "Поле логина загружено");
        assertTrue(driver,3,PASSWORD_LOCATOR, "Поле пароля загурзилось", "Поле пароля загруженно");
        assertTrue(driver,3,SUBMIT_LOCATOR,"кнопка 'войти' не загруженна", "кнопка 'войти' загруженна" );
    }
}
