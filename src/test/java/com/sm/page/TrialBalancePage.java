package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.models.CustomComponents;
import com.sm.page.components.CWidgetAPPerform;
import com.sm.page.components.CWidgetAccountBalanceView;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrialBalancePage extends BaseWorkingPaperPage {

    private static TrialBalancePage trialBalancePage;
    private Logger logger = Logger.getLogger(TrialBalancePage.class.getName());

    public static final int MAPPING = 1;
    public static final int CP_INTERIM = 2;
    public static final int CP_FINAL = 3;
    public static final int PP_INTERIM = 4;
    public static final int PP_Final = 5;
    public static final int Yes = 1;
    public static final int No = 2;
    public static final int SaveAsProposed = 1;
    public static final int Post = 2;

    @FindBy(id = "modal-trial-balance-upload")
    private WebElement eleTrialBalanceUploadModal;

    @FindBy(css = ".trial-balance-main nav.menu .item:nth-child(1)")
    private WebElement eleIntegrateDataMenu;

    @FindBy(css = ".trial-balance-main nav.menu .item:nth-child(2)")
    private WebElement eleAccountBalanceViewMenu;

    @FindBy(css = ".trial-balance-main nav.menu .item:nth-child(3)")
    private WebElement eleMappingViewMenu;

    @FindBy(css = ".trial-balance-main nav.menu .item:nth-child(4)")
    private WebElement eleFinancialStatementViewMenu;

    @FindBy(css = ".trial-balance-main nav.menu .item:nth-child(5)")
    private WebElement eleAJEandRJESummaryMenu;

    @FindBy(css = ".integrate-data-main .upload-button")
    private WebElement eleUploadFile;

    @FindBy(xpath = "(//span[contains(@class, 'trial-balance-upload-option')])[1]")
    private WebElement eleOptionTrialBalanceAmount;

    @FindBy(xpath = "(//span[contains(@class, 'trial-balance-upload-option')])[2]")
    private WebElement eleOptionTrialBalanceMapping;

    @FindBy(css = ".trial-balance-upload-file-main .attach-file-upload")
    private WebElement eleAttachFile;

    @FindBy(css = ".trial-balance-upload-file-main .upload-file-period-dropdowm")
    private WebElement elePeriodDropdown;

    @FindBy(css = ".trial-balance-upload-file-main .upload-file-period-dropdowm .menu .item:nth-child(1)")
    private WebElement elePeriodCPInterim;

    @FindBy(css = ".trial-balance-upload-file-main .upload-file-period-dropdowm .menu .item:nth-child(2)")
    private WebElement elePeriodCPFinal;

    @FindBy(css = ".trial-balance-upload-file-main .upload-file-period-dropdowm .menu .item:nth-child(3)")
    private WebElement elePeriodPPInterim;

    @FindBy(css = ".trial-balance-upload-file-main .upload-file-period-dropdowm .menu .item:nth-child(4)")
    private WebElement elePeriodPPFinal;

    @FindBy(css = ".trial-balance-upload-file-main .uploaded-finished")
    private WebElement eleNextBtn;

    @FindBy(css = ".uploaded-finished")
    private WebElement eleUploadCPFinalFinishedBtn;

    @FindBy(xpath = "//p[@class='confirm-text']")
    private WebElement eleAssessMateriality;

    @FindBy(xpath = "//span[text()='Yes']")
    private WebElement eleYesOption;

    @FindBy(xpath = "//span[contains(text(),'No')]")
    private WebElement eleNoOption;

    @FindBy(css = ".create-custom-abcotds .uploaded-finished")
    private WebElement eleUploadFinishedBtn;

    @FindBy(css = ".mapping-view-main .button-export-edit-mapping-view:nth-child(2)")
    private WebElement eleManageCustomComponentsBtn;

    @FindBy(css = ".content-left i")
    private WebElement eleCreateNewCABtn;

//    @FindBy(css = ".main-container .content-right .component-name")
    @FindBy(xpath = "//input[contains(@name,'content-area-name')]")
    private WebElement eleNameOfContentArea;

    @FindBy(css = ".content-area-item")
    private WebElement eleContentAreaItem;

    @FindBy(css=".linked-area .content-area-item")
    private List<WebElement> eleListLinkedCustomABCOTDs;

    @FindBy(css = ".trial-balance-main .list .item")
    private WebElement eleABCOTDsItem;

    @FindBy(css=".linked-area .checkbox-item")
    private List<WebElement> eleListLinkedContentAreas;

    @FindBy(css=".content-left .list .item")
    private List<WebElement> eleListCustomABCOTDs;

    @FindBy(css=".button-export-edit-mapping-view")
    private List<WebElement> eleListMappingViewBtn;

    @FindBy(css = ".main-container .content-left-header")
    private List<WebElement> eleListCustomComponentHeader;

    @FindBy(css = ".trial-balance-main .save-changes .button:nth-child(2)")
    private WebElement eleCreateComponentBtn;

    @FindBy(css = ".trial-balance-main .save-changes .button")
    private WebElement eleSaveChangesBtn;

    @FindBy(css=".integrate-data-list-main tr.amount-cell")
    private List<WebElement> eleListTrialBalanceFiles;

    @FindBy(css = ".grid-background")
    private WebElement eleAccountBalanceGridBackGround;

    @FindBys(@FindBy(css="tbody tr"))
    private List<WebElement> eleListAccountBalance;

    @FindBy(css = ".account-balance-data-button .add-journal-entry-button")
    private WebElement eleAddJournalEntryBtn;

    @FindBy(css = ".journal-entry-modal .journal-entry-dropdown")
    private WebElement eleTypeOfJournalEntryDropdown;

    @FindBy(css = ".journal-entry-modal .journal-entry-dropdown .item")
    private List<WebElement> eleListTypeOfJournalEntry;

    @FindBy(css = ".journal-entry-lines .ht_master tbody tr:nth-child(1) td:nth-child(4)")
    private WebElement eleDebitAmount;

    @FindBy(css = ".journal-entry-lines .ht_master tbody tr:nth-child(2) td:nth-child(5)")
    private WebElement eleCreditAmount;

    @FindBy(css = ".journal-entry-description textarea")
    private WebElement eleDescriptionOfEntry;

    @FindBy(css = ".journal-entry-modal")
    private WebElement eleJournalEntryModal;

    @FindBy(css = ".journal-entry-modal .btn-save")
    private WebElement eleSaveAsProposedBtn;

    @FindBy(css = ".journal-entry-modal .btn-post")
    private WebElement elePostBtn;

    @FindBy(id="file")
    private WebElement eleUpload;

    private CWidgetAccountBalanceView cWidgetAccountBalanceView;

    public TrialBalancePage(WebDriver driver) {
        super(driver);
    }
    public static TrialBalancePage getInstance(WebDriver webDriver){
        if(trialBalancePage == null) return new TrialBalancePage(webDriver);
        return trialBalancePage;
    }

    @Override
    public boolean isLoad(){
        waitForElementToBeClickable(this.eleIntegrateDataMenu);
        return wpNameOnHeader().contains("Trial Balance");
    }

    /**
     * Upload Trial balance file
     * @param type MAPPING, CP Interim, CP Final, PP Interim, PP Final
     * @param path path of file
     * @param date period end date
     * @param accessMateriality Answer access materiality (Yes=1 / No=2)
     */
    public void uploadTrialBalanceFile(int type, String path, String date, int accessMateriality) {

        //Click on Integrate Data menu
        waitForVisibleElement(eleUploadFile, 120);
        waitForElementToBeClickable(eleIntegrateDataMenu, 20);
        logger.info("-- I click on Integrate Data Menu");
        click(eleIntegrateDataMenu);

        logger.info("-- I click on Upload File button");
        click(eleUploadFile);

        path = System.getProperty("user.dir") + "\\" + path;
        //Checking file
        File file = new File(path);
        if(!file.exists() || !file.isFile()){
            logger.error(path + " doesnt exit'");
            return;
        }
        switch (type) {
            case MAPPING:
                this.uploadMapping(path);
                break;
            case CP_INTERIM:
                this.uploadCPInterim(path, date);
                break;
            case CP_FINAL:
                this.uploadCPFinal(path, date, accessMateriality);
                break;
            case PP_INTERIM:
                this.uploadPPInterim(path, date);
                break;
            case PP_Final:
                this.uploadPPFinal(path, date);
                break;
            default: break;
        }

        //click Next button
        waitForElementToBeClickable(this.eleNextBtn);
        logger.info("-- I click on Next button");
        click(eleNextBtn);
        if(type == MAPPING){
            try {
                waitForVisibleElement(this.eleUploadFinishedBtn, 10);
                logger.info("-- I click on Finish button");
                click(eleUploadFinishedBtn);
            }catch (NoSuchElementException | TimeoutException e){
                System.out.println(e.getMessage());
            }
        }
        if(type == CP_FINAL) {
            //Answer on Access materiality
            this.answerAssessMateriality(accessMateriality);
            try {
                waitForVisibleElement(this.eleUploadCPFinalFinishedBtn, 10);
                logger.info("-- I click on Finish button");
                click(eleUploadCPFinalFinishedBtn);
            }catch (NoSuchElementException | TimeoutException e){
                System.out.println(e.getMessage());
            }
        }
        //wait for back to integrate tab
        waitUtilElementHidden(eleTrialBalanceUploadModal, 60);
        waitForDimmerDisappeared();
        waitForElementToBeClickable(this.eleUploadFile, 10);
    }

    public void checkResultAfterUploadFile(String type, String fileName, String period, String periodEnd, String dateUploaded, String status){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date  = new Date();
        if("Now".equals(periodEnd)) {
            periodEnd = sdf.format(date);
        }
        if("Now".equals(dateUploaded)) {
            dateUploaded = sdf.format(date);
        }
        WebElement rs = null;
        PageFactory.initElements(wedDriver, eleListTrialBalanceFiles);
        for (WebElement ele: this.eleListTrialBalanceFiles){
            List<WebElement> eleTds = ele.findElements(By.cssSelector("td.integratedatalist"));
            String actualUploadType = eleTds.get(1).getText();
            String actualFileName = eleTds.get(2).getText();
            String actualPeriod = eleTds.get(3).getText();
//            String actualPeriodEnd = eleTds.get(4).getText();
//            String actualDateUploaded = eleTds.get(5).getText();
            String actualStatus = eleTds.get(6).getText();
            if(status.equals(actualStatus)
                && period.equals(actualPeriod)
                && fileName.equals(actualFileName)
                && type.equals(actualUploadType)){
                rs = ele;
                break;
            }
        }
        org.junit.Assert.assertNotNull("Can not find TB data", rs);
    }

    private void uploadPPFinal(String path, String date) {
        logger.info("-- I click on Trial balance Amount option");
        //Selecting trial balance amount
        click(eleOptionTrialBalanceAmount);
        logger.info("-- I click on Period End dropdown");
        //selecting period CP Interim
        click(this.elePeriodDropdown);

        logger.info("-- I click on Period PP Final option");
        click(elePeriodPPFinal);

        logger.info("-- I send key " + path + " on attach file");
        this.eleUpload.sendKeys(path);
    }

    private void uploadPPInterim(String path, String date) {
        //Selecting trial balance amount
        logger.info("-- I click on Trial balance Amount option");
        click(eleOptionTrialBalanceAmount);
        //selecting period CP Interim
        logger.info("-- I click on Period End dropdown");
        click(elePeriodDropdown);

        logger.info("-- I click on Period PP Interim option");
        click(elePeriodPPInterim);

        logger.info("-- I send key " + path + " on attach file");
        this.eleUpload.sendKeys(path);
    }

    private void uploadMapping(String path) {
        //selecting trial balance mapping option
        logger.info("-- I click on Trial balance mapping option");
        click(eleOptionTrialBalanceMapping);

        logger.info("-- I send key " + path + " on attach file");
        this.eleUpload.sendKeys(path);
    }

    private void uploadCPInterim(String path, String date) {
        //Selecting trial balance amount
        logger.info("-- I click on Trial balance Amount option");
        click(eleOptionTrialBalanceAmount);

        //selecting period CP Interim
        logger.info("-- I click on Period End dropdown");
        click(elePeriodDropdown);

        logger.info("-- I click on CP Interim option");
        click(elePeriodCPInterim);
        logger.info("-- I send key " + path + " on attach file");
        this.eleUpload.sendKeys(path);
    }

    private void uploadCPFinal(String path, String date, int accessMateriality) {
        //Selecting trial balance amount
        click(eleOptionTrialBalanceAmount);
        //selecting period CP Final
        click(elePeriodDropdown);
        click(elePeriodCPFinal);
        waitForDimmerDisappeared();
        this.eleUpload.sendKeys(path);
    }

    private void answerAssessMateriality(int accessMateriality) {
        switch (accessMateriality) {
            case Yes:
                try {
                    waitForElementToBeClickable(this.eleYesOption, 10);
                    click(eleYesOption);
                }catch (NoSuchElementException | TimeoutException e){
                    System.out.println(e.getMessage());
                }
                break;
            case No:
                try {
                    waitForElementToBeClickable(this.eleNoOption, 10);
                    click(eleNoOption);
                }catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default: break;
        }
    }

    public void createCustomCA(String name) {
        waitForVisibleElement(eleUploadFile, 120);
        waitForElementToBeClickable(eleMappingViewMenu);
        logger.info("-- I click on Mapping View menu");
        click(eleMappingViewMenu);
        waitForDimmerDisappeared();

        waitForElementToBeClickable(eleManageCustomComponentsBtn);
        logger.info("-- I click on Manage Custom Component button");
        click(eleManageCustomComponentsBtn);
        waitForVisibleElement(eleABCOTDsItem, 30);

        logger.info("-- I click on Create New CA button");
        waitForElementToBeClickable(eleCreateNewCABtn);
        click(eleCreateNewCABtn);
        waitForDimmerDisappeared();
        waitForVisibleElement(eleContentAreaItem, 10);

        waitForElementToBeClickable(eleNameOfContentArea, 5);
        logger.info("-- I type on field content area name: " + name);
        this.eleNameOfContentArea.sendKeys(name);
    }

    /**
     * select list linked custom ABCOTDs
     * @param customABCOTDs
     */
    public void selectLinkedCustomABCOTDs(List<String> customABCOTDs) {
        waitForVisibleElement(eleContentAreaItem, 10);
        for (String customABCOTD : customABCOTDs) {
            WebElement ele = this.eleListLinkedCustomABCOTDs.stream().filter(e -> {
                boolean rs = false;
                WebElement eleName = e.findElement(By.cssSelector(".linked-area .content-area-item .checkbox-item label"));
                if(eleName != null && eleName.getText().equals(customABCOTD)) {
                    rs = true;
                }
                return rs;
            }).findFirst().orElse(null);
            Assert.assertNotNull(ele, "The custom ABCOTDs doesn't exist.");

            WebElement cb = ele.findElement(By.cssSelector(".linked-area .content-area-item .checkbox-item"));
            if(cb == null || !cb.isEnabled()) continue;
            logger.info("-- I select custom ABCOTD: " + customABCOTD);
            click(cb);
            waitSomeSecond(0.2);
        }
        waitForElementToBeClickable(eleCreateComponentBtn, 5);
        logger.info("-- Click on Create Component button");
        click(eleCreateComponentBtn);
        waitForDimmerDisappeared();
    }

    /**
     * Map Custom ABCOTDs to Linked Content Areas
     * @param datas
     */
    public void mapCustomABCOTDsToLinkedContentAreas(List<CustomComponents> datas) {
        for (CustomComponents r : datas) {
            String abcotd = r.getAbcotd();
            WebElement eleabcotd = this.eleListCustomABCOTDs.stream().filter(ele -> ele.getText().toUpperCase().contains(abcotd.toUpperCase())).findFirst().orElse(null);
            Assert.assertNotNull(eleabcotd,"Does not find abcotd" + abcotd);
            logger.info("-- I select ABCOTDs on Manage Custom Component: " + abcotd);
            click(eleabcotd);

            String contentAreas = r.getContentAreas();
            WebElement eleContentAreas = this.eleListLinkedContentAreas.stream().filter(ele -> ele.getText().toUpperCase().contains(contentAreas.toUpperCase())).findFirst().orElse(null);
            Assert.assertNotNull(eleContentAreas, "Does not find contentAreas" + contentAreas);
            logger.info("-- I select content area: " + contentAreas);
            click(eleContentAreas);
        }
        waitForElementToBeClickable(eleSaveChangesBtn, 10);
        logger.info("-- I click on Save Change button.");
        click(eleSaveChangesBtn);
        waitForDimmerDisappeared();
    }

    /**
     * Find linked custom ABCOTDs by name
     * @param name
     * @return
     */
    public WebElement findLinkedCustomABCOTDsByName(String name) {

        for(WebElement ele : this.eleListLinkedCustomABCOTDs) {
            WebElement td = ele.findElement(By.cssSelector(".checkbox-item label"));
            if(td.getText().contains(name)) return td;
        }
        return null;
    }

    /**
     * Select linked custom ABCOTDs by name
     * @param name
     */
    public void selectLinkedCustomABCOTDsByName(String name) {
        WebElement ele = findLinkedCustomABCOTDsByName(name);
        click(ele);
    }

    public void closeManageCustomComponents() {
        WebElement body = wedDriver.findElement(By.xpath("//body"));
        body.sendKeys(Keys.ESCAPE);
        waitSomeSecond(1);
    }

    /**
     * Select account in Account Balance View
     * @param data
     */
    public void selectAccountInAccountBalanceView(List<String> data) throws InterruptedException {
        Thread.sleep(1000);
        waitForVisibleElement(eleUploadFile, 120);
        waitForElementToBeClickable(eleAccountBalanceViewMenu);
        click(eleAccountBalanceViewMenu);
        waitForDimmerDisappeared();
        this.select(data);
    }

    /**
     * Select account by account name
     * @param accounts
     */
    public void select(List<String> accounts) {
        for (String account : accounts) {
            WebElement ele = this.eleListAccountBalance.stream().filter(e -> {
                boolean rs = false;
                WebElement eleName = e.findElement(By.cssSelector("tbody tr> td:nth-child(4)"));
                if(eleName != null && eleName.getText().equals(account)) {
                    rs = true;
                }
                return rs;
            }).findFirst().orElse(null);

            if(ele == null) continue;
            WebElement cb = ele.findElement(By.cssSelector(".htCheckboxRendererInput"));
            if(cb == null || !cb.isEnabled()) continue;
            click(cb);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find type of journal entry by name
     * @param name
     * @return
     */
    public WebElement findTypeOfJournalEntryByName(String name) {

        for(WebElement ele : this.eleListTypeOfJournalEntry) {
            WebElement td = ele.findElement(By.cssSelector(".journal-entry-modal .journal-entry-dropdown .item .text"));
            if(td.getText().contains(name)) return td;
        }
        return null;
    }

    /**
     * Select type of journal entry by name
     * @param name
     */
    public void selectTypeOfJournalEntryByName(String name) {
        WebElement ele = findTypeOfJournalEntryByName(name);
        click(ele);
    }

    public void addJournalEntry(String type) {
        waitForElementToBeClickable(eleAddJournalEntryBtn);
        click(eleAddJournalEntryBtn);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeClickable(eleTypeOfJournalEntryDropdown);
        click(eleTypeOfJournalEntryDropdown);
        selectTypeOfJournalEntryByName(type);
    }

    public void inputAmountInJournalEntry(String amount, String text) {
        waitForElementToBeClickable(eleDebitAmount);
        try{
            doubleClick(eleDebitAmount);
            WebElement eleTextArea = wedDriver.findElement(By.cssSelector(".handsontableInputHolder textarea.handsontableInput"));
            eleTextArea.sendKeys(amount);
        }catch (NoSuchElementException | TimeoutException e){
            System.out.println(e.getMessage());
        }
        waitSomeSecond(1);
        try{
            doubleClick(eleCreditAmount);
            WebElement eleTextArea = wedDriver.findElement(By.cssSelector(".handsontableInputHolder textarea.handsontableInput"));
            eleTextArea.sendKeys(amount);

        }catch (NoSuchElementException | TimeoutException e){
            System.out.println(e.getMessage());
        }
        waitSomeSecond(1);
        click(eleDescriptionOfEntry);
        eleDescriptionOfEntry.sendKeys(text);
    }

    public void postJournalEntry(int type) {
        switch (type) {
            case SaveAsProposed:
                try {
                    waitForVisibleElement(this.eleSaveAsProposedBtn, 10);
                    eleSaveAsProposedBtn.click();
                }catch (NoSuchElementException | TimeoutException e){
                    System.out.println(e.getMessage());
                }
                break;
            case Post:
                try {
                    waitForVisibleElement(this.elePostBtn, 10);
                    elePostBtn.click();
                }catch (NoSuchElementException | TimeoutException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default: break;
        }
        waitUtilElementHidden(eleJournalEntryModal, 10);
        waitSomeSecond(2);
    }
}
