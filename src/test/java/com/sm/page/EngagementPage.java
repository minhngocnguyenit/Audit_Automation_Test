package com.sm.page;

import com.sm.assertion.CAssertion;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EngagementPage extends PageInit {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static EngagementPage engagementPage;
    public static final int Filter = 1;
    public static final int UnFilter = 2;
    public static final int RoMMs = 1;
    public static final int Controls = 2;
    public static final int Procedures = 3;

    @FindBy(css = ".vsa-nav nav a.item")
    private List<WebElement> eleHeaderMenu;

    @FindBy(css = ".summaries-page nav a.item")
    private List<WebElement> eleSummaryPageMenu;

    @FindBy( css="table.wp-list tr.workingpaper")
    private List<WebElement> eleListWPs;

    @FindBy(css = "table.wp-list tr.subphase")
    private List<WebElement> eleListSubphase;

    @FindBy(css = ".search-container button[role='button']")
    private WebElement eleFileCheckBtn;

    @FindBy(css = ".create-wp-btn")
    private WebElement eleCreateNewBtn;

    @FindBy(css = ".create-wp-btn .menu .item:nth-child(1)")
    private WebElement eleStandardWorkingPaper;

    @FindBy(css = ".create-wp-btn .menu .item:nth-child(2)")
    private WebElement eleCustomWorkingPaper;

    @FindBy(css = ".create-wp-btn .menu .item:nth-child(3)")
    private WebElement eleSubPhase;

    @FindBy(css=".wp-list-standard > thead .fal")
    private WebElement eleCheckAllStandWP;

    @FindBy(css = ".topnav-add-selected-wp")
    private WebElement eleAddSelectedWPBtn;

    @FindBy(css = ".topnav-create-wp-model")
    private WebElement eleCreateCustomWpPopUp;

    @FindBy(css = ".topnav-icon-dropdown")
    private WebElement eleCreateCustomWpDropdown;

    @FindBy(css = ".topnav-icon-dropdown .menu .item:nth-child(1)")
    private WebElement elePerformOption;

    @FindBy(css = ".topnav-icon-dropdown .menu .item:nth-child(2)")
    private WebElement eleOtherOption;

    @FindBy(css = ".topnav-backNextAndCreate")
    private WebElement eleCreateCustomWpNextBtn;

    @FindBy(xpath = "//input[@placeholder='Give your working paper a name']")
    private WebElement eleCreateCustomWpInputNameField;

    @FindBy(css = ".segment-header")
    private WebElement eleROMMSummaryTitle;

    @FindBy(css = ".btn-export-all")
    private WebElement eleExportAllBtn;

    @FindBy(css = ".btn-export-all .menu .item:nth-child(1)")
    private WebElement eleExportUnfilteredTableBtn;

    @FindBy(css = ".btn-export-all .menu .item:nth-child(2)")
    private WebElement eleExportFilteredTableBtn;

    @FindBy(css = ".romms-table .btn-export")
    private WebElement eleROMMTableExportBtn;

    @FindBy(css = ".control-table .btn-export")
    private WebElement eleControlTableExportBtn;

    @FindBy(css = ".procedure-table .btn-export")
    private WebElement eleProcedureTableExportBtn;

    @FindBy(css = ".romms-table .filter-btn")
    private WebElement eleROMMTableFilterBtn;

    @FindBy(css = ".control-table .filter-btn")
    private WebElement eleControlTableFilterBtn;

    @FindBy(css = ".procedure-table .filter-btn")
    private WebElement eleProcedureTableFilterBtn;

    @FindBy(css = ".filter-actions .filter-apply .button")
    private WebElement eleFilterApply;

    @FindBys(@FindBy(css=".filter-popup .filter-dropdown"))
    private List<WebElement> eleListFilterDropdown;

    @FindBy(css=".filter-popup .filter-dropdown.active")
    private WebElement eleROMMSummaryFilterDropdownActive;

    @FindBy( css=".filter-popup .filter-dropdown .item")
    private List<WebElement> eleListFilterDropdownItem;

    @FindBys(@FindBy(css = ".wp-list-standard tbody tr"))
    private List<WebElement> eleListStandardWPs;

    @FindBy(css = ".topnav-create-standard-wp-model .topnav-cancelBtn")
    private WebElement eleCancelStandardWP;

    private String engagementName;

    public String getEngagementName() {
        return engagementName;
    }

    public void setEngagementName(String engagementName) {
        this.engagementName = engagementName;
    }

    public EngagementPage(WebDriver driver) {
        super(driver);
    }

    public static EngagementPage getInstance(WebDriver webDriver){
        if(engagementPage == null) return new EngagementPage(webDriver);
        return engagementPage;
    }

    public boolean isLoad(){
        return this.eleFileCheckBtn.isDisplayed();
    }

    /**
     * Open File Check
     */
    public void openFileCheck() {
        click(eleFileCheckBtn);
        waitSomeSecond(1);
        this.switchTab(1);
    }

    public void openWorkingTab() {
        click(this.eleHeaderMenu.get(0));
        waitForDimmerDisappeared();
    }

    /**
     * Open trial balance from menu on header
     */
    public void openTrialBalanceMenu(){
        logger.info("-- I click Trial balance menu on header");
        click(this.eleHeaderMenu.get(1));
        //Wait new tab is displays
        logger.info("-- I switch to tab index 1");
        this.switchTab(1);
    }

    /**
     * Open Risk summary from menu on header
     */
    public void openRiskSummary(){
        logger.info("-- I click on Summary menu");
        click(this.eleHeaderMenu.get(2));
        waitForDimmerDisappeared();
        waitForElementToBeClickable(this.eleSummaryPageMenu.get(2), 5);
        logger.info("-- I click on Risk Summary menu");
        click(this.eleSummaryPageMenu.get(2));
        waitForDimmerDisappeared();
    }

    /**
     * Find row element of a working paper by wp name
     * @param name
     * @return
     */
    public WebElement findWorkingPaperByName(String name) {
        return this.eleListWPs.stream().filter(e -> e.findElement(By.cssSelector(".sub-name")).getText().toUpperCase().equals(name.toUpperCase())).findFirst().orElse(null);
    }

    public boolean isPendingActiveWP(WebElement ele) {
        return attributeElement(ele, "class").contains("disabled");
    }

    /**
     * Open a working paper by name
     * @param name
     */
    public void openWorkingPaperByName(String name) {
        WebElement ele = findWorkingPaperByName(name);
        Assert.assertNotNull("The WP '" + name + "' doesn't exist.", ele);
        scrollToElement(ele);
        boolean isPendingWP = isPendingActiveWP(ele);
        Assert.assertFalse("The WP '" + name + "' is pending active", isPendingWP);
        WebElement td = ele.findElement(By.cssSelector(".sub-name"));
        click(td);
    }

    /**
     * Open a working paper by name
     * @param wpName
     */
    public void openWorkingPage(String wpName){
        logger.info("-- I open working paper: " + wpName);
        this.openWorkingPaperByName(wpName);
        waitSomeSecond(2);
        logger.info("-- I switch tab browser: " + 1);
        this.switchTab(1);
        waitForDimmerDisappeared();
    }

    /**
     * Create standard working paper
     * @throws InterruptedException
     */
    public void createStandardWorkingPaper(List<String> data) {
        click(this.eleCreateNewBtn);
        waitForElementToBeClickable(this.eleStandardWorkingPaper, 5);
        click(this.eleStandardWorkingPaper);
        waitSomeSecond(1);
        waitForElementToBeClickable(this.eleCheckAllStandWP, 5);

        AtomicInteger count = new AtomicInteger(0);
        for(WebElement ele : this.eleListStandardWPs) {
            WebElement eleCb = ele.findElement(By.cssSelector(".cell-check-box i"));
            String generated = ele.findElement(By.cssSelector(".cell-status")).getText();
            String name = ele.findElement(By.cssSelector(".cell-name .sub-name")).getText();

            data.forEach(d -> {
                if(d.equalsIgnoreCase(name) && generated.equalsIgnoreCase("Not generated")) {
                    count.incrementAndGet();
                    click(eleCb);
                    return;
                }
            });
        }
        if(count.get()> 0) {
            waitForElementToBeClickable(this.eleAddSelectedWPBtn, 5);
            click(this.eleAddSelectedWPBtn);
        }else{
            click(eleCancelStandardWP);
        }
        waitUtilElementHidden(eleCancelStandardWP, 10);
    }

    /**
     * Create custom WP
     * @throws InterruptedException
     */
    public void createCustomWorkingPaper(String wpName)  {
        click(this.eleCreateNewBtn);
        waitForElementToBeClickable(this.eleCustomWorkingPaper, 30);
        click(this.eleCustomWorkingPaper);
        waitForElementToBeClickable(this.eleCreateCustomWpPopUp, 30);
        click(this.eleCreateCustomWpDropdown);
        waitForElementToBeClickable(this.elePerformOption, 30);
        click(this.elePerformOption);
        waitForElementToBeClickable(this.eleCreateCustomWpNextBtn, 30);
        click(this.eleCreateCustomWpNextBtn);
        waitSomeSecond(1);
        click(this.eleCreateCustomWpNextBtn);
        waitForElementToBeClickable(this.eleCreateCustomWpInputNameField, 30);
        click(this.eleCreateCustomWpInputNameField);
        this.eleCreateCustomWpInputNameField.sendKeys(wpName);
        waitSomeSecond(1);
        waitForElementToBeClickable(this.eleCreateCustomWpNextBtn, 30);
        click(this.eleCreateCustomWpNextBtn);

        waitUtilElementHidden(eleCreateCustomWpNextBtn, 5);
    }

    /**
     * Check existing wp
     * @param wps
     */
    public void checkExitingWps(List<String> wps) {

        StringBuffer sb = new StringBuffer();
        this.refresh();
        waitForDimmerDisappeared();
        //Wait for refresh completely
        waitSomeSecond(2);
        wps.forEach(wp -> {
            WebElement ele = findWorkingPaperByName(wp);
            if(ele == null)
                sb.append(String.format("The working paper '%s' doesn't exist\n", wp));
        });
        if(sb.length() > 0) {
            Assert.fail(sb.toString());
        }
    }

    /**
     * Check don't existing wp
     * @param wps
     */
    public void checkDontExistingWps(List<String> wps) {
        StringBuffer sb = new StringBuffer();
        this.refresh();
        waitForDimmerDisappeared();
        //Wait for refresh completely
        waitSomeSecond(2);
        wps.forEach(wp -> {
            WebElement ele = findWorkingPaperByName(wp);
            if(ele != null) {
                sb.append(String.format("The working paper '%s' already exist\n", wp));
            }
        });
        if(sb.length() > 0) {
            Assert.fail(sb.toString());
        }
    }

    /**
     * Export all function in ROMM summary
     */
    public void exportAll(int f){
            waitForElementToBeClickable(this.eleExportAllBtn);
            click(this.eleExportAllBtn);
            switch (f) {
                case Filter:
                    try {
                        waitForVisibleElement(this.eleExportFilteredTableBtn, 10);
                        eleExportFilteredTableBtn.click();
                    } catch (NoSuchElementException | TimeoutException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UnFilter:
                    try {
                        waitForVisibleElement(this.eleExportUnfilteredTableBtn, 10);
                        eleExportUnfilteredTableBtn.click();
                    } catch (NoSuchElementException | TimeoutException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
    }

    /**
     * Export function in Each table
     */
    public void exportFuncInTable(int type){
        switch (type) {
            case RoMMs:
                try {
                    waitForElementToBeClickable(this.eleROMMTableExportBtn);
                    click(this.eleROMMTableExportBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Controls:
                try {
                    waitForElementToBeClickable(this.eleControlTableExportBtn);
                    click(this.eleControlTableExportBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Procedures:
                try {
                    waitForElementToBeClickable(this.eleProcedureTableExportBtn);
                    click(this.eleProcedureTableExportBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
        waitSomeSecond(2);
    }

    /**
     * Verify attachment is downloaded
     */
    public boolean verifyAttachmentFileIsDownload(String fileName) {
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "download";
        File downloadFolder = new File(downloadFilepath);
        File[] files = downloadFolder.listFiles();
        for(File f : files) {
            if(f.getName().contains(fileName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Filter function in table
     */
    public void setFilterInTable(int type, String title, String select, String value) {
        switch (type) {
            case RoMMs:
                try {
                    waitForElementToBeClickable(this.eleROMMTableFilterBtn, 5);
                    click(this.eleROMMTableFilterBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Controls:
                try {
                    scrollBottomOfElement(eleControlTableFilterBtn);
                    waitForElementToBeClickable(this.eleControlTableFilterBtn, 5);
                    click(this.eleControlTableFilterBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case Procedures:
                try {
                    waitForElementToBeClickable(this.eleProcedureTableFilterBtn, 5);
                    click(this.eleProcedureTableFilterBtn);
                } catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                break;
        }
        try{
            waitForElementToBeClickable(this.eleListFilterDropdown.get(0), 5);
            click(this.eleListFilterDropdown.get(0));
            selectFilterOptionByText(title);

            waitSomeSecond(0.5);

            waitForElementToBeClickable(this.eleListFilterDropdown.get(1), 5);
            click(this.eleListFilterDropdown.get(1));
            selectFilterOptionByText(select);

            waitSomeSecond(0.5);

            waitForElementToBeClickable(this.eleListFilterDropdown.get(2));
            click(this.eleListFilterDropdown.get(2));
            selectFilterOptionByText(value);

            waitSomeSecond(0.5);

            //apply filter
            waitForElementToBeClickable(this.eleFilterApply);
            click(this.eleFilterApply);
            waitSomeSecond(1);

        }catch (NoSuchElementException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Find row element of Filter by name
     * @param name
     * @return
     */
    public WebElement findFilterInRoMMsTableByName(String name) {
        return eleROMMSummaryFilterDropdownActive.findElement(By.xpath("//div[contains(@class, 'menu')]/div/span[text()='" + name + "']"));
    }

    /**
     * Select filter option by text
     * @param text
     */
    public void selectFilterOptionByText(String text) {
        WebElement ele = findFilterInRoMMsTableByName(text);
        waitForElementToBeClickable(ele, 5);
        Assert.assertNotNull("Filter '" + text + "' doesn't exist", ele);
        click(ele);
    }

    public WebElement findSubphaseByName(String name) {
        return eleListSubphase.stream().filter(
                e -> e.findElement(By.cssSelector(".id-and-name")).getText().toUpperCase().contains(name.toUpperCase())
        ).findFirst().orElse(null);
    }

    /**
     * Check existing subphase
     * @param subphase
     */
    public void checkExistingSubphase(List<String> subphase) {
        StringBuffer sb = new StringBuffer();
        refresh();
        waitForDimmerDisappeared();
        waitSomeSecond(2);
        for (String s : subphase) {
            WebElement ele = findSubphaseByName(s);
            if(ele == null) {
                sb.append(String.format("Subphase '%s' doesn't exit\n", s));
            }
        }
        if(sb.length() > 0) {
            Assert.fail(sb.toString());
        }
    }
}
