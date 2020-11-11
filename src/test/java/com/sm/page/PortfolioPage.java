package com.sm.page;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PortfolioPage extends PageInit {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static PortfolioPage portfolioPage;

    private static final String ENGAGEMENT_STATUS_IN_PROGRESS = "In Progress";
    private static final String ENGAGEMENT_STATUS_ARCHIVE_INTEGRITY_CHECK_IN_PROGRESS = "Archive integrity check in progress";
    private static final String ENGAGEMENT_STATUS_PENDING_CREATION = "Pending Creation";
    private static final String ENGAGEMENT_STATUS_ARCHIVE_PENDING_APPROVAL = "Archive pending approval";
    private static final String ENGAGEMENT_STATUS_ARCHIVE_REJECTED = "Archive rejected";
    private static final String ENGAGEMENT_STATUS_SUBMITTED_FOR_DELETION = "Submitted for Deletion";


    @FindBy(css = ".engagement-portfolio .create")
    private WebElement eleCreateEngButton;

    @FindBy(css=".modal.begin-modal")
    WebElement eleBeginModal;

    @FindBy(css = ".modal.engagement-creation")
    WebElement eleCreationModal;

    @FindBy(css=".portfolio-actions .portfolio-dropdown:nth-child(1)")
    WebElement eleDeleteButton;

    @FindBy(css=".portfolio-actions .portfolio-dropdown:nth-child(2)")
    WebElement eleArchiveButton;

    @FindBy(xpath="(//input[@class='search'])[1]")
    WebElement eleContentFromDropdown;

    @FindBy(xpath = "//div[@role='listbox']/div[contains(@class, 'item')]")
    List<WebElement> eleContentFromList;

    @FindBy(css = ".radios .checkbox")
    List<WebElement> eleCloudCabinetList;

    @FindBy(xpath="(//input[@class='search'])[2]")
    WebElement eleLanguageDropdown;

    @FindBy(xpath = "//div[@role='listbox']/div[contains(@class, 'item')]")
    List<WebElement> eleLanguageList;

    @FindBy(css="button.begin")
    WebElement eleBeginButton;

    @FindBy(css="button.cancel")
    WebElement eleCancelButton;

    @FindBy(css = ".section-title")
    WebElement eleSectionTitle;

    @FindBy(css = ".engagement-creation .input-limit > input")
    WebElement eleEngagementName;

    @FindBy(xpath = "(//input[@class='search'])[3]")
    WebElement eleEngagementType;

    @FindBy(xpath = "//div[@role='listbox']/div[contains(@class, 'item')]")
    List<WebElement> eleEngagementTypeList;

    @FindBy(css = ".engagement-creation .date-picker input")
    WebElement elePeriodEndDate;

    @FindBy(xpath = "(//input[@class='search'])[4]")
    WebElement eleDropdownCountry;

    @FindBy(xpath = "//div[@role='listbox']/div[contains(@class, 'item')]")
    List<WebElement> eleCountryList;

    @FindBy(xpath = "(//input[@class='search'])[5]")
    WebElement eleOffice;

    @FindBy(xpath = "//div[@role='listbox']/div[contains(@class, 'item')]")
    List<WebElement> eleOfficeList;

    @FindBy(css = ".engagement-creation .fixed-footer .primary")
    WebElement eleNextButton;

    @FindBy(css = "div.input-limit .input input")
    WebElement eleEntityName;

    @FindBy(xpath = "//div[contains(@id, 'check-list-industry')]/div/label")
    List<WebElement> eleIndustryList;


    @FindBy(xpath = "//div[contains(@id, 'check-list-financialReportingFramework')]/div/label")
    List<WebElement> eleReportFinancialList;

    @FindBy(xpath = "//div[contains(@id, 'check-list-engagementStandardsGaas')]/div/label")
    List<WebElement> eleEngagementStandardList;

    @FindBy(css = ".team-role .option > div:nth-child(1)")
    WebElement eleSelectRole;

    @FindBy(css = ".team-role .menu .item")
    List<WebElement> eleRoleList;

    @FindBy(xpath = "//button[contains(.,'Finish')]")
    WebElement eleFinishButton;

    @FindBy(css = ".engagement-portfolio table tbody tr")
    private List<WebElement> eleEngagementList;

    @FindBy(css = ".engagement-pending.creation")
    private List<WebElement> elePendingEngagementList;

    @FindBy(css = ".independence-confirm-modal .content")
    private WebElement eleIndependentContent;

    @FindBy(css = ".independence-confirm-modal .confirmBtn")
    private WebElement eleIndependentConfirmBtn;

    @FindBy(css = ".user-nav .text")
    private WebElement eleUserName;

    @FindBy(css = ".brand-logo")
    private WebElement eleLogo;

    @FindBy(css = ".auv-archiveModal")
    private WebElement eleArchiveRejectModal;

    @FindBy(css = ".auv-archiveModal .reason")
    private WebElement eleArchiveRejectReason;

    @FindBy(css = ".auv-archiveModal .btn-update")
    private WebElement eleArchiveRejectBtn;

    @FindBy(css = ".auv-confirmRejectModal .btn-update")
    private WebElement eleConfirmRejectArchiveBtn;

    @FindBy(css = ".auv-archiveRejectInfo")
    private WebElement eleArchiveRejectInfoModal;

    @FindBy(css = ".auv-archiveRejectInfo .btn-close")
    private WebElement eleCloseArchiveRejectInfoBtn;

    @FindBy(css = ".auv-archiveModal")
    private WebElement eleApproveArchiveModal;

    @FindBy(css = ".auv-archiveModal .btn-update")
    private WebElement eleApproveArchiveContinueBtn;

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public static PortfolioPage getInstance(WebDriver driver) {
        if(portfolioPage == null) return new PortfolioPage(driver);
        return portfolioPage;
    }

    public void isLoad(){
        waitForElementToBeClickable(this.eleCreateEngButton, 60);
    }

    private WebElement findEngagementByName(String name) {
        return eleEngagementList.stream().filter(
                webElement -> name.equals(webElement.findElement(By.cssSelector(".engagement-clm")).getText())
        ).findFirst().orElse(null);
    }

    public String getEngagementStatus(WebElement ele) {
        return ele.findElement(By.cssSelector("td.status-clm")).getText();
    }

    /**
     * Find an engagement by name and status
     * @param name
     * @param status
     * @return
     */
    private WebElement findEngagementByNameAndStatus(String name, String status) {
//        if(getEngagementNamePrefix() != null) name += getEngagementNamePrefix();
//        String finalName = name;
//        logger.info("----Finding engagement name: " + finalName);
        return eleEngagementList.stream().filter(
                webElement -> name.equals(webElement.findElement(By.cssSelector(".engagement-clm")).getText()) && status.equalsIgnoreCase(webElement.findElement(By.cssSelector("td.status-clm")).getText())
        ).findFirst().orElse(null);
    }

    public void verifyEngagementStatus(String engagement, String status) {
        WebElement ele = findEngagementByNameAndStatus(engagement, status);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);
        Assert.assertEquals("The status is not match", status.toUpperCase(), getEngagementStatus(ele).toUpperCase());
    }

    public void waitEngagementChangeStatus(String engagementName, String oldStatus, String newStatus, int timeout) {
        refresh();
        waitForDimmerDisappeared();
        WebElement after = findEngagementByNameAndStatus(engagementName, newStatus);
        if(after != null) {
            return;
        }
        WebElement eleEngagement = findEngagementByNameAndStatus(engagementName, oldStatus);
        Assert.assertNotNull("Engagement doesn't exist", eleEngagement);
        for(int i = 0; i < timeout; i++) {
            WebElement temp = findEngagementByNameAndStatus(engagementName, newStatus);
            if(temp != null){
                break;
            }else{
                waitSomeSecond(60);
                refresh();
                waitForDimmerDisappeared();
            }
        }
        WebElement after1 = findEngagementByNameAndStatus(engagementName, newStatus);
        Assert.assertNotNull(String.format("The engagement '%s' is not changed status from '%s' to '%s' in %d minutes", engagementName, oldStatus, newStatus, timeout), after1);

    }


    /**
     * Submit archive
     * @param engagement
     */
    public void submitArchive(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_IN_PROGRESS);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleArchiveButton);

        WebElement submitArchiveButton = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Submit for archive']"));
        waitForElementToBeClickable(submitArchiveButton, 60);
        click(submitArchiveButton);
        waitForDimmerDisappeared();
    }

    /**
     * Reject archive
     * @param engagement
     */
    public void rejectArchive(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_ARCHIVE_PENDING_APPROVAL);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleArchiveButton);

        WebElement rejectArchive = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Reject archive']"));
        waitForElementToBeClickable(rejectArchive, 60);
        click(rejectArchive);
        waitForVisibleElement(eleArchiveRejectModal, 10);

        eleArchiveRejectReason.sendKeys("Reason");
        waitForElementToBeClickable(eleArchiveRejectBtn, 10);
        click(eleArchiveRejectBtn);

        waitForElementToBeClickable(eleConfirmRejectArchiveBtn, 10);
        click(eleConfirmRejectArchiveBtn);
        waitUtilElementHidden(eleArchiveRejectModal,10);
        refresh();
    }

    /**
     * Approve archive
     * @param engagement
     */
    public void approveArchive(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_ARCHIVE_PENDING_APPROVAL);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleArchiveButton);

        WebElement approveArchive = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Approve archive']"));
        waitForElementToBeClickable(approveArchive, 60);
        click(approveArchive);
        waitForVisibleElement(eleApproveArchiveModal, 10);
        click(eleApproveArchiveContinueBtn);
    }

    /**
     * Open 1 archive reject
     * @param engagement
     */
    public void openArchiveRejectEngagement(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_ARCHIVE_REJECTED);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);
        click(ele);

        waitForVisibleElement(eleArchiveRejectInfoModal, 10);
        click(eleCloseArchiveRejectInfoBtn);
        waitUtilElementHidden(eleArchiveRejectInfoModal, 10);
        waitForDimmerDisappeared();
    }

    /**
     * Submit deletion
     * @param engagement
     */
    public void submitDeletion(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_IN_PROGRESS);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleDeleteButton);

        WebElement submitDeletionButton = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Submit for deletion']"));
        waitForElementToBeClickable(submitDeletionButton, 60);
        click(submitDeletionButton);
        waitForDimmerDisappeared();
    }

    /**
     * Reject deletion
     * @param engagement
     */
    public void rejectDeletion(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_SUBMITTED_FOR_DELETION);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleDeleteButton);

        WebElement rejectDeletion = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Reject deletion']"));
        waitForElementToBeClickable(rejectDeletion, 60);
        click(rejectDeletion);
        waitForDimmerDisappeared();
        refresh();
        WebElement ele1 = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_SUBMITTED_FOR_DELETION);
        Assert.assertNull("Engagement '" + engagement + "' has been rejected deletion", ele1);
    }

    /**
     * Approve deletion
     * @param engagement
     */
    public void approveDeletion(String engagement) {
        WebElement ele = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_SUBMITTED_FOR_DELETION);
        Assert.assertNotNull("Engagement '" + engagement + "' doesn't exist", ele);

        WebElement cb = ele.findElement(By.cssSelector("td.cbx-clm input"));
        click(cb);
        click(eleDeleteButton);

        WebElement approveDeletion = ele.findElement(By.xpath("//div[contains(@class, 'menu')]/div[contains(@class, 'item') and text()='Approve deletion']"));
        waitForElementToBeClickable(approveDeletion, 60);
        click(approveDeletion);
        waitForDimmerDisappeared();
        refresh();
        WebElement ele1 = findEngagementByNameAndStatus(engagement, ENGAGEMENT_STATUS_SUBMITTED_FOR_DELETION);
        Assert.assertNull("Engagement '" + engagement + "' is disappeared from Portfolio View", ele1);
    }

    /**
     * Open an Engagement
     * @param name
     * @return
     */
    public void openEngagementByName(String name) {
        WebElement ele = findEngagementByNameAndStatus(name, ENGAGEMENT_STATUS_IN_PROGRESS);
        Assert.assertNotNull("Engagement '" + name + "' doesn't exist", ele);
        Assert.assertEquals("The engagement status is not in-progress", ENGAGEMENT_STATUS_IN_PROGRESS, getEngagementStatus(ele));
        click(ele);
    }

    /**
     * Create new engagement
     * @throws InterruptedException
     */
    public void createEngagement(String cloudCabinet, String contentFrom, String language,
                                 String engagementName, String engagementType, String periodEndDate,
                                 String country, String office, String entity,
                                 String entityChargeCode, String industry, String financialReportingFramework,
                                 String engagementStandard, String role) {

        //Check Create engagement button displays successfully
        logger.info("-- I click on create engagement button");
        waitForElementToBeClickable(this.eleCreateEngButton);
        waitUtilElementHidden(super.eleLoading, 60); //Make sure that loading element hide
        waitSomeSecond(2);
        click(eleCreateEngButton);

        waitForElementToBeClickable(this.eleBeginModal); //Wait for Modal pop-up display

        //Select cloud cabinet
        logger.info("-- I select cloud cabinet: " + cloudCabinet);
        WebElement eleCloudCabinet = eleCloudCabinetList.stream().filter(ele -> cloudCabinet.equals(ele.getText())).findFirst().orElse(null);
        if(eleCloudCabinet != null) {
            click(eleCloudCabinet);
        }
        waitSomeSecond(0.2);

        //Search and select content type
        logger.info("-- I select content from: "+ contentFrom);
        click(eleContentFromDropdown);
        eleContentFromDropdown.sendKeys(contentFrom);
//        waitSomeSecond(0.2);
        WebElement eleContentFrom = eleContentFromList.stream().filter(ele -> contentFrom.equals(ele.getText())).findFirst().orElse(null);
        if(eleContentFrom != null) {
            click(eleContentFrom);
        }
        waitSomeSecond(0.5); //Wait for selecting content first

        //Search and select language
        logger.info("-- I select language: " + language);
        click(eleLanguageDropdown);
        eleLanguageDropdown.sendKeys(language);
//        waitSomeSecond(0.2);
        WebElement eleLanguage = eleLanguageList.stream().filter(ele -> language.equals(ele.getText())).findFirst().orElse(null);
        if(eleLanguage != null){
            click(eleLanguage);
        }
        waitSomeSecond(0.5); //Wait for selecting content first

        logger.info("-- I click on Begin button.");
        waitForElementToBeClickable(this.eleBeginButton);
        click(eleBeginButton);
        waitSomeSecond(0.5);

        //Input Engagement name

        waitForElementToBeClickable(this.eleEngagementName);
        eleEngagementName.sendKeys(engagementName);

        //Select Engagement type
        logger.info("-- I select engagement type: " + engagementType);
        click(eleEngagementType);
        eleEngagementType.sendKeys(engagementType);
//        waitSomeSecond(0.2);
        WebElement eleEngagementType = eleEngagementTypeList.stream().filter(ele -> engagementType.equals(ele.getText())).findFirst().orElse(null);
        if(eleEngagementType != null) {
            click(eleEngagementType);
        }
        waitSomeSecond(0.5);

        //Select Period End Date
        logger.info("-- I select period end date");
        click(elePeriodEndDate);
        waitSomeSecond(0.5);
        elePeriodEndDate.sendKeys(Keys.ENTER);
        waitSomeSecond(0.5);

        //Select Country Canada
        logger.info("-- I select country: " + country);
        click(eleDropdownCountry);
        eleDropdownCountry.sendKeys(country);
//        waitSomeSecond(0.2);
        WebElement eleCountry = eleCountryList.stream().filter(ele -> country.equals(ele.getText())).findFirst().orElse(null);
        if(eleCountry != null) {
            click(eleCountry);
        }
        waitSomeSecond(0.5);

        //Select Office
        logger.info("-- I select office: " + office);
        click(eleOffice);
        eleOffice.sendKeys(office);
//        waitSomeSecond(0.2);
        WebElement eleOffice = eleOfficeList.stream().filter(ele -> office.equals(ele.getText())).findFirst().orElse(null);
        if(eleOffice != null) {
            click(eleOffice);
        }
        waitSomeSecond(0.5);

        //Click on Next button
        logger.info("-- I click Next button");
        waitForElementToBeClickable(this.eleNextButton);
        click(eleNextButton);
        waitSomeSecond(0.5);

        //Input Entity Name
        logger.info("-- I input entity name: " + entity);
        waitForElementToBeClickable(this.eleEntityName);
        eleEntityName.sendKeys(entity);
        waitSomeSecond(0.5);
        eleEntityName.sendKeys(Keys.TAB);

        //Select Industry
        logger.info("-- I select industry: " + industry);
        WebElement eleIndustry = eleIndustryList.stream().filter(ele -> industry.equals(ele.getText())).findFirst().orElse(null);
        if(eleIndustry != null){
            click(eleIndustry);
        }

        //Click on Next button
        logger.info("-- I click Next button");
        waitForElementToBeClickable(this.eleNextButton);
        click(eleNextButton);
        waitSomeSecond(1);

        //Select financial reporting framework
        logger.info("-- I select financial reporting framework");
        WebElement eleFinancialReporting = eleReportFinancialList.stream().filter(ele -> financialReportingFramework.equals(ele.getText())).findFirst().orElse(null);
        if(eleFinancialReporting != null){
            click(eleFinancialReporting);
        }
        waitSomeSecond(1);

        //Select engagement standard
        logger.info("-- I select engagement standard");
        WebElement eleEngagementStandard = eleEngagementStandardList.stream().filter(ele -> engagementStandard.equals(ele.getText())).findFirst().orElse(null);
        if(eleEngagementStandard != null) {
            click(eleEngagementStandard);
        }

        //Click on Next button
        logger.info("-- I click on Next button");
        waitForElementToBeClickable(this.eleNextButton);
        click(eleNextButton);
        waitSomeSecond(1);

        //Select Role member
        logger.info("-- I select role member: " + role);
        waitForElementToBeClickable(this.eleSelectRole);
        click(eleSelectRole);
        waitSomeSecond(0.5);
        WebElement eleRole = eleRoleList.stream().filter(ele -> role.equals(ele.getText())).findFirst().orElse(null);
        if(eleRole != null) {
            click(eleRole);
        }

        logger.info("-- I click on Finish button");
        waitForElementToBeClickable(this.eleFinishButton);
        click(eleFinishButton);
        waitUtilElementHidden(eleCreationModal, 240);
        waitForVisibleElement(this.eleCreateEngButton, 60);
        waitForElementToBeClickable(this.eleCreateEngButton, 60);
    }

    /**
     * Check result after creating new an engagement
     * @param engagementName
     * @param entity
     * @return
     */
    public boolean checkResultAfterCreate(String engagementName, String entity){
        //Wait for new engagement is displayed on Portfolio
        if(engagementNamePrefix !=null) {
            engagementName += engagementNamePrefix;
            entity += engagementNamePrefix;
        }
        String finalEngagementName = engagementName;
        String finalEntity = entity;
        WebElement expectedElement = elePendingEngagementList.stream().filter(ele ->
            finalEngagementName.equals(ele.findElement(By.cssSelector(".engagement-clm")).getText()) &&
                    finalEntity.equals(ele.findElement(By.cssSelector(".entity-clm")).getText()) &&
                    ENGAGEMENT_STATUS_PENDING_CREATION.equals(ele.findElement(By.cssSelector(".status-clm")).getText())
        ).findFirst().orElse(null);
        waitForVisibleElement(expectedElement, 30);
        return true;
    }

    /**
     * Read independent
     */
    public void readIndependent() {
        scrollBottomOfElement(this.eleIndependentContent);
        waitForElementToBeClickable(this.eleIndependentConfirmBtn);
        this.eleIndependentConfirmBtn.click();
    }

    /**
     * Open Engagement settings
     */
    public void openEngagementSetting() {
        click(eleUserName);
        WebElement eleEngSetting = wedDriver.findElement(By.xpath("//div[contains(@class, 'menu')]/a[@class='item' and text()='Engagement settings']"));
        waitForElementToBeClickable(eleEngSetting, 5);
        click(eleEngSetting);
        waitForDimmerDisappeared();
    }

    /**
     * Open User profiles
     */
    public void openUserProfile() {
        click(eleUserName);
        WebElement eleEngSetting = wedDriver.findElement(By.xpath("//div[contains(@class, 'menu')]/a[@class='item' and text()='User profile']"));
        waitForElementToBeClickable(eleEngSetting, 5);
        click(eleEngSetting);
        waitForDimmerDisappeared();
    }

    /**
     * Open Portfolio page
     */
    public void openPortfolioPage() {
        click(eleLogo);
        try{
            wedDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            waitForVisibleElement(eleConfirmModal, 5);
            waitForElementToBeClickable(eleConfirmSaveBtn, 5);
            click(eleConfirmSaveBtn);
        }catch (TimeoutException | NoSuchElementException e) {

        }
        wedDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        waitForDimmerDisappeared();
    }

    public void confirmIndependence() {
        waitForVisibleElement(this.eleIndependentContent, 30);
        scrollBottomOfElement(this.eleIndependentContent);
        waitForElementToBeClickable(this.eleIndependentConfirmBtn);
        click(eleIndependentConfirmBtn);
        waitUtilElementHidden(eleIndependentContent, 30);
    }
}
