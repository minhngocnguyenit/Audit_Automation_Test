package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.WebElement;

public class CComplexText extends PageInit {

    private WebElement ele;
    private String label;
    private WebElement textarea;

    public CComplexText(WebElement ele) {
        this.ele = ele;
    }

    public CComplexText(String title) {

    }

    public boolean isDisplayed() {
        return ele != null && ele.isDisplayed();
    }

    public void type(String text) {
        textarea.sendKeys(text);
    }
}
