package stepdefs.dtwp;

import com.sm.page.dtwp.ARConfirmationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ARConfirmationDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public ARConfirmationDefinition() {
        arConfirmationPage = new ARConfirmationPage(webDriver);
    }

    @And("I type some data to AR Perform table")
    public void iTypeSomeDataToARPerformTable(DataTable dataTable) {
        logger.info("I type some data to AR Perform");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        arConfirmationPage.typeOnARPerform(data);
    }

    @And("I update alternative procedure on AR Confirmation")
    public void iUpdateAlternativeProcedureOnARConfirmation() {
        logger.info("I update Alternative Procedures on AR Confirmation");
        arConfirmationPage.updateAlternativeProcedure();
    }

    @And("I type some data to AR Lower Level table")
    public void iTypeSomeDataToARLowerLevelTable(DataTable dataTable) {
        logger.info("I type some data to AR Lower Level table");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        arConfirmationPage.typeOnARLowerLevel(data);
    }

    @And("I open Testing Sheet {int} on AR Confirmation")
    public void iOpenTestingSheetOnARConfirmation(int sheetIndex) {
        logger.info("I open index " + sheetIndex + " on AR Testing sheet");
        arConfirmationPage.openARConfirmationTestingSheet(sheetIndex);
    }

    @Then("I should see the AR Testing Sheet")
    public void iShouldSeeTheARTestingSheet() {
        logger.info("I should see the AR Testing Sheet");
        arConfirmationPage.checkTestingSheetDisplay();
    }

    @And("I type some data to AR Testing Sheet")
    public void iTypeSomeDataToARTestingSheet(DataTable dataTable) {
        logger.info("I type some data to AR Testing sheet");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        arConfirmationPage.typeOnARTestingSheet(data);
    }

    @And("I save and close AR Testing sheet")
    public void iSaveAndCloseARTestingSheet() {
        logger.info("I save and close AR Testing Sheet");
        arConfirmationPage.saveCloseTestingSheet();
    }

    @Then("I check value on AR Balance Summary table after saving Testing Sheet")
    public void iCheckValueOnARBalanceSummaryTableAfterSavingTestingSheet(DataTable dataTable) {
        logger.info("I check data on AR Balance Summary table");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int sheetIndex = Integer.parseInt(lst.get(0).get("Sheet Index"));
        double unexplainedDifference = Double.parseDouble(lst.get(0).get("Unexplained difference"));
        String wereAnyExpectedNote = lst.get(0).get("Were any exceptions noted?");
        arConfirmationPage.verifyDataOnBalanceSummaryTable(sheetIndex, unexplainedDifference, wereAnyExpectedNote);
    }
}
