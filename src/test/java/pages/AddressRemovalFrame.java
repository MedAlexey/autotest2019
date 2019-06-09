package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressRemovalFrame extends BasePage {

    private static final By DELETE_BUTTON = By.xpath(".//*[@value='Удалить']");

    AddressRemovalFrame(WebDriver driver) {
        super(driver);
    }

    public AddressesPage deleteAddress(){
        click(DELETE_BUTTON);
        return new AddressesPage(driver);
    }

    @Override
    void check(WebDriver driver) {

    }
}
