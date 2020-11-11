package com.sm.page.dtwp;

import com.sm.page.components.CWidgetSubsequentPerform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubsequentReceiptPage extends BaseDigitizedWorkingPaper {

    private static SubsequentReceiptPage subsequentReceiptPage;

    public SubsequentReceiptPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(css = ".widget-perform-subreceipts")
    private WebElement eleSubsequentPerform;

    private CWidgetSubsequentPerform cWidgetSubsequentPerform;

    public static SubsequentReceiptPage getInstance(WebDriver webDriver){
        if(subsequentReceiptPage == null) return new SubsequentReceiptPage(webDriver);
        return subsequentReceiptPage;
    }

    public  void typeOnPerform(List<List<String>> data) {
        waitForVisibleElement(eleSubsequentPerform, 30);
        cWidgetSubsequentPerform = new CWidgetSubsequentPerform(eleSubsequentPerform, wedDriver);
        cWidgetSubsequentPerform.type(data);
    }
}
