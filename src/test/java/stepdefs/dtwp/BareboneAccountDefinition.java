package stepdefs.dtwp;

import com.sm.models.AbcotdsMateriality;
import com.sm.models.Procedure;
import com.sm.models.Romm;
import com.sm.page.dtwp.BareboneAccountPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BareboneAccountDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public BareboneAccountDefinition() {
        bareboneAccountPage = BareboneAccountPage.getInstance(webDriver);
        bareboneAccountPage.isLoad();
    }

    @Then("I check amount on Materiality widget")
    public void iCheckAmountOnMaterialityWidget(DataTable dataTable) {
        logger.info("I verify data on Materiality Widget.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double materiality = Double.parseDouble(lst.get(0).get("Materiality"));
        double pm = Double.parseDouble(lst.get(0).get("PerformanceMateriality"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));

        List<AbcotdsMateriality> abcotdsMaterialities = new ArrayList<>();
        if(lst.size() > 1) {
            for(int i = 1; i < lst.size(); i++) {
                Map<String, String> item = lst.get(i);
                abcotdsMaterialities.add(new AbcotdsMateriality(item.get("Overall"), item.get("Materiality"), item.get("PerformanceMateriality")));
            }
        }
        bareboneAccountPage.verifyMateriality(materiality, pm, ctt, abcotdsMaterialities);
    }

    @Then("I check data on Risk widget")
    public void iCheckDataOnRiskWidget(DataTable dataTable) {
        logger.info("I verify data Risk Widget.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<Romm> romms = new ArrayList<>();
        lst.forEach(d ->{
            Romm r = new Romm();
            r.setId(d.get("riskID"));
            r.setTitle(d.get("title"));
            r.setAssertion(Arrays.asList(d.get("assertion").split(",")));
            r.setClassification(d.get("classification"));
            romms.add(r);
        });
        bareboneAccountPage.verifyRisk(romms);
    }

    @Then("I check data on Procedure widget")
    public void iCheckDataOnProcedureWidget(DataTable dataTable) {
        logger.info("I check data on Procedure Widget.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Procedure p = new Procedure();
        p.setId(lst.get(0).get("procedureID"));
        p.setTitle(lst.get(0).get("title"));

        bareboneAccountPage.verifyProcedure(p);
    }

    @And("I answer {string} to Tailoring question {string}")
    public void iAnswerToTailoringQuestion(String answer, String question) {
        logger.info("I answer '" + answer + "' to Tailoring Question '" + question + "'");
        bareboneAccountPage.answerTailoringQuestion(answer, question);
    }

    @And("Enable editing Working Paper")
    public void enableEditingWorkingPaper() {
        logger.info("I enable Editing Working Paper.");
        bareboneAccountPage.enableEditing();
    }

    @Then("I check the Complex Memo will be displayed")
    public void iCheckTheTextMemoWillBeDisplayed() {
        logger.info("I should see the Text Memo on Working Paper.");
        bareboneAccountPage.verifyComplexMemoDisplayed();
    }

    @And("I open {string} section on Working Paper")
    public void iOpenSectionOnWorkingPaper(String section) {
        logger.info("I open '" + section + "' section in Working Paper.");
        bareboneAccountPage.openSectionByName(section);
    }

    @Then("I check the Freeform Table will be displayed")
    public void iCheckTheFreeformTableWillBeDisplayed() {
        logger.info("I should be the FreeFrom Table in Working Paper.");
        bareboneAccountPage.verifyFreeformTableDisplayed();
    }

    @And("I save the Working paper")
    public void iSaveTheWorkingPaper() {
        logger.info("I save the Working Paper.");
        bareboneAccountPage.save();
    }

    @Then("I check the Complex Text {string} will be displayed")
    public void iCheckTheComplexTextWillBeDisplayed(String name) {
        logger.info("I should see the Complex Text in Working Paper");
        bareboneAccountPage.verifyComplexTextDisplay(name);
    }

    @Then("I close the Working paper")
    public void iCloseTheWorkingPaper() {
        logger.info("I close the Working Paper.");
        bareboneAccountPage.closeWP(false);
    }

    @Then("I check the Vertical will be displayed")
    public void iCheckTheVerticalWillBeDisplayed() {
        logger.info("I should see the Vertical Table in Working Paper.");
        bareboneAccountPage.verifyVerticalTableDisplayed();
    }

    @Then("I check the Tailoring Question {string} will be displayed")
    public void iCheckTheTailoringQuestionWillBeDisplayed(String heading) {
        logger.info("I should see the Tailoring Question '" + heading + "'");
        bareboneAccountPage.verifyTailoringQuestionDisplayed(heading);
    }

    @Then("I check the {string} section will be activated")
    public void iCheckTheSectionWillBeActivated(String sectionName) {
        logger.info("I check '" + sectionName+ "' section will be activated");
        bareboneAccountPage.verifySectionActivated(sectionName);
    }

    @Then("I should see the answer {string} is selected on Tailoring question {string} by another Working Paper {string}")
    public void iShouldSeeTheAnswerIsSelectedOnTailoringQuestionByAnotherWorkingPaper(String answer, String heading, String working) {
        logger.info(String.format("I should see the answer '%s' on Tailoring question '%s' is selected by another Working paper '%s'", answer, heading, working));
        bareboneAccountPage.verifyAnswerOnTQIsSelectByWP(answer, heading, working);
    }

    @And("I mark Prepare Sign Off")
    public void iMarkPrepareSignOff() {
        logger.info("I mark prepare SignOff");
        bareboneAccountPage.markPrepareSignOff();
    }

    @Then("I should see have a Prepare Sign Off on working paper")
    public void iShouldSeeHaveAPrepareSignOffOnWorkingPaper() {
        logger.info("I should see have a prepare sign off on WP");
        bareboneAccountPage.verifyExistingPrepareSignOff();
    }
}
