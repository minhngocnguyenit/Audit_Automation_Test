package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import com.sm.page.components.CWidgetAPPerform;
import com.sm.page.components.CWidgetAPTestingSheet;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class APConfirmationPage extends BaseDigitizedWorkingPaper {

    private static APConfirmationPage apConfirmationPage;

    @FindBy(css = ".widget-perform-ap")
    private WebElement eleAPPerform;

    @FindBy(css = ".wdg-update-alternative-procedures-button button")
    private WebElement eleUpdateAlternativeProcedures;

    @FindBy(css = ".widget-alternative-procedures-balance-summary table.content-table")
    private WebElement eleWidgetAlternativeProcedureBalanceSummary;

    @FindBy(css = ".widget-ap-alternative-procedures-testing")
    private WebElement eleAPTestingSheet;

    private CWidgetAPPerform cWidgetAPPerform;
    private CWidgetAPTestingSheet cWidgetAPTestingSheet;

    private String currentTab = null;

    public APConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static APConfirmationPage getInstance(WebDriver webDriver) {
        if(apConfirmationPage == null) return new APConfirmationPage(webDriver);
        return apConfirmationPage;
    }

    /**
     * Type data to AP Confirmation Perform section
     * @param data
     */
    public void typeOnAPPerform(List<List<String>> data) {
        cWidgetAPPerform = new CWidgetAPPerform(eleAPPerform, wedDriver);
        cWidgetAPPerform.type(data);
    }

    /**
     * I update alternative procedure
     */
    public void updateAlternativeProcedures() {
        click(this.eleUpdateAlternativeProcedures);
        waitForDimmerDisappeared();
    }

    /**
     * Open AP Testing sheet
     * @param sheetIndex
     */
    public void openTestingSheet(int sheetIndex) {
        List<WebElement> eleTrs = eleWidgetAlternativeProcedureBalanceSummary.findElements(By.cssSelector("tbody tr"));
        if(sheetIndex <= eleTrs.size()) {
            //Store current tab
            currentTab = wedDriver.getWindowHandle();

            WebElement td = eleTrs.get(sheetIndex - 1);
            WebElement icon = td.findElement(By.cssSelector(".external.alternate"));
            click(icon);
            this.switchTab(2);
            waitForDimmerDisappeared();
        }
    }

    public void checkTestingSheetDisplay() {
        waitForVisibleElement(eleAPTestingSheet, 30);
        cWidgetAPTestingSheet = new CWidgetAPTestingSheet(eleAPTestingSheet, wedDriver);
        Assert.assertTrue("Can not open AP TestingSheet", cWidgetAPTestingSheet.isDisplayed());
    }

    public void typeOnTestingSheetSheet(List<List<String>> data) {
        Assert.assertNotNull("Can not open AP Testing sheet.", cWidgetAPTestingSheet);
        if(cWidgetAPTestingSheet != null)
            cWidgetAPTestingSheet.type(data);
    }

    public void verifyDataOnBalanceSummaryTable(int sheetIndex, double unexplainedDifference, String wereAnyExceptionsNoted) {
        this.refresh();
        waitForDimmerDisappeared();
        waitForVisibleElement(eleWidgetAlternativeProcedureBalanceSummary, 10);
        List<WebElement> eleTrs = eleWidgetAlternativeProcedureBalanceSummary.findElements(By.cssSelector("tbody tr"));
        Assert.assertTrue("The sheet index out of table size",sheetIndex <= eleTrs.size());
        if(sheetIndex <= eleTrs.size()) {
            WebElement tr = eleTrs.get(sheetIndex - 1);
            List<WebElement> tds = tr.findElements(By.cssSelector("td"));
            WebElement eleUnexplainedDifference = tds.get(5);
            WebElement eleWereAnyExceptionsNoted = tds.get(6);
            Assert.assertEquals("The Unexplained difference doesn't match.", AccountingFormatter.format(unexplainedDifference, locale), eleUnexplainedDifference.getText());
            Assert.assertEquals("The Were any Exception noted doesn't match", wereAnyExceptionsNoted, eleWereAnyExceptionsNoted.getText());
        }
    }

    public void saveCloseTestingSheet(){
        save();
        closeWP(false);
        if(currentTab != null){
            wedDriver.switchTo().window(currentTab);
        }else{
            switchTab(1);
        }
    }
}
