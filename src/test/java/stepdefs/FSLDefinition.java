package stepdefs;

import com.sm.models.RiskAssessmentRelateRommProcedure;
import com.sm.models.TailoringQuestion;
import com.sm.page.FSLPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FSLDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public FSLDefinition() {
        this.fslPage = FSLPage.getInstance(webDriver);
        this.fslPage.isLoad();
    }

    @When("I answer all FSL tailoring question")
    public void iAnswerAllTailoringQuestion(DataTable dataTable) {
        logger.info("I answer Tailoring Question on FSL.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<TailoringQuestion> data = new ArrayList<>();
        lst.forEach(m -> data.add(new TailoringQuestion(m.get("id"), m.get("answered"))));
        fslPage.answerFslTQ(data);
    }

    @When("I open {string} section on FSL")
    public void iOpenSection(String sectionName) {
        logger.info("I open '" + sectionName + "' section on FSL.");
        fslPage.openFslSection(sectionName);
    }

    @When("I select procedure to generate FSL Working Paper")
    public void iSelectProcedureToGenerateWorkingPaper(DataTable dataTable) {
        logger.info("I select procedure to generate Working Paper on FSL.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<RiskAssessmentRelateRommProcedure> data = new ArrayList<>();
        lst.forEach(m -> data.add(new RiskAssessmentRelateRommProcedure(m.get("romm"), m.get("procedure"), Integer.parseInt(m.get("timing")), m.get("rollforward"))));
        fslPage.selectFslProcedureWP(data);
    }

    @When("I generate Working Paper in FSL")
    public void iGenerateWorkingPaperInRA() {
        logger.info("I generate Working Paper on FSL");
        fslPage.generateFslWorkingPaper();
    }

    @Then("I close Financial Statement level")
    public void iCloseRiskAssessment() {
        logger.info("I close FSL");
        fslPage.closeWP(false);
    }

    @And("I select FSL controls")
    public void iSelectControls(DataTable dataTable) {
        logger.info("I select Control on FSL.");
        List<String> lst = dataTable.asList(String.class);
        fslPage.selectFslControls(lst);
    }

    @And("I generate Control Testing on FSL")
    public void iGenerateControlTestingOnRA(DataTable dataTable) {
        logger.info("I generate Control Testing on FSL.");
        List<String> lst = dataTable.asList(String.class);
        fslPage.generateFslControlTesting(lst);
    }
}
