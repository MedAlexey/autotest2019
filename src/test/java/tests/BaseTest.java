package tests;

import providers.ConfigFileProvider;

public abstract class BaseTest {
    ConfigFileProvider config = new ConfigFileProvider("config.txt");
}
