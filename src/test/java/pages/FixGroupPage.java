package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

public class FixGroupPage extends BasePage {

    private static final By GROUP = By.xpath(".//*[@class='stub-img stub-group-business-128 stub-img__128']");

    public FixGroupPage(WebDriver driver) {
        super(driver);
    }

    public GroupPage openGroup(WebDriver driver){
        click(GROUP);
        return new GroupPage(driver);
    }
    @Override
    protected void check(WebDriver driver) {
        assertTrue(driver,3,GROUP,"Не смогли кликнуть на группу", "Смогли килкнуть");
    }
}
