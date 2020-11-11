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

public class BareboneDisclosureDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public BareboneDisclosureDefinition() {
        bareboneAccountPage = BareboneAccountPage.getInstance(webDriver);
        bareboneAccountPage.isLoad();
    }

    @Then("I check amount on Materiality widget of Barebone Disclosure")
    public void iCheckAmountOnMaterialityWidget(DataTable dataTable) {
        logger.info("I verify data on Materiality Widget of Barebone Disclosure.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double materiality = Double.parseDouble(lst.get(0).get("Materiality"));
        double pm = Double.parseDouble(lst.get(0).get("PerformanceMateriality"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));
        bareboneAccountPage.verifyMaterialityOverall(materiality, pm, ctt);
    }

    @Then("I check data on Risk widget of Barebone Disclosure")
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

    @Then("I check data on Procedure widget of Barebone Disclosure")
    public void iCheckDataOnProcedureWidget(DataTable dataTable) {
        logger.info("I check data on Procedure Widget.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Procedure p = new Procedure();
        p.setId(lst.get(0).get("procedureID"));
        p.setTitle(lst.get(0).get("title"));

        bareboneAccountPage.verifyProcedure(p);
    }

    @And("I answer {string} to Tailoring question {string} of Barebone Disclosure")
    public void iAnswerToTailoringQuestion(String answer, String question) {
        logger.info("I answer '" + answer + "' to Tailoring Question '" + question + "'");
        bareboneAccountPage.answerTailoringQuestion(answer, question);
    }

    @And("Enable editing Working Paper of Barebone Disclosure")
    public void enableEditingWorkingPaper() {
        logger.info("I enable Editing Working Paper.");
        bareboneAccountPage.enableEditing();
    }

//    @Then("I check the Complex Memo will be displayed in Barebone Disclosure")
//    public void iCheckTheTextMemoWillBeDisplayed() {
//        logger.info("I should see the Text Memo on Working Paper.");
//        bareboneAccountPage.verifyComplexMemoDisplayed();
//    }

    @And("I open {string} section on Barebone Disclosure Working Paper")
    public void iOpenSectionOnWorkingPaper(String section) {
        logger.info("I open '" + section + "' section in Working Paper.");
        bareboneAccountPage.openSectionByName(section);
    }

    @Then("I check the Freeform Table will be displayed in Barebone Disclosure")
    public void iCheckTheFreeformTableWillBeDisplayed() {
        logger.info("I should be the FreeFrom Table in Disclosure Working Paper.");
        bareboneAccountPage.verifyFreeformTableDisplayed();
    }

    @And("I save the Barebone Disclosure Working paper")
    public void iSaveTheWorkingPaper() {
        logger.info("I save the Working Paper.");
        bareboneAccountPage.save();
    }

    @Then("I check the Complex Text {string} will be displayed in Barebone Disclosure")
    public void iCheckTheComplexTextWillBeDisplayed(String name) {
        logger.info("I should see the Complex Text in Working Paper");
        bareboneAccountPage.verifyComplexTextDisplay(name);
    }

    @Then("I close the Barebone Disclosure Working paper")
    public void iCloseTheWorkingPaper() {
        logger.info("I close the Working Paper.");
        bareboneAccountPage.closeWP(false);
    }

    @Then("I check the Vertical will be displayed in Barebone Disclosure")
    public void iCheckTheVerticalWillBeDisplayed() {
        logger.info("I should see the Vertical Table in Working Paper.");
        bareboneAccountPage.verifyVerticalTableDisplayed();
    }

    @Then("I check the Tailoring Question {string} will be displayed in Barebone Disclosure")
    public void iCheckTheTailoringQuestionWillBeDisplayed(String heading) {
        logger.info("I should see the Tailoring Question '" + heading + "'");
        bareboneAccountPage.verifyTailoringQuestionDisplayed(heading);
    }

    @Then("I check the {string} section will be activated in Barebone Disclosure")
    public void iCheckTheSectionWillBeActivated(String sectionName) {
        logger.info("I check '" + sectionName+ "' section will be activated");
        bareboneAccountPage.verifySectionActivated(sectionName);
    }

}
