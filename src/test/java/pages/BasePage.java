package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.NoSuchElementException;

public abstract class BasePage {

    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    void sendKeys(By element, String keys) {
        driver.findElement(element).sendKeys(keys);
    }

    void click(By element) {
        driver.findElement(element).click();
    }

    boolean isElementPresent(By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    abstract void check(WebDriver driver);
}
