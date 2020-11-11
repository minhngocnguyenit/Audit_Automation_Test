package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.WebElement;

public class CFreeformTable extends PageInit {

    private WebElement ele;
    public CFreeformTable(WebElement ele) {
        this.ele = ele;
    }

    public boolean isDisplayed() {
        return ele != null && ele.isDisplayed();
    }
}
