package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.helper.AccountingFormatter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class LeadsheetPage extends BaseWorkingPaperPage{
    private static LeadsheetPage leadsheetPage;

    public LeadsheetPage(WebDriver driver) {
        super(driver);
    }
    public static LeadsheetPage getInstance(WebDriver webDriver) {
        if (leadsheetPage == null) return new LeadsheetPage(webDriver);
        return leadsheetPage;
    }

    @FindBy(xpath="//div[@class = 'active item']")
    WebElement eleParPlanning;

    @FindBy(css = ".risk-assessment-nav-abcotd-item .risk-assessment-nav-step-item:nth-of-type(2)")
    private WebElement eleTailoringQuestionLeftNav;

    @FindBy(css = ".lead-sheet-header")
    WebElement eleLeadSheetHeader;

    @FindBy(css = ".accordion-table-expand")
    WebElement eleEngagementMateriality;

    @FindBy(css = ".abcotds-table-area")
    WebElement eleABCOTDsTable;

    @FindBy(css = ".enhanced-dropdown")
    WebElement eleLaunchWPBtn;

    @FindBy(css = ".enhanced-dropdown .menu .item:nth-child(1)")
    WebElement eleOpenPARBtn;

    @FindBy(css = ".enhanced-dropdown .menu .item:nth-child(2)")
    WebElement eleOpenRABtn;

    @FindBy(css="td:nth-child(2) > .text-cell")
    WebElement eleOverall;

    @FindBy(css="td:nth-child(3) > .text-cell")
    WebElement elePerformanceMateriality;

    @FindBy(css="td:nth-child(4) > .text-cell")
    WebElement eleCTT;

    @FindBy(css = ".abcotds-table-area .title p")
    private List<WebElement> lstListABCOTDs;


    @Override
    public boolean isLoad(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeClickable(this.eleLeadSheetHeader);
        return this.eleLeadSheetHeader.isDisplayed();
    }

    /**
     * Open PAR Working paper from Launch WP Button
     */
    public void openParWorkingPaper(){
        this.eleLaunchWPBtn.click();
        waitForElementToBeClickable(this.eleOpenPARBtn);
        this.eleOpenPARBtn.click();
        //Wait new tab is displays
        this.switchTab(2);
    }

    /**
     * Open RA Working paper from Launch WP Button
     */
    public void openRAWorkingPaper(){
        this.eleLaunchWPBtn.click();
        waitForElementToBeClickable(this.eleOpenRABtn);
        this.eleOpenRABtn.click();
        //Wait new tab is displays
        this.switchTab(2);
    }

    public boolean ParWPisLoad(){
        waitForVisibleElement(eleParPlanning, 120);
        return this.eleParPlanning.isDisplayed();
    }

    public boolean RAWPisLoad() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForVisibleElement(eleTailoringQuestionLeftNav, 120);
        return this.eleTailoringQuestionLeftNav.isDisplayed();
    }

    public void checkLeadSheetIsDisplay() {
    // Todo
    }

    public void checkOverallOfLeadSheet(double overall, double pm, double ctt){
        //Wait for Engagement materiality is displayed on LeadSheet
        waitForElementToBeClickable(this.eleLeadSheetHeader);
        String actualOverall = this.eleOverall.getText();
        String actualPM = this.elePerformanceMateriality.getText();
        String actualCTT = this.eleCTT.getText();

        CAssertion.assertEquals(AccountingFormatter.format(overall, locale), actualOverall);
        CAssertion.assertEquals(AccountingFormatter.format(pm, locale), actualPM);
        CAssertion.assertEquals(AccountingFormatter.format(ctt, locale), actualCTT);
    }

    public void closeWPOpenedFromLeadSheet(boolean save) {
        waitForVisibleElement(eleExitBtn, 30);
        click(eleExitBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        try {
            if(this.eleConfirmDontSaveBtn.isDisplayed() && this.eleConfirmDontSaveBtn.isDisplayed()) {
                if(save) this.eleConfirmSaveBtn.click();
                this.eleConfirmDontSaveBtn.click();
            }
        }catch (NoSuchElementException | NoSuchWindowException e){

        }
        //Switch to main tab
        List<String> tabs = this.listTabs();
        this.switchTab(1);
    }

    public void verifyListABCOTDs(List<String> lst) {
        lst.forEach(s -> {
            WebElement table = lstListABCOTDs.stream().filter(e->e.getText().toUpperCase().contains(s.toUpperCase())).findFirst().orElse(null);
            CAssertion.assertNotNull("The ABCOTDs '" + s + "' doesns't exist", table);
        });
    }
}
