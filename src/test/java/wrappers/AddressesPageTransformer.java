package wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressesPageTransformer {

    private AddressesPageTransformer() {
    }

    public static List<AddressesPageWrapper> wrapAddresses(List<WebElement> cards, WebDriver driver) {
        if (cards.isEmpty()) {
            return Collections.emptyList();
        }
        List<AddressesPageWrapper> wrapAddresses = new ArrayList<AddressesPageWrapper>();
        for (WebElement card : cards) {
            wrapAddresses.add(new AddressesPageWrapper(driver, card));
        }
        return wrapAddresses;
    }
}