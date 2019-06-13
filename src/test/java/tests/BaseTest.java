package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import providers.ConfigFileProvider;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    WebDriver driver;
    ConfigFileProvider config = new ConfigFileProvider("config.txt");

    BaseTest() {
        setUpDriver();
    }

    private void setUpDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ok.ru/dk?st.cmd=anonymMain");
    }
}
