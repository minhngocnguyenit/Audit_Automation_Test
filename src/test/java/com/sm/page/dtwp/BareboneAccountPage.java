package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.page.components.CComplexMemo;
import com.sm.page.components.CComplexText;
import com.sm.page.components.CFreeformTable;
import com.sm.page.components.CVerticalTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BareboneAccountPage extends BaseDigitizedWorkingPaper {

    public static BareboneAccountPage bareboneAccountPage;

    public BareboneAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static BareboneAccountPage getInstance(WebDriver webDriver) {
        if(bareboneAccountPage == null) return new BareboneAccountPage(webDriver);
        return bareboneAccountPage;
    }
}
