package com.sm.page.dtwp;

import com.sm.page.components.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SAPPage extends BaseDigitizedWorkingPaper {

    private static SAPPage sapPage;

    private CComplexMemo cComplexMemo;
    private CFreeformTable cFreeformTable;
    private CVerticalTable cVerticalTable;
    private CComplexText cComplexText;

    @FindBy(css = ".widget-lines-of-disaggregation input")
    private WebElement eleInputLinesWantToDisaggregateBy;

    @FindBy(css = ".btn-update-sap-table")
    private WebElement eleUpdateSAPTableBtn;

    @FindBy(css = ".table-row-gct .list-view-table")
    private List<WebElement> eleListTestingSheet;

    @FindBy(xpath = "//input[@placeholder = 'Untitled population/part']")
    private WebElement eleInputNameOfPopulation;

    @FindBy(xpath = "(//input)[2]")
    private WebElement eleRecordedAmount;

    @FindBy(xpath = "(//input)[3]")
    private WebElement eleExpectation;

    @FindBy(xpath = "(//input)[4]")
    private WebElement eleExplainedDifference;

    @FindBy(xpath = "(//input[@type = 'radio'])[3]")
    private WebElement eleRelyingOnControls;

    @FindBy(css = ".threshold-calculator-w-control-reliance")
    private WebElement eleThresholdGuidance;

    @FindBy(css = ".widget-sap-testing-summary")
    private WebElement widgetTestingSheet;

    public SAPPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static SAPPage getInstance(WebDriver webDriver) {
        if(sapPage == null) return new SAPPage(webDriver);
        return sapPage;
    }

    /**
     * Generate SAP Perform
     * @param n
     */
    public void generateSAPTable(int n){
        try{
            waitForElementToBeClickable(this.eleInputLinesWantToDisaggregateBy);
            this.eleInputLinesWantToDisaggregateBy.clear();
            this.eleInputLinesWantToDisaggregateBy.sendKeys(String.valueOf(n));
            Thread.sleep(1000);
            waitForElementToBeClickable(this.eleUpdateSAPTableBtn);
            click(this.eleUpdateSAPTableBtn);
            Thread.sleep(5000);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CWidgetTable getCWidgetTestingSheet() {
        cWidgetTestingSheet = new CWidgetTable(this.widgetTestingSheet);
        return  cWidgetTestingSheet;
    }

    /**
     * Open SAP Testing sheet
     * @param index
     */
    public void openTestingSheet(int index) {
        if (index < eleListTestingSheet.size()) {
            WebElement eleTr = this.eleListTestingSheet.get(index);
            if (eleTr == null) return;
            WebElement cb = eleTr.findElement(By.cssSelector("td i.alternate"));
            click(cb);
            this.switchTab(2);
        }
    }

    /**
     * Input data on Testing Sheet
     * @param title
     * @param amount
     * @param expectation
     * @param explainedDifference
     */
    public void inputTestingSheet(String title, double amount, double expectation, double explainedDifference) {
        waitForElementToBeClickable(this.eleInputNameOfPopulation);
        this.eleInputNameOfPopulation.clear();
        this.eleInputNameOfPopulation.sendKeys(title);

        waitForElementToBeClickable(this.eleRecordedAmount);
        this.eleRecordedAmount.clear();
        this.eleRecordedAmount.sendKeys(String.valueOf(amount));

        waitForElementToBeClickable(this.eleExpectation);
        this.eleExpectation.clear();
        this.eleExpectation.sendKeys(String.valueOf(expectation));

        waitForElementToBeClickable(this.eleExplainedDifference);
        this.eleExplainedDifference.clear();
        this.eleExplainedDifference.sendKeys(String.valueOf(explainedDifference));
    }

    /**
     * Save Testing sheet
     */
    public void saveTestingSheet() {
        waitForElementToBeClickable(eleSaveBtn);
        click(eleSaveBtn);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForDimmerDisappeared();
        waitForElementToBeClickable(this.eleSaveBtn);
        waitForElementToBeClickable(this.eleExitBtn);
    }

    public void closeTestingSheet(boolean save) {
        waitForVisibleElement(eleExitBtn, 30);
        click(eleExitBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
        try {
            if(this.eleConfirmDontSaveBtn.isDisplayed()) {
                this.eleConfirmDontSaveBtn.isDisplayed();
                if (save) this.eleConfirmSaveBtn.click();
                this.eleConfirmDontSaveBtn.click();
            }
        }catch (NoSuchElementException | NoSuchWindowException ignored){

        }
        //Switch to main tab
        List<String> tabs = this.listTabs();
        this.switchTab(1);
    }
}
