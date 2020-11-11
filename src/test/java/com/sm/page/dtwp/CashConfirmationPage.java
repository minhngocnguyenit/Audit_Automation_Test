package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.page.components.CWidgetCashPerform;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CashConfirmationPage extends BaseDigitizedWorkingPaper{

    private static CashConfirmationPage cashConfirmationPage;

    public CashConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(css = ".dtw-widget-generate-confirmations-testing-button button")
    private WebElement eleGenerateBtn;

    @FindBy(css = ".widget-confirmation-testing.cash-yes-foreign-currency")
    private WebElement cashPerformYesForeignCurrency;

    @FindBy(css = ".widget-confirmation-testing.cash-no-foreign-currency")
    private WebElement cashPerformNoForeignCurrency;

    private CWidgetCashPerform cWidgetCashPerform;

    public static CashConfirmationPage getInstance(WebDriver webDriver) {
        if(cashConfirmationPage == null) return new CashConfirmationPage(webDriver);
        return cashConfirmationPage;
    }

    public void generateSampleSelectionTesting() {
        click(eleGenerateBtn);
    }

    public void cashPerformYesForeignCurrencyDisplayed() {
        Assert.assertTrue("The Cash Perform Yes Foreign Currency doesn't display", this.cashPerformYesForeignCurrency.isDisplayed());
    }

    public void cashPerformNoForeignCurrencyDisplayed() {
        Assert.assertTrue("The Cash Perform No Foreign Currency doesn't display", this.cashPerformNoForeignCurrency.isDisplayed());
    }

    public void typeOnCashPerformYesCurrency(List<List<String>> data) {
        waitForVisibleElement(cashPerformYesForeignCurrency, 10);
        cWidgetCashPerform = new CWidgetCashPerform(this.cashPerformYesForeignCurrency, wedDriver);
        cWidgetCashPerform.type(data);
    }

    public void typeOnCashPerformNoCurrency(List<List<String>> data) {
        waitForVisibleElement(cashPerformNoForeignCurrency, 10);
        cWidgetCashPerform = new CWidgetCashPerform(this.cashPerformNoForeignCurrency, wedDriver);
        cWidgetCashPerform.type(data);
    }
}
