package com.sm.page.dtwp;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import com.sm.models.AbcotdsMateriality;
import com.sm.models.Procedure;
import com.sm.models.Romm;
import com.sm.page.BaseWorkingPaperPage;
import com.sm.page.components.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseDigitizedWorkingPaper extends BaseWorkingPaperPage {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FindBy(css = ".widget-materiality tbody tr")
    protected List<WebElement> widgetMateriality;

    @FindBy(css = ".widget-account-balances")
    protected List<WebElement> widgetAccountBalances;

    @FindBy(css = ".widget-risks-details")
    protected WebElement widgetRisk;

    @FindBy(css = ".widget-procedure")
    protected WebElement widgetProcedure;

    @FindBy(css = ".widget-procedure-tasks")
    protected WebElement widgetTask;

    @FindBy(css = ".widget-materiality .materiality-table tbody tr:nth-child(1)")
    protected WebElement eleRowOverallMateriality;

    @FindBy(xpath = "//div[contains(@class, 'ui-trigger') and div/div[contains(@class, 'dtw-widget-agree-balance-tb')]]")
    protected WebElement widgetAgreeTrialBalance;

    @FindBy(css = ".ui-trigger .widget-sample-table")
    protected List<WebElement> widgetTables;

    @FindBy(css = ".ui-trigger .formula-field")
    protected List<WebElement> widgetFormulaFields;

    @FindBy(css = ".ui-trigger .simple-text-limit")
    protected List<WebElement> sourceDocuments;

    @FindBy(id = "btn-update-sample-selection")
    private WebElement eleUpdateSampleSelection;

    @FindBy(id = "btn-generate-sample")
    private WebElement eleGenerateSample;

    @FindBy(css = ".widget-sample-selection-table")
    private WebElement eleWidgetSampleSelection;

    @FindBy(css = ".widget-testing-table")
    private WebElement eleWidgetTestingTable;

    @FindBy(css = ".complex-memo-input")
    private WebElement eleTextComplexMemo;

    @FindBy(css = ".widget-free-form-table")
    private WebElement eleFreeformTable;

    @FindBy(css = ".vertical-table")
    private WebElement eleVerticalTable;

    @FindBy(css = ".ComplexText")
    private WebElement eleComplexText;

    @FindBy(css = ".dwp-modal")
    private WebElement eleDTWPConfirmModal;

    @FindBy(css = ".dwp-modal .confirmBtn")
    private WebElement eleDWPConfirmBtn;

    private CComplexMemo cComplexMemo;
    private CFreeformTable cFreeformTable;
    private CVerticalTable cVerticalTable;
    private CComplexText cComplexText;

    protected CWidgetAgreeBalance cWidgetAgreeBalance;
    protected CWidgetTable cWidgetTableEntity;
    protected CWidgetTable cWidgetTestingSheet;
    protected CWidgetTable cWidgetTableReconciledDifference;
    protected CWidgetTable cWidgetTablePopulation;
    protected CWidgetSampleSelection cWidgetSampleSelection;
    protected CWidgetTestingTable cWidgetTestingTable;

    public BaseDigitizedWorkingPaper(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isLoad() {
        waitForElementToBeClickable(this.eleExitBtn);
//        waitFor
        return false;
    }

    /**
     * Verify materiality on FSL Barebone
     * @param overall
     * @param performanceMateriality
     * @param ctt
     */
    public void verifyMaterialityFSL(double overall, double performanceMateriality, double ctt) {
        String overallStr = AccountingFormatter.format(overall, locale);
        String pmStr = AccountingFormatter.format(performanceMateriality, locale);
        String cttStr = AccountingFormatter.format(ctt, locale);

        String materialityAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(0).getText();
        String pMAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(1).getText();
        String cTTAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(2).getText();

        Assert.assertEquals(overallStr, materialityAmount);
        Assert.assertEquals(pmStr, pMAmount);
        Assert.assertEquals(cttStr, cTTAmount);
    }

    /**
     * Verify materiality
     * @param overall
     * @param performanceMateriality
     * @param abcotdsMaterialities
     */
    public void verifyMateriality(double overall, double performanceMateriality, double ctt, List<AbcotdsMateriality> abcotdsMaterialities) {
        String overallStr = AccountingFormatter.format(overall, locale);
        String pmStr = AccountingFormatter.format(performanceMateriality, locale);
        String cttStr = AccountingFormatter.format(ctt, locale);

        String materialityAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(0).getText();
        String pMAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(1).getText();
        String cTTAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(2).getText();

        Assert.assertEquals(overallStr, materialityAmount);
        Assert.assertEquals(pmStr, pMAmount);
        Assert.assertEquals(cttStr, cTTAmount);

        Assert.assertEquals(abcotdsMaterialities.size(), this.widgetMateriality.size() - 1);

        if(abcotdsMaterialities.size() == this.widgetMateriality.size() - 1) {
            for (int i = 0; i < abcotdsMaterialities.size(); i++) {
                AbcotdsMateriality item = abcotdsMaterialities.get(i);
                WebElement tr = this.widgetMateriality.get(i + 1);

                //Verify ABCOTDs name
                Assert.assertEquals("ABCOTD: " + item.getAbcotdName(), tr.findElement(By.cssSelector("td:nth-child(2) span")).getText());
                //Verify Materiality
                Assert.assertEquals(AccountingFormatter.format(item.getDeterminedMateriality(), locale), tr.findElement(By.cssSelector("td:nth-child(3) span")).getText());
                //Verify Performance materiality
                Assert.assertEquals(AccountingFormatter.format(item.getDeterminedPM(), locale), tr.findElement(By.cssSelector("td:nth-child(4) span")).getText());
            }
        }
    }

    /**
     * Verify materiality overall
     * @param overall
     * @param performanceMateriality
     */
    public void verifyMaterialityOverall(double overall, double performanceMateriality, double ctt) {
        String overallStr = AccountingFormatter.format(overall, locale);
        String pmStr = AccountingFormatter.format(performanceMateriality, locale);
        String cttStr = AccountingFormatter.format(ctt, locale);

        String materialityAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(0).getText();
        String pMAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(1).getText();
        String cTTAmount = eleRowOverallMateriality.findElements(By.cssSelector("td.colNumber span")).get(2).getText();

        Assert.assertEquals(overallStr, materialityAmount);
        Assert.assertEquals(pmStr, pMAmount);
        Assert.assertEquals(cttStr, cTTAmount);
    }

    /**
     * Verify balance per abcotds
     */
    public void verifyAccountBalance() {

    }

    /**
     * Verify romms data
     * @param romms
     */
    public void verifyRisk(List<Romm> romms) {
        List<WebElement> eleTrRomms = this.widgetRisk.findElements(By.cssSelector("table.risks-table tbody tr"));
        for(int i = 0; i < romms.size(); i++) {
            Romm r = romms.get(i);
            WebElement eleRoMMID = eleTrRomms.get(i).findElement(By.cssSelector("td:nth-child(2) span"));
            WebElement eleRoMMTitle = eleTrRomms.get(i).findElement(By.cssSelector("td:nth-child(3) span"));
            WebElement eleRoMMAssertion = eleTrRomms.get(i).findElement(By.cssSelector("td:nth-child(4) span"));

            //Check if romm is custom romm
            String expectedRiskId = eleRoMMID.getText();
            wedDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            try{
                WebElement temp = eleRoMMID.findElement(By.cssSelector("span.risk-link-text"));
                if(temp.isDisplayed()) {
                    expectedRiskId = expectedRiskId.replace("\n", "").replace("Description", "");
                }
            }catch (NoSuchElementException e) {

            }
            wedDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            Assert.assertEquals(r.getId(), expectedRiskId);
            Assert.assertEquals(r.getTitle(), eleRoMMTitle.getText());

            List<String> assertions = r.getAssertion();
            if(assertions == null ||assertions.size() == 0) {
                List<String> actualAssertion = Arrays.asList(eleRoMMAssertion.getText().split("\n"));
                actualAssertion.sort(Comparator.naturalOrder());
                StringBuffer actual = new StringBuffer();
                actualAssertion.forEach(s -> {
                    actual.append(s).append(",");
                });
                actual.setLength(actual.length() - 1);

                assertions.sort(Comparator.naturalOrder());
                StringBuffer expected = new StringBuffer();
                assertions.forEach(s->{
                    expected.append(s).append(",");
                });
                expected.setLength(expected.length()-1);
                Assert.assertEquals(expected, actual);
            }
        }
    }

    /**
     * Verify romms data in Barebone FSL
     * @param romms
     */
    public void verifyRiskInBareboneFSL(List<Romm> romms) {
        List<WebElement> eleTrRomms = this.widgetRisk.findElements(By.cssSelector("table.risks-table tbody tr"));
        for(int i = 0; i < romms.size(); i++) {
            Romm r = romms.get(i);
            WebElement eleRoMMID = eleTrRomms.get(i).findElement(By.cssSelector("td:nth-child(2) span"));
            WebElement eleRoMMTitle = eleTrRomms.get(i).findElement(By.cssSelector("td:nth-child(3) span"));

            Assert.assertEquals(r.getId(), eleRoMMID.getText());
            Assert.assertEquals(r.getTitle(), eleRoMMTitle.getText());
        }
    }

    /**
     * Verify procedure data
     * @param procedure
     */
    public void verifyProcedure(Procedure procedure) {
        WebElement tr = this.widgetProcedure.findElement(By.cssSelector("table tbody tr:nth-child(1)"));
        Assert.assertEquals(procedure.getId(), tr.findElement(By.cssSelector("td:nth-child(2) span")).getText());
        Assert.assertEquals(procedure.getTitle(), tr.findElement(By.cssSelector("td:nth-child(3) span")).getText());
    }

    public void verifyTask(String data) {

    }

    /**
     * Find a widget table element as Entity, Reconciled Difference by title
     * @param title
     * @return
     */
    public WebElement findWidgetTableByTitle(String title) {
        return this.widgetTables.stream().filter(e->title.equals(e.findElement(By.cssSelector(".handsontable-top-title .title")).getText())).findFirst().orElse(null);
    }

    /**
     * Verify data agree summary card
     */
    public void verifyWidgetAgreeTrialBalance() {
        waitForVisibleElement(widgetAgreeTrialBalance, 30);
        cWidgetAgreeBalance = new CWidgetAgreeBalance(widgetAgreeTrialBalance);
    }

    /**
     * Verify widget table isDisplayed
     * @param tableName
     */
    public void verifyWidgetTableDisplayed(String tableName) {
        WebElement ele = findWidgetTableByTitle(tableName);
        waitForVisibleElement(ele, 30);
    }

    /**
     * Type data into widget table
     * @param title
     * @param data
     */
    public void typeDataIntoWidgetSampleTable(String title, List<List<String>> data) {
        WebElement ele = findWidgetTableByTitle(title);
        if(ele != null) scrollToElement(ele);
        CWidgetTable cWidgetTable = new CWidgetTable(ele);
        cWidgetTable.type(data);
    }

    /**
     * Add new rows into a widget table
     * @param row
     * @param title
     */
    public void addNewRowToWidgetTable(int row, String title) {
        WebElement ele = findWidgetTableByTitle(title);
        waitForVisibleElement(ele, 5);
        Assert.assertNotNull(String.format("The working paper should have table '%s'", title), ele);
        if(ele != null) scrollToElement(ele);
        CWidgetTable cWidgetTable = new CWidgetTable(ele);
        cWidgetTable.addNewRow(row);
    }

    /**
     * Find a formula field by label
     * @param title
     * @return
     */
    public WebElement findFormulaFieldByTitle(String title) {
        return this.widgetFormulaFields.stream().filter(e->e.findElement(By.cssSelector("label")).getText().contains(title)).findFirst().orElse(null);
    }

    public WebElement findSourceDocumentByLabel(String label) {
        WebElement ele = this.sourceDocuments.stream().filter(e-> e.findElement(By.cssSelector("p")).getText().contains(label)).findFirst().orElse(null);
        if(ele == null) return null;

        WebElement parent = ele.findElement(By.xpath(".."));
        return parent;
    }

    /**
     * Type a text on formula field
     * @param text
     * @param title
     */
    public void typeOnFormulaField(String text, String title) {
        WebElement ele = findFormulaFieldByTitle(title);
        Assert.assertNotNull("The field '" + title + "'doesn't exist", ele);
        WebElement eleInput = ele.findElement(By.cssSelector("input"));
        if(eleInput != null) {
            waitSomeSecond(0.3);
            eleInput.sendKeys(text);
            eleInput.sendKeys(Keys.TAB);
            waitSomeSecond(1);
        }
    }

    /**
     * Type text on source document field
     * @param label
     * @param text
     */
    public void typeOnSourceDocument(String label, String text) {
        WebElement element = findSourceDocumentByLabel(label);
        Assert.assertNotNull("The source document '" + label + "' doesn't exist.", element);
        if(element != null) {
            WebElement eleInput = element.findElement(By.cssSelector("input"));
            if(eleInput != null) {
                eleInput.clear();
                eleInput.sendKeys(text);
                waitSomeSecond(1);
            }
        }
    }

    /**
     * Update sample/selection table
     */
    public void updateSampleSelectionTable() {
        click(eleUpdateSampleSelection);
        try{
            waitForVisibleElement(this.eleDWPConfirmBtn, 1);
            click(eleDWPConfirmBtn);
        }catch (TimeoutException e) {

        }
    }

    /**
     * Generate sample/selections for testing
     */
    public void generateSampleSelectionForTesting() {
        click(eleGenerateSample);
        try{
            waitForVisibleElement(this.eleDWPConfirmBtn, 1);
            click(eleDWPConfirmBtn);
        }catch (TimeoutException e) {

        }
    }

    /**
     * Type data on sample/selection table
     * @param data
     */
    public void typeDataOnSampleSelectionTable(List<List<String>> data) {
        waitForVisibleElement(eleWidgetSampleSelection, 5);
        cWidgetSampleSelection = new CWidgetSampleSelection(eleWidgetSampleSelection);
        if(!cWidgetSampleSelection.isEmpty()){
            scrollToElement(eleWidgetSampleSelection);
            cWidgetSampleSelection.type(data);
        }
    }

    public void typeDataOnTestingTable(List<List<String>> data) {
        waitForVisibleElement(eleWidgetTestingTable, 5);
        cWidgetTestingTable = new CWidgetTestingTable(eleWidgetTestingTable, wedDriver);
        cWidgetTestingTable.type(data);
    }

    public void verifyComplexTextDisplay(String title) {
        WebElement header = this.eleComplexText.findElement(By.cssSelector(".field-description-heading"));
        Assert.assertTrue(header.getText().contains(title));
        cComplexText = new CComplexText(this.eleComplexText);
        Assert.assertTrue("Complex text should be displayed", cComplexText.isDisplayed());
    }

    public void verifyComplexMemoDisplayed() {
        waitForVisibleElement(eleTextComplexMemo, 5);
        cComplexMemo = new CComplexMemo(eleTextComplexMemo);
        Assert.assertNotNull("The text memo doesn't exist.", cComplexMemo);
        Assert.assertTrue("Complex memo should be displayed", cComplexMemo.isDisplayed());
    }

    public void verifyFreeformTableDisplayed() {
        waitForVisibleElement(eleFreeformTable, 10);
        cFreeformTable = new CFreeformTable(eleFreeformTable);
        Assert.assertTrue("Free form table should be displayed", cFreeformTable.isDisplayed());
    }

    public void verifyVerticalTableDisplayed() {
        waitForVisibleElement(eleVerticalTable, 5);
        cVerticalTable = new CVerticalTable(eleVerticalTable);
        Assert.assertTrue("Verical table should be displayed",cVerticalTable.isDisplayed());
    }

    public void markPrepareSignOff() {
        prepare();
    }
}
