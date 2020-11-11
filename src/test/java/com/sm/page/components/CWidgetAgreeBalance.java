package com.sm.page.components;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import com.sm.page.PageInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CWidgetAgreeBalance extends PageInit {

    private WebElement ele;

    public CWidgetAgreeBalance(WebElement ele) {
        this.ele = ele;
    }

    public boolean isDisplayed() {
        return ele != null && ele.isDisplayed();
    }

    public String getTitle() {
        return ele.findElement(By.cssSelector(".dtw-widget-agree-balance-tb-title")).getText();
    }

    public String getTotalScheduleFromEntity() {
        WebElement e = ele.findElement(By.cssSelector("table tr:nth-child(1) td:nth-child(2) label"));
        return  e == null ? "" : e.getText();
    }

    public String getAmountFromTrialBalanceOrReconciled() {
        WebElement e = ele.findElement(By.cssSelector("table tr:nth-child(2) td:nth-child(2) label"));
        return  e == null ? "" : e.getText();
    }

    public String getDifference() {
        WebElement e = ele.findElement(By.cssSelector("table tr:nth-child(3) td:nth-child(2) label"));
        return  e == null ? "" : e.getText();
    }

    public String getTotalFromReconciledDifference() {
        WebElement e = ele.findElement(By.cssSelector("table tr:nth-child(4) td:nth-child(2) label"));
        return  e == null ? "" : e.getText();
    }

    public String getFinalDifference() {
        WebElement e = ele.findElement(By.cssSelector("table tr:nth-child(5) td:nth-child(2) label"));
        return  e == null ? "" : e.getText();
    }

    public void verifyCardData(double totalEntity, double trialBalanceOrReconciled, double reconciledDifference) {
        CAssertion.assertEquals(AccountingFormatter.format(totalEntity, locale), getTotalScheduleFromEntity());
        CAssertion.assertEquals(AccountingFormatter.format(trialBalanceOrReconciled, locale), getAmountFromTrialBalanceOrReconciled());
        CAssertion.assertEquals(AccountingFormatter.format(reconciledDifference, locale), getTotalFromReconciledDifference());

        double difference = trialBalanceOrReconciled - totalEntity;
        CAssertion.assertEquals(AccountingFormatter.format(difference,locale), getFinalDifference());

        double finalDifference = difference - reconciledDifference;
        CAssertion.assertEquals(AccountingFormatter.format(finalDifference, locale), getFinalDifference());
    }
}
