package tests;

import providers.ConfigFileProvider;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    ConfigFileProvider config = new ConfigFileProvider("config.txt");

  //  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
}
