package com.sm.page.dtwp;

import com.sm.page.components.CWidgetTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestOfDetailsPage extends BaseDigitizedWorkingPaper {

    private static TestOfDetailsPage testOfDetailsPage;

    private CWidgetTable cWidgetTablePopulation;
    private CWidgetTable cWidgetTableEntity;
    private CWidgetTable cWidgetTableReconciledDifference;
    private CWidgetTable cWidgetTableSampleSelection;

    public TestOfDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public  static TestOfDetailsPage getInstance(WebDriver webDriver) {
        if(testOfDetailsPage == null) return new TestOfDetailsPage(webDriver);
        return testOfDetailsPage;
    }
}
