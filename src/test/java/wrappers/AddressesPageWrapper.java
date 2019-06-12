package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressesPageWrapper {

    private static final By NAME = By.xpath(".//div[@class='mall-column_item']/a/div[1]");
    private static final By PHONE = By.xpath(".//div[@class='mall-column_item']/a/div[2]");
    private static final By ADDRESS = By.xpath(".//div[@class='mall-column_item']/a/div[3]");

    WebDriver driver;
    WebElement bookmarksCard;

    public AddressesPageWrapper(WebDriver driver, WebElement bookmarksCard) {
        this.driver = driver;
        this.bookmarksCard = bookmarksCard;
    }

    //возвращает ФИО
    public String getName() {
        return bookmarksCard.findElement(NAME).getText();
    }

    //возвращает номер телефона
    public String getPhone() {
        return bookmarksCard.findElement(PHONE).getText();
    }

    //возвращает адрес
    public String getAddress() {
        return bookmarksCard.findElement(ADDRESS).getText();
    }
}
