package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.models.TailoringQuestion;
import com.sm.page.components.CTailoringQuestion;
import com.sm.page.gct.*;
import com.sm.page.gct.GeneralCommonTool;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseWorkingPaperPage extends PageInit {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FindBy(css=".cwp-group-title p")
    protected WebElement eleWPNameHeader;

    @FindBy(css = ".left-planning-nav-scrolling .header")
    protected WebElement eleWPNameOfNav;

    @FindBy(xpath="//div[contains(@class, 'group-edit')]/button[text()='Edit']")
    protected WebElement eleEditBtn;

    @FindBy(css=".group-edit .link-button")
    protected WebElement eleSaveBtn;

    @FindBy(css = ".group-edit .done-editing")
    protected WebElement eleDoneEditing;

    @FindBy(css="#sign-off-content .box-prepare")
    protected WebElement eleBoxPrepare;

    @FindBy(css="#sign-off-content .box-review")
    protected WebElement eleBoxReviewer;

    @FindBy(xpath = "//div[contains(@class, 'group-btn')]/div/button[text()='Exit']")
    protected WebElement eleExitBtn;

    @FindBy(css = ".content-wp .menu .header")
    public WebElement getEleWPName;

    @FindBy(css = ".left-planning-nav-scrolling .item .title")
    private List<WebElement> eleLeftSections;

    @FindBy(css = ".qna-field.question")
    private List<WebElement> eleLstTQ;

    @FindBy(css = ".box-signoff")
    private WebElement eleSignOffBox;

    @FindBy(css = ".signoff-dropdown")
    private WebElement eleSignOffDropdown;

    protected GCTRisk gctRisk = GCTRisk.getInstance(wedDriver);
    protected GCTControl gctControl =  GCTControl.getInstance(wedDriver);

    public BaseWorkingPaperPage(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isLoad();

    public void verifyWPisLoad() {
        waitForVisibleElement(eleWPNameOfNav, 10);
    }

    /**
     * Save data on a working paper
     */
    public void save() {
        try{
            waitForElementToBeClickable(eleSaveBtn, 10);
            click(eleSaveBtn);
            waitForDimmerDisappeared();
            waitForElementToBeClickable(this.eleSaveBtn, 5);
            waitForElementToBeClickable(this.eleExitBtn, 5);
        }catch (TimeoutException | NoSuchElementException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
        }
    }

    /**
     * Enable editing
     */
    public void enableEditing() {
        try{
            waitForElementToBeClickable(eleEditBtn, 10);
            logger.info("-- I click on Edit button");
            click(eleEditBtn);
            //Check button save and done editing will be displayed
            this.waitForElementToBeClickable(this.eleSaveBtn, 60);
            this.waitForElementToBeClickable(this.eleDoneEditing, 60);
            logger.info("-- Button Don't Editing displays");
        }catch (Exception e) {

        }
//        try{
//            wedDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            click(eleEditBtn);
//            wedDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//            //Check button save and done editing will be displayed
//            this.waitForElementToBeClickable(this.eleSaveBtn, 60);
//            this.waitForElementToBeClickable(this.eleDoneEditing, 60);
//        }catch (NoSuchElementException | TimeoutException e){
//            logger.info("Working paper is already editing.");
//        }catch (NoSuchWindowException e) {
//            waitForElementToBeClickable(eleEditBtn, 5);
//            click(eleEditBtn);
//            //Check button save and done editing will be displayed
//            this.waitForElementToBeClickable(this.eleSaveBtn, 60);
//            this.waitForElementToBeClickable(this.eleDoneEditing, 60);
//        }
    }

    public void doneEditing() {
        this.eleDoneEditing.click();
        //TODO
    }

    public void confirmCancel() {
        this.eleConfirmCancelBtn.click();
        //TODO
    }

    public void confirmSave(){
        this.eleConfirmSaveBtn.click();
        //TODO
    }

    public void confirmDontSave() {
        this.eleConfirmDontSaveBtn.click();
        //TODO
    }

    public void prepare() {
        click(eleBoxPrepare);
    }

    public void reviwer() {
        this.eleBoxReviewer.click();
        //TODO
    }

    public void verifyExistingPrepareSignOff() {
        click(eleSignOffBox);
        waitForVisibleElement(eleSignOffDropdown, 5);

    }

    public void closeWP(boolean save){
        try {
            click(eleExitBtn);
            waitSomeSecond(2);
            waitForElementToBeClickable(eleConfirmDontSaveBtn, 2);
            if(save) this.eleConfirmSaveBtn.click();
            this.eleConfirmDontSaveBtn.click();
        }catch (NoSuchElementException | NoSuchWindowException | TimeoutException e){
//            logger.warn(e.getMessage());
        }
        waitSomeSecond(3);
        //Switch to main tab
        this.switchTab(0);
    }

    public void checkWPName(String wpName){
        waitUtilTextPresent(getEleWPName, 120, wpName);
    }
    public String wpNameOnHeader() {
        return this.eleWPNameHeader.getText();
    }

    /**
     * Find section element by section name
     * @param sectionName
     * @return
     */
    private WebElement findElementSectionByName(String sectionName) {
        WebElement ele = this.eleLeftSections.stream().filter(e -> {
            if(e.getText().equals(sectionName)) return true;
            return false;
        }).findFirst().orElse(null);
        return ele;
    }

    /**
     * Open a select
     * @param sectionName
     */
    public void openSectionByName(String sectionName) {
        WebElement ele = findElementSectionByName(sectionName);
        if(ele == null || !ele.isEnabled()) return;
        click(ele);
    }

    /**
     * Check a lock section
     * @param sectionName
     * @return
     */
    public boolean checkLockSection(String sectionName) {
        WebElement ele = findElementSectionByName(sectionName);
        if (ele == null) return false;
        if (ele.getAttribute("class").contains("disabled")) return true;
        return false;
    }

    /**
     * Click on sub section
     * @param parentSection
     * @param subSection
     */
    public void openSubSection(String parentSection, String subSection) {
        WebElement eleParent = findElementSectionByName(parentSection);
        if(eleParent == null) return;
        if(!eleParent.getAttribute("class").contains("active")) {
            click(eleParent);
        }
        String parentId = eleParent.getAttribute("data-id");
        WebElement parent = eleParent.findElement(By.xpath(".."));
        WebElement eleSubSection = parent.findElements(By.cssSelector(".sub-section-active .item"))
                .stream().filter(e -> e.getAttribute("parentod").equals(parentId) && e.getText().equals(subSection)).findAny().orElse(null);
        if( eleSubSection == null) return;
        if(!eleSubSection.getAttribute("class").contains("active")) {
            click(eleSubSection);
        }
    }

    /**
     * Find Tailoring question by heading
     * @param heading
     */
    private WebElement findTailoringQuestion(String heading) {
        return eleLstTQ.stream().filter(e -> e.findElement(By.cssSelector(".field-description-heading")).getText().toUpperCase().contains(heading.toUpperCase())).findFirst().orElse(null);
    }

    /**
     * Answer a TQ
     * @param answer
     * @param heading
     */
    public void answerTailoringQuestion(String answer, String heading) {
        WebElement ele = findTailoringQuestion(heading);
        Assert.assertNotNull("The TQ '" + heading + "' doesn't exist", ele);
        CTailoringQuestion cTailoringQuestion = new CTailoringQuestion(ele, wedDriver);
        if(cTailoringQuestion.isDisPlay()) {
            cTailoringQuestion.answerTQ(answer);
        }
    }

    /**
     * Answer a TQ
     * @param index
     * @param heading
     */
    public void answerTailoringQuestion(int index, String heading) {
        WebElement ele = findTailoringQuestion(heading);
        if(ele == null) {
            System.out.println("The TQ doesnt existing");
            return;
        }
        CTailoringQuestion cTailoringQuestion = new CTailoringQuestion(ele, wedDriver);
        if(cTailoringQuestion.isDisPlay()) {
            cTailoringQuestion.answerTQ(index);
        }
    }

    public void verifyAttachmentFileIsDownload(String fileName) {
        boolean rs = false;
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "download";
        File downloadFolder = new File(downloadFilepath);
        File[] files = downloadFolder.listFiles();
        for(File f : files) {
            if(f.getName().contains(fileName)) {
                rs = true;
                break;
            }
        }
        Assert.assertTrue("File '" + fileName + "' is not downloaded", rs);
    }

    /**
     * Verify TQ displayed
     * @param heading
     */
    public void verifyTailoringQuestionDisplayed(String heading) {
        WebElement ele = findTailoringQuestion(heading);
        CAssertion.assertTrue(ele != null && ele.isDisplayed());
    }

    public void closeGCTModal() {
        GeneralCommonTool gct = new GeneralCommonTool(wedDriver);
        gct.closeGCT();
    }

    /**
     * Verify activated section
     * @param section
     */
    public void verifySectionActivated(String section) {
        WebElement ele = findElementSectionByName(section);
        Assert.assertNotNull("The section '" + section + "' doesn't exist", ele);
        String attrs = attributeElement(ele, "class");
        Assert.assertTrue("The section '" + section + "' doesn't active", !attrs.contains("disabled"));
    }

    /**
     * Verify deactived section
     * @param section
     */
    public void verifySectionDeactivated(String section) {
        WebElement ele = findElementSectionByName(section);
        Assert.assertNotNull("The section '" + section + "' doesn't exist", ele);
        String attrs = attributeElement(ele, "class");
        Assert.assertTrue("The section '" + section + "' active", attrs.contains("disabled"));
    }

    public void verifyAnswerOnTQIsSelectByWP(String answer, String heading, String wp) {
        WebElement ele = findTailoringQuestion(heading);
        Assert.assertNotNull("The TQ '" + heading + "' doesn't exist", ele);
        CTailoringQuestion tq = new CTailoringQuestion(ele, wedDriver);

        String answerIsSelected = tq.getTextIsSelectedAnswer();
        Assert.assertEquals(answer, answerIsSelected);
        Assert.assertTrue("The answer should be disabled.", tq.isDisabledSelected());

        String externalLink = tq.getExternalLink();
        Assert.assertEquals(wp, externalLink);
    }
}
