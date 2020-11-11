package com.sm.page;

import com.sm.assertion.CAssertion;
import com.sm.models.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RiskAssessmentPage extends BaseWorkingPaperPage {

    private static RiskAssessmentPage riskAssessment;
    private Logger logger = Logger.getLogger(RiskAssessmentPage.class.getName());

    public static final int INTERIM_FINAL = 1;
    public static final int INTERIM_ROLLFORWARD = 2;
    public static final int ONLY_FINAL = 3;

    @FindBy(css = ".risk-assessment-nav-abcotd-item .risk-assessment-nav-step-item:nth-of-type(2)")
    private WebElement eleTailoringQuestionLeftNav;

    @FindBy(css = ".risk-assessment-nav-abcotd-item .risk-assessment-nav-step-item:nth-of-type(3)")
    private WebElement eleROMMsLeftNav;

    @FindBy(css = ".risk-assessment-nav-abcotd-item .risk-assessment-nav-step-item:nth-of-type(4)")
    private WebElement eleControlsLeftNav;

    @FindBy(css = ".risk-assessment-nav-abcotd-item .risk-assessment-nav-step-item:nth-of-type(5)")
    private WebElement eleProceduresLeftNav;

    @FindBys(@FindBy(css = ".risk-assessment-container .tq-content"))
    private List<WebElement> eleLstTQ;

    @FindBy(css = ".risk-assessment-container .risks-multi-title-review-button")
    private WebElement eleAddCustomRommBtn;

    @FindBy(css = ".risk-assessment-container .control-review-button")
    private WebElement eleAddCustomControlBtn;

    @FindBy(css = ".risk-assessment-container .procedure-button")
    private WebElement eleManageProcedures;

    @FindBy(css = ".active.risk-assessment-nav-abcotd-procedure .item")
    private List<WebElement> eleProcedureRomms;

    @FindBy(css = ".procedures-left-container .list-pro .item")
    private List<WebElement> eleProcedureProcedure;

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

    @FindBy(css = ".control-table-detail tbody tr")
    private List<WebElement> eleListControls;

    @FindBy(css = ".risk-management-grid tr.risk-row-container")
    private List<WebElement> eleListControlSelected;

    @FindBy(css = ".risk-multi-abcotd .risk-multi-abcotd-accordion")
    private List<WebElement> eleABCOTDsTable;

    @FindBy(css = ".review-note-modal")
    protected WebElement gctModal;

    @FindBy(css = ".manage-custom-procedures")
    private WebElement eleManageProcedureModal;

    private List<RATailoringQuestion> lstTailoringQuestion;
    private ManageProcedure manageProcedure;

    public RiskAssessmentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoad() {
        waitForVisibleElement(eleTailoringQuestionLeftNav, 120);
        return this.eleTailoringQuestionLeftNav.isDisplayed();
    }

    public static RiskAssessmentPage getInstance(WebDriver webDriver) {
        if(riskAssessment == null) return new RiskAssessmentPage(webDriver);
        return riskAssessment;
    }

    /**
     * Select romm, procedures, timing to generate
     * @param datas
     */
    public void selectProcedureWP(List<RiskAssessmentRelateRommProcedure> datas){
        for (RiskAssessmentRelateRommProcedure d: datas) {
            try {
                WebElement romm = this.eleProcedureRomms.stream().filter(e -> d.getRoom().equals(e.getText())).findFirst().orElse(null);
                if(romm == null) {
                    continue;
                }
                click(romm);
                Thread.sleep(500);
                PageFactory.initElements(wedDriver, this.eleProcedureProcedure);
                WebElement procedure = this.eleProcedureProcedure.stream().filter(e -> e.findElement(By.cssSelector(".content")).getText().contains(d.getProcedure())).findFirst().orElse(null);
                if(procedure == null) {
                    continue;
                }
                WebElement checkbox = procedure.findElement(By.cssSelector("i.square"));
                if(checkbox !=null && !checkbox.getAttribute("class").contains("check")){
                    click(checkbox);
                }
                if(eleListTiming.get(d.getTiming() - 1) != null && eleListTiming.get(d.getTiming() - 1).isEnabled())
                    click(eleListTiming.get(d.getTiming() - 1));
                if(d.getTiming() == INTERIM_ROLLFORWARD && d.getRollforward() != null) {
                    Thread.sleep(1000);
                    PageFactory.initElements(wedDriver, this.eleListRollfordward);
                    WebElement rollforward = this.eleListRollfordward.stream().filter(e -> e.findElement(By.cssSelector(".content")).getText().contains(d.getRollforward())).findFirst().orElse(null);
                    if(rollforward == null) continue;
                    WebElement rfwCb = rollforward.findElement(By.cssSelector(".checkbox"));
                    if(rfwCb != null && rfwCb.isEnabled()) {
                        click(rfwCb);
                    }
                }
                Thread.sleep(500);
            }catch (Exception e) {

            }
        }
    }

    /**
     * select list controls
     * @param controls
     */
    public void selectControls(List<String> controls) {
        for(String control : controls) {
            WebElement eleTr = this.eleListControls.stream().filter(e -> e.findElement(By.cssSelector("td.col-control .title")).getText().contains(control)).findFirst().orElse(null);
            if(eleTr == null) continue;
            WebElement cb = eleTr.findElement(By.cssSelector("td.col-checkbox i"));
            if(cb == null || attributeElement(cb, "class").contains("fa-check-square") || !cb.isEnabled()) continue;
            logger.info("-- I select Control: " + control);
            click(cb);
        }
    }

    /**
     * Answering TQ
     * @param tqs
     * @return
     */
    public boolean answerTQ(List<TailoringQuestion> tqs){
        if(this.eleTailoringQuestionLeftNav.isEnabled()) this.eleTailoringQuestionLeftNav.click();
        for (TailoringQuestion tq : tqs){
            RATailoringQuestion raTQ = new RATailoringQuestion(tq.getId());
            if(raTQ == null){
                continue;
            }
            logger.info(String.format("-- I answer '%s' on Tailoring Question '%s", tq.getId(), raTQ.getTqHeading()));
            raTQ.answerByHeading(tq.getAnswered());
            waitSomeSecond(0.3);
        }
        return this.eleROMMsLeftNav.isEnabled() && this.eleControlsLeftNav.isEnabled() && this.eleProceduresLeftNav.isEnabled();
    }

    /**
     * Select a section on Risk Assessment by name
     * @param sectionName
     */
    public void openSection(String sectionName) {
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
     * Select account mapping
     * @param datas
     */
    public void selectAccountMapping(List<RiskAssessmentRelateProcedureAccount> datas) {
        for (RiskAssessmentRelateProcedureAccount r : datas) {
            String abcotds = r.getAbcotd();
            WebElement groupAbcotds = this.eleListABCOTDsInAccountMapping.stream().filter(
                    e -> {
                        WebElement procedureName = e.findElement(By.cssSelector("p.account-mapping-header"));
                        if(procedureName == null) return false;
                        return procedureName.getText().contains(abcotds);
                    }).findFirst().orElse(null);
            if(groupAbcotds == null) {
                System.out.println("Can not find abcotd: " + abcotds);
                continue;
            }
            String procedure = r.getProcedure();
            List<WebElement> eleProcedures = groupAbcotds.findElements(By.cssSelector(".procedure-account-mapping-item"));
            WebElement eleProcedure = eleProcedures.stream().filter(
                    e -> procedure.equals(e.findElement(By.cssSelector(".procedure-account-mapping-item-code")).getText())).findFirst().orElse(null);
            if(eleProcedure == null) {
                System.out.println("Can not file procedure: " + procedure);
                continue;
            }
            if(!eleProcedure.getAttribute("class").contains("item-selected")) {
                click(eleProcedure);
                PageFactory.initElements(wedDriver, this.eleAccountRows);
            }
            String account = r.getAccount();
            if("All".equalsIgnoreCase(account)) {
                //Select all account
                click(this.eleCheckAllAccount);
            }else{
                WebElement eleAccount = eleAccountRows.stream().filter(e -> e.findElements(By.cssSelector("td.account-cell")).get(1).getText().equals(account)).findFirst().orElse(null);
                if(eleAccount == null) {
                    System.out.println("Can not find account: " + account);
                    continue;
                }
                WebElement cb = eleAccount.findElement(By.cssSelector(".account-checkbox"));
                if(cb != null) click(cb);
            }
        }
    }

    /**
     * Generate control testing working paper
     * @param controls
     */
    public void generateControlTesting(List<String> controls) {
        for (String control : controls) {
            WebElement ele = this.eleListControlSelected.stream().filter(e -> {
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
            logger.info("-- I select control " + control + " to generate Control Testing");
            click(cb);
        }

        waitForElementToBeClickable(eleGenerateWpBtn, 30);
        logger.info("-- I click on Generate Working Paper button");
        click(eleGenerateWpBtn);
        waitForDimmerDisappeared();
        waitForVisibleElement(eleGenerateSuccess, 60);
    }

    /**
     * Generate wp
     */
    public  void generateWorkingPaper() {
        eleCbSelectAllProcedureToGenerate.forEach(e -> {
            if(e.isEnabled()) click(e);
        });
        waitForElementToBeClickable(eleGenerateWpBtn, 30);
        logger.info("-- I click on Generate Working Paper button");
        click(eleGenerateWpBtn);
        waitUtilTextPresent(eleGenerateSuccess, 120, "Working paper generated");
    }

    /**
     * Create custom ROMM
     * @param r
     */
    public void createCustomROMM(Romm r) {
        click(eleAddCustomRommBtn);
        waitForVisibleElement(gctModal, 30);
        if(gctRisk.isRisk()) {
            gctRisk.create(r);
            gctRisk.waitUtilGCTClosed();
            waitForDimmerDisappeared();
        }
    }

    /**
     * Find ABCOTDs table by name
     * @param name
     * @return
     */
    private WebElement findABCOTDsTableByName(String name) {
        WebElement ele = eleABCOTDsTable.stream().filter(
                e->name.equalsIgnoreCase(e.findElement(By.cssSelector(".risk-multi-abcotd-header-text")).getText().replace("ABCOTD: ","").trim())
        ).findFirst().orElse(null);
        return ele;
    }

    /**
     * Select ROMM tab on Risk Assessment
     * @param abcotd
     * @return
     */
    private WebElement selectRommTab(String abcotd) {
        WebElement eleTableABCOTDs = findABCOTDsTableByName(abcotd);
        Assert.assertNotNull("This ABCOTDs don't have", eleTableABCOTDs);

        if(eleTableABCOTDs != null) {
            List<WebElement> eleRommTabMenu = eleTableABCOTDs.findElements(By.cssSelector(".risk-multi-abcotd-tab-div a.item"));
            click(eleRommTabMenu.get(0));

            WebElement tableRomms = eleTableABCOTDs.findElement(By.cssSelector("table.romms-tab"));
            waitForVisibleElement(tableRomms, 5);
            return tableRomms;
        }
        return null;
    }

    /**
     * Select Open Flags tab
     * @param abcotd
     * @return
     */
    private WebElement selectOpenRiskFlagTab(String abcotd) {
        WebElement eleTableABCOTDs = findABCOTDsTableByName(abcotd);
        Assert.assertNotNull("This ABCOTDs don't have", eleTableABCOTDs);

        if(eleTableABCOTDs != null) {
            List<WebElement> eleRommTabMenu = eleTableABCOTDs.findElements(By.cssSelector(".risk-multi-abcotd-tab-div a.item"));
            click(eleRommTabMenu.get(1));

            WebElement tableRomms = eleTableABCOTDs.findElement(By.cssSelector("table.risk-flags-tab"));
            waitForVisibleElement(tableRomms, 5);
            return tableRomms;
        }
        return null;
    }

    /**
     * Check list ROMMs
     * @param abcotd
     * @param romms
     */
    public void checkListROMMs(String abcotd, List<Romm> romms) {
        WebElement table = selectRommTab(abcotd);
        Assert.assertNotNull("Can not find table for ABCOTD:  " + abcotd, table);

        if(table != null) {
            List<WebElement> eleDataRows = table.findElements(By.cssSelector("tbody tr"));
            if(romms.size() == 0) {
                Assert.assertTrue("ROMM table should be empty.", eleDataRows.size() == 0);
            }else{
                for (Romm r : romms) {
                    String id = r.getId();
                    WebElement row = eleDataRows.stream().filter(
                            e->id.equalsIgnoreCase(e.findElement(By.cssSelector("td.risk-cell:nth-child(2)")).getText())
                    ).findFirst().orElse(null);
                    Assert.assertNotNull("Can not find ROMM via id " + id, row);
                    if(row != null) {
                        //Compare title
                        WebElement title = row.findElement(By.cssSelector("td.risk-cell:nth-child(3) span > span"));
                        Assert.assertEquals(r.getTitle(), title.getText());

                        //Compare assertions
                        WebElement assertions = row.findElement(By.cssSelector("td.risk-cell:nth-child(4)"));
                        List<String> expectedAssertions = r.getAssertion().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                        List<String> actualAssertions = Arrays.asList(assertions.getText().split("\n")).stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());;
                        Assert.assertEquals(expectedAssertions, actualAssertions);

                        //Compare classification
                        WebElement riskClassification = row.findElement(By.cssSelector("td.risk-cell:nth-child(5) img"));
                        String attrs = attributeElement(riskClassification, "src");
                        switch (r.getClassification()) {
                            case Romm.CLASSIFICATION_LOWER:
                                Assert.assertTrue(attrs.contains("lower"));
                                break;
                            case Romm.CLASSIFICATION_HIGHER:
                                Assert.assertTrue(attrs.contains("higher"));
                                break;
                            case Romm.CLASSIFICATION_SIGNIFICANT:
                                Assert.assertTrue(attrs.contains("significant"));
                                break;
                            case Romm.CLASSIFICATION_REMOTE:
                                Assert.assertTrue(attrs.contains("remote"));
                                break;
                            default:
                                Assert.fail("The classification " + r.getClassification() + " is invalid");
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Create custom Control
     * @param control
     */
    public void createCustomControl(Control control) {
        click(eleAddCustomControlBtn);
        waitForVisibleElement(gctModal, 30);
        if(gctControl.isControl()) {
            gctControl.create(control);
        }
    }

    /**
     * Check list Control on Risk Assessment
     * @param controls
     */
    public void checkListControl(List<Control> controls) {
        controls.forEach(c -> {
            String id = c.getControlId();
            String title = c.getTitle();
            String description = c.getDescription();
            List<Romm> romms = c.getAssociateROMMs();
            String nature = c.getNature();
            String frequency = c.getFrequency();
            String approach = c.getApproach();
            String type = c.getType();
            String designConclusion = c.getDesignConclusion();
            String implementationConclusion = c.getImplementationConclusion();
            String oeTestingStrategy = c.getOeTestingStrategy();
            String oeDateLastTested = c.getOeDateLastTested();
            String oeConclusion = c.getOeConclusion();

            //Compare title and ID
            WebElement eleRow = eleListControls.stream().filter(e -> {
                try{
                    WebElement ele = e.findElement(By.cssSelector("td.col-control .title"));
                    if(ele.getText().contains(id) && ele.getText().contains(title)){
                        return true;
                    }
                }catch (Exception ex) {
                    return false;
                }
                return false;
            }).findFirst().orElse(null);
            Assert.assertNotNull("ID and Title don't match", eleRow);

            if(eleRow != null) {
                scrollToElement(eleRow);

                StringBuffer sb = new StringBuffer();
                //Check cb relevant
                WebElement cb = eleRow.findElement(By.cssSelector("td.col-checkbox i"));
                String attrClass = attributeElement(cb, "class");
                if(c.isRelevant() && !attrClass.contains("fa-check-square")) {
                    sb.append("The control relevant should be checked\n");
                }else if(!c.isRelevant() && !attrClass.contains("fa-square")){
                    sb.append("The control relevant should be unchecked\n");
                }

                //Compare description
                WebElement eleDescription = eleRow.findElement(By.cssSelector("td.col-control .description"));
                if(!description.equals(eleDescription.getText())) {
                    sb.append(String.format("The control description doesn't match. Expected is '%s'. Actual result is '%s'\n", description, eleDescription.getText()));
                }

                //Compare nature
                WebElement eleNature = eleRow.findElement(By.cssSelector("td.col-nature"));
                if (!nature.equals(eleNature.getText())) {
                    sb.append(String.format("The control nature doesn't match. Expected is '%s'. Actual result is '%s'\n", nature, eleNature.getText()));
                }

                //Compare frequency
                WebElement eleFrequency = eleRow.findElement(By.cssSelector("td.col-frequency"));
                if(!frequency.equals(eleFrequency.getText())){
                    sb.append(String.format("The control frequency doesn't match. Expected is '%s'. Actual result is '%s'\n", frequency, eleFrequency.getText()));
                }

                //Compare approach
                WebElement eleApproach = eleRow.findElement(By.cssSelector("td.col-approach"));
                if(!approach.equals(eleApproach.getText())) {
                    sb.append(String.format("The control approach doesn't match. Expected is '%s'. Actual result is '%s'\n", approach, eleApproach.getText()));
                }

                //Compare type
                WebElement eleType = eleRow.findElement(By.cssSelector("td.col-type"));
                if(!type.equals(eleType.getText())) {
                    sb.append(String.format("The control type doesn't match. Expected is '%s'. Actual result is '%s'\n", type, eleType.getText()));
                }

                //Compare design conclusion
                WebElement eleDesignConclusion = eleRow.findElement(By.cssSelector("td.col-design-conclusion"));
                if(!designConclusion.equals(eleDesignConclusion.getText())) {
                    sb.append(String.format("The control design conclusion doesn't match. Expected is '%s'. Actual result is '%s'\n", designConclusion, eleDesignConclusion.getText()));
                }

                WebElement eleImplementationConclusion = eleRow.findElement(By.cssSelector("td.col-implementation-conclusion"));
                if(!implementationConclusion.equals(eleImplementationConclusion.getText())){
                    sb.append(String.format("The control implementation conclusion doesn't match. Expected is '%s'. Actual result is '%s'\n", implementationConclusion, eleImplementationConclusion.getText()));
                }

                WebElement eleOETestingStrategy = eleRow.findElement(By.cssSelector("td.col-oe-testing-strategy .oe-testing-strategy"));
                if(!oeTestingStrategy.equals(eleOETestingStrategy.getText())) {
                    sb.append(String.format("The control oe testing strategy doesn't match. Expected is '%s'. Actual result is '%s'\n", oeTestingStrategy, eleOETestingStrategy.getText()));
                }

                WebElement eleOELastTested = eleRow.findElement(By.cssSelector("td.col-oe-testing-strategy .oe-last-tested"));
                if(!oeDateLastTested.equals(eleOELastTested.getText().replace("Last tested: ", "").trim())) {
                    sb.append(String.format("The control oe date last tested doesn't match. Expected is '%s'. Actual result is '%s'\n", oeDateLastTested, eleOELastTested.getText()));
                }

                WebElement eleOEConclusion = eleRow.findElement(By.cssSelector("td.col-oe-conclusion"));
                if(!oeConclusion.equals(eleOEConclusion.getText())) {
                    sb.append(String.format("The control oe conclusion doesn't match. Expected is '%s'. Actual result is '%s'\n", oeConclusion, eleOEConclusion.getText()));
                }

                if(sb.length() > 0) {
                    Assert.fail(sb.toString());
                }
            }
        });
    }

    /**
     * Create custom procedure
     * @param title
     * @param description
     * @param task
     */
    public void createCustomProcedure(String title, String description, String task) {
        click(eleManageProcedures);
        waitForVisibleElement(eleManageProcedureModal, 5);
        manageProcedure = new ManageProcedure(wedDriver);
        manageProcedure.createCustomProcedure(title, description, task);
    }

    /**
     * Check list associate ROMM to procedure
     * @param rommID
     * @param procedures
     */
    public void checkAssociateROMMProcedure(String rommID, List<String> procedures) {
        WebElement eleROMM = this.eleProcedureRomms.stream().filter(e -> rommID.equalsIgnoreCase(e.getText())).findFirst().orElse(null);
        Assert.assertNotNull("Can not find ROMM: " + rommID, eleROMM);
        if(eleROMM != null) {
            click(eleROMM);
            waitSomeSecond(1);
            PageFactory.initElements(wedDriver, this.eleProcedureProcedure);
            procedures.forEach(p-> {
                //Check it
                WebElement eleProcedure = this.eleProcedureProcedure.stream().filter(e -> e.findElement(By.cssSelector(".content")).getText().contains(p)).findFirst().orElse(null);
                Assert.assertNotNull("Procedure " + p + " is not exist", eleProcedure);
            });
        }
    }

    /**
     * Map a ROMM to a list standard procedures
     * @param rommID
     * @param procedures
     */
    public void mapROMMToStandardProcedure(String rommID, List<String> procedures) {
        WebElement eleROMM = this.eleProcedureRomms.stream().filter(e -> rommID.equalsIgnoreCase(e.getText())).findFirst().orElse(null);
        Assert.assertNotNull("Can not find ROMM: " + rommID, eleROMM);

        if(eleROMM != null) {
            click(eleROMM);
            waitSomeSecond(1);
            click(eleManageProcedures);
            manageProcedure = new ManageProcedure(wedDriver);
            manageProcedure.selectAssociateProcedure(true, procedures);
        }
    }

    /**
     * Map a romm to list custom procedures
     * @param rommID
     * @param procedures
     */
    public void mapROMToCustomProcedure(String rommID, List<String> procedures) {
        WebElement eleROMM = this.eleProcedureRomms.stream().filter(e -> rommID.equalsIgnoreCase(e.getText())).findFirst().orElse(null);
        Assert.assertNotNull("Can not find ROMM: " + rommID, eleROMM);

        if(eleROMM != null) {
            click(eleROMM);
            waitSomeSecond(1);
            click(eleManageProcedures);
            manageProcedure = new ManageProcedure(wedDriver);
            manageProcedure.selectAssociateProcedure(false, procedures);
        }
    }

    /**
     * Check list abcotd material
     * @param abcotds
     */
    public void checkListABCOTDMaterial(List<String> abcotds) {
        List<String> actual = new ArrayList<>();
        this.eleABCOTDsTable.forEach(e->{
            WebElement header = e.findElement(By.cssSelector(".risk-multi-abcotd-header-text"));
            actual.add(header.getText().replace("ABCOTD: ", "").trim());
        });

        CAssertion.assertEquals(abcotds, actual);
    }

    class ManageProcedure extends PageInit {

        @FindBy(css = ".manage-custom-procedures .procedures-left-container .menu-item:nth-child(1)")
        private WebElement eleSelectLibrary;

        @FindBy(css = ".manage-custom-procedures .procedures-left-container .menu-item:nth-child(2)")
        private WebElement eleCreateCustomProcedure;

        @FindBy(css = ".manage-custom-procedures .procedures-right-container .right-content-tab:nth-child(1)")
        private WebElement eleStandardProcedureTab;

        @FindBy(css = ".manage-custom-procedures .procedures-right-container .right-content-tab:nth-child(2)")
        private WebElement eleCustomProcedureTab;

        @FindBy(css = ".manage-custom-procedures .action-button-manage-custom-procedure .close-button")
        private WebElement eleCloseBtn;

        @FindBy(css = ".manage-custom-procedures .action-button-manage-custom-procedure .ok-button")
        private WebElement eleAddSelectedProcedure;

        @FindBy(css = ".manage-custom-procedures .procedure-title-textarea")
        private WebElement eleProcedureTitle;

        @FindBy(css = ".manage-custom-procedures textarea[name='productDescription']")
        private WebElement eleProcedureDescription;

        @FindBy(css = ".manage-custom-procedures textarea[name='taskDesciption']")
        private WebElement eleProcedureTask;

        @FindBys(@FindBy(css = ".manage-custom-procedures .custom-procedure-grid tbody tr"))
        private List<WebElement> eleListStandProcedures;

        @FindBys(@FindBy(css = ".manage-custom-procedures .custom-procedure-grid tbody tr"))
        private List<WebElement> eleListCustomProcedures;

        public ManageProcedure(WebDriver webDriver) {super(webDriver);}

        public void createCustomProcedure(String title, String description, String task) {
            click(eleCreateCustomProcedure);
            eleProcedureTitle.sendKeys(title);
            eleProcedureDescription.sendKeys(description);
            eleProcedureTask.sendKeys(task);
            click(eleAddSelectedProcedure);
            waitForVisibleElement(eleCustomProcedureTab, 30);
            click(eleCloseBtn);
            waitUtilElementHidden(eleManageProcedureModal, 30);
        }

        public void selectAssociateProcedure(boolean type, List<String> procedures) {
            if(type) {
                click(eleStandardProcedureTab);
            }else{
                click(eleCustomProcedureTab);
            }
            waitSomeSecond(1);

            for(String p : procedures) {
                WebElement tr = eleListStandProcedures.stream().filter(e->{
                    WebElement tdID = e.findElement(By.cssSelector("td:nth-child(2)"));
                    if(tdID.getText().toUpperCase().contains(p.toUpperCase())) {
                        return true;
                    }
                    return false;
                }).findFirst().orElse(null);
                if(tr == null) continue;

                WebElement eleCb = tr.findElement(By.cssSelector("td:nth-child(1) i"));
                if(!attributeElement(eleCb,"class").contains("check")) {
                   click(eleCb);
                }
            }
            try{
                waitForElementToBeClickable(eleAddSelectedProcedure, 5);
                RiskAssessmentPage.this.click(eleAddSelectedProcedure);
            }catch (TimeoutException e) {
                click(eleCloseBtn);
            }
            waitUtilElementHidden(eleManageProcedureModal, 30);
        }
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
            ele = eleLstTQ.stream().filter(e -> tdID.equals(e.findElement(By.cssSelector(".label-tpid")).getText())).findFirst().orElse(null);
        }

//        public void setEle(String tqID){
//            ele = eleLstTQ.stream().filter(e -> tqID.equals(e.findElement(By.cssSelector(".label-tpid")).getText())).findFirst().orElse(null);
//        }

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
