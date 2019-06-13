package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class FivePercentDiscount {

    private static final By SALE = By.xpath(".//*[@class='mall-card_section' and contains(text(), 'Получить скидку 5%')]");

    public boolean isAvailable(WebDriver driver) {
        return isElementPresent(driver, SALE);
    }

    public boolean isNotAvailable(WebDriver driver) {
        return !isElementPresent(driver, SALE);
    }

    private boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
