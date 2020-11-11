package stepdefs;

import com.sm.models.*;
import com.sm.page.RiskAssessmentPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RiskAssessmentDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public RiskAssessmentDefinition() {
        this.riskAssessmentPage = RiskAssessmentPage.getInstance(webDriver);
        this.riskAssessmentPage.isLoad();
    }

    @When("I answer all tailoring question")
    public void iAnswerAllTailoringQuestion(DataTable dataTable) {
        logger.info("I answer all Tailoring Question on Risk Assessment.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<TailoringQuestion> data = new ArrayList<>();
        lst.forEach(m -> data.add(new TailoringQuestion(m.get("id"), m.get("answered"))));
        riskAssessmentPage.answerTQ(data);
    }

    @When("I open {string} section on Risk Assessment")
    public void iOpenSection(String sectionName) {
        logger.info("I open '" + sectionName + "' section on Risk Assessment");
        riskAssessmentPage.openSection(sectionName);
    }

    @When("I select procedure to generate Working Paper")
    public void iSelectProcedureToGenerateWorkingPaper(DataTable dataTable) {
        logger.info("I select procedure to generate working paper on Risk Assessment.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<RiskAssessmentRelateRommProcedure> data = new ArrayList<>();
        lst.forEach(m -> data.add(new RiskAssessmentRelateRommProcedure(m.get("romm"), m.get("procedure"), Integer.parseInt(m.get("timing")), m.get("rollforward"))));
        riskAssessmentPage.selectProcedureWP(data);
    }

    @When("I select account mapping")
    public void iSelectAccountMapping(DataTable dataTable) {
        logger.info("I select account mapping on Risk Assessment.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<RiskAssessmentRelateProcedureAccount> data = new ArrayList<>();
        lst.forEach( m -> data.add(new RiskAssessmentRelateProcedureAccount(m.get("abcotd"), m.get("procedure"), m.get("account"))));
        riskAssessmentPage.selectAccountMapping(data);
    }

    @When("I generate Working Paper in RA")
    public void iGenerateWorkingPaperInRA() {
        logger.info("I generate Working Paper on Risk Assessment.");
        riskAssessmentPage.generateWorkingPaper();
    }

    @Then("I close Risk Assessment")
    public void iCloseRiskAssessment() {
        logger.info("I close Risk Assessment");
        riskAssessmentPage.closeWP(false);
    }

    @And("I select controls")
    public void iSelectControls(DataTable dataTable) {
        logger.info("I select Control in Risk Assessment.");
        List<String> lst = dataTable.asList(String.class);
        riskAssessmentPage.selectControls(lst);
    }

    @And("I generate Control Testing on RA")
    public void iGenerateControlTestingOnRA(DataTable dataTable) {
        logger.info("I generate Control Testing in Risk Assessment.");
        List<String> lst = dataTable.asList(String.class);
        riskAssessmentPage.generateControlTesting(lst);
    }

    @And("I create a custom ROMMs on Risk Assessment with following data")
    public void iCreateACustomROMMsOnRiskAssessmentWithFollowingData(DataTable dataTable) {
        logger.info("I create a custom ROMM on Risk Assessment");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Romm r = new Romm();
        r.setType(Integer.parseInt(lst.get(0).get("type")));
        r.setAssociateABCOTDs(new ABCOTD(lst.get(0).get("ABCOTD")));
        r.setTitle(lst.get(0).get("Title"));
        r.setDescription(lst.get(0).get("Description"));
        r.setAssertion(Arrays.asList(lst.get(0).get("Assertions").split(",")));
        r.setClassification(lst.get(0).get("Classification"));
        r.setRiskClassificationRationale(lst.get(0).get("Classification  Rationale"));

        riskAssessmentPage.createCustomROMM(r);
    }

    @Then("I should see the custom ROMM on ABCOTDs {string} via following information")
    public void iShouldSeeTheCustomROMMOnABCOTDsViaFollowingInformation(String abcotd, DataTable dataTable) {
        logger.info("I should see the ROMM on table ABCOTD: " + abcotd);
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<Romm> data = new ArrayList<>();
        lst.forEach( m -> {
            Romm r = new Romm();
            r.setId(m.get("ID"));
            r.setTitle(m.get("Title"));
            String[] arr = m.get("Assertion(s)").split(",");
            List<String> assertions = new ArrayList<>();
            for(String a : arr) {
                assertions.add(a.trim());
            }
            r.setAssertion(assertions);
            r.setClassification(m.get("Risk Classification"));
            data.add(r);
        });
        riskAssessmentPage.checkListROMMs(abcotd, data);
    }

    @And("I create a custom Control on Risk Assessment with following data")
    public void iCreateACustomControlOnRiskAssessmentWithFollowingData(DataTable dataTable) {
        logger.info("I create a custom control in Risk Assessment");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Control control = new Control();
        control.setAssociatedWith(Integer.parseInt(lst.get(0).get("Associate With")));
        String[] associatedABCOTDArr = lst.get(0).get("Associate ABCOTD").split(",");
        List<ABCOTD> abcotds = new ArrayList<>();
        for(String s : associatedABCOTDArr){
            abcotds.add(new ABCOTD(s.trim()));
        }
        control.setAssociateAbcotds(abcotds);
        control.setRelevant(Boolean.parseBoolean(lst.get(0).get("Relevant Control")));
        control.setTitle(lst.get(0).get("Control Title"));
        control.setDescription(lst.get(0).get("Control Description"));
        control.setAutomated(Boolean.parseBoolean(lst.get(0).get("Automated")));
        control.setFrequency(lst.get(0).get("Frequency"));
        control.setApproach(lst.get(0).get("Approach"));
        control.setType(lst.get(0).get("Type"));
        control.setDesignConclusion(lst.get(0).get("Design Conclusion"));
        control.setImplementationConclusion(lst.get(0).get("Implementation Conclusion"));
        control.setOeTestingStrategy(lst.get(0).get("OE Testing Strategy"));
        control.setOeDateLastTested(lst.get(0).get("OE Date Last Tested"));
        control.setOeConclusion(lst.get(0).get("OE Conclusion"));
        String[] associatedROMMArr = lst.get(0).get("Associated ROMMs").split(",");
        List<Romm> rooms = new ArrayList<>();
        for(String s: associatedROMMArr) {
             rooms.add(new Romm(s.trim()));
        }
        control.setAssociateROMMs(rooms);
        riskAssessmentPage.createCustomControl(control);
    }

    @Then("I should see the custom Control on Risk Assessment with folowwing data")
    public void iShouldSeeTheCustomControlOnRiskAssessmentWithFolowwingData(DataTable dataTable) {
        logger.info("I should see list of Control on Risk Assessment");
        List<Control> controls = new ArrayList<>();
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        lst.forEach(data -> {
            Control control = new Control();
            control.setRelevant(Boolean.parseBoolean(data.get("Relevant Control")));
            control.setControlId(data.get("ID"));
            control.setTitle(data.get("Control Title"));
            control.setDescription(data.get("Control Description"));
            control.setAutomated(Boolean.parseBoolean(data.get("Automated")));
            control.setFrequency(data.get("Frequency"));
            control.setApproach(data.get("Approach"));
            control.setType(data.get("Type"));
            control.setDesignConclusion(data.get("Design Conclusion"));
            control.setImplementationConclusion(data.get("Implementation Conclusion"));
            control.setOeTestingStrategy(data.get("OE Testing Strategy"));
            control.setOeDateLastTested(data.get("OE Date Last Tested"));
            control.setOeConclusion(data.get("OE Conclusion"));

            controls.add(control);
        });
        riskAssessmentPage.checkListControl(controls);
    }

    @Then("I should see list associate procedure with ROMM {string}")
    public void iShouldSeeListAssociateProcedureWithROMM(String rommID, DataTable dataTable) {
        logger.info("I should see list associate procedure with ROMM: " + rommID);
        List<String> procedures = dataTable.asList(String.class);
        riskAssessmentPage.checkAssociateROMMProcedure(rommID, procedures);
    }

    @And("I create a custom Procedure")
    public void iCreateACustomProcedure(DataTable dataTable) {
        logger.info("I create a custom procedure");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        String title = lst.get(0).get("Title");
        String description = lst.get(0).get("Description");
        String task = lst.get(0).get("Task");

        riskAssessmentPage.createCustomProcedure(title, description, task);
    }

    @And("I mark associate ROMM {string} with list standard procedures")
    public void iMarkAssociateROMMWithListStandardProcedures(String rommID, DataTable dataTable) {
        logger.info("I mark associate ROMM " + rommID + " with list standard procedures");
        List<String> procedures = dataTable.asList(String.class);
        riskAssessmentPage.mapROMMToStandardProcedure(rommID, procedures);
    }

    @And("I mark associate ROMM {string} with list custom procedures")
    public void iMarkAssociateROMMWithListCustomProcedures(String rommID, DataTable dataTable) {
        logger.info("I mark associate ROMM " + rommID + " with list custom procedures");
        List<String> procedures = dataTable.asList(String.class);
        riskAssessmentPage.mapROMToCustomProcedure(rommID, procedures);
    }

    @Then("I should see list ABCOTD Material as following")
    public void iShouldSeeListABCOTDMaterialAsFollowing(DataTable dataTable) {
        logger.info("I should see list ABCOTD Material");
        List<String> abcotds = dataTable.asList(String.class);
        riskAssessmentPage.checkListABCOTDMaterial(abcotds);
    }
}
