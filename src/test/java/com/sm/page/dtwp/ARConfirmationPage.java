package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import com.sm.page.components.CWidgetAPTestingSheet;
import com.sm.page.components.CWidgetARAlternativeLowerLevel;
import com.sm.page.components.CWidgetARPerform;
import com.sm.page.components.CWidgetARTestingSheet;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ARConfirmationPage extends BaseDigitizedWorkingPaper {

    private static ARConfirmationPage arConfirmationPage;

    public ARConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(css = ".widget-perform")
    private WebElement eleWidgetARPerform;

    @FindBy(css = ".wdg-update-alternative-procedures-button button")
    private WebElement eleUpdateAlternativeProcedures;

    @FindBy(css = ".widget-lowerlevel")
    private WebElement eleAlternativeLowerLever;

    @FindBy(css = ".widget-alternative-procedures-balance-summary table.content-table")
    private WebElement eleWidgetAlternativeProcedureBalanceSummary;

    @FindBy(css = ".widget-ar-balance-testing-sheet-table")
    private WebElement eleARTestingSheet;

    private CWidgetARPerform cWidgetARPerform;
    private CWidgetARAlternativeLowerLevel cWidgetARAlternativeLowerLevel;
    private CWidgetARTestingSheet cWidgetARTestingSheet;

    private String currentTab;

    public static ARConfirmationPage getInstance(WebDriver webDriver) {
        if(arConfirmationPage == null) return new ARConfirmationPage(webDriver);
        return arConfirmationPage;
    }

    public void typeOnARPerform(List<List<String>> data) {
        waitForVisibleElement(eleWidgetARPerform, 30);
        cWidgetARPerform = new CWidgetARPerform(eleWidgetARPerform, wedDriver);
        cWidgetARPerform.type(data);
    }

    public void updateAlternativeProcedure() {
        click(eleUpdateAlternativeProcedures);
        waitForDimmerDisappeared();
    }

    public void typeOnARLowerLevel(List<List<String>> data) {
        waitForVisibleElement(eleAlternativeLowerLever, 30);
        cWidgetARAlternativeLowerLevel  = new CWidgetARAlternativeLowerLevel(eleAlternativeLowerLever, wedDriver);
        cWidgetARAlternativeLowerLevel.type(data);
    }

    public void openARConfirmationTestingSheet(int sheetIndex) {
        List<WebElement> eleTrs = eleWidgetAlternativeProcedureBalanceSummary.findElements(By.cssSelector("tbody tr"));
        if(sheetIndex <= eleTrs.size()) {
            currentTab = wedDriver.getWindowHandle();

            WebElement td = eleTrs.get(sheetIndex - 1);
            WebElement icon = td.findElement(By.cssSelector(".external.alternate"));
            click(icon);
            this.switchTab(2);
            waitForDimmerDisappeared();
        }
    }

    public void checkTestingSheetDisplay() {
        waitForVisibleElement(eleARTestingSheet, 10);
        cWidgetARTestingSheet = new CWidgetARTestingSheet(eleARTestingSheet, wedDriver);
    }

    public void typeOnARTestingSheet(List<List<String>> data) {
        Assert.assertNotNull("Can not open AR Testing sheet", cWidgetARTestingSheet);
        if(cWidgetARTestingSheet != null)
            cWidgetARTestingSheet.type(data);
    }

    public void verifyDataOnBalanceSummaryTable(int sheetIndex, double unexplainedDifference, String wereAnyExceptionsNoted) {
        this.refresh();
        waitForDimmerDisappeared();
        waitForVisibleElement(eleWidgetAlternativeProcedureBalanceSummary, 5);
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
