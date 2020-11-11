package com.sm.page;

import com.sm.models.RiskAssessmentRelateProcedureAccount;
import com.sm.models.RiskAssessmentRelateRommProcedure;
import com.sm.models.TailoringQuestion;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FSLPage extends BaseWorkingPaperPage {

    private Logger logger = Logger.getLogger(FSLPage.class.getName());
    private static FSLPage fslPage;
    public static final int INTERIM_FINAL = 1;
    public static final int INTERIM_ROLLFORWARD = 2;
    public static final int ONLY_FINAL = 3;

    @FindBy(css = ".risk-assessment-nav-step-item:nth-of-type(1)")
    private WebElement eleFslTailoringQuestionLeftNav;

    @FindBy(css = ".risk-assessment-nav-step-item:nth-of-type(2)")
    private WebElement eleFslROMMsLeftNav;

    @FindBy(css = ".risk-assessment-nav-step-item:nth-of-type(3)")
    private WebElement eleFslControlsLeftNav;

    @FindBy(css = ".risk-assessment-nav-step-item:nth-of-type(4)")
    private WebElement eleFslProceduresLeftNav;

    @FindBy(css = ".tq-content")
    private List<WebElement> eleFslLstTQ;

    @FindBy(css = ".risk-assessment-container .risks-multi-title-review-button")
    private WebElement eleFslAddCustomRommBtn;

    @FindBy(css = ".risk-assessment-container .control-review-button")
    private WebElement eleFslAddCustomControlBtn;

    @FindBy(css = ".risk-assessment-container .procedure-button")
    private WebElement eleFslManageProcedures;

    @FindBy(css = ".active.risk-assessment-nav-abcotd-procedure .item")
    private List<WebElement> eleFslProcedureRomms;

    @FindBy(css = ".procedures-left-container .list-pro .item")
    private List<WebElement> eleFslProcedureProcedure;

    @FindBy(css = ".risk-assessment-nav-step-item .title")
    private List<WebElement> getEleListSectionsFirstLv;

    @FindBy(css = ".risk-assessment-nav-step-item .risk-assessment-nav-abcotd-procedure-risk")
    private List<WebElement> eleListSections;

    @FindBy(css = ".testing-selection .custom-radio-button")
    private List<WebElement> eleListTiming;

    @FindBy(css = ".rollf-pro-description")
    private List<WebElement> eleListRollfordward;

    @FindBy(css = ".list-account-mapping-selected-parent >div:not(.abcotd-no-selected-procedures)")
    private List<WebElement> eleListABCOTDsInAccountMapping;

    @FindBy(css = "table.table-contentarea-accounts .account-row-header .account-checkbox")
    private WebElement eleCheckAllAccount;

    @FindBy(css = "table.table-contentarea-accounts .account-row")
    private List<WebElement> eleAccountRows;

    @FindBy(css = ".btn-generate-wp")
    private WebElement eleGenerateWpBtn;

    @FindBy(css = "th .risk-check-box")
    private List<WebElement> eleCbSelectAllProcedureToGenerate;

    @FindBy(css = ".generate-wp-success")
    private WebElement eleGenerateSuccess;

    @FindBy(css = ".control-table table tbody tr")
    private List<WebElement> eleListFSLControls;

    @FindBy(css = ".risk-management-grid tr.risk-row-container")
    private List<WebElement> eleListFSLControlSelected;

    private List<RATailoringQuestion> lstTailoringQuestion;

    public FSLPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoad() {
        waitForVisibleElement(eleFslTailoringQuestionLeftNav, 120);
        return this.eleFslTailoringQuestionLeftNav.isDisplayed();
    }

    public static FSLPage getInstance(WebDriver webDriver) {
        if(fslPage == null) return new FSLPage(webDriver);
        return fslPage;
    }

    /**
     * Select romm, procedures, timing to generate
     * @param datas
     */
    public void selectFslProcedureWP(List<RiskAssessmentRelateRommProcedure> datas){
        for (RiskAssessmentRelateRommProcedure d: datas) {
            WebElement romm = this.eleFslProcedureRomms.stream().filter(e -> d.getRoom().equals(e.getText())).findFirst().orElse(null);
            if(romm == null) {
                continue;
            }
            logger.info("-- I select ROMM: " + d.getRoom());
            click(romm);
            waitSomeSecond(0.5);
            PageFactory.initElements(wedDriver, this.eleFslProcedureProcedure);
            WebElement procedure = this.eleFslProcedureProcedure.stream().filter(e -> e.findElement(By.cssSelector(".content")).getText().contains(d.getProcedure())).findFirst().orElse(null);
            if(procedure == null) {
                continue;
            }
            WebElement checkbox = procedure.findElement(By.cssSelector("i.square"));
            if(checkbox !=null && !checkbox.getAttribute("class").contains("check")){
                logger.info("-- I select procedure: " + d.getProcedure());
                click(checkbox);
            }
            if(eleListTiming.get(d.getTiming() - 1) != null && eleListTiming.get(d.getTiming() - 1).isEnabled())
                click(eleListTiming.get(d.getTiming() - 1));
            if(d.getTiming() == INTERIM_ROLLFORWARD && d.getRollforward() != null) {
                waitSomeSecond(1);
                PageFactory.initElements(wedDriver, this.eleListRollfordward);
                WebElement rollforward = this.eleListRollfordward.stream().filter(e -> e.findElement(By.cssSelector(".content")).getText().contains(d.getRollforward())).findFirst().orElse(null);
                if(rollforward == null) continue;
                WebElement rfwCb = rollforward.findElement(By.cssSelector(".checkbox"));
                if(rfwCb != null && rfwCb.isEnabled()) {
                    click(rfwCb);
                }
            }
            waitSomeSecond(1);
        }
    }

    /**
     * select list controls
     * @param controls
     */
    public void selectFslControls(List<String> controls) {
        for(String control : controls) {
            WebElement eleTr = this.eleListFSLControls.stream().filter(e -> e.findElement(By.cssSelector("td.col-control .title")).getText().contains(control)).findFirst().orElse(null);
            if(eleTr == null) continue;
            WebElement cb = eleTr.findElement(By.cssSelector("td.col-checkbox i"));
            if(cb == null || cb.getAttribute("class").contains("fa-check-square") || !cb.isEnabled()) continue;
            logger.info("-- I select FSL Control: " + control);
            click(cb);
        }
    }

    /**
     * Answering TQ
     * @param tqs
     * @return
     */
    public boolean answerFslTQ(List<TailoringQuestion> tqs){
        if(this.eleFslTailoringQuestionLeftNav.isEnabled()) this.eleFslTailoringQuestionLeftNav.click();
        for (TailoringQuestion tq : tqs){
            RATailoringQuestion raTQ = new RATailoringQuestion(tq.getId());
            if(raTQ == null){
                continue;
            }
            logger.info(String.format("-- I answer '%s' to Tailoring Question '%s'", tq.getAnswered(), raTQ.getTqHeading()));
            raTQ.answerByHeading(tq.getAnswered());
            waitSomeSecond(0.3);
        }
        return this.eleFslROMMsLeftNav.isEnabled() && this.eleFslControlsLeftNav.isEnabled() && this.eleFslProceduresLeftNav.isEnabled();
    }

    /**
     * Select a section on Risk Assessment by name
     * @param sectionName
     */
    public void openFslSection(String sectionName) {
        WebElement eleFirstLv = this.getEleListSectionsFirstLv.stream().filter(e -> sectionName.equals(e.getText())).findFirst().orElse(null);
        WebElement ele = this.eleListSections.stream().filter(e -> sectionName.equals(e.getText())).findFirst().orElse(null);
        if(ele != null && ele.isEnabled()) {
            click(ele);
        }
        if(eleFirstLv != null && eleFirstLv.isEnabled()){
            click(eleFirstLv);
        }
    }

    /**
     * Generate control testing working paper
     * @param controls
     */
    public void generateFslControlTesting(List<String> controls) {
        for (String control : controls) {
            WebElement ele = this.eleListFSLControlSelected.stream().filter(e -> {
                boolean rs = false;
                WebElement eleName = e.findElement(By.cssSelector("td:nth-child(3)"));
                if(eleName != null && eleName.getText().equals(control)) {
                    rs = true;
                }
                return rs;
            }).findFirst().orElse(null);

            if(ele == null) continue;
            WebElement cb = ele.findElement(By.cssSelector("td:nth-child(2) i"));
            if(cb == null || !cb.isEnabled()) continue;
            logger.info("-- I click on control: " + control);
            click(cb);
        }
        waitForElementToBeClickable(eleGenerateWpBtn, 5);
        logger.info("-- I click on Generate Working paper button");
        click(eleGenerateWpBtn);
        waitForVisibleElement(eleGenerateSuccess, 60);
    }


    /**
     * Generate wp
     */
    public  void generateFslWorkingPaper() {
        eleCbSelectAllProcedureToGenerate.forEach(e -> {
            if(e.isEnabled()) click(e);
        });
        waitForElementToBeClickable(eleGenerateWpBtn, 5);
        logger.info("-- I click on Generate Working paper button");
        click(eleGenerateWpBtn);
        waitUtilTextPresent(eleGenerateSuccess, 120, "Working paper generated");
    }

    class RATailoringQuestion{

        private WebElement ele;
        private String tqID;
        private String tqHeading;
        private List<String> tqAnswering;

        public RATailoringQuestion(WebElement ele) {
            this.ele = ele;
        }

        public RATailoringQuestion(String tdID){
            ele = eleFslLstTQ.stream().filter(e -> tdID.equals(e.findElement(By.cssSelector(".label-tpid")).getText())).findFirst().orElse(null);
        }

        public String getTqID(){
            tqID = ele.findElement(By.cssSelector(".label-tpid")).getText();
            return tqID;
        }
        public String getTqHeading(){
            tqHeading = ele.findElement(By.cssSelector(".qna-field-description p")).getText();
            return tqHeading;
        }

        private List<WebElement> getLstEleAnswering(){
            return ele.findElements(By.cssSelector(".qna-field .field"));
        }

        public List<String> getTqAnswering() {
            List<WebElement> eleAnswerings = this.getLstEleAnswering();
            return eleAnswerings.stream().map(ele -> ele.getText()).collect(Collectors.toList());
        }

        public boolean answerByHeading(String answering){
            WebElement ele = getLstEleAnswering().stream().filter(e -> answering.equals(e.getText())).findFirst().orElse(null);
            if(ele == null)
                return false;
            click(ele);
            WebElement child = ele.findElement(By.cssSelector(".checkbox"));
            return answering.equals(child.getAttribute("source-text")) && child.getAttribute("class").contains("checked");
        }

        public boolean answerByIndex(int index) {
            WebElement ele = getLstEleAnswering().get(index);
            if(ele == null)
                return false;
            click(ele);
            WebElement child = ele.findElement(By.cssSelector(".checkbox"));
            return child.getAttribute("class").contains("checked");
        }
    }
}
