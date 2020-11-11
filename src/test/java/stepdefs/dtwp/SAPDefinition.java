package stepdefs.dtwp;

import com.sm.models.AbcotdsMateriality;
import com.sm.models.Procedure;
import com.sm.models.Romm;
import com.sm.page.dtwp.SAPPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SAPDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public SAPDefinition() {
        sapPage = SAPPage.getInstance(webDriver);
    }

    @Then("I check amount on SAP Materiality widget")
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
        sapPage.verifyMateriality(materiality, pm, ctt, abcotdsMaterialities);
    }

    @Then("I check data on SAP Risk widget")
    public void iCheckDataOnRiskWidget(DataTable dataTable) {
        logger.info("I verify data SAP Risk Widget.");
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
        sapPage.verifyRisk(romms);
    }

    @Then("I check data on SAP Procedure widget")
    public void iCheckDataOnProcedureWidget(DataTable dataTable) {
        logger.info("I check data on SAP Procedure Widget.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Procedure p = new Procedure();
        p.setId(lst.get(0).get("procedureID"));
        p.setTitle(lst.get(0).get("title"));

        sapPage.verifyProcedure(p);
    }

    @And("I answer {string} to Tailoring question {string} in SAP")
    public void iAnswerToTailoringQuestion(String answer, String question) {
        logger.info("I answer '" + answer + "' to Tailoring Question '" + question + "'");
        sapPage.answerTailoringQuestion(answer, question);
    }

    @And("I answer {string} to Tailoring question index {int} in SAP")
    public void iAnswerToTailoringQuestionIndex(String answer, int index) {
        logger.info("I answer '" + answer + "' to Tailoring Question '" + index + "'");
        sapPage.answerTailoringQuestion(index, answer);
    }

    @And("Enable editing SAP Working Paper")
    public void enableEditingWorkingPaper() {
        logger.info("I enable Editing SAP Working Paper.");
        sapPage.enableEditing();
    }

    @And("I open {string} section on SAP Working Paper")
    public void iOpenSectionOnWorkingPaper(String section) {
        logger.info("I open '" + section + "' section in SAP Working Paper.");
        sapPage.openSectionByName(section);
    }

    @And("I save the SAP Working paper")
    public void iSaveTheWorkingPaper() {
        logger.info("I save the SAP Working Paper.");
        sapPage.save();
    }

    @Then("I close the SAP Working paper")
    public void iCloseTheWorkingPaper() {
        logger.info("I close the SAP Working Paper.");
        sapPage.closeWP(false);
    }

    @Then("I check the widget table {string} will be displayed in SAP")
    public void iCheckTheWidgetTableWillBeDisplayed(String title) {
        logger.info("I should see the widget table '" + title + "'");
        sapPage.verifyWidgetTableDisplayed(title);
    }

    @And("I type some data to widget table {string} in SAP")
    public void iTypeSomeDataToWidgetTable(String title, DataTable dataTable) {
        logger.info("I type data into widget table in SAP'" + title + "'");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        sapPage.typeDataIntoWidgetSampleTable(title, data);
    }

    @And("I add {int} rows to widget table {string} in SAP")
    public void iAddRowsToWidgetTable(int row, String title) {
        logger.info("I add " + row + " to widget table '" + title + "'");
        sapPage.addNewRowToWidgetTable(row, title);
    }

    @And("I generate {int} rows in SAP table")
    public void iGenerateSAPTable(int n) throws InterruptedException {
        logger.info("I generate" + n + "row in SAP table");
        sapPage.generateSAPTable(n);
    }

    @And("I open row {int} of testing sheet")
    public void iOpenTestingSheet(int index) {
        logger.info("I open row " + index + " of testing sheet");
        sapPage.openTestingSheet(index);
    }

    @And("I input value to Testing sheet in SAP")
    public void iInputValueToTestingSheet(DataTable dataTable) {
        logger.info("I input value to Testing Sheet in SAP");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        String title = lst.get(0).get("Title");
        double amount = Double.parseDouble(lst.get(0).get("Amount"));
        double expectation = Double.parseDouble(lst.get(0).get("Expectation"));
        double explainedDifference = Double.parseDouble(lst.get(0).get("ExplainedDifference"));
        this.sapPage.inputTestingSheet(title,amount,expectation,explainedDifference);
    }

    @And("I save the Testing Sheet in SAP")
    public void iSaveTheTestingSheet() {
        logger.info("I save the Testing Sheet in SAP");
        sapPage.saveTestingSheet();
    }

    @Then("I close the Testing Sheet in Sap")
    public void iCloseTheTestingSheet() {
        logger.info("I close the Testing Sheet in Sap");
        sapPage.closeTestingSheet(false);
    }

    @Then("I check the Vertical will be displayed in SAP")
    public void iCheckTheVerticalWillBeDisplayed() {
        logger.info("I should see the Vertical Table in SAP Working Paper.");
        sapPage.verifyVerticalTableDisplayed();
    }

    @Then("I check the Complex Text {string} will be displayed in SAP")
    public void iCheckTheComplexTextWillBeDisplayed(String name) {
        logger.info("I should see the Complex Text in Working Paper");
        sapPage.verifyComplexTextDisplay(name);
    }

    @Then("I check the Tailoring Question {string} will be displayed in SAP")
    public void iCheckTheTailoringQuestionWillBeDisplayed(String heading) {
        logger.info("I should see the Tailoring Question in SAP'" + heading + "'");
        sapPage.verifyTailoringQuestionDisplayed(heading);
    }

    @Then("I check the {string} section will be activated in SAP")
    public void iCheckTheSectionWillBeActivated(String sectionName) {
        logger.info("I check '" + sectionName+ "' section will be activated in SAP");
        sapPage.verifySectionActivated(sectionName);
    }
}
