package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupsPage extends BasePage{

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

    @Override
    void check(WebDriver driver) {
        isElementPresent(GROUPS_MODERATION);
        isElementPresent(GROUP);
    }
}