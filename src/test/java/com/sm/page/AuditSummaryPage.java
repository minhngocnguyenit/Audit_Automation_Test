package com.sm.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class AuditSummaryPage extends BaseWorkingPaperPage {

    private static AuditSummaryPage auditSummaryPage;

    public AuditSummaryPage(WebDriver driver) {
        super(driver);
    }

    public static AuditSummaryPage getInstance(WebDriver webDriver) {
        if(auditSummaryPage == null) return new AuditSummaryPage(webDriver);
        return auditSummaryPage;
    }

    @Override
    public boolean isLoad() {
        waitForDimmerDisappeared();
        try {
            waitForVisibleElement(eleEditBtn, 2);
            return false;
        }catch (TimeoutException e) {

        }
        return true;
    }
}
