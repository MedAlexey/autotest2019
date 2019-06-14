package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.className("js-mall-card-button-to-basket");
    private static final By BUY_BUTTON = By.className("js-mall-card-button-buy");
    private static final By MALL_CARD = By.className("mall-card_section");
    private static final By SHARE_BUTTON = By.xpath(".//*[@data-type='RESHARE']"); //????????????????

    static final By SHARE_NOW_BUTTON = By.xpath(".//*[@data-l='t,now']");
    static final By SHARE_IN_GROUP = By.xpath(".//*[@data-l='t,group']");
    static final By SHARE_WITH_FEED = By.xpath(".//*[@data-l='t,feed']");
    static final By SHARE_WITH_MESSAGE = By.xpath(".//*[@data-l='t,msg']");
    private static final By INCREASE_QUANTITY_BUTTON = By.xpath(".//*[@title='Добавить' and @type='button' and contains(text(),'+')]");
    private static final By DECREASE_QUANTITY_BUTTON = By.xpath(".//*[@title='Убрать' and @type='button' and contains(text(),'–')]");
    // поле с текущим цветом товара
    private static final By CURRENT_COLOR_NAME = By.xpath(".//*[@class='js-mall-card-color-dsc']");
    // поле с именем продукта
    private static final By PRODUCT_NAME = By.xpath(".//*[@class='mall-card_t mb-4x']");
    // слово "размер"
    private static final By PRODUCT_SIZE_WORD = By.xpath(".//*[@class='mall-title __bold' and contains(text(), 'Размер:')]");
    // юнит, отвечающий за определённый размер товара
    private static final By PRODUCT_SIZE_ITEM = By.xpath(".//*[@class='mall-card_label-el __size']");
    private static final By SALE = By.xpath(".//*[@class='mall-card_sale-button mb-3x']");
    String price = driver.findElement(By.xpath(".//*[@class='mall-price __big __rub js-mall-card-price']")).getText();

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // взять цену элемента
    public int getPrice(){
        return Integer.parseInt(price);
    }

    // добавление товара в корзину
    public void addToCart() {
        click(ADD_TO_CART_BUTTON);
    }


    // выбор размера случайным образом
    public String chooseSize() {

        if (isElementPresent(PRODUCT_SIZE_WORD)) {
            List<WebElement> dimensions = driver.
                    findElement(PRODUCT_SIZE_WORD).
                    findElement(By.xpath("..")).
                    findElements(PRODUCT_SIZE_ITEM);

            WebElement chosenElement = dimensions.get(new Random().nextInt(dimensions.size()));
            chosenElement.click();
            return chosenElement.getText();

        }

        return "";
    }


    // увеличить количество товара на 1
    public void increaseQuantity() {
        click(INCREASE_QUANTITY_BUTTON);
    }


    // уменьшить количество товара на 1
    public void decreaseQuantity() {
        click(DECREASE_QUANTITY_BUTTON);
    }


    /**
     * выбор цвета товара
     * по порядковому номеру или случайно
     * счёт начинается с 0
     * @param number порядковый номер цвета. Если равен 0, то цвет выбирается случайным образом
     * @return название выбранного цвета
     */
    public String chooseColor(int number) {
        List<WebElement> colors = getColors();

        if (colors.size() != 0) {
            number = (number == 0) ? new Random().nextInt(colors.size()) : number;
            colors.get(number).click();
            return driver.findElement(CURRENT_COLOR_NAME).getText();
        }

        return "";
    }


    /**
     * выбор случайного цвета товара
     * @return цвет выбранного товара
     */
    public String chooseColor() {
        return chooseColor(0);
    }


    // получение цветов данного товара
    private List<WebElement> getColors() {
        List<WebElement> colors = new ArrayList<>();

        if (isElementPresent(CURRENT_COLOR_NAME)) {
            colors = driver.
                    findElement(CURRENT_COLOR_NAME).
                    findElement(By.xpath("..")).
                    findElements(By.xpath(".//*[@class='mall-card_choose-item']"));

        }

        return colors;
    }


    /**
     * получение количества цветов данного товара
     * @return количество цветов товара
     */
    public int getNumberOfColors() {
        return getColors().size();
    }


    private void clickShareButton() {
        click(SHARE_BUTTON);
    }

    // нажать на "дополнить своим текстом"
    public ShareWithTextFrame shareWithText() {
        this.clickShareButton();
        click(SHARE_WITH_FEED);
        return new ShareWithTextFrame(driver);
    }


    // нажать на "опубликовать в группе"
    public void shareInGroup() {
        this.clickShareButton();
        click(SHARE_IN_GROUP);
    }


    // нажать на "поделиться сейчас"
    public void shareNow() {
        this.clickShareButton();
        click(SHARE_NOW_BUTTON);
    }


    // нажать на "отправить сообщением"
    public ShareWithMessageFrame shareInMessage() {
        this.clickShareButton();
        click(SHARE_WITH_MESSAGE);
        return new ShareWithMessageFrame(driver);
    }


    // проверить наличие кнопки "получить скидку 5%"
    public void isFivePercentDiscountPresent() {
            Assert.assertTrue("Не нашли товар со скидкой",isElementPresent(SALE));
    }

    // получить скидку
    public SaleFrame getFivePercentDiscount(){
        click(SALE);
        return new SaleFrame(driver);
    }

    // обновить страницу
    public ProductPage refresh() {
        new EventFiringWebDriver(driver).navigate().refresh();
        return new ProductPage(driver);
    }

    // получение названия товара
    public String getProductName() {
        if (isElementPresent(PRODUCT_NAME)) {
            return driver.findElement(PRODUCT_NAME).getText();
        }
        return "";
    }

    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver, 10, BUY_BUTTON,
                "Не дождались кнопки \"купить\"",
                "Кнопка \"купить\" загружена");

        assertTrue(driver, 10, MALL_CARD,
                "Не дождались элементов управления товаром",
                "Элементы управления товаром загружены");

        assertTrue(driver, 10, SHARE_BUTTON,
                "Не дождались кнопки \"поделиться\"",
                "Кнопка \"поделиться\" загружена");

       /* assertTrue(driver, 10, ADD_TO_CART_BUTTON,
                "Не дождались кнопки \"добавить в корзину\"",
                "Кнопка \"добавить в корзину\" загружена");*/

/*        assertTrue(driver, 10, INCREASE_QUANTITY_BUTTON,
                "Не дождались кнопки увеличения количества товара",
                "Кнопка увеличения количества товара загружена");

        assertTrue(driver, 10, DECREASE_QUANTITY_BUTTON,
                "Не дождались кнопки уменьшения количества товара",
                "Кнопка уменьшения количества товара загружена");
    */
    }
}
