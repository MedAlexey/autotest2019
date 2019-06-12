package wrappers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogPageTransformer {

    private DialogPageTransformer(){}

    public static List<DialogPageWrapper> wrapDialog(List<WebElement> cards, WebDriver driver) {
        if (cards.isEmpty()){
            return Collections.emptyList();
        }
        List<DialogPageWrapper> wrapDialog = new ArrayList<DialogPageWrapper>();
        for (WebElement card : cards){
            wrapDialog.add(new DialogPageWrapper(driver, card));
        }
        return wrapDialog;
    }
}
