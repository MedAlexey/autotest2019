package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductPageFrame extends BasePage implements IProductPage {


    private static final By ADD_TO_CART_BUTTON = By.className("js-mall-card-button-to-basket");
    private static final By BUY_BUTTON = By.className("js-mall-card-button-buy");
    private static final By MALL_CARD = By.className("mall-card_section");
    private static final By SHARE_BUTTON = By.xpath(".//*[@title='Поделиться' and @data-l='t,reshareOverProduct']");
    private static final By SHARE_NOW_BUTTON = By.xpath(".//*[@data-l='t,now']");
    private static final By SHARE_IN_GROUP = By.xpath(".//*[@data-l='t,group']");
    private static final By SHARE_WITH_FEED = By.xpath(".//*[@data-l='t,feed']");
    private static final By SHARE_WITH_MESSAGE = By.xpath(".//*[@data-l='t,msg']");
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
    // кнопка "добавить в закладки"
    private static final By ADD_TO_BOOKMARKS_BUTTON = By.xpath(".//*[@title='Добавить в закладки']");
    // кнопка "закрыть"
    private static final By CLOSE_BUTTON = By.xpath(".//*[@class='ic modal-new_close_ico']");


    public ProductPageFrame(WebDriver driver) {
        super(driver);
    }

    // добавление товара в корзину
    @Override
    public void addToCart() {
        click(ADD_TO_CART_BUTTON);
    }

    public void close() {
        click(CLOSE_BUTTON);
    }


    // выбор размера случайным образом
    @Override
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
    @Override
    public void increaseQuantity() {
        click(INCREASE_QUANTITY_BUTTON);
    }


    // уменьшить количество товара на 1
    @Override
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
    @Override
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
    @Override
    public int getNumberOfColors() {
        return getColors().size();
    }


    // нажать на "дополнить своим текстом"
    @Override
    public ShareWithTextFrame shareWithText() {
        click(SHARE_BUTTON);
        click(SHARE_WITH_FEED);
        return new ShareWithTextFrame(driver);
    }


    // нажать на "опубликовать в группе"
    @Override
    public ShareInGroupFrame shareInGroup() {
        click(SHARE_BUTTON);
        click(SHARE_IN_GROUP);
        return new ShareInGroupFrame(driver);
    }


    // нажать на "поделиться сейчас"
    @Override
    public void shareNow() {
        click(SHARE_BUTTON);
        click(SHARE_NOW_BUTTON);
    }


    // нажать на "отправить сообщением"
    @Override
    public ShareWithMessageFrame shareInMessage() {
        click(SHARE_BUTTON);
        click(SHARE_WITH_MESSAGE);
        return new ShareWithMessageFrame(driver);
    }


    // проверить наличие кнопки "получить скидку 5%"
    @Override
    public FivePercentDiscount checkFivePercentDiscount() {
        return new FivePercentDiscount();
    }


    // обновить страницу
    @Override
    public ProductPage refresh() {
        new EventFiringWebDriver(driver).navigate().refresh();
        return new ProductPage(driver);
    }

    // получение названия товара
    @Override
    public String getProductName() {
        if (isElementPresent(PRODUCT_NAME)) {
            return driver.findElement(PRODUCT_NAME).getText();
        }
        return null;
    }

    // добавление товара в закладки
    public void addToBookmarks() {
        click(ADD_TO_BOOKMARKS_BUTTON);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue( "Не дождались кнопки \"купить\"",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(BUY_BUTTON)));

        Assert.assertTrue( "Не дождались элементов управления товаром",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(MALL_CARD)));

        Assert.assertTrue( "Не дождались кнопки \"поделиться\"",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SHARE_BUTTON)));


        Assert.assertTrue( "Не дождались кнопки \"добавить в корзину\"",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ADD_TO_CART_BUTTON)));

        Assert.assertTrue( "Не дождались кнопки увеличения количества товара",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(INCREASE_QUANTITY_BUTTON)));

        Assert.assertTrue( "Не дождались кнопки уменьшения количества товара",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(DECREASE_QUANTITY_BUTTON)));

        Assert.assertTrue( "Не дождались кнопки \"добавить в закладки\"",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ADD_TO_BOOKMARKS_BUTTON)));
    }


}
