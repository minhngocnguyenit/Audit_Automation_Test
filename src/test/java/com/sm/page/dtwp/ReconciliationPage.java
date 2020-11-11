package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.page.components.CWidgetAgreeBalance;
import com.sm.page.components.CWidgetTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReconciliationPage extends BaseDigitizedWorkingPaper {

    private static ReconciliationPage reconciliationPage;

    @FindBy(xpath = "//div[contains(@class, 'ui-trigger') and div/div/div[contains(@class, 'widget-sample-table')]][1]")
    private WebElement widgetTableEntity;

    @FindBy(xpath = "//div[contains(@class, 'ui-trigger') and div/div/div[contains(@class, 'widget-sample-table')]][2]")
    private WebElement widgetReconciledDifference;

    public ReconciliationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static ReconciliationPage getInstance(WebDriver webDriver) {
        if(reconciliationPage == null) return new ReconciliationPage(webDriver);
        return reconciliationPage;
    }

    public CWidgetTable geCWidgetTableEntity() {
        cWidgetTableEntity = new CWidgetTable(this.widgetTableEntity);
        return  cWidgetTableEntity;
    }

    public CWidgetTable cWidgetTableReconciledDifference() {
        cWidgetTableReconciledDifference = new CWidgetTable(this.widgetReconciledDifference);
        return cWidgetTableReconciledDifference;
    }

    public void verifyDataOnAgreeSummaryCard() {

    }
}
