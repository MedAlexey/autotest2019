package tests;

import org.junit.Test;
import providers.ConfigFileProvider;

public abstract class BaseTest {
    ConfigFileProvider config = new ConfigFileProvider("config.txt");
}

