package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.junit.Assert.fail;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    public void sendKeys(By element, String keys) {
        driver.findElement(element).sendKeys(keys);
    }

    public void click(By element) {

        if(isElementPresent(element)){
            driver.findElement(element).click();
        }
        else Assert.fail("Не удалось кликнуть на элемент");

    }

    public boolean isElementPresent(By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void assertTrue(WebDriver driver, int time, By xpath, String badMessage, String goodMessage){
        Assert.assertTrue( badMessage,
                new WebDriverWait(driver, time).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath)));
        System.out.println(new Date()+" - " + goodMessage);
    }

    abstract protected void check(WebDriver driver);
}
