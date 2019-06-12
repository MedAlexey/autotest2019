package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupsPage extends BasePage {

    private static final By GROUPS_MODERATION = By.xpath(".//*[@class='hookBlock' and contains(text(), 'Модерирую')]");
    private static final By GROUP = By.xpath(".//*[@class='stub-img stub-group-interest-128 stub-img__128']");

    public GroupsPage(WebDriver driver) {
        super(driver);
    }

    // открыть свою группу
    public GroupPage openGroup(WebDriver driver) {
        click(GROUPS_MODERATION);
        click(GROUP);

        return new GroupPage(driver);
    }

    // проверка наличия и видимости кнопок
    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'Модерирую'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(GROUPS_MODERATION)));
    }
}
