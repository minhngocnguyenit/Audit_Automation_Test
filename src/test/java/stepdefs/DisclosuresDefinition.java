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

public class DisclosuresDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public DisclosuresDefinition() {
        this.fslPage = FSLPage.getInstance(webDriver);
        this.fslPage.isLoad();
    }

    @When("I answer all Disclosures tailoring question")
    public void iAnswerAllTailoringQuestion(DataTable dataTable) {
        logger.info("I answer all Tailoring Question in Disclosures.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<TailoringQuestion> data = new ArrayList<>();
        lst.forEach(m -> data.add(new TailoringQuestion(m.get("id"), m.get("answered"))));
        fslPage.answerFslTQ(data);
    }

    @When("I open {string} section on Disclosures")
    public void iOpenSection(String sectionName) {
        logger.info("I open '" + sectionName + "'section in Disclosures.");
        fslPage.openFslSection(sectionName);
    }

    @When("I select procedure to generate Disclosures Working Paper")
    public void iSelectProcedureToGenerateWorkingPaper(DataTable dataTable) {
        logger.info("I select procedure to generate Working Paper in Disclosures.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<RiskAssessmentRelateRommProcedure> data = new ArrayList<>();
        lst.forEach(m -> data.add(new RiskAssessmentRelateRommProcedure(m.get("romm"), m.get("procedure"), Integer.parseInt(m.get("timing")), m.get("rollforward"))));
        fslPage.selectFslProcedureWP(data);
    }

    @When("I generate Working Paper in Disclosures")
    public void iGenerateWorkingPaperInRA() {
        logger.info("I generate Working Paper in Disclosures.");
        fslPage.generateFslWorkingPaper();
    }

    @Then("I close Disclosures")
    public void iCloseRiskAssessment() {
        logger.info("I close Disclosures.");
        fslPage.closeWP(false);
    }

    @And("I select Disclosures controls")
    public void iSelectControls(DataTable dataTable) {
        logger.info("I select Control in Disclosures.");
        List<String> lst = dataTable.asList(String.class);
        fslPage.selectFslControls(lst);
    }

    @And("I generate Control Testing on Disclosures")
    public void iGenerateControlTestingOnRA(DataTable dataTable) {
        logger.info("I generate Control Testing in Disclosures.");
        List<String> lst = dataTable.asList(String.class);
        fslPage.generateFslControlTesting(lst);
    }
}
