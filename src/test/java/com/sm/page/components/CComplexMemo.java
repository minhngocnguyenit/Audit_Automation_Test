package com.sm.page.components;

import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CComplexMemo extends PageInit {

    private WebElement ele;

    public CComplexMemo(WebElement ele) {
        this.ele = ele;
    }

    public boolean isDisplayed() {
        return ele != null && ele.isDisplayed();
    }

    public void type(String text) {
        this.ele.findElement(By.cssSelector("textarea")).sendKeys(text);
    }
}
